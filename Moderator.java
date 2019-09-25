import java.util.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

   // Moderator class:
   public class Moderator{

      // Attributes:
      private int numPlayers;
      private Player firstPlayer;
      private int dayNumber;
      private int maxNumDays;
      private Player winner;
      private Player[] allPlayers;
      private ArrayList<Scene> allScenes;
   
      // Constructors:
      public Moderator(int numPlayers){
      
      }
   
      // Methods:
      
      private Scene useScene(){ 
         Random rand = new Random();
         int num = rand.nextInt(allScenes.size()) + 1;
         Scene newScene = allScenes.get(num);
         newScene.setIsFlipped(true);
         allScenes.remove(num);
         return newScene;
      }
      
      public void setFirstPlayer(){
         Random rand = new Random();
         int n = rand.nextInt(this.numPlayers) + 1;
         this.firstPlayer = allPlayers[n];
      }
   
      public Player getFirstPlayer(){
         return this.firstPlayer;
      }
   
      public void setDayNumber(int day){
         this.dayNumber = day;
      }
   
      public int getDayNumber(){
         return this.dayNumber;
      }
      
      public void setMaxNumDays(int maxDays){
         this.maxNumDays = maxDays;
      }
            
      private void payRewards(int amount, Player p){
         p.setMoney(amount);
      }
      
      public void calcScores(ArrayList<Player> allPlayers){
         int score = 0;
         int money = 0;
         int fame = 0;
         int rank = 0;
         for(int i=0; i < allPlayers.size(); i++){
            money = allPlayers.get(i).getMoney();
            fame = allPlayers.get(i).getFame();
            rank = allPlayers.get(i).getRank();
            score = money + fame + 5*rank;
            allPlayers.get(i).setScore(score);
         }
      }
      
      public Player declareWinner(ArrayList<Player> allPlayers){
     
         for(int i=0; i < allPlayers.size(); i++){
            for(int j=i+1; j < allPlayers.size() - 1; j++){
               if(allPlayers.get(i).getScore() > allPlayers.get(j).getScore()){
                  this.winner = allPlayers.get(i);
               }
               else if(allPlayers.get(i).getScore() < allPlayers.get(j).getScore()){
                  this.winner = allPlayers.get(j);
               }
            }
         }
         return this.winner;
      }

      public int getNumPlayers(){
         return this.numPlayers;
      }
   } // End Moderator Class
   
