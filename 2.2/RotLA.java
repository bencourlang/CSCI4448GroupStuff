import java.awt.*;

import javax.lang.model.util.ElementScanner6;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.Random;

public class RotLA{
    public static void main(String[] args){
        Facility f = new Facility();

        Adventurer b = new Brawler();
        Adventurer sneak = new Sneakers();
        Adventurer r = new Runners();
        Adventurer t = new Thieves();

        Creature o = new Orbiters();
        Creature see = new Seekers();
        Creature blink = new Blinkers();

        
        ArrayList<ArrayList<ArrayList<String>>> map = f.makeRooms();

        f.updateRooms(b, sneak, r, t, o, see, blink, map);

        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.size()-2; j++){
                System.out.println(map.get(i).get(j));
            }
            System.out.println();
        }

        /*Scanner s = new Scanner(System.in);

        Facility f = new Facility();
        ArrayList<ArrayList<ArrayList<String>>> map = f.makeRooms();

        String p = "it's a me";
        String store = map.get(0).get(1).get(1);
        map.get(0).get(1).set(1, p);

        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.size()-2; j++){
                System.out.println(map.get(i).get(j));
            }
            System.out.println();
        }

        //System.out.println("n, s, e, w, u, d");
        String dir = s.nextLine();

        while(dir != null){
            if(dir.isEmpty()){
                break;
            }
    
            ArrayList<Integer> pPos = new ArrayList<>();
            for(int i = 0; i < map.size()-2; i++){
                for(int j = 0; j < map.size()-2; j++){
                    for(int k = 0; k < map.size()-2; k++){
                        //System.out.println(i + " " + j + " " + k);
                        if(map.get(i).get(j).get(k) == p){
                            pPos.add(i);
                            pPos.add(j);
                            pPos.add(k);
                        }
                    }
                }
            }

            Schmoovement thing = new Schmoovement(pPos);
    
            if(pPos.get(1) == 1 && pPos.get(2) == 1){
                //System.out.println("n, s, e, w, u, d");
                if(dir.equals("n")){
                    System.out.println("north");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y-1).get(z);
                    map.get(x).get(y-1).set(z, p);
                }
                else if(dir.equals("s")){
                    System.out.println("south");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y+1).get(z);
                    map.get(x).get(y+1).set(z, p);
                }
                else if(dir.equals("e")){
                    System.out.println("east");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y).get(z+1);
                    map.get(x).get(y).set(z+1, p);
                }
                else if(dir.equals("w")){
                    System.out.println("west");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
                    
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y).get(z-1);
                    map.get(x).get(y).set(z-1, p);
                }
                else if(dir.equals("u")){
                    System.out.println("up");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(z).set(y, store);
                    store = map.get(x+1).get(y).get(z);
                    map.get(x+1).get(z).set(y, p);
                }
                else if(dir.equals("d")){
                    System.out.println("down");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(z).set(y, store);
                    store = map.get(x-1).get(y).get(z);
                    map.get(x-1).get(z).set(y, p);
                }
                else{
                    System.out.println("kys");
                }
            }
            else{
                //System.out.println("n, s, e, w");
                if(dir.equals("n")){
                    System.out.println("north");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y-1).get(z);
                    map.get(x).get(y-1).set(z, p);
                }
                else if(dir.equals("s")){
                    System.out.println("south");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y+1).get(z);
                    map.get(x).get(y+1).set(z, p);
                }
                else if(dir.equals("e")){
                    System.out.println("east");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
        
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y).get(z+1);
                    map.get(x).get(y).set(z+1, p);
                }
                else if(dir.equals("w")){
                    System.out.println("west");
        
                    int x = pPos.get(0);
                    int y = pPos.get(1);
                    int z = pPos.get(2);
                    
                    map.get(x).get(y).set(z, store);
                    store = map.get(x).get(y).get(z-1);
                    map.get(x).get(y).set(z-1, p);
                }
                else{
                    System.out.println("kys");
                }
            }
    
            for(int i = 0; i < map.size(); i++){
                for(int j = 0; j < map.size()-2; j++){
                    System.out.println(map.get(i).get(j));
                }
                System.out.println();
            }
            dir = s.nextLine();
        }*/
    }
}

