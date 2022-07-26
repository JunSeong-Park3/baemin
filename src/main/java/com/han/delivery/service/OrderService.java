package com.han.delivery.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.han.delivery.config.auth.CustomUserDetails;
import com.han.delivery.dao.OrderMapper;
import com.han.delivery.dto.CartDto;
import com.han.delivery.dto.CartListDto;
import com.han.delivery.dto.OrderDetailDto;
import com.han.delivery.dto.OrderInfoDto;

@Service
public class OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	@Transactional
	public long orderPriceCheck(CartListDto cartListDto) {
		 
		System.out.println("cartDetail = " + cartListDto);
 
		List<CartDto> cart = cartListDto.getCartDto();
		List<Integer> foodPriceList = orderMapper.foodPriceList(cart);
		List<Integer> optionPriceList = orderMapper.optionPriceList(cart);
		int deliveryTip = orderMapper.getDeliveryTip(cartListDto.getStoreId());
		System.out.println("foodPriceList = " + foodPriceList);
		System.out.println("optionPriceList = " + optionPriceList);
		
		int sum = 0;
		
		for (int i = 0; i < cart.size(); i++) {
			int foodPrice = foodPriceList.get(i);
			int amount = cart.get(i).getAmount();
			int optionPrice = optionPriceList.get(i);
 
			sum += (foodPrice + optionPrice) * amount;
		}
 
		return sum + deliveryTip; 
	}
	
	@Transactional
	public String order(CartListDto cartListDto, OrderInfoDto orderInfoDto, CustomUserDetails principal, HttpSession session) {
		Gson gson = new Gson();
		
		System.out.println("info = " + orderInfoDto);
		
		int total = cartListDto.getCartTotal();
		
		orderInfoDto.setStoreId(cartListDto.getStoreId());
		orderInfoDto.setTotalPrice(total);
		
		long userId = 0;
		if (principal != null) {
			userId = principal.getId();
			orderInfoDto.setUserId(userId);
		}
		
		List<CartDto> cartList = cartListDto.getCartDto();
		
		OrderDetailDto[] orderDetailDto = new OrderDetailDto[cartList.size()];
		
		
		for(int i=0;i<orderDetailDto.length;i++) {
			String cartJSON = gson.toJson(cartList.get(i));
			orderDetailDto[i] = new OrderDetailDto(orderInfoDto.getOrderNum(), cartJSON);
		}
		
		orderMapper.order(orderInfoDto);
		
		
		  Map<String, Object> orderDetailMap = new HashMap<>(); 
		  orderDetailMap.put("userId", userId);
		  orderDetailMap.put("detail", orderDetailDto); 
		  orderMapper.orderDetail(orderDetailMap);
		 
		return null;
	}
	
	
	
}
