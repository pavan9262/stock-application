package com.zensar.stockapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	
	private long stockId;
	private String name;
	private String marketName;
	private double price;
}
