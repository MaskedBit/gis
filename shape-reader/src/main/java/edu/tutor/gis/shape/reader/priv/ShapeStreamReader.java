package edu.tutor.gis.shape.reader.priv;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import edu.tutor.gis.shape.BoundingBox;
import edu.tutor.gis.shape.MultiPointShapeRecord;
import edu.tutor.gis.shape.NullShapeRecord;
import edu.tutor.gis.shape.Point;
import edu.tutor.gis.shape.PointShapeRecord;
import edu.tutor.gis.shape.ShapeHeader;
import edu.tutor.gis.shape.ShapeRecord;
import edu.tutor.gis.shape.ShapeType;

public class ShapeStreamReader extends ShapeFileReader implements Iterator<ShapeRecord>
{
	public static final int RECORD_HEADER_LENGTH = 8;	// 2 big-endian ints
	public static final int SHAPE_HEADER_LENGTH = 4;	// 1 little-endian int

	private ShapeHeader header;
	private long fileByteLength;

	private ShapeStreamReader(SeekableByteChannel channel, ShapeHeader header) throws IOException
	{
		super(channel);
		
		this.channel.position(SHAPE_FILE_HEADER_LENGTH);
		this.readOffset = SHAPE_FILE_HEADER_LENGTH;
		this.header = header;
		this.fileByteLength = this.header.getFileLength() * 2;  // File length is in 16-bit words
	}

	public ShapeHeader getHeader()
	{
		return (header);
	}

	public static Iterator<ShapeRecord> recordIterator(Path filePath, ShapeHeader header) throws IOException
	{
		SeekableByteChannel channel = Files.newByteChannel(filePath);

		ShapeStreamReader reader = new ShapeStreamReader(channel, header);

		return (reader);
	}
	
	@Override
	public boolean hasNext()
	{
		if ((readOffset + RECORD_HEADER_LENGTH + SHAPE_HEADER_LENGTH) < fileByteLength)
		{
			return (true);
		}
		else
		{
			return (false);
		}
	}

	@Override
	public ShapeRecord next()
	{
		try
		{
			return (buildRecord());
		}
		catch (IOException e)
		{
			throw new RuntimeException("I/O error reading shape record", e);
		}
	}

	private ShapeRecord buildRecord() throws IOException
	{
		ShapeRecordHeader recordHeader = readRecordHeader();
		
		switch (recordHeader.getShapeType())
		{
		case NULL:
			return (new NullShapeRecord(recordHeader));

		case POINT:
			return (buildPointShapeRecord(recordHeader));

		case POLYLINE:
			return (PolyShapeReader.readRecord(this, recordHeader));

		case POLYGON:
			return (PolyShapeReader.readRecord(this, recordHeader));

		case MULTI_POINT:
			return (buildMultiPointShapeRecord(recordHeader));

		case POINT_Z:
		case POLYLINE_Z:
		case POLYGON_Z:
		case MULTI_POINT_Z:
		case POINT_M:
		case POLYLINE_M:
		case POLYGON_M:
		case MULTI_POINT_M:
		case MULTI_PATCH:
		default:
			throw new RuntimeException("Cannot process shape type " + recordHeader.getShapeType().toString());
		}
	}

	private ShapeRecordHeader readRecordHeader() throws IOException
	{
		ByteBuffer readerHeaderBuffer = fetchByteBuffer(RECORD_HEADER_LENGTH, ByteOrder.BIG_ENDIAN);
		
		int recordNumber = readerHeaderBuffer.getInt();
		int contentLength = readerHeaderBuffer.getInt();

		ByteBuffer shapeHeaderBuffer = fetchByteBuffer(SHAPE_HEADER_LENGTH, ByteOrder.LITTLE_ENDIAN);

		ShapeType shapeType = ShapeType.valueOf(shapeHeaderBuffer.getInt());

		return (new ShapeRecordHeader(recordNumber, contentLength, shapeType));
	}

	private static final int POINT_RECORD_LENGTH = 16;

	private PointShapeRecord buildPointShapeRecord(ShapeRecordHeader recordHeader) throws IOException
	{
		ByteBuffer buffer = fetchByteBuffer(POINT_RECORD_LENGTH, ByteOrder.LITTLE_ENDIAN);
		
		Point point = readPoint(buffer);
		
		return (new PointShapeRecord(recordHeader, point));
	}

	private static final int FIXED_MULTI_POINT_RECORD_LENGTH = 36;

	private ShapeRecord buildMultiPointShapeRecord(ShapeRecordHeader recordHeader) throws IOException
	{
		ByteBuffer buffer = fetchByteBuffer(FIXED_MULTI_POINT_RECORD_LENGTH, ByteOrder.LITTLE_ENDIAN);

		BoundingBox boundingBox = readBoundingBox(buffer);
		int numPoints = buffer.getInt();
		
		Point[] pointArray = new Point[numPoints];
		
		for (int index = 0; index < numPoints; index++)
		{
			pointArray[index] = readPoint(buffer);
		}

		return (new MultiPointShapeRecord(recordHeader, boundingBox, pointArray));
	}

}