class Facility{
    Facility(){}

    public static ArrayList<ArrayList<ArrayList<String>>> makeRooms(){
        int x_axis_length = 3;
        int y_axis_length = 3;
        int z_axis_length = 5;	
        ArrayList<ArrayList<ArrayList<String>>> map = new ArrayList<>(x_axis_length);

        for (int i = 0; i < z_axis_length; i++) {
            map.add(new ArrayList<ArrayList<String>>(y_axis_length));
            for (int j = 0; j < y_axis_length; j++) {
                map.get(i).add(new ArrayList<String>(z_axis_length));
            }
        }

        map.get(0).set(0, new ArrayList<String>(Arrays.asList("0-0-0: - : -", "0-1-0: - : -", "0-2-0: - : -")));
        map.get(0).set(1, new ArrayList<String>(Arrays.asList("0-0-1: - : -", "0-1-1: - : -", "0-2-1: - : -")));
        map.get(0).set(2, new ArrayList<String>(Arrays.asList("0-1-2: - : -", "0-1-2: - : -", "0-2-2: - : -")));

        map.get(1).set(0, new ArrayList<String>(Arrays.asList("1-0-0: - : -", "1-1-0: - : -", "1-2-0: - : -")));
        map.get(1).set(1, new ArrayList<String>(Arrays.asList("1-0-1: - : -", "1-1-1: - : -", "1-2-1: - : -")));
        map.get(1).set(2, new ArrayList<String>(Arrays.asList("1-1-2: - : -", "1-1-2: - : -", "1-2-2: - : -")));

        map.get(2).set(0, new ArrayList<String>(Arrays.asList("2-0-0: - : -", "2-1-0: - : -", "2-2-0: - : -")));
        map.get(2).set(1, new ArrayList<String>(Arrays.asList("2-0-1: - : -", "2-1-1: - : -", "2-2-1: - : -")));
        map.get(2).set(2, new ArrayList<String>(Arrays.asList("2-1-2: - : -", "2-1-2: - : -", "2-2-2: - : -")));

        map.get(3).set(0, new ArrayList<String>(Arrays.asList("3-0-0: - : -", "3-1-0: - : -", "3-2-0: - : -")));
        map.get(3).set(1, new ArrayList<String>(Arrays.asList("3-0-1: - : -", "3-1-1: - : -", "3-2-1: - : -")));
        map.get(3).set(2, new ArrayList<String>(Arrays.asList("3-1-2: - : -", "3-1-2: - : -", "3-2-2: - : -")));

        map.get(4).set(0, new ArrayList<String>(Arrays.asList("4-0-0: - : -", "4-1-0: - : -", "4-2-0: - : -")));
        map.get(4).set(1, new ArrayList<String>(Arrays.asList("4-0-1: - : -", "4-1-1: - : -", "4-2-1: - : -")));
        map.get(4).set(2, new ArrayList<String>(Arrays.asList("4-1-2: - : -", "4-1-2: - : -", "4-2-2: - : -")));

        return map;
    }

    /*public static void populateRooms(Adventurer b, Adventurer sneak, Adventurer r, Adventurer t, Creature o, Creature see, Creature blink, ArrayList<ArrayList<ArrayList<String>>> map){
    //public static void populateRooms(Adventurer b, Creature o, ArrayList<ArrayList<ArrayList<String>>> map){
        //ArrayList<ArrayList<ArrayList<String>>> map = f.makeRooms();
        updateRooms(map, b, o);
        ArrayList<Integer> orbSpot = o.getLoc();
        map.get(orbSpot.get(0)).get(orbSpot.get(1)).get(orbSpot.get(2));
        
        //adventure starts at 0-1-1
        //orbiters starts in outer rooms
        //seekers randomly placed
        //blinkers start randomly on level 4
    }*/

