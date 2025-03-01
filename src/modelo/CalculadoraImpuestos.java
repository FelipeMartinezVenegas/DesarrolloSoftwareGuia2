package modelo;

public class CalculadoraImpuestos {
    public static double calcularImpuesto(Vehiculo vehiculo) {
        double tasa = vehiculo.getTipoUso().equalsIgnoreCase("particular") ? 0.015 : 0.02; // 1.5% o 2%
        double impuestoBase = vehiculo.getAvaluo() * tasa;

        int antiguedad = 2025 - vehiculo.getAnio();
        double descuento = (antiguedad > 10) ? 0.1 * impuestoBase : 0;

        return impuestoBase - descuento;
    }
}
