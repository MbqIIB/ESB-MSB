///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package multi.seatechit.mbconnector.processor;
//
//
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.PrintStream;
//import java.net.ConnectException;
//import java.net.InetAddress;
//import java.net.Socket;
//import java.util.Date;
//import seatechit.mbconnector.utils.General;
//import seatechit.mbconnector.utils.Global;
//import seatechit.mbconnector.utils.Utils;
//
//
///**
// *
// * @author Administrator
// */
//public class TCPSocket implements Global{
//    private ConnectTCP  connTCPSocket;
//    private int iDataMaxByte = 4096;
//    protected int iTimeOut = 60;
//    private int intTimeOut = 0;
//    private Socket socket;
//    private DataInputStream dataInputStream;
//    private OutputStream outPutStream;
//    private int state;
//    protected boolean bTimeout;
//    protected boolean bConnected;
//    private General general;
//    protected String receiveData;
//    public int lengthReceByteData;
//    public byte byteTestSocket[][];
//    public int iHead;
//    public int iTail;
//    private byte byteReceiveData[];
//    private ReadThread readThread;
//
//
//    public class ReadThread extends Thread implements Global
//    {
//      private boolean isLineBroken;
//      private boolean isReadDone;
//  //    private byte _$14203[];
//      private byte msgBytes[];
//      private byte tmpByte[];
//      public  void setLineBroken()
//      {
//          isLineBroken = true;
//      }
//
//      public boolean getOnline()
//      {
//          return !isLineBroken;
//      }
//
//      public boolean getReadDone()
//      {
//          return isReadDone;
//      }
//
//      public void setReadDone(boolean ReadDone)
//      {
//          isReadDone = ReadDone;
//      }
//
//      public void terminate()
//      {
//          try
//          {
//              Thread.yield();
//              isLineBroken = true;
//          }
//          catch(Exception ie)
//          {
//              ie.printStackTrace();
//          }
//      }
//
//      public void run()
//      {
//          long start=new Date().getTime();
//          int iLen = -1;
//          int iMSG_LENGTH = 4;
//          switch(0)
//          {
//          case 1: // '\001'
//              iMSG_LENGTH = 9;
//              break;
//
//          case 0: // '\0'
//              iMSG_LENGTH = 4;
//              break;
//          }
//          int cnt = 0;
//          int nBytes = 0;
//          int WAIT = 200;
//          String sMsgLen = "";
//          byte baMsgLength[] = null;
//          msgBytes = new byte[iMSG_LENGTH];
//          do
//          {
//            if(isLineBroken || isReadDone)
//                break;
//            if(!connTCPSocket.isConnected()){
//                continue;
//            }
//  //          isLineBroken = false;
//  //          isReadDone = false;
//            if(cnt == 0)
//                cnt++;
//            nBytes = 0;
//            sMsgLen = "";
//            try
//            {
//              try
//              {
//                  iLen = dataInputStream.readInt();
//                  
//              }
//              catch(Exception e)
//              {
//                  return;
//              }
//              if(iLen > 0 && iLen < 0xfa000)
//              {
//                  String sLen = "";
//                  int iLenCompare = String.valueOf(iLen).length();
//                  switch(iLenCompare)
//                  {
//                  case 4: // '\004'
//                      sLen = String.valueOf(iLen);
//                      break;
//
//                  case 3: // '\003'
//                      sLen = " ".concat(String.valueOf(String.valueOf(String.valueOf(iLen))));
//                      break;
//
//                  case 2: // '\002'
//                      sLen = "  ".concat(String.valueOf(String.valueOf(String.valueOf(iLen))));
//                      break;
//
//                  case 1: // '\001'
//                      sLen = "   ".concat(String.valueOf(String.valueOf(String.valueOf(iLen))));
//                      break;
//                  }
//                  msgBytes = new byte[iMSG_LENGTH];
//                  char cLenTemp[] = sLen.toCharArray();
//                  for(int i = 0; i < iMSG_LENGTH; i++)
//                  {
//                      String sLenTemp = String.valueOf(cLenTemp[i]);
//                      if(sLenTemp.trim().equals(""))
//                          msgBytes[i] = 0;
//                      else
//                          msgBytes[i] = Byte.parseByte(sLenTemp);
//                  }
//
//                  nBytes = iLen + iMSG_LENGTH;
//                  byte byteData[] = new byte[nBytes];
//                  tmpByte = new byte[nBytes];
//                  int iReadLen = 0;
//                  int iTotalReadLen = 0;
//                  int iStartPos = 0;
//                  int iAvailable = 0;
//                  for(; iTotalReadLen < iLen; iTotalReadLen += iReadLen)
//                  {
//                      iReadLen = dataInputStream.read(tmpByte, iTotalReadLen, tmpByte.length - iTotalReadLen - iMSG_LENGTH);
////                      if(general.DEBUGMSG)
////                          System.out.println("Length of read bytes in one read == ".concat(String.valueOf(String.valueOf(iReadLen))));
//                      if(iReadLen == -1)
//                          isLineBroken = true;
//                      for(int ii = 0; ii < iReadLen; ii++)
//                          byteData[iTotalReadLen + ii + iMSG_LENGTH] = tmpByte[iTotalReadLen + ii];
//
//                      iStartPos = iReadLen;
//                  }
//                  if(nBytes > 0)
//                  {
//                      for(int ii = 0; ii < iMSG_LENGTH; ii++)
//                          byteData[ii] = msgBytes[ii];
//
//                      byteTestSocket[iHead] = byteData;
//                      if(iHead == lengthReceByteData - 1)
//                          iHead = 0;
//                      else
//                          iHead++;
//                       isReadDone = true;
//
////                      if(general.DEBUGMSG)
////                          System.out.println("TCPSocket.readThread: Read done.");
//                  } else
//                  {
//                      System.out.println("\r\n[TCPSocket::receive] Receive bytes is zero");
//                  }
//                  if(nBytes == -1)
//                      isLineBroken = true;
////                  System.out.println(new Date()+": \rNumber of bytes receive:"+String.valueOf(nBytes));
//              } else
//              {
//                  System.out.println("E TCPSocket.rThread:: Error:Invalid message length. Terminating rThread.");
//                  isLineBroken = true;
//                  return;
//              }
//            }
//            catch(Exception e)
//            {
//              isLineBroken = true;
//              e.printStackTrace();
//              System.out.println(String.valueOf(String.valueOf((new StringBuffer("\r\n[TCPSocket::receive] ")).append(e.toString()).append("\n"))));
//              return;
//            }
//          } while(true);
//          long end=new Date().getTime();
//          System.out.println("Thoi gian Response: "+(end-start)+"ms");
//      }
//
//      public ReadThread(){
//          isLineBroken = false;
//          isReadDone = false;
//      }
//
//    }
//
//
//    public void init()
//    {
//        iDataMaxByte = Integer.parseInt(Utils.getHostCommSetting("settings.dataMaxByte"));
//        iTimeOut = Integer.parseInt(Utils.getHostCommSetting("settings.timeout"));
//    }
//
//    public void setGeneral(General settings)
//    {
//        general = settings;
//    }
//
//    public  void resetReceivedBytesData()
//    {
//        byteTestSocket = new byte[lengthReceByteData][];
//    }
//
//    protected byte[] getByteReceiveData()
//    {
//        if(readThread != null && readThread.isReadDone)
//            return byteReceiveData;
//        else
//            return new byte[0];
//    }
//
//    protected String getReceiveData()
//    {
//        return receiveData;
//    }
//
//    protected synchronized boolean getOnline()
//    {
//        if(socket == null)
//            return false;
//        else
//            return readThread.getOnline();
//    }
//
//    protected boolean getTimeOut()
//    {
//        return bTimeout;
//    }
//
//    protected TCPSocket()
//    {
//        bConnected = false;
//        
//        init();
//        try
//        {
//            intTimeOut = 1000 * Integer.parseInt(Utils.getHostCommSetting("settings.connectionTimeOut"));
//        }
//        catch(NumberFormatException nfe)
//        {
//            System.err.println("E No [settings.connectionTimeOut] settings specified or error parsing the values in Host.prop. Default time-out at 15 seconds.\nE Details: ".concat(String.valueOf(String.valueOf(nfe.getMessage()))));
//            intTimeOut = 15000;
//        }
//    }
//
//    protected boolean connect(String sRemoteIP, String sServerPort)
//    {
////        close();
//        long iInit = 0L;
//        long iEnd = 0L;
//        long iElapsed = 0L;
//        iInit = System.currentTimeMillis();
//        int iServerPort = (new Integer(sServerPort)).intValue();
//        try
//        {
//            setState(4);
//            InetAddress svraddr = InetAddress.getByName(sRemoteIP);
//            if(svraddr == null)
//            {
//                setState(9);
//                boolean flag = false;
//                return flag;
//            }
//            setState(5);
//            setState(6);
//            boolean bConnected = false;
//            int i = 0;
//            if(connTCPSocket == null)
//            {
//                connTCPSocket = new ConnectTCP(sRemoteIP, iServerPort);
//                connTCPSocket.start();
//            } else
//            {
//                switch(connTCPSocket.getStates())
//                {
//                 case 1: // '\001'
//                  System.out.println("Thread is connecting in backgroud. Not spawning new connection thread.");
//                  break;
//
//                default:
//                  System.out.println("Spawning new connection thread.");
//                  connTCPSocket = null;
//                  connTCPSocket = new ConnectTCP(sRemoteIP, iServerPort);
//                  connTCPSocket.start();
//                  break;
//                }
//            }
//            while(!connTCPSocket.isConnected())
//              try
//              {
//                  Thread.sleep(10L);
//              }
//              catch(InterruptedException interruptedexception) { }
//            bConnected = true;
//            socket = null;
//            if(bConnected){
//                socket = connTCPSocket.getSock();
//            }
//            if(socket.isClosed())
//                socket = new Socket(sRemoteIP,iServerPort);
//            iEnd = System.currentTimeMillis();
//            iElapsed = (iEnd - iInit) / (long)1000;
//            if(socket == null)
//            {
//                setState(9);
//                System.out.println(String.valueOf(String.valueOf((new StringBuffer("[TestSocket::connect] - ")).append(sRemoteIP).append(", ").append(sServerPort).append("  ").append("Socket is null"))));
//                receiveData = "Socket is null";
//                boolean flag3 = false;
//                return flag3;
//            }
//            
//            outPutStream = new PrintStream(socket.getOutputStream());
//            if(outPutStream == null)
//            {
//                setState(9);
//                receiveData = "Output stream is null";
//                boolean flag4 = false;
//                return flag4;
//            }
//            dataInputStream = new DataInputStream(socket.getInputStream());
//            if(dataInputStream == null)
//            {
//                setState(9);
//                receiveData = "Input stream is null";
//                boolean flag5 = false;
//                return flag5;
//            }
//            else
//            {
//                setState(7);
//                readThread = null;
//                readThread = new ReadThread();
//                readThread.setName("ReadThread"+sServerPort);
//                readThread.start();
//                boolean flag6 = true;
//                return flag6;
//            }
//        }
//        catch(ConnectException ce)
//        {
//            iEnd = System.currentTimeMillis();
//            iElapsed = (iEnd - iInit) / (long)1000;
//            System.out.println(String.valueOf(String.valueOf((new StringBuffer("Connection time = ")).append(iElapsed).append(" seconds."))));
//            System.out.println("TestSocket::connect - ConnectException");
//            setState(3);
//            receiveData = ce.getMessage();
//            ce.printStackTrace();
//            boolean flag1 = false;
//            return flag1;
//        }
//        catch(IOException ioe)
//        {
//            iEnd = System.currentTimeMillis();
//            iElapsed = (iEnd - iInit) / (long)1000;
//            System.out.println(String.valueOf(String.valueOf((new StringBuffer("Connection time = ")).append(iElapsed).append(" seconds."))));
//            System.out.println("TestSocket::connect - IOException");
//            setState(9);
//            receiveData = ioe.getMessage();
//            ioe.printStackTrace();
//            boolean flag2 = false;
//            return flag2;
//        }
//       
//    }
//
//    public boolean send(byte bSendData[])
//    {
//        receiveData = new String();
//        lengthReceByteData = 10;
//        iHead = 0;
//        iTail = 0;
//        byteReceiveData = new byte[4096];
//        resetReceivedBytesData();
//        readThread.setReadDone(false);
//        if(outPutStream == null)
//        {
//            System.out.println("[TCPSocket::send] - Output stream is null");
//            setState(9);
//            return false;
//        }
//        if(bSendData.length <= iDataMaxByte)
//        {
//            try
//            {
//                outPutStream.write(bSendData, 0, bSendData.length);
//                boolean flag = true;
//                return flag;
//            }
//            catch(Exception e)
//            {
////                readThread.setLineBroken();
//                System.out.println("TCPSocket.sendException: ".concat(String.valueOf(String.valueOf(e.getMessage()))));
//            }
//            
//            boolean flag1 = false;
//            return flag1;
//        } else
//        {
//            return false;
//        }
//    }
//
//    public void receive()
//    {
//        int ii = 0;
//        bTimeout = false;
//        do
//        {
//          if(ii >= iTimeOut || iHead != iTail)
//              break;
//          try
//          {
//              Thread.sleep(10L);
//          }
//          catch(Exception e)
//          {
//              break;
//          }
//          ii++;
//        } while(true);
//        if(ii >= iTimeOut)
//        {
//          bTimeout = true;
//          System.out.println(String.valueOf(String.valueOf((new StringBuffer("TCPSocket: System auto time out after waiting for ")).append(ii).append(" seconds."))));
//        }
//        byteReceiveData = byteTestSocket[iTail];
//        if(iTail == lengthReceByteData - 1)
//          iTail = 0;
//        else
//          iTail++;
//    }
//    protected boolean close()
//    {
////        if(general.DEBUGMSG)
////            System.out.println("TCPSocket:close");
//        try
//        {
//            if(dataInputStream != null)
//            {
//                int iAvai = 0;
//                int iRead = 0;
//                try
//                {
//                    iAvai = dataInputStream.available();
//                    if(iAvai > 0)
//                    {
//                        byte baFinalContent[] = new byte[iAvai];
//                        iRead = dataInputStream.read(baFinalContent);
//                    }
//                }
//                catch(Exception ies)
//                {
//                    ies.printStackTrace();
//                }
//               System.out.println("\r\n\t\t**********************************************************************\r\n");
//            }
//            setState(8);
////            if(connTCPSocket != null && connTCPSocket.isAlive())
////                connTCPSocket.interrupt();
//            if(outPutStream != null){
//              outPutStream.close();
////              outPutStream.flush();
//              outPutStream = null;
//            }
//            if(dataInputStream != null){
//              dataInputStream.close();
//              dataInputStream = null;
//            }
//            if(socket != null)
//                socket.close();
//            socket = null;
//            setState(0);
//            boolean flag = true;
//            return flag;
//            
//        }
//        catch(IOException ioe)
//        {
//            System.out.println("TestSocket::close - IOException");
//            setState(9);
//            System.out.println(String.valueOf(String.valueOf((new StringBuffer("[TestSocket::close] - ")).append("  ").append(ioe.getMessage()))));
//            ioe.printStackTrace();
//            boolean flag1 = false;
//            return flag1;
//        }
//    }
//
//    protected synchronized void setState(int iNewState)
//    {
//        state = iNewState;
//        switch(state)
//        {
//        case 0: // '\0'
//        case 1: // '\001'
//        case 2: // '\002'
//        case 3: // '\003'
//        case 4: // '\004'
//        case 5: // '\005'
//        case 6: // '\006'
//        case 7: // '\007'
//        case 8: // '\b'
//        case 9: // '\t'
//        default:
//            return;
//        }
//    }
//}
