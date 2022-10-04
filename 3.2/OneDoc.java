import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.StreamSupport;
import java.security.spec.NamedParameterSpec;
import java.util.*;

public class OneDoc {

    public static void main(String[] args) {
        // Get the main object for running games
        Engine gameEngine = new Engine();

        // Run a game
        gameEngine.RunSingleGame();

        // Run 30 games - I'll leave that to you...
        // int numberOfGames = 30;
        // gameEngine.RunMultipleGames(numberOfGames);

    }
}

interface CombatStrategy {
    public void Stealth();
    public void Untrained();
    public void Trained();
    public void Expert();
}


interface SearchStrategy {
    void Careful();
    void Quick();
    void Careless();
}

// The engine drives game turns and also has some utility functions used during turns
// This is where instantiated rooms, adventurers, and creatures are kept

class Engine implements Logger {

    ArrayList<Adventurer> adventurers;
    ArrayList<Creature> creatures;
    ArrayList<Adventurer> exAdventurers;  // I'm keeping the "dead" adventurers and creatures
    ArrayList<Creature> exCreatures;      // Not sure I need to really...

    //Stuff I added
    ArrayList<Treasure> treasures;
    //End of stuff I added

    ArrayList<Room> rooms;
    Integer treasureCount;

    Engine() {
    }

    // What we need to do to prepare for a game - rooms and character set up
    void InitializeGame() {
        // Initialize collections
        adventurers = new ArrayList<Adventurer>();
        creatures = new ArrayList<Creature>();
        exAdventurers = new ArrayList<Adventurer>();
        exCreatures = new ArrayList<Creature>();

        //*************************************** Stuff I added ***************************************
        treasures = new ArrayList<Treasure>();
        //*************************************** End of stuff I added ***************************************

        // Initial treasure count is 0
        treasureCount = 0;

        // create the rooms
        rooms = Room.CreateAllRooms();

        // create the adventurers (I'm doing two Runners and two Thieves)
        /*for(int i=1; i<=2; i++) {
            adventurers.add(new Runner("Runner "+i,Room.GetRoomByName(rooms,"011")));
            adventurers.add(new Thief("Thief "+i,Room.GetRoomByName(rooms,"011")));
        }*/

        adventurers.add(new Runner("Runner",Room.GetRoomByName(rooms,"011")));
        adventurers.add(new Thief("Thief",Room.GetRoomByName(rooms,"011")));
        adventurers.add(new Brawler("Brawler",Room.GetRoomByName(rooms,"011")));
        adventurers.add(new Sneaker("Sneaker",Room.GetRoomByName(rooms,"011")));

        // create the creatures (I'm only making Orbiters and Seekers, so I'll do 6 of each)
        for(int i=1; i<=4; i++) {
            creatures.add(new Orbiter("Orbiter "+i, rooms));
            creatures.add(new Seeker( "Seeker "+i, rooms));
            creatures.add(new Blinker( "Blinker "+i, rooms));
        }

        //*************************************** Stuff I added ***************************************
        for(int i = 1; i <= 4; i++){
            treasures.add(new Sword("Sword " + i, rooms));
            treasures.add(new Gem("Gem " + i, rooms));
            treasures.add(new Armor("Armor " + i, rooms));
            treasures.add(new Portal("Portal " + i, rooms));
            treasures.add(new Trap("Trap " + i, rooms));
            treasures.add(new Potion("Potion " + i, rooms));
        }

        for(Treasure t:treasures){
            out("Treasure " + t.name + " starts in " + t.location.name);
        }
        //*************************************** End of stuff I added ***************************************

        for (Adventurer a:adventurers) {
            out("Adventurer "+a.name+" starts in "+a.location.name);
        }

        for (Creature c:creatures) {
            out("Creature "+c.name+" starts in "+c.location.name);
        }
    }

    // I perform game turns until I meet the ending conditions
    void RunSingleGame() {
        int turn = 0;
        InitializeGame();
        do {
            turn += 1;
            out("--- Starting turn "+turn+" ---");
            PerformTurn();
            SummaryReport(turn);
        }
        while (!((treasureCount >= 10) || adventurers.isEmpty() || creatures.isEmpty()));
    }

