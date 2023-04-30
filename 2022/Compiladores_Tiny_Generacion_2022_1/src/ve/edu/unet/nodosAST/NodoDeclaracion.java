package ve.edu.unet.nodosAST;

public class NodoDeclaracion extends NodoBase {

	private String id;
	tipoVar tipo;
	private int tamano;
	private int linea;

	public NodoDeclaracion(String id, tipoVar tipo, int linea) {
		this.id = id;
		this.tipo = tipo;
		this.tamano = 1;
		this.linea = linea;
	}

	public NodoDeclaracion(String id, tipoVar tipo, int tamano, int linea) {
		this.id = id;
		this.tipo = tipo;
		this.tamano = tamano;
		this.linea = linea;
	}

	public int getLinea() {
		return linea;
	}

	public String getId() {
		return id;
	}

	public int getTamano() {
		return tamano;
	}

	public tipoVar getTipo() {
		return tipo;
	}

}