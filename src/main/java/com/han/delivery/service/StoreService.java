package com.han.delivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.han.delivery.dao.StoreMapper;
import com.han.delivery.dto.FoodDto;
import com.han.delivery.dto.FoodOptionDto;
import com.han.delivery.dto.StoreDetailDto;
import com.han.delivery.dto.StoreDto;

@Service
public class StoreService {

	@Autowired
	StoreMapper storeMapper;

	@Transactional
	public List<StoreDto> storeList(long category, long address) {
		return storeMapper.storeList(category, address);
	}

	@Transactional
	public StoreDetailDto storeDetail(long storeId) {
		StoreDto storeDto = storeMapper.storeDetail(storeId); 
		List<FoodDto> foodList = storeMapper.foodList(storeId);
		
		return new StoreDetailDto(storeDto, foodList);
	}
	
	@Transactional
	public List<FoodOptionDto> foodOption(long foodId) {
		return storeMapper.foodOption(foodId);
	}

}