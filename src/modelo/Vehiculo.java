package modelo;

public class Vehiculo {
    private String marca;
    private String modelo;
    private int ano;
    private int cilindraje;
    private double avaluo;
    private String tipoUso; // "particular" o "público"

    public Vehiculo(String marca, String modelo, int anio, int cilindraje, double avaluo, String tipoUso) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = anio;
        this.cilindraje = cilindraje;
        this.avaluo = avaluo;
        this.tipoUso = tipoUso;
    }

    public double getAvaluo() {
        return avaluo;
    }

    public String getTipoUso() {
        return tipoUso;
    }

    public int getAnio() {
        return ano;
    }
}
