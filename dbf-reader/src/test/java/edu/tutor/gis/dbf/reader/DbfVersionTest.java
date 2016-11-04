package edu.tutor.gis.dbf.reader;

import org.junit.Assert;
import org.junit.Test;

import edu.tutor.gis.dbf.reader.DbfVersion;

public class DbfVersionTest
{

	@Test
	public void testVersion3()
	{
		DbfVersion expected = DbfVersion.VERSION_3;
		DbfVersion observed = DbfVersion.valueOf(3);
		
		Assert.assertEquals(expected, observed);
	}

	@Test
	public void testVersion7()
	{
		DbfVersion expected = DbfVersion.VERSION_7;
		DbfVersion observed = DbfVersion.valueOf(7);
		
		Assert.assertEquals(expected, observed);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testVersion5()
	{
		DbfVersion observed = DbfVersion.valueOf(5);
		
		Assert.fail("Did not yield expected exception");
	}

}
