package com.han.delivery.dto;

import lombok.Data;

@Data
public class FoodDto {
	private long id;
    private long storeId;
    private String foodName;
    private long foodPrice;
    private String foodDec;
    private String foodImg;
    private String foodThumb;
}
