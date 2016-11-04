package edu.tutor.gis.dbf.reader;

import java.io.IOException;

public enum DbfFieldType
{
	UNKNOWN('X'),
	CHARACTER('C'),
	DATE('D'),
	FLOAT('F'),
	LOGICAL('L'),
	MEMO('M'),
	NUMERIC('N');

	private char fieldType;

	DbfFieldType(char fieldType)
	{
		this.fieldType = fieldType;
	}
	
	public char getLetterCode()
	{
		return (fieldType);
	}

	public static DbfFieldType valueOf(char fieldType) throws IOException
	{
		switch (fieldType)
		{
		case 'C':
			return (CHARACTER);

		case 'D':
			return (DATE);

		case 'F':
			return (FLOAT);

		case 'L':
			return (LOGICAL);

		case 'M':
			return (MEMO);

		case 'N':
			return (NUMERIC);

		default:
			throw new IOException("Unknown .dbf field type:  '" + fieldType + "'");
		}
	}

}
