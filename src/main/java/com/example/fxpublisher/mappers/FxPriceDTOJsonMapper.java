/**
 * 
 */
package com.example.fxpublisher.mappers;

import java.io.IOException;
import java.util.Optional;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.example.fxpublisher.model.FxPriceDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author siag.bbva
 *
 */
@Slf4j
@Component
public class FxPriceDTOJsonMapper {

	private ObjectMapper mapper;


	public FxPriceDTOJsonMapper() {
		mapper = new ObjectMapper();
	}
	

	public Optional<String> toJson(final FxPriceDTO fxPrice) {
		try {
			String fxPriceAsJson = mapper.writeValueAsString(fxPrice);
			log.info(fxPriceAsJson);
			return Optional.of(fxPriceAsJson);
		} catch (IOException e) {
			// This should be printed in a log file.
			log.error("Error parsing price {},", fxPrice, e);
			// Return empty optional.
			return Optional.empty();
		}
	}


	public Optional<FxPriceDTO> fromJson(final String fxPriceAsJson) {
		try {
			FxPriceDTO fxPrice = mapper.readValue(fxPriceAsJson, FxPriceDTO.class);
			return Optional.of(fxPrice);
		} catch (IOException e) {
			log.error("Error parsing JSON {},", fxPriceAsJson, e);
			// Return empty optional.
			return Optional.empty();
		}
	}
}
