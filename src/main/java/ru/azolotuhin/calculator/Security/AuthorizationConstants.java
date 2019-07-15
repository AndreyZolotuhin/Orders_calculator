package ru.azolotuhin.calculator.Security;

public class AuthorizationConstants {

    public enum Rule{
        Orders,
        Metal,
        All,
        Calculation;
    }

    public enum Response{
        NO_USER,
        INVALID_PASSWORD,
        OK;
    }
}
