package com.viettel.imdb.util.validator;

import com.viettel.imdb.type.exception.ErrorCode;
import com.viettel.imdb.type.exception.IMDBException;

public class Validator {
    public static void throwIMDBException(final ErrorCode errorCode) throws IMDBException {
        throw new IMDBException(errorCode);
    }

    public static void throwIMDBException(final ErrorCode errorCode, final String message) throws IMDBException {
        throw new IMDBException(errorCode, message);
    }

    public static void throwIMDBException(final ErrorCode errorCode, final Throwable throwable) throws IMDBException {
        throw new IMDBException(errorCode, throwable);
    }

    public static void verify(final boolean expression, final ErrorCode errorCode, String message) throws IMDBException {
        if (expression)
            throwIMDBException(errorCode, message);
    }
}
