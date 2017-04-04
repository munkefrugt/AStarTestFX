package sample;

import javafx.scene.Group;

import java.util.ArrayList;

/**
 * Created by v on 4/3/17.
 */
public class A_star {


    int boxesY;
    int boxesX;
    int blockSize;
    NodeObject[][] nodeObject;
    Group root;

    /*
    psudo code in steps:

    initialize the open list
    initialize the closed list
    put the starting node on the open list (you can leave its f at zero)

    while the open list is not empty
        find the node with the least f on the open list, call it "q"
        pop q off the open list
        generate q's 8 successors and set their parents to q
        for each successor
            if successor is the goal, stop the search
            successor.g = q.g + distance between successor and q
            successor.h = distance from goal to successor
            successor.f = successor.g + successor.h

            if a node with the same position as successor is in the OPEN list \
                which has a lower f than successor, skip this successor
            if a node with the same position as successor is in the CLOSED list \
                which has a lower f than successor, skip this successor
            otherwise, add the node to the open list
        end
        push q on the closed list
    end
    */


    public A_star(Group root, NodeObject[][] nodeObject, int blockSize, int boxesX, int boxesY){

        this.root=root;
        this.nodeObject = nodeObject;
        this.blockSize = blockSize;
        this.boxesX = boxesX;
        this.boxesY = boxesY;


        //initialize the open list
        // The set of currently discovered nodes that are not evaluated yet.
        // Initially, only the start node is known.
        // nodes that needs to be checked.
        // we add nodes to openlist for each step when the A* tries to find the next values.
        ArrayList<NodeObject> openList = new ArrayList<NodeObject>();






    // when nodes are checked they go on closed list.
    //initialize the closed list     // The set of nodes already evaluated.
        ArrayList<NodeObject> closedList = new ArrayList<NodeObject>();


    // put the starting node on the closed list (you can leave its f at zero)

        // pretend start very top corners
        closedList.add(nodeObject[1][1]);
        nodeObject[1][1].setFZero();
        nodeObject[1][1].makeGreen();

        ArrayList<NodeObject> neighborsNodes = new ArrayList<NodeObject>();
        ArrayList<NodeObject> ParrentNodes = new ArrayList<NodeObject>();



        /*

    while the open list is not empty
    */


        // replace "true" with is openlistempty..
        int i=0;
        while( i<1)
        {


        //find the node with the least f on the open list, call it "q", search the neighbors
            // it has max 4 neighborsNodes
            System.out.println("make neighborsNodes");
            // find the neighborNodes

            // change 0.
            int CurrentCenterNodeX = closedList.get(0).getUniqueXval();
            System.out.println(CurrentCenterNodeX);
            int CurrentCenterNodeY = closedList.get(0).getUniqueYval();
            System.out.println(CurrentCenterNodeY);

            // check node above
            // check if node is wall.
            // if wall is false
            // check below
            if(!nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].getisWall())
            {
                System.out.println("node below not wall");
                int nodeUnderY = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].getUniqueYval();
                nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].setRectColor();
                System.out.println("abovenodeY" + nodeUnderY);

            }
            else {
                System.out.println("wall at y : " + (CurrentCenterNodeY + 1));
            }

            // check above
            if (!nodeObject[CurrentCenterNodeX][CurrentCenterNodeY-1].getisWall())
            {
                System.out.println("node above not wall");
                int nodeOverY = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY-1].getUniqueYval();
                nodeObject[CurrentCenterNodeX][CurrentCenterNodeY-1].setRectColor();
                System.out.println("nodeOverY" + nodeOverY);

            }
            else {
                System.out.println("wall at y : " + (CurrentCenterNodeY - 1));
            }

            // check left
            if (!nodeObject[CurrentCenterNodeX-1][CurrentCenterNodeY].getisWall())
            {
                System.out.println("node above not wall");
                int nodeOverY = nodeObject[CurrentCenterNodeX-1][CurrentCenterNodeY].getUniqueYval();
                nodeObject[CurrentCenterNodeX-1][CurrentCenterNodeY].setRectColor();
                System.out.println("nodeOverY" + nodeOverY);

            }
            else {
                System.out.println("wall at X : " + (CurrentCenterNodeX - 1));
            }

            // check right
            if (!nodeObject[CurrentCenterNodeX+1][CurrentCenterNodeY].getisWall())
            {
                System.out.println("node above not wall");
                int nodeOverY = nodeObject[CurrentCenterNodeX+1][CurrentCenterNodeY].getUniqueYval();
                nodeObject[CurrentCenterNodeX+1][CurrentCenterNodeY].setRectColor();
                System.out.println("nodeOverY" + nodeOverY);

            }
            else {
                System.out.println("wall at X : " + (CurrentCenterNodeX + 1));
            }










        /*

        pop q off the open list
        generate q's 8 successors and set their parents to q
        for each successor
            if successor is the goal, stop the search
            successor.g = q.g + distance between successor and q
            successor.h = distance from goal to successor
            successor.f = successor.g + successor.h

            if a node with the same position as successor is in the OPEN list \
                which has a lower f than successor, skip this successor
            if a node with the same position as successor is in the CLOSED list \
                which has a lower f than successor, skip this successor
            otherwise, add the node to the open list
        end
        push q on the closed list

        */
            i++;
        }

    }

}
