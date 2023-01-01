package com.fasttrackit.BugetPersonal.exception;

import lombok.Getter;

@Getter
public class ParseException extends Exception{
    private int errorOffset;

    public ParseException(String s, int errorOffset) {
        super(s);
        this.errorOffset = errorOffset;
    }

}
