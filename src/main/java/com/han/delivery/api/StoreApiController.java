package com.han.delivery.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.han.delivery.dto.FoodOptionDto;
import com.han.delivery.service.StoreService;

@RestController
public class StoreApiController {
	
	@Autowired
	StoreService storeService;
	
	
	// 메뉴 클릭시 음식 추가옵션 가져요기
	@GetMapping("/api/food/{foodId}/option")
	public List<FoodOptionDto> menuDetail(@PathVariable long foodId) {
		List<FoodOptionDto> foodOption = storeService.foodOption(foodId);
		return foodOption;
	}
	
}
