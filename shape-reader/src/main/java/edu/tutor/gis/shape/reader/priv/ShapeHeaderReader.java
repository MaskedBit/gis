package edu.tutor.gis.shape.reader.priv;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

import edu.tutor.gis.shape.MeasuredBoundingBox3D;
import edu.tutor.gis.shape.ShapeHeader;
import edu.tutor.gis.shape.ShapeType;

public class ShapeHeaderReader extends ShapeFileReader
{
	public static final int BIG_ENDIAN_SEGMENT_LENGTH = 28;
	public static final int LITTLE_ENDIAN_SEGMENT_LENGTH = SHAPE_FILE_HEADER_LENGTH - BIG_ENDIAN_SEGMENT_LENGTH;

	public static final int ESRI_FILE_CODE = 9994;
	
	private ShapeHeader header;

	public ShapeHeaderReader(SeekableByteChannel channel)
	{
		super(channel);

		this.header = new ShapeHeader();
	}

	public static ShapeHeader connect(Path path) throws IOException
	{
		try (SeekableByteChannel channel = Files.newByteChannel(path))
		{
			ShapeHeaderReader reader = new ShapeHeaderReader(channel);

			reader.readHeader();
			
			return (reader.header);
		}
	}

	private void readHeader() throws IOException
	{
		// The first 28 bytes are big-endian
		ByteBuffer buffer = fetchByteBuffer(BIG_ENDIAN_SEGMENT_LENGTH, ByteOrder.BIG_ENDIAN);

		int fileCode = buffer.getInt();
		
		if (fileCode != ESRI_FILE_CODE)
		{
			throw new IOException("File code was " + fileCode + ", should be " + ESRI_FILE_CODE);
		}
		
		buffer.position(24);

		header.setFileLength(buffer.getInt());

		// The rest of the 100-byte header is little-endian
		buffer = fetchByteBuffer(LITTLE_ENDIAN_SEGMENT_LENGTH, ByteOrder.LITTLE_ENDIAN);
		
		header.setVersion(buffer.getInt());
		
		if (header.getVersion() != ShapeHeader.CURRENT_ESRI_VERSION)
		{
			throw new IOException("Cannot process file version " + header.getVersion()
				+ ", only " + ShapeHeader.CURRENT_ESRI_VERSION + " is supported");
		}
		
		int shapeTypeCode = buffer.getInt();
		
		header.setShapeType(ShapeType.valueOf(shapeTypeCode));
		
		MeasuredBoundingBox3D boundingBox = readMeasuredBoundingBox3D(buffer);
		
		header.setBoundingBox(boundingBox);
	}
	
}
