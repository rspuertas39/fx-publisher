/**
 * 
 */
package com.example.fxpublisher.clients;

import java.util.Optional;

import com.example.fxpublisher.model.FxPriceDTO;

/**
 * @author Rebeca Sánchez
 *
 */
public interface FxPriceClient {

	public Optional<FxPriceDTO> publishPrice(final FxPriceDTO price);
}
