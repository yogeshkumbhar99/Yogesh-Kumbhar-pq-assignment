package com.payconiq.service;

import java.util.List;

import com.payconiq.dto.StockDetails;
import com.payconiq.dto.entity.StockEntity;
import com.payconiq.exception.ResourceNotFoundException;

public interface StockServices {

	public List<StockEntity> getAllStocksDetails(Integer pageNo, Integer pageSize, String sortBy);

	public String createStocksDetails(StockDetails stockDetails);

	public StockEntity getStockByID(Long stockId) throws ResourceNotFoundException;

	public StockEntity updateStocksDetails(StockDetails stockDetails, Long stockId) throws ResourceNotFoundException;

	public String deleteStockByID(Long stockId) throws ResourceNotFoundException;
}
