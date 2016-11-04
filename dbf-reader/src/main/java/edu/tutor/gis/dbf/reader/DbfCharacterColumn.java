package edu.tutor.gis.dbf.reader;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class DbfCharacterColumn extends DbfColumn
{
	private String value;
	
	private DbfCharacterColumn(String name, int length, DbfFieldType type, String value)
	{
		super(name, length, type);
		
		this.value = value;
	}

	public static DbfColumn construct(DbfField field, ByteBuffer buffer)
	{
		byte[] rawBits = new byte[field.getLength()];
		
		buffer.get(rawBits);

		String value = new String(rawBits, Charset.forName("UTF-8" ));
		
		return (new DbfCharacterColumn(field.getName(), field.getLength(), field.getType(), value.trim()));
	}

	@Override
	public String asString()
	{
		return (value);
	}

}
