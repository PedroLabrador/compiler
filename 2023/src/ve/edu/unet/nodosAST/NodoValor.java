package ve.edu.unet.nodosAST;

public class NodoValor extends NodoBase {
	private Integer valor;

	public NodoValor(Integer valor) {
		super();
		this.valor = valor;
	}

	public NodoValor() {
		super();
	}
	
	public Integer getValor() {
		return valor;
	}

}
