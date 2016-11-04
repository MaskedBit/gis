package edu.tutor.gis.shape.reader.priv;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;

public abstract class ShapeFileReader
{
	protected SeekableByteChannel channel;
	protected long readOffset = 0;

	public ShapeFileReader(SeekableByteChannel channel)
	{
		this.channel = channel;
	}
	
	protected ByteBuffer fetchByteBuffer(int size, ByteOrder byteOrder) throws IOException
	{
		ByteBuffer buffer = ByteBuffer.allocate(size).order(byteOrder);
		
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

	public void close() throws IOException
	{
		if (channel.isOpen())
		{
			channel.close();
		}
	}

}
