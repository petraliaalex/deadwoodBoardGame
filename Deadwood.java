// Deadwood.java --> driver class for game
// Shannon Melious and Alex Petralia
// CSCI 345 Spring 2018
// Assignment 3 Final Version


import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;

import java.util.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays; 

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Deadwood extends JFrame {

  // JLabels
   JLabel boardlabel;
   JLabel cardlabel;
   JLabel playerlabel;
   JLabel mLabel;
   ArrayList<JLabel> cardLabels = new ArrayList<JLabel>();
   JLabel cLabel;  
   JLabel sLabel;
  
  
  //JButtons
   JButton bAct;
   JButton bMove;
   JButton bStartGame;
   JButton bEndTurn;
   JButton bTakeRole;
   JButton bGetMyStats;
  
  // Image Icons
   ArrayList<ImageIcon> cardIcon = new ArrayList<ImageIcon>();
   ImageIcon cIcon;
   ImageIcon sIcon;
  
  // JLayered Pane
   JLayeredPane bPane;
  
  //Option Pane
   JOptionPane frame;
  
  //Main Lists/values
   ArrayList<Player> allP = new ArrayList<Player>();
   ArrayList<Room> allR = new ArrayList<Room>();
   int totalDays = 0;
   static Room trailer = new Room();
   static Room castingOffice = new Room();
   int tempN = 0; //number of players
   int numP = 0; //number of players
   int curPlayerNum = 0; //keeps track of current player number
   boolean gameStarted = false;
   Player curPlayer;
   boolean moved = false;
   boolean actReh = false;
   boolean upgraded = false;
   boolean tookRole = false;
   String[] allColorNames = {"cyan", "orange", "pink", "violet", "blue", "green", "red", "yellow"};
   static ArrayList<Scene> roomScenes = new ArrayList<Scene>();
  
  
  //Casting Office Prices
   int[] famePrices = {0,0,5,10,15,20,25}; //[0-indexed: rank 0 to 6]
   int[] moneyPrices = {0,0,4,10,18,28,40}; //[0-indexed: rank 0 to 6]
  
  //scene counter
   int sceneCounter = 10;
  
  //Initialize scenes and rooms from xml files
   static Document doc = null;
   static ParseCardXML parsing = new ParseCardXML();

   static Document docB = null;
   static ParseBoardXML parser = new ParseBoardXML();

   static ArrayList<Scene> sceneDeck = new ArrayList<Scene>();
  //sceneDeck = null;

   static ArrayList<Room> locations = new ArrayList<Room>();
  //locations = null;
  
  
  //main method
   public static void main(String[] args){
            //Start GUI
      Deadwood board = new Deadwood();
      board.startBoardListener();
      
      
      try{
         
         //Add information from the parsers into the scenes and rooms
         doc = parsing.getDocFromFile("cards.xml");
         sceneDeck = parsing.createSceneDeck(doc);
      
         docB = parser.getDocFromFile("board.xml");
         locations = parser.createRooms(docB);
         
      } catch(Exception ex){
         System.out.println("Error = "+ex);
      }
         
      
      //Set location of Trailers and Casting Office
      
      for(int i=0; i < locations.size(); i++){
         if("Trailer".equals(locations.get(i).getName())){
            trailer = locations.get(i);
         }
         else if("Casting Office".equals(locations.get(i).getName())){
            castingOffice = locations.get(i);
         }
      }
      
   
   
   
   
      //Initialize prices for upgrades
      int[][] famePrices = new int[7][1];
      int[][] moneyPrices = new int[7][1];
      famePrices[2][0] = 5;
      famePrices[3][0] = 10;
      famePrices[4][0] = 15;
      famePrices[5][0] = 20;
      famePrices[6][0] = 25;
      moneyPrices[2][0] = 4;
      moneyPrices[3][0] = 10;
      moneyPrices[4][0] = 18;
      moneyPrices[5][0] = 28;
      moneyPrices[6][0] = 40;
      
      //set shots
      
      for(int i=0; i < locations.size(); i++){
         if(locations.get(i).getName() != "Trailer"){
            if(locations.get(i).getName() != "Casting Office"){
               if(locations.get(i).getName().equals("Train Station")){
                  Shot tsShot1 = new Shot(3, 36, 11, 47, 47);
                  Shot tsShot2 = new Shot(2, 89, 11, 47, 47);
                  Shot tsShot3 = new Shot(1, 141, 11, 47, 47);
                  ArrayList<Shot> tsShots = new ArrayList<Shot>();
                  tsShots.add(tsShot1);
                  tsShots.add(tsShot2);
                  tsShots.add(tsShot3);
                  locations.get(i).setShots(tsShots);
               }
               else if(locations.get(i).getName().equals("Secret Hideout")){
                  Shot shShot1 = new Shot(3, 244, 764, 47, 47);
                  Shot shShot2 = new Shot(2, 299, 764, 47, 47);
                  Shot shShot3 = new Shot(1, 354, 764, 47, 47);
                  ArrayList<Shot> shShots = new ArrayList<Shot>();
                  shShots.add(shShot1);
                  shShots.add(shShot2);
                  shShots.add(shShot3);
                  locations.get(i).setShots(shShots);
               }
               else if(locations.get(i).getName().equals("Church")){
                  Shot chShot1 = new Shot(2, 623, 675, 47, 47);
                  Shot chShot2 = new Shot(1, 682, 675, 47, 47);
                  ArrayList<Shot> chShots = new ArrayList<Shot>();
                  chShots.add(chShot1);
                  chShots.add(chShot2);
                  locations.get(i).setShots(chShots);
               }
               else if(locations.get(i).getName().equals("Hotel")){
                  Shot hShot1 = new Shot(3, 1005, 683, 47, 47);
                  Shot hShot2 = new Shot(2, 1058, 683, 47, 47);
                  Shot hShot3 = new Shot(1, 1111, 683, 47, 47);
                  ArrayList<Shot> hShots = new ArrayList<Shot>();
                  hShots.add(hShot1);
                  hShots.add(hShot2);
                  hShots.add(hShot3);
                  locations.get(i).setShots(hShots);
               }
               else if(locations.get(i).getName().equals("Main Street")){
                  Shot msShot1 = new Shot(3, 912, 23, 47, 47);
                  Shot msShot2 = new Shot(2, 858, 23, 47, 47);
                  Shot msShot3 = new Shot(1, 804, 23, 47, 47);
                  ArrayList<Shot> msShots = new ArrayList<Shot>();
                  msShots.add(msShot1);
                  msShots.add(msShot2);
                  msShots.add(msShot3);
                  locations.get(i).setShots(msShots);
               }
               else if(locations.get(i).getName().equals("Jail")){
                  Shot jShot1 = new Shot(1, 442, 156, 47, 47);
                  ArrayList<Shot> jShots = new ArrayList<Shot>();
                  jShots.add(jShot1);
                  locations.get(i).setShots(jShots);
               }
               else if(locations.get(i).getName().equals("General Store")){
                  Shot gsShot1 = new Shot(2, 313, 277, 47, 47);
                  Shot gsShot2 = new Shot(1, 313, 330, 47, 47);
                  ArrayList<Shot> gsShots = new ArrayList<Shot>();
                  gsShots.add(gsShot1);
                  gsShots.add(gsShot2);
                  locations.get(i).setShots(gsShots);
               }
               else if(locations.get(i).getName().equals("Ranch")){
                  Shot rShot1 = new Shot(2, 472, 473, 47, 47);
                  Shot rShot2 = new Shot(1, 525, 473, 47, 47);
                  ArrayList<Shot> rShots = new ArrayList<Shot>();
                  rShots.add(rShot1);
                  rShots.add(rShot2);
                  locations.get(i).setShots(rShots);
               }
               else if(locations.get(i).getName().equals("Bank")){
                  Shot bShot1 = new Shot(1, 840, 549, 47, 47);
                  ArrayList<Shot> bShots = new ArrayList<Shot>();
                  bShots.add(bShot1);
                  locations.get(i).setShots(bShots);
               }
               else if(locations.get(i).getName().equals("Saloon")){
                  Shot sShot1 = new Shot(2, 626, 216, 47, 47);
                  Shot sShot2 = new Shot(1, 679, 216, 47, 47);
                  ArrayList<Shot> sShots = new ArrayList<Shot>();
                  sShots.add(sShot1);
                  sShots.add(sShot2);
                  locations.get(i).setShots(sShots);
               }
            }
         }
      }
      
            //roomScenes at the top
      for(int i=0; i < locations.size(); i++){
         roomScenes.add(sceneDeck.get(i));
      }
      
      // Set the a scene for each room from locations
      for(int i=0; i < locations.size(); i++){
         if(locations.get(i).getName() != "Trailer"){
            if(locations.get(i).getName() != "Casting Office"){
               locations.get(i).setScene(roomScenes.get(i)); 
            }
         }
      }
      
            //Set attributes for the adjcent rooms (of each location)
      for(int i=0; i < locations.size(); i++){
         ArrayList<Room> locAdjRooms = locations.get(i).getAdjRooms();
         
         for(int j=0; j < locAdjRooms.size(); j++){
            Room anAdjRoom = locAdjRooms.get(j);
            
            for(int k=0; k <locations.size(); k++){
               if((anAdjRoom.getName()).equals(locations.get(k).getName())){
               
                  anAdjRoom.setRoles(locations.get(k).getRoles());
                  anAdjRoom.setUpgrades(locations.get(k).getUpgrades());
                  anAdjRoom.setShots(locations.get(k).getShots());
                  anAdjRoom.setAdjRooms(locations.get(k).getAdjRooms());
                  anAdjRoom.setScene(locations.get(k).getCurrentScene());
                  anAdjRoom.setNumShots(locations.get(k).getNumShots());
                  anAdjRoom.setX(locations.get(k).getX());
                  anAdjRoom.setY(locations.get(k).getY());
                  anAdjRoom.setH(locations.get(k).getH());
                  anAdjRoom.setW(locations.get(k).getW());
               }
            }
         }
      }
      
     
      
      //set adjRooms for traile and Casting Office
      trailer.setName("Trailer");
      ArrayList<Room> trailerAdjRooms = new ArrayList<Room>();
      for(int i=0; i < locations.size(); i++){
         if(locations.get(i).getName().equals("Main Street")){
            trailerAdjRooms.add(locations.get(i));
         }
         else if(locations.get(i).getName().equals("Saloon")){
            trailerAdjRooms.add(locations.get(i));
         }
         else if(locations.get(i).getName().equals("Hotel")){
            trailerAdjRooms.add(locations.get(i));
         }
      }
      
      trailer.setAdjRooms(trailerAdjRooms);
      
      trailer.setX(991);
      trailer.setY(248);
      trailer.setH(194);
      trailer.setW(201);
      
      
      
      
      castingOffice.setName("Casting Office");
      ArrayList<Room> officeAdjRooms = new ArrayList<Room>();
      for(int i=0; i < locations.size(); i++){
         if(locations.get(i).getName().equals("Train Station")){
            officeAdjRooms.add(locations.get(i));
         }
         else if(locations.get(i).getName().equals("Ranch")){
            officeAdjRooms.add(locations.get(i));
         }
         else if(locations.get(i).getName().equals("Secret Hideout")){
            officeAdjRooms.add(locations.get(i));
         }
      }
      
      castingOffice.setAdjRooms(officeAdjRooms);
      
      castingOffice.setX(9);
      castingOffice.setY(459);
      castingOffice.setH(208);
      castingOffice.setW(209);
      
   
      
      
      
   
   }
  
  // Constructor  
   public Deadwood() {
      
       // Set the title of the JFrame
      super("Deadwood");
       // Set the exit option for the JFrame
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
       // Create the JLayeredPane to hold the display, cards, dice and buttons
      bPane = getLayeredPane();
    
       // Create the deadwood board
      boardlabel = new JLabel();
      ImageIcon icon =  new ImageIcon("board.jpg");
      boardlabel.setIcon(icon); 
      boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
      
      
       // Add the board to the lowest layer
      bPane.add(boardlabel, new Integer(0));
      
       // Set the size of the GUI
      setSize(icon.getIconWidth()+200,icon.getIconHeight());
      
       // Create the Menu for action buttons
      mLabel = new JLabel("MENU");
      mLabel.setBounds(icon.getIconWidth()+95,40,200,40);
      bPane.add(mLabel,new Integer(2));
   
       // Create Action buttons
      bAct = new JButton("ACT OR REHEARSE");
      bAct.setBackground(Color.white);
      bAct.setBounds(icon.getIconWidth()+20, 80,200, 40);
      bAct.addMouseListener(new boardMouseListener());
       
      bMove = new JButton("MOVE");
      bMove.setBackground(Color.white);
      bMove.setBounds(icon.getIconWidth()+20,120,200, 40);
      bMove.addMouseListener(new boardMouseListener());
       
      bTakeRole = new JButton("TAKE ROLE");
      bTakeRole.setBackground(Color.white);
      bTakeRole.setBounds(icon.getIconWidth()+ 20,160,200,40);
      bTakeRole.addMouseListener(new boardMouseListener());
       
      bEndTurn = new JButton("END TURN");
      bEndTurn.setBackground(Color.white);
      bEndTurn.setBounds(icon.getIconWidth()+ 20,240,200,40);
      bEndTurn.addMouseListener(new boardMouseListener());
       
       
      bStartGame = new JButton("START GAME");
      bStartGame.setBackground(Color.white);
      bStartGame.setBounds(icon.getIconWidth()+ 20,360,200,40);
      bStartGame.addMouseListener(new boardMouseListener());
      
      bGetMyStats = new JButton("GET MY STATS");
      bGetMyStats.setBackground(Color.white);
      bGetMyStats.setBounds(icon.getIconWidth()+ 20,200,200,40);
      bGetMyStats.addMouseListener(new boardMouseListener());
       
       
       
       
   
       // Place the action buttons in the top layer
      bPane.add(bAct, new Integer(2));
      bPane.add(bMove, new Integer(2));
      bPane.add(bStartGame, new Integer(2));
      bPane.add(bEndTurn, new Integer(2));
      bPane.add(bTakeRole, new Integer(2));
      bPane.add(bGetMyStats, new Integer(2));
      
      //create text field
      //JTextField textField = new JTextField();
      //textField.setText("PLAYER DATA");
      //textField.setColumns(10);
      JTextField textField = new JTextField("PLAYER DATA",10);
      bPane.add(textField);
   }
  
  // This class implements Mouse Events
  
   class boardMouseListener implements MouseListener{
      
      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {
         //if a game hasn't started yet
         if((e.getSource()== bAct || e.getSource()== bMove 
         || e.getSource() == bEndTurn || e.getSource()== bTakeRole || e.getSource() == bGetMyStats) && (gameStarted == false)){
            JOptionPane.showMessageDialog(frame, "Please Start a game first.");
         //starts game
         }else if (e.getSource()== bStartGame){
            if(gameStarted == true){
               JOptionPane.showMessageDialog(frame, "The game has already started!");
            
            
            //START GAME
            }else if(gameStarted == false){
               gameStarted = true;
               
               //ask for number of players------------------------------------------------------------------------------------------------------------------------
               boolean validIn = false;
               do{
                  try{    
                     numP = Integer.parseInt(JOptionPane.showInputDialog(frame,
                        "How many players?"));
                     validIn = true;
                  }catch(IllegalArgumentException z){
                     JOptionPane.showMessageDialog(frame, "Wrong input type, should be an Integer.");
                  }
               }while(validIn == false);
               
               while(numP < 2 || numP > 8){
                  JOptionPane.showMessageDialog(frame, "You entered " + numP + " player(s). Invalid, please choose 2 to 8 players.");         
                  numP = Integer.parseInt(JOptionPane.showInputDialog(frame,
                     "How many players?"));
               }
               JOptionPane.showMessageDialog(frame, "Okay, " + numP + " players.");
               
               //sets temp to number of players
               tempN = numP;
               
               String[] allColors = new String[numP];
               //--------------------------------------------------------------------------------------------------------------------------------------------------
               
               
               
               
               //----------------------------------------------------------set number of days----------------------------------------------------------------------
                     //Set number of days based on number of players
               if((numP == 2) || (numP == 3)){
                  totalDays = 3;
               
               }else{
                  totalDays = 4;
               
               }
               
               //--------------------------------------------------------------------------------------------------------------------------------------------------
                        
            
               
               // Add scene card to each room
               for(int i=0; i < locations.size()-8; i++){
                  cLabel = new JLabel();
                  cIcon =  new ImageIcon("back.png"); // image for the scene card
                  cLabel.setIcon(cIcon); 
                  int x = locations.get(i).getX();
                  int y = locations.get(i).getY();
                  cLabel.setBounds(x,y,cIcon.getIconWidth()+2,cIcon.getIconHeight());
                  cLabel.setOpaque(true);
                  cardLabels.add(cLabel);
                  locations.get(i).getCurrentScene().setLabel(cLabel);
                  bPane.add(cardLabels.get(i), new Integer(1));
               }
                    
                    
                // Add shot markers to proper areas in each room
               for(int i=0; i < locations.size()-8; i++){
                  if(locations.get(i).getName() != "Trailer"){
                     if(locations.get(i).getName() != "Casting Office"){
                        ArrayList<Shot> roomShots = locations.get(i).getShots();
                        for(int j=0; j < roomShots.size(); j++){
                        
                           int shotX = roomShots.get(j).getX();
                           int shotY = roomShots.get(j).getY();
                           int shotH = roomShots.get(j).getH();
                           int shotW = roomShots.get(j).getW();
                        
                           sLabel = new JLabel();
                           sIcon = new ImageIcon("shot.png");
                           sLabel.setIcon(sIcon);
                        
                           sLabel.setBounds(shotX, shotY, shotH, shotW);
                           sLabel.setOpaque(false);
                           roomShots.get(j).setLabel(sLabel);
                           bPane.add(sLabel, new Integer(1));
                        }
                     }
                  }
               }    
               
               
               ////------------------------------------------------------ask users for die colors-------------------------------------------------------------------
               ArrayList<String> allColorsStatic = new ArrayList<>(Arrays.asList("cyan", "orange", "pink", "violet", "blue", "green", "red", "yellow")); //list for reference only
               ArrayList<String> allColorsTemp = new ArrayList<>(Arrays.asList("cyan", "orange", "pink", "violet", "blue", "green", "red", "yellow")); //list to use remove() on
               
               for(int i = 0; i < numP; i++){
                  Player newPlayer = new Player();
               
                  //MY EDIT: Initialize rank, money, and fame for each player
                  if(numP <= 4){
                     newPlayer.setRank(1);
                     newPlayer.setMoney(0);
                     newPlayer.setFame(0);
                  } 
                  else if(numP == 5){
                     newPlayer.setRank(1);
                     newPlayer.setMoney(0);
                     newPlayer.setFame(2);
                  }
                  else if(numP == 6){
                     newPlayer.setRank(1);
                     newPlayer.setMoney(0);
                     newPlayer.setFame(4);
                  }
                  else if(numP > 6){
                     newPlayer.setRank(2);
                     newPlayer.setMoney(0);
                     newPlayer.setFame(0);
                  }
                  //Set players initial location to trailer
                  newPlayer.move(trailer);
                  allP.add(newPlayer);
                  
                  
                  
               
                  String colorIn = JOptionPane.showInputDialog(frame,
                     "Player " + (i + 1) + " choose your color: [ cyan, orange, pink, violet, blue, green, red, yellow ]");
                  //if a valid color but already taken
                  while(allColorsStatic.contains(colorIn) && !allColorsTemp.contains(colorIn)){
                     colorIn = JOptionPane.showInputDialog(frame, "Sorry the color " + colorIn + " is already taken, choose a color not taken. [ cyan, orange, pink, purple, blue, green, red, yellow]");
                  
                  }
                  //if invalid color
                  while(!allColorsStatic.contains(colorIn)){
                     colorIn = JOptionPane.showInputDialog(frame, "You entered " + colorIn + ". Invalid, please choose: cyan, orange, pink, purple, blue, green, red, yellow.");
                  }
                  //if valid color
                  if(allColorsStatic.contains(colorIn)){
                     allColorsTemp.remove(colorIn);
                     
                     
                     
                     
                     //errors: dice are on top of eachother
                     
                     //Shannon: Display new player's color of die on the board
                     allP.get(i).setColor(colorIn.charAt(0));
                     char color = allP.get(i).getColor();
                     int rank = allP.get(i).getRank();
                     
                     playerlabel = new JLabel();
                     String ex = "" + color;
                     String file = ex+rank+".png";
                     ImageIcon pIcon = new ImageIcon(file);
                     playerlabel.setIcon(pIcon);
                     int xt = allP.get(i).getCurrentRoom().getX();
                     //xt +=  20 * numP; //makes next die close to the other
                     int yt = allP.get(i).getCurrentRoom().getY();
                     int ht = allP.get(i).getCurrentRoom().getH();
                     int wt = allP.get(i).getCurrentRoom().getW();
                     playerlabel.setBounds(xt,yt,ht,wt);
                     allP.get(i).setLabel(playerlabel);
                     bPane.add(playerlabel,new Integer(3));
                  
                     //set colors
                     allColors[i] = colorIn;
                     newPlayer.setColor(color);
                  
                  
                  
                  
                     /////////////////////////////////////////////////////////
                     
                     
                     
                     
                     
                     
                  }
                  
                  
                  
                  
                  
                  
               
               //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
               
               
               
               ////-------------------------------------------------initialize player data//-----------------------------------------------------------------------------------------
                  
               
               }
            }
               
         //----------------------if game is already started-----------------------------------
         }else if(gameStarted == true){
            //if the first player's turn
            
            curPlayer = allP.get(curPlayerNum);//grabs current player [0-indexed]
            
            
            
            if(e.getSource() == bGetMyStats){
                  //wanted stats//gets full color name (only the first letter is given)
                  String myColor = "";
                  int myRank = curPlayer.getRank();
                  int myMoney = curPlayer.getMoney();
                  int myFame = curPlayer.getFame();
                  String myRoomName = "";
                  String myRoleName = "";
                  int myRehTokens = curPlayer.getRehearseTokens();
                  
                  //gets full color name (only the first letter is given)
                  String myColorLetter = "" + curPlayer.getColor();
                  for(int i = 0; i < allColorNames.length; i++){
                     String curCol = allColorNames[i].substring(0,1);
                     if(curCol.equals(myColorLetter)){
                        myColor = allColorNames[i];
                     }
                  }
                  //checks if any values are null
                  if(curPlayer.getCurrentRoom() == null){
                     myRoomName = "Trailers";
                  }else{
                     myRoomName = curPlayer.getCurrentRoom().getName();
                  }
                  if(curPlayer.getCurrentRole() == null){
                     myRoleName = "You do not have a role yet.";
                  }else{
                     myRoleName = curPlayer.getCurrentRole().getRoleName();
                  }

                  JOptionPane.showMessageDialog(frame, "Here are your stats:"
                  +"\n"
                  +"\n"
                  +"Color: " + myColor
                  +"\n"
                  +"Rank: " + myRank
                  +"\n"
                  +"Bank: $" + myMoney
                  +"\n"
                  +"Fame: " + myFame
                  +"\n"
                  +"Room: " + myRoomName
                  +"\n"
                  +"Role: " + myRoleName + " (" + myRehTokens + " Rehearse Tokens)");
         
            //if the player needs to end their turn
            
            //*****************work on this so they don't move mroe than once
            }else if(((moved == true && actReh == true) || (moved == true && actReh == true && tookRole == true)) && (e.getSource() == bAct ||e.getSource() == bMove ||
             e.getSource() == bTakeRole)){
               JOptionPane.showMessageDialog(frame, "Please end your turn, you have already moved and acted or rehearsed.");
            
            
            
            //------------------if the player still can use their turn--------------
            }else if((moved == false || actReh == false || tookRole == false) || e.getSource() == bEndTurn){
            
            
            
               if (e.getSource()== bAct){
                  if(curPlayer.getCurrentRole() == null){
                     JOptionPane.showMessageDialog(frame, "You do not have a role to act for yet.");
                  
                  }else if(curPlayer.getHasRole() == true){
                  //act logic---------------------------
                     Role curRole = curPlayer.getCurrentRole();
                     curRole.setReqRoll(curPlayer.getCurrentScene().getBudget());
                     String[] rehOrRoll = {"Roll", "Rehearse"};
                     
                     //option dialogue for roll or rehearse options
                     int in1 = JOptionPane.showOptionDialog(frame, "Player " + numP  
                        +" you currently have role called '" + curRole.getRoleName() + "'."
                        + " The role has a required roll of " + curRole.getReqRoll() 
                        + " and you have " + curPlayer.getRehearseTokens() + " rehearse tokens for this role. "
                        +  "Will you choose to act(roll) or rehearse for your role?", 
                        "Act or Rehearse", JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.INFORMATION_MESSAGE, null, rehOrRoll, rehOrRoll[0]);
                     
                    
                  
                     if(in1 == 0){
                     
                     //Prepare dice roll
                        Random rand  = new Random();
                        int num1 = rand.nextInt(6) + 1;
                        if(curPlayer.getCurrentRole().getType().equals("On-Card")){
                           //Player says line
                           JOptionPane.showMessageDialog(frame, "Player " + numP + "'s Line: ''" 
                              + curPlayer.getCurrentRole().getLine() + "''");
                           
                           if(num1 + curPlayer.getRehearseTokens() >= curPlayer.getCurrentRole().getReqRoll()){
                           
                              //Rewards for success
                              curPlayer.setRehearseTokens(-(curPlayer.getRehearseTokens())); //resets rehearse tokens
                              curPlayer.setFame(2);
                              JOptionPane.showMessageDialog(frame,"Congrats Player " + numP + "! your role was a "  
                                 + "success and you earned 2 fame. Your fame is now " + curPlayer.getFame()); 
                              curPlayer.setHasRole(false);
                              curPlayer.setCurrentRole(null);
                              curPlayer.getCurrentRoom().getCurrentScene().setNumRoles(-1);
                              //Shannon: remove one shot marker on the board
                              if(curPlayer.getCurrentRoom().getShots().size() > 0){
                                 curPlayer.getCurrentRoom().getShots().get(0).getLabel().setIcon(null);
                                 //ImageIcon newIcon = new ImageIcon(null);
                                 //curPlayer.getCurrentRoom().getShots().getLabel().setIcon(newIcon);
                                 //curPlayer.getCurrentRoom().getShots().get(0).getLabel().revalidate();
                                 curPlayer.getCurrentRoom().getShots().remove(0);
                                 curPlayer.getCurrentRoom().setNumShots(-1);
                              }
                              /////////////////////////////////////////////////////////
                              
                              //if last role on scene card
                              //Shannon: remove scene card from the room on the board
                              if(curPlayer.getCurrentRoom().getNumShots() == 0){
                                 JOptionPane.showMessageDialog(frame, "The Scene in " + curPlayer.getCurrentRoom().getName() + " has ended!");
                                 sceneCounter--;
                                 curPlayer.getCurrentRoom().getCurrentScene().getLabel().setIcon(null);
                                 //curPlayer.getCurrentRoom().getCurrentScene().getLabel().revalidate();
                                 
                              
                              }
                              actReh = true;
                           }else{
                              JOptionPane.showMessageDialog(frame, "Sorry Player " + numP + ", you failed your role and will earn nothing.");
                              actReh = true;
                           }
                        
                        }else if(curPlayer.currentRole.getType().equals("Off-Card")){
                        //Player says line
                           JOptionPane.showMessageDialog(frame, "Player " + numP + "'s Line: ''" + curPlayer.getCurrentRole().getLine() + "''");
                           if(num1 + curPlayer.getRehearseTokens() >= curPlayer.getCurrentRole().getReqRoll()){
                              //Rewards for success
                              curPlayer.setRehearseTokens(-(curPlayer.getRehearseTokens())); //resets rehearse tokens
                              curPlayer.setFame(1); 
                              curPlayer.setMoney(1); 
                              JOptionPane.showMessageDialog(frame, "Congrats Player " + numP 
                                 + "! your role was a success and you earned "  
                                 + "1 fame and $1. Your fame is now " + curPlayer.getFame() 
                                 + "Your money is equal to $" + curPlayer.getMoney() + ".");
                              actReh = true;
                              curPlayer.setHasRole(false);
                              curPlayer.setCurrentRole(null);
                              curPlayer.getCurrentRoom().getCurrentScene().setNumRoles(-1);
                              
                              //remove shot counter
                              if(curPlayer.getCurrentRoom().getShots().size() > 0){
                                 curPlayer.getCurrentRoom().getShots().get(0).getLabel().setIcon(null);
                                 //ImageIcon newIcon = new ImageIcon(null);
                                 //curPlayer.getCurrentRoom().getShots().getLabel().setIcon(newIcon);
                                 //curPlayer.getCurrentRoom().getShots().get(0).getLabel().revalidate();
                                 curPlayer.getCurrentRoom().getShots().remove(0);
                                 curPlayer.getCurrentRoom().setNumShots(-1);
                              }
                              curPlayer.getCurrentRoom().setNumShots(-1);
                              if(curPlayer.getCurrentRoom().getCurrentScene().getNumRoles() == 0){
                                 JOptionPane.showMessageDialog(frame, "The Scene in " + curPlayer.getCurrentRoom().getName() + " has ended!");
                                 //JOptionPane.showMessageDialog(frame, "The Scene in " + curPlayer.getCurrentRoom().getName() + " has ended!");
                                 sceneCounter--;
                                 curPlayer.getCurrentRoom().getCurrentScene().getLabel().setIcon(null);
                              }
                           
                           }else{
                              curPlayer.setMoney(1);
                              JOptionPane.showMessageDialog(frame, "Sorry Player " + numP 
                                 + ", you failed your role but you will still earn $1 since this is an off-card role.");
                              actReh = true;
                           }
                        }
                     
                     //Update rehearsal token count if they choose to rehearse
                     }else if(in1 == 1){
                        curPlayer.setRehearseTokens(1);
                        JOptionPane.showMessageDialog(frame, "Okay Player " + numP + " you rehearsed for this role and have "+
                           + curPlayer.getRehearseTokens() + " for this role.");
                        actReh = true;
                     }
                  //end of act logic--------------------
                  }
               
               //------------------GET MY STATS logic
                
                  
               
               
               
               
               
               
               
               
               
               
               
               
               
               }else if (e.getSource()== bMove){
                  if(moved == true){
                     JOptionPane.showMessageDialog(frame, "You already moved this turn.");
                  }else if(curPlayer.getHasRole() == true){
                     JOptionPane.showMessageDialog(frame, "You currently have role, you cannot move until you complete the role.");
                  }else if(moved == false){
                     ArrayList<Room> neighbors = curPlayer.getCurrentRoom().getAdjRooms();
                     
                     String[] tempNames = new String[neighbors.size()];
                     for(int k = 0; k < neighbors.size(); k++){
                        tempNames[k] = neighbors.get(k).getName();
                     }
                     
                     //sets each option to a 0-indexed interval, "u" will save this interval
                     int u = JOptionPane.showOptionDialog(frame, "You are currently in " + curPlayer.getCurrentRoom().getName()
                        + ". Here are the rooms that have available roles. Which room will you move to?", "Move to Room", JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.INFORMATION_MESSAGE, null, tempNames, tempNames[0]);
                     
                     if(tempNames[u].equals("office")){
                        curPlayer.move(castingOffice);
                     }else{
                     
                     
                        for(int p = 0; p < neighbors.size(); p++){
                           if(tempNames[u].equals(neighbors.get(p).getName())){
                              curPlayer.move(neighbors.get(p));
                           }
                        }
                     }
                     //CODE FOR MOVING TO THE SPECIFIED ROOM ON THE GUI
                     if(!curPlayer.getCurrentRoom().getName().equals("Casting Office")){ 
                        if(!curPlayer.getCurrentRoom().getName().equals("Trailer")){
                        
                           if(curPlayer.getCurrentRoom().getCurrentScene().getIsFlipped() == false)
                           {
                              // Flip card
                              curPlayer.getCurrentRoom().getCurrentScene().setIsFlipped(true);
                              
                              for(int t=0; t < locations.size(); t++){
                                 if(curPlayer.getCurrentRoom().getCurrentScene().getName().equals(locations.get(t).getName())){
                                    curPlayer.getCurrentRoom().getCurrentScene().setImgFile(locations.get(t).getCurrentScene().getImgFile());
                                 }
                              }
                              
                             
                              String file = curPlayer.getCurrentRoom().getCurrentScene().getImgFile();
                              ImageIcon cIcon =  new ImageIcon(file);
                              curPlayer.getCurrentRoom().getCurrentScene().getLabel().setIcon(cIcon);
                           }
                           

                           //CODE FOR MOVING TO THE SPECIFIED ROOM ON THE GUI
                        }
                     }
                     int rx = curPlayer.getCurrentRoom().getX();
                     int ry = curPlayer.getCurrentRoom().getY();
                     int rh = curPlayer.getCurrentRoom().getH();
                     int rw = curPlayer.getCurrentRoom().getW();
                     
                     //update the bounds of the label
                     curPlayer.getLabel().setBounds(rx, ry, rh, rw);
                  
                     
                     
                     JOptionPane.showMessageDialog(frame, "You have successfully moved to " + curPlayer.getCurrentRoom().getName());
                     moved = true;
                     //----------------logic for Upgrading/Casting Office---------------------------------------
                     if(curPlayer.getCurrentRoom().getName().equals("Casting Office")){
                        
                        String[] yesNo = {"yes", "no"};
                        int p = JOptionPane.showOptionDialog(frame, "You just moved to the Casting office would you like to upgrade your rank?", "Casting Office", JOptionPane.DEFAULT_OPTION, 
                           JOptionPane.INFORMATION_MESSAGE, null, yesNo, yesNo[0]);
                        if(p == 0){
                           //see which ranks the player can actually afford to rank up to
                           int pFame = curPlayer.getFame();
                           int pMoney = curPlayer.getMoney();
                           int pRank = curPlayer.getRank();
                        
                           ArrayList<Integer> availRanks = new ArrayList<Integer>();
                           
                           for(int f = 2; f <= 6; f++){
                              if(famePrices[f] <= curPlayer.getFame() && pRank < f){
                                 availRanks.add(f);
                              }
                              if(moneyPrices[f] <= curPlayer.getMoney() && pRank < f){
                                 availRanks.add(f);
                              }
                           }
                           Integer[] finalAvailRanks = new Integer[availRanks.size()];
                           for(int c = 0; c < availRanks.size(); c++){
                              finalAvailRanks[c] = availRanks.get(c);
                           }
                           
                           //if player can't afford any ranks
                           if(finalAvailRanks.length == 0){
                              JOptionPane.showMessageDialog(frame, "Sorry but you don't have enough "
                                 +"fame or money to upgrade to any rank. Here are the pricing details."
                                 +"\n"+"\n"
                                 +"\n|| Rank || Money || Fame || "
                                 +"\n----------------------------------------"
                                 +"\n||    2     ||      $4       ||      5       || "
                                 +"\n||    3     ||      $10     ||      10     || "
                                 +"\n||    4     ||      $18     ||      15     || "
                                 +"\n||    5     ||      $28     ||      20     || "
                                 +"\n||    6     ||      $40     ||      25     || "
                                 +"\n----------------------------------------");
                           
                           }else{
                              int k = JOptionPane.showOptionDialog(frame, 
                                 
                                 
                                 "\n"+"\n"
                                 +"\n|| Rank || Money || Fame || "
                                 +"\n----------------------------------------"
                                 +"\n||    2     ||      $4       ||      5       || "
                                 +"\n||    3     ||      $10     ||      10     || "
                                 +"\n||    4     ||      $18     ||      15     || "
                                 +"\n||    5     ||      $28     ||      20     || "
                                 +"\n||    6     ||      $40     ||      25     || "
                                 +"\n----------------------------------------"
                                 +"\nHere are the ranks you can afford to upgrade to, choose one:"
                                 ,"Casting Office", JOptionPane.DEFAULT_OPTION, 
                                 JOptionPane.INFORMATION_MESSAGE, null, finalAvailRanks, 
                                 finalAvailRanks[0]);
                              
                              String[] fameOrMoney = {"Fame", "Money"};
                              
                              int y = JOptionPane.showOptionDialog(frame, 
                                 
                                 "----------------------------------------------"
                                 +"\n----------RANK UPGRADES AND COSTS-------------"
                                 +"\n----------------------------------------------"
                                 +"\n|| New Rank || Money (cost) || Fame (cost) || "
                                 +"\n----------------------------------------------"
                                 +"\n||    2     ||      $4      ||      5      || "
                                 +"\n||    3     ||      $10     ||      10     || "
                                 +"\n||    4     ||      $18     ||      15     || "
                                 +"\n||    5     ||      $28     ||      20     || "
                                 +"\n||    6     ||      $40     ||      25     || "
                                 +"\n----------------------------------------------"
                                 +"\nWould you like to use Money or fame to get rank " + finalAvailRanks[k] +" ?"
                                 ,"Casting Office", JOptionPane.DEFAULT_OPTION, 
                                 JOptionPane.INFORMATION_MESSAGE, null, fameOrMoney, 
                                 fameOrMoney[0]);
                              
                              //if fame is chosen
                              if(y == 0){
                                 curPlayer.setRank(finalAvailRanks[k]);
                                 String col = "" + curPlayer.getColor();
                                 int rk = curPlayer.getRank();
                                 ImageIcon newFile = new ImageIcon(col+rk+".png");
                                 curPlayer.getLabel().setIcon(newFile);
                                 JOptionPane.showMessageDialog(frame, "You just upgraded to rank " + curPlayer.getRank() + "!");
                              //if money is chosen
                              }else if(y == 1){
                                 curPlayer.setMoney(-(moneyPrices[finalAvailRanks[k]]));
                                 curPlayer.setRank(finalAvailRanks[k]);
                                 String col = "" + curPlayer.getColor();
                                 int rk = curPlayer.getRank();
                                 ImageIcon newFile = new ImageIcon(col+rk+".png");
                                 curPlayer.getLabel().setIcon(newFile);
                                 JOptionPane.showMessageDialog(frame, "You just upgraded to rank " + curPlayer.getRank() + "!");
                              }
                           
                           }
                        }
                        
                     }
                     
                     //---------------------------------------------------------------------------------------------------
                  }
               
               }else if(e.getSource() == bEndTurn){
                  //resets counters
                  moved = false;
                  actReh = false;
                  tookRole = false;
                  upgraded = false;
                  curPlayerNum++; //0-indexed for player number
                  
                  
                  //if game ends
                  if(curPlayerNum == numP && totalDays == 0){
                     curPlayerNum = 0;
                     
                     
                     //------------calculate scores and end game--------------------
                     Moderator mod = new Moderator(allP.size());
                     //Calculate final scores
                     mod.calcScores(allP);
                        
                     //Declare winner
                     Player winner = mod.declareWinner(allP);
                     
                     if(winner == null){
                        JOptionPane.showMessageDialog(frame, "Game Over! All players have the same score, you tied.");
                     }
                     else{  
                        JOptionPane.showMessageDialog(frame, "Game over! Congratulations " + winner.getColor()+ " Player, you won!");
                     }
                     System.exit(0);
                     //--------------------GAME ENDS----------------------------------
                     
                  //if the last player's turn ended ---- CHANGE TO LAST SCENE -> day ends 
                  }else if(sceneCounter == 1){
                    
                     //Shannon: Reset all Scenes and add new ones from Scene deck to the board
                     // Add scene card to each room
                     for(int i=0; i < locations.size()-8; i++){
                        cLabel = new JLabel();
                        cIcon =  new ImageIcon("back.png"); // image for the scene card
                        cLabel.setIcon(cIcon); 
                        int x = locations.get(i).getX();
                        int y = locations.get(i).getY();
                        cLabel.setBounds(x,y,cIcon.getIconWidth()+2,cIcon.getIconHeight());
                        cLabel.setOpaque(true);
                        cardLabels.add(cLabel);
                        locations.get(i).getCurrentScene().setLabel(cLabel);
                        bPane.add(cardLabels.get(i), new Integer(1));
                     }
                          
                          
                      // Add shot markers to proper areas in each room
                     for(int i=0; i < locations.size()-8; i++){
                        if(locations.get(i).getName() != "Trailer"){
                           if(locations.get(i).getName() != "Casting Office"){
                              ArrayList<Shot> roomShots = locations.get(i).getShots();
                              for(int j=0; j < roomShots.size(); j++){
                              
                                 int shotX = roomShots.get(j).getX();
                                 int shotY = roomShots.get(j).getY();
                                 int shotH = roomShots.get(j).getH();
                                 int shotW = roomShots.get(j).getW();
                              
                                 sLabel = new JLabel();
                                 sIcon = new ImageIcon("shot.png");
                                 sLabel.setIcon(sIcon);
                              
                                 sLabel.setBounds(shotX, shotY, shotH, shotW);
                                 sLabel.setOpaque(false);
                                 roomShots.get(j).setLabel(sLabel);
                                 bPane.add(sLabel, new Integer(1));
                              }
                           }
                        }
                     }    
                     
                     
                     
                     
                     
                     
                     
                     
                     
                     ///////////////////////////////////////////////////////////////////////
                     
                     
                     curPlayerNum = 0;
                     totalDays--;
                     JOptionPane.showMessageDialog(frame, "Day has ended, new scenes are now available! " + totalDays + " Day(s) left.");
                  }else if(curPlayerNum == numP){
                     curPlayerNum = 0;
                  }
                  
               
                  curPlayer = allP.get(curPlayerNum); //sets curPlayer to next Player (0-indexed)
                  tempN -= 1; //may not need this
                  JOptionPane.showMessageDialog(frame, "Your turn has ended.");
                  JOptionPane.showMessageDialog(frame, "It's now Player " + 
                     (curPlayerNum + 1) + "'s turn!");
                  
                  
               //-----------------------------------take role--------------------------
               }else if(e.getSource() == bTakeRole){
                  
                  if(actReh == true){
                     JOptionPane.showMessageDialog(frame, "You already acted this turn, wait till next turn to take another role.");
                  }else if(curPlayer.getCurrentRoom().getRoles() == null){
                     JOptionPane.showMessageDialog(frame, "Please move to a Room first.");
                  }else{
                     
                     
                     
                     if(curPlayer.getHasRole() == true){
                        JOptionPane.showMessageDialog(frame, "Your already have a role.");
                        
                     }else if(curPlayer.getHasRole() == false){
                     
                     
                     //MY EDIT: If they are not in trailer or casting office, then give option to take a role
                        if((curPlayer.getCurrentRoom().getName()) != "Trailer"){ 
                           if((curPlayer.getCurrentRoom().getName()) != "Casting Office"){
                              for(int r=0; r < sceneDeck.size(); r++){
                                 curPlayer.getCurrentRoom().setScene(sceneDeck.get(r));
                              }
                           
                           //Set a scene in the room of the player 
                              curPlayer.setCurrentScene(curPlayer.getCurrentRoom().getCurrentScene());
                           
                           //Get the possible roles that the players can choose from
                              ArrayList<Role> possibleRoles = new ArrayList<Role>();
                              possibleRoles = curPlayer.getCurrentRoom().getRoles();
                           //the roles the player can choose from
                              ArrayList<String> eligibleRoleNames = new ArrayList<String>();
                              ArrayList<Role> cardRoles = curPlayer.getCurrentRoom().getCurrentScene().getRoles();
                           
                           
                              if(possibleRoles != null){
                                 for(int r = 0; r < cardRoles.size(); r++){
                                    possibleRoles.add(cardRoles.get(r));
                                 }
                                 if(possibleRoles.size() > 0){
                                    for(int l = 0; l < possibleRoles.size(); l++){
                                       if(curPlayer.getRank() >= possibleRoles.get(l).getReqRank() && possibleRoles.get(l).checkAvailability() == true){
                                          eligibleRoleNames.add(possibleRoles.get(l).getRoleName());
                                       }
                                    }
                                 }else if(possibleRoles.size() == 0){
                                    JOptionPane.showMessageDialog(frame, "There are no available roles.");
                                 }
                              }
                           //dumps ArrayList into an array
                              String[] finalAvailRoles = new String[eligibleRoleNames.size()];
                              for(int h = 0; h < eligibleRoleNames.size(); h++){
                                 finalAvailRoles[h] = eligibleRoleNames.get(h);
                              }
                           //if there are no roles left
                              if(finalAvailRoles.length == 0){
                                 JOptionPane.showMessageDialog(frame, "There are no available roles.");
                              }else{
                                 int numChoice = JOptionPane.showOptionDialog(frame, "Please choose an available role.", "Choose Role", JOptionPane.DEFAULT_OPTION, 
                                    JOptionPane.INFORMATION_MESSAGE, null, finalAvailRoles, finalAvailRoles[0]);
                              
                                 String choice = finalAvailRoles[numChoice];
                              
                                 for(int l = 0; l < possibleRoles.size(); l++){
                                    if(choice.equals(possibleRoles.get(l).getRoleName())){
                                       curPlayer.setCurrentRole(possibleRoles.get(l));
                                       possibleRoles.get(l).setAvailability(false);
                                    }
                                 }
                              
                                 int rx2 = curPlayer.getCurrentRole().getX();
                                 int ry2 = curPlayer.getCurrentRole().getY();
                                 int rh2 = curPlayer.getCurrentRole().getH();
                                 int rw2 = curPlayer.getCurrentRole().getW();
                              
                              //update the bounds of the players dice when they take a role
                                 curPlayer.getLabel().setBounds(rx2, ry2, rh2, rw2);
                              
                                 curPlayer.getCurrentRole().setReqRoll(curPlayer.getCurrentScene().getBudget());
                              
                                 curPlayer.setHasRole(true);
                                 JOptionPane.showMessageDialog(frame, "Okay you have taken " + choice + " as a Role!");
                                 tookRole = true;
                              }
                           }
                        }  
                     
                     
                     //---------------------------------------------------------------
                     
                     
                     }                
                  
                     
                     
                  
                  
                  } 
               }
            }        
         }
      }
      public void mousePressed(MouseEvent e) {
      }
      public void mouseReleased(MouseEvent e) {
      }
      public void mouseEntered(MouseEvent e) {
      }
      public void mouseExited(MouseEvent e) {
      }
   }





   public static void startBoardListener() {
      Deadwood board = new Deadwood();
      board.setVisible(true);
   
   
   }
  
  
  
}
