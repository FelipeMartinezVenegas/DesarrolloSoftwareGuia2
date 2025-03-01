import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Crear el layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        // Etiquetas y Campos de Entrada
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

        // Botón Calcular
        Button btnCalcular = new Button("Calcular Impuesto");

        // Evento al presionar el botón
        btnCalcular.setOnAction(event -> {
            try {
                // Obtener los valores ingresados
                String marca = txtMarca.getText();
                String modelo = txtModelo.getText();
                int anio = Integer.parseInt(txtAnio.getText());
                int cilindraje = Integer.parseInt(txtCilindraje.getText());
                double avaluo = Double.parseDouble(txtAvaluo.getText());
                String uso = cbUso.getValue();

                // Validación básica
                if (marca.isEmpty() || modelo.isEmpty() || uso == null) {
                    mostrarAlerta("Error", "Todos los campos son obligatorios.");
                    return;
                }

                // Cálculo del impuesto (ejemplo simple)
                double impuesto = calcularImpuesto(avaluo, uso);

                // Mostrar el resultado en un Alert
                mostrarAlerta("Resultado", "El impuesto a pagar es: $" + impuesto);

            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "Ingrese valores numéricos válidos en Año, Cilindraje y Avalúo.");
            }
        });

        // Agregar elementos al GridPane
        grid.add(lblMarca, 0, 0);
        grid.add(txtMarca, 1, 0);

        grid.add(lblModelo, 0, 1);
        grid.add(txtModelo, 1, 1);

        grid.add(lblAnio, 0, 2);
        grid.add(txtAnio, 1, 2);

        grid.add(lblCilindraje, 0, 3);
        grid.add(txtCilindraje, 1, 3);

        grid.add(lblAvaluo, 0, 4);
        grid.add(txtAvaluo, 1, 4);

        grid.add(lblUso, 0, 5);
        grid.add(cbUso, 1, 5);

        grid.add(btnCalcular, 0, 6, 2, 1);

        // Crear la escena y agregar CSS
        Scene scene = new Scene(grid, 400, 350);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        // Configurar y mostrar la ventana
        primaryStage.setTitle("Calculadora de Impuestos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para calcular el impuesto (ejemplo básico)
    private double calcularImpuesto(double avaluo, String uso) {
        double tasa = uso.equals("Público") ? 0.02 : 0.015; // 2% para público, 1.5% para particular
        return avaluo * tasa;
    }

    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
