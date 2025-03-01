package controlador;

import modelo.Vehiculo;
import modelo.CalculadoraImpuestos;

public class Controlador {
    public double calcularImpuesto(String marca, String modelo, int ano, int cilindraje, double avaluo, String tipoUso) {
        Vehiculo vehiculo = new Vehiculo(marca, modelo, ano, cilindraje, avaluo, tipoUso);
        return CalculadoraImpuestos.calcularImpuesto(vehiculo);
    }
}
