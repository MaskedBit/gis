package edu.tutor.gis.shape;

import edu.tutor.gis.shape.reader.priv.ShapeRecordHeader;

public class NullShapeRecord extends ShapeRecord
{

	public NullShapeRecord(ShapeRecordHeader header)
	{
		super(header);
	}

	@Override
	public ShapeType getShapeType()
	{
		return (ShapeType.NULL);
	}

}
