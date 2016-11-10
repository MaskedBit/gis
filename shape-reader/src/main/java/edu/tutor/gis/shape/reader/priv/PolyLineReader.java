package edu.tutor.gis.shape.reader.priv;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import edu.tutor.gis.shape.BoundingBox;
import edu.tutor.gis.shape.Point;
import edu.tutor.gis.shape.PolyLinePart;
import edu.tutor.gis.shape.PolyLineShapeRecord;

public class PolyLineReader extends RecordReader
{

	private PolyLineReader(ShapeFileReader reader, ShapeRecordHeader recordHeader)
	{
		super(reader, recordHeader);
	}

	public static PolyLineShapeRecord readRecord(ShapeFileReader fileReader, ShapeRecordHeader recordHeader) throws IOException
	{
		PolyLineReader reader = new PolyLineReader(fileReader, recordHeader);

		return (reader.read());
	}

	private static final int FIXED_RECORD_LENGTH = 40;
	
	private PolyLineShapeRecord read() throws IOException
	{
		ByteBuffer buffer = reader.fetchByteBuffer(FIXED_RECORD_LENGTH, ByteOrder.LITTLE_ENDIAN);
		BoundingBox boundingBox = reader.readBoundingBox(buffer);
		int numParts = buffer.getInt();
		int numPoints = buffer.getInt();
		
		PolyLinePart[] parts = readParts(numParts, numPoints);
		
		return (new PolyLineShapeRecord(recordHeader, boundingBox, parts));
	}

	private PolyLinePart[] readParts(int numParts, int numPoints) throws IOException
	{
		// Read the array of part starting indices
		ByteBuffer buffer = reader.fetchByteBuffer((numParts * 4), ByteOrder.LITTLE_ENDIAN);
		int[] partOffsets = new int[numParts];
		PolyLinePart[] parts = new PolyLinePart[numParts];
		
		for (int index = 0; index < numParts; index++)
		{
			partOffsets[index] = buffer.getInt();
		}
		
		// Read the points for each part
		int curPoint = 0;
		for (int partIndex = 0; (partIndex < numParts) && (curPoint < numPoints); partIndex++)
		{
			int partLength = ((partIndex + 1) < numParts) ? (partOffsets[partIndex + 1] - curPoint) : (numPoints - curPoint);
			buffer = reader.fetchByteBuffer((partLength * ShapeFileReader.POINT_STRUCTURE_LENGTH), ByteOrder.LITTLE_ENDIAN);
			Point[] points = new Point[partLength];
			
			for (int pointIndex = 0; pointIndex < partLength; pointIndex++)
			{
				points[pointIndex] = reader.readPoint(buffer);
			}
			
			parts[partIndex] = new PolyLinePart(points);
		}

		return (parts);
	}

}
