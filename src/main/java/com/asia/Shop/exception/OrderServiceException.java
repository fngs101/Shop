package com.asia.Shop.exception;

public class OrderServiceException extends RuntimeException
{
    public OrderServiceException(String message)
    {
        super(message);
    }
}
