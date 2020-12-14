package com.snowman.touristspot.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.snowman.touristspot.exception.BadRequestException;
import com.snowman.touristspot.exception.CategoryDescriptionAlreadyRegistredException;
import com.snowman.touristspot.exception.CategoryNotFoundException;
import com.snowman.touristspot.exception.DeletePictureNotAllowedException;
import com.snowman.touristspot.exception.TouristicSpotNameAlreadyRegistredException;


@ControllerAdvice
public class SnowmanExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String userMsg = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
		String devMsg = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(userMsg, devMsg));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = createErrorList(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String userMsg = messageSource.getMessage("resource.notfound", null, LocaleContextHolder.getLocale());
		String devMsg = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(userMsg, devMsg));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class } )
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String userMsg = messageSource.getMessage("resource.operation-does-not-allow", null, LocaleContextHolder.getLocale());
		String devMsg = ExceptionUtils.getRootCauseMessage(ex);
		List<Erro> erros = Arrays.asList(new Erro(userMsg, devMsg));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({CategoryDescriptionAlreadyRegistredException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(CategoryDescriptionAlreadyRegistredException ex) {
		String userMsg = messageSource.getMessage("resource.already-registered", null, LocaleContextHolder.getLocale());
		String devMsg = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(userMsg, devMsg));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({TouristicSpotNameAlreadyRegistredException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(TouristicSpotNameAlreadyRegistredException ex) {
		String userMsg = messageSource.getMessage("resource.already-registered", null, LocaleContextHolder.getLocale());
		String devMsg = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(userMsg, devMsg));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({DeletePictureNotAllowedException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(DeletePictureNotAllowedException ex) {
		String userMsg = messageSource.getMessage("resource.already-registered", null, LocaleContextHolder.getLocale());
		String devMsg = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(userMsg, devMsg));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({CategoryNotFoundException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(CategoryNotFoundException ex) {
		String userMsg = messageSource.getMessage("resource.already-registered", null, LocaleContextHolder.getLocale());
		String devMsg = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(userMsg, devMsg));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({BadRequestException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(BadRequestException ex) {
		String userMsg = ex.getMessage();
		String devMsg = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(userMsg, devMsg));
		return ResponseEntity.badRequest().body(erros);
	}
	
	private List<Erro> createErrorList(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String userMsg = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String devMsg = fieldError.toString();
			erros.add(new Erro(userMsg, devMsg));
		}
			
		return erros;
	}
	
}
