

fun main() {
    val tablero = Tablero()
    val jugadores = listOf(Jugador(1), Jugador(2))

    var turno = 0
    var ronda = 0
    var ganador = false

    while (!ganador) {
        tablero.mostrarTablero()
        val (nuevoTurno, nuevaRonda) = cambiarTurno(turno, ronda)
        turno = nuevoTurno
        ronda = nuevaRonda

        if (ronda <= 3) {
            jugadores[turno - 1].colocarFicha(tablero)
        } else {
            jugadores[turno - 1].moverFicha(tablero)
        }

        ganador = jugadores[turno - 1].comprobarGanador(tablero)
        if (ganador) {
            tablero.mostrarTablero()
            println("El jugador $turno ha ganado.")
        }
    }
}

/**
 * Cambia el turno del juego y la ronda.
 * @param turno El número del turno actual.
 * @param ronda El número de la ronda actual.
 * @return Un par de enteros representando el nuevo turno y la nueva ronda.
 */
fun cambiarTurno(turno: Int, ronda: Int): Pair<Int, Int> {
    return if (turno % 2 == 0) {
        println("\n RONDA ${ronda + 1}\n")
        1 to (ronda + 1)
    } else {
        println("\n RONDA ${ronda}\n")
        2 to ronda
    }
}

/**
 * Imprime un mensaje y espera a que el usuario presione una tecla para continuar.
 */
fun pulseTeclaParaContinuar() {
    println("Pulse cualquier tecla para continuar...")
    readln()
}