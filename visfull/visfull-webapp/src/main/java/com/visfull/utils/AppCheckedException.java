package com.visfull.utils;

/**
 * app root checked exception.
 * 
 * @author yong
 *
 */
public abstract class AppCheckedException extends Exception {
    private static final long serialVersionUID = 2413938379499881450L;

    public AppCheckedException(String msg) {
        super(msg);
    }


    public AppCheckedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getMessage() {
        return buildMessage(super.getMessage(), getCause());
    }

    public Throwable getRootCause() {
        Throwable rootCause = null;
        Throwable cause = getCause();
        while (cause != null && cause != rootCause) {
            rootCause = cause;
            cause = cause.getCause();
        }
        return rootCause;
    }

    public Throwable getMostSpecificCause() {
        Throwable rootCause = getRootCause();
        return (rootCause != null ? rootCause : this);
    }

    @SuppressWarnings({"rawtypes"})
    public boolean contains(Class exType) {
        if (exType == null) {
            return false;
        }
        if (exType.isInstance(this)) {
            return true;
        }
        Throwable cause = getCause();
        if (cause == this) {
            return false;
        }
        if (cause instanceof AppCheckedException) {
            return ((AppCheckedException) cause).contains(exType);
        } else {
            while (cause != null) {
                if (exType.isInstance(cause)) {
                    return true;
                }
                if (cause.getCause() == cause) {
                    break;
                }
                cause = cause.getCause();
            }
            return false;
        }
    }

    private String buildMessage(String message, Throwable cause) {
        if (cause != null) {
            StringBuilder sb = new StringBuilder();
            if (message != null) {
                sb.append(message).append(";");
            }
            sb.append("cause exception is ").append(cause);
            return sb.toString();
        }
        else {
            return message;
        }
    }

}
