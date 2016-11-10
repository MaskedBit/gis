package edu.tutor.gis.shape;

import java.io.PrintStream;

import edu.tutor.gis.shape.reader.priv.ShapeRecordHeader;

public abstract class ShapeRecord
{
	private ShapeRecordHeader header;
	
	protected ShapeRecord(ShapeRecordHeader header)
	{
		this.header = header;
	}

	public int getRecordNumber()
	{
		return header.getRecordNumber();
	}

	public long getContentLength()
	{
		return header.getContentLength();
	}

	public abstract ShapeType getShapeType();

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		
		out.format("%s ShapeRecord:%n", indent);
		header.debugPrint(out, childIndent);
	}

}