    // Exercise for the reader...
    // void RunMultipleGames(Integer gameCount) {};

    // I was too lazy to print out the board, so I just did a quick status summary here
    void SummaryReport(int turn) {
        out("---Summary for turn "+turn+ " ---");
        out("Treasure count = "+treasureCount);
        out("Active adventurers = "+adventurers.size());
        out("Active creatures = "+creatures.size());
    }

    // In a turn, the adventurers go and then the creatures go
    // I decided to do the extra Runner turn here because it was easy reuse of turn logic
    void PerformTurn() {
        // Process adventurers
        for (Adventurer a: adventurers) PerformOneAdventurerTurn(a);
        for (Adventurer a: adventurers) {
            if (a.type == AdventurerType.RUNNER) PerformOneAdventurerTurn(a);  // extra turn for runners
        }
        SweepEliminatedCharacters();  // remove any eliminated characters post fights
        // Process creatures
        for (Creature c: creatures) PerformOneCreatureTurn(c);
        SweepEliminatedCharacters();  // remove any eliminated characters post fights
    }

    // Here are the details of what happens in a turn for an adventurer
    void PerformOneAdventurerTurn(Adventurer a) {
        // See if I have creatures in my room
        ArrayList<Creature> creaturesInRoom = GetCreaturesInRoom(a.location);
        // If alone, adventurers will move
        if (creaturesInRoom.isEmpty()) {
            a.move(rooms);
            // if the new room is empty, search for treasure
            creaturesInRoom = GetCreaturesInRoom(a.location);
            if (creaturesInRoom.isEmpty()) {
                //*************************************** Stuff I added ***************************************
                if(tSameAdv(treasures, a)){
                    treasureCount += 1;
                }

                //*************************************** End of stuff I added ***************************************
                /*if (a.search()) {
                    treasureCount += 1;
                    out(a.name + " finds treasure #" + treasureCount);
                }*/
            }
        }
        // If not alone, fight!
        else a.fight(creaturesInRoom);
    }

    // This is the part of a turn for a creatures
    void PerformOneCreatureTurn(Creature c) {
        // See if any adventurers are in here with me
        ArrayList<Adventurer> advInRoom = GetAdventurersInRoom(c.location);
        // if alone, creatures will move
        if (advInRoom.isEmpty()) c.move(rooms, adventurers);
        // if not alone, fight!
        else c.fight(advInRoom);
    }

    // Utility to find all creatures in a room
    // I don't feel like I need to store references directly in the rooms for adventurers
    // or creatures present - I can just see if the creatures are here by asking them
    ArrayList<Creature> GetCreaturesInRoom(Room room) {
        ArrayList<Creature> creaturesInRoom = new ArrayList<Creature>();
        for (Creature c: creatures) {
            if (Objects.equals(c.location.name, room.name)) creaturesInRoom.add(c);
        }
        return creaturesInRoom;
    }

    // Utility to find all adventurers in a room
    ArrayList<Adventurer> GetAdventurersInRoom(Room room) {
        ArrayList<Adventurer> advInRoom = new ArrayList<Adventurer>();
        for (Adventurer a: adventurers) {
            if (Objects.equals(a.location.name, room.name)) advInRoom.add(a);
        }
        return advInRoom;
    }

