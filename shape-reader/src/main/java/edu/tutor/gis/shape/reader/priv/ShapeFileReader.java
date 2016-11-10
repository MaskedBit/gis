package edu.tutor.gis.shape.reader.priv;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;

import edu.tutor.gis.shape.BoundingBox;
import edu.tutor.gis.shape.BoundingBox3D;
import edu.tutor.gis.shape.MeasuredBoundingBox3D;
import edu.tutor.gis.shape.Point;

public abstract class ShapeFileReader
{
	public static final int SHAPE_HEADER_LENGTH = 100;	// Header of .shp and .shx files is always 100 bytes long.
	public static final int POINT_STRUCTURE_LENGTH = 16;	// 2 doubles at 8 each

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

	protected MeasuredBoundingBox3D readMeasuredBoundingBox3D(ByteBuffer buffer)
	{
		MeasuredBoundingBox3D boundingBox = new MeasuredBoundingBox3D();
		
		// Note the order of bounding box fields!
		boundingBox.setMinX(buffer.getDouble());
		boundingBox.setMinY(buffer.getDouble());
		boundingBox.setMaxX(buffer.getDouble());
		boundingBox.setMaxY(buffer.getDouble());
		boundingBox.setMinZ(buffer.getDouble());
		boundingBox.setMaxZ(buffer.getDouble());
		boundingBox.setMinM(buffer.getDouble());
		boundingBox.setMaxM(buffer.getDouble());
		
		return (boundingBox);
	}

	protected BoundingBox3D readBoundingBox3D(ByteBuffer buffer)
	{
		BoundingBox3D boundingBox = new BoundingBox3D();
		
		// Note the order of bounding box fields!
		boundingBox.setMinX(buffer.getDouble());
		boundingBox.setMinY(buffer.getDouble());
		boundingBox.setMaxX(buffer.getDouble());
		boundingBox.setMaxY(buffer.getDouble());
		boundingBox.setMinZ(buffer.getDouble());
		boundingBox.setMaxZ(buffer.getDouble());
		
		return (boundingBox);
	}

	protected BoundingBox readBoundingBox(ByteBuffer buffer)
	{
		BoundingBox boundingBox = new BoundingBox();
		
		// Note the order of bounding box fields!
		boundingBox.setMinX(buffer.getDouble());
		boundingBox.setMinY(buffer.getDouble());
		boundingBox.setMaxX(buffer.getDouble());
		boundingBox.setMaxY(buffer.getDouble());
		
		return (boundingBox);
	}
	
	protected Point readPoint(ByteBuffer buffer)
	{
		double x = buffer.getDouble();
		double y = buffer.getDouble();
		
		return (new Point(x, y));
	}

}
