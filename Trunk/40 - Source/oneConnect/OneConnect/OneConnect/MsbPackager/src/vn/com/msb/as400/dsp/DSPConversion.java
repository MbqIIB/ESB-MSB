package vn.com.msb.as400.dsp;

import axlua2.Conversion;
import com.ftl.util.StringUtil;

public class DSPConversion extends Conversion
{
	public static DSPConversion instance = new DSPConversion();
	public byte[] Ascii2Ebcdic(String input,int iLength)
	{
		byte[] bt = new byte[iLength];
		java.util.Arrays.fill(bt,(byte)64);
		byte[] btValue = super.Ascii2Ebcdic(input);
		System.arraycopy(btValue,0,bt,0,btValue.length);
		return bt;
	}

	public byte[] strToPacked(String input,int iFieldLenght,int iFieldDec)
	{
		return super.strToPacked(input,iFieldLenght,iFieldDec);
	}

	public byte[] charToHex(char[] sSendDataPartCharArray,int iFieldLength)
	{
		return super.charToHex(sSendDataPartCharArray,iFieldLength);
	}

	public byte[] toNumericEbcdic(String sSendDataPart,int iFieldLength)
	{
		return super.toNumericEbcdic(StringUtil.lpad(sSendDataPart,iFieldLength,'0'),iFieldLength);
	}

	public byte[] strToZone(String sSendDataPart,int iFieldLength,int iFieldDecPnt)
	{
		return super.strToZone(sSendDataPart,iFieldLength,iFieldDecPnt);
	}

	public String byteToStr(byte[] b)
	{
		return super.byteToStr(super.Ebcdic2Ascii(b));
	}

	public byte[] Ebcdic2Ascii(byte[] b)
	{
		return super.Ebcdic2Ascii(b);
	}

	public String packedToString(byte[] b,int iLen,int iDec)
	{
		return super.packedToStr(b,iLen,iDec);
	}
}
