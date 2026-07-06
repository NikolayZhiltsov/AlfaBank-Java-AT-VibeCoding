package ru.alfabank.homeworks.homework12.exceptions.verifiable;

public class FlightNotFoundException extends AirportServiceException{
    public FlightNotFoundException(String message){
        super(message);
    }
}
