/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multi.seatechit.mbconnector.conversion;

import java.util.Hashtable;
import java.util.StringTokenizer;
import multi.seatechit.mbconnector.utils.Utils;





/**
 *
 * @author Administrator
 */
public class MessageConversion
{
    Conversion cvs;
    Utils utils;
    private static char charSpecial_1 = '|';
    private Hashtable hashTable_1;
    private Hashtable hashTable_2;
    private Hashtable hashTable_3;
    private byte receiveData[];
    private String strSendData;
    private int receiveDataLen;
    private int dataPosition;
    private boolean isEOMflag;
    private boolean hasResponseV2Format;
    private String v2ResponseCode;
    private Hashtable hashTable_4;
    private String strExtension;
    private String decodedValue;
    private StringBuffer strResponseHeader;
    private byte newReceiveData[];
    private String formatID;
    private String _$1856;
    private int totalNoRecord;
    private String transCode;
    private int intUnrepeatedKey;
    public boolean TRIM_ON;
    final int SOCKETMESSAGE_LENGTH = 4;
    final int MONENTARYHEADER_LENGTH = 320;
    private Object responseTemplate1[][];
    private Object responseTemplate2[][];
    private Object responseTemplate3[][];
    private Object responseTemplate4[][];
    private int lenTemplate1_1;
    private int lenTemplate2_1;
    private int lenTemplate1_2;
    private int lenTemplate2_2;
    private boolean isResponseStatus;
    private boolean isAppResponseStatus;
    private String strLastErrCode;
    private String strLastErrorMsg;
    private String strOverridErrorCode;
    private int intTotalRecordReceived;
    private String strMoreRecInd;
    private String strEOMStatus;
    private String strHeaderName;
    private String strBodyName;

    public void setEOMflag(boolean stat)
    {
        isEOMflag = stat;
    }

    public boolean getEOMflag()
    {
        return isEOMflag;
    }

    public int getUnrepeatedKey()
    {
        return intUnrepeatedKey;
    }

    public void setTrimOption(boolean value)
    {
        TRIM_ON = value;
    }

    public void resetTotalNoRecord()
    {
        totalNoRecord = 0;
    }

    public boolean getMiddleWareResponseStatus()
    {
        return isResponseStatus;
    }

    public boolean getApplicationResponseStatus()
    {
        return isAppResponseStatus;
    }

    public String getLastErrorCode()
    {
        return strLastErrCode;
    }

    public String getLastErrorMsg()
    {
        return strLastErrorMsg;
    }

    public String getOverrideErrorCode()
    {
        return strOverridErrorCode;
    }

    public int getTotalRecordReceived()
    {
        return intTotalRecordReceived;
    }

    public String getMoreRecInd()
    {
        return strMoreRecInd;
    }

    public String getEOMStatus()
    {
        return strEOMStatus;
    }

    public void setHeaderName(String newHeaderName)
    {
        strHeaderName = newHeaderName;
    }

    public String getHeaderName()
    {
        return strHeaderName;
    }

    public void setBodyName(String newBodyName)
    {
        strBodyName = newBodyName;
    }

    public String getBodyName()
    {
        return strBodyName;
    }
    public void setReponseCodeExtension(String extension)
    {
        if(extension != null)
            strExtension = extension;
    }
    public MessageConversion()
    {
        receiveData = null;
        strSendData = "";
        dataPosition = 0;
        isEOMflag = false;
        hasResponseV2Format = false;
        v2ResponseCode = null;
        hashTable_4 = null;
        strExtension = "V2";
        decodedValue = "";
        newReceiveData = null;
        formatID = "";
        _$1856 = "";
        transCode = "";
        intUnrepeatedKey = 0;
        TRIM_ON = false;
        responseTemplate1 = null;
        responseTemplate2 = null;
        responseTemplate3 = null;
        responseTemplate4 = null;
        lenTemplate1_1 = 323;
        lenTemplate2_1 = 249;
        lenTemplate1_2 = 323;
        lenTemplate2_2 = 249;
        isResponseStatus = false;
        isAppResponseStatus = false;
        strLastErrCode = "";
        strLastErrorMsg = "";
        strOverridErrorCode = "";
        intTotalRecordReceived = 0;
        strMoreRecInd = "";
        strEOMStatus = "";
        strHeaderName = new String();
        strBodyName = new String();
        cvs = new Conversion();
        hashTable_1 = new Hashtable();
        hashTable_2 = new Hashtable();
        hashTable_3 = new Hashtable();
       contructResponseTemplate();
        try
        {
            String sTrimOption = Utils.getHostCommSetting("settings.trim");
            if(sTrimOption.toUpperCase().equals("Y"))
                setTrimOption(true);
        }
        catch(Exception e)
        {
            System.out.println("E HostMsg: [settings.trim] not defined.");
        }
    }
    public byte[] encodeNonMonetary(String sNewSendData)
    {
        strSendData = sNewSendData;
        hashTable_2 = new Hashtable();
        if(strHeaderName.equals(""))
            strHeaderName = "MBASEHEAD";
        if(strBodyName.equals(""))
            strBodyName = String.valueOf(String.valueOf(Utils.getToken(strSendData, 28, charSpecial_1))).concat("I");
//             strBodyName = "MBASEBODY";
        hashTable_2.put("1", strHeaderName);
        hashTable_2.put("2", strBodyName);
        dataPosition = 0;
        return endCode();
    }

