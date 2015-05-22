package vn.com.msb.as400.dsp;

import java.util.*;

public class DSPHexField extends DSPField
{
	public DSPHexField(int iLen,String strDes)
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
		return DSPConversion.instance.charToHex((strValue).toCharArray(),getLength());
	}

	public String unpack(byte[] btPacked) throws Exception
	{
		return null;
	}

	public DSPField createCopy() throws Exception
	{
		return new DSPHexField(getLength(),getDescription());
	}
}

