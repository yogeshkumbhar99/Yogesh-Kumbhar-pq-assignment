package com.payconiq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDetails {

	private long id;
	private String name;
	private String currentPrice;
	private String lastUpdated;
	private String initialQuantity;
	private String createDate;
	
	
	
}
