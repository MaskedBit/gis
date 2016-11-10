package edu.tutor.gis.shape;

import java.io.PrintStream;

import edu.tutor.gis.shape.reader.priv.ShapeRecordHeader;

public class NullShapeRecord extends ShapeRecord
{

	public NullShapeRecord(ShapeRecordHeader header)
	{
		super(header);
	}

	@Override
	public ShapeType getShapeType()
	{
		return (ShapeType.NULL);
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		
		out.format("%s NullShapeRecord:%n", indent);
		super.debugPrint(out, childIndent);
	}

}
