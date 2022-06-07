package com.payconiq.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.payconiq.dto.entity.StockEntity;

@Repository
public interface StockRepository extends PagingAndSortingRepository<StockEntity, Long>{

}
