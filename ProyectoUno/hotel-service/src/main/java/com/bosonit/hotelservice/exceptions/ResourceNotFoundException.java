package com.bosonit.hotelservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super("Rcuerso no encontrado en el servidor");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
