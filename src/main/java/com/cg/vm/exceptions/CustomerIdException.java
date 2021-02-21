package com.cg.vm.exceptions;


	
	public class CustomerIdException extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public CustomerIdException()
		{
			super();
		}
		
		public CustomerIdException(String msg)
		{
			super(msg);
		}
	}
