/**
 * 
 */
package com.example.fxpublisher.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fxpublisher.model.FxPriceDTO;

/**
 * @author siag.bbva
 *
 */
@SpringBootTest
class FxPriceDTOJsonMapperTest {

	@Autowired
	private FxPriceDTOJsonMapper jsonMapper;

	/**
	 * Test method for {@link com.example.fxpublisher.mappers.FxPriceDTOJsonMapper#toJson(com.example.fxpublisher.model.FxPriceDTO)}.
	 */
	@Test
	void testToJson() {
		/** Timestamp format. */
		final String TIMESTAMP_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);

		final FxPriceDTO inputFxPrice = new FxPriceDTO(106, "EUR/USD", new BigDecimal("1.0989000"), new BigDecimal("1.2012000"), LocalDateTime.parse("01-06-2020 12:01:01.001", formatter));
		String expected = "{\"uniqueId\":106,\"currencyPair\":\"EUR/USD\",\"bid\":1.0989000,\"ask\":1.2012000,"
				+ "\"timestamp\":{\"month\":\"JUNE\",\"year\":2020,\"dayOfMonth\":1,"
				+ "\"hour\":12,\"minute\":1,\"monthValue\":6,\"nano\":1000000,\"second\":1,"
				+ "\"dayOfWeek\":\"MONDAY\",\"dayOfYear\":153,\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"}}}";
		Optional<String> actual = jsonMapper.toJson(inputFxPrice);
		assertEquals(expected, actual.get());
	}

}
