/*
 * ConsoleIO.java
 * Copyright (C) 2003 - 2005 by RSA Security Inc. All rights reserved.
 */
package seatechit.esb.rsa.verify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A simple class for data input from and output to the console.
 */
public class ConsoleIO
{
    public static final String LINEFEED = System.getProperty("line.separator", "\n");
    private BufferedReader console;
    
    public ConsoleIO()
    {
        console = new BufferedReader(new InputStreamReader(
                System.in));
    }
    
    /**
     * Outputs a message to the console.
     * @param msg the message to send
     */
    public void output(String msg)
    {
        if (msg != null)
        {
            System.out.print(msg);
            /*
             * This is necessary when it is run in ANT, which flushes its
             * buffer only when it sees an end-of-line.
             */
            System.out.flush();
        }
    }

    /**
     * Outputs a message to the console followed by a new line.
     * @param msg the message to send
     */
    public void outputLn(String msg)
    {
        if (msg != null)
        {
            output(msg + LINEFEED);
        }
    }

    /**
     * Inputs a string from the user console.
     * @param prompt a message to display before prompting
     * @return the string entered by the user
     * @throws IOException
     */
    public String input(String prompt) throws IOException
    {
        return input(prompt, false);
    }

    /**
     * Inputs a string from the user console.
     * @param prompt a message to display before prompting
     * @param nullAllowed if no input, just return an empty string.
     * @return the string entered by the user
     * @throws IOException
     */
    public String input(String prompt, boolean nullAllowed) throws IOException
    {
        String command;
        try
        {
            output(prompt);
            if ((command = console.readLine()) == null
                    || command.length() == 0)
            {
                if (nullAllowed)
                {
                    return "";
                }
                else
                {
                    throw new IOException("No input");
                }
            }
            else
            {
                return command;
            }
        }
        catch (IOException e)
        {
            outputLn("Input error: "
                    + e.getMessage());
            throw e;
        }
    }
}
