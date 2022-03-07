/**
 * 
 */
package com.example.fxpublisher.components;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.fxpublisher.exceptions.WrongFormatException;
import com.example.fxpublisher.model.FxPriceDTO;

/**
 * @author Rebeca Sánchez
 *
 */
@Component
public class FxPriceParser {

	/** Message separators. This could be defined in the config file. */
	private static final String SEPARATOR = ",";
	private static final String LINE_SEPARATOR = "\n";

	/** Mandatory length. */
	private static final int LENGTH= 5;

	/** Wrong lengthe error message. This should be defined in config. */
	private static final String WRONG_LENGTH_ERROR = "The number of fields is wrong.";

	/** Timestamp format. */
	private static final String TIMESTAMP_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";

	private DateTimeFormatter formatter;


	public FxPriceParser() {
		formatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);
	}

	/**
	 * Helper to parse a raw price into an object.
	 * @param rawPrices
	 * @return The raw price as an object.
	 */
	public List<FxPriceDTO> parseMessage(final String rawPrices) {
		String[]separatePrices = rawPrices.split(LINE_SEPARATOR);
		List<FxPriceDTO> parsedPrices = new ArrayList<>();
		for (String rawPrice : separatePrices) {
			String[]splittedPrice = rawPrice.split(SEPARATOR);
			if (splittedPrice.length != LENGTH) {
				throw new WrongFormatException(WRONG_LENGTH_ERROR);
			}
			// Let's suppose the fields are in the correct format.
			parsedPrices.add(new FxPriceDTO(Integer.parseInt(splittedPrice[0]), splittedPrice[1], new BigDecimal(splittedPrice[2]),
					new BigDecimal(splittedPrice[3]), LocalDateTime.parse(splittedPrice[4], formatter)));
		}

		return parsedPrices;
	}
}
