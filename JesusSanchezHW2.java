/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jesussanchezhw2;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author JesusSanchez
 */
public class JesusSanchezHW2 extends Application {
   
    final ToggleGroup operatorGroup = new ToggleGroup();
    Button calcButton;
    Button resetButton;
    RadioButton rbAdd;
    RadioButton rbSub;
    RadioButton rbMult;
    RadioButton rbDIv;
    Label message;
    TextField xTBox;
    TextField yTBox;
    TextField OutputTB;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        createOpPane(root);
        createIOPane(root);
        createmessagePane(root);
        addHandlers();
        
        
        Scene scene = new Scene(root, 450, 275);

        primaryStage.setTitle("GUI Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void addHandlers() {
        calcButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                calculate();
            }
        });

        resetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                reset();
            }
        });
    }

    public void calculate() {
        double x = 0.0;
        double y = 0.0;
        double answer;
        boolean correctInput = true;
        try {
            x = Double.parseDouble(xTBox.getText());
        } catch (NumberFormatException e) {
            correctInput = false;
            message.setText("Please select a number for X");
            return;
        }
        try {
            y = Double.parseDouble(yTBox.getText());
        } catch (NumberFormatException e) {
            message.setText("Please select a number for Y");
            correctInput = false;
            return;
        }
        if (correctInput) {
            message.setText("GUI Calculator");
            if (rbAdd.isSelected()) {
                answer = x + y;
                OutputTB.setText(String.format("%.2f + %.2f = %.2f", x, y, answer));
            } else if (rbSub.isSelected()) {
                answer = x - y;
                OutputTB.setText(String.format("%.2f - %.2f = %.2f", x, y, answer));
            } else if (rbMult.isSelected()) {
                answer = x * y;
                OutputTB.setText(String.format("%.2f * %.2f = %.2f", x, y, answer));
            } else if (rbDIv.isSelected()) {
                answer = x / y;
                OutputTB.setText(String.format("%.2f / %.2f = %.2f", x, y, answer));
            } else {
                message.setText("You must select an operator");
            }
        }
    }
    
    public void reset() {
        xTBox.setText("");
        yTBox.setText("");
        OutputTB.setText("");
        message.setText("GUI Calculator");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void createOpPane(BorderPane root) {
        HBox operatorPane = new HBox();
        operatorPane.setPadding(new Insets(20));
        operatorPane.setSpacing(15);
        rbAdd = new RadioButton("+");
        rbSub = new RadioButton("-");
        rbMult = new RadioButton("*");
        rbDIv = new RadioButton("/");
        rbAdd.setFont(Font.font ("Times New Roman", 20));
        rbSub.setFont(Font.font ("Times New Roman", 20));
        rbMult.setFont(Font.font ("Times New Roman", 20));
        rbDIv.setFont(Font.font ("Times New Roman", 20));
        rbAdd.setToggleGroup(operatorGroup);
        rbSub.setToggleGroup(operatorGroup);
        rbMult.setToggleGroup(operatorGroup);
        rbDIv.setToggleGroup(operatorGroup);

        calcButton = new Button();
        calcButton.setText("Calculate");
        resetButton = new Button();
        resetButton.setText("Reset");

        operatorPane.getChildren().add(rbAdd);
        operatorPane.getChildren().add(rbSub);
        operatorPane.getChildren().add(rbMult);
        operatorPane.getChildren().add(rbDIv);
        operatorPane.getChildren().add(calcButton);
        operatorPane.getChildren().add(resetButton);

        root.setTop(operatorPane);
    }

    private void createIOPane(BorderPane root) {
        GridPane IOPane = new GridPane();
        IOPane.setPadding(new Insets(20));
        IOPane.setHgap(10);
        IOPane.setVgap(10);
        Label firstInputLabel = new Label("Enter first number (x):");
        Label secondInputLabel = new Label("Enter second number (y):");
        Label OutputLabel = new Label("Output:");

        xTBox = new TextField();
        yTBox = new TextField();
        OutputTB = new TextField();
        xTBox.setAlignment(Pos.CENTER_RIGHT);
        yTBox.setAlignment(Pos.CENTER_RIGHT);
        OutputTB.setAlignment(Pos.CENTER_RIGHT);
        xTBox.setFont(Font.font("Arial" ,16));
        yTBox.setFont(Font.font("Arial" ,16));
        OutputTB.setFont(Font.font("Times New Roman",16));
        OutputTB.setEditable(false);

        IOPane.add(firstInputLabel, 0, 0);
        IOPane.add(xTBox, 1, 0);

        IOPane.add(secondInputLabel, 0, 1);
        IOPane.add(yTBox, 1, 1);

        IOPane.add(OutputLabel, 0, 2);
        IOPane.add(OutputTB, 1, 2);

        root.setCenter(IOPane);
    }

    private void createmessagePane(BorderPane root) {
        StackPane messagePane = new StackPane();
        messagePane.setPadding(new Insets(20));
        message = new Label();
        message.setText("GUI Calculator");
        message.setTextFill(Color.web("#1CC4DE"));
        messagePane.getChildren().add(message);
        root.setBottom(messagePane);
    }

}
