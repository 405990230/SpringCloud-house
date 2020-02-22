package com.mooc.boss.house.user.exception;

public class IllegalParamsException extends RuntimeException implements WithTypeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public enum Type {
        WRONG_PAGE_NUM, WRONG_TYPE;
    }

    private Type type;

    public IllegalParamsException() {

    }

    public IllegalParamsException(Type type, String msg) {
        super(msg);
        this.type = type;
    }

    public Type type() {
        return type;
    }


}
