package com.kjellvos.school;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;

/*
TODO enter is ook raden!
 */

public class Main extends Application {
    public static final boolean DEBUG = true;

    private Pane pane;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Font font;
    private Text labelText;
    private Label label;
    private TextField textField;
    private Button button;

    private HangmanWithUI hangmanWithUI;

    @Override
    public void start(Stage startStage) throws Exception{
        pane = new Pane();
        scene = new Scene(pane, 400, 600, Color.WHITESMOKE);
        font = new Font("Arial", 18);

        canvas = new Canvas(400D, 500D);
        graphicsContext = canvas.getGraphicsContext2D();
        pane.getChildren().add(canvas);
        pane.applyCss();
        pane.layout();

        final double padding = 10D;
        final double offsetX = 75D, offsetY = 550D;

        labelText = new Text("Letter:");
        labelText.setFont(font);
        label = new Label("Letter:");
        label.relocate(offsetX, offsetY);
        label.setPrefWidth(labelText.getLayoutBounds().getWidth());
        label.setFont(font);
        pane.getChildren().add(label);
        pane.applyCss();
        pane.layout();

        textField = new TextField();
        textField.setTextFormatter(getTextFormatter());
        textField.setPrefWidth(60D);
        textField.setAlignment(Pos.CENTER);
        textField.relocate(offsetX + (label.getWidth()) + padding, offsetY);
        pane.getChildren().add(textField);
        pane.applyCss();
        pane.layout();

        button = new Button("Raden!");
        button.setOnMouseClicked(event -> {
            if(textField.getText().length() == 1) {
                char[] letter = textField.getText().toCharArray();
                textField.setText("");
                hangmanWithUI.takeInput(letter[0]);
            }
        });
        button.setFont(font);
        button.setPrefWidth(100D);
        button.relocate(offsetX + (label.getWidth() + textField.getWidth()) + (padding*2), offsetY);
        pane.getChildren().add(button);
        pane.applyCss();
        pane.layout();

        startStage.setResizable(false);
        startStage.setTitle("Hangman!");
        startStage.setScene(scene);
        startStage.sizeToScene();
        startStage.show();

        createBorderAndBackground(graphicsContext);
        hangmanWithUI = new HangmanWithUI(this);
        hangmanWithUI.setupGame();
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void setupCanvasWithWords(GraphicsContext gc, Letters[] letters) {
        double topPadding = 20D, leftPadding = 5D;

        Text tempText = null;
        gc.setFont(font);
        for(int i = 0; i < 26; i++) {
            if (letters[i].getIsGuessed()) {
                char letter = letters[i].getLetter();
                if (i == 0) {
                    tempText = new Text(Character.toString(letters[i].getLetter()));
                    tempText.setFont(font);
                    leftPadding += tempText.getLayoutBounds().getWidth() + 1D;
                }else if (tempText != null){
                    leftPadding += tempText.getLayoutBounds().getWidth() + 1D;
                }
                if (letters[i].getIsPartOfWordToGuess()){
                    gc.setFill(Color.GREEN);
                }else{
                    gc.setFill(Color.RED);
                }
                gc.fillText(Character.toString(letter), leftPadding, topPadding);
                gc.stroke();
                if (i != 0) {
                    tempText = new Text(Character.toString(letters[i].getLetter()));
                }
                tempText.setFont(font);
            }
        }
        leftPadding = 5D;
        topPadding = 490D;
        char[] wordPlayerTriesToGuess = hangmanWithUI.getWordPlayerTriesToGuessCharArray();
        for(int i = 0; i < wordPlayerTriesToGuess.length; i++) {
            int alphabeticalPosition = hangmanWithUI.getAlphabetPosition(wordPlayerTriesToGuess[i]);
            if (i == 0) {
                if(letters[alphabeticalPosition].getIsGuessed()){
                    tempText = new Text(Character.toString(letters[i].getLetter()));
                }else{
                    tempText = new Text(".");
                }
            } else {
                if(letters[alphabeticalPosition].getIsGuessed()){
                    tempText = new Text(Character.toString(letters[i - 1].getLetter()));
                }else{
                    tempText = new Text(".");
                }
            }
            tempText.setFont(font);
            leftPadding += tempText.getLayoutBounds().getWidth() + 15D;
            gc.setFill(Color.BLACK);
            if(letters[alphabeticalPosition].getIsPartOfWordToGuess() && letters[alphabeticalPosition].getIsGuessed()){
                gc.fillText(Character.toString(wordPlayerTriesToGuess[i]), leftPadding, topPadding);
            }else{
                gc.fillText(".", leftPadding, topPadding);
            }
            gc.stroke();
        }
    }

    public void createHangMan(){
        int amountOfMistakes = hangmanWithUI.getAmountOfMistakes();
        if (amountOfMistakes > 0) {

        }
        if (amountOfMistakes > 1) {

        }
        if (amountOfMistakes > 2) {

        }
        if (amountOfMistakes > 3) {

        }
        if (amountOfMistakes > 4) {

        }
        if (amountOfMistakes > 5) {

        }
        if (amountOfMistakes > 6) {

        }
        if (amountOfMistakes > 7) {

        }
        if (amountOfMistakes > 8) {

        }
        if (amountOfMistakes > 9) {

        }
    }

    public void createBorderAndBackground(GraphicsContext gc){
        final double lineWidth = 5D;
        gc.setFont(font);

        gc.clearRect(0D, 0D, 400D, 500D);

        gc.setFill(Color.GRAY);
        gc.fillRect(0D, 0D, 400D, 500D);
        gc.fill();

        gc.setLineWidth(lineWidth);
        gc.beginPath();
        gc.moveTo(0D+lineWidth/2, 0D-lineWidth/2);
        gc.lineTo(0D+lineWidth/2, 500D-lineWidth/2);
        gc.lineTo(400D-lineWidth/2, 500D-lineWidth/2);
        gc.lineTo(400D-lineWidth/2, 0D+lineWidth/2);
        gc.lineTo(0D+lineWidth/2, 0D+lineWidth/2);
        gc.stroke();
    }

    private TextFormatter<String> getTextFormatter() {
        return new TextFormatter<>(getFilter());
    }

    private UnaryOperator<TextFormatter.Change> getFilter() {
        return change -> {
            String text = change.getText();

            if (!change.isContentChange()) {
                return change;
            }

            if ((text.matches("[a-z]*") && change.getControlNewText().length() < 2) || text.isEmpty()) {
                return change;
            }

            return null;
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
