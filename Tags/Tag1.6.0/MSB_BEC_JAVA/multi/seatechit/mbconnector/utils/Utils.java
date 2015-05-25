/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package multi.seatechit.mbconnector.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Administrator
 */
public class Utils {
    protected static final String sFileSeparator;
    private static int debugMode = 0;
    private static Properties properties;

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        Utils.properties = properties;
    }

    static
    {
        sFileSeparator = File.separator;
    }
    
    public Utils()
    {
    }
    public static void setDebugMode()
    {
//        debugMode = Integer.parseInt(getHostCommSetting("settings.debugmode"));
    }


    protected static void captureTime()
    {
        Date dtNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        HostTrace(String.valueOf(String.valueOf((new StringBuffer("OS time              : [")).append(sdf.format(dtNow)).append(" ]"))));
    }

    public static String rmLeadingZeroes(String input)
    {
        if(input == null)
            return null;
        if(input.length() == 0)
            return input;
        int iBrkPnt = 0;
        int i = 0;
        do
        {
            if(i >= input.length())
                break;
            char ctmp = input.charAt(i);
            if(ctmp != '0')
            {
                iBrkPnt = i;
                break;
            }
            i++;
        } while(true);
        return input.substring(iBrkPnt, input.length());
    }

    public static String getHostCommSetting(String sPropertyName)
    {
        try
        {
            String s = System.getProperty(sPropertyName, "not specified");
            return s;
        }
        catch(Exception ioe)
        {
            ioe.printStackTrace();
        }
        String s1 = "";
        return s1;
    }

    public static Properties loadFormatSetting()
    {
        try
        {
            FileInputStream propFile = new FileInputStream(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf((new File("")).getCanonicalPath())))).append(sFileSeparator).append("FormatMessage.properties"))));
            properties= new Properties(System.getProperties());
            properties.load(propFile);
            if (propFile!=null) {
                propFile.close();
            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        return properties;
    }
      public static void loadHostCommSetting()
    {
        try
        {
            FileInputStream propFile = new FileInputStream(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf((new File("")).getCanonicalPath())))).append(sFileSeparator).append("Host.prop"))));
            Properties p = new Properties(System.getProperties());
            p.load(propFile);
            System.setProperties(p);
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    public static String padTrailingZeroes(String str, int Size)
    {
        if(str == null)
            return null;
        if(Size < 0)
            return str;
        str = str.trim();
        int iLen = str.length();
        if(iLen >= Size)
            return str;
        StringBuffer sb = new StringBuffer(str);
        int iNoOfZeroes = 0;
        iNoOfZeroes = Size - iLen;
        for(int i = 0; i < iNoOfZeroes; i++)
            sb.append("0");

        return sb.toString();
    }

    public static String trimStr(String str, int Size)
    {
        if(str == null)
            return null;
        if(Size < 0)
            return str;
        str = str.trim();
        int iLen = str.length();
        if(iLen == Size)
            return str;
        if(iLen > Size)
            return str.substring(0, Size);
        else
            return str;
    }

    public static String getToken(String sBuffer, int iIndex, char cDelimiter)
    {
        int iOffset1 = 0;
        int iOffset2 = 0;
        int iCount = 0;
        if(iIndex <= 0)
            return null;
        String sNewBuffer = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(cDelimiter)))).append(sBuffer).append(cDelimiter)));
        int iLen = sNewBuffer.length();
        for(int ii = 0; ii < iLen; ii++)
        {
            if(sNewBuffer.charAt(ii) != cDelimiter)
                continue;
            if(++iCount == iIndex)
                iOffset1 = ii;
            if(iCount != iIndex + 1)
                continue;
            iOffset2 = ii;
            break;
        }

        if(iOffset2 == 0)
            return new String("");
        else
            return sNewBuffer.substring(iOffset1 + 1, iOffset2);
    }

    public static String printlnByte(byte b_print[], String sTypeToDisplay)
    {
        String sResult = "";
        char hexDigit[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
        };
        for(int ii = 0; ii < b_print.length; ii++)
        {
            byte b = b_print[ii];
            if(sTypeToDisplay.toUpperCase().trim().equals("HEX"))
            {
                char array[] = {
                    hexDigit[b >> 4 & 0xf], hexDigit[b & 0xf]
                };
                sResult = String.valueOf(sResult) + String.valueOf(new String(array));
                continue;
            }
            if(sTypeToDisplay.toUpperCase().trim().equals("DECIMAL"))
                sResult = String.valueOf(sResult) + String.valueOf(b);
        }

        return sResult;
    }

    public static String printlnByte(byte b_print, String sTypeToDisplay)
    {
        String sResult = "";
        char hexDigit[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
        };
        if(sTypeToDisplay.toUpperCase().trim().equals("HEX"))
        {
            char array[] = {
                hexDigit[b_print >> 4 & 0xf], hexDigit[b_print & 0xf]
            };
            sResult = String.valueOf(sResult) + String.valueOf(new String(array));
        }
        return sResult;
    }


    protected static String getWorkStationDateStr(String DateFormat)
    {
        Date dtNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
        return sdf.format(dtNow);
    }

    protected static String StringPad(String sString, int MaxLength, char cChar)
    {
        int iPadSpace = MaxLength - sString.length();
        String sPadSpace = "";
        for(int iSpace = 0; iSpace < iPadSpace; iSpace++)
            sPadSpace = String.valueOf(sPadSpace) + String.valueOf(cChar);

        return sPadSpace;
    }

    public static String convert(String rawdata)
        throws Exception
    {
        if(rawdata == null)
        {
            return null;
        } else
        {
            byte rawBytes[] = rawdata.getBytes();
            return new String(rawBytes, "Cp935");
        }
    }

    protected static void chmod(String sPath, String filename)
    {
        if(System.getProperty("os.name").equals("Linux"))
        {
            String prog = "/bin/chmod";
            String option = "a+w";
            String fileName = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(sPath)))).append(sFileSeparator).append(filename)));
            String arrProg[] = new String[3];
            arrProg[0] = prog;
            arrProg[1] = option;
            arrProg[2] = fileName;
            Runtime rt = Runtime.getRuntime();
            Process process;
            try
            {
                process = rt.exec(arrProg);
            }
            catch(Exception fe)
            {
               fe.printStackTrace();
            }
        }
    }