    public byte[] encodeMonetary(String sNewSendData)
       {
        strSendData = sNewSendData;
        if(strHeaderName.equals(""))
            hashTable_2.put("1", "ABCSHEADER");
        else
            hashTable_2.put("1", strHeaderName);
        if(strBodyName.equals(""))
            hashTable_2.put("2", "ABCSBODY");
        else
            hashTable_2.put("2", strBodyName);
          dataPosition = 0;
        return endCode();
      
    }

    private void reset()
    {
        strResponseHeader = new StringBuffer();
        strEOMStatus = "";
        decodedValue = "";
    }

    private byte[] endCode() {
        try {
            reset();
            int iHostDataLen = 0;
            int iCurrentDataPosition = dataPosition;
            receiveDataLen = 0;
            String strSendMsg = "";
            for (int ii = 1; ii <= hashTable_2.size(); ii++) {
                int iByte = 0;
                String sFormatID = (String) hashTable_2.get(String.valueOf(ii));
                iByte = MessageFormatFile.getTotalByte(sFormatID);
                receiveDataLen = receiveDataLen + iByte;
            }
            receiveData = new byte[receiveDataLen];
            for (int ii = 0; ii < receiveDataLen; ii++) {
                receiveData[ii] = 64;
            }
            int iBufferFieldLength = 1;
            int iSendPartNo = 1;
            label0:
            for (iSendPartNo = 1; iSendPartNo <= hashTable_2.size(); iSendPartNo++) {
                hashTable_1 = MessageFormatFile.getFieldInformation((String) hashTable_2.get(String.valueOf(iSendPartNo)));
                String sSendDataPart = "";
                String sFieldRec = "";
                String sFieldType = "";
                String sFieldName = "";
                int iFieldLength = 0;
                int iFieldDecPnt = 0;
                 iBufferFieldLength = 1;
                    do {
                        if (iBufferFieldLength>= hashTable_1.size()) {
                            continue label0;
                        }
                        sFieldRec = (String) hashTable_1.get("FIELD".concat(String.valueOf(String.valueOf(String.valueOf(iBufferFieldLength)))));
                        if (!sFieldRec.equals("")) {
                            sFieldRec = sFieldRec.trim();
                        }
                        sFieldType = Utils.getToken(sFieldRec, 1, ',');
                        if (!sFieldType.equals("")) {
                            sFieldType = sFieldType.trim();
                        }
                        if (!Utils.getToken(sFieldRec, 2, ',').equals("")) {
                            iFieldLength = Integer.parseInt(Utils.getToken(sFieldRec, 2, ','));
                        }
                        if (!Utils.getToken(sFieldRec, 3, ',').equals("")) {
                            iFieldDecPnt = Integer.parseInt(Utils.getToken(sFieldRec, 3, ','));
                        }
                        String fieldName = Utils.getToken(sFieldRec, 4, ',');
                        if (fieldName.equals("")) {
                            fieldName = "FIELD".concat(String.valueOf(iBufferFieldLength));
                        }
                        sSendDataPart=Utils.getToken(strSendData, ++iCurrentDataPosition, charSpecial_1);
                        byte[] bPortionEncodedData = new byte[iFieldLength];
                        if (!sSendDataPart.trim().equals("") && sSendDataPart != null) {
                            if (sFieldType.equals("CHAR")) {
                                bPortionEncodedData = cvs.Ascii2Ebcdic(sSendDataPart);
                            } else if (sFieldType.equals("VIET")) {
                                bPortionEncodedData = cvs.Ascii2EbcdicViet(sSendDataPart);
                            } else if (sFieldType.equals("PACKED")) {
                                bPortionEncodedData = cvs.strToPacked(sSendDataPart, iFieldLength, iFieldDecPnt);
                            } else if (sFieldType.equals("HEX")) {
                                bPortionEncodedData = cvs.charToHex(sSendDataPart.toCharArray(), iFieldLength);
                            } else if (sFieldType.equals("BINARY")) {
                                if (iSendPartNo == 1 && iBufferFieldLength == 1) {
                                    bPortionEncodedData = cvs.toFixed4ByteBINARY(String.valueOf(receiveDataLen - iFieldLength), iFieldLength);
                                } else {
                                    bPortionEncodedData = cvs.toFixed4ByteBINARY(sSendDataPart, iFieldLength);
                                }
                            } else if (sFieldType.equals("NUMERIC")) {
                                if (iSendPartNo == 1 && iBufferFieldLength == 1) {
                                    bPortionEncodedData = cvs.toNumericEbcdic(utils.padChars(String.valueOf(receiveDataLen - iFieldLength), iFieldLength, '0'), iFieldLength);
                                } else {
                                    bPortionEncodedData = cvs.toNumericEbcdic(utils.padChars(sSendDataPart, iFieldLength, '0', "RIGHT"), iFieldLength);
                                }
                            } else if (sFieldType.equals("ZONE")) {
                                bPortionEncodedData = cvs.strToZone(sSendDataPart, iFieldLength, iFieldDecPnt);
                            } else if (sFieldType.equals("FILLER")) {
                                for (int ii = 0; ii < iFieldLength; ii++) {
                                    bPortionEncodedData[ii] = 0;
                                }
                            }
                            if (bPortionEncodedData == null) {
                                byte[] abyte0 = null;
                                return abyte0;
                            }
                        } else {
                            for (int ii = 0; ii < iFieldLength; ii++) {
                                if (sFieldType.equals("CHAR")) {
                                    bPortionEncodedData[ii] = 64;
                                    continue;
                                }
                                if (sFieldType.equals("NUMERIC")) {
                                    bPortionEncodedData[ii] = -16;
                                } else {
                                    bPortionEncodedData[ii] = 0;
                                }
                            }
                            if (sFieldType.equals("PACKED")) {
                                bPortionEncodedData[iFieldLength - 1] = 15;
                            }
                            if (iSendPartNo == 1 && iBufferFieldLength == 1) {
                                bPortionEncodedData = cvs.toFixed4ByteBINARY(String.valueOf(receiveDataLen), iFieldLength);
                            }
                        }
                        for (int iTempFieldLength = iHostDataLen; iTempFieldLength < iHostDataLen + bPortionEncodedData.length; iTempFieldLength++) {
                            receiveData[iTempFieldLength] = bPortionEncodedData[iTempFieldLength - iHostDataLen];
                        }
                        iHostDataLen += iFieldLength;
                        iBufferFieldLength++;
                    } while (true);

               
            }
           
        } catch (Exception ex){
            ex.printStackTrace();
        }
//        startWriteMsg();
        return receiveData;
    }

