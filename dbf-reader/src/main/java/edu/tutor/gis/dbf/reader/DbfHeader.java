package edu.tutor.gis.dbf.reader;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbfHeader
{
	private DbfVersion version = DbfVersion.UNKNOWN;
	private boolean hasDosMemoFile = false;
	private int sqlFlags = -1;
	private boolean hasPlusMemoFile = false;
	private LocalDate lastUpdate = LocalDate.MAX;
	private int recordCount = -1;
	private int headerLength = -1;
	private int recordLength = -1;
	private boolean transactionPending = false;
	private boolean isEncrypted = false;
	private boolean hasMdx = false;
	private int languageDriverId = -1;
	private List<DbfField> fields = new ArrayList<DbfField>();
	
	public DbfHeader()
	{
		;
	}

	public DbfVersion getVersion() {
		return version;
	}

	public void setVersion(DbfVersion version) {
		this.version = version;
	}

	public boolean isHasDosMemoFile() {
		return hasDosMemoFile;
	}

	public void setHasDosMemoFile(boolean hasDosMemoFile) {
		this.hasDosMemoFile = hasDosMemoFile;
	}

	public int getSqlFlags() {
		return sqlFlags;
	}

	public void setSqlFlags(int sqlFlags) {
		this.sqlFlags = sqlFlags;
	}

	public boolean isHasPlusMemoFile() {
		return hasPlusMemoFile;
	}

	public void setHasPlusMemoFile(boolean hasPlusMemoFile) {
		this.hasPlusMemoFile = hasPlusMemoFile;
	}

	public LocalDate getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getHeaderLength() {
		return headerLength;
	}

	public void setHeaderLength(int headerLength) {
		this.headerLength = headerLength;
	}

	public int getRecordLength() {
		return recordLength;
	}

	public void setRecordLength(int recordLength) {
		this.recordLength = recordLength;
	}

	public boolean isTransactionPending() {
		return transactionPending;
	}

	public void setTransactionPending(boolean transactionPending) {
		this.transactionPending = transactionPending;
	}

	public boolean isEncrypted() {
		return isEncrypted;
	}

	public void setEncrypted(boolean isEncrypted) {
		this.isEncrypted = isEncrypted;
	}

	public boolean hasMdx() {
		return hasMdx;
	}

	public void setHasMdx(boolean hasMdx) {
		this.hasMdx = hasMdx;
	}

	public int getLanguageDriverId() {
		return languageDriverId;
	}

	public void setLanguageDriverId(int languageDriverId) {
		this.languageDriverId = languageDriverId;
	}

	public List<DbfField> getFields() {
		return fields;
	}

	public void setFields(List<DbfField> fields) {
		this.fields = fields;
	}

	public void debugPrint(PrintStream out, String indent)
	{
		String childIndent = indent + "  ";
		String grandChildIndent = childIndent + "  ";
		
		out.format("%s DbfHeader:%n", indent);
		out.format("%s version=%s%n", childIndent, version.toString());
		out.format("%s hasDosMemoFile=%s%n", childIndent, Boolean.toString(hasDosMemoFile));
		out.format("%s sqlFlags=%d%n", childIndent, sqlFlags);
		out.format("%s hasPlusMemoFile=%s%n", childIndent, Boolean.toString(hasPlusMemoFile));
		out.format("%s lastUpdate=%s%n", childIndent, lastUpdate.toString());
		out.format("%s recordCount=%d%n", childIndent, recordCount);
		out.format("%s headerLength=%d%n", childIndent, headerLength);
		out.format("%s recordLength=%d%n", childIndent, recordLength);
		out.format("%s transactionPending=%s%n", childIndent, Boolean.toString(transactionPending));
		out.format("%s isEncrypted=%s%n", childIndent, Boolean.toString(isEncrypted));
		out.format("%s hasMdx=%s%n", childIndent, Boolean.toString(hasMdx));
		out.format("%s languageDriverId=%d%n", childIndent, languageDriverId);
		out.format("%s fields:%n", childIndent);
		int index = 0;
		for (DbfField curField : fields)
		{
			out.format("%s [%d]:%n", grandChildIndent, index++);
			curField.debugPrint(out, (grandChildIndent + "  "));
		}
	}

}
