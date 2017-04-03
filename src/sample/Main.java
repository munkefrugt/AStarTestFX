package sample;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    public enum Direction{
        UP, DOWN, LEFT, RIGHT
    }
    private Direction direction = Direction.RIGHT;

    Timeline timeline = new Timeline();


    NodeObject[][] nodeObject;
    int BoxesZ= 10;
    int BoxesY= 10;
    int blockSize = 40;

    int yHeight =BoxesY*blockSize;
    int zWitdh =BoxesZ *blockSize ;




    @Override
    public void start(Stage primaryStage) throws Exception{


        Group root = new Group();
        Scene scene = new Scene(root, zWitdh, yHeight);

        //Filled rectangle test rectangle
        // x,y, size,size.
        Rectangle rect1 = new Rectangle(1*blockSize, 1*blockSize, blockSize, blockSize);
        rect1.setFill(Color.BLUE);

        // make the nodeclass, to call each node object, do: nodeObject[z][y]. z, y, are numbers
        nodeObject = new NodeObject[10][10];

        // make startupclass
        Setup setup = new Setup(root,nodeObject,blockSize);
        setup.startUpBoard();




        root.getChildren().addAll(rect1);

        primaryStage.setTitle("A*");
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
