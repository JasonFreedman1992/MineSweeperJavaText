import java.util.HashSet;
import java.util.Scanner;
import java.awt.Point;
import java.util.Random;
import java.util.HashMap;

public class Solution{

    public static Scanner scanner = new Scanner(System.in);

    public static boolean gameFinished = false;
    public static boolean gameLost = false;

    public static int mapMax = 10;
    public static int mapMin = 10;

    public static HashSet<Point> mineMap = new HashSet<Point>();

    public static HashMap<Point,Integer> nextToMineMap = new HashMap<Point,Integer>();

    public static String[][] clientMap = new String[mapMax][mapMax];

    public static HashSet<Point> foundMap = new HashSet<Point>();

    public static void state(Point selected){
        if(mineMap.contains(selected)){
            System.out.println("hit mine");
            gameFinished = true;
            gameLost = true;
        }
        
        if(nextToMineMap.containsKey(selected) && !gameLost){
            System.out.println("found next to mine map");
            clientMap[(int) selected.getX()][(int) selected.getY()] = Integer.toString(nextToMineMap.get(selected));
        }
        else if(!nextToMineMap.containsKey(selected) && !gameLost){
            //
            // todo: write logic to map out areas that are 
            //
            System.out.println("did not hit mine");
        }   
    }

    public static void main(String[] args) throws Exception{

        initClientMap();
        initMineList();
        initNextToMineList();
        initIntro();
        // printClientMap();
        // printRawClientMap();
        // println(scanner.nextLine());

        while(gameFinished == false){
            String input = scanner.nextLine();
            Point selected = handleInput(input);
            state(selected);
            printRawClientMap();
            // gameFinished = true;
        }

        if(!gameLost){
            println("Congrats you won!");
        }
        else{
            println("You lost!");
        }
    }

    public static void initClientMap(){
        for(int i = 0; i < clientMap.length; i++){
            for(int j = 0; j < clientMap[i].length; j++){
                clientMap[i][j] = "0";
            }
        }
    }

    public static Point handleInput(String input) throws Exception {
        Point p = new Point();
        try{
            String[] inputSplit = input.split(",");
            StringBuilder s = new StringBuilder();
            for(int i = 0; i < inputSplit[0].length(); i++){
                char c = inputSplit[0].charAt(i);
                if(Character.isDigit(c)){
                    s.append(c);
                }
            }
            int tempX = Integer.parseInt(s.toString());
            p.x = Integer.parseInt(s.toString());
            
            s.setLength(0);
    
            for(int i = 0; i < inputSplit[1].length(); i++){
                char c = inputSplit[1].charAt(i);
                if(Character.isDigit(c)){
                    s.append(c);
                }
            }
            p.y = Integer.parseInt(s.toString());
        }
        catch(Exception e){
            System.out.println(e);
        }
        return p;
    }

    public static void initIntro(){
        System.out.println("Welcome to MineSweeper, here is the map.");
        System.out.println("--------------------");
        printClientMap();
        System.out.println("--------------------");
        System.out.println("Enter move x then y using 0-9.");
        System.out.println("Sample Input: 3,5");
        System.out.println("Use a comma to space your numbers");
        System.out.println("We will use the first 2 numbers 0-9 the scanner finds.");
    }

    public static void printRawClientMap(){
        for(int i = 0; i < clientMap.length; i++){
            for(int j = 0; j < clientMap[i].length; j++){
                print("|");
                Point p = new Point(i,j);
                if(mineMap.contains(p)){
                    print("X");
                }
                else if(nextToMineMap.containsKey(p)){
                    print(nextToMineMap.get(p));
                }
                else{
                    print("0");
                }
            }
            print("|");
            println("");
        }
    }

    public static void printClientMap(){
        for(int i = 0; i < clientMap.length; i++){
            for(int j = 0; j < clientMap[i].length; j++){
                print("|");
                print(clientMap[i][j]);
            }
            print("|");
            println("");
        }
    }
    
    public static void print(Object o){
        System.out.print(o);
    }

    public static void println(Object o){
        System.out.println(o);
    }

    public static void initMineList(){
        Random r = new Random();
        while(mineMap.size() != mapMax){
            int tempX = r.nextInt(mapMax);
            int tempY = r.nextInt(mapMax);
            Point p = new Point(tempX, tempY);
            mineMap.add(p);
        }
    }

    public static boolean isNextToMine(int i, int j){
        Point p = new Point(i, j);
        if(mineMap.contains(p)){
            return true;
        }
        else{
            return false;
        }
    }

    public static void initNextToMineList(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){

                if(goodInt(i+1) && goodInt(j+1)){
                    if(isNextToMine(i+1, j+1)){
                        Point p = new Point(i, j);
                        if(!nextToMineMap.containsKey(p)){
                            nextToMineMap.put(p, 1);
                        }
                        else{
                            nextToMineMap.put(p, nextToMineMap.get(p)+1);
                        }
                    }
                }

                if(goodInt(i) && goodInt(j+1)){
                    if(isNextToMine(i, j+1)){
                        Point p = new Point(i, j);
                        if(!nextToMineMap.containsKey(p)){
                            nextToMineMap.put(p, 1);
                        }
                        else{
                            nextToMineMap.put(p, nextToMineMap.get(p)+1);
                        }
                    }
                }

                if(goodInt(i-1) && goodInt(j+1)){
                    if(isNextToMine(i-1, j+1)){
                        Point p = new Point(i, j);
                        if(!nextToMineMap.containsKey(p)){
                            nextToMineMap.put(p, 1);
                        }
                        else{
                            nextToMineMap.put(p, nextToMineMap.get(p)+1);
                        }
                    }
                }

                if(goodInt(i-1) && goodInt(j)){
                    if(isNextToMine(i-1, j)){
                        Point p = new Point(i, j);
                        if(!nextToMineMap.containsKey(p)){
                            nextToMineMap.put(p, 1);
                        }
                        else{
                            nextToMineMap.put(p, nextToMineMap.get(p)+1);
                        }
                    }
                }

                if(goodInt(i-1) && goodInt(j-1)){
                    if(isNextToMine(i-1, j-1)){
                        Point p = new Point(i, j);
                        if(!nextToMineMap.containsKey(p)){
                            nextToMineMap.put(p, 1);
                        }
                        else{
                            nextToMineMap.put(p, nextToMineMap.get(p)+1);
                        }
                    }
                }

                if(goodInt(i) && goodInt(j-1)){
                    if(isNextToMine(i, j-1)){
                        Point p = new Point(i, j);
                        if(!nextToMineMap.containsKey(p)){
                            nextToMineMap.put(p, 1);
                        }
                        else{
                            nextToMineMap.put(p, nextToMineMap.get(p)+1);
                        }
                    }
                }

                if(goodInt(i+1) && goodInt(j-1)){
                    if(isNextToMine(i+1, j-1)){
                        Point p = new Point(i, j);
                        if(!nextToMineMap.containsKey(p)){
                            nextToMineMap.put(p, 1);
                        }
                        else{
                            nextToMineMap.put(p, nextToMineMap.get(p)+1);
                        }
                    }
                }

                if(goodInt(i+1) && goodInt(j)){
                    if(isNextToMine(i+1, j)){
                        Point p = new Point(i, j);
                        if(!nextToMineMap.containsKey(p)){
                            nextToMineMap.put(p, 1);
                        }
                        else{
                            nextToMineMap.put(p, nextToMineMap.get(p)+1);
                        }
                    }
                }
            }
        }
    }

    public static boolean goodInt(int x){
        if(x < 0 || x > 9){
            return false;
        }
        else{
            return true;
        }
    }

}