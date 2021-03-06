package edu.tutor.gis.shape;

import java.io.PrintStream;

import edu.tutor.gis.shape.reader.priv.ShapeRecordHeader;

public class PolyLineShapeRecord extends PolyShapeRecord
{

	public PolyLineShapeRecord(ShapeRecordHeader header, BoundingBox boundingBox, PolyLinePart[] parts)
	{
		super(header, boundingBox, parts);
	}

	@Override
	public ShapeType getShapeType()
	{
		return (ShapeType.POLYLINE);
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		
		out.format("%s PolyLineShapeRecord:%n", indent);
		super.debugPrint(out, childIndent);
	}

}
