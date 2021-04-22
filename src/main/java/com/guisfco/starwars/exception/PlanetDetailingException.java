package com.guisfco.starwars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class PlanetDetailingException extends RuntimeException {

    private static final long serialVersionUID = 1007426079688903432L;

    public PlanetDetailingException() {
        super("An error occurred while trying to get the planet details.");
    }
}
