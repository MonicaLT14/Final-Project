package client.agency.controller.error;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {

	@ExceptionHandler(UnsupportedOperationException.class)
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public Map<String, String> handleUnsupportedOperationException(UnsupportedOperationException ex) {
		log.error("Exception: {}", ex.toString());
		return Map.of("message", ex.toString());
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, String> handleNoSuchElementException(NoSuchElementException ex) {
		log.error("Exception: {}", ex.toString());
		return Map.of("message", ex.toString());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String, String> handleIllegalArgumentException(IllegalArgumentException ex) {
		log.error("Exception: {}", ex.toString());
		return Map.of("message", ex.toString());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public Map<String, String> handleDuplicateKeyException(DuplicateKeyException ex) {
		log.error("Exception: {}", ex.toString());
		return Map.of("message", ex.toString());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleException(Exception ex) {
		log.error("Exception: {}", ex.toString());
		return Map.of("message", ex.toString());
	}
}