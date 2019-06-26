package com.kushal.battleship.exception;

public class InvalidInput extends RuntimeException {

	private static final long serialVersionUID = -1L;

	public InvalidInput(){
		super();
	}
	
	public InvalidInput(String s){
		super(s);
	}
}