//    public static Bits getBits(String data, int byteLocation)
//    {
//        String hdr = "Utils.getBits():";
//        if(data == null)
//        {
//            System.out.println(String.valueOf(String.valueOf(hdr)).concat("Error: Invalid data input string."));
//            return null;
//        }
//        int iSize = data.length();
//        if(byteLocation > iSize - 1 || byteLocation < 0)
//        {
//            System.out.println(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(hdr)))).append("Error: Invalid byte location[").append(byteLocation).append("]."))));
//            return null;
//        } else
//        {
//            byte ba[] = data.getBytes();
//            return Bits.parseByte(ba[byteLocation]);
//        }
//    }
//
//    public static boolean isBitTurnOn(String data, int byteLocation, int bitLocation)
//        throws Exception
//    {
//        String hdr = "Util.isBitTurnOn:";
//        Bits b = getBits(data, byteLocation);
//        if(b == null)
//            throw new Exception(String.valueOf(String.valueOf(hdr)).concat("Error: Null Bits object."));
//        if(bitLocation < 0 || bitLocation > 7)
//            throw new Exception(String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(hdr)))).append("Error: Invalid bit location[").append(bitLocation).append("]."))));
//        else
//            return b.get(bitLocation);
//    }

    public static String padChars(String str, int MaxLength, char c)
    {
        return padChars(str, MaxLength, c, "RIGHT");
    }

    public static String padChars(String str, int MaxLength, char c, String sJustified)
    {
        if(str == null)
            return str;
        if(str.length() >= MaxLength)
            return str;
        StringBuffer sbZeroes = new StringBuffer();
        if(str.length() < MaxLength)
        {
            for(int i = 0; i < MaxLength - str.length(); i++)
                sbZeroes.append(c);

        }
        if(sJustified != null && sJustified.equalsIgnoreCase("LEFT"))
            return String.valueOf(str) + String.valueOf(sbZeroes.toString());
        else
            return String.valueOf(sbZeroes.toString()) + String.valueOf(str);
    }

    
}
