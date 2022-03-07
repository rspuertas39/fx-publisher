/**
 * 
 */
package com.example.fxpublisher.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fxpublisher.clients.FxPriceClient;
import com.example.fxpublisher.model.FxPriceDTO;
import com.example.fxpublisher.services.FxPriceService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Rebeca Sánchez
 *
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class FxPriceServiceListenerImpl implements FxPriceListener {

	@NonNull
	private FxPriceService fxPriceService;

	@NonNull
	private FxPriceClient fxPriceClient;


	@Override
	public void onMessage(final String rawPrice) {
		// We parse the message.
		List<FxPriceDTO> fxPrices = fxPriceService.getPrice(rawPrice);
		fxPrices.forEach(fxPriceI -> {
			// And we send it.
			Optional<FxPriceDTO> publishedPrice = fxPriceClient.publishPrice(fxPriceI);
			if (publishedPrice.isPresent()) {
				log.info("The price {} was correctly published.", publishedPrice.get());
			}
			else {
				log.info("The price {} was not published.", fxPriceI);
			}
		});
	}
}
