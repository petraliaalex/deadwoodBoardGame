import java.util.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays; 

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;

   // Player class:
public class Player extends JFrame{

      // Attributes:
      private char diceColor;
      private int rank = 1;
      private int money;
      private int fame;
      private int score = 0;
      private Room currentRoom;
      boolean hasRole = false;
      Role currentRole;
      private Scene curScene;
      private int rehearseTokens = 0;
      JLabel label;
   
   
      // Constructors:
      public Player(char diceColor, int rank, int money, int fame) {
         this.diceColor = diceColor;
         this.rank = rank;
         this.money = money;
         this.fame = fame;
      }
      
      public Player(){
      
      }
   
      // Methods:
      public boolean act(){
         if(this.hasRole == true){
            System.out.println(this.diceColor + ": " + currentRole.getLine());
            int dieTotal = rollDice();
            int budg = this.curScene.getBudget();
            if(dieTotal >= budg){
               return true;
            
            }else{
               return false;
            }
         }else{
            System.out.println("You have no role.");
            return false;
         }
         
      }
   
      public boolean hasRoom(){
         if(this.currentRoom == null){
            return false;
         }else{
            return true;
         }
      }
      
      public void setLabel(JLabel l){
         this.label = l;
      }
      
      public JLabel getLabel(){
         return this.label;
      }
   
      public char getColor(){
         return this.diceColor;
      
      }
      
      public void setColor(char color){
         this.diceColor = color;
      }
   
      public void rehearse(){
         this.rehearseTokens += 1;
      }
   
      public void move(Room nextRoom){
         this.currentRoom = nextRoom;
      }
   
      public int rollDice(){
         Random rand = new Random();
         int num = rand.nextInt(6) + 1;
         return num;
      }
   
      public int getRank(){
         return this.rank;
      }
   
      public void setRank(int newRank){
         this.rank = newRank;
      }
   
      public int getMoney(){
         return this.money;
      }
   
      public void setMoney(int newMoney){
         this.money += newMoney;
      }
   
      public int getFame(){
         return this.fame;
      }
   
      public void setFame(int newFame){
         this.fame += newFame;
      }
   
      public boolean getHasRole(){
         return this.hasRole;
      }
   
      public Role getCurrentRole(){
         return this.currentRole;
      }
   
      public void setCurrentRole(Role r){
         this.currentRole = r;
      }
   
      public void setRehearseTokens(int tokens){
         this.rehearseTokens += tokens;
      }
      
      public int getRehearseTokens(){
         return this.rehearseTokens;
      }
   
      public Room getCurrentRoom(){
         return this.currentRoom;
      }
      
      public int getScore(){
         return this.score;
      }
   
      public void setScore(int finalScore){
         this.score += finalScore;
      }
      
      public void setHasRole(boolean tf){
         this.hasRole = tf;
      }
      
      public void setCurrentScene(Scene currentScene){
         this.curScene = currentScene;
      }
      
      public Scene getCurrentScene(){
         return curScene;
      }
      
   } // End Player Class
