package vn.com.msb.as400.dsp;

import java.util.*;

public class DSPCharField extends DSPField
{
	public DSPCharField(int iLen,String strDes)
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
			Arrays.fill(bt,(byte)64);
			return bt;
		}
		if(strValue.length() > getLength())
			strValue = strValue.substring(0,getLength());
		return DSPConversion.instance.Ascii2Ebcdic(strValue,getLength());
	}

	public String unpack(byte[] btPacked) throws Exception
	{
		byte[] temp = new byte[getLength()];
//		System.out.println(" - startpos: "+getOffset() + " - Length:"+temp.length +" - byte length: "+ btPacked.length + " - temp length:"+ temp.length);
		System.arraycopy(btPacked,getOffset(),temp,0,temp.length);
		return new String(DSPConversion.instance.Ebcdic2Ascii(temp)).trim();
	}

	public DSPField createCopy() throws Exception
	{
		return new DSPCharField(getLength(),getDescription());
	}
}
