//import java.awt.*;

//import javax.lang.model.util.ElementScanner6;
//import javax.swing.*;
//import java.io.*;
import java.time.Year;
import java.util.*;
//import java.util.Scanner;
import java.util.Random;

public class RotLA{
    public static void main(String[] args){
        System.out.println();
        Facility f = new Facility();

        Adventurer b = new Brawler();
        Adventurer sneak = new Sneakers();
        Adventurer r = new Runners();
        Adventurer t = new Thieves();

        Creature o = new Orbiters();
        Creature see = new Seekers();
        Creature blink = new Blinkers();
        
        ArrayList<ArrayList<ArrayList<String>>> map = f.makeRooms();
        
        for(int i = 0; i < 10; i++){
            System.out.println("*********************" + i + "*********************");
            System.out.println();

            f.updateRooms(b, sneak, r, t, o, see, blink, map);

            b.move(map);
            sneak.move(map);
            r.move(map);
            t.move(map);

            o.move(map);
            blink.move(map);
            see.move(map);
    
            System.out.println();
        }

        /*f.updateRooms(b, sneak, r, t, o, see, blink, map);

        b.move(map);
        sneak.move(map);
        r.move(map);
        t.move(map);

        f.updateRooms(b, sneak, r, t, o, see, blink, map);*/

        // System.out.println(b.getLocation());
        // System.out.println(sneak.getLocation());
        // System.out.println(r.getLocation());
        // System.out.println(t.getLocation());
    }
}

class Facility{
    Facility(){}

    ArrayList<ArrayList<ArrayList<String>>> makeRooms(){
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
        map.get(0).set(2, new ArrayList<String>(Arrays.asList("0-0-2: - : -", "0-1-2: - : -", "0-2-2: - : -")));

        map.get(1).set(0, new ArrayList<String>(Arrays.asList("1-0-0: - : -", "1-1-0: - : -", "1-2-0: - : -")));
        map.get(1).set(1, new ArrayList<String>(Arrays.asList("1-0-1: - : -", "1-1-1: - : -", "1-2-1: - : -")));
        map.get(1).set(2, new ArrayList<String>(Arrays.asList("1-0-2: - : -", "1-1-2: - : -", "1-2-2: - : -")));

        map.get(2).set(0, new ArrayList<String>(Arrays.asList("2-0-0: - : -", "2-1-0: - : -", "2-2-0: - : -")));
        map.get(2).set(1, new ArrayList<String>(Arrays.asList("2-0-1: - : -", "2-1-1: - : -", "2-2-1: - : -")));
        map.get(2).set(2, new ArrayList<String>(Arrays.asList("2-0-2: - : -", "2-1-2: - : -", "2-2-2: - : -")));

        map.get(3).set(0, new ArrayList<String>(Arrays.asList("3-0-0: - : -", "3-1-0: - : -", "3-2-0: - : -")));
        map.get(3).set(1, new ArrayList<String>(Arrays.asList("3-0-1: - : -", "3-1-1: - : -", "3-2-1: - : -")));
        map.get(3).set(2, new ArrayList<String>(Arrays.asList("3-0-2: - : -", "3-1-2: - : -", "3-2-2: - : -")));

        map.get(4).set(0, new ArrayList<String>(Arrays.asList("4-0-0: - : -", "4-1-0: - : -", "4-2-0: - : -")));
        map.get(4).set(1, new ArrayList<String>(Arrays.asList("4-0-1: - : -", "4-1-1: - : -", "4-2-1: - : -")));
        map.get(4).set(2, new ArrayList<String>(Arrays.asList("4-0-2: - : -", "4-1-2: - : -", "4-2-2: - : -")));

        return map;
    }

