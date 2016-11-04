package edu.tutor.gis.shape.reader;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ShapeTypeTest
{
    @Parameters(name = "{index}: ShapeType={0}, code={1}")
    public static Collection<Object[]> data()
    {
        return Arrays.asList(new Object[][]
                {
                    {ShapeType.NULL, 0},
                    {ShapeType.POINT, 1},
                    {ShapeType.POLYLINE, 3},
                    {ShapeType.POLYGON, 5},
                    {ShapeType.MULTI_POINT, 8},
                    {ShapeType.POINT_Z, 11},
                    {ShapeType.POLYLINE_Z, 13},
                    {ShapeType.POLYGON_Z, 15},
                    {ShapeType.MULTI_POINT_Z, 18},
                    {ShapeType.POINT_M, 21},
                    {ShapeType.POLYLINE_M, 23},
                    {ShapeType.POLYGON_M, 25},
                    {ShapeType.MULTI_POINT_M, 28},
                    {ShapeType.MULTI_PATCH, 31},
                    {ShapeType.UNKNOWN, -1}
                });
    }

	private ShapeType shapeType;
	private int code;

	public ShapeTypeTest(ShapeType shapeType, int code)
	{
		this.shapeType = shapeType;
		this.code = code;
	}

	@Test
	public void testGetCode()
	{
		int observed = shapeType.getCode();
		
		Assert.assertEquals(code, observed);
	}

	@Test
	public void testValueOf()
	{
		ShapeType observed = ShapeType.valueOf(code);
		
		Assert.assertEquals(shapeType, observed);
	}

}
