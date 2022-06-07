package com.payconiq.dto;

import java.util.List;

import com.payconiq.dto.entity.StockEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetsManagmets {

	private List<StockEntity> stocks;
}
