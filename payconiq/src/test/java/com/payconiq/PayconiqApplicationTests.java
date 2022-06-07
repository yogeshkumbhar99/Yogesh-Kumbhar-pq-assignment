package com.payconiq;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.payconiq.dto.StockDetails;
import com.payconiq.dto.entity.StockEntity;
import com.payconiq.exception.ResourceNotFoundException;
import com.payconiq.repository.StockRepository;
import com.payconiq.service.StockServicesImpl;

//@SpringBootTest
@DataJpaTest
class PayconiqApplicationTests {

	@Autowired
	private TestEntityManager em;
	
	@Mock
	private StockRepository stockRepository;
	
	@Mock
	private Page page;
	
	@InjectMocks
	private StockServicesImpl stockServicesImpl;

	@Test
	void contextLoads() {
	}
	java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	
	@Test
	void testCreateStock() {
		StockDetails stockDetails = new StockDetails();
		stockDetails.setName("payconiq2");
		stockDetails.setInitialQuantity("1000000");
		stockDetails.setCurrentPrice("1000");

		String message = stockServicesImpl.createStocksDetails(stockDetails);
		assertNotNull(message);
		assertEquals("Stock saved successfully", message);
	}
	
	@Test
	void testGetAllStocksDetails() {
		List<StockEntity> list = new ArrayList<StockEntity>();
		StockEntity entity = new StockEntity();
		//entity.setId(1L);
		entity.setName("pyconiq");
		entity.setCurrentPrice("1000");
		entity.setCreateDate(date);
		entity.setInitialQuantity("1000000");
		list.add(entity);
		
		StockEntity entity2 = new StockEntity();
		//entity.setId(2L);
		entity2.setName("techM");
		entity2.setCurrentPrice("500");
		entity2.setCreateDate(date);
		entity2.setInitialQuantity("250000");
		list.add(entity2);
		
		Page<StockEntity> pagedResult = new PageImpl(list);
		Pageable paging = PageRequest.of(0, 1, Sort.by("id"));
		when(this.stockRepository.findAll(paging)).thenReturn(pagedResult);
		List<StockEntity> result = stockServicesImpl.getAllStocksDetails(0,1,"id");
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("pyconiq", result.get(0).getName());
		assertEquals("1000", result.get(0).getCurrentPrice());
		assertEquals("techM", result.get(1).getName());
		assertEquals("500", result.get(1).getCurrentPrice());
	}
	
	@Test
	void testGetStockByID() throws ResourceNotFoundException {
		StockEntity entity = new StockEntity();
		entity.setId(1L);
		entity.setName("pyconiq");
		entity.setCurrentPrice("1000");
		entity.setCreateDate(date);
		entity.setInitialQuantity("1000000");
		when(stockRepository.findById(1L)).thenReturn(Optional.of(entity));
		StockEntity entity1 = stockServicesImpl.getStockByID(1L);
		assertNotNull(entity1);
		assertEquals(1, entity1.getId());
		assertEquals("pyconiq", entity1.getName());
		assertEquals("1000", entity1.getCurrentPrice());
		assertEquals("1000000", entity1.getInitialQuantity());
	}
	
	@Test
	void testUpdateStocksDetails() throws ResourceNotFoundException {
		StockDetails stockDetails = new StockDetails();
		stockDetails.setId(1);
		stockDetails.setName("pyconiq");
		stockDetails.setCurrentPrice("1000");
		stockDetails.setInitialQuantity("1000000");
		StockEntity entity = new StockEntity();
		entity.setId(1L);
		entity.setName("pyconiq");
		entity.setCurrentPrice("1000");
		entity.setCreateDate(date);
		entity.setInitialQuantity("1000000");
		when(stockRepository.findById(1L)).thenReturn(Optional.of(entity));
		when(stockRepository.save(entity)).thenReturn(entity);
		StockEntity entity1 = stockServicesImpl.updateStocksDetails(stockDetails,1L);
		assertNotNull(entity1);
		assertEquals(1, entity1.getId());
		assertEquals("pyconiq", entity1.getName());
		assertEquals("1000", entity1.getCurrentPrice());
		assertEquals("1000000", entity1.getInitialQuantity());
	}
	
	@Test
	void testDeleteStockByID() throws ResourceNotFoundException {
		StockEntity entity = new StockEntity();
		entity.setId(1L);
		entity.setName("pyconiq");
		entity.setCurrentPrice("1000");
		entity.setCreateDate(date);
		entity.setInitialQuantity("1000000");
		when(stockRepository.findById(1L)).thenReturn(Optional.of(entity));
		doNothing().when(stockRepository).delete(entity);
		String message = stockServicesImpl.deleteStockByID(1L);
		assertNotNull(message);
		assertEquals("Stock deleted successfully", message);
	}
}
