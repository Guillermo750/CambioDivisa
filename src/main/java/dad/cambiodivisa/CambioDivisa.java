package dad.cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {
	
	private Divisa euro = new Divisa("Euro", 1.0);
	private Divisa libra = new Divisa("Libra", 0.8873);
	private Divisa dolar = new Divisa("Dolar", 1.2007);
	private Divisa yen = new Divisa("Yen", 133.59);
	
	private Divisa [] monedas = { euro, libra, dolar, yen };
	
	private TextField numero1Text, numero2Text;
	private HBox numero1Box,numero2Box;
	private ComboBox<Divisa> miCombo1Box;
	private ComboBox<Divisa> miCombo2Box;
	private Button cambiarButton;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		numero1Text = new TextField("0");
		numero1Text.setPrefColumnCount(4);
		
		numero2Text = new TextField("0");
		numero2Text.setPrefColumnCount(4);
		numero2Text.setEditable(false);
		
		miCombo1Box = new ComboBox<>();
		miCombo1Box.getItems().addAll(monedas);
		miCombo1Box.getSelectionModel().select(euro);
		
		miCombo2Box = new ComboBox<>();
		miCombo2Box.getItems().addAll(monedas);
		miCombo2Box.getSelectionModel().select(yen);
		
		cambiarButton = new Button("Cambiar");
		cambiarButton.setOnAction(e -> onCambiarAction(e));
		
		numero1Box = new HBox(2);
		numero1Box.setAlignment(Pos.BASELINE_CENTER);
		numero1Box.getChildren().addAll(numero1Text, miCombo1Box);
		
		numero2Box = new HBox(2);
		numero2Box.setAlignment(Pos.BASELINE_CENTER);
		numero2Box.getChildren().addAll(numero2Text, miCombo2Box);
		
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(numero1Box, numero2Box, cambiarButton);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Cambio de divisa");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void onCambiarAction(ActionEvent e) {
		
		try {
			
			Divisa origen = miCombo1Box.getValue();
			
			Divisa destino = miCombo2Box.getValue();
			
			Double cantidad1 = Double.parseDouble(numero1Text.getText());
			
			Double Euros = origen.toEuro(cantidad1);
			
			Double cantidad2 = destino.fromEuro(Euros);
			
			numero2Text.setText("" + cantidad2);
			
		} catch (Exception e2) {
			
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText("Error");
			alert1.setContentText("El número introducido no es válido");
			alert1.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
