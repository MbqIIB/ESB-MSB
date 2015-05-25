/*
 * NextCodeSession.java
 * Copyright (C) 2003 - 2005 by RSA Security Inc. All rights reserved.
 */
package seatechit.esb.rsa.verify;

import com.rsa.authagent.authapi.AuthSession;

/**
 * This class handles the Next Tokencode mode of a user's session.
 */
public class NextCodeSession
{
    private AuthSession session;
    private ConsoleIO io;

    protected NextCodeSession(ConsoleIO io, AuthSession session)
    {
        this.io = io;
        this.session = session;
    }

    /**
     * Processes the next tokencode of a user.
     * @return the status of the next tokencode
     * @throws Exception
     */
    public int process() throws Exception
    {
        String nextCode = io.input("Next tokencode: ");
        return session.next(nextCode);
    }

}
