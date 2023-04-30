package ve.edu.unet.nodosAST;

public class NodoDeclaracion extends NodoBase {
	private String identificador;
	private NodoBase expresion;
	private tipoVariable tipo;
	private int tamano;

	public NodoDeclaracion(String identificador, tipoVariable tipo) {
		super();
		this.identificador = identificador;
		this.tipo = tipo;
		this.expresion = null;
		this.tamano = 1;
	}

	public NodoDeclaracion(String identificador, tipoVariable tipo, NodoBase expresion) {
		super();
		this.identificador = identificador;
		this.tipo = tipo;
		this.expresion = expresion;
		this.tamano = 1;
	}

	public NodoDeclaracion(String identificador, tipoVariable tipo, String tamano) {
		super();
		this.identificador = identificador;
		this.tipo = tipo;
		this.expresion = null;
		this.tamano = Integer.parseInt(tamano);
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public NodoBase getExpresion() {
		return expresion;
	}

	public void setExpresion(NodoBase expresion) {
		this.expresion = expresion;
	}

	public void setTipo(tipoVariable tipo) {
		this.tipo = tipo;
	}

	public tipoVariable getTipo() {
		return tipo;
	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}	

}
