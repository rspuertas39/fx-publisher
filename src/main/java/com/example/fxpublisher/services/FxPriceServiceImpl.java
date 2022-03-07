/**
 * 
 */
package com.example.fxpublisher.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fxpublisher.components.FxPriceParser;
import com.example.fxpublisher.model.FxPriceDTO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author siag.bbva
 *
 */
@RequiredArgsConstructor
@Service
public class FxPriceServiceImpl implements FxPriceService {

	/** Default mark-up. This should be defined in config or in a database table. */
	private static final BigDecimal PERCENTAGE = BigDecimal.valueOf(.001d);

	@NonNull
	private FxPriceParser fxPriceParser;

	@Override
	public List<FxPriceDTO> getPrice(String rawPrice) {
		// Parse the price.
		List<FxPriceDTO> parsedPrices = fxPriceParser.parseMessage(rawPrice);
		for (FxPriceDTO priceI : parsedPrices) {
			// Apply mark-up.
			BigDecimal newBid = applyMarkup(priceI.getBid(), true);
			BigDecimal newAsk = applyMarkup(priceI.getAsk(), false);
			// Apply changes.
			priceI.setBid(newBid);
			priceI.setAsk(newAsk);
		}

		return parsedPrices;
	}


	/**
	 * 
	 * @param price The price.
	 * @param isBid Is it bid or ask? Bid substracts the mark-up and ask adds it.
	 * @return The calculated price.
	 */
	private BigDecimal applyMarkup(final BigDecimal price, boolean isBid) {
		BigDecimal priceMarkup = price.multiply(PERCENTAGE);
		if (isBid) {
			priceMarkup = priceMarkup.negate();
		}

		return price.add(priceMarkup);
	}
}
