import java.util.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays; 

 // Role class:
   public class Role{

      // Attributes:
      private String roleName = "";
      private int reqRank = 0; 
      private int priorityNum = 0;  
      private String line = "";
      String type = "";
      private String assignedPlayer = "";
      private boolean available = true;
      private int xCoord = 0;
      private int yCoord = 0;
      private int hCoord = 0;
      private int wCoord = 0;
      int reqRoll = 0;
      int fameReward = 0;
      int moneyReward = 0;
   
      // Constructors:
      public Role(String roleName, int reqRank, String line, String type){
         this.roleName = roleName;
         this.reqRank = reqRank;
         this.line = line;
         this.type = type;
         if(this.type == "on card role"){
            this.fameReward = 2;
            this.moneyReward = 0;
         }else if(this.type == "off card role"){
            this.fameReward = 1;
            this.moneyReward = 1;
         }
      }
   
      public Role(){
      
      }
   
      // Methods:
      
      public void setReqRoll(int req){
         this.reqRoll = req;
      }
      
      public void setAvailability(boolean avail){
         this.available = avail;
      
      }
      
      public int getPriorityNum(){
         return this.priorityNum;
      }
      
      public int getFameReward(){
         return this.fameReward;
      }
      
      public int getMoneyReward(){
         return this.moneyReward;
      }
      
      
      public int getReqRoll(){
         return this.reqRoll;
      }
      
      public boolean checkAvailability(){
         return this.available;
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
         
      public String getRoleName(){
         return this.roleName;
      }
      
      public void setRoleName(String s){
         this.roleName = s;
      }
   
      public int getReqRank(){
         return this.reqRank;
      }
   
      public void setReqRank(int r){
         this.reqRank = r;
      }
   
      public String getLine(){
         return this.line;
      }
   
      public void setLine(String l){
         this.line = l;
      }
   
      public String getType(){
         return this.type;
      }
      
      public void setType(String t){
         this.type = t;
      }
      
   } // End Role Class
 