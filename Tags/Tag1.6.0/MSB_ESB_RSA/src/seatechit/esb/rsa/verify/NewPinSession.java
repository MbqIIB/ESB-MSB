/*
 * NewPinSession.java
 * Copyright (C) 2003 - 2005 by RSA Security Inc. All rights reserved.
 */
package seatechit.esb.rsa.verify;

import java.io.IOException;

import com.rsa.authagent.authapi.AuthSession;
import com.rsa.authagent.authapi.PinData;

/**
 * This class handles the New Pin mode of a user's session.
 */
public class NewPinSession
{
    private AuthSession session;
    private ConsoleIO io;
    private PinData pinData;

    private static final String PIN_CANCELED = "New Pin canceled; retry login.";
    private static final String PIN_CREATED = "Waiting for authorization of the new Pin...";
    private static final String PIN_INVALID = "New Pin does not match the Pin criteria; please retry.";
    private static final String PIN_GENERATED = "Memorize your new Pin: ";    
    
    protected NewPinSession(ConsoleIO io, AuthSession session) throws Exception
    {
        this.io = io;
        this.session = session;
        this.pinData = session.getPinData();
    }

    /**
     * Processes the new Pin creation for the user.
     * @return the status of the new Pin creation.
     * @throws Exception
     */
    public int process() throws Exception
    {
        String newPinMsg;
        String newPin;
        int authStatus;
        int pinState = pinData.getUserSelectable();
        
        switch (pinState)
        {
        case PinData.MUST_CHOOSE_PIN :
            newPinMsg = "Pin should be between "
                    + pinData.getMinPinLength()
                    + " and "
                    + pinData.getMaxPinLength()
                    + (pinData.isAlphanumeric()
                            ? " letters"
                            : " numbers")
                    + ".";
        
            newPin = createPin(newPinMsg, false);
            break;
            
        case PinData.CANNOT_CHOOSE_PIN :
            newPin = generatePin();
            break;
        
        case PinData.USER_SELECTABLE :
            newPinMsg = "Enter your new Pin, or press <Return> to generate it."
                + ConsoleIO.LINEFEED
                + "Pin should be between "
                + pinData.getMinPinLength()
                + " and "
                + pinData.getMaxPinLength()
                + (pinData.isAlphanumeric()
                        ? " letters"
                        : " numbers")
                + ".";
            newPin = createPin(newPinMsg, true);
            break;
        
        default:
            newPin = "";
            io.outputLn("Error: unexpected Pin selection mode.");
            break;
        }
        if (newPin.length() == 0)
        {
            newPinMsg = PIN_CANCELED;
        }
        else
        {
            newPinMsg = PIN_CREATED;
        }
        io.outputLn(newPinMsg);
        authStatus = session.pin(newPin);
        return authStatus;
    }
    
    /**
     * Validates the new Pin.
     * @param newPin the submitted new Pin to validate
     * @return <code>true</code> if valid; <code>false</code> otherwise
     */
    private boolean isPinValid(String newPin)
    {
        boolean isValid;
        int len = newPin.length();
        if (len < pinData.getMinPinLength()
                || len > pinData.getMaxPinLength())
        {
            return false;
        }
        isValid = true;
        for (int i = 0; i < newPin.length(); i++)
        {
            if (pinData.isAlphanumeric())
            {
                if (!Character.isLetterOrDigit(newPin.charAt(i)))
                {
                    isValid = false;
                    break;
                }
            }
            else
            {
                if (!Character.isDigit(newPin.charAt(i)))
                {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

    private String generatePin()
    {
        String resp;
        String newPin;
        try
        {
            resp = io.input("Are you ready to receive your new Pin? (Y/N) ");
            if (resp.trim().toUpperCase().startsWith("Y"))
            {
                newPin = pinData.getSystemPin().trim();
                if (newPin.length() != 0)
                {
                    io.outputLn(PIN_GENERATED + newPin);
                }
            }
            else
            {
                newPin = "";
            }
        }
        catch (IOException e)
        {
            newPin = "";
        }
        
        return newPin;
    }
    
    /**
     * Create a new Pin.
     * @param newPinMsg the prompt for the user
     * @param alsoGen if true, allow to generate the Pin
     * @return
     */
    private String createPin(String newPinMsg, boolean alsoGen)
    {
        String newPin;
        io.outputLn(newPinMsg);
        for (int i = 0; i < 3; i++)
        {
            try
            {
                newPin = io.input("New Pin: ", alsoGen).trim();
                if (newPin.length() == 0)
                {
                    newPin = generatePin();
                    return newPin;
                }
                
                if (isPinValid(newPin))
                {
                    return newPin;
                }
                else
                {
                    io.outputLn(PIN_INVALID);
                }
            }
            catch (IOException e)
            {
                io.outputLn("Invalid input; please retry.");
            }
        }
        return "";
    }
}
