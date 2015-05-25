package vn.com.msb.as400.dsp;

import java.util.*;

public class DSPPackedField extends DSPField
{
	private int idec;

	public DSPPackedField(int iLen,int dec,String strDes)
	{
		super(iLen,strDes);
		idec = dec;
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
			bt[iLen - 1] = 15;
			return bt;
		}

		return DSPConversion.instance.strToPacked(strValue,getLength(),getDecimal());
	}

	public String unpack(byte[] btPacked) throws Exception
	{
		byte[] temp = new byte[getLength()];
		System.arraycopy(btPacked,getOffset(),temp,0,temp.length);
		return DSPConversion.instance.packedToString(temp,getLength(),getDecimal()).trim();
	}

	public void setDecimal(int idec)
	{
		this.idec = idec;
	}

	public int getDecimal()
	{
		return idec;
	}

	public DSPField createCopy() throws Exception
	{
		return new DSPPackedField(getLength(),getDecimal(),getDescription());
	}
}
