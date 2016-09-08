package com.singplayground.showcase.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.singplayground.showcase.dto.StockDto;
import com.singplayground.showcase.dto.StockReportDto;

@RestController
public class JsonController {

	protected Logger logger = LogManager.getLogger(JsonController.class);

	@RequestMapping(value = "/test/json/example1", method = RequestMethod.GET)
	@ResponseBody
	public String example1() {
		logger.info("******** json example 1 ******");
		String msg = "test";
		return msg;
	}

	@RequestMapping(value = "/getStock", method = RequestMethod.GET)
	@ResponseBody
	public StockDto getStockJason() {
		logger.info("******** get stock ******");
		StockDto stockDto = new StockDto();
		StockReportDto stockReportDto = new StockReportDto();
		stockReportDto.setStockNo("005");
		stockReportDto.setStockRank("AAA");
		stockReportDto.setStockComment("I think ....");

		stockDto.setStockName("HSBC");
		stockDto.setMarketCode("HK");
		stockDto.setStockReportDto(stockReportDto);
		return stockDto;
	}

}
