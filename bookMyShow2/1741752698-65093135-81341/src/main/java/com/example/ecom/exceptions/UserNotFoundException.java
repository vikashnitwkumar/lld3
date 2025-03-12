package com.example.ecom.exceptions;

import java.util.function.Supplier;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
