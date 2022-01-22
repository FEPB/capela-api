
package com.vxtech.capela.exceptions;

public class ServerInternalErrorException extends ExceptionGeneric {

     private static final long serialVersionUID = -6266810601264303948L;

     public ServerInternalErrorException(String message, Integer code, String errorCode){

          super(message, code, errorCode);
     }

     public ServerInternalErrorException(String message, Integer code, Throwable cause){

          super(message, code, cause);
     }

}