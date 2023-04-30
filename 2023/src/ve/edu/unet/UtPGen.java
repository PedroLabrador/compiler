package ve.edu.unet;

public class UtPGen {

	private static int label = 0;

	public static boolean debug = false;

	public static void emitirComentario(String c) {
		if (debug)
			System.out.println("*      " + c);
	}

	public static int getLabel() {
		return label++;
	}

	public static void emitir(String op, int r, String c) {
		System.out.print(op + " " + r);
		if (debug)
			System.out.print("   " + c);
		System.out.print("\n");

	}

	public static void emitir(String op, String c) {
		System.out.print(op);
		if (debug)
			System.out.print("   " + c);
		System.out.print("\n");

	}

}
