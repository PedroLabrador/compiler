# Interprete P

Este es un código simple para interpretar la salida escrita en código P y generar el resultado respectivo

## Interprete
[Interprete](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/src/ve/edu/unet/InterpreteP.java)

## Uso

Para usarlo, se debe agregar como argumento `/ejemplo_generado/salida.p`, luego la salida en consola de codigo P, se copia y se pega en la carpeta `/ejemplo_generado/salida.p` o usar uno de los ejemplos que se encuentran en la carpeta `/ejemplo_generado/ejemplos`

## Instrucciones

|Instrucción|Resultado|
|---|---|
|LDC X| toma la constante X (entero o char) y la pone en el tope de la pila|
|ADI| toma los dos ultimos valores de la pila, los suma y pone el resultado en el tope de la pila|
|SBI| toma los dos ultimos valores de la pila, los resta y pone el resultado en el tope de la pila|
|DIV| toma los dos ultimos valores de la pila, los divide y pone el resultado en el tope de la pila|
|MPI| toma los dos ultimos valores de la pila, los multiplica y pone el resultado en el tope de la pila|
|AND| toma los dos ultimos valores de la pila, si ambos son 1 pone 1 en el tope de la pila, sino 0|
|OR | toma los dos ultimos valores de la pila, si alguno es 1 pone 1 en el tope de la pila, sino 0|
|LST| toma los dos ultimos valores de la pila, si el primero es menor al segundo pone 1 en el tope de la pila, sino 0|
|GRT| toma los dos ultimos valores de la pila, si el primero es mayor al segundo pone 1 en el tope de la pila, sino 0|
|LTE| toma los dos ultimos valores de la pila, si el primero es menor o igual al segundo pone 1 en el tope de la pila, sino 0|
|GTE| toma los dos ultimos valores de la pila, si el primero es mayor al segundo pone 1 en el tope de la pila, sino 0|
|MEM X| toma el ultimo valor de la pila V y reserva V espacios de memoria para la variable X|
|LDA X| pone en el tope de la pila la direccion de la variable X (para un vector es su direccion base)|
|LDM| toma el ultimo valor de la pila como posicion de memoria, pone el valor de la memoria en esa posicion en el tope de la pila|
|STO| toma los dos ultimos valores de la pila, el primero es el valor y el segundo la direccion de memoria donde lo guardara
|LOD X| toma el valor de la variable X y lo pone en el tope de la pila|
|WRI| toma el ultimo valor de la pila y lo imprime como un entero|
|WRC| toma el ultimo valor de la pila y lo imprime como un caracter|
|RDI| toma un valor del teclado y lo pone en el tope de la pila|
|FJP LX| toma el ultimo valor de la pila, si es 0, hace un salto a LX|
|JMP LX| hace un salto incondicional hacia la etiqueta LX|
|LAB LX| marca en el codigo una etiqueta a donde puede ocurrir un salto|
|EQU| toma los dos ultimos valores de la pila y los compara, si son iguales pone 1 en el tope de la |pila, 0 si son diferentes
|NEQ| toma los dos ultimos valores de la pila y los compara, si son diferentes pone 1 en el tope de la |pila, 0 si son diferentes
|STP| termina la ejecucion del interprete|
|NOP| No operation, continua a la siguiente linea|

### Realizado por 

Fabiola Rueda 23.137.871
Pedro Labrador 25.587.776