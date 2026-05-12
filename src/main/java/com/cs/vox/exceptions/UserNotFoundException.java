package com.cs.vox.exceptions;


public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {

        super("user not found");
    }
}
