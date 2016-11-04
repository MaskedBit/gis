package edu.tutor.gis.shape.dump;

public class ShapeDump
{

	public static void main(String[] args)
	{
		CommandLine commandLine = CommandLine.parse(args);
		
		if (commandLine.files().length < 1)
		{
			System.out.println("*** Requires at least 1 shapefile (.shp) name");
			return;
		}

		for (String curPath : commandLine.files())
		{
			System.out.println("Input file:  \"" + curPath + "\"");
		}
	}

}