    private void startWriteMsg()
    {
        System.out.println("\rStart of Write       :---------------------------------\r");
        System.out.println("\rWrite (Text)");
        System.out.println(String.valueOf(String.valueOf((new StringBuffer("\r\nStart Of Record      : [")).append(strSendData).append("] End Of Record.\r\n"))));
        System.out.println("Write (Hex)\r");
        System.out.println(getReadableHexTable(Utils.printlnByte(receiveData, "HEX"), 0, "Start Of Record      : "));
        System.out.println(" End Of Record.\r");
    }

    public String decodeMonetary(byte byteNewReceiveData[])
    {
        String strdecodeMontary="";
        try{
            strdecodeMontary=decodeMonetaryAP(byteNewReceiveData);
        }catch(Exception exc){
            exc.printStackTrace();
        }
       return strdecodeMontary;
    }

    public String decodeMonetaryAP(byte byteNewReceiveData[])
    {
        String sTempBuffer = "";
        hashTable_3 = new Hashtable();
        newReceiveData = byteNewReceiveData;
        try
        {
            try
            {
                for(int ii = 0; ii < responseTemplate1.length; ii++)
                {
                    responseTemplate1[ii][5] = getResponseDataByPosition((String)responseTemplate1[ii][1], (String)responseTemplate1[ii][2], (String)responseTemplate1[ii][3], (String)responseTemplate1[ii][4]);
                }
                sTempBuffer = (String)responseTemplate1[0][5];
                isResponseStatus =(!sTempBuffer.equals(""))? sTempBuffer.trim().substring(0, 1).equals("/"):false;
                transCode = (String)responseTemplate1[1][5];
                sTempBuffer = (String)responseTemplate1[3][5];
                strEOMStatus = sTempBuffer.trim();
            }
            catch(Exception initHostTranslationException)
            {
               initHostTranslationException.printStackTrace();
            }
            if(hasResponseV2Format)
            {
                for(int ii = 0; ii < responseTemplate3.length; ii++)
                    responseTemplate3[ii][5] = getResponseDataByPosition((String)responseTemplate3[ii][1], (String)responseTemplate3[ii][2], (String)responseTemplate3[ii][3], (String)responseTemplate3[ii][4]);

                sTempBuffer = (String)responseTemplate3[0][5];
                isResponseStatus = (!sTempBuffer.equals(""))? sTempBuffer.trim().substring(0, 1).equals("/"):false;
                transCode = (String)responseTemplate3[1][5];
            }
            int iTotalReceiveFormat = 1;
            int iResponseLen = 0;
            String sResponseCode = "";
            int iTempTotalByteReceive = 0;
            int iCurrentBytePos = 0;
            if(strHeaderName.equals(""))
            {
                iTempTotalByteReceive = byteNewReceiveData.length - lenTemplate1_1;
                iCurrentBytePos = 323;
                hashTable_3.put("1", "ABCSHEADER");
            } else
            if(strHeaderName.equals("CIFHEADER"))
            {
                iTempTotalByteReceive = byteNewReceiveData.length - lenTemplate2_1;
                iCurrentBytePos = 249;
                hashTable_3.put("1", strHeaderName);
            } else
            {
                iTempTotalByteReceive = byteNewReceiveData.length - lenTemplate1_2;
                iCurrentBytePos = 323;
                hashTable_3.put("1", strHeaderName);
            }
            String sRMtype = "";
            if(strHeaderName.length() >= 2)
            {
                sRMtype = strHeaderName.substring(0, 2);
                if(sRMtype.equalsIgnoreCase("RM"))
                {
                    iTempTotalByteReceive = byteNewReceiveData.length - lenTemplate2_2;
                    iCurrentBytePos = lenTemplate2_2;
                    hashTable_3.put("1", "RMRES");
                    strEOMStatus = ((String)responseTemplate1[0][5]).trim();
                    if(strEOMStatus.length() == 0)
                        strEOMStatus = "*EOM";
                }
            }
            for(; iTempTotalByteReceive > 0; iTempTotalByteReceive -= iResponseLen)
            {
                iCurrentBytePos += iResponseLen;
                if(strHeaderName.equals("") || strHeaderName.equals("ABCSHEADER"))
                {
                    iResponseLen = Integer.parseInt(getResponseDataByPosition(String.valueOf(iCurrentBytePos), "PACKED", "2", "0")) + 2;
                    sResponseCode = getResponseDataByPosition(String.valueOf(iCurrentBytePos + 2), "CHAR", "2", "0");
                } else
                if(strHeaderName.equals("CIFHEADER"))
                {
                    if(isResponseStatus)
                        iResponseLen = 209;
                    else
                        iResponseLen = 38;
                    sResponseCode = utils.printlnByte(newReceiveData[iCurrentBytePos], "HEX").toUpperCase().trim();
                    if(sResponseCode.equals("F5"))
                        sResponseCode = String.valueOf(String.valueOf(sResponseCode)).concat("V2");
                } else
                {
                    String sType = "";
                    if(strHeaderName.length() >= 2)
                        sType = strHeaderName.substring(0, 2);
                    try
                    {
                        sResponseCode = Utils.printlnByte(newReceiveData[iCurrentBytePos], "HEX").toUpperCase().trim();
                        String sMinorResponseCode = Utils.printlnByte(newReceiveData[iCurrentBytePos + 1], "HEX").toUpperCase().trim();
                        if(sType.equalsIgnoreCase("RM"))
                        {
                            if(sResponseCode.equals("99"))
                            {
                                if(sMinorResponseCode.equals("F1") || sMinorResponseCode.equals("F3") || sMinorResponseCode.equals("F2"))
                                    sResponseCode = String.valueOf(sResponseCode) + String.valueOf(sMinorResponseCode);
                            } else
                            if(sResponseCode.equals("F2") || sResponseCode.equals("42"))
                                sResponseCode = String.valueOf(String.valueOf(sResponseCode)).concat("V2");
                            iTempTotalByteReceive = -1;
                        }
                    }
                    catch(Exception parseException)
                    {
                       parseException.printStackTrace();
                    }
                }
                if(hasResponseV2Format)
                    sResponseCode = getResponseCode(sResponseCode);
                iTotalReceiveFormat++;
                hashTable_3.put(String.valueOf(iTotalReceiveFormat), sResponseCode);
            }

            strEOMStatus = strEOMStatus.trim();
            if(strEOMStatus.length() == 0 && isEOMflag)
                strEOMStatus = "*EOM";
            String s1 = decode();
            return s1;
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        displayHostMsg();
        String s = "";
        return s;
    }

    private String getResponseDataByPosition(String sResponsePosition, String sDataType, String sFieldLength, String sFieldDecPnt)
    {
        try
        {
            if(newReceiveData != null && newReceiveData.length > 0)
            {
                byte byteReadDataPart[] = new byte[Integer.parseInt(sFieldLength)];
                try
                {
                    for(int ii = 0; ii < Integer.parseInt(sFieldLength); ii++)
                        byteReadDataPart[ii] = newReceiveData[Integer.parseInt(sResponsePosition) + ii];

                }
                catch(ArrayIndexOutOfBoundsException outofbound)
                {
                    outofbound.printStackTrace();
                }
                if(sDataType.equals("CHAR"))
                {
                    String s = cvs.byteToStr(cvs.Ebcdic2Ascii(byteReadDataPart));
                    return s;
                }
                if(sDataType.equals("PACKED"))
                {
                    MessageConversion _tmp = this;
                    String s1 = Conversion.packedToStr(byteReadDataPart, Integer.parseInt(sFieldLength), Integer.parseInt(sFieldDecPnt));
                    return s1;
                }
                if(sDataType.equals("NUMERIC"))
                {
                    String s2 = cvs.byteToStr(cvs.Ebcdic2Ascii(byteReadDataPart));
                    return s2;
                }
            }
        }
        catch(Exception e)
        {
         e.printStackTrace();
        }
        return "";
    }

    public String decodeNonMonentary(byte byteNewReceiveData[])
    {
        String strDecodeNonMonentary="";
        try{
            String sTempBuffer = "";
            hashTable_3 = new Hashtable();
            hashTable_3.put("1", "MBASEHEAD");
            newReceiveData = byteNewReceiveData;
            for(int ii = 0; ii < responseTemplate4.length; ii++)
            {
                responseTemplate4[ii][5] = getResponseDataByPosition((String)responseTemplate4[ii][1], (String)responseTemplate4[ii][2], (String)responseTemplate4[ii][3], (String)responseTemplate4[ii][4]);

            }

            sTempBuffer = (String)responseTemplate4[0][5];
            if(sTempBuffer != null && sTempBuffer.length() >= 1)
                isResponseStatus = sTempBuffer.trim().substring(0, 1).equals("/");
            else
                isResponseStatus = false;
            transCode = (String)responseTemplate4[1][5];
            sTempBuffer = (String)responseTemplate4[2][5];
            isAppResponseStatus = sTempBuffer.equals("AA");
            sTempBuffer = (String)responseTemplate4[3][5];
            if(!sTempBuffer.equals("") && sTempBuffer != null)
            {
                totalNoRecord = Integer.parseInt(sTempBuffer) <= 0 ? 0 : Integer.parseInt(sTempBuffer);
                intTotalRecordReceived = totalNoRecord;
            }
            strMoreRecInd = (String)responseTemplate4[4][5];
            strLastErrCode = (String)responseTemplate4[5][5];
            strLastErrorMsg = (String)responseTemplate4[6][5];
            formatID = String.valueOf(String.valueOf(transCode)).concat("R");
            if(!isAppResponseStatus)
                strOverridErrorCode = (String)responseTemplate4[7][5];
            if(!formatID.trim().equals("R"))
                hashTable_3.put("2", formatID);
            strDecodeNonMonentary=decode();
        }catch(Exception exp){}
        
        return strDecodeNonMonentary;
    }

    private String decode()
    {
        String sFieldRec = "";
        String sFieldType = "";
        int iCurrentReadBytePosition = 0;
        int iFieldLength = 0;
        int iFieldDecPnt = 0;
        int iBufferFieldLength = 0;
        decodedValue = "";
        if(newReceiveData != null && newReceiveData.length > 0)
        {
            for(int iReadPartNo = 1; iReadPartNo <= hashTable_3.size(); iReadPartNo++)
            {
                hashTable_1 = null;
                hashTable_1 = MessageFormatFile.getFieldInformation((String)hashTable_3.get(String.valueOf(iReadPartNo)));
                intUnrepeatedKey = MessageFormatFile.getUnRepeatedKey();
                int iFieldSize = 0;
                iFieldSize = hashTable_1.size();
                if(iReadPartNo != 1 && totalNoRecord > 1)
                    putByteOfField(totalNoRecord);
                if(iReadPartNo > 2)
                {
                    decodedValue = decodedValue.substring(0, decodedValue.length() - 1);
                    decodedValue = String.valueOf(decodedValue) + String.valueOf('$');
                }
                iBufferFieldLength = 1;
                for(iBufferFieldLength = 1; iBufferFieldLength < hashTable_1.size(); iBufferFieldLength++)
                {
                    sFieldRec = (String)hashTable_1.get("FIELD".concat(String.valueOf(String.valueOf(String.valueOf(iBufferFieldLength)))));
                    sFieldType = Utils.getToken(sFieldRec, 1, ',');
                    if(!Utils.getToken(sFieldRec, 2, ',').equals(""))
                        iFieldLength = Integer.parseInt(Utils.getToken(sFieldRec, 2, ','));
                    if(!Utils.getToken(sFieldRec, 3, ',').equals(""))
                        iFieldDecPnt = Integer.parseInt(Utils.getToken(sFieldRec, 3, ','));
                    if(iCurrentReadBytePosition <= newReceiveData.length)
                    {
                        byte byteReadDataPart[] = new byte[iFieldLength];
                        for(int ii = 0; ii < iFieldLength; ii++)
                            if(iCurrentReadBytePosition + ii < newReceiveData.length)
                                byteReadDataPart[ii] = newReceiveData[iCurrentReadBytePosition + ii];
                            else
                                byteReadDataPart[ii] = 0;

                        String sPortionDecocedReadData = "";
                        if(sFieldType.equals("CHAR"))
                            sPortionDecocedReadData = cvs.byteToStr(cvs.Ebcdic2Ascii(byteReadDataPart));
                        else
                        if(sFieldType.equals("VIET"))
                            sPortionDecocedReadData = cvs.EbcdicViet2Ascii(byteReadDataPart);
                        else
                        if(sFieldType.equals("PACKED"))
                        {
                            MessageConversion _tmp = this;
                            sPortionDecocedReadData = Conversion.packedToStr(byteReadDataPart, iFieldLength, iFieldDecPnt);
                        } else
                        if(sFieldType.equals("HEX"))
                            sPortionDecocedReadData = Utils.printlnByte(byteReadDataPart, "HEX").toUpperCase().trim();
                        else
                        if(sFieldType.equals("BINARY"))
                        {
                            StringBuffer sbBin = new StringBuffer();
                            for(int ii = 0; ii < iFieldLength; ii++)
                                sbBin.append(" ");

                            sPortionDecocedReadData = sbBin.toString();
                        } else
                        if(sFieldType.equals("NUMERIC"))
                            sPortionDecocedReadData = cvs.byteToStr(cvs.Ebcdic2Ascii(byteReadDataPart));
                        else
                        if(sFieldType.equals("ZONE"))
                            sPortionDecocedReadData = cvs.ZoneToStr(byteReadDataPart, iFieldLength, iFieldDecPnt);
                        else
                        if(sFieldType.equals("FILLER"))
                        {
                            StringBuffer sbFiller = new StringBuffer();
                            for(int ii = 0; ii < iFieldLength; ii++)
                                sbFiller.append(" ");

                            sPortionDecocedReadData = sbFiller.toString();
                        } else
                        if(sFieldType.equals("DATA"))
                        {
                            String resCd = Utils.getToken(decodedValue, 2, charSpecial_1).trim();
                            if(resCd.equals("IA"))
                            {
                                sPortionDecocedReadData = dataToStr(byteReadDataPart, iFieldLength, Utils.getToken(decodedValue, 9, charSpecial_1));
                            } else
                            {
                                StringBuffer sbIA = new StringBuffer();
                                for(int ii = 0; ii < iFieldLength; ii++)
                                    sbIA.append(" ");

                                sPortionDecocedReadData = sbIA.toString();
                            }
                        }
                        
                        if(iReadPartNo == 1 && iBufferFieldLength == 1)
                        {
                            if(_$1856.equals("F5"))
                                formatID = String.valueOf(String.valueOf(_$1856)).concat("V2");
                            sPortionDecocedReadData = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(formatID)))).append(charSpecial_1).append(String.valueOf(newReceiveData.length - 4))));
                        }
                        if(TRIM_ON)
                            sPortionDecocedReadData = sPortionDecocedReadData.trim();
                        if(iReadPartNo == 1)
                        {
                            strResponseHeader.append(sPortionDecocedReadData).append(charSpecial_1);
                            decodedValue = "";
                        } else
                        {
                            decodedValue = String.valueOf(decodedValue) + String.valueOf(String.valueOf(sPortionDecocedReadData) + String.valueOf(charSpecial_1));
                        }
                        iCurrentReadBytePosition += iFieldLength;
                    } else
                    {
                        decodedValue = String.valueOf(decodedValue) + String.valueOf(charSpecial_1);
                    }
                }

            }

