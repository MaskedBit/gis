package edu.tutor.gis.shape.reader.priv;

import edu.tutor.gis.shape.ShapeType;

public class ShapeRecordHeader
{
	public static final int INVALID_RECORD_NUMBER = -1;
	public static final int INVALID_CONTENT_LENGTH = -1;

	private int recordNumber = INVALID_RECORD_NUMBER;
	private int contentLength = INVALID_CONTENT_LENGTH;
	private ShapeType shapeType = ShapeType.UNKNOWN;
	
	public ShapeRecordHeader(int recordNumber, int contentLength, ShapeType shapeType)
	{
		this.recordNumber = recordNumber;
		this.contentLength = contentLength;
		this.shapeType = shapeType;
	}

	public int getRecordNumber()
	{
		return recordNumber;
	}

	public long getContentLength()
	{
		return contentLength;
	}

	public ShapeType getShapeType()
	{
		return shapeType;
	}

}

