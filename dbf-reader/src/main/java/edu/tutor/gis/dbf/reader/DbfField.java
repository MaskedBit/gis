package edu.tutor.gis.dbf.reader;

import java.io.PrintStream;

public class DbfField
{
	private String name;
	private DbfFieldType type = DbfFieldType.UNKNOWN;
	private int length = -1;
	private int decimalCount = -1;
	private int workAreaId = -1;
	private int example = -1;
	private boolean hasIndexTag = false;
	
	public DbfField()
	{
		;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DbfFieldType getType() {
		return type;
	}

	public void setType(DbfFieldType type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDecimalCount() {
		return decimalCount;
	}

	public void setDecimalCount(int decimalCount) {
		this.decimalCount = decimalCount;
	}

	public int getWorkAreaId() {
		return workAreaId;
	}

	public void setWorkAreaId(int workAreaId) {
		this.workAreaId = workAreaId;
	}

	public int getExample() {
		return example;
	}

	public void setExample(int example) {
		this.example = example;
	}

	public boolean isHasIndexTag() {
		return hasIndexTag;
	}

	public void setHasIndexTag(boolean hasIndexTag) {
		this.hasIndexTag = hasIndexTag;
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";

		out.format("%s DbfField:%n", indent);
		out.format("%s name=%s%n", childIndent, name);
		out.format("%s type=%s%n", childIndent, type.toString());
		out.format("%s length=%d%n", childIndent, length);
		out.format("%s decimalCount=%d%n", childIndent, decimalCount);
		out.format("%s workAreaId=%d%n", childIndent, workAreaId);
		out.format("%s example=%d%n", childIndent, example);
		out.format("%s hasIndexTag=%s%n", childIndent, Boolean.toString(hasIndexTag));
	}

}
