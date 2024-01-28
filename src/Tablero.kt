

/**
 * Representa el tablero del juego.
 */
class Tablero {
    private val fichas = listOf(" ", "X", "O")

    val posicionesPosibles = listOf(
        listOf(
            setOf(0 to 1, 1 to 0, 1 to 1),
            setOf(0 to 0, 0 to 2, 1 to 1),
            setOf(0 to 1, 1 to 1, 1 to 2)
        ),
        listOf(
            setOf(0 to 0, 1 to 1, 2 to 0),
            setOf(0 to 0, 0 to 1, 0 to 2, 1 to 0, 1 to 2, 2 to 0, 2 to 1, 2 to 2),
            setOf(0 to 2, 1 to 1, 2 to 2)
        ),
        listOf(
            setOf(1 to 0, 1 to 2, 2 to 1),
            setOf(1 to 1, 2 to 0, 2 to 2),
            setOf(1 to 1, 1 to 2, 2 to 1)
        )
    )

    val tablero = MutableList(3) { MutableList(3) { 0 } }

    /**
     * Limpia la consola.
     */
    private fun clearConsole() {
        println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
    }

    /**
     * Muestra una fila del tablero en la consola.
     * @param lista Lista de enteros representando una fila del tablero.
     */
    private fun mostrarFila(lista: List<Int>) {
        var filaStr = "║"
        for (i in lista) {
            filaStr += " ${fichas[i]} ║"
        }
        println(filaStr)
    }

    /**
     * Muestra el tablero en la consola.
     */
    fun mostrarTablero() {
        clearConsole()
        println("""    ╔═══╦═══╦═══╗
    ║ 1 ║ 2 ║ 3 ║
╔═══╬═══╬═══╬═══╣""")
        for (i in tablero.indices) {
            print("║ ${i + 1} ")
            mostrarFila(tablero[i])
            if (i != 2) {
                println("╠═══╬═══╬═══╬═══╣")
            }
        }
        println("╚═══╩═══╩═══╩═══╝")
    }

    /**
     * Comprueba si hay movimientos posibles en la posición especificada del tablero.
     * @param fila Índice de fila.
     * @param columna Índice de columna.
     * @return true si hay movimientos posibles, false de lo contrario.
     */
    fun comprobarMovimientosPosibles(fila: Int, columna: Int): Boolean {
        for (posicion in posicionesPosibles[fila][columna]) {
            if (tablero[posicion.first][posicion.second] == 0) {
                return true
            }
        }
        return false
    }
}


