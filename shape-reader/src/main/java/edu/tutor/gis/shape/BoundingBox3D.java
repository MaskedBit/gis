package edu.tutor.gis.shape;

import java.io.PrintStream;

public class BoundingBox3D extends BoundingBox
{
	private double minZ = Double.NaN;
	private double maxZ = Double.NaN;

	public BoundingBox3D()
	{
		;
	}

	public BoundingBox3D(double minX, double maxX, double minY, double maxY, double minZ, double maxZ)
	{
		super(minX, maxX, minY, maxY);
		
		this.minZ = minZ;
		this.maxZ = maxZ;
	}

	public double getMinZ() {
		return minZ;
	}

	public void setMinZ(double minZ) {
		this.minZ = minZ;
	}

	public double getMaxZ() {
		return maxZ;
	}

	public void setMaxZ(double maxZ) {
		this.maxZ = maxZ;
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		
		out.format("%s BoundingBox3D:%n", indent);
		super.debugPrint(out, childIndent);
		out.format("%s minZ=%f%n", childIndent, minZ);
		out.format("%s maxZ=%f%n", childIndent, maxZ);
	}

}
