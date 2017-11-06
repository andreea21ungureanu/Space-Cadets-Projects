package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Random;

public class Spirograph extends Application {
    private final Label Ra = new Label("R axis");
    private final Label ra = new Label("r axis");
    private final Label aa = new Label("a axis");


    public static void main(String[] args) {
        System.out.println("Launching JavaFX application.");

        // Start the JavaFX application by calling launch().
        launch(args);
    }

    // Override the init() method.
    public void init() {
        System.out.println("Inside the init() method.");
    }

    // Override the start() method.
    public void start(Stage myStage) {
        System.out.println("Inside the start() method.");

        // Give the stage a title.
        myStage.setTitle("JavaFX Skeleton.");

        // Create a root node. In this case, a flow layout pane
        // is used, but several alternatives exist.
        FlowPane rootNode = new FlowPane(10, 10);

        // Create a scene.
        Scene myScene = new Scene(rootNode, 1200, 1200,Color.BLACK);
        //aligns the scene
        rootNode.setAlignment(Pos.TOP_CENTER);

        // Set the scene on the stage.
        myStage.setScene(myScene);



        // This creates a canvas in order to be able to draw spirographs on it
        Canvas canvas = new Canvas(1000, 1000);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // This will create a Vbox that will contain tha canvas so that it won't cover the sliders
        VBox vbox = new VBox();
        vbox.getChildren().addAll(canvas);

        /*
        *
        *
        // SLIDERS //
        *
        *
        */

        // Slider for the R axis
        Slider sliderR = new Slider(0,120,95);
        sliderR.setLayoutX(60);
        sliderR.setLayoutY(50);
        sliderR.setShowTickLabels(true);
        sliderR.setShowTickLabels(true);
        sliderR.setPrefWidth(370);

        // Slider for the r axis
        Slider sliderr = new Slider(0,120,55);
        sliderr.setLayoutX(60);
        sliderr.setLayoutY(50);
        sliderr.setShowTickLabels(true);
        sliderr.setShowTickLabels(true);
        sliderr.setPrefWidth(370);

        // Slider for the a axis
        Slider slidera = new Slider(0,120,28);
        slidera.setLayoutX(60);
        slidera.setLayoutY(50);
        slidera.setShowTickLabels(true);
        slidera.setShowTickLabels(true);
        slidera.setPrefWidth(370);


        // Label fonts and colors
        Ra.setFont(Font.font("Didot", 20));
        Ra.setTextFill(Color.SKYBLUE);

        ra.setFont(Font.font("Didot", 20));
        ra.setTextFill(Color.SKYBLUE);

        aa.setFont(Font.font("Didot", 20));
        aa.setTextFill(Color.SKYBLUE);


        // This will create a button that will be able to start the process of drawing
        Button button = new Button("Start to draw");
        button.setStyle("-fx-font: 22 didot; -fx-base: #7FDBFF;");

        // Setting the button on action so that everytime when it is pressed, it will draw a new spirograph with a new combination of colors
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                draw( sliderR.getValue(), sliderr.getValue(), slidera.getValue(),gc);
                drawDropShadow(randomColor(),randomColor(),randomColor(),randomColor(),gc);

            }
        });

        // Will project the parameters to the scene
        rootNode.getChildren().addAll(sliderR,Ra,sliderr,ra,slidera,aa,button,vbox);

        // Show the stage and its scene.
        myStage.show();
    }

    // This method does the magic --> drawing the spirograph
    void draw(double R, double r, double a, GraphicsContext gc)
    {
        // Before every new drawing, the canvas is cleaned so that it won't draw over the last spirograph
        gc.clearRect(0,0,1000,1000);

        for (double t = 0.0; t < 80; t += 0.0002) {
            double x = (R+r) * Math.cos(t) - (r+a) * Math.cos(((R+r)/r)*t);
            double y = (R+r) * Math.sin(t) - (r+a) * Math.sin(((R+r)/r)*t);

            // Interesting way of creating the spirograph out of ovals
            gc.strokeOval(x + 500, y + 400, 0.6,0.6);
        }

    }

    // This method will dispose the colors as a shadow of the spirograph
    private void drawDropShadow(Paint firstColor, Paint secondColor,
                                Paint thirdColor, Paint fourthColor,GraphicsContext gc) {
        gc.applyEffect(new DropShadow(20, 20, 0, (Color) firstColor));
        gc.applyEffect(new DropShadow(20, 0, 20, (Color) secondColor));
        gc.applyEffect(new DropShadow(20, -20, 0, (Color) thirdColor));
        gc.applyEffect(new DropShadow(20, 0, -20, (Color) fourthColor));
    }

    // Create random color every time the method is called.
    private Paint randomColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r, g, b);
    }

    // Override the stop() method.
    public void stop() {
        System.out.println("Inside the stop() method.");
    }
}


