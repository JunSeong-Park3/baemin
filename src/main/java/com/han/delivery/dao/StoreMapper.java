package com.han.delivery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.han.delivery.dto.FoodDto;
import com.han.delivery.dto.FoodOptionDto;
import com.han.delivery.dto.StoreDto;

@Mapper
public interface StoreMapper {
	public List<StoreDto> storeList(long category, long address);
	public StoreDto storeDetail(long storeId);
	public List<FoodDto> foodList(long storeId);
	public List<FoodOptionDto> foodOption(long foodId);
}