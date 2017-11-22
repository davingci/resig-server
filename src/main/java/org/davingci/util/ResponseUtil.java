/**
 *@author huangdongxu
 *@Date Oct 20, 2017
 *@description

*/

package org.davingci.util;

import java.io.Serializable;

public class ResponseUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private int  code;
	private Object data;
	private String message;
	
	public ResponseUtil() {
		
		super();
		code = 200;
		data = "";
		message = "";
	}

	public ResponseUtil(int code, Object data, String message) {
		super();
		this.code = code;
		this.data = data;
		this.message = message;
	}
	
	private ResponseUtil(ResponseBuilder b) {
		this.code = b.code;
		this.message = b.message;
		this.data = b.data;
	}
	
	public static class ResponseBuilder {
		
		private int  code;
		private Object data;
		private String message;
		
		
		
		public ResponseBuilder() {
			
		}

		public ResponseBuilder code(int code) {
			this.code = code;
			return this;
		}
		
		public ResponseBuilder data(Object data) {
			this.data = data;
			return this;
		}
		
		public ResponseBuilder message(String message) {
			this.message = message;
			return this;
		}
		
		public ResponseUtil build() {
			return new ResponseUtil(this);
		}
	}

	
	
	
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public ResponseUtil code(int code) {
		this.code = code;
		return this;
	}
	
	public ResponseUtil data(Object data) {
		this.data = data;
		return this;
	}
	
	public ResponseUtil message(String message) {
		this.message = message;
		return this;
	}
	
	
	
}