    // Utility to eliminate characters at 0 hitpoints
    // This code in particular makes me wonder if a Character superclass for
    // Adventurers and Creatures is a better design...
    // duplicate code smell here...
    void SweepEliminatedCharacters() {
        Iterator<Adventurer> itr_a = adventurers.iterator();
        while (itr_a.hasNext()) {
            Adventurer a = itr_a.next();
            if (a.hitPoints <= 0) {
                exAdventurers.add(a);
                itr_a.remove();
            }
        }
        Iterator<Creature> itr_c = creatures.iterator();
        while (itr_c.hasNext()) {
            Creature c = itr_c.next();
            if (c.hitPoints<=0) {
                exCreatures.add(c);
                itr_c.remove();
            }
        }
    }
    //*************************************** Stuff I added ***************************************
    public Boolean tSameAdv(ArrayList<Treasure> t, Adventurer a){
        Boolean spot = false;
        Iterator<Treasure> itr_t = t.iterator();
        while (itr_t.hasNext()) {
            Treasure tre = itr_t.next();
            if(tre.location.name == a.location.name){
                if(a.skipSearch <= Random.rnd()){
                    if(tre.type == "trap"){
                        if(a.noTrapProb <= Random.rnd()){
                            out(a.name + " took damage from a trap");
                            a.hitPoints -= 1;
                        }
                    }
                    else if(tre.type== "sword"){                        
                        if(a.sword == false){
                            a.sword = true;
                            a.modifier += 1;
                            out(a.name + " finds treasure " + tre.name);
                            itr_t.remove();
                            spot = true;
                            break;
                        }
                    }
                    else if(tre.type == "gem"){
                        if(a.gem == false){
                            a.gem = true;
                            //maybe cheating
                            a.modifier -= 1;
                            //end cheating
                            out(a.name + " finds treasure " + tre.name);
                            itr_t.remove();
                            spot = true;
                            break;
                        }
                    }
                    else if(tre.type == "armor"){
                        if(a.armor == false){
                            a.armor = true;
                            //maybe cheating
                            a.modifier += 1;
                            //end cheating
                            out(a.name + " finds treasure " + tre.name);
                            itr_t.remove();
                            spot = true;
                            break;
                        }
                    }
                    else if(tre.type == "portal"){
                        if(a.portal == false){
                            a.portal = true;
                            out(a.name + " finds treasure " + tre.name);
                            itr_t.remove();
                            spot = true;
                            break;
                        }
                    }
                    else if(tre.type == "potion"){
                        if(a.potion == false){
                            a.potion = true;
                            a.hitPoints += 1;
                            out(a.name + " finds treasure " + tre.name);
                            itr_t.remove();
                            spot = true;
                            break;
                        }
                    }
                }
            }
        }
        return spot;
    }
    //*************************************** End of stuff I added ***************************************
}

interface Logger {
    default void out(String msg) {
        System.out.println(msg);
    }
}

abstract class Adventurer implements Logger{
    String name;
    Room location;
    AdventurerType type;
    Integer hitPoints;

    //*************************************** Stuff I added ***************************************
    Boolean sword;
    Boolean gem;
    Boolean armor;
    Boolean portal;
    Boolean potion;

    Integer modifier;

    CombatStrategy cs;
    double noCombatProb;

    SearchStrategy ss;
    int searchNum;
    double skipSearch;
    double noTrapProb;
    //*************************************** End of stuff I added ***************************************

    public Adventurer(String name, Room location) {
        this.name = name;
        this.location = location;
        this.type = AdventurerType.NONE;
        this.hitPoints = 3;

        //*************************************** Stuff I added ***************************************
        this.sword = false;
        this.gem = false;
        this.armor = false;
        this.portal = false;
        this.potion = false;

        this.modifier = 0;
        this.noCombatProb = 0;
        this.searchNum = 10;
        this.skipSearch = 0;
        this.noTrapProb = 0;
        //*************************************** End of stuff I added ***************************************
    }

    abstract void fight(ArrayList<Creature> creatures);
    abstract boolean search();

    // default movement
    void move(ArrayList<Room> rooms) {
        //*************************************** Stuff I added **********************************************
        if(this.portal == true){
            if(Random.rndFromRange(0, 1) == 0){
                this.location = Room.GetRandomRoom(rooms, false);
                out(name+" moves to "+location.toString());
            }
            else if(Random.rndFromRange(0, 1) == 1){
                //out("no portal");
                int roomCount = location.connectedRooms.size();
                int roomChoice = Random.rndFromRange(0,roomCount-1);
                location = location.connectedRooms.get(roomChoice);
                out(name+" moves to "+location.toString());
            }
        }
        else{
            int roomCount = location.connectedRooms.size();
            int roomChoice = Random.rndFromRange(0,roomCount-1);
            location = location.connectedRooms.get(roomChoice);
            out(name+" moves to "+location.toString());
        }
        //*************************************** End of stuff I added ***************************************

        // What rooms are connected to mine?  Randomly pick one to move to
        /*int roomCount = location.connectedRooms.size();
        int roomChoice = Random.rndFromRange(0,roomCount-1);
        location = location.connectedRooms.get(roomChoice);
        out(name+" moves to "+location.toString());*/
    }

