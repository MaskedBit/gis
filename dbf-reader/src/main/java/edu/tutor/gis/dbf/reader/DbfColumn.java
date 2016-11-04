package edu.tutor.gis.dbf.reader;

import java.nio.ByteBuffer;

public abstract class DbfColumn
{
	private String name;
	private int length = -1;
	private DbfFieldType type = DbfFieldType.UNKNOWN;

	protected DbfColumn(String name, int length, DbfFieldType type)
	{
		this.name = name;
		this.length = length;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public int getLength() {
		return length;
	}

	public DbfFieldType getType() {
		return type;
	}

	public abstract String asString();
	
	public static DbfColumn construct(DbfField field, ByteBuffer buffer)
	{
		switch (field.getType())
		{
		case CHARACTER:
			return (DbfCharacterColumn.construct(field, buffer));

		default:
			throw new RuntimeException("Unknown field type:  " + field.getType());
		}
	}

}
