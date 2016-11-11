package edu.tutor.gis.shape.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import edu.tutor.gis.shape.ShapeHeader;
import edu.tutor.gis.shape.ShapeRecord;
import edu.tutor.gis.shape.reader.priv.ShapeHeaderReader;
import edu.tutor.gis.shape.reader.priv.ShapeStreamReader;

public class ShapeReader implements AutoCloseable, Iterable<ShapeRecord>
{
	private Path shapePath;
	private Path indexPath;
	private ShapeHeader header;

	public ShapeReader(Path shapePath, Path indexPath, ShapeHeader header)
	{
		this.shapePath = shapePath;
		this.indexPath = indexPath;
		this.header = header;
	}

	public Path getShapePath()
	{
		return (shapePath);
	}

	public Path getIndexPath()
	{
		return (indexPath);
	}

	public ShapeHeader getHeader()
	{
		return header;
	}

	public static ShapeReader connect(String dbPath) throws IOException
	{
		Path filePath = Paths.get(dbPath);
		Path shapePath = filePath.resolveSibling(filePath.getFileName() + ".shp");
		Path indexPath = filePath.resolveSibling(filePath.getFileName() + ".shx");

		ShapeHeader header = ShapeHeaderReader.connect(shapePath);

		return (new ShapeReader(shapePath, indexPath, header));
	}

	@Override
	public void close() throws IOException
	{
		;
	}

	@Override
	public Iterator<ShapeRecord> iterator()
	{
		try
		{
			return (ShapeStreamReader.recordIterator(shapePath, header));
		}
		catch (IOException e)
		{
			throw new RuntimeException("I/O error reading file", e);
		}
	}

}
