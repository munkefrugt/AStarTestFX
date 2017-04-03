package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by v on 4/1/17.
 */
public class Setup {
    Group root;
    int blockSize;
    NodeObject[][] nodeObject;

    public Setup(Group root, NodeObject[][] nodeObject, int blockSize) {
       // nodeObject = new NodeObject[10][10];

        this.root=root;
        this.nodeObject = nodeObject;
        this.blockSize = blockSize;


    }
    // make nodes,and rectangles
    public void startUpBoard() {
        // this makes an a array of NodeObjects
        //Rectangle[][] rect = new Rectangle[10][10];

        for(int y = 0; y < 10; y++) {

            // make an array of squares.
            for(int z = 0; z < 10; z++) {
                // here the objects are made.
                nodeObject[z][y]=new NodeObject(z,y,root,blockSize);
                // make a square
                //Transparent rectangle with Stroke
                nodeObject[z][y].makeNodeRectangle();


            }
        }

        // prints the nodes
        for(int y = 0; y < 10; y++) {

            for(int z = 0; z < 10; z++) {


                System.out.println("print nodeObject["+z+y+"] return unique z and y value for obj=  "+
                        nodeObject[z][y].getUniqueZval()+ " ," + nodeObject[z][y].getUniqueYval());
            }
        }

    }


}
