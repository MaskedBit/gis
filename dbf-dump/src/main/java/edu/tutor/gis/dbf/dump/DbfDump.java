package edu.tutor.gis.dbf.dump;

import java.io.IOException;
import java.io.PrintStream;

import edu.tutor.gis.dbf.reader.DbfColumn;
import edu.tutor.gis.dbf.reader.DbfReader;
import edu.tutor.gis.dbf.reader.DbfRecord;

public class DbfDump
{
	private int recordCounter = 0;
	private DbfReader reader;
	
	private DbfDump(DbfReader reader)
	{
		this.reader = reader;
	}

	public static void main(String[] args)
	{
		CommandLine commandLine = CommandLine.parse(args);
		
		if (commandLine.files().length < 1)
		{
			System.out.println("*** Requires at least 1 .dbf filename");
			return;
		}

		for (String curPath : commandLine.files())
		{
			System.out.println("Input file:  \"" + curPath + "\"");

			try (DbfReader reader = DbfReader.connect(curPath))
			{
				if (commandLine.isDebug())
				{
					reader.getHeader().debugPrint(System.out, "");
				}
				
				DbfDump dumper = new DbfDump(reader);
				
				dumper.printContents(System.out, "");
				
				// TEST for random file access
				System.out.println("---- Record 355 ----");
				dumper.recordCounter = 355;
				DbfRecord randomRecord = reader.readRecord(355);
				
				dumper.printRecord(System.out, "", randomRecord);

				System.out.println("---- Record 358 ----");
				dumper.recordCounter = 358;
				randomRecord = reader.readRecord(358);
				
				dumper.printRecord(System.out, "", randomRecord);
			}
			catch (IOException e)
			{
				System.out.println("*** I/O Error:  " + e.getMessage());
			}
			catch (Exception e1)
			{
				System.out.println("*** General Error:  " + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	private void printContents(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		
		for (DbfRecord curRecord : reader)
		{
			printRecord(out, childIndent, curRecord);
		}
	}

	private void printRecord(PrintStream out, String indent, DbfRecord record)
	{
		String childIndent = indent + "  ";

		out.format("%s Record #%d%n", indent, recordCounter++);
		
		for (DbfColumn curColumn : record.getColumns())
		{
			printColumn(out, childIndent, curColumn);
		}
	}

	private void printColumn(PrintStream out, String indent, DbfColumn curColumn)
	{
		out.format("%s name=\"%s\", type=%s, value=\"%s\"%n", indent, curColumn.getName(), curColumn.getType().toString(), curColumn.asString());
	}

}
