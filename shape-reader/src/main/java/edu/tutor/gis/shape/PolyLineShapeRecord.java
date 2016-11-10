package edu.tutor.gis.shape;

import edu.tutor.gis.shape.reader.priv.ShapeRecordHeader;

public class PolyLineShapeRecord extends ShapeRecord
{
	private BoundingBox boundingBox;
	private PolyLinePart[] parts;

	public PolyLineShapeRecord(ShapeRecordHeader header, BoundingBox boundingBox, PolyLinePart[] parts)
	{
		super(header);
		
		this.boundingBox = boundingBox;
		this.parts = parts;
	}

	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	public PolyLinePart[] getParts() {
		return parts;
	}

	@Override
	public ShapeType getShapeType()
	{
		return (ShapeType.POLYLINE);
	}

}
