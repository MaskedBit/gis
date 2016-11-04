package edu.tutor.gis.shape;

import java.io.PrintStream;

public class BoundingBox
{
	private double minX = Double.NaN;
	private double maxX = Double.NaN;
	private double minY = Double.NaN;
	private double maxY = Double.NaN;

	public BoundingBox()
	{
		;
	}

	public BoundingBox(double minX, double maxX, double minY, double maxY)
	{
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}

	public double getMinX() {
		return minX;
	}

	public void setMinX(double minX) {
		this.minX = minX;
	}

	public double getMaxX() {
		return maxX;
	}

	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	public double getMinY() {
		return minY;
	}

	public void setMinY(double minY) {
		this.minY = minY;
	}

	public double getMaxY() {
		return maxY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		
		out.format("%s BoundingBox3D:%n", indent);
		out.format("%s minX=%f%n", childIndent, minX);
		out.format("%s maxX=%f%n", childIndent, maxX);
		out.format("%s minY=%f%n", childIndent, minY);
		out.format("%s maxY=%f%n", childIndent, maxY);
	}

}
