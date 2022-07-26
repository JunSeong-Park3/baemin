package com.han.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.han.delivery.dto.StoreDetailDto;
import com.han.delivery.dto.StoreDto;
import com.han.delivery.service.StoreService;

@Controller
public class StoreController {
	
	@Autowired
	StoreService storeService;
	
	@GetMapping("/store/{category}/{address1}")
	public String store(@PathVariable long category, @PathVariable long address1, Model model) {
		
		List<StoreDto> storeList = storeService.storeList(category, address1 / 100);
		model.addAttribute("storeList", storeList);
		
		return "store/store";
	}
	
	@GetMapping("/store/{id}/detail")
	public String storeDetail(@PathVariable long id, Model model) {
 
		StoreDetailDto storeDetailDto = storeService.storeDetail(id);
 
		model.addAttribute("store", storeDetailDto);
 
		return "store/detail";
	}
	
	
}