    void FightCreature(ArrayList<Creature> creatures, int modifier) {
        if(noCombatProb <= Random.rnd()){
            for (Creature c: creatures) {
                // be sure no-one that's at 0 hitpoints is fighting
                if ((hitPoints>0) && (c.hitPoints>0)) {
                    // make combat rolls
                    int advRoll = Random.RollTwoDice() + modifier;
                    int creRoll = Random.RollTwoDice();
                    // announce results and update affected character hitpoints
                    if (advRoll > creRoll) {
                        out(name + " defeats " + c.name);
                        c.hitPoints -= 1;
                    }
                    if (advRoll < creRoll) {
                        out(c.name + " defeats " + name);
                        hitPoints -= 1;
                    }
                    if (advRoll == creRoll) {
                        out(c.name + " fights " + name + " to a draw");
                    }
                }
            }
        }
    }
    //*************************************** Stuff I added ***************************************
    public class CombStrat implements CombatStrategy{
        @Override
        public void Stealth(){
            modifier = 0;
            noCombatProb = 0.5;
        }
        @Override
        public void Untrained(){
            modifier = 0;
        }
        @Override
        public void Trained(){
            modifier = 1;
        }
        @Override
        public void Expert(){
            modifier = 2;
        }
    }

    class SearchStrat implements SearchStrategy{
        @Override 
        public void Careful(){
            searchNum = 7;
            //on teasure 50% chance to dodge
            noTrapProb = 0.5;
        }
        @Override 
        public void Quick(){
            searchNum = 9;
            skipSearch = 0.67;
        }
        @Override 
        public void Careless(){
            searchNum = 10;
        }
    }
}
    //*************************************** End of stuff I added ***************************************


// Only implementing Runners and Thieves, Brawlers and Sneakers are left as an exercise to the reader :-)
// I do this to avoid parsing output from getclass() and to be clear about what object I'm talking to
enum AdventurerType {
    RUNNER,
    THIEF,
    BRAWLER,
    SNEAKER,
    NONE;
    private AdventurerType() {
    }
}

// the subclasses are very similar, but using them prevents me from having to make
// conditional checks on calling variations of methods
class Runner extends Adventurer {
    public Runner(String name, Room location) {
        super(name, location);
        type = AdventurerType.RUNNER;

        this.cs = new CombStrat();
        cs.Untrained();

        this.ss = new SearchStrat();
        ss.Quick();
    }
    @Override
    void fight(ArrayList<Creature> creatures) {
        //super.FightCreature(creatures, 0);

        //*************************************** Stuff I added ***************************************
        super.FightCreature(creatures, modifier);
        //*************************************** End of stuff I added ***************************************
    };
    @Override
    boolean search() {
        //*************************************** Stuff I added ***************************************
        //*************************************** End of stuff I added ***************************************
        
        return (Random.RollTwoDice() >= searchNum); // true if 10 or better roll
    };
}

class Thief extends Adventurer {
    public Thief(String name, Room location) {
        super(name, location);
        type = AdventurerType.THIEF;

        this.cs = new CombStrat();
        cs.Trained();

        this.ss = new SearchStrat();
        ss.Careful();
    }
    @Override
    void fight(ArrayList<Creature> creatures) {
        super.FightCreature(creatures, modifier);
    };
    @Override
    boolean search() {
        return ((Random.RollTwoDice()+1) >= searchNum); // true if 10 or better roll with +1 modifier
    };
}

