package edu.tutor.gis.dbf.reader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class DbfHeaderReader extends DbfFileReader
{
	private static final byte HEADER_TERMINATOR = 0x0D;

	private DbfHeader header;
	
	public DbfHeaderReader(SeekableByteChannel channel)
	{
		super(channel);

		this.header = new DbfHeader();
	}
	
	public static DbfHeader connect(Path filePath) throws IOException
	{
		try (SeekableByteChannel channel = Files.newByteChannel(filePath))
		{
			DbfHeaderReader reader = new DbfHeaderReader(channel);

			reader.readHeader();
			
			return (reader.header);
		}
	}
	
	/**
	 * Read the header data from the file.
	 * 
	 * @return
	 * @throws IOException
	 */
	private void readHeader() throws IOException
	{
		ByteBuffer buffer = fetchByteBuffer(32);
		
		byte versionByte = buffer.get();
		
		header.setVersion(DbfVersion.valueOf(versionByte & 0x07));
		
		if (header.getVersion() != DbfVersion.VERSION_3)
		{
			throw new UnsupportedVersionException("Version " + header.getVersion() + " is not supported");
		}

		header.setHasDosMemoFile(((versionByte & 0x08) == 0x08) ? true : false);
		header.setSqlFlags(versionByte & 0x70);
		header.setHasPlusMemoFile(((versionByte & 0x80) == 0x80) ? true : false);
		
		int year = ((int)buffer.get()) + 1900;
		int month = buffer.get();
		int day = buffer.get();
		
		header.setLastUpdate(LocalDate.of(year, month, day));

		int recordCount = buffer.getInt();
		
		header.setRecordCount(recordCount);
		
		int headerLength = buffer.getShort();
		
		header.setHeaderLength(headerLength);
		
		int recordLength = buffer.getShort();
		
		header.setRecordLength(recordLength);
		
		byte transactionFlag = buffer.get();
		
		header.setTransactionPending(transactionFlag != 0);
		
		byte encryptionFlag = buffer.get();
		
		header.setEncrypted(encryptionFlag != 0);
		
		// Skip 12 bytes
		buffer.position(28);
		
		byte mdxFlag = buffer.get();
		
		header.setHasMdx(mdxFlag != 0);
		
		byte languageDriverId = buffer.get();
		
		header.setLanguageDriverId(languageDriverId);
		
		// Note that we haven't read the whole buffer - there are 2 reserved bytes
		// at the end - but the file position has been advanced 32 bytes
		
		readFields();
		validateHeader();
	}

	private void readFields() throws IOException
	{
		while (readOffset < (header.getHeaderLength() - 1))
		{
			DbfField curField = readOneField();
			
			header.getFields().add(curField);
		}
	}

	private DbfField readOneField() throws IOException
	{
		DbfField field = new DbfField();
		ByteBuffer nameBuffer = fetchByteBuffer(11);
		ByteBuffer metadata = fetchByteBuffer(21);
		
		int numChars = 0;
		byte[] array = nameBuffer.array();
		
		while (array[numChars] != 0)
		{
			numChars++;
		}

		String name = new String(array, 0, numChars, Charset.forName("UTF-8" ));
		
		field.setName(name);
		
		char fieldType = (char) metadata.get();
		
		field.setType(DbfFieldType.valueOf(fieldType));
		
		// Skip 4 bytes
		metadata.position(5);

		byte length = metadata.get();
		
		field.setLength(length);
		
		byte decimalCount = metadata.get();
		
		field.setDecimalCount(decimalCount);
		
		int workAreaId = metadata.getShort();
		
		field.setWorkAreaId(workAreaId);
		
		byte example = metadata.get();
		
		field.setExample(example);
		
		// Skip 10 bytes
		metadata.position(20);
		
		byte mdxFlag = metadata.get();
		
		field.setHasIndexTag(mdxFlag != 0);

		return (field);
	}
	
	private void validateHeader() throws IOException
	{
		ByteBuffer buffer = fetchByteBuffer(1);		// Yes, one!  Need to read the "header end" flag byte.
		
		byte terminator = buffer.get();
		
		if (terminator != HEADER_TERMINATOR)
		{
			throw new IOException("Invalid .dbf file");
		}
	}

	@Override
	public void close()
	{
		;
	}

}
