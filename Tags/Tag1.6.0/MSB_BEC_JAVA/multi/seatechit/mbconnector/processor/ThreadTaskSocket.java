//package multi.seatechit.mbconnector.processor;
//
//import java.util.Date;
//import multi.seatechit.mbconnector.conversion.MessageConversion;
//import multi.seatechit.mbconnector.utils.General;
//import multi.seatechit.mbconnector.utils.Global;
//
///**
// *
// * @author 
// */
//class ThreadTaskSocket extends Thread implements Global {
//    
//    public ThreadTaskSocket(PortList thePort,String message,General general) {
//      port = thePort;
//      settings = general;
//      msgs = message;
//      msgConversion = new MessageConversion();
//    }
//    
//    public void run() {
//      long start=new Date().getTime();
//      int msgCount = 0;
//      do{
//        newSettings();
//        boolean b = false;
//        try{
//          while(needOpenTcp)
//          {
//            try
//            {
//              b = tcpSocket.connect(getPort().getIp(), getPort().getPort());
////                System.out.println("tcpSocket connect to ip:"+getPort().getIp()+" - port: "+getPort().getPort());
//              needOpenTcp = false;
////                if(settings.DEBUGMSG)
////                  System.out.println("TCPSocket port".concat(getPort().getPort()).concat(" connect state = ").concat(String.valueOf((b)?"connected":"not connected")));
//            }
//            catch(Exception exception) {
//              exception.printStackTrace();
//            }
//           }
//        }
//        catch(Exception exception1) { }
//        // blocks until job
////        String strRequest = "696|*LINX|10.1.99.164||||213||0200|*DSP|MBSD|*LINX||||01|||BBMBSDDINQFNC|||||10|10|PC011004|hungpd|25520|N||||PC011004|1||*END||BTS|RBS||hungpd|27|011||||25520|I|R|10|N|F|||||||||||||||||02007010003674|S|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
//        try{
//          String strRequest = msgs;
//          if(msgCount > 0)
//            strRequest = "";
//          if(strRequest == null || "".equals(strRequest)){
//            noMore = true;
//            continue;
//          }
//          String returnMessage[] = new String[6];
//          String strResponse[] = new String[4];
//          if (strRequest.indexOf("ABCS")<0) {
//             writeNonMonentary(strRequest);
//          }else{
//             writeMonetary(strRequest);
//          }
//          String desc = "Port ".concat(port.getPort()).concat(" process message "+msgCount+":");
//          if(strRequest.indexOf("ABCS")<0) {
//            System.out.println(desc+readNonMonentary());
//          }else{
//            System.out.println(desc+readMonentary());
//          }
//          msgCount++;
//          close();
//        }catch(Exception ex){
//          ex.printStackTrace();
//        }
//      }while(!noMore);
//      close();
//      System.out.print("Tong thoi gian 1000 message: "+(new Date().getTime()- start)+"ms");
//    }
//
//    public synchronized boolean writeMonetary(String sNewWriteData)
//    {
//      System.out.println("Message: "+sNewWriteData);
//       long start=new Date().getTime();
////        for (int i = 0; i < 1000; i++) {
//            byteData = msgConversion.encodeMonetary(sNewWriteData);
////        }
//       System.out.println("Thoi gian encode: "+(new Date().getTime()-start)+"ms");
//      return sendMsg();
//    }
//
//    public synchronized boolean writeNonMonentary(String sNewWriteData)
//    {
//      System.out.println("Message: "+sNewWriteData);
//      long start=new Date().getTime();
////      for (int i = 0; i < 1000; i++) {
//            byteData = msgConversion.encodeNonMonetary(sNewWriteData);
////        }
//      long end= new Date().getTime();
//      System.out.println("Thoi gian encode: "+(end-start)+"ms");
//      return sendMsg();
//    }
//
//    public synchronized boolean sendMsg()
//    {
//      msgConversion.resetTotalNoRecord();
//      if(byteData != null || byteData.length > 0)
//      {
//        return tcpSocket.send(byteData);
//      } else
//      {
//        return false;
//      }
//    }
//
//    public synchronized String readNonMonentary()
//    {
//      if(byteData != null || byteData.length > 0)
//      {
//
//        tcpSocket.receive();
//        if(tcpSocket.getByteReceiveData() == null || tcpSocket.getByteReceiveData().toString().trim().equals(""))
//            return "";
////            return "receive Ok!";
//             long start=new Date().getTime();
//             String result="";
////             for (int i = 0; i < 1000; i++) {
//              result= msgConversion.decodeNonMonentary(tcpSocket.getByteReceiveData());
////             }
//             
//             long end= new Date().getTime();
//             System.out.println("Thoi gian decode: "+(end-start)+"ms");
//            return result;
//      } else
//      {
//        return "";
//      }
//    }
//
//    public synchronized String readMonentary()
//    {
//      if(byteData != null || byteData.length > 0)
//      {
//        tcpSocket.receive();
//        if(tcpSocket.getByteReceiveData() == null || tcpSocket.getByteReceiveData().toString().trim().equals(""))
//          return "";
////            return "receive Ok!";
//          return msgConversion.decodeMonetary(tcpSocket.getByteReceiveData());
//
//      } else
//      {
//        return "";
//      }
//    }
//    
//    public void setTrimOption(boolean value)
//    {
//      if(msgConversion != null)
//        msgConversion.setTrimOption(value);
//    }
//
//    public int getUnrepeatedKey()
//    {
//      if(msgConversion == null)
//        return -1;
//      else
//        return msgConversion.getUnrepeatedKey();
//    }
//
//    public void setEOMflag(boolean status)
//    {
//      msgConversion.setEOMflag(status);
//    }
//
//    public boolean getEOMflag()
//    {
//      return msgConversion.getEOMflag();
//    }
//
//    private boolean msgConversionResponseV2Format(boolean flag)
//    {
//      if(msgConversion != null)
//      {
//        msgConversion.isResponseV2Format(flag);
//        return true;
//      } else
//      {
//        System.out.println("E ThreadTask.isResponseV2Format:Error: Flag not set. You need to construct the ThreadTask first.");
//        return false;
//      }
//    }
//
//    public boolean setExpectingResponseCodes(String responseCodes, String extension)
//    {
//      msgConversion.setReponseCodeExtension(extension);
//      return setExpectingResponseCodes(responseCodes);
//    }
//
//    public boolean setExpectingResponseCodes(String responseCodes)
//    {
//      if(msgConversion != null && responseCodes != null)
//      {
//        responseCodes = responseCodes.trim();
//        if(responseCodes.length() > 0)
//            msgConversionResponseV2Format(true);
//        else
//            msgConversionResponseV2Format(false);
//        msgConversion.setV2ResponseCodes(responseCodes);
//        return true;
//      } else
//      {
//        System.out.println("E ThreadTask.setV2ResponseCode:Error: Flag not set. You need to construct the ThreadTask first.");
//        return false;
//      }
//    }
//
//    public String getResponseHeader()
//    {
//      if(msgConversion != null)
//      {
//        return msgConversion.getResponseHeader();
//      } else
//      {
//        System.out.println("E ThreadTask.getResponseHeader:Error: Flag not set. You need to construct the ThreadTask first.");
//        return "";
//      }
//    }
//
//    public boolean getDebugMode()
//    {
//      if(msgConversion != null)
//      {
//        return General.getDebugMode();
//      } else
//      {
//        System.out.println("E ThreadTask.getDebugMode:Error: You need to initialize the ThreadTask.");
//        return false;
//      }
//    }
//
//    public void setDebugMode(boolean mode)
//    {
//      if(msgConversion != null)
//      {
//        General.setDebugMode(mode);
//      } else
//      {
//        System.out.println("E ThreadTask.setDebugMode:Error: You need to initialize the ThreadTask.");
//      }
//    }
//
//    public void setTcpSocket(TCPSocket tcpSocket) {
//      this.tcpSocket = tcpSocket;
//    }
//
//    public TCPSocket getTCPSocket(){
//      return tcpSocket;
//    }
//
//    public boolean getTimeOut()
//    {
//      return tcpSocket.getTimeOut();
//    }
//
//    public void setTimeOut(int newTimeOut)
//    {
//      timeOut = newTimeOut;
//    }
//
//    public void setHeaderName(String newHeaderName)
//    {
//      msgConversion.setHeaderName(newHeaderName);
//    }
//
//    public void setBodyName(String newBodyName)
//    {
//      msgConversion.setBodyName(newBodyName);
//    }
//
//    public boolean getOnline()
//    {
//      return tcpSocket.getOnline();
//    }
//
//    public String getLastErrorCode()
//    {
//      return msgConversion.getLastErrorCode();
//    }
//
//    public String getOverrideErrorCode()
//    {
//      return msgConversion.getOverrideErrorCode();
//    }
//
//    public String getLastErrorMsg()
//    {
//      return msgConversion.getLastErrorMsg();
//    }
//
//    public int getTotalRecordReceived()
//    {
//      return msgConversion.getTotalRecordReceived();
//    }
//
//    public String getMoreRecordInd()
//    {
//      return msgConversion.getMoreRecInd();
//    }
//
//    public boolean getMiddleWareResponseStatus()
//    {
//      return msgConversion.getMiddleWareResponseStatus();
//    }
//
//    public boolean getApplicationResponseStatus()
//    {
//      return msgConversion.getApplicationResponseStatus();
//    }
//
//    public String getEOMStatus()
//    {
//      return msgConversion.getEOMStatus();
//    }
//
//    public String getBodyName()
//    {
//      return msgConversion.getBodyName();
//    }
//
//    public String getHeaderName()
//    {
//      return msgConversion.getHeaderName();
//    }
//
//    public PortList getPort()
//    {
//      return port;
//    }
//
//    public boolean open()
//    {
//      if(settings.getWaitForConnection())
//      {
//          return tcpSocket.connect(getPort().getIp(), getPort().getPort());
//      } else
//      {
//          needOpenTcp = true;
//          return false;
//      }
//    }
//    
//    public void newSettings(){
//      noMore = false;
//      byteData = null;
//      needOpenTcp = true;
//      tcpSocket = null;
//      tcpSocket = new TCPSocket();
//      tcpSocket.setGeneral(settings);
//    }
//
//    public boolean close()
//    {
//      System.out.println("TCP Connection is close now");
//      if(tcpSocket != null){
////            tcpSocket = null;
////            return true;
//          return tcpSocket.close();
//      }
//      else
//          return true;
//    }
//
//
////    public  void setLineBroken()
////    {
////        isLineBroken = true;
////    }
////
////    public boolean getReadDone()
////    {
////        return isReadDone;
////    }
////
////    public void setReadDone(boolean ReadDone)
////    {
////        isReadDone = ReadDone;
////    }
//
//
//    private String msgs;
//    private PortList port;
//    private boolean noMore;
//    protected General settings;
//    private MessageConversion msgConversion;
//    private TCPSocket tcpSocket;
//    private byte byteData[];
//    protected boolean needOpenTcp;
//    private int timeOut;
//}
