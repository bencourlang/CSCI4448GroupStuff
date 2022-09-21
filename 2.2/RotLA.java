import java.util.*;
import java.util.Random;

/*TODO

1. Fix map x and y switching randomly
2. Fix seekers movement (problem most likely has to do with map bugging)
3. Move Adventurers then move Creatures ********
4. Add Runners special ability *********
5. If adv in room cre dont move *********

*/

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

        TotalTreasure tt = new TotalTreasure();

        checkAllDeadAdv cada = new checkAllDeadAdv();
        checkAllDeadCre cadc = new checkAllDeadCre();

        SameRoom sr = new SameRoom();
        
        ArrayList<ArrayList<ArrayList<String>>> map = f.makeRooms();

        Boolean gameOver = false;

        //f.updateRooms(b, sneak, r, t, o, see, blink, map);

        /*for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.size()-2; j++){
                System.out.println(map.get(i).get(j));
            }
            System.out.println();
        }*/
        
        /*for(int i = 0; i < 10; i++){
            System.out.println("*********************" + i + "*********************");
            System.out.println();

            f.updateRooms(b, sneak, r, t, o, see, blink, map, gameOver, tt);

            b.move(map);
            sneak.move(map);
            r.move(map);
            t.move(map);

            o.move(map);
            blink.move(map);
            //see.move(map);
    
            System.out.println();
        }*/

        //System.out.println(tt.numTreasure());

        int count = 0;

        

        while(!gameOver){
            System.out.println("********************* " + count + " *********************");
            System.out.println();
            count++;

            //f.updateRooms(b, sneak, r, t, o, see, blink, map, tt);

            f.updateAdv(b, sneak, r, t, o, see, blink, map, tt);
            f.updateCre(b, sneak, r, t, o, see, blink, map, tt);

            b.move(map);
            sneak.move(map);
            r.move(map);

            r.runnerTurnTwo(b, sneak, r, t, o, see, blink, map, tt);

            r.move(map);
            t.move(map);

            sr.checkRoom(b, sneak, r, t, o, see, blink, map);

            /*o.move(map);
            blink.move(map);
            //see.move(map);*/

            
    
            System.out.println();



            if(tt.gameOver == true){
                gameOver = tt.gameOver;
            }
            if(cada.areTheyDead(b, sneak, r, t) == true){
                gameOver = true;
            }
            if(cadc.areTheyDead(o, see, blink) == true){
                gameOver = true;
            }
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
                map.get(i).add(new ArrayList<String>(x_axis_length));
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

    /*ArrayList<ArrayList<ArrayList<String>>> updateRooms(Adventurer b, Adventurer sneak, Adventurer r, Adventurer t, Creature o, Creature see, Creature blink, ArrayList<ArrayList<ArrayList<String>>> map, TotalTreasure tt){
        //Check if null
        //if null put -
        //if not null put name
        //if multiple in 1 room, adv on left cre on right
        //check for mult adv and cre same room, do something if so

        ArrayList<Adventurer> aliveAdv = new ArrayList<>();

        if(b.isAlive == true){
            aliveAdv.add(b);
        }
        if(sneak.isAlive == true){
            aliveAdv.add(sneak);
        }
        if(r.isAlive == true){
            aliveAdv.add(r);
        }
        if(t.isAlive == true){
            aliveAdv.add(t);
        }

        ArrayList<Creature> aliveCre = new ArrayList<>();
        if(o.isAlive == true){
            aliveCre.add(o);
        }
        if(see.isAlive == true){
            aliveCre.add(see);
        }
        if(blink.isAlive == true){
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
                            //String temp = z + "-" + y + "-" + x + ": " + names + ": ";
                            String creNames = "";
                            for(int j = 0; j < aliveCre.size(); j++){
                                if(aliveCre.get(j).getLocation().equals(hold)){
                                    creNames += aliveCre.get(j).getName() + " ";
                                }
                            }
                            if(creNames.equals("")){
                                creNames = " -";
                                aliveAdv.get(i).findTreasure(tt);
                            }
                            //String temp = z + "-" + x + "-" + y + ": " + aliveAdv.get(i).getName() + " : -";
                            temp += creNames;

                            // if(names == ""){
                            //     map.get(z).get(x).set(y, temp);
                            // }
                            // else{
                            //     map.get(z).get(y).set(x, temp);
                            // }
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
                        if(aliveCre.get(i).getLocation().equals(hold)){
                            String temp = z + "-" + x + "-" + y + ": " + names + ": -";
                            map.get(z).get(x).set(y, temp);
                        }
                        if(aliveCre.get(i).getLocation().equals(hold)){
                            //String temp = z + "-" + x + "-" + y + ": " + names + ": ";
                            String temp = z + "-" + x + "-" + y + ": ";
                            //String temp = z + "-" + y + "-" + x + ": ";
                            String advNames = "";
                            for(int j = 0; j < aliveAdv.size(); j++){
                                if(aliveAdv.get(j).getLocation().equals(hold)){
                                    Fight f = new Fight(aliveAdv.get(j), aliveCre.get(i));
                                    advNames += aliveAdv.get(j).getName() + " ";
                                }
                            }
                            if(advNames.equals("")){
                                advNames = " - ";
                            }
                            //String temp = z + "-" + x + "-" + y + ": " + aliveAdv.get(i).getName() + " : -";
                            temp += advNames;

                            // temp += ": " + names;

                            // if(names == ""){
                            //     map.get(z).get(x).set(y, temp);
                            // }
                            // else{
                            //     map.get(z).get(y).set(x, temp);
                            // }

                            temp += ": " + names;
                            map.get(z).get(x).set(y, temp);
                        }
                    }
                    //System.out.println(map.get(z).get(x).get(y));
                }
            }
        }

        /*for(int i = 0; i < aliveAdv.size(); i++){ // ************** START *****************
             ArrayList<Adventurer> addAdv = new ArrayList<>();
             addAdv.add(aliveAdv.get(i));
             //addAdv.add(aliveAdv.get(i));
             for(int j = 0; j < aliveAdv.size(); j++){
                if(aliveAdv.get(i).getLocation().equals(aliveAdv.get(j).getLocation())){
                    addAdv.add(aliveAdv.get(j));
                }
            }

            String names = "";

            if(addAdv.size() == 1){
                names += addAdv.get(0).getName();
                 
                ArrayList<Integer> advSpot = addAdv.get(0).getLocation();
                map.get(advSpot.get(0)).get(advSpot.get(1)).set(advSpot.get(2), advSpot.get(0) + "-" + advSpot.get(1) + "-" + advSpot.get(2) + ": " + names);  
            }
            else{
                for(int j = 0; j < addAdv.size(); j++){
                    if(j == addAdv.size()-1){
                        names += addAdv.get(j).getName() + ": ";
                    }
                    else{
                        names += addAdv.get(j).getName() + ", ";
                    }
                }

                ArrayList<Integer> advSpot = addAdv.get(i).getLocation();
                map.get(advSpot.get(0)).get(advSpot.get(1)).set(advSpot.get(2), advSpot.get(0) + "-" + advSpot.get(1) + "-" + advSpot.get(2) + ": " + names);  
            }

            ArrayList<Integer> advSpot = addAdv.get(i).getLocation();
            map.get(advSpot.get(0)).get(advSpot.get(1)).set(advSpot.get(2), advSpot.get(0) + "-" + advSpot.get(1) + "-" + advSpot.get(2) + ": " + names + " : -");       
        }

             //locs.add(aliveAdv.get(i).getLocation());

        //ArrayList<Creature> aliveCre = new ArrayList<>();

        //System.out.println(o.getLocation());

        for(int i = 0; i < aliveCre.size(); i++){
            ArrayList<Creature> addCre = new ArrayList<>();
            //addCre.add(aliveCre.get(i));
            for(int j = 0; j < aliveCre.size(); j++){
                if(aliveCre.get(i).getLocation().equals(aliveCre.get(j).getLocation())){
                    addCre.add(aliveCre.get(j));
                }
            }

            String names = "";

            if(addCre.size() == 1){
                names += addCre.get(0).getName();
                 
                ArrayList<Integer> creSpot = addCre.get(0).getLocation();
                map.get(creSpot.get(0)).get(creSpot.get(1)).set(creSpot.get(2), creSpot.get(0) + "-" + creSpot.get(1) + "-" + creSpot.get(2) + ": " + names);  
            }
            else{
                for(int j = 0; j < addCre.size(); j++){
                    if(j == addCre.size()-1){
                        names += addCre.get(j).getName();
                    }
                    else{
                        names += addCre.get(j).getName() + ", ";
                    }
                }

                ArrayList<Integer> creSpot = addCre.get(i).getLocation();
                map.get(creSpot.get(0)).get(creSpot.get(1)).set(creSpot.get(2), creSpot.get(0) + "-" + creSpot.get(1) + "-" + creSpot.get(2) + ": " + names);  
            }
             
            //locs.add(aliveCre.get(i).getLocation());
        }*/ //                              ************* END *****************

        /*for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.size()-2; j++){
                System.out.println(map.get(i).get(j));
            }
            System.out.println();
        }

        return map;
    }*/

    ArrayList<ArrayList<ArrayList<String>>> updateAdv(Adventurer b, Adventurer sneak, Adventurer r, Adventurer t, Creature o, Creature see, Creature blink, ArrayList<ArrayList<ArrayList<String>>> map, TotalTreasure tt){
        //Check if null
        //if null put -
        //if not null put name
        //if multiple in 1 room, adv on left cre on right
        //check for mult adv and cre same room, do something if so

        System.out.println("(********************* Adventure Move *********************");

        ArrayList<Adventurer> aliveAdv = new ArrayList<>();

        if(b.isAlive == true){
            aliveAdv.add(b);
        }
        if(sneak.isAlive == true){
            aliveAdv.add(sneak);
        }
        if(r.isAlive == true){
            aliveAdv.add(r);
        }
        if(t.isAlive == true){
            aliveAdv.add(t);
        }

        ArrayList<Creature> aliveCre = new ArrayList<>();
        if(o.isAlive == true){
            aliveCre.add(o);
        }
        if(see.isAlive == true){
            aliveCre.add(see);
        }
        if(blink.isAlive == true){
            aliveCre.add(blink);
        }

        for(int z = 0; z < 5; z++){
            for(int x = 0; x < 3; x++){
                for(int y = 0; y < 3; y++){
                    for(int i = 0; i < aliveAdv.size(); i++){
                        String names = "";

                        for(int j = 0; j < aliveAdv.size(); j++){
                            if(aliveAdv.get(i).getLocation().equals(aliveAdv.get(j).getLocation())){
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
                                aliveAdv.get(i).findTreasure(tt);
                            }
                            temp += creNames;

                            map.get(z).get(x).set(y, temp);
                        }
                    }
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

    ArrayList<ArrayList<ArrayList<String>>> updateCre(Adventurer b, Adventurer sneak, Adventurer r, Adventurer t, Creature o, Creature see, Creature blink, ArrayList<ArrayList<ArrayList<String>>> map, TotalTreasure tt){
        //Check if null
        //if null put -
        //if not null put name
        //if multiple in 1 room, adv on left cre on right
        //check for mult adv and cre same room, do something if so

        System.out.println("********************* Creature Move *********************");

        ArrayList<Adventurer> aliveAdv = new ArrayList<>();

        if(b.isAlive == true){
            aliveAdv.add(b);
        }
        if(sneak.isAlive == true){
            aliveAdv.add(sneak);
        }
        if(r.isAlive == true){
            aliveAdv.add(r);
        }
        if(t.isAlive == true){
            aliveAdv.add(t);
        }

        ArrayList<Creature> aliveCre = new ArrayList<>();
        if(o.isAlive == true){
            aliveCre.add(o);
        }
        if(see.isAlive == true){
            aliveCre.add(see);
        }
        if(blink.isAlive == true){
            aliveCre.add(blink);
        }

        for(int z = 0; z < 5; z++){
            for(int x = 0; x < 3; x++){
                for(int y = 0; y < 3; y++){
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
                        if(aliveCre.get(i).getLocation().equals(hold)){
                            String temp = z + "-" + x + "-" + y + ": " + names + ": -";
                            map.get(z).get(x).set(y, temp);
                        }
                        if(aliveCre.get(i).getLocation().equals(hold)){
                            //String temp = z + "-" + x + "-" + y + ": " + names + ": ";
                            String temp = z + "-" + x + "-" + y + ": ";
                            //String temp = z + "-" + y + "-" + x + ": ";
                            String advNames = "";
                            for(int j = 0; j < aliveAdv.size(); j++){
                                if(aliveAdv.get(j).getLocation().equals(hold)){
                                    Fight f = new Fight(aliveAdv.get(j), aliveCre.get(i));
                                    advNames += aliveAdv.get(j).getName() + " ";
                                }
                            }
                            if(advNames.equals("")){
                                advNames = " - ";
                            }
                            //String temp = z + "-" + x + "-" + y + ": " + aliveAdv.get(i).getName() + " : -";
                            temp += advNames;

                            // temp += ": " + names;

                            // if(names == ""){
                            //     map.get(z).get(x).set(y, temp);
                            // }
                            // else{
                            //     map.get(z).get(y).set(x, temp);
                            // }

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
    String name;
    Boolean isAlive;

    Adventurer(){
        hp = 3;
        location.add(0);
        location.add(1);
        location.add(1);
        isAlive = true;
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
        RollDie rd = new RollDie();
        return rd.val1 + rd.val2;
    }

    void findTreasure(TotalTreasure tt){
        RollDie rd = new RollDie();
        if(rd.val1 + rd.val2 > 9){
            System.out.println(this.name + " has found a treasure");
            tt.addTreasure();
            System.out.println();
            /*if(tt.treasure >= 10){
                System.out.println("All Treasure Found");
            }*/
        }
        //roll 2 die 
        //if above 10, they get a treasure (treasure +=1)
    }

    ArrayList<ArrayList<ArrayList<String>>> runnerTurnTwo(Adventurer b, Adventurer sneak, Adventurer r, Adventurer t, Creature o, Creature see, Creature blink, ArrayList<ArrayList<ArrayList<String>>> map, TotalTreasure tt){
        return map;
    }

    void changeHp(int newHp){
        hp = newHp;

        if(hp <= 0){
            System.out.println(name + " is dead");
            isAlive = false;
        }
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
        RollDie rd = new RollDie();
        return rd.val1 + rd.val2 + 4;
    }
}

class Sneakers extends Adventurer{
    Sneakers(){
        name = "SN";
    }

    @Override
    int fight(){

        Random rand = new Random(); //instance of random class
        int randVal = rand.nextInt(2);
        
        if(randVal == 1){
            return -1;
        }
        RollDie rd = new RollDie();
        return rd.val1 + rd.val2;
    }
}

class Runners extends Adventurer{
    Boolean tookTurn;

    Runners(){
        name = "R";
        tookTurn = false;
    }

    @Override
    ArrayList<ArrayList<ArrayList<String>>> runnerTurnTwo(Adventurer b, Adventurer sneak, Adventurer r, Adventurer t, Creature o, Creature see, Creature blink, ArrayList<ArrayList<ArrayList<String>>> map, TotalTreasure tt){
        //Check if null
        //if null put -
        //if not null put name
        //if multiple in 1 room, adv on left cre on right
        //check for mult adv and cre same room, do something if so

        System.out.println("****************** Runner Turn 2 ******************");
        System.out.println("");

        ArrayList<Adventurer> aliveAdv = new ArrayList<>();

        if(b.isAlive == true){
            aliveAdv.add(b);
        }
        if(sneak.isAlive == true){
            aliveAdv.add(sneak);
        }
        if(r.isAlive == true){
            aliveAdv.add(r);
        }
        if(t.isAlive == true){
            aliveAdv.add(t);
        }

        ArrayList<Creature> aliveCre = new ArrayList<>();
        if(o.isAlive == true){
            aliveCre.add(o);
        }
        if(see.isAlive == true){
            aliveCre.add(see);
        }
        if(blink.isAlive == true){
            aliveCre.add(blink);
        }

        for(int z = 0; z < 5; z++){
            for(int x = 0; x < 3; x++){
                for(int y = 0; y < 3; y++){
                    for(int i = 0; i < aliveAdv.size(); i++){
                        String names = "";

                        for(int j = 0; j < aliveAdv.size(); j++){
                            if(aliveAdv.get(i).getLocation().equals(aliveAdv.get(j).getLocation())){
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
                                if(aliveAdv.get(i).getName() == "r"){
                                    aliveAdv.get(i).findTreasure(tt);
                                }
                            }
                            temp += creNames;

                            map.get(z).get(x).set(y, temp);
                        }
                    }
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
    
    //take a turn if they move 

}

class Thieves extends Adventurer{
    Thieves(){
        name = "T";
    }
    
    @Override
    int fight(){
        RollDie rd = new RollDie();
        return rd.val1 + rd.val2 + 1;
    }

    @Override
    void findTreasure(TotalTreasure tt){
        RollDie rd = new RollDie();
        if(rd.val1 + rd.val2 > 8){
            System.out.println(this.name + " has found a treasure");
            System.out.println();
            tt.addTreasure();
            /*if(tt.treasure >= 10){
                System.out.println("All Treasure Found");
            }*/
        }
        //roll 2 die 
        //if above 10, they get a treasure (treasure +=1)
    }
}


abstract class Creature{
    int hp;
    ArrayList<Integer> location = new ArrayList<>(); //going to update
    String name;
    Boolean isAlive;
    
    Creature(){
        hp = 1;
        isAlive = true;
    }
    int fight(){
        RollDie rd = new RollDie();
        return rd.val1 + rd.val2;
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

    void changeHp(int newHp){
        hp = newHp;
        if(hp <= 0){
            System.out.println(name + " is dead");
            isAlive = false;
        }
    }
    
    void move(ArrayList<ArrayList<ArrayList<String>>> map){
        //System.out.println("hmmm");
    }

    Boolean sameRoomAdv(ArrayList<Integer> loc){
        if(location.equals(loc)){
            return true;
        }
        return false;
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

        //System.out.println(oldLoc);

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
        
        //System.out.println(this.location);
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
        System.out.println(schmo.potDir.size());
        String k = map.get(curLoc.get(0)).get(curLoc.get(1)).get(curLoc.get(2));
        System.out.println("Current Location" + k);
        

        for(int i = 0; i < schmo.potDir.size(); i++){
            if(schmo.potDir.get(i) == 1){ //can move north
                //System.out.println(s);
                String s = map.get(curLoc.get(0)).get(curLoc.get(1)).get(curLoc.get(2) + 1);
                System.out.println("North " + s);
                s = s.substring(s.indexOf(":") + 1);
                s = s.substring(0, s.indexOf(":"));
                System.out.println(s);
                if(s != "-"){
                    newLoc.add(this.location.get(curLoc.get(0)));
                    newLoc.add(this.location.get(curLoc.get(1)));
                    newLoc.add(this.location.get(curLoc.get(2)) + 1);
                    setLocation(newLoc);
                }
            }
            if(schmo.potDir.get(i) == 2){
                String s = map.get(curLoc.get(0)).get(curLoc.get(1)).get(curLoc.get(2) - 1);
                System.out.println("South " + s);
                s = s.substring(s.indexOf(":") + 1);
                s = s.substring(0, s.indexOf(":"));
                System.out.println(s);
                if(s != "-"){
                    newLoc.add(this.location.get(curLoc.get(0)));
                    newLoc.add(this.location.get(curLoc.get(1)));
                    newLoc.add(this.location.get(curLoc.get(2)) - 1);
                    setLocation(newLoc);
                }
                
            }
            if(schmo.potDir.get(i) == 3){   
                String s = map.get(curLoc.get(0)).get(curLoc.get(1) + 1).get(curLoc.get(2));
                System.out.println("East " + s);
                s = s.substring(s.indexOf(":") + 1);
                s = s.substring(0, s.indexOf(":")); 
                System.out.println(s);           
                if(s != "-"){
                    newLoc.add(this.location.get(curLoc.get(0)));
                    newLoc.add(this.location.get(curLoc.get(1)) + 1);
                    newLoc.add(this.location.get(curLoc.get(2)));
                    setLocation(newLoc);
                }

            }
            if(schmo.potDir.get(i) == 4){
                String s = map.get(curLoc.get(0)).get(curLoc.get(1) - 1).get(curLoc.get(2));
                System.out.println("West " + s);
                s = s.substring(s.indexOf(":") + 1);
                s = s.substring(0, s.indexOf(":"));
                System.out.println(s);
                if(s != "-"){
                    newLoc.add(this.location.get(curLoc.get(0)));
                    newLoc.add(this.location.get(curLoc.get(1) - 1));
                    newLoc.add(this.location.get(curLoc.get(2)));
                    setLocation(newLoc);
                }
            }
        }
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
        int adv = a.fight();
        int cre = c.fight();

        if(adv != -1){
            if(adv > cre){
                c.changeHp(c.hp-1);
                System.out.println();
                System.out.println(a.getName() + " rolled a " + adv + " and has beat " + c.getName() + " who rolled a " + cre);
                System.out.println();
            }
            else if(adv < cre){
                a.changeHp(a.hp-1);
                System.out.println();
                System.out.println(a.getName() + " rolled a " + adv + " and has lost to " + c.getName() + " who rolled a " + cre);
                System.out.println();
            }
            else{
                System.out.println();
                System.out.println(a.getName() + " rolled a " + adv + " and has tied " + c.getName() + " who rolled a " + cre);
                System.out.println();
            }
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
        if(z != 4 && x == 1 && y == 1){
            potDir.add(5);
        }
    }
    void canDown(int z){
        if(z != 0 && x == 1 && y == 1){
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

class TotalTreasure{
    int treasure;
    Boolean gameOver;

    TotalTreasure(){
        treasure = 0;
        gameOver = false;
    }
    int numTreasure(){
        return treasure;
    }
    Boolean addTreasure(){
        treasure += 1;
        if(treasure >= 10){
            System.out.println("All Treasure has been found");
            System.out.println();
            gameOver = true;
        }
        return gameOver;
    }
}

class checkAllDeadAdv{
    Boolean areTheyDead(Adventurer b, Adventurer sneak, Adventurer r, Adventurer t){
        if(b.isAlive == true || sneak.isAlive == true || r.isAlive == true || t.isAlive == true){
            return false;
        }
        System.out.println("All adventureres are dead");
        System.out.println();
        return true;
    }
}

class checkAllDeadCre{
    Boolean areTheyDead(Creature o, Creature see, Creature blink){
        if(o.isAlive == true || see.isAlive == true || blink.isAlive == true){
            return false;
        }
        System.out.println("All creatures are dead");
        System.out.println();
        return true;
    }
}

class SameRoom{
    ArrayList<ArrayList<ArrayList<String>>> checkRoom(Adventurer b, Adventurer sneak, Adventurer r, Adventurer t, Creature o, Creature see, Creature blink, ArrayList<ArrayList<ArrayList<String>>> map){
        Boolean sr = false;
        
        ArrayList<Adventurer> aliveAdv = new ArrayList<>();
        if(b.isAlive == true){
            aliveAdv.add(b);
        }
        if(sneak.isAlive == true){
            aliveAdv.add(sneak);
        }
        if(r.isAlive == true){
            aliveAdv.add(r);
        }
        if(t.isAlive == true){
            aliveAdv.add(t);
        }

        ArrayList<Creature> aliveCre = new ArrayList<>();
        if(o.isAlive == true){
            aliveCre.add(o);
        }
        if(see.isAlive == true){
            aliveCre.add(see);
        }
        if(blink.isAlive == true){
            aliveCre.add(blink);
        }
        
        for(int i = 0; i < aliveCre.size(); i++){
            for(int j = 0; j < aliveAdv.size(); j++){
                if(aliveCre.get(i).sameRoomAdv(aliveAdv.get(j).getLocation())){
                    Fight f = new Fight(aliveAdv.get(j), aliveCre.get(i));
                    
                    sr = true;
                }
            }
            if(sr != true){
                //System.out.println(aliveCre.get(i).getName());
                if(!aliveCre.get(i).getName().equals("SE")){
                    aliveCre.get(i).move(map);
                }
            }
        }
        return map;
    }
}