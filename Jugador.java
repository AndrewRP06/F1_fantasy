/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a2.f1_fantasy;

/**
 *
 * @author ALUMNOS
 */
public class Jugador {
    
    private final String nombre;
    private final String equipo;
    private double valor;
    private int puntos;

    public Jugador(String nombre, String equipo, double valor) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.valor = valor;
        this.puntos = 0;
    }

    public String getNombre() { return nombre; }
    public String getEquipo() { return equipo; }
    public double getValor() { return valor; }
    public int getPuntos() { return puntos; }

    public void sumarPuntos(int p) {
        puntos += p;
    }

    public void actualizarValor(double variacion) {
        valor = Math.max(1.0, valor + variacion);
    }

    @Override
    public String toString() {
        return nombre + " (" + equipo + ") - " + String.format("%.1f", valor) + "M";
    }
}
