
public class Lab4 extends Robot
{
    public static void turnRight()
    {
        Robot.turnLeft();
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void turnAround()
    {
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void backUp()
    {
        turnAround();
        Robot.move();
        turnAround();
    }

    public static void completeBars()
    {
        //insert instructions below
        for(int i=1;i<=20;i++){
            if(frontIsClear()){
                turnLeft();
                completeCol();
                repositionRobot();
            } else{
                turnLeft();
                completeCol();
                return;
            }
            turnRight();
        }

    }

    public static void testCompleteBars1()
    {
        Robot.load("bars1.txt");
        Robot.setDelay(0.025);
        completeBars();
    }

    public static void testCompleteBars2()
    {
        Robot.load("bars2.txt");
        Robot.setDelay(0.025);
        completeBars();
    }

    public static void combinePiles()
    {
        //insert instructions below
        combineOne();
    }

    public static void testCombinePiles1()
    {
        Robot.load("piles1.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }

    public static void testCombinePiles2()
    {
        Robot.load("piles2.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }

    public static void shiftDownLeft(){
        //pre robot has two cells behind it
        //pst robot moves down two, facing left to origin direction
        turnAround();
        move();
        move();
        turnAround();
        turnLeft();
    }

    public static void connectDots()
    {
        //insert instructions below
        fillDot();
        turnRight();
        for(int i=1; i<=3; i++){    
            move();
            move();
            if(onDark()){
                shiftDownLeft();
                connectDots();
                return;
            } else {
                shiftDownLeft();
            }
        }

    }

    public static void testConnectDots1()
    {
        Robot.load("connect1.txt");
        Robot.setDelay(0.025);
        connectDots();
    }

    public static void testConnectDots2()
    {
        Robot.load("connect2.txt");
        Robot.setDelay(0.025);
        connectDots();
    }

    public static void fillCol(){
        //pre robot is facing 180 from horizontal axis upward
        //post robot is at end of cells that were behind it, with all of them filled
        turnAround();
        while (frontIsClear()){
            move();
            makeDark();
        }

    }

    public static void completeCol(){
        //pre Robot facing upward, infront of cells and one dark cell
        //post Robot is in sameplace, facing downward, with all cells in pre dark
        while(onDark()==false){
            move();
        }
        fillCol();
    }

    public static void repositionRobot(){
        //pre robot is facing upward
        //post robot is either in the same spot or in the cell left to it (either way facing upward)
        turnLeft();
        if(frontIsClear()){
            move();
            turnLeft();
        }else{
            return;
        }
    }

    public static void fillDot(){
        //pre robot on dot, with "connected" previous dot behind it
        //post robot either "connects" dot in front of it facing same direction and is on the connected dot or in same place
        turnRight();
        for(int i=1; i<=3; i++){
            move();
            move();
            if(onDark()){
                backUp();
                makeDark();
                move();
                return;
            } else {
                shiftDownLeft();
            }
        }
    }

    public static void moveUpTopSquare(){
        //pre robot on a possible col of dark cells, facing upward or facing rest of dark cells
        //post robot is on last possible dark cell (if not on dark cell origin will stay where it is or will stay if it was on last dark cell)
        while(onDark()){
            move();
        }
        backUp();
    }

    public static void moveDownTopSquare(){
        //pre robot is on "top" of a col of dark cells (with possible white cell on bottom)
        //post robot is at the bottom of col of dark cells, or will stop when it hits a wall or is already on light cell
        while(onDark()==false & frontIsClear()){
            move();
        }
    }

    public static void moveSquare(){
        //pre robot is on dark cell facing upwards, with another col to the right of it
        //post robot is on +1 top of col to the right of it which is dark, and cell it was on is dark
        if(onDark()){
            makeLight();
            turnRight();
            move();
            turnLeft();
            while(onDark()){
                move();
            }
            makeDark();
        }
    }

    public static void combineOne(){
        //pre robot is facing up with col to its right, at the bottom of a line of possible dark cells
        //post robot is facing down from its origin, dark cells in its col all gone added onto the col to its right
        moveSquare();
        if(frontIsClear()){
            move();
        }
        turnLeft();
        move();
        turnLeft();
        moveDownTopSquare();
        if(onDark()){
            while(onDark()){
                move();
            }
            backUp();
            turnAround();
            combineOne();
        }
    }
}
