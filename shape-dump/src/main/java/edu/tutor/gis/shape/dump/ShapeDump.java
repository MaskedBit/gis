package edu.tutor.gis.shape.dump;

import java.io.IOException;

import edu.tutor.gis.shape.reader.ShapeReader;

public class ShapeDump
{
	private ShapeReader reader;

	public static void main(String[] args)
	{
		CommandLine commandLine = CommandLine.parse(args);
		
		if (commandLine.files().length < 1)
		{
			System.out.println("*** Requires at least 1 shapefile name");
			return;
		}

		for (String curPath : commandLine.files())
		{
			System.out.println("Input file:  \"" + curPath + "\"");
			try (ShapeReader reader = ShapeReader.connect(curPath))
			{
				if (commandLine.isDebug())
				{
					reader.getHeader().debugPrint(System.out, "");
				}
			}
			catch (IOException e)
			{
				System.out.println("*** I/O error reading file:  " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
