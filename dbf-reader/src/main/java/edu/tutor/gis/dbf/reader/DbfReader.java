package edu.tutor.gis.dbf.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class DbfReader implements AutoCloseable, Iterable<DbfRecord>
{
	private String dbPath;
	private DbfFileReader fileReader;
	private DbfHeader header;
	
	private DbfReader(String dbPath)
	{
		this.dbPath = dbPath;
	}

	public String getPath()
	{
		return (dbPath);
	}

	public static DbfReader connect(String dbPath) throws IOException
	{
		DbfReader reader = new DbfReader(dbPath);
		Path filePath = Paths.get(dbPath);

		reader.header = DbfHeaderReader.connect(filePath);

		return (reader);
	}
	
	public DbfHeader getHeader()
	{
		return (header);
	}

	public List<DbfField> getFields()
	{
		return (header.getFields());
	}

	public void close() throws Exception
	{
		fileReader.close();
	}

	@Override
	public Iterator<DbfRecord> iterator()
	{
		return (fileReader.recordIterator());
	}

}
