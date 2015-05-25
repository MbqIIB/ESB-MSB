package vn.com.msb.as400.dsp;

public abstract class DSPField
{

	public DSPField(int iLen,String strDes)
	{
		length = iLen;
		description = strDes;
	}

	public int getMaxPackedLength()
	{
		return 0;
	}

	private int offset;
	private int length;
	private String description;
	public void setOffset(int offset)
	{
		this.offset = offset;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getOffset()
	{
		return offset;
	}

	public int getLength()
	{
		return length;
	}

	public String getDescription()
	{
		return description;
	}

	public void copyData(DSPField fld)
	{
		fld.setLength(getLength());
		fld.setOffset(getOffset());
		fld.setDescription(getDescription());
	}

	public abstract DSPField createCopy() throws Exception;
	public abstract byte[] pack(String strValue) throws Exception;
	public abstract String unpack(byte[] btPacked) throws Exception;
}
