package edu.tutor.gis.shape;

public enum ShapeType
{
	UNKNOWN(-1),
	NULL(0),
	POINT(1),
	POLYLINE(3),
	POLYGON(5),
	MULTI_POINT(8),
	POINT_Z(11),
	POLYLINE_Z(13),
	POLYGON_Z(15),
	MULTI_POINT_Z(18),
	POINT_M(21),
	POLYLINE_M(23),
	POLYGON_M(25),
	MULTI_POINT_M(28),
	MULTI_PATCH(31);

	private int code;
	
	ShapeType(int code)
	{
		this.code = code;
	}

	public int getCode()
	{
		return (code);
	}

	public static ShapeType valueOf(int code)
	{
		switch (code)
		{
		case 0:
			return (NULL);

		case 1:
			return (POINT);

		case 3:
			return (POLYLINE);

		case 5:
			return (POLYGON);

		case 8:
			return (MULTI_POINT);

		case 11:
			return (POINT_Z);

		case 13:
			return (POLYLINE_Z);

		case 15:
			return (POLYGON_Z);

		case 18:
			return (MULTI_POINT_Z);

		case 21:
			return (POINT_M);

		case 23:
			return (POLYLINE_M);

		case 25:
			return (POLYGON_M);

		case 28:
			return (MULTI_POINT_M);

		case 31:
			return (MULTI_PATCH);

		default:
			return (UNKNOWN);
		}
	}

}
