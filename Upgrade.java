public class Upgrade {

   // Attributes:
   private int xCoord = 0;
   private int yCoord = 0;
   private int hCoord = 0;
   private int wCoord = 0;
   private String currency = "";
   private int level = 0;
   private int amount = 0;
   
   
   //Constructor:
   public Upgrade(String currency, int level, int amount, int x, int y, int h, int w) {
      this.currency = currency;
      this.level = level;
      this.amount = amount;
      this.xCoord = x;
      this.yCoord = y;
   }
   
   public Upgrade(){
      // No-argument constructor
   }
   
   //Methods:
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

   public String getCurrency(){
      return this.currency;
   }
   
   public void setCurrency(String c){
      this.currency = c;
   }

   public int getLevel(){
      return this.level;
   }
   
   public void setLevel(int l){
      this.level = l;
   }

   public int getAmount(){
      return this.amount;
   }
   
   public void setAmount(int a){
      this.amount = a;
   }
}