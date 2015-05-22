package vn.com.msb.as400.dsp;

import java.util.*;

public class DSPVietField extends DSPField
{
	public DSPVietField(int iLen,String strDes)
	{
		super(iLen,strDes);
	}

	public int getMaxPackedLength()
	{
		return 0;
	}

	public byte[] pack(String strValue) throws Exception
	{
		int iLen = getLength();
		String strInput = strValue;
		if(strInput == null || strInput.equals(""))
		{
			byte[] bt = new byte[iLen];
			Arrays.fill(bt,(byte)0);
			return bt;
		}
		return DSPConversion.instance.Ascii2Ebcdic(strValue,getLength());
	}

	public String unpack(byte[] btPacked) throws Exception
	{
		byte[] temp = new byte[getLength()];
		System.arraycopy(btPacked,getOffset(),temp,0,temp.length);
		return new String(DSPConversion.instance.Ebcdic2Ascii(temp)).trim();		
	}

	public DSPField createCopy() throws Exception
	{
		return new DSPVietField(getLength(),getDescription());
	}
}
