package edu.tutor.gis.shape.reader.priv;

public abstract class RecordReader
{
	protected ShapeFileReader reader;
	protected ShapeRecordHeader recordHeader;

	public RecordReader(ShapeFileReader reader, ShapeRecordHeader recordHeader)
	{
		this.reader = reader;
		this.recordHeader = recordHeader;
	}

}
