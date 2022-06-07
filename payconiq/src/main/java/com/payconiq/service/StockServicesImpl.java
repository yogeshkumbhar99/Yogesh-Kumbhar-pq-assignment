package com.payconiq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.payconiq.dto.StockDetails;
import com.payconiq.dto.entity.StockEntity;
import com.payconiq.exception.DuplicateStockException;
import com.payconiq.exception.ResourceNotFoundException;
import com.payconiq.repository.StockRepository;

@Component
public class StockServicesImpl implements StockServices {

	@Autowired
	private StockRepository stockRepository;

	@Override
	public List<StockEntity> getAllStocksDetails(Integer pageNo, Integer pageSize, String sortBy) {
		 Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		 
	        Page<StockEntity> pagedResult = stockRepository.findAll(paging);
	         
	        if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<StockEntity>();
	        }
	}

	@Override
	public String createStocksDetails(StockDetails stockDetails) {
		String resposneMessage = "Stock saved successfully";
		Iterable<StockEntity> entityResa = stockRepository.findAll();
		for (StockEntity stockEntity : entityResa) {
			if(stockEntity.getName().equals(stockDetails.getName())) {
				throw new DuplicateStockException("Stocks already exist");
			}
		}
		createStock(stockDetails);
		return resposneMessage;
	}

	private void createStock(StockDetails stockDetails) {
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		StockEntity entity = new StockEntity();
		// entity.setId(stockDetails.getId());
		entity.setName(stockDetails.getName());
		entity.setCreateDate(date);
		entity.setInitialQuantity(stockDetails.getInitialQuantity());
		entity.setCurrentPrice(stockDetails.getCurrentPrice());
		stockRepository.save(entity);
	}

	@Override
	public StockEntity getStockByID(Long stockId) throws ResourceNotFoundException {
		return stockRepository.findById(stockId).orElseThrow(() -> new ResourceNotFoundException("Stock Id not exist " + stockId));
	}

	@Override
	public StockEntity updateStocksDetails(StockDetails stockDetails, Long stockId) throws ResourceNotFoundException {
		StockEntity entity = stockRepository.findById(stockId).orElseThrow(() -> new ResourceNotFoundException("Stock id does not exist " + stockId));
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		entity.setName(stockDetails.getName());
		entity.setLastUpdated(date);
		entity.setInitialQuantity(stockDetails.getInitialQuantity());
		entity.setCurrentPrice(stockDetails.getCurrentPrice());
		StockEntity updatedEntity = stockRepository.save(entity);
		return updatedEntity;
	}

	@Override
	public String deleteStockByID(Long stockId) throws ResourceNotFoundException {
		String resposneMessage = "Stock deleted successfully";
		StockEntity entity = stockRepository.findById(stockId).orElseThrow(() -> new ResourceNotFoundException("Stock id does not exist " + stockId));
		stockRepository.delete(entity);
		return resposneMessage;
	}
}
