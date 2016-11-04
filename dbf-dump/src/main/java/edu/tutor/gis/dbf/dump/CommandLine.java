package edu.tutor.gis.dbf.dump;

import java.util.ArrayList;
import java.util.List;

public class CommandLine
{
	private boolean isDebug;
	private String[] files;
	
	private CommandLine(boolean isDebug, String[] files)
	{
		this.isDebug = isDebug;
		this.files = files;
	}

	public boolean isDebug()
	{
		return isDebug;
	}

	public String[] files()
	{
		return files;
	}

	public static CommandLine parse(String[] args)
	{
		boolean isDebug = false;
		List<String> files = new ArrayList<String>();

		for (String curArg : args)
		{
			switch (curArg)
			{
			case "-d":
			case "--debug":
				isDebug = true;
				break;
				
			default:
				files.add(curArg);
				break;
			}
		}
		
		return (new CommandLine(isDebug, files.toArray(new String[files.size()])));
	}

}
