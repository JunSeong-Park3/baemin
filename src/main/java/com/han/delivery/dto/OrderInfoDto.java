package com.han.delivery.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OrderInfoDto {
	
	private String orderNum;
	private long storeId;
	private long userId;
	private Date orderDate;
	private String deleveryStatus;
	private String phone;
	private int deleveryAddress1;
	private String deleveryAddress2;
	private String deleveryAddress3;
	private int totalPrice;
	private int usedPoint;
	private String request;
	private String impUid;
	
}
