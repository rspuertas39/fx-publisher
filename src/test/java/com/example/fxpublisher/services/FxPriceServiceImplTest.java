/**
 * 
 */
package com.example.fxpublisher.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fxpublisher.model.FxPriceDTO;

/**
 * @author siag.bbva
 *
 */
@SpringBootTest
class FxPriceServiceImplTest {

	@Autowired
	private FxPriceService fxPriceService;

	/**
	 * Test method for {@link com.example.fxpublisher.services.FxPriceServiceImpl#getPrice(java.lang.String)}.
	 */
	@Test
	void testGetPrice() {
		/** Timestamp format. */
		final String TIMESTAMP_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);

		final String inputRawPrice = "106,EUR/USD,1.1000,1.2000,01-06-2020 12:01:01.001";
		final FxPriceDTO expectedFxPrice = new FxPriceDTO(106, "EUR/USD", new BigDecimal("1.0989000"), new BigDecimal("1.2012000"), LocalDateTime.parse("01-06-2020 12:01:01.001", formatter));
		final List<FxPriceDTO> actualFxPrice = fxPriceService.getPrice(inputRawPrice);
		assertEquals(expectedFxPrice, actualFxPrice.get(0));
	}

}