    public static ArrayList<ArrayList<ArrayList<String>>> updateRooms(Adventurer b, Adventurer sneak, Adventurer r, Adventurer t, Creature o, Creature see, Creature blink, ArrayList<ArrayList<ArrayList<String>>> map){
        //Check if null
        //if null put -
        //if not null put name
        //if multiple in 1 room, adv on left cre on right
        //check for mult adv and cre same room, do something if so

        ArrayList<Adventurer> aliveAdv = new ArrayList<>();
        if(b.getName() != "-"){
            aliveAdv.add(b);
        }
        if(sneak.getName() != "-"){
            aliveAdv.add(sneak);
        }
        if(r.getName() != "-"){
            aliveAdv.add(r);
        }
        if(t.getName() != "-"){
            aliveAdv.add(t);
        }


        for(int i = 0; i < aliveAdv.size(); i++){
            ArrayList<Adventurer> addAdv = new ArrayList<>();
            addAdv.add(aliveAdv.get(i));
            for(int j = 0; j < aliveAdv.size(); j++){
                if(aliveAdv.get(i).getLocation().equals(aliveAdv.get(j).getLocation())){
                    addAdv.add(aliveAdv.get(j));
                }
            }

            String names = "";
            for(int j = 0; j < addAdv.size(); j++){
                if(j == addAdv.size()-1){
                    names += addAdv.get(j);
                }
                else{
                    names += addAdv.get(j).getName() + ", ";
                }
            }
        
            ArrayList<Integer> advSpot = addAdv.get(i).getLocation();
            map.get(advSpot.get(0)).get(advSpot.get(1)).set(advSpot.get(2), advSpot.get(0) + "-" + advSpot.get(1) + "-" + advSpot.get(2) + ": " + names + " : -");       
        }

        ArrayList<Creature> aliveCre = new ArrayList<>();
        if(o.getName() != "-"){
            aliveCre.add(o);
        }
        if(see.getName() != "-"){
            aliveCre.add(see);
        }
        if(blink.getName() != "-"){
            aliveCre.add(blink);
        }



        // ArrayList<Integer> bSpot = b.getLocation();
        
        // String curSpot = map.get(bSpot.get(0)).get(bSpot.get(1)).get(bSpot.get(2));
        // map.get(bSpot.get(0)).get(bSpot.get(1)).set(bSpot.get(2), bSpot.get(0) + "-" + bSpot.get(1) + "-" + bSpot.get(2) + ": " + b.getName() + " : -");
        // //curSpot = aSpot.get(0) + "-" + aSpot.get(1) + "-" + aSpot.get(2) + ": " + a.getName() + " : -";

        return map;
    }
}

abstract class Adventurer{
    int hp;
    ArrayList<Integer> location = new ArrayList<>(); //going to update
    int treasure;
    String name;
    Adventurer(){
        hp = 3;
        location.add(0);
        location.add(1);
        location.add(1);
        treasure = 0;
    }

    void setLocation(ArrayList<Integer> xyz){
        location.clear();
        location.add(xyz.get(0));
        location.add(xyz.get(1));
        location.add(xyz.get(2));
    }

    ArrayList<Integer> getLocation(){
        return location;
    }
    

    int fight(){
        int sum = 0;
        //roll 2 d6
        //call fight class 
        return sum;
    }

    boolean findTreasure(){
        //roll 2 die 
        //if above 10, they get a treasure (treasure +=1)
        return true;
    }

    public static int randDir(ArrayList<Integer> opt){
        int movDir = -1;
        Random r = new Random();
        return opt.get(r.nextInt(opt.size()-1));
    }

    String getName(){
        return name;
    }
    
}

class Brawler extends Adventurer{
    Brawler(){
        name = "B";
    }

    @Override
    int fight(){
        int sum = 2;
        return sum;
    }
}

class Sneakers extends Adventurer{
    Sneakers(){
        name = "SN";
    }
    //if encounters a creature
    //randomly generate number 0 - 1 
    // Random rand = new Random(); //instance of random class
    // int upperbound = 1;
    // int int_random = rand.nextInt(upperbound); 
    // if(int_random == 1){
    //     //move to join any adjacent Adventurer on their level
    //     //If no Adventurer is adjacent to them or an Adventurer is already in their Room, Seekers will not move
    // }
    // else{
    //     //fight
    // }
}

class Runners extends Adventurer{
    Runners(){
        name = "R";
    }
    boolean tookTurn = false;
    //take a turn if they move 

}

