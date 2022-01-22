
package com.vxtech.capela.exceptions;

import br.com.twsoftware.alfred.object.Objeto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionGeneric extends RuntimeException{

     private static final long serialVersionUID = -2423895860982498778L;
     
     Integer code;

     String errorCode;

     public ExceptionGeneric(String message, Integer code, String errorCode){

          super(message);
          this.code = code;
          this.errorCode = errorCode;

     }
     
     public ExceptionGeneric(String message, Integer code){
          
          super(message);
          this.code = code;
          
     }
     
     public ExceptionGeneric(String message, Integer code, Throwable cause){
          
          super(message, cause);
          this.code = code;
          
     }
     
     public static void checkThrowIsBlank(Object object, ExceptionEnum exceptionEnum) throws ExceptionGeneric {
          
          checkThrow(Objeto.isBlank(object), exceptionEnum);

     }     
     
     public static void checkThrowNotBlank(Object object, ExceptionEnum exceptionEnum) throws ExceptionGeneric {
          
          checkThrow(Objeto.notBlank(object), exceptionEnum);
          
     }     
     
     public static void checkThrow(boolean expression, ExceptionEnum exceptionEnum) throws ExceptionGeneric {
          
          if (expression) {
               exceptionEnum.launch();
          }
          
     }     
     
     public static void checkThrow(boolean expression, ExceptionEnum exceptionEnum, Object... args) throws ExceptionGeneric {
          
          if (expression) {
               exceptionEnum.launch();
          }
          
     }     
     
}
