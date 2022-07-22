/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ca.sheridancollege.project;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *SYST 17796
 *The game with all the methods of the game
 * 
 * @author Kevin (Zheng Yi) Wang
 */
public class BlackJack extends Game {
    
    //All static arraylists are made for the player class to reference
    //An array list for each player's hand when they play
    public static ArrayList<Integer> handList = new ArrayList();
    
    //An array list for each player's bet
    public static ArrayList<Integer> potList = new ArrayList();
    
    //An array list for the winners
    public static ArrayList<String> winList = new ArrayList();
    
    //An array list for the eliminated
    private ArrayList<String> deadList = new ArrayList();
    
    
    public BlackJack(){
        super("Blackjack");
    }

    //Used to register players into the game. 
    //Creates a player object and prompts user for ID
    public void register(){
        //Number of players to register
        int numPlayers;
        
        //ID of each player
        String id;
        
        
        Scanner input = new Scanner(System.in);
            while(true){
                //User prompt for number of players
                System.out.println("How many players are playing?");
                
                //Makes sure it catches bad inputs
                try{
                    numPlayers = input.nextInt();
                    
                    //Catches if the player tries to be clever
                    if (numPlayers<=0){
                        System.out.println("Can't be 0 or fewer");
                        input.nextLine();
                        continue;
                    }
                    
                    //If the player actually registers
                    input.nextLine();
                    break;
                }
                catch(Exception a){
                    System.out.println("Invalid input, try again");
                    input.nextLine();
                }
            }
            
            //Prompts IDs for each player and adds them to the player list
            //This also makes a human player object for each of them
            //One slight problem is the name in the array is human for all human players, but human IDs are unique
            
            //It adds one human player first
            System.out.println("What's your player name?");
            id=input.nextLine();
            Human human = new Human(id);
            players.add(human);
            
            for(int n=1; n<numPlayers;n++){
                
                //This makes sure each user ID is unique
                boolean isRepeat=true;
                while(isRepeat){
                    System.out.println("What's your player name?");
                    id=input.nextLine();
                    
                    //This iterates through the player list
                    for (int i=0; i<n; i++){
                        
                        //This checks the all the previous ids with the current one
                        if (id.equals(players.get(i).getName())){
                            System.out.println("ID is already taken. Use another name");
                            
                            //If it's not unique, then it sets it to repeat the loop again
                            isRepeat=true;
                            break;
                        }
                        else{
                            isRepeat=false;
                        }
                    }
                }
                
                //If it's unique, then it adds it as a player
                human = new Human(id);
                players.add(human);
            }
        
    }
    
    //This is used to start a new round. It resets all arraylists.
    private void initialize(){
        handList.removeAll(handList);
        potList.removeAll(potList);
        winList.removeAll(winList);
        deadList.removeAll(deadList);
        Hand.handCards.removeAll(Hand.handCards);
    }
    
    //This checks if the player is out at the end of the round. If he is, then it unregisters them
    public void declareDead(){
        
        //Going through every player and checks whether or not they have no money
        //If they don't have money, their name is added to the deadList and they get unregistered
        for (int n=0; n<(players.size());n++){
            if(players.get(n).getBankInt()<-1){
                deadList.add(players.get(n).getName());
                players.remove(n);
            }
        }
        //Declares that no-one is eliminated, or the people that are eliminated
        if (deadList.isEmpty()){
            System.out.println("No-one got eliminated.");
        }
        else{
            System.out.println("The following people were eliminated:");
            for(int n=0; n<deadList.size(); n++){
                System.out.println(deadList.get(n));
            }
        }
    }
    
    @Override
    public void declareWinner() {
        if(winList.isEmpty()){
            System.out.println("No-one won.");
        }
        else{
            System.out.println("Let's take a moment to congralate:");
            for (int n=0; n<winList.size(); n++){
                System.out.println(winList.get(n));
            }
        }
            
        
    }
    
    
    @Override
    public void play() {
        //If this is true, then the game keeps going
        boolean startGame = true;
        
        //Makes a new deck
        GroupOfCards mainDeck= new Deck();
        
        //Creates a new dealer
        Dealer dealer = new Dealer();
        
        //registers players
        register();
        
        //Starts the game
        while (startGame){
            
            initialize();
            
            //GroupOfCards.shuffle();
            
            
            //This goes through every player's turn to place a bet and then deals the dealer's hand
            for (int n=0; n<(players.size());n++){
                while(true){
                    players.get(n).bet();
                    break;
                }
                //Dealer gets dealt and shows his hand here
            }
            
            //This goes through every player's turn
            for (int n=0; n<(players.size());n++){
                while(true){
                    players.get(n).play(n);
                    break;
                }
                //Dealer gets dealt and shows his hand here
            }
            
            dealer.play(1);
            
            declareWinner();
            declareDead();
            startGame=false;
            
        }
        
        System.out.println("Thanks for playing.");
    }
    
}