package edu.tutor.gis.dbf.reader.priv;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.tutor.gis.dbf.reader.DbfHeader;
import edu.tutor.gis.dbf.reader.DbfRecord;

public class DbfStreamReader extends DbfFileReader implements Iterator<DbfRecord>
{
	private DbfHeader header;
	private int curRecord = 0;
	
	public DbfStreamReader(SeekableByteChannel channel, DbfHeader header)
	{
		super(channel);

		this.header = header;
	}
	
	public DbfHeader getHeader()
	{
		return (header);
	}
	
	public static Iterator<DbfRecord> recordIterator(Path filePath, DbfHeader header) throws IOException
	{
		SeekableByteChannel channel = Files.newByteChannel(filePath);
		
		channel.position(header.getHeaderLength());

		DbfStreamReader reader = new DbfStreamReader(channel, header);

		return (reader);
	}
	
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
//			try
//			{
//				channel.close();
//
//				return (false);
//			}
//			catch (IOException e)
//			{
//				throw new RuntimeException("I/O error reading file", e);
//			}
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
