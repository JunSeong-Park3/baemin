package com.han.delivery.dto;

import lombok.Data;

@Data
public class StoreDto {
	
	private long id;
	private long category;
	private String storeName;
	private long storeAddress1;
	private String storeAddress2;
	private String storeAddress3;
	private String storePhone;
	private String storeImg;
	private String storeThumb;
	private int openingTime;
	private int closingTime;
	private int minDelivery;
	private int deliveryTime;
	private int deliveryTip;
	private String storeDes;
	private int isOpen;  // 7/22 추가 필드
}