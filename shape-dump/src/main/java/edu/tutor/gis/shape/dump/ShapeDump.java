package edu.tutor.gis.shape.dump;

import java.io.IOException;
import java.io.PrintStream;

import edu.tutor.gis.shape.ShapeRecord;
import edu.tutor.gis.shape.reader.ShapeReader;

public class ShapeDump
{
	private ShapeReader reader;
	private int recordCounter = 0;
	
	private ShapeDump(ShapeReader reader)
	{
		this.reader = reader;
	}

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
				
				ShapeDump dumper = new ShapeDump(reader);
				
				dumper.printContents(System.out, "");
			}
			catch (IOException e)
			{
				System.out.println("*** I/O error reading file:  " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private void printContents(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		
		for (ShapeRecord curRecord : reader)
		{
			out.format("%s Record #%d%n", indent, recordCounter++);

			curRecord.debugPrint(out, childIndent);
		}
	}

}