    ArrayList<ArrayList<ArrayList<String>>> updateRooms(Adventurer b, Adventurer sneak, Adventurer r, Adventurer t, Creature o, Creature see, Creature blink, ArrayList<ArrayList<ArrayList<String>>> map){
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

        for(int z = 0; z < 5; z++){
            for(int x = 0; x < 3; x++){
                for(int y = 0; y < 3; y++){
                    for(int i = 0; i < aliveAdv.size(); i++){
                        //ArrayList<Adventurer> addAdv = new ArrayList<>();
                        //String names = aliveAdv.get(i).getName();
                        String names = "";

                        for(int j = 0; j < aliveAdv.size(); j++){
                            if(aliveAdv.get(i).getLocation().equals(aliveAdv.get(j).getLocation())){
                                //addAdv.add(aliveAdv.get(j));
                                names += aliveAdv.get(j).getName() + " ";
                            }
                        }

                        ArrayList<Integer> hold = new ArrayList<>();
                        hold.add(z);
                        hold.add(x);
                        hold.add(y);
                        if(aliveAdv.get(i).getLocation().equals(hold)){
                            String temp = z + "-" + x + "-" + y + ": " + names + ": ";
                            String creNames = "";
                            for(int j = 0; j < aliveCre.size(); j++){
                                if(aliveCre.get(j).getLocation().equals(hold)){
                                    creNames += aliveCre.get(j).getName() + " ";
                                }
                            }
                            if(creNames.equals("")){
                                creNames = " -";
                            }
                            //String temp = z + "-" + x + "-" + y + ": " + aliveAdv.get(i).getName() + " : -";
                            temp += creNames;
                            map.get(z).get(x).set(y, temp);
                        }
                    }
                    for(int i = 0; i < aliveCre.size(); i++){
                        //ArrayList<Adventurer> addAdv = new ArrayList<>();
                        //String names = aliveAdv.get(i).getName();
                        String names = "";

                        for(int j = 0; j < aliveCre.size(); j++){
                            if(aliveCre.get(i).getLocation().equals(aliveCre.get(j).getLocation())){
                                //addAdv.add(aliveAdv.get(j));
                                names += aliveCre.get(j).getName() + " ";
                            }
                        }

                        ArrayList<Integer> hold = new ArrayList<>();
                        hold.add(z);
                        hold.add(x);
                        hold.add(y);
                        /*if(aliveCre.get(i).getLocation().equals(hold)){
                            String temp = z + "-" + x + "-" + y + ": " + names + ": -";
                            map.get(z).get(x).set(y, temp);
                        }*/
                        if(aliveCre.get(i).getLocation().equals(hold)){
                            //String temp = z + "-" + x + "-" + y + ": " + names + ": ";
                            String temp = z + "-" + x + "-" + y + ": ";
                            String advNames = "";
                            for(int j = 0; j < aliveAdv.size(); j++){
                                if(aliveAdv.get(j).getLocation().equals(hold)){
                                    advNames += aliveAdv.get(j).getName() + " ";
                                }
                            }
                            if(advNames.equals("")){
                                advNames = " - ";
                            }
                            //String temp = z + "-" + x + "-" + y + ": " + aliveAdv.get(i).getName() + " : -";
                            temp += advNames;
                            temp += ": " + names;
                            map.get(z).get(x).set(y, temp);
                        }
                    }
                    //System.out.println(map.get(z).get(x).get(y));
                }
            }
        }

        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.size()-2; j++){
                System.out.println(map.get(i).get(j));
            }
            System.out.println();
        }

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

    void setLocation(ArrayList<Integer> zxy){
        location.clear();
        location.add(zxy.get(0));
        location.add(zxy.get(1));
        location.add(zxy.get(2));
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

    void findTreasure(){
        RollDie rd = new RollDie();
        if(rd.val1 + rd.val2 > 10){
            this.treasure += 1;
        }
        //roll 2 die 
        //if above 10, they get a treasure (treasure +=1)
    }

    void move(ArrayList<ArrayList<ArrayList<String>>> map){
        Random r = new Random();
        Schmoovement schmoo = new Schmoovement(location);
        //System.out.println(schmoo);
        //System.out.println(schmoo.potDir.size());

        ArrayList<Integer> newLoc = new ArrayList<>();
        ArrayList<Integer> oldLoc = new ArrayList<>();
        oldLoc.add(location.get(0));
        oldLoc.add(location.get(1));
        oldLoc.add(location.get(2));

        //System.out.println(schmoo.potDir);

        switch(schmoo.potDir.get(r.nextInt(schmoo.potDir.size()))){
        //switch(r.nextInt(schmoo.potDir.size())){
            case 1:
                newLoc.add(this.location.get(0));
                newLoc.add(this.location.get(1));
                newLoc.add(this.location.get(2) - 1);

                this.setLocation(newLoc);

                //System.out.println("North");
                break;
            case 2:
                newLoc.add(this.location.get(0));
                newLoc.add(this.location.get(1));
                newLoc.add(this.location.get(2) + 1);

                this.setLocation(newLoc);

                //System.out.println("South");
                break;
            case 3:
                newLoc.add(this.location.get(0));
                newLoc.add(this.location.get(1) + 1);
                newLoc.add(this.location.get(2));

                this.setLocation(newLoc);

                //System.out.println("East");
                break;
            case 4:
                newLoc.add(this.location.get(0));
                newLoc.add(this.location.get(1) - 1);
                newLoc.add(this.location.get(2));

                this.setLocation(newLoc);

                //System.out.println("West");
                break;
            case 5:
                newLoc.add(this.location.get(0) + 1);
                newLoc.add(this.location.get(1));
                newLoc.add(this.location.get(2));

                this.setLocation(newLoc);

                //System.out.println("Up");
                break;
            case 6:
                newLoc.add(this.location.get(0) - 1);
                newLoc.add(this.location.get(1));
                newLoc.add(this.location.get(2));

                this.setLocation(newLoc);

                //System.out.println("Down");
                break;
        
            default:
                System.out.println("Yo how tf");
                break;
        }

        String check = oldLoc.get(0) + "-" + oldLoc.get(1) + "-" + oldLoc.get(2) + ": - : -"; 
        if(!map.get(oldLoc.get(0)).get(oldLoc.get(1)).get(oldLoc.get(2)).equals(check)){
            map.get(oldLoc.get(0)).get(oldLoc.get(1)).set(oldLoc.get(2), check);
        }
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
    void findTreasure(){
        RollDie rd = new RollDie();
        if(rd.val1 + rd.val2 > 9){
            this.treasure += 1;
        }
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

    void setLocation(ArrayList<Integer> zxy){
        location.clear();
        location.add(zxy.get(0));
        location.add(zxy.get(1));
        location.add(zxy.get(2));
    }

    ArrayList<Integer> getLocation(){
        return location;
    }

    String getName(){
        return name;
    }
    
    void move(ArrayList<ArrayList<ArrayList<String>>> map){
        //System.out.println("hmmm");
    }
}


class Orbiters extends Creature{

    //ArrayList<Integer> location = new ArrayList<>();

    Orbiters(){
        name = "O";
        
        Random rand = new Random(); //instance of random class
        boolean work = false;

        while(!work){
            int rX = rand.nextInt(3); 
            int rY = rand.nextInt(3); 
            int rZ = rand.nextInt(5); 
        
            if(rX == 1 && rY == 1){
                //idk
            }
            else{
                work = true;
                location.add(rZ);
                location.add(rX);
                location.add(rY);
            }
        }
    }

    @Override
    void move(ArrayList<ArrayList<ArrayList<String>>> map){
        ArrayList<Integer> oldLoc = new ArrayList<>();
        oldLoc.add(location.get(0));
        oldLoc.add(location.get(1));
        oldLoc.add(location.get(2));

        if(this.location.get(1).equals(0) && this.location.get(2).equals(0)){
            ArrayList<Integer> newLoc = new ArrayList<>();
            newLoc.add(this.location.get(0));
            newLoc.add(this.location.get(1) + 1);
            newLoc.add(this.location.get(2));
            setLocation(newLoc);
        }
        else if(this.location.get(1).equals(1) && this.location.get(2).equals(0)){
            ArrayList<Integer> newLoc = new ArrayList<>();
            newLoc.add(this.location.get(0));
            newLoc.add(this.location.get(1) + 1);
            newLoc.add(this.location.get(2));
            setLocation(newLoc);
        }
        else if(this.location.get(1).equals(2) && this.location.get(2).equals(0)){
            ArrayList<Integer> newLoc = new ArrayList<>();
            newLoc.add(this.location.get(0));
            newLoc.add(this.location.get(1));
            newLoc.add(this.location.get(2) + 1);
            setLocation(newLoc);
        }
        else if(this.location.get(1).equals(2) && this.location.get(2).equals(1)){
            ArrayList<Integer> newLoc = new ArrayList<>();
            newLoc.add(this.location.get(0));
            newLoc.add(this.location.get(1));
            newLoc.add(this.location.get(2) + 1);
            setLocation(newLoc);
        }
        else if(this.location.get(1).equals(2) && this.location.get(2).equals(2)){
            ArrayList<Integer> newLoc = new ArrayList<>();
            newLoc.add(this.location.get(0));
            newLoc.add(this.location.get(1) - 1);
            newLoc.add(this.location.get(2));
            setLocation(newLoc);
        }
        else if(this.location.get(1).equals(1) && this.location.get(2).equals(2)){
            ArrayList<Integer> newLoc = new ArrayList<>();
            newLoc.add(this.location.get(0));
            newLoc.add(this.location.get(1) - 1);
            newLoc.add(this.location.get(2));
            setLocation(newLoc);
        }
        else if(this.location.get(1).equals(0) && this.location.get(2).equals(2)){
            ArrayList<Integer> newLoc = new ArrayList<>();
            newLoc.add(this.location.get(0));
            newLoc.add(this.location.get(1));
            newLoc.add(this.location.get(2) - 1);
            setLocation(newLoc);
        }
        else if(this.location.get(1).equals(1) && this.location.get(2).equals(2)){
            ArrayList<Integer> newLoc = new ArrayList<>();
            newLoc.add(this.location.get(0));
            newLoc.add(this.location.get(1));
            newLoc.add(this.location.get(2) - 1);
            setLocation(newLoc);
        }
        else if(this.location.get(1).equals(0) && this.location.get(2).equals(1)){
            ArrayList<Integer> newLoc = new ArrayList<>();
            newLoc.add(this.location.get(0));
            newLoc.add(this.location.get(1));
            newLoc.add(this.location.get(2) - 1);
            setLocation(newLoc);
        }

        String check = oldLoc.get(0) + "-" + oldLoc.get(1) + "-" + oldLoc.get(2) + ": - : -"; 
        if(!map.get(oldLoc.get(0)).get(oldLoc.get(1)).get(oldLoc.get(2)).equals(check)){
            map.get(oldLoc.get(0)).get(oldLoc.get(1)).set(oldLoc.get(2), check);
        }
    }

}

class Seekers extends Creature{
    Seekers(){
        name = "SE";

        Random rand = new Random(); //instance of random class
        boolean work = false;

        while(!work){
            int rX = rand.nextInt(3); 
            int rY = rand.nextInt(3); 
            int rZ = rand.nextInt(5); 
        
            if(rX != 0 && rY != 1 && rZ != 1){
                work = true;
                location.add(rZ);
                location.add(rX);
                location.add(rY);
            }
        }
    }

    @Override
    void move(ArrayList<ArrayList<ArrayList<String>>> map){
        //get current location of seeker
        ArrayList<Integer> curLoc = new ArrayList<>();
        curLoc.add(location.get(0));
        curLoc.add(location.get(1));
        curLoc.add(location.get(2));

        ArrayList<Integer> newLoc = new ArrayList<>();
        //use schmovement class to see where it can move
        Schmoovement schmo = new Schmoovement(curLoc);

        System.out.println(schmo.potDir);
        

        for(int i = 0; i < schmo.potDir.size(); i++){
            if(schmo.potDir.get(i) == 1){ //can move north
                //System.out.println(s);
                String s = map.get(curLoc.get(0)).get(curLoc.get(1) - 1).get(curLoc.get(2));
                System.out.println("North " + s);
                s = s.substring(s.indexOf(":") + 1);
                s = s.substring(0, s.indexOf(":"));
                System.out.println(s);
                if(s != "-"){
                    newLoc.add(this.location.get(curLoc.get(0)));
                    newLoc.add(this.location.get(curLoc.get(1)));
                    newLoc.add(this.location.get(curLoc.get(2)));
                    setLocation(newLoc);
                }
            }
            if(schmo.potDir.get(i) == 2){
                String s = map.get(curLoc.get(0)).get(curLoc.get(1) + 1).get(curLoc.get(2));
                System.out.println("South " + s);
                s = s.substring(s.indexOf(":") + 1);
                s = s.substring(0, s.indexOf(":"));
                System.out.println(s);
                if(s != "-"){
                    newLoc.add(this.location.get(curLoc.get(0)));
                    newLoc.add(this.location.get(curLoc.get(1)));
                    newLoc.add(this.location.get(curLoc.get(2)));
                    setLocation(newLoc);
                }
                
            }
            if(schmo.potDir.get(i) == 3){   
                String s = map.get(curLoc.get(0)).get(curLoc.get(1)).get(curLoc.get(2) + 1);
                System.out.println("East " + s);
                s = s.substring(s.indexOf(":") + 1);
                s = s.substring(0, s.indexOf(":")); 
                System.out.println(s);           
                if(s != "-"){
                    newLoc.add(this.location.get(curLoc.get(0)));
                    newLoc.add(this.location.get(curLoc.get(1)));
                    newLoc.add(this.location.get(curLoc.get(2)));
                    setLocation(newLoc);
                }

            }
            if(schmo.potDir.get(i) == 4){
                String s = map.get(curLoc.get(0)).get(curLoc.get(1)).get(curLoc.get(2) - 1);
                System.out.println("West " + s);
                s = s.substring(s.indexOf(":") + 1);
                s = s.substring(0, s.indexOf(":"));
                System.out.println(s);
                if(s != "-"){
                    newLoc.add(this.location.get(curLoc.get(0)));
                    newLoc.add(this.location.get(curLoc.get(1)));
                    newLoc.add(this.location.get(curLoc.get(2)));
                    setLocation(newLoc);
                }
            }
        }




        
        //if adventurer is in that room move there
        //check if adventurer is north, south, east, or west

        
    }
}

//@Override
class Blinkers extends Creature{
    Blinkers(){
        name = "BL";

        Random rand = new Random(); //instance of random class
    
        int rX = rand.nextInt(3); 
        int rY = rand.nextInt(3); 
        int rZ = 4; 

        location.add(rZ);
        location.add(rX);
        location.add(rY);
    }
    
    @Override
    void move(ArrayList<ArrayList<ArrayList<String>>> map){
        ArrayList<Integer> oldLoc = new ArrayList<>();
        oldLoc.add(location.get(0));
        oldLoc.add(location.get(1));
        oldLoc.add(location.get(2));


        Random rand = new Random(); //instance of random class
        boolean work = false;

        while(!work){
            int rX = rand.nextInt(3); 
            int rY = rand.nextInt(3); 
            int rZ = rand.nextInt(5); 
        
            if(rX == 1 && rY == 1 && rZ == 0){
                //idk
            }
            else{
                ArrayList<Integer> zxy = new ArrayList<>();
                zxy.add(rZ);
                zxy.add(rX);
                zxy.add(rY);

                work = true;
                this.setLocation(zxy);
            }
        }

        String check = oldLoc.get(0) + "-" + oldLoc.get(1) + "-" + oldLoc.get(2) + ": - : -"; 
        if(!map.get(oldLoc.get(0)).get(oldLoc.get(1)).get(oldLoc.get(2)).equals(check)){
            map.get(oldLoc.get(0)).get(oldLoc.get(1)).set(oldLoc.get(2), check);
        }
    }
}

class Fight{
    Fight(Adventurer a, Creature c){
        RollDie advRD = new RollDie();
        RollDie creRD = new RollDie();

        if(advRD.val1 + advRD.val2 > creRD.val1 + creRD.val2){
            
        }
        else if(advRD.val1 + advRD.val2 < creRD.val1 + creRD.val2){

        }
        else{
            
        }
    }
}


class Schmoovement{
    int x;
    int y;
    int z;
    //static ArrayList<String> potDir = new ArrayList<>();
    ArrayList<Integer> potDir = new ArrayList<>();

    Schmoovement(ArrayList<Integer> zxy){
        potDir.clear();

        z = zxy.get(0);
        x = zxy.get(1);
        y = zxy.get(2);

        canNorth(y);
        canSouth(y);
        canEast(x);
        canWest(x);
        canUp(z);
        canDown(z);

        //System.out.println(potDir);
    }

    void canNorth(int y){
        if(y != 0){
            potDir.add(1);
        }
    }
    void canSouth(int y){
        if(y != 2){
            potDir.add(2);
        }
    }
    void canEast(int x){
        if(x != 2){
            potDir.add(3);
        }
    }
    void canWest(int x){
        if(x != 0){
            potDir.add(4);
        }
    }
    void canUp(int z){
        if(z != 3){
            potDir.add(5);
        }
    }
    void canDown(int z){
        if(z != 0){
            potDir.add(6);
        }
    }
}

class RollDie{
    int val1;
    int val2;
    
    RollDie(){
        Random r = new Random();
        val1 = r.nextInt(6) + 1;
        val2 = r.nextInt(6) + 1;
    }
}