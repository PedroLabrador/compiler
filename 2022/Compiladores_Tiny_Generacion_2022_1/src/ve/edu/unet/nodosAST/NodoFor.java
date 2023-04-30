package ve.edu.unet.nodosAST;

public class NodoFor extends NodoBase {

	private NodoBase inicio;
	private NodoBase cuerpo;
	private NodoBase cond;
	private NodoBase incremento;

	public NodoFor(NodoBase inicio, NodoBase cuerpo, NodoBase cond, NodoBase incremento) {
		this.inicio = inicio;
		this.cuerpo = cuerpo;
		this.cond = cond;
		this.incremento = incremento;
	}

	public NodoFor(NodoBase inicio, NodoBase cuerpo, NodoBase incremento) {
		this.inicio = inicio;
		this.cuerpo = cuerpo;
		this.incremento = incremento;
	}

	public NodoBase getCond() {
		return cond;
	}

	public NodoBase getInicio() {
		return inicio;
	}

	public NodoBase getCuerpo() {
		return cuerpo;
	}

	public NodoBase getIncremento() {
		return incremento;
	}

}