import java.util.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays; 

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;

  // Scene class:
 public class Scene extends JFrame{
   
            // Attributes:
   private String title = "";
   private String description = "";
   private int sceneNum = 0;
   private int budget = 0;
   private int numRoles = 0; //number of roles on the card
   private ArrayList<Role> roles;
   private boolean isFlipped = false;
   private Room curRoom;
   private String imgFile = "";
   private JLabel label;


   // Constructors:
   public Scene(String title, String description, int budget, ArrayList<Role> roles) {
      this.title = title;
      this.description = description;
      this.budget = budget;
      this.sceneNum = sceneNum;
      this.roles = roles;
   }

   public Scene(){
   
   }

   // Methods:
    public void setIsFlipped(boolean tf){
      this.isFlipped = tf;
   }
   
   public boolean getIsFlipped(){
      return this.isFlipped;
   }

   public void advanceScene(){
      this.curRoom.setNumShots(-1);
   }
   
   public void setImgFile(String f){
      this.imgFile = f;
   }
   
   public String getImgFile(){
      return this.imgFile;
   }
   
   public void setCurRoom(Room r){
      this.curRoom = r;
   }
   
   public Room getCurRoom(){
      return this.curRoom;
   }

   public void setTitle(String s){
      this.title = s;
   }

   public String getSceneTitle(){
      return this.title;
   }
   
   public String getDescription(){
      return this.description;
   }
   
   public void setDescription(String s){
      this.description = s;
   }
      
   public int getSceneNum(){
      return this.sceneNum;
   }
   
   public void setSceneNum(int n){
      this.sceneNum = n;
   }
   
   public int getBudget(){
      return this.budget;
   }
   
   public void setBudget(int b){
      this.budget = b;
   }
   
   public int getNumRoles(){
      return this.numRoles;
   }
   
   public void setNumRoles(int num){
      this.numRoles = num;
   }

   public ArrayList<Role> getRoles(){
      return this.roles;
   }
   
   public void setRoles(ArrayList<Role> roles){
      this.roles = roles;
   }
   
   public void setLabel(JLabel l){
      this.label = l;
   }
   
   public JLabel getLabel(){
      return this.label;
   }
    // End Scene Class
}