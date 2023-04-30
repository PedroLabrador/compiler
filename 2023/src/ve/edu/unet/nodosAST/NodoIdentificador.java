package ve.edu.unet.nodosAST;

public class NodoIdentificador extends NodoBase {
	private String nombre;
	private NodoBase indice;

	public NodoIdentificador(String nombre) {
		super();
		this.nombre = nombre;
		this.indice = null;
	}

	public NodoIdentificador(String nombre, NodoBase indice) {
		super();
		this.nombre = nombre;
		this.indice = indice;
	}

	public NodoIdentificador() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public NodoBase getIndice() {
		return indice;
	}

}
