package io.basic.website.exception;

public class BusinessTypeNotFoundException extends Exception {

    public BusinessTypeNotFoundException(int id) {
        super("No business type with id = " + id + " found.");
    }
}
