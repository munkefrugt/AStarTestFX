package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by v on 4/1/17.
 */
public class NodeObject {

    // the parent is the node it came from.
    // or a node to reach this node
    NodeObject[][] parent;
    int blockSize;
    Group root;
    int x;
    int y;
    int f;
    int h;
    int g;
    // when node is made is wall is false
    boolean isWall = false;
    // packMan will change node each time he takes a step. // default is false
    boolean isNodePacman = false;

    Rectangle rect;


    public NodeObject(int z, int y, Group root, int blockSize) {
        this.root= root;
        this.blockSize= blockSize;
        this.x = z;
        this.y = y;




    }


    public int getUniqueXval() {
        return x;
    }

    public int getUniqueYval()
    {
        return y;
    }

    public void makeNodeRectangle() {
        if(!isWall)
        {

        rect= new Rectangle(x *blockSize, y*blockSize, blockSize, blockSize);

        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.RED);
        // set line thickness
        rect.setStrokeWidth(1);

        // add to root
        root.getChildren().add(rect);

        }
        else if(isWall)
        {
            Rectangle rectwall= new Rectangle(x *blockSize, y*blockSize, blockSize, blockSize);

            rectwall.setFill(Color.BLACK);



            // add to root
            root.getChildren().add(rectwall);
        }
    }

    public void makeNodewall() {
        isWall = true;
    }

    public void PacmanSetTrue() {
        isNodePacman = true;
    }

    public void setFZero() {
        f = 0;
    }


    public void setRectColor() {
        //Rectangle rect= new Rectangle(x *blockSize, y*blockSize, blockSize, blockSize);

        rect.setFill(Color.BLUE);

        // add to root
       // root.getChildren().add(rect);

    }

    public boolean getisWall() {
        return isWall;
    }

    public void makeGreen() {
        rect.setFill(Color.GREEN);
    }
}
