package edu.tutor.gis.dbf.reader;

import java.io.IOException;

public class UnsupportedVersionException extends IOException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedVersionException()
	{
		;
	}

	public UnsupportedVersionException(String arg0)
	{
		super(arg0);
	}

	public UnsupportedVersionException(Throwable arg0)
	{
		super(arg0);
	}

	public UnsupportedVersionException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

}
