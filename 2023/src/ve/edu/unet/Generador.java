package ve.edu.unet;

import ve.edu.unet.nodosAST.*;

public class Generador {

	private static TablaSimbolos tablaSimbolos = null;

	public static void setTablaSimbolos(TablaSimbolos tabla) {
		tablaSimbolos = tabla;
	}

	public static void generarCodigoObjeto(NodoBase raiz) {
		System.out.println();
		System.out.println();
		System.out.println("------ CODIGO P ------");
		System.out.println();
		System.out.println();
		generar(raiz);
		UtPGen.emitir("STP", "Fin del codigo");
		System.out.println();
		System.out.println();
		System.out.println("------ FIN DEL CODIGO P ------");
	}

	private static void generar(NodoBase nodo) {
		if (tablaSimbolos != null) {
			if (nodo instanceof NodoIf) {
				generarIf(nodo);
			} else if (nodo instanceof NodoRepeat) {
				generarRepeat(nodo);
			} else if (nodo instanceof NodoAsignacion) {
				generarAsignacion(nodo);
			} else if (nodo instanceof NodoLeer) {
				generarLeer(nodo);
			} else if (nodo instanceof NodoEscribir) {
				generarEscribir(nodo);
			} else if (nodo instanceof NodoValor) {
				generarValor(nodo);
			} else if (nodo instanceof NodoIdentificador) {
				generarIdentificador(nodo);
			} else if (nodo instanceof NodoOperacion) {
				generarOperacion(nodo);
			} else if (nodo instanceof NodoDeclaracion) {
				generarDeclaracion(nodo);
			} else {
				System.out.println("BUG: Tipo de nodo a generar desconocido");
			}
			/*
			 * Si el hijo de extrema izquierda tiene hermano a la derecha lo genero tambien
			 */
			if (nodo.TieneHermano())
				generar(nodo.getHermanoDerecha());
		} else
			System.out
					.println("���ERROR: por favor fije la tabla de simbolos a usar antes de generar codigo objeto!!!");
	}

	private static void generarIf(NodoBase nodo) {
		NodoIf n = (NodoIf) nodo;

		int labelElse = UtPGen.getLabel();
		int labelEnd = UtPGen.getLabel();
		int labelSalto = n.getParteElse() != null ? labelElse : labelEnd;

		generar(n.getPrueba());
		UtPGen.emitir("FJP L" + labelSalto, "Si es falso, salta al label L" + labelSalto);
		generar(n.getParteThen());

		if (n.getParteElse() != null) {
			UtPGen.emitir("JMP L" + labelEnd, " al terminar va al final del if");
			UtPGen.emitir("LAB L" + labelElse, null);
			generar(n.getParteElse());
		}
		UtPGen.emitir("LAB L" + labelEnd, null);

	}

	private static void generarRepeat(NodoBase nodo) {
		NodoRepeat n = (NodoRepeat) nodo;
		int labelInicio = UtPGen.getLabel();
		UtPGen.emitir("LAB L" + labelInicio, "Label de inicio del repeat");
		generar(n.getCuerpo());
		generar(n.getPrueba());
		UtPGen.emitir("FJP L" + labelInicio, "Saltar al label L" + labelInicio + " si es false");

	}

	private static void generarAsignacion(NodoBase nodo) {
		NodoAsignacion n = (NodoAsignacion) nodo;
		UtPGen.emitir("LDA " + n.getIdentificador(), " Asignar");
		if (n.getIndice() != null) {
			generar(n.getIndice());
			UtPGen.emitir("ADI", "Calculo nuevo indice ");
		}
		generar(n.getExpresion());
		UtPGen.emitir("STO", "Guardar");

	}

	private static void generarDeclaracion(NodoBase nodo) {
		NodoDeclaracion n = (NodoDeclaracion) nodo;

		UtPGen.emitir("LDC", n.getTamano(), "declaracion test"); // tamaño de variable
		UtPGen.emitir("MEM " + n.getIdentificador(), "reservar memoria a la variable"); // nombre de la variable
		if (n.getExpresion() != null) {
			UtPGen.emitir("LDA " + n.getIdentificador(), " Asignar");
			generar(n.getExpresion());
			UtPGen.emitir("STO", "Guardar");
		}

	}

	private static void generarLeer(NodoBase nodo) {
		NodoLeer n = (NodoLeer) nodo;
		UtPGen.emitir("LDA " + n.getIdentificador(), "Cargar direccion de variable");
		UtPGen.emitir("RDI ", "Leer de consola");
		UtPGen.emitir("STO", "Guardar");
	}

	private static void generarEscribir(NodoBase nodo) {
		NodoEscribir n = (NodoEscribir) nodo;
		generar(n.getExpresion());
		UtPGen.emitir("WRI", "print top of stack");
	}

	private static void generarValor(NodoBase nodo) {
		NodoValor n = (NodoValor) nodo;
		UtPGen.emitir("LDC", n.getValor(), "cargar constante: " + n.getValor());
	}

	private static void generarIdentificador(NodoBase nodo) {
		NodoIdentificador n = (NodoIdentificador) nodo;
		if (n.getIndice() != null) {
			UtPGen.emitir("LDA " + n.getNombre(), "cargar direccion base de " + n.getNombre());
			generar(n.getIndice());
			UtPGen.emitir("ADI", "calcular direcciones");
			UtPGen.emitir("LDM", "cargar valor en memoria");
		} else {
			UtPGen.emitir("LOD " + n.getNombre(), "cargar valor de " + n.getNombre());
		}

	}

	private static void generarOperacion(NodoBase nodo) {
		NodoOperacion n = (NodoOperacion) nodo;

		generar(n.getOpIzquierdo());

		generar(n.getOpDerecho());

		switch (n.getOperacion()) {
		case mas:
			UtPGen.emitir("ADI", "op: +");
			break;
		case menos:
			UtPGen.emitir("SBI", "op: -");
			break;
		case por:
			UtPGen.emitir("MPI", "op: *");
			break;
		case entre:
			UtPGen.emitir("DIV", "op: /");
			break;
		case menor:
			UtPGen.emitir("LST", "op: <");
			break;
		case mayor:
			UtPGen.emitir("GRT", "op: >");
			break;
		case igual:
			UtPGen.emitir("EQU", "op: ==");
			break;
		case mayorigual:
			UtPGen.emitir("GTE", "op: >=");
			break;
		case menorigual:
			UtPGen.emitir("LTE", "op: <=");
			break;
		case diferente:
			UtPGen.emitir("NEQ", "op: <>");
			break;
		case and:
			UtPGen.emitir("AND", "op: and");
			break;
		case or:
			UtPGen.emitir("OR", "op: or");
			break;
		default:
			UtPGen.emitirComentario("BUG: tipo de operacion desconocida");
		}
		if (UtPGen.debug)
			UtGen.emitirComentario("<- Operacion: " + n.getOperacion());
	}

}
