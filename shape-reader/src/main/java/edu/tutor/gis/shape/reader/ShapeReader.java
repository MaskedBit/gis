package edu.tutor.gis.shape.reader;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import edu.tutor.gis.shape.reader.priv.ShapeHeaderReader;

public class ShapeReader implements Closeable
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

	public ShapeHeader getHeader()
	{
		return header;
	}

	public static ShapeReader connect(String dbPath) throws IOException
	{
		Path filePath = Paths.get(dbPath);
		Path shapePath = filePath.resolveSibling(filePath.getFileName() + ".shp");
		Path indexPath = filePath.resolveSibling(filePath.getFileName() + ".shx");

		ShapeHeader header = ShapeHeaderReader.connect(filePath);

		return (new ShapeReader(shapePath, indexPath, header));
	}

	@Override
	public void close() throws IOException
	{
		// TODO Auto-generated method stub

	}

}
