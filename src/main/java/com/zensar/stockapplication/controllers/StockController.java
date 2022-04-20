package com.zensar.stockapplication.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.stockapplication.entity.Stock;

@RestController
//@Controller
//@CrossOrigin("*")
//@ImportResource("beans.xml")
@RequestMapping("/stocks")
public class StockController {

	List<Stock> stocks = new ArrayList<Stock>();

	public StockController() {

		stocks.add(new Stock(2l, "REL", "Jio", 2675));
		stocks.add(new Stock(1l, "Zen", "gen", 2375));
		stocks.add(new Stock(3l, "air", "gen", 2665));

	}

	//@GetMapping
	//@ResponseBody
	//Getting all Stocks
	//http://localhost:9090/stocks
	@RequestMapping(method=RequestMethod.GET)
	public List<Stock> getAllStock() {
		return stocks;
	}
	
	@RequestMapping(value ="/test", method= {RequestMethod.GET,RequestMethod.POST})
	public void test() {
		System.out.println("Iam inside test method");
	}

	/*//@GetMapping("/{stockId}")
	@RequestMapping(value="/{stockId}", method=RequestMethod.GET)
	public Stock getStock(@PathVariable("stockId") long id) {

		for (Stock stock : stocks) {

			if (stock.getStockId() == id) {
				return stock;
			}
		}
		return null;
	}*/
	
	//@GetMapping("/{stockId}")
		//@ResponseBody
		//Getting particular Stock..
		//http://localhost:9090/stocks/id number
		@RequestMapping(value="/{stockId}", method=RequestMethod.GET)
		public Stock getStock(@PathVariable("stockId") long id) {
			
			Optional<Stock> stock1=stocks.stream().filter(stock -> stock.getStockId()==id).findAny();
			
			if(stock1.isPresent()) {
				return stock1.get();
			}else {
				return stock1.orElseGet(()-> {return new Stock();});
			}
			
		}
	
	/*@GetMapping("/stockId")
	public Stock getStockByRequestParam(@RequestParam(required=false, value="id", defaultValue="2") long id) {

		for (Stock stock : stocks) {

			if (stock.getStockId() == id) {
				return stock;
			}
		}
		return null;
	}*/

	//@PostMapping
	//@ResponseBody
		//Creating stock
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Stock> CreateStock(@RequestBody Stock stock, @RequestHeader("auth-code")String code) {
		if(code.contentEquals("NP66472")){
		stocks.add(stock);
		}else {
			return new ResponseEntity<Stock>(HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<Stock>(stock, HttpStatus.CREATED);
	}
	
	//Deleting particular Stock
	@DeleteMapping("/{stockId}")
	public String deleteStock(@PathVariable("stockId") long stockid) {
		for(Stock stock:stocks) {
			if(stock.getStockId()==stockid) {
				
				stocks.remove(stock);
				return "stock deleted successfully"+stockid;
				
			}
		}
		return "stock is not available";
	}
	
	//Updating particular Stock
	@PutMapping("/{stockId}")
	public Stock updateStock(@PathVariable int stockid, @RequestBody Stock stock) {
		
		Stock availableStock=getStock(stockid);
		availableStock.setMarketName(stock.getMarketName());
		availableStock.setName(stock.getName());
		availableStock.setPrice(stock.getPrice());
		
		return availableStock;
	}
	
	
	//Deleting all Stocks
	@DeleteMapping()
	public ResponseEntity<String> deleteAllStocks(){
		
		stocks.removeAll(stocks);
		
		return new ResponseEntity<String>("All Stocks deleted Successfully", HttpStatus.OK);
	}
	
	
	
}