class Brawler extends Adventurer{
    public Brawler(String name, Room location){
        super(name, location);
        type = AdventurerType.BRAWLER;

        this.cs = new CombStrat();
        cs.Expert();

        this.ss = new SearchStrat();
        ss.Careless();
    }
    @Override
    void fight(ArrayList<Creature> creatures) {
        super.FightCreature(creatures, modifier);
    };
    @Override
    boolean search() {
        return ((Random.RollTwoDice()) >= searchNum); // true if 10 or better roll with +1 modifier
    };
}

class Sneaker extends Adventurer{
    public Sneaker(String name, Room location){
        super(name, location);
        type = AdventurerType.BRAWLER;

        this.cs = new CombStrat();
        cs.Stealth();

        this.ss = new SearchStrat();
        ss.Quick();
    }
    @Override
    void fight(ArrayList<Creature> creatures) {
        super.FightCreature(creatures, modifier);
    };
    @Override
    boolean search() {
        return ((Random.RollTwoDice()) >= searchNum); // true if 10 or better roll with +1 modifier
    };
}

abstract class Creature implements Logger {
    String name;
    Room location;
    Integer hitPoints;

    public Creature(String name, ArrayList<Room> rooms) {
        this.name = name;
        this.location = null;
        this.hitPoints = 1;
    }

    // when the creature fights, let's use the adventurer's fight method since
    // those are specialized and the creatures are generic
    void fight(ArrayList<Adventurer> adventurers) {
        for (Adventurer a: adventurers) {
            ArrayList<Creature> c = new ArrayList<Creature>();
            c.add(this);
            if ((this.hitPoints>0) && (a.hitPoints>0)) a.fight(c);
        }
    }

    abstract void move(ArrayList<Room> rooms, ArrayList<Adventurer> adventurers);
}

// Only implementing Orbiters and Seekers, Blinkers are left as an exercise to the reader :-)
// The main override in these subclasses is their movement approaches
// Plus orbiter has the direction attribute and can only start in an outside room

class Orbiter extends Creature {
    Direction direction = Random.randomEnum(Direction.class); // picks a random direction

    public Orbiter(String name, ArrayList<Room> rooms) {
        super(name, rooms);
        location = Room.GetRandomRoom(rooms, true);
    }

    @Override
    void move(ArrayList<Room> rooms, ArrayList<Adventurer> adventurers) {
        // Orbiters don't care who is where, they just move if the room they're in is empty
        location = Room.FindOrbitRoom(rooms,location,direction);
        out(name + " moves to "+location.toString());
    }
}

class Seeker extends Creature {
    public Seeker(String name, ArrayList<Room> rooms) {
        super(name, rooms);
        location = Room.GetRandomRoom(rooms, false);
    }

    @Override
    void move(ArrayList<Room> rooms, ArrayList<Adventurer> adventurers) {
        // Seekers will only move if they see a connected room with an adventurer in it
        for (Room r: location.connectedRooms) {
            for (Adventurer a: adventurers) {
                if (Objects.equals(a.location.name, r.name)) {
                    // we found a connected room with an adventurer, let's move
                    location = r;
                    out(name + " moves to "+location.toString());
                    return;
                }
            }
        }
    }
}

class Blinker extends Creature {
    public Blinker(String name, ArrayList<Room> rooms) {
        super(name, rooms);
        location = Room.GetRandomRoom(rooms, false);
        while(location.name.charAt(0) != '4')
        {
            location = Room.GetRandomRoom(rooms, false);
        } 
    }

    @Override
    void move(ArrayList<Room> rooms, ArrayList<Adventurer> adventurers) {
        this.location = Room.GetRandomRoom(rooms, false);
    }
}

enum Direction {
    CLOCKWISE,
    COUNTERCLOCKWISE;

    private Direction() {
    }
}

interface Random {
    // making this utility function that can be used by saying Random.rndFromRange(min,max)
    // https://www.baeldung.com/java-generating-random-numbers-in-range
    static int rndFromRange(int min, int max) {
        //returns a uniform inclusive random number from a given min and max range
        return (int) ((Math.random() * ((max+1) - min)) + min);
    }

    // enough 2D6 rolls to warrant a helper
    static int RollTwoDice() {
        return rndFromRange(1,6) + rndFromRange(1,6);
    }

