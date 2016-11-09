package edu.tutor.gis.shape;

import java.io.PrintStream;

public class ShapeHeader
{
	public static final int CURRENT_ESRI_VERSION = 1000;

	private int fileLength;
	private int version;
	private ShapeType shapeType;
	private MeasuredBoundingBox3D boundingBox;

	public ShapeHeader()
	{
		;
	}

	public int getFileLength() {
		return fileLength;
	}

	public void setFileLength(int fileLength) {
		this.fileLength = fileLength;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public ShapeType getShapeType() {
		return shapeType;
	}

	public void setShapeType(ShapeType shapeType) {
		this.shapeType = shapeType;
	}

	public MeasuredBoundingBox3D getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(MeasuredBoundingBox3D boundingBox) {
		this.boundingBox = boundingBox;
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		String grandChildIndent = childIndent + "  ";
		
		out.format("%s ShapeHeader:%n", indent);
		out.format("%s version=%d%n", childIndent, version);
		out.format("%s shapeType=%s%n", childIndent, shapeType.toString());
		out.format("%s boundingBox:%n", childIndent);
		boundingBox.debugPrint(out, grandChildIndent);
	}

}
