package multi.seatechit.mbconnector.utils;

public final class General implements Global
{
    private static General general = null;
    private String debugMSG;
    public static boolean DEBUGMSG;
    protected int LEVEL;
    private boolean waitForConnection;
    private General()
    {
        debugMSG = null;
        DEBUGMSG = false;
        LEVEL = -1;
        waitForConnection = false;
        settings();
    }

    public static synchronized General getInstance()
    {
        if(general == null)
            general = new General();
        return general;
    }

    private void settings()
    {
        try
        {
            try
            {
                String s = Utils.getHostCommSetting("settings.debugmsg");
                String sWait4Connection = Utils.getHostCommSetting("settings.connectionWait");
                if(sWait4Connection != null && sWait4Connection.length() > 0 && sWait4Connection.equalsIgnoreCase("Y"))
                    waitForConnection = true;
                debugMSG = "";
                String level = "";
                if(s != null)
                {
                    if(s.length() > 0)
                        debugMSG = s.substring(0, 1);
                    if(s.length() > 1)
                    {
                        level = s.substring(1, 2);
                        try
                        {
                            LEVEL = Integer.parseInt(level);
                            System.out.println("Level=".concat(String.valueOf(String.valueOf(LEVEL))));
                        }
                        catch(NumberFormatException nfe)
                        {
                            System.out.println("NumberFormatException:Undefined Level in Host.prop: settings.debugmsg");
                        }
                    }
                }
                if(debugMSG.equalsIgnoreCase("YES") || debugMSG.equalsIgnoreCase("y"))
                    DEBUGMSG = true;
            }
            catch(Exception exception) { }
//            Date dtnow = new Date();
          
        }
        catch(Exception e)
        {
            System.out.println("E Utils.General.init():Exception ".concat(String.valueOf(String.valueOf(e.toString()))));
        }
    }

    public static synchronized void setDebugMode(boolean stat)
    {
        try
        {
            general.DEBUGMSG = stat;
        }
        catch(Exception e)
        {
            System.out.println(String.valueOf(String.valueOf((new StringBuffer("E Utils.General.setDebugModeException:")).append(e.toString()).append(" You need to call getInstance() method before access its method. "))));
        }
    }

    public static synchronized boolean getDebugMode()
    {
        try
        {
            boolean flag = general.DEBUGMSG;
            return flag;
        }
        catch(Exception e)
        {
            System.out.println(String.valueOf(String.valueOf((new StringBuffer("E Utils.General.getDebugModeException:")).append(e.toString()).append(" You need to call getInstance() method before access its method. "))));
        }
        boolean flag1 = false;
        return flag1;
    }

    public boolean getWaitForConnection()
    {
        return waitForConnection;
    }

   

}