package edu.tutor.gis.dbf.reader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DbfStreamReader extends DbfFileReader
{
	private DbfHeader header;
	
	public DbfStreamReader(SeekableByteChannel channel)
	{
		super(channel);

		this.header = new DbfHeader();
	}
	
	public DbfHeader getHeader()
	{
		return (header);
	}
	
	public static DbfStreamReader connect(Path filePath) throws IOException
	{
		SeekableByteChannel channel = Files.newByteChannel(filePath);
		DbfStreamReader reader = new DbfStreamReader(channel);

		reader.readHeader();
		
		return (reader);
	}
	
	public Iterator<DbfRecord> recordIterator()
	{
		return (new RecordIterator());
	}
	
	private class RecordIterator implements Iterator<DbfRecord>
	{
		private int curRecord = 0;

		@Override
		public boolean hasNext()
		{
			if (curRecord < header.getRecordCount())
			{
				return (true);
			}
			else
			{
				return (false);
			}
		}

		@Override
		public DbfRecord next()
		{
			if (curRecord >= header.getRecordCount())
			{
				throw new NoSuchElementException("There are only " + header.getRecordCount() + " records in this file");
			}
			else
			{
				try
				{
					ByteBuffer buffer = fetchByteBuffer(header.getRecordLength());

					curRecord++;

					return (DbfRecord.construct(header.getFields(), buffer));
				}
				catch (IOException e)
				{
					throw new RuntimeException("I/O error reading file", e);
				}
			}
		}
		
	}

	public void close()
	{
		;
	}

}
