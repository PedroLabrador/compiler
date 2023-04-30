# Compilador 2023

Este compilador fue desarrollado utilizando Tiny, Cup y JFlex para generar código P. El objetivo principal de este compilador es analizar el código de entrada, generar un árbol y luego traducirlo a código P.

## Caracteristicas

- Análisis léxico usando JFlex
- Análisis de sintaxis usando Cup
- Generación de código en código P

## Intalación

Pasos para correr el compilador:

1. Clonar el repositorio
2. Instalar Java
3. Verificar que JFlex y CUP estén en /especificacion/
4. Usar JFlex para generar `Lexico.java` debe ser generado en `/src/ve/edu/unet`
4.1 Abrir JFlex seleccionar ruta de la especificacion en `/especificacion/lexico.flex`
4.2 Seleccionar ruta de salida
4.3 Presionar generar
5. Usar CUP para generar `parser.java` debe ser generado en `/src/ve/edu/unet`
5.1 Ejecutar el archivo runcup.cmd que se encuentra en `/especificacion`
6. Luego de generar los archivos de especificaciones, agregar como argumento el archivo de entrada de ejemplo .tny que se encuentra en la carpeta `/ejemplo_fuente/factorial.tny` y luego ejecutar el parser.java desde su IDE de preferencia

## Uso

Para usar el compilador, simplemente realice los pasos anteriores, luego la salida en consola de codigo P, se copia y se pega en la carpeta `/ejemplo_generado/salida.p`

## Sintaxis

La siguiente es una lista de funciones hechas:

- Operadores aritméticos (+, -, *, /)
- Operadores relacionales (==, <>, >, <, >=, <=)
- Operadores lógicos (and, or)
- Condicionales
- Do while

## Interprete

Para ver el interprete por favor dirigirse acá:
[Interprete](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/src/ve/edu/unet)

## Ejemplos

|Ejemplo Fuente|Ejemplo Generado|
|---|---|
|[Factorial](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/ejemplo_fuente/ejemplos/factorial.tny)|[Factorial](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/ejemplo_generado/ejemplos/factorial.p)|
|[If](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/ejemplo_fuente/ejemplos/if.tny)|[If](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/ejemplo_generado/ejemplos/if.p)|
|[Suma](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/ejemplo_fuente/ejemplos/suma.tny)|[Suma](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/ejemplo_generado/ejemplos/suma.p)|
|[Maximo](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/ejemplo_fuente/ejemplos/maximo.tny)|[Maximo](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/ejemplo_generado/ejemplos/maximo.p)|
|[Promedio](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/ejemplo_fuente/ejemplos/promedio.tny)|[Promedio](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/ejemplo_generado/ejemplos/promedio.p)|
|[Logicos](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/ejemplo_fuente/ejemplos/logicos.tny)|[Logicos](https://bitbucket.org/pedrolabrador/compilador-2023/src/master/ejemplo_generado/ejemplos/logicos.p)|

### Realizado por 

Fabiola Rueda 23.137.871
Pedro Labrador 25.587.776