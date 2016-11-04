package edu.tutor.gis.dbf.reader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;

public abstract class DbfFileReader
{
	protected SeekableByteChannel channel;
	protected long readOffset = 0;
	
	public DbfFileReader(SeekableByteChannel channel)
	{
		this.channel = channel;
	}
	
	protected ByteBuffer fetchByteBuffer(int size) throws IOException
	{
		ByteBuffer buffer = ByteBuffer.allocate(size).order(ByteOrder.LITTLE_ENDIAN);
		
		int readCount = channel.read(buffer);
		
		if (readCount != size)
		{
			throw new IOException("File truncated");
		}
		else
		{
			readOffset += readCount;
		}
		
		buffer.rewind();
		
		return (buffer);
	}

	public abstract void close();

}
