package edu.tutor.gis.dbf.reader;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class DbfRecord
{
	private boolean isDeleted = false;
	private List<DbfColumn> columns = new ArrayList<DbfColumn>();
	
	public DbfRecord(boolean isDeleted)
	{
		this.isDeleted = isDeleted;
	}

	public boolean isDeleted()
	{
		return isDeleted;
	}

	public List<DbfColumn> getColumns()
	{
		return columns;
	}

	public static DbfRecord construct(List<DbfField> fields, ByteBuffer buffer)
	{
		DbfRecord record = new DbfRecord(getIsDeleted(buffer));
		
		for (DbfField curField : fields)
		{
			record.columns.add(DbfColumn.construct(curField, buffer));
		}

		return (record);
	}

	private static boolean getIsDeleted(ByteBuffer buffer)
	{
		char deletedFlag = (char)buffer.get();
		
		if (deletedFlag != ' ')
		{
			return (true);
		}
		else
		{
			return (false);
		}
	}

	
}
