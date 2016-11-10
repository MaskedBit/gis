package edu.tutor.gis.shape;

import java.io.PrintStream;

public class Point
{
	private double x = Double.NaN;

	private double y = Double.NaN;
	
	public Point()
	{
		;
	}
	
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";

		out.format("%s Point:%n", indent);
		out.format("%s x=%f%n", childIndent, x);
		out.format("%s y=%f%n", childIndent, y);
	}

}
