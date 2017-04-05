package sample;

import javafx.scene.Group;

import java.util.ArrayList;

/**
 * Created by v on 4/3/17.
 */
public class A_star {
    int CurrentCenterNodeX;
    int CurrentCenterNodeY;

    // the node that its is currently checking from is the current center node, from here it finds neighbors,
    // in each step as the a-star goes along, the center node is changed.


    int boxesY;
    int boxesX;
    int blockSize;
    NodeObject[][] nodeObject;
    Group root;
    int g =10; // can be changed if needed.
    int goalX =8, goalY =8;
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

        // pretend start very top corners inside boarder

        //startNode
        int startX=4;
        int startY=4;
        closedList.add(nodeObject[startX][startY]);
        nodeObject[startX][startY].setFZero();
        nodeObject[startX][startY].makeGreen();


        CurrentCenterNodeX= nodeObject[startX][startY].getUniqueXval();
        CurrentCenterNodeY= nodeObject[startX][startY].getUniqueYval();


        System.out.println("startnode  x,y"+ nodeObject[1][1].getUniqueXval() + ","+ nodeObject[1][1].getUniqueYval());
        // end node is in opposite corner

        nodeObject[goalX][goalY].makeRed();
        System.out.println("startnode  x,y"+ nodeObject[goalX][goalY].getUniqueXval() + ","+ nodeObject[goalX][goalY].getUniqueYval());


        ArrayList<NodeObject> neighborsNodes = new ArrayList<NodeObject>();
        ArrayList<NodeObject> ParrentNodes = new ArrayList<NodeObject>();








        // replace "true" with is openlistempty..
        int i=0;


        // the main loop that makes the a-star  **********************************
          //while the open list is not empty

        while( i<1)
        {


        //find the node with the least f on the open list, call it "q", search the neighbors
            // it has max 4 neighborsNodes
            System.out.println("make neighborsNodes");
            // find the neighborNodes
            findAndcheckNeighborsNodes();












        /*

        pop q off the open list
        generate q's 8 successors and set their parents to q
        for each successor
            if successor is the goal, stop the search
            successor.g = q.g + distance between successor and q
            successor.h = distance from goal to successor
            successor.f = successor.g + successor.h

            if a node with the same position as successor is in the OPEN list \
         // if wall is false
        // check below



        if(!nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].getisWall())
        {
            // if node is goal node break out of loop.
            //if (nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1] = )

            System.out.println("node below not wall");
            int nodeUnderY = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].getUniqueYval();
            nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].setRectColor();
            System.out.println("abovenodeY" + nodeUnderY);

            // calculate  first :
            int h= getAndcalculateH(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1]);

            int f= g+h;
            System.out.println("F = "+f);
            //int h = calculateH(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1]);
            //int h = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].calculateAndGetH();
            //neighborsNodes.add(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1]);

        }
        else {
            System.out.println("wall at y : " + (CurrentCenterNodeY + 1));
        }
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

    private void findAndcheckNeighborsNodes() {




        // check above
        if (!nodeObject[CurrentCenterNodeX][CurrentCenterNodeY-1].getisWall())
        {
            System.out.println("node above not wall");
            int nodeOverY = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY-1].getUniqueYval();
            nodeObject[CurrentCenterNodeX][CurrentCenterNodeY-1].setRectColor();
            System.out.println("nodeOverY" + nodeOverY);
            int h= getAndcalculateH(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY-1]);
            int f= g+h;
            System.out.println("F = "+f);
        }
        else {
            System.out.println("wall at y : " + (CurrentCenterNodeY - 1));
        }

        // if wall is false
        // check below



        if(!nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].getisWall())
        {
            // if node is goal node break out of loop.
            //if (nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1] = )

            System.out.println("node below not wall");
            int nodeUnderY = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].getUniqueYval();
            nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].setRectColor();
            System.out.println("abovenodeY" + nodeUnderY);

            // calculate  first :
            int h= getAndcalculateH(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1]);

            int f= g+h;
            System.out.println("F = "+f);
            //int h = calculateH(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1]);
            //int h = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].calculateAndGetH();
            //neighborsNodes.add(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1]);

        }
        else {
            System.out.println("wall at y : " + (CurrentCenterNodeY + 1));
        }

        // check left
        if (!nodeObject[CurrentCenterNodeX-1][CurrentCenterNodeY].getisWall())
        {
            System.out.println("node left not wall");
            int nodeOverY = nodeObject[CurrentCenterNodeX-1][CurrentCenterNodeY].getUniqueYval();
            nodeObject[CurrentCenterNodeX-1][CurrentCenterNodeY].setRectColor();
            System.out.println("nodeOverY" + nodeOverY);

            int h= getAndcalculateH(nodeObject[CurrentCenterNodeX-1][CurrentCenterNodeY]);
            int f= g+h;
            System.out.println("F = "+f);
        }
        else {
            System.out.println("wall at X : " + (CurrentCenterNodeX - 1));
        }

        // check right
        if (!nodeObject[CurrentCenterNodeX+1][CurrentCenterNodeY].getisWall())
        {
            System.out.println("node right not wall");
            int nodeOverY = nodeObject[CurrentCenterNodeX+1][CurrentCenterNodeY].getUniqueYval();
            nodeObject[CurrentCenterNodeX+1][CurrentCenterNodeY].setRectColor();
            System.out.println("nodeOverY" + nodeOverY);

            int h= getAndcalculateH(nodeObject[CurrentCenterNodeX+1][CurrentCenterNodeY]);
            int f= g+h;
            System.out.println("F = "+f);

        }
        else {
            System.out.println("wall at X : " + (CurrentCenterNodeX + 1));
        }


    }

    public int getAndcalculateH(NodeObject nodeObject) {
        // h is calculated as manhatten heuristics

        // ex. h= 9-3 + 9-2;

        int CurrentNeighborX = nodeObject.getUniqueXval();
        int CurrentNeighborY = nodeObject.getUniqueYval();
        int h = (goalX - CurrentNeighborX)   + (goalY - CurrentNeighborY);


        return h;
    }

}
