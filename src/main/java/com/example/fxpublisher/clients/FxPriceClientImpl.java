/**
 * 
 */
package com.example.fxpublisher.clients;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.example.fxpublisher.components.FxPriceParser;
import com.example.fxpublisher.mappers.FxPriceDTOJsonMapper;
import com.example.fxpublisher.model.FxPriceDTO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Rebeca Sánchez
 *
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class FxPriceClientImpl implements FxPriceClient {

	@NonNull
	private FxPriceParser fxPriceParser;

	@NonNull
	private FxPriceDTOJsonMapper fxPriceMapper;

	/** URL where we will send the price. This should be defined in config. */
	private static final String URL = "http://example.org/";

	@Override
	public Optional<FxPriceDTO> publishPrice(FxPriceDTO price) {
		// Map the price into a JSON string.
		Optional<String> jsonPrice = fxPriceMapper.toJson(price);
		// Try to publish only if present.
		if (jsonPrice.isPresent()) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			// This should be done like this:
//			RestTemplate restTemplate = new RestTemplate();
//			HttpEntity<String> request = new HttpEntity<String>(jsonPrice.get(), headers);
//			String priceResultAsJsonStr = restTemplate.postForObject(URL, request, String.class);
			// Return the published price as an object. This will return an Optional.
//			return fxPriceMapper.fromJson(priceResultAsJsonStr);
			log.info("Publishing to {} the price {}.", URL, price);
			return Optional.of(price);
		}

		return Optional.empty();
	}
}