    // just a quick random call for a random number between 0 and 1
    static double rnd() {
        return Math.random();
    }

    // a utility for getting a random enum value from any enum
    // https://stackoverflow.com/questions/1972392/pick-a-random-value-from-an-enum
    // call like randomEnum(MyEnum.class)
    static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = new java.util.Random().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}

class Room implements Logger {

    String name;    // level+x+y
    Integer level;  // 0 is ground, 1-4 are below ground and get deeper as they go
    Integer x; // horizontal location
    Integer y; // vertical location
    ArrayList<Room> connectedRooms; // rooms connected to this one

    @Override
    // We override toString here to print out the "Name" of the room in Level-x-y format
    // You can also just print out the three digit name
    public String toString() {
        return level + "-" + x + "-" + y;
    }

    public Room(Integer level, Integer x, Integer y) {
        this.name = "" + level + x + y;
        this.level = level;
        this.x = x;
        this.y = y;
        connectedRooms = new ArrayList<Room>();  // empty at first, ConnectAllRooms runs later after all rooms created
    }

    // create the initial set of rooms
    // just throw them all into an ArrayList
    // then connect them to all their connecting rooms
    public static ArrayList<Room> CreateAllRooms() {
        ArrayList<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(0, 1, 1));
        for (int level = 1; level <= 4; level++) {
            for (int x = 0; x <= 2; x++) {
                for (int y = 0; y <= 2; y++) {
                    rooms.add(new Room(level, x, y));
                }
            }
        }
//        for (Room room : rooms) {
//            room.out("Room: " + room.name);
//        }
        for (Room room : rooms) {
            room.ConnectRoom(rooms);
        }
        return rooms;
    }

    // A room knows how to connect itself to other rooms
    // Set this room's connected rooms based on its location recorded in
    // the connections array below
    // this is used by CreateAllRooms above, and only there
    private void ConnectRoom(ArrayList<Room> rooms) {
        for (int i = 0; i < connections.length; i++) {
            // given the room name, look it up in the connections array and get the
            // corresponding list of rooms
            if (Objects.equals(connections[i], name)) {
                ArrayList<String> names = new ArrayList<>(Arrays.asList(connections[i + 1].split(",")));
//                out("Room " + this.name + " is connected to ");
                for (String name : names) {
                    Room r = GetRoomByName(rooms, name);
//                    out("Room " + r.name);
                    this.connectedRooms.add(r);
                }
            }
        }
    }

    // helper function to find room by name (a HashMap would avoid this search and allow direct lookup
    // but I committed to using simple ArrayLists for stuff)
    public static Room GetRoomByName(ArrayList<Room> rooms, String name) {
        Room found = null;
        for (Room r : rooms) {
            if (Objects.equals(r.name, name)) {
                found = r;
                break;
            }
        }
        return found;
    }

    // helper function for a randomly selected room (not 011)
    // if outsideOnly is true, center rooms are rejected - used for Orbiter placement
    public static Room GetRandomRoom(ArrayList<Room> rooms, boolean outsideOnly) {
        int level = Random.rndFromRange(1,4);
        int x,y;
        do {
            x = Random.rndFromRange(0,2);
            y = Random.rndFromRange(0,2);
        } while (outsideOnly && (x == 1) && (y == 1));
        return GetRoomByName(rooms, ""+level+x+y);
    }


    // Utility function: Given a room and a direction, which room should I orbit to?
    public static Room FindOrbitRoom(ArrayList<Room> rooms, Room room, Direction dir) {
        int index = -1;
        for (int i = 0; i < orbitRooms.length; i++) {
            // given the room name, look it up in the orbitRooms array and determine the orbit room
            // based on direction
            if (Objects.equals(orbitRooms[i], room.name)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            // get the 2 possible rooms from the orbitRooms list
            ArrayList<String> names = new ArrayList<>(Arrays.asList(orbitRooms[index + 1].split(",")));
            String name;
            if (dir == Direction.CLOCKWISE) name = names.get(0);
            else name = names.get(1);  // Direction.COUNTERCLOCKWISE
            return GetRoomByName(rooms, name);
        }
        else return null;
    }

    // To keep things simple (?), I'm just using a big string array here to map rooms to their connected rooms
    // I couldn't think of a good way to it algorithmically - and this does allow for custom mapping
    // In most cases, I'd probably put this in a CSV or JSON file and read it in instead of having all this
    // inline data. It does have a special structure of room names followed by a list of connected room names
    private static final String[] connections = {
            "011", "111",
            "100", "110,101",
            "101", "100,111,102",
            "102", "101,112",
            "110", "100,111,120",
            "111", "110,101,121,112,211",
            "112", "102,111,122",
            "120", "110,121",
            "121", "120,111,122",
            "122", "112,121",
            "200", "210,201",
            "201", "200,211,202",
            "202", "201,212",
            "210", "200,211,220",
            "211", "210,201,221,212,111,311",
            "212", "202,211,222",
            "220", "210,221",
            "221", "220,211,222",
            "222", "212,221",
            "300", "310,301",
            "301", "300,311,302",
            "302", "301,312",
            "310", "300,311,320",
            "311", "310,301,321,312,211,411",
            "312", "302,311,322",
            "320", "310,321",
            "321", "320,311,322",
            "322", "312,321",
            "400", "410,401",
            "401", "400,411,402",
            "402", "401,412",
            "410", "400,411,420",
            "411", "410,401,421,412,311",
            "412", "402,411,422",
            "420", "410,421",
            "421", "420,411,422",
            "422", "412,421"
    };

    // I used this same approach for orbiting room selection
    // Given the first room, the list provides the clockwise room and the counterclockwise room
    // If there's a good algorithm for the selections, I'm not sure what it would be
    // This way, you could define paths for custom orbits I suppose...
    private static final String[] orbitRooms = {
            "100", "110,101",
            "101", "100,102",
            "102", "101,112",
            "110", "100,120",
            "112", "102,122",
            "120", "121,110",
            "121", "122,120",
            "122", "112,121",
            "200", "210,201",
            "201", "200,202",
            "202", "201,212",
            "210", "200,220",
            "212", "202,222",
            "220", "221,210",
            "221", "222,220",
            "222", "212,221",
            "300", "310,301",
            "301", "300,302",
            "302", "301,312",
            "310", "300,320",
            "312", "302,322",
            "320", "321,310",
            "321", "322,320",
            "322", "312,321",
            "400", "410,401",
            "401", "400,402",
            "402", "401,412",
            "410", "400,420",
            "412", "402,422",
            "420", "421,410",
            "421", "422,420",
            "422", "412,421"
    };

}


