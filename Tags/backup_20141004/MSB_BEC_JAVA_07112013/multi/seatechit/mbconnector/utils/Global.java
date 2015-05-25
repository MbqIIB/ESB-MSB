package multi.seatechit.mbconnector.utils;


public interface Global
{
    public static final boolean ERROR = true;
    public static final boolean WARNING = false;
    public static final boolean DEBUG = false;
    public static final boolean INFO = false;
    public static final boolean GENERAL = false;
    public static final boolean FIELD_INFO = false;
    public static final String UPDATED = "Mar 18, 2003 9:40PM";
    public static final String VERSION = "1.1.2";
    public static final String COMMA = ",";
    public static final int READTHREAD_SLEEP = 1000;
    public static final int ONE_SECOND = 1000;
    public static final int CONNECTION_TIMEOUT = 15000;
    public static final int INTERVAL = 500;
    public static final String HOST_CODE_PAGE = "Cp935";
    public static final String AX_CODE_PAGE = "Cp1381";
    public static final String UNICODE = "UTF8";
    public static final String NOT_SPECIFIED = "not specified";
    public static final String HOST_CONFIG_FILE = "Host.prop";
    public static final int MAX_TCPTRACE_FILE = 10;
    public static final String MAX_TCPTRACE_FILE_KEY = "settings.maxtracefile";
    public static final String TRIM_OPTION = "settings.trim";
    public static final String CONNECTION_WAIT_KEY = "settings.connectionWait";
    public static final String TCPTRACE_FILENAME = "TCPTrace.dat";
    public static final int MAX_TCPTRACE_SIZE = 0x100000;
    public static final String DEBUGMSG_KEY = "settings.debugmsg";
    public static final String YES1 = "YES";
    public static final String YES2 = "y";
    public static final String KEY_CONNECTIONINTERVAL = "settings.connectionInterval";
    public static final int CONNECTED = 0;
    public static final int CONNECTING = 1;
    public static final int NOT_CONNECTED = -1;
    public static final String V2 = "V2";
    public static final String V5 = "";
    public static final String TYPE_VIET = "VIET";
    public static final boolean ENABLE_UNICODE_CONVERSION = false;
    public static final int BANK_ID = 0;
    public static final int BANK_AP = 0;
    public static final int BANK_CHINA = 1;
    public static final int MSG_HDR = 9;
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";

}