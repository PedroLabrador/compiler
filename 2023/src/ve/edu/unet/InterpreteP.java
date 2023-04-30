package ve.edu.unet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStreamReader;

public class InterpreteP {

	public static void main(String[] args) {
		boolean debug = false;
		if (args.length != 1) {
			System.out.println("Falta la ruta del archivo");
			return;
		}

		String filePath = args[0];
		Stack<Long> pila = new Stack<Long>();
		long[] memoria = new long[256];

		List<String> simbolos = new ArrayList<String>();
		Map<String, Integer> labels = new HashMap<>();

		List<String> code = new ArrayList<>();
		HashMap<String, Integer> variables = new HashMap<String, Integer>();

		int direccionMemoria = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String linea;
			int lineNumber = 0;
			while ((linea = br.readLine()) != null) {
				String[] tokens = linea.split("\\s+");
				String opcode = tokens[0];

				if (opcode.equals("LAB")) {
					labels.put(tokens[1], lineNumber);
				}

				code.add(linea);
				lineNumber++;
			}
		} catch (Exception e) {
			System.out.println("error leyendo linea");
		}

		int pc = 0;
		while (true) {
			String[] tokens = code.get(pc).split("\\s+");
			String opcode = tokens[0];

			if (debug) {
				System.out.println("OPERACION: " + opcode);
				System.out.println("PILA: " + pila.toString());
				System.out.println("MEMORIA: " + variables.toString());
			}

			if (opcode.equals("LDC")) {
				long operando = Long.parseLong(tokens[1]);
				pila.push(operando);
				if (debug)
					System.out.println("\tMeter variable en la pila: " + operando);
			} else if (opcode.equals("ADI")) {
				long sumando2 = pila.pop();
				long sumando1 = pila.pop();
				pila.push(sumando1 + sumando2);
				if (debug)
					System.out.println("\tMeter suma en la pila: " + (sumando2 + sumando1));
			} else if (opcode.equals("SBI")) {
				long sumando2 = pila.pop();
				long sumando1 = pila.pop();
				pila.push(sumando1 - sumando2);
				if (debug)
					System.out.println("\tMeter suma en la pila: " + (sumando2 + sumando1));
			} else if (opcode.equals("DIV")) {
				long divisor = pila.pop();
				long dividendo = pila.pop();
				pila.push(dividendo / divisor);
				if (debug)
					System.out.println("\tMeter division en la pila: " + (dividendo / divisor));
			} else if (opcode.equals("MEM")) {
				long tamano = pila.pop();
				String nombreVar = tokens[1];
				long direccionVar = direccionMemoria;
				direccionMemoria += tamano;
				variables.put(nombreVar, (int) direccionVar);
				if (debug)
					System.out.println("\tPop de la pila con el tamaño y se guarda la variable: " + nombreVar);

			} else if (opcode.equals("LDA")) {
				String nombreVar = tokens[1];
				long direccionVar = variables.getOrDefault(nombreVar, -1);
				if (direccionVar == -1) {
					System.out.println("La variable debe haber sido declarada antes");
					break;
				}
				pila.push(direccionVar);

				if (debug)
					System.out.println(
							"\tGuardar direccion de variable en la pila: " + nombreVar + "  " + direccionMemoria);
			} else if (opcode.equals("STO")) {
				long valor = pila.pop();
				long direccionVar = pila.pop();
				memoria[(int) direccionVar] = valor;

			} else if (opcode.equals("LOD")) {
				String nombreVar = tokens[1];
				int direccionVar = variables.get(nombreVar);
				long valor = memoria[direccionVar];
				pila.push(valor);
				if (debug)
					System.out.println("\tGuardar valor de la variable en la pila: " + nombreVar + "  ");
			} else if (opcode.equals("MPI")) {
				long factor2 = pila.pop();
				long factor1 = pila.pop();
				pila.push(factor1 * factor2);
				if (debug)
					System.out.println("\tGuardar valor de la variable en la pila: " + (factor1 * factor2));
			} else if (opcode.equals("WRI")) {
				if (!pila.empty()) {
					long valor = pila.pop();
					System.out.println("::= " + valor);
				}
			} else if (opcode.equals("RDI")) {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					String valor;
					System.out.println("ingrese un valor: ");
					valor = reader.readLine();
					long operando = Long.parseLong(valor);
					pila.push(operando);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (opcode.equals("JMP")) {
				int labelIndex = labels.get(tokens[1]);
				pc = labelIndex;

				if (debug)
					System.out.println("Salto a LABEL: " + (tokens[1]));
				continue;
			} else if (opcode.equals("EQU")) {
				long factor2 = pila.pop();
				long factor1 = pila.pop();
				if (factor1 == factor2) {
					pila.push(1L);
				} else {
					pila.push(0L);
				}
			} else if (opcode.equals("NEQ")) {
				long factor2 = pila.pop();
				long factor1 = pila.pop();
				if (factor1 != factor2) {
					pila.push(1L);
				} else {
					pila.push(0L);
				}
			} else if (opcode.equals("AND")) {
				long factor2 = pila.pop();
				long factor1 = pila.pop();
				if (factor1 == 1L && factor2 == 1L) {
					pila.push(1L);
				} else {
					pila.push(0L);
				}
			} else if (opcode.equals("OR")) {
				long factor2 = pila.pop();
				long factor1 = pila.pop();
				if (factor1 == 1L || factor2 == 1L) {
					pila.push(1L);
				} else {
					pila.push(0L);
				}
			} else if (opcode.equals("LST")) {
				long factor2 = pila.pop();
				long factor1 = pila.pop();
				if (factor1 < factor2) {
					pila.push(1L);
				} else {
					pila.push(0L);
				}
				if (debug)
					System.out.println("\tMeter resultado de la comparacion menor en la pila: " + (factor1 < factor2));
			} else if (opcode.equals("GRT")) {
				long factor2 = pila.pop();
				long factor1 = pila.pop();
				if (factor1 > factor2) {
					pila.push(1L);
				} else {
					pila.push(0L);
				}
				if (debug)
					System.out.println(
							"\tMeter resultado de la comparacion mayor que en la pila: " + (factor1 < factor2));
			} else if (opcode.equals("GTE")) {
				double factor2 = pila.pop();
				double factor1 = pila.pop();
				if (factor1 >= factor2) {
					pila.push(1L);
				} else {
					pila.push(0L);
				}
				if (debug)
					System.out.println(
							"\tMeter resultado de la comparacion mayor igual en la pila: " + (factor1 >= factor2));
			} else if (opcode.equals("LTE")) {
				double factor2 = pila.pop();
				double factor1 = pila.pop();
				if (factor1 <= factor2) {
					pila.push(1L);
				} else {
					pila.push(0L);
				}
				if (debug)
					System.out.println(
							"\tMeter resultado de la comparacion menor igual en la pila: " + (factor1 <= factor2));
			} else if (opcode.equals("FJP")) {
				long valor = pila.pop();
				if (valor == 0L) {
					int labelIndex = labels.get(tokens[1]);
					pc = labelIndex;
				}

			} else if (opcode.equals("LDM")) {
				long direccion = pila.pop();
				long valor = memoria[(int) direccion];
				pila.push(valor);

			} else if (opcode.equals("STP")) {
				break;
			} else if (opcode.equals("NOP")) {
				if (debug)
					System.out.println("\tNo operation: ");
			}

			pc++;

		}

		if (debug)

		{
			System.out.println("Resultado PILA: " + pila.toString());
			System.out.println("Resultado memoria: " + simbolos.toString());
			System.out.println("Resultado memoria: " + memoria.toString());
		}

	}
}