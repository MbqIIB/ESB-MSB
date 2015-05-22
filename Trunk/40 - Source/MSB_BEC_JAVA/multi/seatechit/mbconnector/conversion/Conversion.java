package multi.seatechit.mbconnector.conversion;

import multi.seatechit.mbconnector.utils.Utils;


public class Conversion    
{

    public Conversion()
    {
        
    }

    protected byte[] Ascii2Ebcdic(String sAscii)
    {
        return _$14300(sAscii);
    }

    protected byte[] Ascii2Ebcdic(byte byteAscii[])
    {
        return _$14300(byteAscii);
    }

    protected byte[] Ebcdic2Ascii(String sEbcdic)
    {
        return _$14303(sEbcdic);
    }

    protected byte[] Ebcdic2Ascii(byte byteEbcdic[])
    {
        return _$14303(byteEbcdic);
    }

    private byte[] _$14303(String sEbcdic)
    {
        return _$14303(sEbcdic.getBytes());
    }

    private byte[] _$14303(byte bEbcdic[])
    {
        byte bAscii[] = new byte[bEbcdic.length];
        for(int i = 0; i < bEbcdic.length; i++)
            bAscii[i] = _$14297[convertByteToInt(bEbcdic[i])];

        return bAscii;
    }

    private byte[] _$14300(String sAscii)
    {
        return _$14300(sAscii.getBytes());
    }

    private byte[] _$14300(byte bAscii[])
    {
        byte bEbcdic[] = new byte[bAscii.length];
        for(int i = 0; i < bAscii.length; i++)
            bEbcdic[i] = _$14298[bAscii[i]];

        return bEbcdic;
    }

    protected int convertByteToInt(byte aByte)
    {
        return aByte >= 0 ? aByte : aByte + 256;
    }

    protected byte[] charToHex(char szData[], int iLength)
    {
        char p[] = new char[iLength * 2];
        for(int ii = 0; ii < p.length && ii < szData.length; ii++)
            p[ii] = szData[ii];

        byte pDataInHex[] = new byte[iLength];
        int i = 0;
        for(int j = 0; i < iLength * 2; j++)
        {
            int k = i;
            int t1;
            if(p[i] >= '0' && p[i] <= '9')
                t1 = p[i] - 48;
            else
                t1 = p[i] - 55;
            k++;
            int t2;
            if(p[k] >= '0' && p[k] <= '9')
                t2 = p[k] - 48;
            else
                t2 = p[k] - 55;
            pDataInHex[j] = (byte)(t1 * 16 + t2);
            i += 2;
        }

        return pDataInHex;
    }

    protected byte[] charToByte(char szData[], int iLength)
    {
        char p[] = szData;
        byte pDataInHex[] = new byte[iLength];
        int i = 0;
        for(int j = 0; i < iLength * 2; j++)
        {
            int k = i;
            int t1;
            if(p[i] >= '0' && p[i] <= '9')
                t1 = p[i] - 48;
            else
                t1 = p[i] - 55;
            k++;
            int t2;
            if(p[k] >= '0' && p[k] <= '9')
                t2 = p[k] - 48;
            else
                t2 = p[k] - 55;
            pDataInHex[j] = (byte)(t1 * 16 + t2);
            i += 2;
        }

        return pDataInHex;
    }

