package edu.tutor.gis.shape;

import java.io.PrintStream;

import edu.tutor.gis.shape.reader.priv.ShapeRecordHeader;

public class MultiPointShapeRecord extends ShapeRecord
{
	private BoundingBox boundingBox;
	private Point[] points;

	public MultiPointShapeRecord(ShapeRecordHeader header, BoundingBox boundingBox, Point[] points)
	{
		super(header);
		
		this.boundingBox = boundingBox;
		this.points = points;
	}

	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	public Point[] getPointArray() {
		return points;
	}

	@Override
	public ShapeType getShapeType()
	{
		return (ShapeType.MULTI_POINT);
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		String grandChildIndent = childIndent + "  ";
		
		out.format("%s MultiPointShapeRecord:%n", indent);
		out.format("%s boundingBox:%n", childIndent);
		boundingBox.debugPrint(out, grandChildIndent);
		for (int index = 0; index < points.length; index++)
		{
			out.format("%s points[%i]:  x=%f, y=%f%n", childIndent, index, points[index].getX(), points[index].getY());
		}
	}

}
