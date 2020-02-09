package com.bn.exception;

public class BNStoreException extends Exception {

	public BNStoreException(String message)
	{
		super(message);
	}
	
	public BNStoreException(String message, Throwable cause)
	{
		super(message,cause);
	}
}
