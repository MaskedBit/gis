package edu.tutor.gis.dbf.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import edu.tutor.gis.dbf.reader.priv.DbfHeaderReader;
import edu.tutor.gis.dbf.reader.priv.DbfRandomReader;
import edu.tutor.gis.dbf.reader.priv.DbfStreamReader;

public class DbfReader implements AutoCloseable, Iterable<DbfRecord>
{
	private String dbPath;
	private DbfHeader header;
	private DbfRandomReader randomReader = null;
	
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
		if (randomReader != null)
		{
			randomReader.close();
		}
	}

	@Override
	public Iterator<DbfRecord> iterator()
	{
		try
		{
			return (DbfStreamReader.recordIterator(Paths.get(dbPath), header));
		}
		catch (IOException e)
		{
			throw new RuntimeException("I/O error reading file", e);
		}
	}

	public DbfRecord readRecord(int index) throws IOException
	{
		if (randomReader == null)
		{
			randomReader = DbfRandomReader.connect(Paths.get(dbPath), header);
		}
		
		return (randomReader.read(index));
	}

}
