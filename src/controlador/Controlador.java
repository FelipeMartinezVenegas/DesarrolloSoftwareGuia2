package controlador;

import modelo.Automovil;
import modelo.Motocicleta;
import modelo.Vehiculo;
import modelo.CalculadoraImpuestos;

public class Controlador {
    public double calcularImpuesto(String tipo, String marca, String modelo, int anio, int cilindraje, double avaluo, String tipoUso, Boolean tieneSidecar) {
        Vehiculo vehiculo;

        if (tipo.equalsIgnoreCase("Automovil")) {
            vehiculo = new Automovil(marca, modelo, anio, cilindraje, avaluo, tipoUso);
        } else if (tipo.equalsIgnoreCase("Motocicleta")) {
            vehiculo = new Motocicleta(marca, modelo, anio, cilindraje, avaluo, tipoUso, tieneSidecar);
        } else {
            throw new IllegalArgumentException("Tipo de vehículo no válido");
        }

        return CalculadoraImpuestos.calcularImpuesto(vehiculo);
    }
}
