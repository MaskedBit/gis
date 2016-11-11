package edu.tutor.gis.shape;

import java.io.PrintStream;

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

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		String grandChildIndent = childIndent + "  ";

		out.format("%s PointShapeRecord:%n", indent);
		super.debugPrint(out, childIndent);
		out.format("%s point:%n", childIndent);
		point.debugPrint(out, grandChildIndent);
	}

}
