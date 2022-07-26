package com.han.delivery.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.han.delivery.dto.CartListDto;
import com.han.delivery.utils.CreateOrderNum;

@Controller
public class OrderController {
	
	@GetMapping("/order")
	public String order(HttpSession session, Model model) {
 
		CartListDto cartListDto = (CartListDto) session.getAttribute("cartList");
 
		model.addAttribute("cartList", cartListDto);
 
 
		String orderNum = CreateOrderNum.createOrderNum();
		model.addAttribute("orderNum", orderNum);
		return "order/order";
	}
}
