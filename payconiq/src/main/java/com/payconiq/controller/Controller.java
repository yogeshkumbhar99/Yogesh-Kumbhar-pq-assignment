package com.payconiq.controller;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.payconiq.dto.StockDetails;
import com.payconiq.dto.entity.StockEntity;
import com.payconiq.exception.ResourceNotFoundException;
import com.payconiq.service.StockServices;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class Controller {

	@Autowired
	private StockServices service;

	@GetMapping(path = "/stocks")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<StockEntity>> findAll(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
		log.info("Inside Get All Stocks");
		List<StockEntity> as = service.getAllStocksDetails(pageNo,pageSize,sortBy);
		return ResponseEntity.ok(as);
	}

	@GetMapping(path = "/stocks/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<StockEntity> findById(@PathVariable(value = "id") Long stockId) throws ResourceNotFoundException {
		log.info("Inside Get Stocks by Stock id");
		StockEntity as = service.getStockByID(stockId);
		return ResponseEntity.ok(as);
	}
	
	@PostMapping(path = "/stocks", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, String> createStocks(@RequestBody StockDetails stockDetails) {
		log.info("Inside Stocks Details");
			String resposne = service.createStocksDetails(stockDetails);
		return Collections.singletonMap("Response", resposne);
	}

	@PatchMapping(path = "/stocks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<StockEntity> updateStocks(@RequestBody StockDetails stockDetails,@PathVariable(value = "id") Long stockId) throws ResourceNotFoundException {
		log.info("Inside Stocks Details");
			StockEntity resposne = service.updateStocksDetails(stockDetails,stockId);
		return ResponseEntity.ok(resposne);
	}
	@DeleteMapping(path = "/stocks/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Map<String, String> deleteById(@PathVariable(value = "id") Long stockId) throws ResourceNotFoundException {
		log.info("Inside Delete Stocks by Stock id");
		String resposne = service.deleteStockByID(stockId);
		return Collections.singletonMap("Response", resposne);
	}
}
