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
        Scene scene = new Scene(grid, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        // Configurar y mostrar la ventana
        primaryStage.setTitle("Calculadora de Impuestos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
