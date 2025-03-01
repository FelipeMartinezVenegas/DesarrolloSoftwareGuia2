package vista;

import controlador.Controlador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaPrincipal extends Application {
    private Controlador controlador = new Controlador();

    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(10));

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

        Button btnCalcular = new Button("Calcular Impuesto");
        Label lblResultado = new Label();

        btnCalcular.setOnAction(e -> {
            try {
                String marca = txtMarca.getText();
                String modelo = txtModelo.getText();
                int anio = Integer.parseInt(txtAnio.getText());
                int cilindraje = Integer.parseInt(txtCilindraje.getText());
                double avaluo = Double.parseDouble(txtAvaluo.getText());
                String uso = tipoUso.getValue();

                double impuesto = controlador.calcularImpuesto(marca, modelo, anio, cilindraje, avaluo, uso);
                lblResultado.setText("Impuesto a pagar: $" + impuesto);
            } catch (Exception ex) {
                lblResultado.setText("Error en los datos ingresados");
            }
        });

        layout.getChildren().addAll(txtMarca, txtModelo, txtAnio, txtCilindraje, txtAvaluo, tipoUso, btnCalcular, lblResultado);

        primaryStage.setScene(new Scene(layout, 300, 400));
        primaryStage.setTitle("Cálculo de Impuestos");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
