package edu.tutor.gis.shape;

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

}
