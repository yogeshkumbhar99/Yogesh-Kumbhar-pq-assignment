package com.payconiq.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.payconiq.dto.entity.StockEntity;
import com.payconiq.repository.StockRepository;

@Configuration
public class DatabaseInitializer {

	@Autowired
	private StockRepository repository;

	@Bean
	CommandLineRunner initDatabase() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repository.saveAll(createListOfRecords());
			}
		};

	}

	private List<StockEntity> createListOfRecords() {
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
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
		
		StockEntity entity3 = new StockEntity();
		//entity.setId(3L);
		entity3.setName("clarivate");
		entity3.setCurrentPrice("700");
		entity3.setCreateDate(date);
		entity3.setInitialQuantity("5000");
		list.add(entity3);
		return list;
		
	}

}
