package com.otavio.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7476903191211254493L;
	
	public ObjectNotFoundException(String msg){
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause){
		super(msg, cause);
	}

	
}
