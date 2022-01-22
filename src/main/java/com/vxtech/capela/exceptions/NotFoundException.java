
package com.vxtech.capela.exceptions;

public class NotFoundException extends ExceptionGeneric {

     private static final long serialVersionUID = -7390706183368829263L;

     public NotFoundException(String message, Integer code, String errorCode){

          super(message, code, errorCode);
     }

     public NotFoundException(String message, Integer code, Throwable cause){

          super(message, code, cause);
     }

}