import java.util.HashSet;
import java.awt.Point;
import java.util.Random;
import java.util.HashMap;

public class Solution{

    public static boolean gameFinished = false;
    static int mapMax = 10;
    static int mapMin = 10;

    public static HashSet<Point> mineMap = new HashSet<Point>();

    public static HashMap<Point,Integer> nextToMineMap = new HashMap<Point,Integer>();

    public static String[][] clientMap = new String[mapMax][mapMax];

    public static void main(String[] args){

        initMineList();
        initNextToMineList();
        initClientMap();
        initIntro();
        //printClientMap();
        printRawClientMap();
        // while(!gameFinished);
        // {

        // }
    }

    public static void initClientMap(){
        for(int i = 0; i < clientMap.length; i++){
            for(int j = 0; j < clientMap[i].length; j++){
                clientMap[i][j] = "0";
            }
        }
    }

    public static void initIntro(){
        
    }

    public static Point handleInput(String input){
        Point p = new Point();
        return p;
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
        while(mineMap.size() != 10){
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