    protected static String packedToStr(byte szData[], int iLength, int iDecimal)
    {
        char cData[] = new char[iLength * 2];
        StringBuffer sb = new StringBuffer();
        String sData = "";
        String sFormat = "";
        String sFormatDec = ".";
        int i = 0;
        int j;
        for(j = 0; i < iLength; j += 2)
        {
            int k = (szData[i] >> 4) + 48;
            if(k >= 48)
                cData[j] = (char)((szData[i] >> 4) + 48);
            else
                cData[j] = (char)((szData[i] >> 4) + 64);
            cData[j + 1] = (char)((szData[i] & 0xf) + 48);
            i++;
        }

        int nSign = cData[j - 1] != '?' ? -1 : 1;
        cData[j - 1] = '\0';
        for(int ii = 0; ii < iLength * 2 - 1; ii++)
            sb.append(cData[ii]);

        sData = sb.toString();
        try
        {
            if(iDecimal > 0)
            {
                int iEnd = sData.length() - iDecimal;
                String NDec = sData.substring(0, iEnd);
                String Dec = sData.substring(iEnd, sData.length());
                sData = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(NDec)))).append(".").append(Dec)));
            }
            sData = Utils.rmLeadingZeroes(sData);
            if(nSign < 0)
                sData = "-".concat(String.valueOf(String.valueOf(sData)));
        }
        catch(NumberFormatException nfe)
        {
           nfe.printStackTrace();
        }
        return sData;
    }

    protected String ZoneToStr(byte szData[], int iLength, int iDecimal)
    {
        String sBuffer = "";
        int nBytes = szData.length;
        for(int i = 0; i < nBytes; i++)
            sBuffer = String.valueOf(sBuffer) + String.valueOf((char)szData[i]);

        String sNonDecBuffer = sBuffer.substring(0, sBuffer.length() - iDecimal);
        String sDecBuffer = sBuffer.substring(sBuffer.length() - iDecimal, sBuffer.length());
        sBuffer = String.valueOf(sNonDecBuffer) + String.valueOf(sBuffer);
        return sBuffer;
    }

    protected byte[] strToPacked(String sData, int iLength, int iDecimal)
    {
        char c_packedData[] = new char[iLength * 2];
        int i_currentPackedPosition = 0;
        int i_currentDataPosition = 0;
        int iTempDecimal = iDecimal;
        if(!sData.equals(""))
        {
            if(sData.indexOf(".") != -1)
            {
                String sNonDecimalPart = Utils.getToken(sData, 1, '.');
                String sDecimalPart = "";
                sDecimalPart = Utils.getToken(sData, 2, '.');
                sDecimalPart = Utils.padTrailingZeroes(Utils.trimStr(sDecimalPart, iDecimal), iDecimal);
                if(sDecimalPart.trim().length() != iDecimal)
                {
                    _$14332(String.valueOf(String.valueOf((new StringBuffer("Invalid Packed Number - [")).append(sData).append(" should have ").append(String.valueOf(iDecimal)).append(" decimal digits.]"))));
                    return null;
                }
                sData = String.valueOf(sNonDecimalPart) + String.valueOf(sDecimalPart);
            }
            char c_data[] = new char[sData.length()];
            sData.getChars(0, sData.length(), c_data, 0);
            i_currentPackedPosition = iLength * 2 - 1;
            c_packedData[i_currentPackedPosition] = sData.substring(0, 1).equals("-") ? 'D' : 'F';
            i_currentDataPosition = sData.length() - 1;
            for(i_currentPackedPosition--; i_currentPackedPosition >= 0; i_currentPackedPosition--)
            {
                if(i_currentDataPosition < 0)
                    c_packedData[i_currentPackedPosition] = '0';
                else
                if(c_data[i_currentDataPosition] == '-')
                    c_packedData[i_currentPackedPosition] = '0';
                else
                    c_packedData[i_currentPackedPosition] = c_data[i_currentDataPosition];
                i_currentDataPosition--;
            }

        }
        return charToByte(c_packedData, iLength);
    }

    protected byte[] strToZone(String sData, int iLength, int iDecimal)
    {
        if(sData.length() > iLength)
        {
            _$14332(String.valueOf(String.valueOf((new StringBuffer("Invalid Zone Type -[")).append(sData).append(" exceeds length ").append(String.valueOf(iLength)).append(" ]"))));
            return null;
        }
        byte byteNumeric[] = new byte[iLength];
        String sTempData = "";
        String sNonDecimalPart = Utils.getToken(sData, 1, '.');
        String sDecimalPart = Utils.getToken(sData, 2, '.');
        if(sDecimalPart.trim().length() != iDecimal)
        {
            _$14332(String.valueOf(String.valueOf((new StringBuffer("Invalid Zone Number - [")).append(sData).append(" should have ").append(String.valueOf(iDecimal)).append(" decimal digits.]"))));
            return null;
        }
        sData = String.valueOf(sNonDecimalPart) + String.valueOf(sDecimalPart);
        for(int ii = 0; ii < iLength; ii++)
            byteNumeric[ii] = 48;

        for(int ii = 0; ii < sData.length(); ii++)
        {
            sTempData = String.valueOf(sData.trim().toCharArray()[ii]);
            byteNumeric[(iLength - sData.length()) + ii] = (byte)(Integer.parseInt(sTempData) + 48);
        }

        byteNumeric = Ascii2Ebcdic(byteNumeric);
        return byteNumeric;
    }

    protected byte[] toFixed4ByteBINARY(String sData, int iLength)
    {
        byte fixed4ByteBINARY[] = new byte[4];
        char fixed4CharBINARY[] = new char[4];
        int iData = Integer.parseInt(sData);
        sData = Integer.toHexString(iData);
        byte byteNewData[] = {
            0, 0, 0, 0
        };
        String sDataPortion = sData;
        int iEnd = sData.length();
        for(int ii = 0; ii < 4; ii++)
        {
            if(iEnd > 0)
            {
                if(iEnd - 2 >= 0)
                    sDataPortion = sData.substring(iEnd - 2, iEnd);
                else
                    sDataPortion = sData.substring(0, iEnd);
            } else
            {
                sDataPortion = "";
            }
            iEnd -= 2;
            if(!sDataPortion.equals(""))
                byteNewData[3 - ii] = (byte)Integer.parseInt(sDataPortion, 16);
            else
                byteNewData[3 - ii] = 0;
        }

        for(int ii = 0; ii < 4; ii++)
            fixed4ByteBINARY[ii] = byteNewData[ii];

        return fixed4ByteBINARY;
    }

    protected byte[] toFixed4ByteBINARY(String sData, int iLength, boolean test)
    {
        byte fixed4ByteBINARY[] = new byte[4];
        char fixed4CharBINARY[] = new char[4];
        int iData = Integer.parseInt(sData);
        sData = Integer.toHexString(iData);
        System.out.println(String.valueOf(String.valueOf((new StringBuffer("iData=")).append(iData).append(", sData=").append(sData))));
        byte byteNewData[] = {
            0, 0, 0, 0
        };
        String sDataPortion = sData;
        for(int ii = 0; ii < 4; ii++)
        {
            if(sData.length() - 2 - ii > 0)
                sDataPortion = sData.substring(sData.length() - 2 - ii, sData.length() - ii);
            else
            if(sData.length() - 1 - ii > 0 && ii > 0)
                sDataPortion = sData.substring(0, 1);
            else
            if(sData.length() - 1 - ii > 0 && ii == 0)
            {
                if(sData.length() >= 2)
                    sDataPortion = sData.substring(0, 2);
                else
                    sDataPortion = sData.substring(0, 1);
            } else
            {
                sDataPortion = "";
            }
            System.out.println(String.valueOf(String.valueOf((new StringBuffer("No ")).append(ii).append(" Data=[").append(sDataPortion).append("] parsed=[").append(sDataPortion.equals("") ? 0 : Integer.parseInt(sDataPortion, 16)).append("]"))));
            if(!sDataPortion.equals(""))
                byteNewData[3 - ii] = (byte)Integer.parseInt(sDataPortion, 16);
            else
                byteNewData[3 - ii] = 0;
        }

        for(int ii = 0; ii < 4; ii++)
            fixed4ByteBINARY[ii] = byteNewData[ii];

        return fixed4ByteBINARY;
    }

    protected byte[] toNumericEbcdic(String sData, int iLength)
    {
        if(sData.length() > iLength)
        {
            _$14332(String.valueOf(String.valueOf((new StringBuffer("Invalid Numeric Type -[")).append(sData).append(" exceeds length ").append(String.valueOf(iLength)).append(" ]"))));
            return null;
        }
        byte byteNumeric[] = new byte[iLength];
        String sTempData = "";
        for(int ii = 0; ii < iLength; ii++)
            byteNumeric[ii] = 48;

        for(int ii = 0; ii < sData.length(); ii++)
        {
            sTempData = String.valueOf(sData.trim().toCharArray()[ii]);
            byteNumeric[(iLength - sData.length()) + ii] = (byte)(Integer.parseInt(sTempData) + 48);
        }

        byteNumeric = Ascii2Ebcdic(byteNumeric);
        return byteNumeric;
    }

    private void _$14332(String sErrorDescription)
    {
        try
        {
            throw new Exception(sErrorDescription);
        }
        catch(Exception ae)
        {
           ae.printStackTrace();
        }
    }

    protected String byteToStr(byte byteBuffer[])
    {
        String sBuffer = "";
        int nBytes = byteBuffer.length;
        for(int i = 0; i < nBytes; i++)
            sBuffer = String.valueOf(sBuffer) + String.valueOf((char)byteBuffer[i]);

        return sBuffer;
    }

    protected static String packedToStr(byte szData[], int iLength, int iDecimal, boolean b)
    {
        char cData[] = new char[iLength * 2];
        String sData = "";
        String sFormat = "";
        String sFormatDec = ".";
        int i = 0;
        int j;
        for(j = 0; i < iLength; j += 2)
        {
            int k = (szData[i] >> 4) + 48;
            if(k >= 48)
                cData[j] = (char)((szData[i] >> 4) + 48);
            else
                cData[j] = (char)((szData[i] >> 4) + 64);
            cData[j + 1] = (char)((szData[i] & 0xf) + 48);
            i++;
        }

        int nSign = cData[j - 1] != '?' ? -1 : 1;
        cData[j - 1] = '\0';
        for(int ii = 0; ii < iLength * 2 - 1; ii++)
            sData = String.valueOf(sData) + String.valueOf(String.valueOf(cData[ii]));

        try
        {
            if(iDecimal > 0)
            {
                int iEnd = sData.length() - iDecimal;
                String NDec = sData.substring(0, iEnd);
                String Dec = sData.substring(iEnd, sData.length());
                sData = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(NDec)))).append(".").append(Dec)));
            }
            sData = Utils.rmLeadingZeroes(sData);
            if(nSign < 0)
                sData = "-".concat(String.valueOf(String.valueOf(sData)));
        }
        catch(NumberFormatException nfe)
        {
           nfe.printStackTrace();
        }
        return sData;
    }

    private static void _$14342()
    {
        String data = "99999999";
        int iLength = 4;
        Conversion cvs = new Conversion();
        byte test[] = cvs.toFixed4ByteBINARY(data, iLength, false);
        System.out.print(String.valueOf(String.valueOf((new StringBuffer("\nData=")).append(data).append(" iLength=").append(iLength).append(". Converted to ").append(test.length).append(" bytes."))));
        System.out.print("\nFormatted as [");
        for(int i = 0; i < test.length; i++)
            System.out.print(test.toString());

        System.out.print("]\n");
        int iData = Integer.parseInt(data);
        String sData = Integer.toHexString(iData);
        byte bData[] = new byte[4];
        bData = sData.getBytes();
    }

    private static void _$14344()
    {
        Conversion cvs = new Conversion();
        String data = "-61201.05";
        String data1 = "61201.05";
        byte b[] = cvs.strToPacked(data, 6, 2);
        byte b1[] = cvs.strToPacked(data1, 6, 2);
        int iLength = 6;
        int iDecimal = 2;
        String formatted = packedToStr(b, iLength, iDecimal, true);
        String formatted1 = packedToStr(b1, iLength, iDecimal, true);
        System.out.println(String.valueOf(String.valueOf((new StringBuffer("Data=[")).append(data).append("] Formatted=[").append(formatted).append("]"))));
        System.out.println(String.valueOf(String.valueOf((new StringBuffer("Data=[")).append(data1).append("] Formatted=[").append(formatted1).append("]"))));
    }

    protected byte[] Ascii2EbcdicViet(String sAscii)
    {
        return _$14351(sAscii);
    }

    protected byte[] Ascii2EbcdicViet(byte byteAscii[])
    {
        return _$14351(byteAscii);
    }

    protected byte[] EbcdicViet2Ascii(String sEbcdic)
    {
        return _$14352(sEbcdic);
    }

    protected String EbcdicViet2Ascii(byte byteEbcdic[])
    {
        try
        {
            String s = new String(_$14352(byteEbcdic), "UTF8");
            return s;
        }
        catch(Exception e)
        {
            e.toString();
        }
        return "";
    }

    private byte[] _$14352(String sEbcdic)
    {
        try
        {
            byte abyte0[] = _$14352(sEbcdic.getBytes("UTF8"));
            return abyte0;
        }
        catch(Exception e)
        {
            byte abyte1[] = new byte[0];
            return abyte1;
        }
    }

    private byte[] _$14352(byte bEbcdic[])
    {
        byte bAscii[] = new byte[bEbcdic.length];
        for(int i = 0; i < bEbcdic.length; i++)
            bAscii[i] = _$14297[convertByteToInt(bEbcdic[i])];

        return bAscii;
    }

    private byte[] _$14351(String sAscii)
    {
        char ca[] = sAscii.toCharArray();
        Object oa[] = new Object[sAscii.length()];
        int iCounter = 0;
        int i = 0;
        int j = 0;
        int iByteSizeCount = 0;
        for(i = 0; i < ca.length; i++)
            try
            {
                byte baTmp[] = (new String("".concat(String.valueOf(String.valueOf(ca[i]))))).getBytes("UTF8");
                iByteSizeCount += baTmp.length;
                oa[i] = baTmp;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        byte ba[] = new byte[iByteSizeCount];
        for(i = 0; i < oa.length; i++)
        {
            byte tmp[] = (byte[])oa[i];
            for(j = 0; j < tmp.length;)
            {
                ba[iCounter] = tmp[j];
                j++;
                iCounter++;
            }

        }

        return _$14351(ba);
    }

    private byte[] _$14351(byte bAscii[])
    {
        byte bEbcdic[] = new byte[bAscii.length];
        for(int i = 0; i < bAscii.length; i++)
            bEbcdic[i] = _$14298[convertByteToInt(bAscii[i])];

        return bEbcdic;
    }

    
    private byte _$14297[] = {
        0, 1, 2, 3, -100, 9, -122, 127, -105, -115, 
        -114, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        -99, -123, 8, -121, 24, 25, -110, -113, 28, 29, 
        30, 31, -128, -127, -126, -125, -124, 10, 23, 27, 
        -120, -119, -118, -117, -116, 5, 6, 7, -112, -111, 
        22, -109, -108, -107, -106, 4, -104, -103, -102, -101, 
        20, 21, -98, 26, 32, -96, -95, -94, -93, -92, 
        -91, -90, -89, -88, 91, 46, 60, 40, 43, 93, 
        38, -87, -86, -85, -84, -83, -82, -81, -80, -79, 
        33, 36, 42, 41, 59, 94, 45, 47, -78, -77, 
        -76, -75, -74, -73, -72, -71, 124, 44, 37, 95, 
        62, 63, -70, -69, -68, -67, -66, -65, -64, -63, 
        -62, 96, 58, 35, 64, 39, 61, 34, -61, 97, 
        98, 99, 100, 101, 102, 103, 104, 105, -60, -59, 
        -58, -57, -56, -55, -54, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, -53, -52, -51, -50, -49, -48, 
        -47, 126, 115, 116, 117, 118, 119, 120, 121, 122, 
        -46, -45, -44, -43, -42, -41, -40, -39, -38, -37, 
        -36, -35, -34, -33, -32, -31, -30, -29, -28, -27, 
        -26, -25, 123, 65, 66, 67, 68, 69, 70, 71, 
        72, 73, -24, -23, -22, -21, -20, -19, 125, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, -18, -17, 
        -16, -15, -14, -13, 92, -98, 83, 84, 85, 86, 
        87, 88, 89, 90, -12, -11, -10, -9, -8, -7, 
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
        -6, -5, -4, -3, -2, -1
    };
    private byte _$14298[] = {
        0, 1, 2, 3, 55, 45, 46, 47, 22, 5, 
        37, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        60, 61, 50, 38, 24, 25, 63, 39, 28, 29, 
        30, 31, 64, 90, 127, 123, 91, 108, 80, 125, 
        77, 93, 92, 78, 107, 96, 75, 97, -16, -15, 
        -14, -13, -12, -11, -10, -9, -8, -7, 122, 94, 
        76, 126, 110, 111, 124, -63, -62, -61, -60, -59, 
        -58, -57, -56, -55, -47, -46, -45, -44, -43, -42, 
        -41, -40, -39, -30, -29, -28, -27, -26, -25, -24, 
        -23, 74, -32, 79, 95, 109, 121, -127, -126, -125, 
        -124, -123, -122, -121, -120, -119, -111, -110, -109, -108, 
        -107, -106, -105, -104, -103, -94, -93, -92, -91, -90, 
        -89, -88, -87, -64, 106, -48, -95, 7, 32, 33, 
        34, 35, 36, 21, 6, 23, 40, 41, 42, 43, 
        44, 9, 10, 27, 48, 49, 26, 51, 52, 53, 
        54, 8, 56, 57, 58, 59, 4, 20, 62, -31, 
        65, 66, 67, 68, 69, 70, 71, 72, 73, 81, 
        82, 83, 84, 85, 86, 87, 88, 89, 98, 99, 
        100, 101, 102, 103, 104, 105, 112, 113, 114, 115, 
        116, 117, 118, 119, 120, -128, -118, -117, -116, -115, 
        -114, -113, -112, -102, -101, -100, -99, -98, -97, -96, 
        -86, -85, -84, -83, -82, -81, -80, -79, -78, -77, 
        -76, -75, -74, -73, -72, -71, -70, -69, -68, -67, 
        -66, -65, -54, -53, -52, -51, -50, -49, -38, -37, 
        -36, -35, -34, -33, -22, -21, -20, -19, -18, -17, 
        -6, -5, -4, -3, -2, -1
    };
    private byte _$14349[] = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 
        30, 31, 64, 79, 127, 123, 91, 108, 80, 125, 
        77, 93, 92, 78, 107, 96, 75, 97, -16, -15, 
        -14, -13, -12, -11, -10, -9, -8, -7, 122, 94, 
        76, 126, 110, 111, 124, -63, -62, -61, -60, -59, 
        -58, -57, -56, -55, -47, -46, -45, -44, -43, -42, 
        -41, -40, -39, -30, -29, -28, -27, -26, -25, -24, 
        -23, 74, -32, 90, 95, 109, 121, -127, -126, -125, 
        -124, -123, -122, -121, -120, -119, -111, -110, -109, -108, 
        -107, -106, -105, -104, -103, -94, -93, -92, -91, -90, 
        -89, -88, -87, -64, -69, -48, -95, -1, -1, -1, 
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
        -99, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
        -1, -1, -1, -1, -1, -1, -67, -1, -1, -66, 
        65, -86, -80, -79, -97, -78, 106, -75, -1, -76, 
        -102, -118, -70, -54, -81, -68, -112, -113, -22, -6, 
        -1, -96, -74, -77, -1, -38, -101, -117, -73, -72, 
        -71, -85, 100, 101, 98, 102, 99, 103, -98, 104, 
        116, 113, 114, 115, -114, 117, 118, 119, -84, 105, 
        -115, -18, -21, -17, -20, -65, -128, -3, -2, -5, 
        -4, -19, 88, 89, 68, 69, 66, 70, 67, 71, 
        -100, 72, 84, 81, 82, 83, -82, 85, 86, 87, 
        -116, 73, -83, -50, -53, -49, -52, -31, 112, -35, 
        -34, -37, -36, -51, 120, -33
    };
    private byte _$14350[] = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 
        30, 31, 63, 63, 63, 63, 63, 63, 63, 63, 
        63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 
        63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 
        63, 63, 63, 63, 32, -96, -30, -28, -32, -31, 
        -29, -27, -25, -15, 91, 46, 60, 40, 43, 33, 
        38, -23, -22, -21, -24, -19, -18, -17, -34, -33, 
        93, 36, 42, 41, 59, 94, 45, 47, -62, -60, 
        -64, -63, -61, -59, -57, -47, -90, 44, 37, 95, 
        62, 63, -8, -55, -54, -53, -56, -51, -50, -49, 
        -2, 96, 58, 35, 64, 39, 61, 34, -40, 97, 
        98, 99, 100, 101, 102, 103, 104, 105, -85, -69, 
        -16, -46, -52, -79, -80, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, -86, -70, -26, -116, -58, -92, 
        -75, 126, 115, 116, 117, 118, 119, 120, 121, 122, 
        -95, -65, -48, -14, -20, -82, -94, -93, -91, -73, 
        -87, -89, -74, -68, -67, -66, -84, 124, -81, -100, 
        -97, -41, 123, 65, 66, 67, 68, 69, 70, 71, 
        72, 73, -83, -12, -10, -3, -13, -11, 125, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, -71, -5, 
        -4, -7, -6, -1, 92, -9, 83, 84, 85, 86, 
        87, 88, 89, 90, -78, -44, -42, -35, -45, -43, 
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
        -77, -37, -36, -39, -38, 63
    };
}