class Thieves extends Adventurer{
    Thieves(){
        name = "T";
    }
    
    @Override
    int fight(){
        int sum = 1;
        return sum;
    }

    @Override
    boolean findTreasure(){
        int sum = 1;
        return true;
    }
}


abstract class Creature{
    int hp;
    ArrayList<Integer> location = new ArrayList<>(); //going to update
    String name;
    
    Creature(){
        hp = 1;
    }
    int fight(){
        int sum = 0;
        return sum;
    }

    ArrayList<Integer> getLoc(){
        return location;
    }

    String getName(){
        return name;
    }
}


class Orbiters extends Creature{

    ArrayList<Integer> location = new ArrayList<>();

    Orbiters(){
        name = "O";
        
        Random rand = new Random(); //instance of random class
        boolean work = false;

        while(!work){
            int rX = rand.nextInt(2); 
            int rY = rand.nextInt(2); 
            int rZ = rand.nextInt(4); 
        
            if(rX != 1 && rY != 1){
                work = true;
                location.add(rX);
                location.add(rY);
                location.add(rZ);
            }
        }
    }

}

class Seekers extends Creature{
    Seekers(){
        name = "SE";

        Random rand = new Random(); //instance of random class
        boolean work = false;

        while(!work){
            int rX = rand.nextInt(4); 
            int rY = rand.nextInt(4); 
            int rZ = rand.nextInt(4); 
        
            if(rX != 0 && rY != 1 && rZ != 1){
                work = true;
                location.add(rX);
                location.add(rY);
                location.add(rZ);
            }
        }
    }
}

class Blinkers extends Creature{
    Blinkers(){
        name = "BL";

        Random rand = new Random(); //instance of random class
    
        int rX = rand.nextInt(2); 
        int rY = rand.nextInt(2); 
        int rZ = 4; 

        location.add(rX);
        location.add(rY);
        location.add(rZ);
    }
}

/*class Fight{
    Adventurer a = new Adventurer();
    Fight(int adv, int cr){
        if(adv > cr){
            //call die function for creature 
        }
        else if(cr > adv){
            
        }
        else{
            //tie
        }
    }
}*/


class Schmoovement{
    int x;
    int y;
    int z;
    //static ArrayList<String> potDir = new ArrayList<>();
    static ArrayList<Integer> potDir = new ArrayList<>();

    Schmoovement(ArrayList<Integer> xyz){
        potDir.clear();

        x = xyz.get(0);
        y = xyz.get(1);
        z = xyz.get(2);

        canNorth(y);
        canSouth(y);
        canEast(z);
        canWest(z);
        canUp(x);
        canDown(x);

        System.out.println(potDir);
    }

    public static void move(int dir){
        switch(dir){
            case 1: //move north save status ? update status
                /*map.get(x).get(y).set(z, store);
                store = map.get(x).get(y-1).get(z);
                map.get(x).get(y-1).set(z, p);*/
                break;
        
            default:
                break;
        }
    }

    public static void canNorth(int y){
        if(y != 0){
            potDir.add(1);
        }
    }
    public static void canSouth(int y){
        if(y != 2){
            potDir.add(2);
        }
    }
    public static void canEast(int z){
        if(z != 2){
            potDir.add(3);
        }
    }
    public static void canWest(int z){
        if(z != 0){
            potDir.add(4);
        }
    }
    public static void canUp(int x){
        if(x != 3){
            potDir.add(5);
        }
    }
    public static void canDown(int x){
        if(x != 0){
            potDir.add(6);
        }
    }

    /*public static void canNorth(int y){
        if(y != 0){
            potDir.add("North");
        }
    }
    public static void canSouth(int y){
        if(y != 2){
            potDir.add("South");
        }
    }
    public static void canEast(int z){
        if(z != 2){
            potDir.add("East");
        }
    }
    public static void canWest(int z){
        if(z != 0){
            potDir.add("West");
        }
    }
    public static void canUp(int x){
        if(x != 3){
            potDir.add("Up");
        }
    }
    public static void canDown(int x){
        if(x != 0){
            potDir.add("Down");
        }
    }*/
}