//            displayHostMsg();
        } else
        {
          System.out.println("[HostMsg::Decode] Total receive bytes is zero.");
        }
        return decodedValue;
    }

    private void putByteOfField(int iTotalNoOfRec)
    {
        Hashtable htResponseTranCode = new Hashtable();
        htResponseTranCode = MessageFormatFile.getFieldInformation(formatID);
        int iUnRepeatedKey = MessageFormatFile.getUnRepeatedKey();
        for(iTotalNoOfRec--; iTotalNoOfRec > 0; iTotalNoOfRec--)
        {
            int ii = 1 + iUnRepeatedKey;
            for(int jj = hashTable_1.size(); ii <= htResponseTranCode.size() - 1; jj++)
            {
                String sColumnValue = (String)htResponseTranCode.get("FIELD".concat(String.valueOf(String.valueOf(String.valueOf(ii)))));
                hashTable_1.put("FIELD".concat(String.valueOf(String.valueOf(String.valueOf(jj)))), sColumnValue);
                ii++;
            }

        }

    }

    private void displayHostMsg()
    {
        String sHostResponseCodes = "";
        System.out.println("\n\nStart of Read (Second Level)       :---------------------------------\r\n");
        System.out.println();
        System.out.println("\r\nTotal Length Received: ["+String.valueOf(newReceiveData.length)+"]\r\n");
        System.out.println("Transaction Code     : ["+transCode+"]\r\n");
        for(int iReadPartNo = 2; iReadPartNo <= hashTable_3.size(); iReadPartNo++)
            if(sHostResponseCodes.equals(""))
                sHostResponseCodes = (String)hashTable_3.get(String.valueOf(iReadPartNo));
            else
                sHostResponseCodes = String.valueOf(sHostResponseCodes) + String.valueOf(",".concat(String.valueOf(String.valueOf((String)hashTable_3.get(String.valueOf(iReadPartNo))))));

        System.out.println("Host Response Code   : ["+sHostResponseCodes+"]\r\n");
        System.out.println("Read  (Text)");
        System.out.println(String.valueOf(String.valueOf((new StringBuffer("\r\nStart Of Header      : [")).append(getResponseHeader()).append("] End Of Header."))));
        System.out.println(String.valueOf(String.valueOf((new StringBuffer("\r\nStart Of Record      : [")).append(decodedValue).append("] End Of Record.\r\n"))));
    }

    public static void printReadableHexTable(String data, int Spacing)
    {
        if(data == null)
            return;
        if(data.length() == 0)
            return;
        int ONE_BLOCK = 4;
        int ONE_LINE = 30;
        int iPrinted = 0;
        int iRow = 1;
        for(int i = 0; i < data.length(); i++)
        {
            if(i == 0)
            {
                for(int s = 0; s < Spacing; s++)
                    System.out.print(" ");

            }
            if(i % ONE_BLOCK == 0 && i != 0)
            {
                System.out.print(" ");
                iPrinted++;
            }
            if(iPrinted % ONE_LINE == 0 && iPrinted != 0)
            {
                System.out.println();
                iPrinted = 0;
                for(int s = 0; s < Spacing; s++)
                    System.out.print(" ");

            }
            System.out.print(data.charAt(i));
        }

    }

    public static String getReadableHexTable(String data, int Spacing, String title)
    {
        Spacing++;
        if(data == null)
            return null;
        if(data.length() == 0)
        {
            System.out.println("E HostMsg.getReadableHexTable:: No record!");
            return null;
        }
        if(title == null)
            title = "";
        StringBuffer sb = new StringBuffer();
        int ONE_BLOCK = 4;
        int ONE_LINE = 30;
        int iPrinted = 0;
        int iRow = 1;
        for(int i = 0; i < data.length(); i++)
        {
            if(i == 0)
            {
                if(Spacing > 1)
                {
                    for(int s = 0; s < Spacing - 1; s++)
                        sb.append(" ");

                }
                sb.append(title).append("[");
                Spacing += title.length();
            }
            if(i % ONE_BLOCK == 0 && i != 0)
            {
                sb.append(" ");
                iPrinted++;
            }
            if(iPrinted % ONE_LINE == 0 && iPrinted != 0)
            {
                sb.append("\n");
                iPrinted = 0;
                for(int s = 0; s < Spacing; s++)
                    sb.append(" ");

            }
            sb.append(data.charAt(i));
        }

        sb.append("]");
        return sb.toString();
    }

    
    public String dataToStr(byte data[], int fieldLength, String dataFormatID)
    {
        if(fieldLength == 0)
            return "";
        if(data == null || dataFormatID == null)
        {
            StringBuffer sb = new StringBuffer();
            for(int j = 0; j < fieldLength; j++)
                sb.append(" ");

            return sb.toString();
        }
        dataFormatID = dataFormatID.trim();
        StringBuffer result = new StringBuffer();
        Hashtable htFieldInfo = MessageFormatFile.getFieldInformation(dataFormatID);
        String sFieldID = "";
        String sFieldType = "";
        int iFieldLength = 0;
        int iFieldDecPnt = 0;
        int iCurrentReadBytePosition = 0;
        for(int i = 1; i < htFieldInfo.size(); i++)
        {
            sFieldID = "FIELD".concat(String.valueOf(String.valueOf(i)));
            String sFieldRec = "";
            iFieldLength = 0;
            if(!htFieldInfo.containsKey(sFieldID))
                continue;
            sFieldRec = (String)htFieldInfo.get(sFieldID);
            if(!sFieldRec.equals(""))
                sFieldRec = sFieldRec.trim();
            sFieldType = Utils.getToken(sFieldRec, 1, ',');
            if(!Utils.getToken(sFieldRec, 2, ',').equals(""))
                iFieldLength = Integer.parseInt(Utils.getToken(sFieldRec, 2, ','));
            if(!Utils.getToken(sFieldRec, 3, ',').equals(""))
                iFieldDecPnt = Integer.parseInt(Utils.getToken(sFieldRec, 3, ','));
            if(iCurrentReadBytePosition > data.length)
                continue;
            byte baPart[] = new byte[iFieldLength];
            for(int ii = 0; ii < iFieldLength; ii++)
                if(iCurrentReadBytePosition + ii < data.length)
                    baPart[ii] = data[iCurrentReadBytePosition + ii];
                else
                    baPart[ii] = 0;

            String sPortionDecocedReadData = "";
            if(sFieldType.equals("CHAR"))
                sPortionDecocedReadData = cvs.byteToStr(cvs.Ebcdic2Ascii(baPart));
            else
            if(sFieldType.equals("VIET"))
                sPortionDecocedReadData = cvs.EbcdicViet2Ascii(baPart);
            else
            if(sFieldType.equals("PACKED"))
            {
                MessageConversion _tmp = this;
                sPortionDecocedReadData = Conversion.packedToStr(baPart, iFieldLength, iFieldDecPnt);
            } else
            if(sFieldType.equals("HEX"))
                sPortionDecocedReadData = Utils.printlnByte(baPart, "HEX").toUpperCase().trim();
            else
            if(sFieldType.equals("BINARY"))
            {
                StringBuffer sbBin = new StringBuffer();
                for(int ii = 0; ii < iFieldLength; ii++)
                    sbBin.append(" ");

                sPortionDecocedReadData = sbBin.toString();
            } else
            if(sFieldType.equals("NUMERIC"))
                sPortionDecocedReadData = cvs.byteToStr(cvs.Ebcdic2Ascii(baPart));
            else
            if(sFieldType.equals("ZONE"))
                sPortionDecocedReadData = cvs.ZoneToStr(baPart, iFieldLength, iFieldDecPnt);
            else
            if(sFieldType.equals("FILLER"))
            {
                StringBuffer sbFiller = new StringBuffer();
                for(int ii = 0; ii < iFieldLength; ii++)
                    sbFiller.append(" ");

                sPortionDecocedReadData = sbFiller.toString();
            }
            result.append(sPortionDecocedReadData.trim()).append(charSpecial_1);
            iCurrentReadBytePosition += iFieldLength;
        }

        int idataLength = result.length();
        if(idataLength < fieldLength)
        {
            int iRemaining = fieldLength - idataLength;
            for(int f = 0; f < iRemaining; f++)
                result.append(" ");

        }
        return result.toString();
    }

    public void isResponseV2Format(boolean yesOrNo)
    {
        hasResponseV2Format = yesOrNo;
    }

    public String getResponseHeader()
    {
        if(strResponseHeader == null)
        {
            System.out.println("E HostMsg.getResponseHeader(): Error: Null object decode header.");
            return "";
        } else
        {
            return strResponseHeader.toString();
        }
    }

    private String getResponseCode(String resCode)
    {
        String responseCode = resCode;
        if(responseCode != null && hasResponseV2Format && hashTable_4 != null && hashTable_4.containsKey(responseCode))
            responseCode = String.valueOf(responseCode) + String.valueOf(strExtension);
        return responseCode;
    }

    public void setV2ResponseCodes(String codes)
    {
        if(codes != null)
        {
            v2ResponseCode = codes;
            if(v2ResponseCode.length() > 0)
            {
                hashTable_4 = new Hashtable();
                StringTokenizer st = new StringTokenizer(v2ResponseCode, ",");
                int i = 0;
                if(st != null)
                {
                    String code;
                    for(; st.hasMoreTokens(); hashTable_4.put(code, code))
                    {
                        i++;
                        code = st.nextToken().toString();
                    }

                    if(i > 0)
                        hasResponseV2Format = true;
                }
            }
        }
    }



    private void contructResponseTemplate()
    {
        charSpecial_1 = "|".concat(" ").toCharArray()[0];
        switch(0)
        {
        case 1: // '\001'
            responseTemplate1 = (new Object[][] {
                new Object[] {
                    "MiddleWareResponseStatus", "98", "CHAR", "8", "0", ""
                }, new Object[] {
                    "TranCode", "395", "CHAR", "4", "0", ""
                }, new Object[] {
                    "ApplicationResponseStatus", "312", "CHAR", "2", "0", ""
                }, new Object[] {
                    "MoreRecordIndicator", "452", "CHAR", "1", "0", ""
                }
            });
            responseTemplate2 = (new Object[][] {
                new Object[] {
                    "MiddleWareResponseStatus", "98", "CHAR", "8", "0", ""
                }, new Object[] {
                    "TranCode", "395", "CHAR", "4", "0", ""
                }, new Object[] {
                    "ApplicationResponseStatus", "691", "CHAR", "2", "0", ""
                }
            });
            break;

            case 0: // '\0'
            responseTemplate1 = (new Object[][] {
                new Object[] {
                    "MiddleWareResponseStatus", "87", "CHAR", "8", "0", ""
                }, new Object[] {
                    "TranCode", "217", "NUMERIC", "4", "0", ""
                }, new Object[] {
                    "ApplicationResponseStatus", "246", "CHAR", "2", "0", ""
                }, new Object[] {
                    "EOMStatus", "319", "CHAR", "4", "0", ""
                }
            });
            responseTemplate2 = (new Object[][] {
                new Object[] {
                    "MiddleWareResponseStatus", "87", "CHAR", "8", "0", ""
                }, new Object[] {
                    "TranCode", "217", "NUMERIC", "4", "0", ""
                }, new Object[] {
                    "ApplicationResponseStatus", "246", "CHAR", "2", "0", ""
                }, new Object[] {
                    "EOMStatus", "319", "CHAR", "4", "0", ""
                }
            });
            break;
        }
        responseTemplate3 = (new Object[][] {
            new Object[] {
                "MiddleWareResponseStatus", "87", "CHAR", "8", "0", ""
            }, new Object[] {
                "TranCode", "217", "NUMERIC", "4", "0", ""
            }
        });
        responseTemplate4 = (new Object[][] {
            new Object[] {
                "MiddleWareResponseStatus", "87", "CHAR", "8", "0", ""
            }, new Object[] {
                "TranCode", "335", "PACKED", "3", "0", ""
            }, new Object[] {
                "MBASE_ApplicationResponseStatus", "249", "CHAR", "2", "0", ""
            }, new Object[] {
                "MBASE_TotalNoOfRecord", "340", "PACKED", "2", "0", ""
            }, new Object[] {
                "MBASE_MoreRecordIndicator", "342", "CHAR", "1", "0", ""
            }, new Object[] {
                "MBASE_ResponseErrorCode1", "344", "CHAR", "7", "0", ""
            }, new Object[] {
                "MBASE_ResponseErrorMsg1", "351", "CHAR", "50", "0", ""
            }, new Object[] {
                "MBASE_OverrideErrorCode", "589", "CHAR", "7", "0", ""
            }
        });
    }

    public String decodeMonetaryChina(byte byteNewReceiveData[])
    {
        String sTempBuffer = "";
        hashTable_3 = new Hashtable();
        newReceiveData = byteNewReceiveData;
        try
        {
            try
            {
                for(int ii = 0; ii < responseTemplate1.length; ii++)
                {
                    responseTemplate1[ii][5] = getResponseDataByPosition((String)responseTemplate1[ii][1], (String)responseTemplate1[ii][2], (String)responseTemplate1[ii][3], (String)responseTemplate1[ii][4]);
                }

                sTempBuffer = (String)responseTemplate1[0][5];
                isResponseStatus = sTempBuffer.trim().substring(0, 1).equals("/");
                transCode = (String)responseTemplate1[1][5];
                sTempBuffer = (String)responseTemplate1[3][5];
                strEOMStatus = sTempBuffer.trim();
            }
            catch(Exception initHostTranslationException)
            {
                initHostTranslationException.printStackTrace();
            }
            if(hasResponseV2Format)
            {
                for(int ii = 0; ii < responseTemplate3.length; ii++)
                    responseTemplate3[ii][5] = getResponseDataByPosition((String)responseTemplate3[ii][1], (String)responseTemplate3[ii][2], (String)responseTemplate3[ii][3], (String)responseTemplate3[ii][4]);

                sTempBuffer = (String)responseTemplate3[0][5];
                isResponseStatus = sTempBuffer.trim().substring(0, 1).equals("/");
                transCode = (String)responseTemplate3[1][5];
            }
            int iTotalReceiveFormat = 1;
            int iResponseLen = 0;
            String sResponseCode = "";
            int iTempTotalByteReceive = 0;
            int iCurrentBytePos = 0;
            if(strHeaderName.equals(""))
            {
                iTempTotalByteReceive = byteNewReceiveData.length - lenTemplate1_1;
                iCurrentBytePos = lenTemplate1_1;
                hashTable_3.put("1", "ABCSHEADER");
            } else
            if(strHeaderName.equals("CIFHEADER"))
            {
                iTempTotalByteReceive = byteNewReceiveData.length - lenTemplate2_1;
                iCurrentBytePos = lenTemplate2_1;
                hashTable_3.put("1", strHeaderName);
            } else
            {
                iTempTotalByteReceive = byteNewReceiveData.length - lenTemplate1_2;
                iCurrentBytePos = lenTemplate1_2;
                hashTable_3.put("1", strHeaderName);
            }
            String sRMtype = "";
            if(strHeaderName.length() >= 2)
            {
                sRMtype = strHeaderName.substring(0, 2);
                if(sRMtype.equalsIgnoreCase("RM"))
                {
                    iTempTotalByteReceive = byteNewReceiveData.length - lenTemplate2_2;
                    iCurrentBytePos = lenTemplate2_2;
                    hashTable_3.put("1", "RMRES");
                    strEOMStatus = ((String)responseTemplate1[0][5]).trim();
                    if(strEOMStatus.length() == 0)
                        strEOMStatus = "*EOM";
                }
            }
            String sRcvCode = MessageFormatFile.getRcvCode(strBodyName);
            System.out.println(String.valueOf(String.valueOf((new StringBuffer("Response =[")).append(sRcvCode).append("]"))));
            hashTable_3.put("2", sRcvCode);
            strEOMStatus = strEOMStatus.trim();
            if(strEOMStatus.length() == 0 && isEOMflag)
                strEOMStatus = "*EOM";
            String s1 = decode();
            return s1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        displayHostMsg();
        String s = "";
        return s;
    }
    


}
