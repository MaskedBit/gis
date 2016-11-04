package edu.tutor.gis.dbf.reader.priv;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

import edu.tutor.gis.dbf.reader.DbfHeader;
import edu.tutor.gis.dbf.reader.DbfRecord;

public class DbfRandomReader extends DbfFileReader
{
	private DbfHeader header;

	public DbfRandomReader(SeekableByteChannel channel, DbfHeader header)
	{
		super(channel);

		this.header = header;
	}

	public static DbfRandomReader connect(Path path, DbfHeader header) throws IOException
	{
		SeekableByteChannel channel = Files.newByteChannel(path);

		return (new DbfRandomReader(channel, header));
	}

	public DbfRecord read(int index) throws IOException
	{
		if (index >= header.getRecordCount())
		{
			throw new IOException("Record index is beyond end of file");
		}

		long offset = (index * header.getRecordLength()) + header.getHeaderLength();

		channel.position(offset);

		ByteBuffer buffer = fetchByteBuffer(header.getRecordLength());

		return (DbfRecord.construct(header.getFields(), buffer));
	}

}
