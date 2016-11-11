package edu.tutor.gis.shape;

import java.io.PrintStream;

public class PolyLinePart
{
	private Point[] points;
	
	public PolyLinePart(Point[] points)
	{
		this.points = points;
	}

	public Point[] getPoints() {
		return points;
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		
		out.format("%s PolyShapeRecord:%n", indent);
		for (int index = 0; index < points.length; index++)
		{
			out.format("%s points[%d]:  x=%f, y=%f%n", childIndent, index, points[index].getX(), points[index].getY());
		}
	}

}
