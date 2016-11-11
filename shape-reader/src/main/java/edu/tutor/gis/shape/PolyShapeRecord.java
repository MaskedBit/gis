package edu.tutor.gis.shape;

import java.io.PrintStream;

import edu.tutor.gis.shape.reader.priv.ShapeRecordHeader;

public abstract class PolyShapeRecord extends ShapeRecord
{
	private BoundingBox boundingBox;
	private PolyLinePart[] parts;

	public PolyShapeRecord(ShapeRecordHeader header, BoundingBox boundingBox, PolyLinePart[] parts)
	{
		super(header);
		
		this.boundingBox = boundingBox;
		this.parts = parts;
	}

	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	public PolyLinePart[] getParts() {
		return parts;
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		String grandChildIndent = childIndent + "  ";
		
		out.format("%s PolyShapeRecord:%n", indent);
		super.debugPrint(out, childIndent);
		out.format("%s boundingBox:%n", childIndent);
		boundingBox.debugPrint(out, grandChildIndent);
		for (int index = 0; index < parts.length; index++)
		{
			out.format("%s parts[%d]:%n", childIndent, index);
			parts[index].debugPrint(out, grandChildIndent);
		}
	}

}
