package com.han.delivery.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreDetailDto {
//	private StoreDto storeDto; 
	private StoreDto storeInfo;
	private List<FoodDto> foodList;
}
