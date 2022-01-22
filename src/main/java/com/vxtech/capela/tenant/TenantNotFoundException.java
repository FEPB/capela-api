
package com.vxtech.capela.tenant;

public class TenantNotFoundException extends RuntimeException{

     private static final long serialVersionUID = 5613067391325973722L;

     public TenantNotFoundException(String exception){

          super(exception);
     }
}
