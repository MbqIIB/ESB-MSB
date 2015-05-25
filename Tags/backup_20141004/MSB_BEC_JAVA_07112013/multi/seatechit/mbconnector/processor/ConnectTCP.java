/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multi.seatechit.mbconnector.processor;

import java.net.Socket;
import multi.seatechit.mbconnector.utils.Global;
import multi.seatechit.mbconnector.utils.Utils;
/**
 *
 * @author Administrator
 */
public class ConnectTCP  extends Thread implements Global {

    public static int iConnectionInterval = 15000;
    private Socket socket = null;
    boolean bConnected;
    private String ipSocket;
    int portSocket;
    private int intState;

    public ConnectTCP(String ip, int port)
    {
        bConnected = false;
        intState = -1;
        ipSocket = ip;
        portSocket = port;
        socket = null;
        try
        {
            iConnectionInterval = 1000 * Integer.parseInt(Utils.getHostCommSetting("settings.connectionInterval"));
        }
        catch(Exception exception) { }
    }

    public void run()
    {
        int i = 0;
        if(socket == null)
        {
            bConnected = false;
            do
                try
                {
                    i++;
                    socket = new Socket(ipSocket, portSocket);
                    bConnected = true;
                    intState = 0;
                }
//                catch(InterruptedException ie){}
                catch(Exception e)
                {
                    try
                    {
                        Thread.sleep(iConnectionInterval);
                    }
                    catch(InterruptedException ie)
                    {
                        ie.printStackTrace();
                    }
                }
            while(!bConnected);
        }
    }

    public Socket getSock()
    {
        if(isConnected())
            return socket;
        else
            return null;
    }

    public boolean isConnected()
    {
        return bConnected;
    }
    public int getStates()
    {
        return intState;
    }

    public String toString()
    {
        return String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(bConnected ? "Connected " : "Not connected ")))).append(" to ").append(ipSocket).append(" port ").append(portSocket)));
    }

}
