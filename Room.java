import java.util.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays; 

 // Room class:
public class Room{

      // Attributes:
      private ArrayList<Role> roles;
      private ArrayList<Shot> shots;
      private Scene currentScene;
      private String roomName ="";
      private int numShots = 0;
      private ArrayList<Room> adjacentRooms;
      private ArrayList<Upgrade> upgrades;
      private int xCoord = 0;
      private int yCoord = 0;
      private int hCoord = 0;
      private int wCoord = 0;

      
      // Constructors:
      public Room(String name, ArrayList<Room> adjRooms, ArrayList<Role> roles){
         this.roomName = name;
         this.adjacentRooms = adjRooms;
         this.roles = roles;
      }
      
      public Room(String name, ArrayList<Room> adjRooms){ //when scene card isn't flipped(no roles)
         this.roomName = name;
         this.adjacentRooms = adjRooms;
      }
      
      public Room(){
      
      }
      
      // Methods:
      
      public ArrayList<Role> getRoles(){
         return this.roles;
      }
      
      public void setRoles(ArrayList<Role> roles){
         this.roles = roles;
      }
         
      public ArrayList<Upgrade> getUpgrades(){
         return this.upgrades;
      }
      
      public void setUpgrades(ArrayList<Upgrade> u){
         this.upgrades = u;
      }
      
      public ArrayList<Shot> getShots(){
         return this.shots;
      }
      
      public void setShots(ArrayList<Shot> s){
         this.shots = s;
      }
      
      
      
      
      public ArrayList<Room> getAdjRooms(){
         return this.adjacentRooms;
      }
   
      public void setAdjRooms(ArrayList<Room> r){
         this.adjacentRooms = r;
      }

   
      public void setScene(Scene sceneName){
         this.currentScene = sceneName;
      }
      
      public Scene getCurrentScene(){
         return this.currentScene;
      }
   
      public void setName(String s){
         this.roomName = s;
      }
   
      public String getName(){
         return this.roomName;
      }
   
      public int getNumShots(){
         return this.numShots;
      }
      
      public void setNumShots(int newNum) {
         this.numShots += newNum;
      }
         
      public int getX(){
         return this.xCoord;
      }
      
      public void setX(int x){
         this.xCoord = x;
      }
      
      public int getY(){
         return this.yCoord;
      }
      
      public void setY(int y){
         this.yCoord = y;
      }
      
      public int getH(){
         return this.hCoord;
      }
      
      public void setH(int h){
         this.hCoord = h;
      }
      
      public int getW(){
         return this.wCoord;
      }
      
      public void setW(int w){
         this.wCoord = w;
      }

      
      
   } // End Room Class
