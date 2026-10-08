/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a2.f1_fantasy;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ALUMNOS
 */
public class Plantilla {

    private static final int MAX_JUGADORES = 5;

    private double presupuesto;
    private final List<Jugador> jugadores = new ArrayList<>();

    public Plantilla(double presupuestoInicial) {
        this.presupuesto = presupuestoInicial;
    }

    public boolean fichar(Jugador j) {
        if (jugadores.contains(j) || jugadores.size() >= MAX_JUGADORES
                || j.getValor() > presupuesto) {
            return false;
        }
        presupuesto -= j.getValor();
        jugadores.add(j);
        return true;
    }

    public boolean vender(Jugador j) {
        if (!jugadores.remove(j)) {
            return false;
        }
        presupuesto += j.getValor();
        return true;
    }

    public int getPuntosTotales() {
        return jugadores.stream().mapToInt(Jugador::getPuntos).sum();
    }

    public double getPresupuesto() { return presupuesto; }
    public List<Jugador> getJugadores() { return jugadores; }
}