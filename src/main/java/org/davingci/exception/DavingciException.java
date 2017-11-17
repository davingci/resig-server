/**
 *@author huangdongxu
 *@Date Nov 17, 2017
*/

package org.davingci.exception;

public class DavingciException extends RuntimeException{
	private String message;
	
	public DavingciException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
