package modelo;

public class Vehiculo {
    private String marca;
    private String modelo;
    private int anio;
    private int cilindraje;
    private double avaluo;
    private String tipoUso; // "particular" o "público"

    public Vehiculo(String marca, String modelo, int anio, int cilindraje, double avaluo, String tipoUso) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio; // Corregido (antes era "ano")
        this.cilindraje = cilindraje;
        this.avaluo = avaluo;
        this.tipoUso = tipoUso;
    }

    // Getters
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public double getAvaluo() {
        return avaluo;
    }

    public String getTipoUso() {
        return tipoUso;
    }
}
