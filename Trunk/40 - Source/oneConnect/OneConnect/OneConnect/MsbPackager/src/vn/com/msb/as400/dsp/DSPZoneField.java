package vn.com.msb.as400.dsp;

import java.util.*;

public class DSPZoneField extends DSPField
{
	private int idec;
	public DSPZoneField(int iLen,int dec,String strDes)
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
			return bt;
		}
		return DSPConversion.instance.strToZone(strValue,getLength(),getDecimal());
	}

	public String unpack(byte[] btPacked) throws Exception
	{
		return null;
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
		return new DSPZoneField(getLength(),getDecimal(),getDescription());
	}
}
