package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by v on 4/3/17.
 */
public class A_star {
    NodeObject goalNode;
    // the neighbornodes that are not walls are added to list list for, where they lateer are evaluated, for finding lowest f.
    Map<NodeObject, Double> OpenlistUncheckedNeighbors = new HashMap<NodeObject, Double>();

    // Closed list are visited nodes, Initially, only the start node is known. and visited,
    // we add it to the closed list in the constructor.
    ArrayList<NodeObject> closedList = new ArrayList<NodeObject>();

    // list for the Astar final path.
    ArrayList<NodeObject> finalPathNodes = new ArrayList<NodeObject>();

    //initialize the open list
    // insert the currently discovered nodes that are not evaluated yet.
    // Initially, only the start node is known.
    // nodes that needs to be checked.
    // we add nodes to openlist for each step when the A* tries to find the next values.
    // !! note!! not not all nodes will have pass through the openlist in the and, some will most likely remain untouch ,
    // in the comming a-star search.
    ArrayList<NodeObject> openList = new ArrayList<NodeObject>();
    ArrayList<NodeObject> ParrentNodes = new ArrayList<NodeObject>();



    int CurrentCenterNodeX;
    int CurrentCenterNodeY;
    int startX=3;
    int startY=6;
    NodeObject pacmanNode;
    NodeObject currentCenterNode = pacmanNode;


    // the node that its is currently checking from is the current center node, from here it finds neighbors,
    // in each step as the a-star goes along, the center node is changed.


    int boxesY;
    int boxesX;
    int blockSize;
    NodeObject[][] nodeObject;
    Group root;
    //int g =10; // can be changed if needed.
    int goalX =20, goalY =9;
    private int xAbove;
    private int yAbove;
    private int stepValueG = 1;
    // start with the pacman location
    // now just testing.
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

        pacmanNode = nodeObject[startX][startY];
        pacmanNode.makeitPacman();
        currentCenterNode = nodeObject[startX][startY];
        currentCenterNode.takeOfOpenList();
        //Make  goal node.
        goalNode= nodeObject[goalX][goalY];
        nodeObject[goalX][goalY].setRectColor(Color.RED);
        System.out.println("startnode  x,y"+ nodeObject[goalX][goalY].getUniqueXval() + ","+ nodeObject[goalX][goalY].getUniqueYval());


        // replace "true" with is openlistempty..
        int i=0;


        // the main loop that makes the a-star  **********************************
          //while the open list is not empty


        // i<3 || true // TODO replace with true
        while(true)
            // TODO remember g costs get added. each node will have a gvalue, g = g value of where it came from plus new g cost.

        {

                System.out.println("previusNode (x,y)  "+currentCenterNode.getUniqueXval()+","+currentCenterNode.getUniqueYval());

                // is current node = goal? then break out.

                if (currentCenterNode == goalNode)
                {
                    // jump out of loop
                    System.out.println("goal node is found , break *******************************");
                    break;
                }
                // clear the OpenlistUncheckedNeighbors, make room for new f values.

                //initialize the closed list     // The set of nodes already evaluated.


                // add only the starting node to the closed list in the beginning.
                // get the new node onto the closed list.
                closedList.add(currentCenterNode);




                // when nodes are checked they go on closed list.


                // put the starting node on the closed list (you can leave its f at zero)

                // pretend start very top corners inside boarder

                //startNode

                closedList.add(nodeObject[startX][startY]);
                nodeObject[startX][startY].setFZero();
                nodeObject[startX][startY].makeGreen();



                CurrentCenterNodeX= currentCenterNode.getUniqueXval(); //nodeObject[startX][startY].getUniqueXval();
                CurrentCenterNodeY= currentCenterNode.getUniqueYval();//nodeObject[startX][startY].getUniqueYval();


                //System.out.println("startnode  x,y"+ nodeObject[1][1].getUniqueXval() + ","+ nodeObject[1][1].getUniqueYval());
                // end node is in opposite corner



                //find the node with the least f on the open list, call it "q", search the neighbors
                // it has max 4 neighborsNodes
                System.out.println("make neighborsNodes");
                // find the neighborNodes
                findAndcheckNeighborsNodes();
                findLowestFAndmakeNewCenterNode();




                // TODO remove the checked node from this list and add it to the closed list.
                currentCenterNode.takeOfOpenList();




                System.out.println("************ i = "+i);
                System.out.println(CurrentCenterNodeX);
                System.out.println(CurrentCenterNodeY);




            /*



            pop q off the open list
            generate q's 4 successors and set their parents to q
            for each successor
                if successor is the goal, stop the search
                successor.g = q.g + distance between successor and q
                successor.h = distance from goal to successor
                successor.f = successor.g + successor.h

                if a node with the same position as successor is in the OPEN list \
             // if wall is false
            // check below





           which has a lower f than successor, skip this successor
                if a node with the same position as successor is in the CLOSED list \
                    which has a lower f than successor, skip this successor
                otherwise, add the node to the open list
            end
            push q on the closed list

            */
                i++;


            int currentNodeH = currentCenterNode.getH();
            System.out.println("currentNodeH? = "+ currentNodeH);
            System.out.println("currentCenterNode(x,y)"+currentCenterNode.getUniqueXval() + ","+ currentCenterNode.getUniqueYval());
            System.out.println(":::::::::::::::::::::::::::::::WHILE end::::::::::::::::::::::::");


            /*
            // test TODO change it THis block shuold print the hole tree as a list.
            Map<Integer, NodeObject> treeMap = new TreeMap<Integer, NodeObject>(
                    new Comparator<Integer>() {

                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o2.compareTo(o1);
                        }

                    });

            NavigableMap.putAll(OpenlistUncheckedNeighbors);
            printMap(OpenlistUncheckedNeighbors); */
        }

