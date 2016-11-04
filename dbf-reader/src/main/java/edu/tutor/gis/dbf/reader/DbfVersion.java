package edu.tutor.gis.dbf.reader;

public enum DbfVersion
{
	UNKNOWN(-1),
	VERSION_3(3),
	VERSION_7(7);
	
	private int version;
	
	DbfVersion(int version)
	{
		this.version = version;
	}

	public int getVersionNumber()
	{
		return (version);
	}

	public static DbfVersion valueOf(int version)
	{
		switch (version)
		{
		case 3:
			return (VERSION_3);
			
		case 7:
			return (VERSION_7);
			
		default:
			throw new IllegalArgumentException("Unknown version:  " + version);
		}
	}

}