//*************************************** Stuff I added ***************************************
abstract class Treasure implements Logger{
    String name;
    Room location;
    String type;

    Treasure(String name, ArrayList<Room> rooms){
        this.name = name;
        this.location = null;
        this.type = null;
    }
}

class Sword extends Treasure{
    public Sword(String name, ArrayList<Room> rooms){
        super(name, rooms);
        location = Room.GetRandomRoom(rooms, false);
        type = "sword";
    }
    //this.modifier += 1;
}

class Gem extends Treasure{
    public Gem(String name, ArrayList<Room> rooms){
        super(name, rooms);
        location = Room.GetRandomRoom(rooms, false);
        type = "gem";
    }
}

class Armor extends Treasure{
    public Armor(String name, ArrayList<Room> rooms){
        super(name, rooms);
        location = Room.GetRandomRoom(rooms, false);
        type = "armor";
    }
}

class Portal extends Treasure{
    public Portal(String name, ArrayList<Room> rooms){
        super(name, rooms);
        location = Room.GetRandomRoom(rooms, false);
        type = "portal";
    }
}

class Trap extends Treasure{
    public Trap(String name, ArrayList<Room> rooms){
        super(name, rooms);
        location = Room.GetRandomRoom(rooms, false);
        type = "trap";
    }
}

class Potion extends Treasure{
    public Potion(String name, ArrayList<Room> rooms){
        super(name, rooms);
        location = Room.GetRandomRoom(rooms, false);
        type = "potion";
    }
}