        // what is the f and h and g?


        goalNode.setRectColor(Color.RED);

        // now when the goal is found track the way back.
        makeThePath();

        gostFollowThePath();


    }

    private void gostFollowThePath() {

        // now make the Gost move one step.

    }


    private void makeThePath() {
        //int i =0;
        while(true)
        {
            if (currentCenterNode ==pacmanNode )
            {
                System.out.println("path done ");
                break;
            }

            // add the nodes to the final pathlist.
            finalPathNodes.add(currentCenterNode);

            currentCenterNode.setRectColor(Color.ORANGE);
            // change to next node.
            NodeObject previusNode = (NodeObject) currentCenterNode.getcameFrom();

            previusNode.setRectColor(Color.ORANGE);

            System.out.println("previusNode (x,y)  "+previusNode.getUniqueXval()+","+previusNode.getUniqueYval());


            // change the previus node to currentcenterNode
            currentCenterNode =  previusNode;
            //i++;

            // add every step to an arraylist.






        }

    }

    private void findLowestFAndmakeNewCenterNode() {

        //
        // TODO !!!find out how this works...


        Map.Entry<NodeObject, Double> min = Collections.min(OpenlistUncheckedNeighbors.entrySet(), new Comparator<Map.Entry<NodeObject, Double>>() {
            public int compare(Map.Entry<NodeObject, Double> entry1, Map.Entry<NodeObject, Double> entry2) {
                return entry1.getValue().compareTo(entry2.getValue());
            }
        });

        System.out.println("Min F value printed ++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(min.getValue());
        System.out.println("its key:");
        System.out.println(min.getKey());
        System.out.println();

        NodeObject newLowestNode = min.getKey();

        // set it new centerNode
        currentCenterNode = newLowestNode;
        currentCenterNode.setRectColor(Color.PINK);

        closedList.add(newLowestNode);



        // remove key pair from OpenlistUncheckedNeighbors List
        OpenlistUncheckedNeighbors.remove(min.getKey());



        //note where the  nextnode camefrom, if came from the Currentcenternode. that is done already in the previus method.



    }



    private void printMap(TreeMap<Integer, NodeObject> map) {

            for (Map.Entry<Integer, NodeObject> entry : map.entrySet()) {
                System.out.println("Key : " + entry.getKey()
                        + " Value : " + entry.getValue());
            }

    }

    // check above
    private void findAndcheckNeighborsNodes() {
        System.out.println("***********************************************checking nodes**********************************");
        // this method checks the 4 surrounding neighbors above below left and right.

        // check above
        // check if its a wall
        // and is not on closed list has to be true






        // if not wall and not on closedList
        NodeObject nodeAbove = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY - 1];

        System.out.println(nodeAbove.getisWall()+"****************************######################");
        if (!nodeAbove.getisWall() && !nodeAbove.isOnClosedList())
        {
            // todo set came form for each node.
            // the currentcenternode is the camefrom node. since thats the node where there is been checked from. !!



            nodeAbove.setCameFrom(currentCenterNode);
            System.out.println("node above not wall");
            nodeAbove.setRectColor(Color.BLUE);
            int h= getAndcalculateH(nodeAbove);
            System.out.println("h->Astar = "+h);
            // store h in node.
            nodeAbove.setH(h);
            int hFromObject = nodeAbove.getH();
            System.out.println("hFromObject" +hFromObject);



            // calculate g. g = current g value + new g value, so the g value in the end when the goal is reached is the
            //  "total  walking distance"

            nodeAbove.calculateG(stepValueG);
            int g= nodeAbove.getG();
            int f= nodeAbove.getG()+h;
            System.out.println("F = "+f +"="+g+"+"+h);
            System.out.println("coordinates = " +nodeAbove.getUniqueXval()+ ", "+ nodeAbove.getUniqueYval());


            // add to treemap openlist
            // insert f and node
            double fDouble = (double) f;
            OpenlistUncheckedNeighbors.put(nodeAbove,fDouble);


        }
        else if(!nodeAbove.isOnClosedList()){
            System.out.println("nodeAbove is on closed list");
        }
        else {
            System.out.println("wall at y : " + (CurrentCenterNodeY - 1));
        }


        // if wall is false
        // check below

            NodeObject nodebelow = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY + 1];
        System.out.println(!nodebelow.isOnClosedList());
        //&& !nodebelow.isOnClosedList()
        if(!nodebelow.getisWall() && !nodebelow.isOnClosedList() )
        {
            // if node is goal node break out of loop.
            //if (nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1] = )

            System.out.println("node below not wall");
            // set camefrom
            nodebelow.setCameFrom(currentCenterNode);
            int nodeUnderY = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].getUniqueYval();
            nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].setRectColor(Color.BLUE);
            System.out.println("abovenodeY" + nodeUnderY);

            // calculate  first :
            int h= getAndcalculateH(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1]);
            nodebelow.calculateG(stepValueG);
            int g= nodebelow.getG();

            int f= g+h;

            System.out.println("F = "+f +"="+g+"+"+h);
            //int h = calculateH(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1]);
            //int h = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].calculateAndGetH();
            //neighborsNodes.add(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1]);



            // insert f and node
            System.out.println("coordinates = " +nodebelow.getUniqueXval()+ ", "+ nodebelow.getUniqueYval());
            double fDouble =  (double) f;
            OpenlistUncheckedNeighbors.put(nodebelow,fDouble);



        }
        else if(!nodebelow.isOnClosedList()){
            System.out.println("nodebelow is on closed list");
        }

        else {
            System.out.println("wall at y : " + (CurrentCenterNodeY + 1));
        }

        // check left
            NodeObject nodeLeft = nodeObject[CurrentCenterNodeX - 1][CurrentCenterNodeY];

        // && !nodeLeft.isOnClosedList()
        if (!nodeLeft.getisWall() && !nodeLeft.isOnClosedList())
        {
            // set camefrom
            nodeLeft.setCameFrom(currentCenterNode);
            System.out.println("node left not wall");
            nodeObject[CurrentCenterNodeX-1][CurrentCenterNodeY].setRectColor(Color.BLUE);

            int h= getAndcalculateH(nodeObject[CurrentCenterNodeX-1][CurrentCenterNodeY]);
            nodeLeft.calculateG(stepValueG);
            int g= nodeLeft.getG();
            int f= g+h;
            System.out.println("F = "+f +"="+g+"+"+h);
            System.out.println("coordinates = " +nodeLeft.getUniqueXval()+ ", "+ nodeLeft.getUniqueYval());


            // insert f and node
            double FDouble =  (double) f;
            OpenlistUncheckedNeighbors.put(nodeLeft,FDouble);

        }
        else if(!nodeLeft.isOnClosedList()){
            System.out.println("nodeLeft is on closed list");
        }
        else {
            System.out.println("wall at X : " + (CurrentCenterNodeX - 1));
        }

        // check right
            NodeObject noderight = nodeObject[CurrentCenterNodeX + 1][CurrentCenterNodeY];
        //&& !noderight.isOnClosedList()
        if (!noderight.getisWall() && !noderight.isOnClosedList())
        {
            // set camefrom
            noderight.setCameFrom(currentCenterNode);

            System.out.println("node right not wall");
            nodeObject[CurrentCenterNodeX+1][CurrentCenterNodeY].setRectColor(Color.BLUE);

            int h= getAndcalculateH(nodeObject[CurrentCenterNodeX+1][CurrentCenterNodeY]);

            noderight.calculateG(stepValueG);
            int g= noderight.getG();
            int f= g+h;
            System.out.println("F = "+f);
            System.out.println("coordinates = " +noderight.getUniqueXval()+ ", "+ noderight.getUniqueYval());

            // insert f and node
            double fDouble = (double) f;
            OpenlistUncheckedNeighbors.put(noderight,fDouble);


        }
        else if(!noderight.isOnClosedList()) {
            System.out.println("noderight is on closed list");
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
        int h = Math.abs(CurrentNeighborX-goalX)   + Math.abs(CurrentNeighborY - goalY);


        return h;
    }

}
