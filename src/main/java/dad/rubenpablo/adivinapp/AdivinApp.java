package dad.rubenpablo.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application{
	
	private Label etiqueta;
	private TextField inputText;
	private Button boton;
	private VBox caja;
	private int intentos;
	private int aleatorio;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Generamos el número aleatorio
		aleatorio = genAleatorio(); // Rango de 1-100
		// Label
		etiqueta = new Label("Introduce un número del 1 al 100");
		
		// Textfield
		inputText = new TextField();
		inputText.setPromptText("Introduce un número");
		inputText.setMaxWidth(150);
		
		// Botón
		boton = new Button();
		boton.setText("Adivinar");
		boton.setOnAction(e -> onAdivinarAction(e));
		boton.setDefaultButton(true);
		
		// Caja (contenedor)
		caja = new VBox();
		caja.setSpacing(15);
		caja.setAlignment(Pos.CENTER);
		caja.getChildren().addAll(etiqueta, inputText, boton);
		
		// Escena
		Scene escena = new Scene(caja, 320, 200);
		
		// Configuramos la ventana
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(escena);
		primaryStage.show();
	}
	
	private int genAleatorio() {
		return (int) (Math.random()*100 + 1);
	}
	
	private void onAdivinarAction(ActionEvent e) {
		 
		try {
			int recogido = Integer.parseInt(inputText.getText());
			
			if (recogido == aleatorio) {
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setHeaderText("¡Has ganado!");
				alerta.setContentText("Sólo has necesitado " + intentos + " intentos.\nVuelve a jugar y hazlo mejor.");
				alerta.showAndWait();
				aleatorio = genAleatorio(); // Generamos nuevo nº aleatorio
				intentos=0;
			} else {
				intentos++;
				String msg = (aleatorio < recogido) ? String.format("El número a adivinar es menor que %d.%nVuelve a intentarlo.", recogido):String.format("El número a adivinar es mayor que %d.%nVuelve a intentarlo.", recogido);
				Alert alerta = new Alert(AlertType.WARNING);
				alerta.setHeaderText("¡Has fallado!");
				alerta.setContentText(msg);
				alerta.showAndWait();
			}
		} catch (NumberFormatException e1) {
			// En caso de no haber introducido nada o introducir texto, devolver Alert tipo Error
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setHeaderText("AdivinApp - Error");
			alerta.setContentText("El número introducido no es válido.");
			alerta.showAndWait();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
