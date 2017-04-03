package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class Main extends Application {



    //Timeline timeline = new Timeline();


    NodeObject[][] nodeObject;
    int boxesX = 30;
    int boxesY = 20;
    int blockSize = 30;

    int yHeight = boxesY *blockSize;
    int xWitdh = boxesX *blockSize ;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right =false;

    // x and y are in hole numbers
    int directionX;
    int directionY;
    int gostPinkpositionX;
    int gostPinkpositionY;


    @Override
    public void start(Stage primaryStage) throws Exception{


        Group root = new Group();
        Scene scene = new Scene(root, xWitdh, yHeight);

        // when keys are pressed -source: http://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
        up=false;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {


                switch (keyEvent.getCode()) {
                    case W:  up =  true;
                        System.out.println("up");

                        break;
                    case S: down = true;
                        System.out.println("down");
                        break;
                    case A: left = true;
                        System.out.println("left");

                        break;
                    case D: right = true;
                        System.out.println("right");

                        break;
                    case P:
                        System.out.println("pause");

                        break;
                }

            }
        });

        // source :http://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    up = false; break;
                    case S:  down = false; break;
                    case A:  left  = false; break;
                    case D: right  = false; break;
                    //case SHIFT: running = false; break;
                }
            }
        });

        //Filled rectangle test rectangle
        // x,y, size,size.
        final Rectangle pacMan = new Rectangle((directionX *(blockSize)+blockSize), (directionY *(blockSize)+blockSize), blockSize, blockSize);
        pacMan.setFill(Color.YELLOW);

        final Rectangle gostpink = new Rectangle(2*blockSize, 2* blockSize, blockSize, blockSize);
        gostpink.setFill(Color.PINK);

        // start location
        final Rectangle rectStart = new Rectangle((directionX *(blockSize)+blockSize), (directionY *(blockSize)+blockSize), blockSize, blockSize);
        rectStart.setFill(Color.GREEN);
        // end
        final Rectangle rectEnd = new Rectangle(xWitdh-2*blockSize, yHeight-2*blockSize, blockSize, blockSize);
        rectEnd.setFill(Color.RED);

        // add walls



        // make the nodeclass, to call each node object, do: nodeObject[z][y]. z, y, are numbers
        nodeObject = new NodeObject[boxesX][boxesY];

        // make startupclass
        final Setup setup = new Setup(root,nodeObject,blockSize, boxesX,boxesY);
        setup.startUpBoard();




        root.getChildren().addAll(pacMan,gostpink,rectStart,rectEnd);

        primaryStage.setTitle("A*");
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //int directionX = 0, directionY = 0;

                if (up)
                {
                    System.out.println("up activate");
                    directionY = directionY -blockSize;
                    // upate all

                    setup.updateAllAlgorithms();


                }
                // change the color of the node above.



                if(down)
                {
                    directionY = directionY +blockSize ;
                    // upate all gosth



                }
                if (left)
                {
                    directionX = directionX -blockSize;

                }
                if (right)
                {
                    directionX = directionX +blockSize;


                }
                pacMan.relocate(directionX, directionY);
                //random x


                gostpink.relocate(gostPinkpositionX, gostPinkpositionY);

                //sleep
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ie)
                {
                    System.out.println("error sleep-main");
                }


            }
        };
        timer.start();

    }

    public void gostMoves() {

    }


    public static void main(String[] args) {
        launch(args);
    }
}
