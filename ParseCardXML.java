import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;

// This class builds a document from the cards.xml file
// Then creates a deck of scene cards with the cards.xml information

public class ParseCardXML{

      // Building a document from the XML file
      //returns a Document object after loading the cards.xml file
      
   public Document getDocFromFile(String filename)
      throws ParserConfigurationException{
      {
      
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
      } // exception handling
      
   }
      
   public ArrayList<Scene> createSceneDeck(Document d){
         
      ArrayList<Scene> sceneDeck = new ArrayList<Scene>(); // initializes the ArrayList to hold our scene cards
      
      Element root = d.getDocumentElement();
         
      NodeList scenes = root.getElementsByTagName("card");
         
      for (int i=0; i<scenes.getLength(); i++){
                     
         // read data from the scene nodes
         Node scene = scenes.item(i);
         
         Scene currScene = new Scene(); // creates a new scene object
            
         String sceneName = scene.getAttributes().getNamedItem("name").getNodeValue();
         currScene.setTitle(sceneName); // set the scene title from xml information
         
         String imgFile = scene.getAttributes().getNamedItem("img").getNodeValue();
         currScene.setImgFile(imgFile); // set the name of the file of the image for scene card
            
         String budgetValue = scene.getAttributes().getNamedItem("budget").getNodeValue();
         int budget = Integer.parseInt(budgetValue);
         currScene.setBudget(budget); // set the scene budget from the xml information
            
         // reads data for scenes
         NodeList children = scene.getChildNodes();
            
         for (int j=0; j< children.getLength(); j++){
               
            Node sub = children.item(j);
               
            if ("scene".equals(sub.getNodeName())){
               String number = sub.getAttributes().getNamedItem("number").getNodeValue();
               int sceneNumber = Integer.parseInt(number);
               currScene.setSceneNum(sceneNumber); // set scene number from the xml information
                
               String description = sub.getTextContent();
               currScene.setDescription(description); // set scene description from the xml information
            }
               
            else if("part".equals(sub.getNodeName())){
                  
               Role onCard = new Role(); // create a new role object
               onCard.setType("On-Card"); // set role type to on-card
                  
               ArrayList<Role> onCardRoles = new ArrayList<Role>(); // create list of roles for the given scene
                  
               String roleName = sub.getAttributes().getNamedItem("name").getNodeValue();
               onCard.setRoleName(roleName); // set role name from the xml file
               
               String level = sub.getAttributes().getNamedItem("level").getNodeValue();
               int reqRank = Integer.parseInt(level); 
               onCard.setReqRank(reqRank); // set required rank from the xml file
               
               // reads data for the roles
               NodeList roleChildren = sub.getChildNodes();
                  
               for (int z=0; z<roleChildren.getLength(); z++) {
                         
                  Node roleChild = roleChildren.item(z);
                         
                  if("area".equals(roleChild.getNodeName())){
                         
                     // get role coordinates from xml file and set them      
                     String xVal = roleChild.getAttributes().getNamedItem("x").getNodeValue();
                     int x = Integer.parseInt(xVal);
                     onCard.setX(x);
                           
                     String yVal = roleChild.getAttributes().getNamedItem("y").getNodeValue();
                     int y = Integer.parseInt(yVal);
                     onCard.setY(y);
                           
                     String hVal = roleChild.getAttributes().getNamedItem("h").getNodeValue();                           
                     int h = Integer.parseInt(hVal);
                     onCard.setH(h);
                           
                     String wVal = roleChild.getAttributes().getNamedItem("w").getNodeValue();                           
                     int w = Integer.parseInt(wVal);
                     onCard.setW(w);
                  }
                         
                  else if("line".equals(roleChild.getNodeName())){
                         
                     String line = roleChild.getTextContent();
                     onCard.setLine(line); // set line of role from the xml file
                  }
               }
                      
               onCardRoles.add(onCard); // add newly created role to list of all roles
                   
               currScene.setRoles(onCardRoles); // set roles as an attribute for the scene
            }
         }
         sceneDeck.add(currScene); // add scene to list of all scenes
      }
      return sceneDeck;
   }
}