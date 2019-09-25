import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;

// This class builds a document from the board.xml file
// Then creates Room objects containing the xml information

public class ParseBoardXML{

      // Building a document from the XML file
      //returns a Document object after loading the cards.xml file
      
   public Document getDocFromFile(String filename)
      throws ParserConfigurationException{
      
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = null;
         
      try{
         doc = db.parse(filename);
      } catch (Exception ex){
         System.out.println("XML parse failure");
         ex.printStackTrace();
      }
      return doc;
      // exception handling
   }
   
   public ArrayList<Room> createRooms(Document d){
   
      ArrayList<Room> rooms = new ArrayList<Room>();
      
      Element root = d.getDocumentElement();
         
      NodeList sets = root.getElementsByTagName("set");
         
      for (int i=0; i<sets.getLength(); i++){
      
         Room newRoom = new Room(); // creates a new room object
         
         // reads data from the nodes
         Node set = sets.item(i);
         
         String setName = set.getAttributes().getNamedItem("name").getNodeValue();
         newRoom.setName(setName); // set the room name to the info from xml
      
         NodeList children = set.getChildNodes();
         
         for (int j=0; j<children.getLength(); j++){
         
            Node sub = children.item(j);
            
            if("neighbors".equals(sub.getNodeName())){
               
               NodeList setNeighbors = sub.getChildNodes();
                              
               ArrayList<Room> roomNeighbors = new ArrayList<Room>(); // create an ArrayList that holds the neighbors of the current room
               
               for(int k=0; k<setNeighbors.getLength(); k++){
                              
                  Node neighbor = setNeighbors.item(k);
               
                  if("neighbor".equals(neighbor.getNodeName())){
                  
                     Room adjRoom = new Room(); // create a new room object that is adjacent to current room
                  
                     String adjacentSet = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                     adjRoom.setName(adjacentSet);
                     
                     roomNeighbors.add(adjRoom); // adds the adjacent rooms to the list of neighbors
                  }
                  newRoom.setAdjRooms(roomNeighbors); // sets the arrayList as the neighbors of the newRoom
               }
            }
               
            else if("takes".equals(sub.getNodeName())){
               
               NodeList takes = sub.getChildNodes();
               
               for (int k=0; k<takes.getLength(); k++){
               
                  ArrayList<Shot> roomShots = new ArrayList<Shot>();
               
                  Node take = takes.item(k);
               
                  if("take".equals(take.getNodeName())){
                  
                     Shot newShot = new Shot();
                     
                     String number = take.getAttributes().getNamedItem("number").getNodeValue();
                     int takeNumber = Integer.parseInt(number);
                     newShot.setNum(takeNumber); // set the shot number
                  
                     NodeList areaValues = take.getChildNodes();
                  
                     for (int s=0; s<areaValues.getLength(); s++){
                     
                        Node area = areaValues.item(s);
                     
                        if("area".equals(area.getNodeName())){
                           String xVal = area.getAttributes().getNamedItem("x").getNodeValue();
                           int x = Integer.parseInt(xVal);
                           newShot.setX(x); // set the shot x-coordinate
                        
                           String yVal = area.getAttributes().getNamedItem("y").getNodeValue();
                           int y = Integer.parseInt(yVal);
                           newShot.setY(y); // set the shot y-coordinate
                        
                           String hVal = area.getAttributes().getNamedItem("h").getNodeValue();
                           int h = Integer.parseInt(hVal);
                           newShot.setH(h); // set the shot h-coordinate
                        
                           String wVal = area.getAttributes().getNamedItem("w").getNodeValue();
                           int w = Integer.parseInt(wVal);
                           newShot.setW(w); // set the shot w-coordinate
                        }
                     }
                     roomShots.add(newShot); // add the new shot to the list of shots for the room
                  }
                  newRoom.setShots(roomShots); // set the room shots as the shots for our new room object
               }
            }
            
            else if("area".equals(sub.getNodeName())){
                         
               String xVal = sub.getAttributes().getNamedItem("x").getNodeValue();
               int x = Integer.parseInt(xVal);
               newRoom.setX(x);
                           
               String yVal = sub.getAttributes().getNamedItem("y").getNodeValue();
               int y = Integer.parseInt(yVal);
               newRoom.setY(y);
                           
               String hVal = sub.getAttributes().getNamedItem("h").getNodeValue();                           
               int h = Integer.parseInt(hVal);
               newRoom.setH(h);
                           
               String wVal = sub.getAttributes().getNamedItem("w").getNodeValue();                           
               int w = Integer.parseInt(wVal);
               newRoom.setW(w);
            }
            
            else if("parts".equals(sub.getNodeName())){
              
               NodeList roles = sub.getChildNodes();
               
               ArrayList<Role> offCardRoles = new ArrayList<Role>();
               
               for(int k=0; k<roles.getLength(); k++){
                   
                  Node role = roles.item(k);
                   
                  if("part".equals(role.getNodeName())){
                     
                     Role offCard = new Role();
                     offCard.setType("Off-Card");
                  
                     String roleName = role.getAttributes().getNamedItem("name").getNodeValue();
                     offCard.setRoleName(roleName);
                     
                     String level = role.getAttributes().getNamedItem("level").getNodeValue();
                     int reqRank = Integer.parseInt(level);
                     offCard.setReqRank(reqRank);
                     
                     NodeList roleChildren = role.getChildNodes();
                     
                     for (int z=0; z<roleChildren.getLength(); z++){
                        
                        Node info = roleChildren.item(z);
                        
                        if("area".equals(info.getNodeName())){
                        
                           String xVal = info.getAttributes().getNamedItem("x").getNodeValue();
                           int x = Integer.parseInt(xVal);
                           offCard.setX(x);
                           
                           String yVal = info.getAttributes().getNamedItem("y").getNodeValue();
                           int y = Integer.parseInt(yVal);
                           offCard.setY(y);
                           
                           String hVal = info.getAttributes().getNamedItem("h").getNodeValue();                           
                           int h = Integer.parseInt(hVal);
                           offCard.setH(h);
                           
                           String wVal = info.getAttributes().getNamedItem("w").getNodeValue();                           
                           int w = Integer.parseInt(wVal);
                           offCard.setW(w);
                        }
                        
                        else if("line".equals(info.getNodeName())){
                            
                           String line = info.getTextContent();
                           offCard.setLine(line);
                        }
                     }
                     offCardRoles.add(offCard);
                  }
               }
               newRoom.setRoles(offCardRoles);
            }
         }
         rooms.add(newRoom);
      }
      
      NodeList trailers = root.getElementsByTagName("trailer");
      
      Room trailerRoom = new Room();
      trailerRoom.setName("Trailer");
      
      for (int i=0; i<trailers.getLength(); i++){
         
         // reads data from the nodes
         Node trailer = trailers.item(i);
         NodeList children2 = trailer.getChildNodes();
         
         for (int j=0; j<children2.getLength(); j++){
            
            Node sub = children2.item(j);
            
            if("neighbors".equals(sub.getNodeName())){
            
               NodeList trailerNeighbors = sub.getChildNodes();
               
               ArrayList<Room> tNeighs = new ArrayList<Room>();
               
               for (int k=0; k<trailerNeighbors.getLength(); k++){
               
                  Node neighbor = trailerNeighbors.item(k);
               
                  if("neighbor".equals(neighbor.getNodeName())){
                  
                     Room trailNeigh = new Room();
                  
                     String adjSet = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                     trailNeigh.setName(adjSet);
                     
                     tNeighs.add(trailNeigh);
                  }
               
               }
               trailerRoom.setAdjRooms(tNeighs);
            }
            
            else if("area".equals(sub.getNodeName())){
                         
               String xVal = sub.getAttributes().getNamedItem("x").getNodeValue();
               int x = Integer.parseInt(xVal);
               trailerRoom.setX(x);
                           
               String yVal = sub.getAttributes().getNamedItem("y").getNodeValue();
               int y = Integer.parseInt(yVal);
               trailerRoom.setY(y);
                           
               String hVal = sub.getAttributes().getNamedItem("h").getNodeValue();                           
               int h = Integer.parseInt(hVal);
               trailerRoom.setH(h);
                           
               String wVal = sub.getAttributes().getNamedItem("w").getNodeValue();                           
               int w = Integer.parseInt(wVal);
               trailerRoom.setW(w);
            }
         }
         rooms.add(trailerRoom);                    
      }
      
      NodeList offices = root.getElementsByTagName("office");
      
      Room castOffice = new Room();
      castOffice.setName("Casting Office");
      
      for(int i=0; i<offices.getLength(); i++){
         
         //reads data from the nodes
         Node office = offices.item(i);
         NodeList children3 = office.getChildNodes();
         
         for (int j=0; j<children3.getLength(); j++){
            
            Node sub = children3.item(j);
            
            if("neighbors".equals(sub.getNodeName())){
            
               NodeList officeNeighbors = sub.getChildNodes();
               
               ArrayList<Room> castOfficeNeighs = new ArrayList<Room>();
            
               for (int k=0; k<officeNeighbors.getLength(); k++){
                  
                  Node neighbor = officeNeighbors.item(k);
                  
                  if("neighbor".equals(neighbor.getNodeName())){
                  
                     Room officeNeigh = new Room();
                     
                     String adjSet = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                     officeNeigh.setName(adjSet);
                     
                     castOfficeNeighs.add(officeNeigh);
                  }
               }
               castOffice.setAdjRooms(castOfficeNeighs);
            }
            
            else if("area".equals(sub.getNodeName())){
               
               String xVal = sub.getAttributes().getNamedItem("x").getNodeValue();
               int x = Integer.parseInt(xVal);
               castOffice.setX(x);
                           
               String yVal = sub.getAttributes().getNamedItem("y").getNodeValue();
               int y = Integer.parseInt(yVal);
               castOffice.setY(y);
                           
               String hVal = sub.getAttributes().getNamedItem("h").getNodeValue();                           
               int h = Integer.parseInt(hVal);
               castOffice.setH(h);
                           
               String wVal = sub.getAttributes().getNamedItem("w").getNodeValue();                           
               int w = Integer.parseInt(wVal);
               castOffice.setW(w);
            }
            
            else if("upgrades".equals(sub.getNodeName())){
               
               NodeList upgrades = sub.getChildNodes();
               
               ArrayList<Upgrade> upgradeInfo = new ArrayList<Upgrade>();
               
               for (int k=0; k<upgrades.getLength(); k++){
                  
                  Node upgrade = upgrades.item(k);
               
                  if("upgrade".equals(upgrade.getNodeName())){
                  
                     Upgrade newUpgrade = new Upgrade();
                  
                     String level = upgrade.getAttributes().getNamedItem("level").getNodeValue();
                     int desiredRank = Integer.parseInt(level);
                     newUpgrade.setLevel(desiredRank);
                     
                     String currency = upgrade.getAttributes().getNamedItem("currency").getNodeValue();
                     newUpgrade.setCurrency(currency);
                     
                     String amt = upgrade.getAttributes().getNamedItem("amt").getNodeValue();
                     int amount = Integer.parseInt(amt);
                     newUpgrade.setAmount(amount);
                     
                     NodeList areas = upgrade.getChildNodes();
                     
                     for (int z=0; z<areas.getLength(); z++){
                        
                        Node area = areas.item(z);
                        
                        if("area".equals(area.getNodeName())){
                           
                           String xVal = area.getAttributes().getNamedItem("x").getNodeValue();
                           int x = Integer.parseInt(xVal);
                           newUpgrade.setX(x);
                           
                           String yVal = area.getAttributes().getNamedItem("y").getNodeValue();
                           int y = Integer.parseInt(yVal);
                           newUpgrade.setY(y);
                           
                           String hVal = area.getAttributes().getNamedItem("h").getNodeValue();                           
                           int h = Integer.parseInt(hVal);
                           newUpgrade.setH(h);
                           
                           String wVal = area.getAttributes().getNamedItem("w").getNodeValue();                           
                           int w = Integer.parseInt(wVal);
                           newUpgrade.setW(w);
                        }
                     }
                     upgradeInfo.add(newUpgrade);
                  }
               }
               castOffice.setUpgrades(upgradeInfo);
            }
            rooms.add(castOffice);
         }
      }
      return rooms;
   }
}