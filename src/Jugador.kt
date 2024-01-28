

/**
 * Representa un jugador en el juego.
 * @property turno El número del jugador (1 o 2).
 * @constructor Crea un jugador con el número de turno especificado.
 */
class Jugador(private val turno: Int) {

    /**
     * Solicita al jugador que ingrese un número entre 1 y 3.
     * @param texto El mensaje que se muestra al solicitar el número.
     * @return El número ingresado por el jugador, menos 1 para ajustarse al índice del tablero.
     */
    private fun pedirNumero(texto: String): Int {
        var numero: Int
        while (true) {
            print("    ($texto)=>")
            try {
                numero = readln().toInt()
                if (numero in 1..3) {
                    break
                } else {
                    throw NumberFormatException()
                }
            } catch (e: NumberFormatException) {
                println("Error, no ha introducido un valor correcto.")
            }
        }
        return numero - 1
    }

    /**
     * Coloca una ficha en el tablero en la posición especificada por el jugador.
     * @param tablero El tablero en el que se coloca la ficha.
     */
    fun colocarFicha(tablero: Tablero) {
        var fila: Int
        var columna: Int
        while (true) {
            println("Jugador $turno, introduzca la posición de la fila y de la columna (1, 2 o 3)")
            fila = pedirNumero("fila")
            columna = pedirNumero("columna")
            if (tablero.tablero[fila][columna] == 0) {
                tablero.tablero[fila][columna] = turno
                break
            } else {
                println("La casilla ya esta ocupada.")
                pulseTeclaParaContinuar()
                tablero.mostrarTablero()
            }
        }
    }

    /**
     * Mueve una ficha del tablero a una nueva posición especificada por el jugador.
     * @param tablero El tablero en el que se mueve la ficha.
     */
    fun moverFicha(tablero: Tablero) {
        var antiguaFila: Int
        var antiguaColumna: Int
        var fila: Int
        var columna: Int
        while (true) {
            println("Jugador $turno, introduzca la posición de la ficha que quiere mover")
            antiguaFila = pedirNumero("fila")
            antiguaColumna = pedirNumero("columna")
            if (tablero.tablero[antiguaFila][antiguaColumna] == turno) {
                if (!tablero.comprobarMovimientosPosibles(antiguaFila, antiguaColumna)) {
                    println("La ficha seleccionada no puede realizar ningún movimiento.")
                } else {
                    break
                }
            } else {
                println("En esa posición no hay ninguna ficha que puedas mover")
                pulseTeclaParaContinuar()
                tablero.mostrarTablero()
            }
        }
        while (true) {
            println("Jugador $turno, introduzca la posición donde quieres colocar la ficha")
            fila = pedirNumero("fila")
            columna = pedirNumero("columna")
            if (fila to columna in tablero.posicionesPosibles[antiguaFila][antiguaColumna] && tablero.tablero[fila][columna] == 0) {
                tablero.tablero[fila][columna] = turno
                tablero.tablero[antiguaFila][antiguaColumna] = 0
                break
            } else {
                println("Error, no es posible mover la ficha a esa posición.")
            }
        }
    }

    /**
     * Comprueba si el jugador ha ganado el juego.
     * @param tablero El tablero en el que se comprueba la victoria.
     * @return true si el jugador ha ganado, false de lo contrario.
     */
    fun comprobarGanador(tablero: Tablero): Boolean {
        for (i in tablero.tablero.indices) {
            if (tablero.tablero[i][0] == turno && tablero.tablero[i][1] == turno && tablero.tablero[i][2] == turno ||
                tablero.tablero[0][i] == turno && tablero.tablero[1][i] == turno && tablero.tablero[2][i] == turno
            ) {
                return true
            }
        }
        return tablero.tablero[0][0] == turno && tablero.tablero[1][1] == turno && tablero.tablero[2][2] == turno ||
                tablero.tablero[0][2] == turno && tablero.tablero[1][1] == turno && tablero.tablero[2][0] == turno
    }
}