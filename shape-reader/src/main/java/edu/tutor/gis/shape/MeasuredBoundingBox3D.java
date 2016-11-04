package edu.tutor.gis.shape;

import java.io.PrintStream;

public class MeasuredBoundingBox3D extends BoundingBox3D
{
	private double minM = Double.NaN;
	private double maxM = Double.NaN;

	public MeasuredBoundingBox3D()
	{
		;
	}

	public MeasuredBoundingBox3D(double minX, double maxX, double minY, double maxY,
			double minZ, double maxZ, double minM, double maxM)
	{
		super(minX, maxX, minY, maxY, minZ, maxZ);

		this.minM = minM;
		this.maxM = maxM;
	}

	public double getMinM() {
		return minM;
	}

	public void setMinM(double minM) {
		this.minM = minM;
	}

	public double getMaxM() {
		return maxM;
	}

	public void setMaxM(double maxM) {
		this.maxM = maxM;
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		
		out.format("%s MeasuredBoundingBox3D:%n", indent);
		super.debugPrint(out, childIndent);
		out.format("%s minM=%f%n", childIndent, minM);
		out.format("%s maxM=%f%n", childIndent, maxM);
	}

}
