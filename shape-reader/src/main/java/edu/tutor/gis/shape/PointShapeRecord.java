package edu.tutor.gis.shape;

import edu.tutor.gis.shape.reader.priv.ShapeRecordHeader;

public class PointShapeRecord extends ShapeRecord
{
	private Point point;

	public PointShapeRecord(ShapeRecordHeader header, Point point)
	{
		super(header);
		
		this.point = point;
	}

	public PointShapeRecord(ShapeRecordHeader header, double x, double y)
	{
		super(header);
		
		this.point = new Point(x, y);
	}

	public Point getPoint() {
		return point;
	}

	@Override
	public ShapeType getShapeType()
	{
		return (ShapeType.POINT);
	}

}
