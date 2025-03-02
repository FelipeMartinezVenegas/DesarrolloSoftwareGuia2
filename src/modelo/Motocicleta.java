package modelo;

public class Motocicleta extends Vehiculo {
    private boolean tieneSidecar;

    public Motocicleta(String marca, String modelo, int anio, int cilindraje, double avaluo, String tipoUso, boolean tieneSidecar) {
        super(marca, modelo, anio, cilindraje, avaluo, tipoUso);
        this.tieneSidecar = tieneSidecar;
    }

    public boolean tieneSidecar() {
        return tieneSidecar;
    }
}
