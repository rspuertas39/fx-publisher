/**
 * 
 */
package com.example.fxpublisher.exceptions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author siag.bbva
 *
 */
@RequiredArgsConstructor
public class WrongFormatException extends RuntimeException {

	private static final long serialVersionUID = -5609388094137451374L;


	@NonNull
	private String message;
}
