package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by v on 4/1/17.
 */
public class NodeObject {
    int blockSize;
    Group root;
    int z;
    int y;
    boolean isWall = false;
    boolean isNodePacman = false;



    public NodeObject(int z, int y, Group root, int blockSize) {
        this.root= root;
        this.blockSize= blockSize;
        this.z = z;
        this.y = y;




    }


    public int getUniqueZval() {
        return z;
    }

    public int getUniqueYval()
    {
        return y;
    }

    public void makeNodeRectangle() {
        if(!isWall)
        {

        Rectangle rect= new Rectangle(z*blockSize, y*blockSize, blockSize, blockSize);

        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.RED);
        // set line thickness
        rect.setStrokeWidth(1);

        // add to root
        root.getChildren().add(rect);

        }
        else if(isWall)
        {
            Rectangle rectwall= new Rectangle(z*blockSize, y*blockSize, blockSize, blockSize);

            rectwall.setFill(Color.BLACK);



            // add to root
            root.getChildren().add(rectwall);
        }
    }

    public void makeNodewall() {
        isWall = true;
    }
}
