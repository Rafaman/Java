package IO;

public class IO {
	private static java.util.Scanner keyboard = new java.util.Scanner(System.in);

	public static String readString(String prompt)
	{	print(prompt);
		String v = keyboard.nextLine();
		return v;
	}

	public static int readInt(String prompt)
	{	print(prompt);
		int v = keyboard.nextInt();
		keyboard.nextLine();
		return v;
	}

	public static long readLong(String prompt)
	{	print(prompt);
		long v = keyboard.nextLong();
		keyboard.nextLine();
		return v;
	}

	public static short readShort(String prompt)
	{	print(prompt);
		short v = keyboard.nextShort();
		keyboard.nextLine();
		return v;
	}

	public static byte readByte(String prompt)
	{	print(prompt);
		byte v = keyboard.nextByte();
		keyboard.nextLine();
		return v;
	}

	public static float readFloat(String prompt)
	{	print(prompt);
		float v = keyboard.nextFloat();
		return v;
	}

	public static double readDouble(String prompt)
	{	print(prompt);
		double v = keyboard.nextDouble();
		keyboard.nextLine();
		return v;
	}

	public static void println()
	{	System.out.println();
	}

	public static void print(Object s)
	{	System.out.print(s);
	}

	public static void println(Object s)
	{	System.out.println(s);
	}

	public static void print(int s)
	{	System.out.print(s);
	}

	public static void println(int s)
	{	System.out.println(s);
	}

	public static void print(long s)
	{	System.out.print(s);
	}

	public static void println(long s)
	{	System.out.println(s);
	}

	public static void print(short s)
	{	System.out.print(s);
	}

	public static void println(short s)
	{	System.out.println(s);
	}

	public static void print(byte s)
	{	System.out.print(s);
	}

	public static void println(byte s)
	{	System.out.println(s);
	}

	public static void print(float s)
	{	System.out.print(s);
	}

	public static void println(float s)
	{	System.out.println(s);
	}

	public static void print(double s)
	{	System.out.print(s);
	}

	public static void println(double s)
	{	System.out.println(s);
	}

	public static void print(char s)
	{	System.out.print(s);
	}

	public static void println(char s)
	{	System.out.println(s);
	}

	public static void print(boolean s)
	{	System.out.print(s);
	}

	public static void println(boolean s)
	{	System.out.println(s);
	}
}

