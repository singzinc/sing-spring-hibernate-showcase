package com.singplayground.showcase.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

public class StockDto {
	private String stockNo;
	private String stockName;
	private String stockDesc;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Double openingPrice;
	private Double closingPrice;
	private Date date;
	private String marketCode;
	private Long volume;
	private StockReportDto stockReportDto;

	/*
		@JsonIgnoreType
		public static class StockReportDto {
			public String stockRank = null;
		}
	*/
	public String getStockNo() {
		return stockNo;
	}

	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStockDesc() {
		return stockDesc;
	}

	public void setStockDesc(String stockDesc) {
		this.stockDesc = stockDesc;
	}

	public Double getOpeningPrice() {
		return openingPrice;
	}

	public void setOpeningPrice(Double openingPrice) {
		this.openingPrice = openingPrice;
	}

	public Double getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(Double closingPrice) {
		this.closingPrice = closingPrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMarketCode() {
		return marketCode;
	}

	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public StockReportDto getStockReportDto() {
		return stockReportDto;
	}

	public void setStockReportDto(StockReportDto stockReportDto) {
		this.stockReportDto = stockReportDto;
	}

}
