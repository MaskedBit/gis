package edu.tutor.gis.shape;

import edu.tutor.gis.shape.reader.priv.ShapeRecordHeader;

public class MultiPointShapeRecord extends ShapeRecord
{
	private BoundingBox boundingBox;
	private int numPoints;
	private Point[] pointArray;

	public MultiPointShapeRecord(ShapeRecordHeader header, BoundingBox boundingBox, int numPoints, Point[] pointArray)
	{
		super(header);
		
		this.boundingBox = boundingBox;
		this.numPoints = numPoints;
		this.pointArray = pointArray;
	}

	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	public int getNumPoints() {
		return numPoints;
	}

	public Point[] getPointArray() {
		return pointArray;
	}

	@Override
	public ShapeType getShapeType()
	{
		return (ShapeType.MULTI_POINT);
	}

}
