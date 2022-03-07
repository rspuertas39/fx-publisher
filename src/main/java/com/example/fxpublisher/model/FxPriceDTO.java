/**
 * 
 */
package com.example.fxpublisher.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NonNull;

/**
 * @author Rebeca Sánchez
 *
 */
@Data
public class FxPriceDTO {

	@NonNull
	private Integer uniqueId;

	@NonNull
	private String currencyPair;

	@NonNull
	private BigDecimal bid;

	@NonNull
	private BigDecimal ask;

	@NonNull
	private LocalDateTime timestamp;
}
