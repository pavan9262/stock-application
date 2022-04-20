package com.zensar.stockapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zensar.stockapplication.entity.Stock;

@SpringBootApplication
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
		
		/*Stock s=new Stock();
		
		s.getName();
		s.getPrice();
		s.getMarketName();
		s.getStockId();
		
		s.setStockId(20L);*/
	
	}

}
