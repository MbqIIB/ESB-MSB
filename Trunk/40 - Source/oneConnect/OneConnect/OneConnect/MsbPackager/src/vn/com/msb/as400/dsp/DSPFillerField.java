package vn.com.msb.as400.dsp;

import java.util.Arrays;

public class DSPFillerField extends DSPField
{
	private byte[] b;
	public DSPFillerField(int iLen,String strDes)
	{
		super(iLen,strDes);
		b = new byte[this.getLength()];
		Arrays.fill(b,(byte)64);
	}

	public int getMaxPackedLength()
	{
		return 0;
	}

	public byte[] pack(String strValue) throws Exception
	{
		return b;
	}

	public String unpack(byte[] btPacked) throws Exception
	{
		return "";
	}

	private int offset;
	public void setOffset(int offset)
	{
		this.offset = offset;
	}

	public int getOffset()
	{
		return offset;
	}

	public DSPField createCopy() throws Exception
	{
		return new DSPFillerField(getLength(),getDescription());
	}
}
