/**
 * 
 */
package com.example.fxpublisher.services;

import java.util.List;

import com.example.fxpublisher.model.FxPriceDTO;

/**
 * @author Rebeca Sánchez
 *
 */
public interface FxPriceService {

	/**
	 * Publishes a price to a REST endpoint.
	 * @param rawPrice The price in CSV format.
	 */
	public List<FxPriceDTO> getPrice(final String rawPrice);
}
