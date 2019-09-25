import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Shot extends JFrame{

   // Attributes:
   private int number = 0;
   private int xCoord = 0;
   private int yCoord = 0;
   private int hCoord = 0;
   private int wCoord = 0;
   JLabel label;
   
   // Constructors:
   public Shot(){
   
   }
   
   public Shot(int num, int x, int y, int h, int w){
      this.number = num;
      this.xCoord = x;
      this.yCoord = y;
      this.hCoord = h;
      this.wCoord = w;
   }
   
   public int getNum(){
      return this.number;
   }
   
   public void setNum(int n){
      this.number = n;
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
   
   public void setLabel(JLabel l){
      this.label = l;
   }
   
   public JLabel getLabel(){
      return this.label;
   }
}