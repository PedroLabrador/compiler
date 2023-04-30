package ve.edu.unet.nodosAST;

public class NodoWhile extends NodoBase {

    private NodoBase cuerpo;
    private NodoBase prueba;

    public NodoWhile(NodoBase cuerpo, NodoBase prueba) {
        this.cuerpo = cuerpo;
        this.prueba = prueba;
    }

    public NodoBase getCuerpo() {
        return cuerpo;
    }

    public NodoBase getPrueba() {
        return prueba;
    }

}