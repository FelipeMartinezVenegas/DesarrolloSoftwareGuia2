package vista;

import controlador.Controlador;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Vehiculo;

public class VentanaPrincipal extends Application {
    private Controlador controlador = new Controlador();
    private TableView<Vehiculo> tablaResultados;
    private ObservableList<Vehiculo> datosTabla = FXCollections.observableArrayList();
    private BarChart<String, Number> grafica;

    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        ComboBox<String> tipoVehiculo = new ComboBox<>();
        tipoVehiculo.getItems().addAll("Automovil", "Motocicleta");
        tipoVehiculo.setValue("Automovil");

        TextField txtMarca = new TextField();
        txtMarca.setPromptText("Marca del Vehículo");

        TextField txtModelo = new TextField();
        txtModelo.setPromptText("Modelo");

        TextField txtAnio = new TextField();
        txtAnio.setPromptText("Año de Fabricación");

        TextField txtCilindraje = new TextField();
        txtCilindraje.setPromptText("Cilindraje");

        TextField txtAvaluo = new TextField();
        txtAvaluo.setPromptText("Avalúo Comercial");

        ComboBox<String> tipoUso = new ComboBox<>();
        tipoUso.getItems().addAll("Particular", "Público");

        CheckBox chkSidecar = new CheckBox("Tiene Sidecar");
        tipoVehiculo.setOnAction(e -> chkSidecar.setVisible(tipoVehiculo.getValue().equals("Motocicleta")));

        Button btnCalcular = new Button("Calcular Impuesto");

        tablaResultados = new TableView<>();
        TableColumn<Vehiculo, String> colMarca = new TableColumn<>("Marca");
        colMarca.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getMarca()));

        TableColumn<Vehiculo, String> colModelo = new TableColumn<>("Modelo");
        colModelo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getModelo()));

        TableColumn<Vehiculo, Number> colImpuesto = new TableColumn<>("Impuesto");
        colImpuesto.setCellValueFactory(data -> {
            Vehiculo v = data.getValue();
            double impuesto = controlador.calcularImpuesto(
                    (v instanceof modelo.Automovil) ? "Automovil" : "Motocicleta",
                    v.getMarca(),
                    v.getModelo(),
                    v.getAnio(),
                    v.getCilindraje(),
                    v.getAvaluo(),
                    v.getTipoUso(),
                    (v instanceof modelo.Motocicleta) ? ((modelo.Motocicleta) v).tieneSidecar() : false
            );

            return new javafx.beans.property.ReadOnlyObjectWrapper<>(impuesto);
        });

        tablaResultados.getColumns().addAll(colMarca, colModelo, colImpuesto);
        tablaResultados.setItems(datosTabla);

        CategoryAxis ejeX = new CategoryAxis();
        NumberAxis ejeY = new NumberAxis();
        grafica = new BarChart<>(ejeX, ejeY);
        grafica.setTitle("Impuestos Calculados");
        ejeX.setLabel("Vehículo");
        ejeY.setLabel("Valor del Impuesto");

        btnCalcular.setOnAction(e -> {
            try {
                String marca = txtMarca.getText();
                String modelo = txtModelo.getText();
                int anio = Integer.parseInt(txtAnio.getText());
                int cilindraje = Integer.parseInt(txtCilindraje.getText());
                double avaluo = Double.parseDouble(txtAvaluo.getText());
                String uso = tipoUso.getValue();

                String tipo = tipoVehiculo.getValue();
                Boolean tieneSidecar = tipo.equals("Motocicleta") ? chkSidecar.isSelected() : null;

                double impuesto = controlador.calcularImpuesto(tipo, marca, modelo, anio, cilindraje, avaluo, uso, tieneSidecar);


                Vehiculo nuevoVehiculo;
                if (tipo.equals("Automovil")) {
                    nuevoVehiculo = new modelo.Automovil(marca, modelo, anio, cilindraje, avaluo, uso);
                } else {
                    nuevoVehiculo = new modelo.Motocicleta(marca, modelo, anio, cilindraje, avaluo, uso, tieneSidecar);
                }

                datosTabla.add(nuevoVehiculo);

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(marca + " " + modelo);
                series.getData().add(new XYChart.Data<>(marca + " " + modelo, impuesto));
                grafica.getData().add(series);
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error en los datos ingresados. Verifica los valores.");
                alert.show();
            }
        });

        layout.getChildren().addAll(
                tipoVehiculo, txtMarca, txtModelo, txtAnio, txtCilindraje, txtAvaluo, tipoUso,
                chkSidecar, btnCalcular, tablaResultados, grafica
        );

        Scene scene = new Scene(layout, 500, 600);
        scene.getStylesheets().add(getClass().getResource("../styles/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cálculo de Impuestos");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
