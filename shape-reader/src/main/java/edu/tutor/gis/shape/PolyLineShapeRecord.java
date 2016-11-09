package edu.tutor.gis.shape;

import edu.tutor.gis.shape.reader.priv.ShapeRecordHeader;

public class PolyLineShapeRecord extends ShapeRecord
{
	private double x;
	private double y;

	public PolyLineShapeRecord(ShapeRecordHeader header, double x, double y)
	{
		super(header);
		
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public ShapeType getShapeType()
	{
		return (ShapeType.POINT);
	}

}
