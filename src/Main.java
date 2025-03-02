import controlador.Controlador;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Controlador controlador = new Controlador();

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label lblTipoVehiculo = new Label("Tipo de Vehículo:");
        ChoiceBox<String> cbTipoVehiculo = new ChoiceBox<>();
        cbTipoVehiculo.getItems().addAll("Automovil", "Motocicleta");
        cbTipoVehiculo.setValue("Automovil");

        Label lblMarca = new Label("Marca:");
        TextField txtMarca = new TextField();

        Label lblModelo = new Label("Modelo:");
        TextField txtModelo = new TextField();

        Label lblAnio = new Label("Año:");
        TextField txtAnio = new TextField();

        Label lblCilindraje = new Label("Cilindraje:");
        TextField txtCilindraje = new TextField();

        Label lblAvaluo = new Label("Avalúo Comercial:");
        TextField txtAvaluo = new TextField();

        Label lblUso = new Label("Tipo de Uso:");
        ChoiceBox<String> cbUso = new ChoiceBox<>();
        cbUso.getItems().addAll("Particular", "Público");

        CheckBox checkSidecar = new CheckBox("Tiene Sidecar");

        GridPane opcionesAdicionales = new GridPane();
        opcionesAdicionales.setHgap(10);
        opcionesAdicionales.setVgap(10);
        actualizarOpciones(cbTipoVehiculo, opcionesAdicionales, checkSidecar);

        cbTipoVehiculo.setOnAction(e -> actualizarOpciones(cbTipoVehiculo, opcionesAdicionales, checkSidecar));

        Button btnCalcular = new Button("Calcular Impuesto");

        btnCalcular.setOnAction(event -> {
            try {
                String tipo = cbTipoVehiculo.getValue();
                String marca = txtMarca.getText();
                String modelo = txtModelo.getText();
                int anio = Integer.parseInt(txtAnio.getText());
                int cilindraje = Integer.parseInt(txtCilindraje.getText());
                double avaluo = Double.parseDouble(txtAvaluo.getText());
                String uso = cbUso.getValue();

                if (marca.isEmpty() || modelo.isEmpty() || uso == null) {
                    mostrarAlerta("Error", "Todos los campos son obligatorios.");
                    return;
                }

                Boolean tieneSidecar = tipo.equals("Motocicleta") ? checkSidecar.isSelected() : false;

                double impuesto = controlador.calcularImpuesto(
                        tipo, marca, modelo, anio, cilindraje, avaluo, uso, tieneSidecar
                );

                mostrarAlerta("Resultado", "El impuesto a pagar es: $" + impuesto);

            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "Ingrese valores numéricos válidos en Año, Cilindraje y Avalúo.");
            }
        });

        grid.add(lblTipoVehiculo, 0, 0);
        grid.add(cbTipoVehiculo, 1, 0);

        grid.add(lblMarca, 0, 1);
        grid.add(txtMarca, 1, 1);

        grid.add(lblModelo, 0, 2);
        grid.add(txtModelo, 1, 2);

        grid.add(lblAnio, 0, 3);
        grid.add(txtAnio, 1, 3);

        grid.add(lblCilindraje, 0, 4);
        grid.add(txtCilindraje, 1, 4);

        grid.add(lblAvaluo, 0, 5);
        grid.add(txtAvaluo, 1, 5);

        grid.add(lblUso, 0, 6);
        grid.add(cbUso, 1, 6);

        grid.add(opcionesAdicionales, 0, 7, 2, 1);

        grid.add(btnCalcular, 0, 8, 2, 1);

        Scene scene = new Scene(grid, 400, 400);
        
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        primaryStage.setTitle("Calculadora de Impuestos");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void actualizarOpciones(ChoiceBox<String> cbTipoVehiculo, GridPane opciones, CheckBox checkSidecar) {
        opciones.getChildren().clear();
        if (cbTipoVehiculo.getValue().equals("Motocicleta")) {
            opciones.add(checkSidecar, 0, 0, 2, 1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
