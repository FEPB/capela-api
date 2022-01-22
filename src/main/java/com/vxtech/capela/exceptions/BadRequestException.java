
package com.vxtech.capela.exceptions;

public class BadRequestException extends ExceptionGeneric {

     private static final long serialVersionUID = -6266810601264303948L;

     public BadRequestException(String message, Integer code, String errorCode){

          super(message, code, errorCode);
     }

     public BadRequestException(String message, Integer code, Throwable cause){

          super(message, code, cause);
     }

}