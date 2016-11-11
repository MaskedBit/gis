package edu.tutor.gis.shape;

import java.io.PrintStream;

import edu.tutor.gis.shape.reader.priv.ShapeRecordHeader;

public class PolygonShapeRecord extends PolyShapeRecord
{

	public PolygonShapeRecord(ShapeRecordHeader header, BoundingBox boundingBox, PolyLinePart[] parts)
	{
		super(header, boundingBox, parts);
	}

	@Override
	public ShapeType getShapeType()
	{
		return (ShapeType.POLYGON);
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		
		out.format("%s PolygonShapeRecord:%n", indent);
		super.debugPrint(out, childIndent);
	}

}
