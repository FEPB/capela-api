
package com.vxtech.capela.exceptions;

import com.vxtech.capela.util.I18n;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.ResourceBundle;

import static org.springframework.http.HttpStatus.*;

@Log4j2
public enum ExceptionEnum {


    /**
     * Global Messages
     */

    GLOBAL_ERROR(INTERNAL_SERVER_ERROR, ServerInternalErrorException.class,"0001"),
    GLOBAL_INCONSISTENCIA_CADASTRAL_ERROR(INTERNAL_SERVER_ERROR, ServerInternalErrorException.class, "0002"),

    /**
     * Oficina Exceptions
     */
    OFICINA_NAO_ENCONTRADA(NOT_FOUND, NotFoundException.class, "1000"),

    /**
     * Inscricao Exceptions
     */
    INSCRICAO_NAO_ENCONTRADA(NOT_FOUND, NotFoundException.class, "2000"),
    INSCRICAO_SEM_ID(BAD_REQUEST, BadRequestException.class, "2001"),
    ;
    
    @Getter
    private HttpStatus status;

    @Getter
    private Class<? extends ExceptionGeneric> klass;

    @Getter
    private String errorCode;

    ExceptionEnum(HttpStatus httpStatus, Class<? extends ExceptionGeneric> exceptionKlass, String errorCode) {

        this.status = httpStatus;
        this.klass = exceptionKlass;
        this.errorCode = errorCode;

    }

    public String getKeyLowerCase() {
        return this.name().toLowerCase();
    }

    public String getMessage() {

        ResourceBundle bundle = ResourceBundle.getBundle("messages", I18n.getLocale());
        return bundle.getString(getKeyLowerCase());

    }

    public ExceptionGeneric getException() {

        String msg = getMessage();
        ExceptionGeneric eg = null;

        try {

            eg = klass.getConstructor(String.class, Integer.class, String.class).newInstance(msg, status.value(), errorCode);

        } catch (Exception e) {
            log.error("Error trying instance internal exception using reflection.");
        }

        if (Objects.isNull(eg)) {
            eg = new ExceptionGeneric(msg, INTERNAL_SERVER_ERROR.value());
        }

        return eg;

    }

    public void launch() {

        throw getException();

    }

}
