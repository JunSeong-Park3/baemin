package com.han.delivery.api;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.han.delivery.config.auth.CustomUserDetails;
import com.han.delivery.dto.CartListDto;
import com.han.delivery.dto.OrderInfoDto;
import com.han.delivery.service.OrderService;

@RestController
public class OrderApiController {
	
	@Autowired
	OrderService orderService;
	
	
	@PostMapping("/api/order/payment-cash")
	public ResponseEntity<?> payment(HttpSession session, OrderInfoDto orderInfoDto, long totalPrice, @AuthenticationPrincipal CustomUserDetails principal) throws IOException {
	    
	    CartListDto cartListDto = (CartListDto) session.getAttribute("cartList");
	    
	    long orderPriceCheck = orderService.orderPriceCheck(cartListDto);
	    
	    System.out.println("계산금액 = " + totalPrice + " 실제 계산해야할 금액 = " + orderPriceCheck );
	    
	    if(orderPriceCheck == totalPrice) {
	        orderService.order(cartListDto, orderInfoDto, principal, session);
	        session.removeAttribute("cartList");
	    }
	 
	    return ResponseEntity.ok().body("주문금액 일치");
	}
	

}
