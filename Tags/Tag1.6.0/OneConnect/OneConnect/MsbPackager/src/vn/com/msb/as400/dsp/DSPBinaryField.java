package vn.com.msb.as400.dsp;

import java.util.*;
import com.ftl.util.StringUtil;

public class DSPBinaryField extends DSPField
{
	public DSPBinaryField(int iLen,String strDes)
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
		byte[] bt = new byte[iLen];
		String strInput = strValue;
		if(strInput == null || strInput.equals(""))
		{
			Arrays.fill(bt,(byte)0);
			return bt;
		}
		byte[] btValue = StringUtil.intToByteArray(Integer.parseInt(strValue));
		System.arraycopy(btValue,0,bt,bt.length - btValue.length,btValue.length);
		return bt;
	}

	public String unpack(byte[] btPacked) throws Exception
	{
		byte[] temp = new byte[getLength()];
		System.arraycopy(btPacked,getOffset(),temp,0,temp.length);
		return String.valueOf(StringUtil.byteArrayToInt(temp)).trim();
	}

	public DSPField createCopy() throws Exception
	{
		return new DSPBinaryField(getLength(),getDescription());
	}
}
