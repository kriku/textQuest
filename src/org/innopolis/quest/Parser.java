package org.innopolis.quest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * parser for quest xml
 * Created by krikun on 8/2/2015.
 */
public class Parser {
    private String file;

    public Parser(String file) {
        this.file = file;
    }

    public Game getStory() {
        Game game = new Game(true, "");

        try {
            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            if (doc.getDocumentElement().hasAttribute("name")) {
                game.setName(doc.getDocumentElement().getAttribute("name"));
            }

            Node locationsNode = doc.getElementsByTagName("locations").item(0);
            Location[] locations = getLocations(locationsNode);
            game.setLocations(locations);

//            Node objectsNode = doc.getElementsByTagName("objects").item(0);
//            Object[] objects = getObjects(objectsNode);
//            game.setObjects(objects);

            game.setObjects(getAllObjects(doc));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return game;
    }

    private Exit[] getExits(Node exitsNode) {
        Element eExits = (Element) exitsNode;
        NodeList exitNodes = eExits.getElementsByTagName("exit");
        int count = exitNodes.getLength();
        Exit[] exits = new Exit[count];
        for (int i = 0; i < count; i++) {
            Node exitNode = exitNodes.item(i);
            if (exitNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eExit = (Element) exitNode;
                int location = Integer.valueOf(eExit.getAttribute("location"));
                String description = eExit.getTextContent();
                int required = -1;
                if (eExit.hasAttribute("required")) {
                    required = Integer.valueOf(eExit.getAttribute("required"));
                }
                exits[i] = new Exit(location, required, description);
            }
        }
        return exits;
    }

    private Location[] getLocations(Node locationsNode) {
        Element eLocations = (Element) locationsNode;
        NodeList locationNodes = eLocations.getElementsByTagName("location");
        int locationCount = locationNodes.getLength();
        Location[] locations = new Location[locationCount];

        for (int i = 0; i < locationCount; i++) {
            Node locationNode = locationNodes.item(i);

            if (locationNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eLocation = (Element) locationNode;
                int id = Integer.valueOf(eLocation.getAttribute("id"));
                String description = eLocation.getElementsByTagName("description").item(0).getTextContent();

                Node exitsNode = eLocation.getElementsByTagName("exits").item(0);
                Exit[] exits = getExits(exitsNode);

                locations[i] = new Location(id, description, exits);
            }
        }
        return locations;
    }

    private Object[] getObjects(Node objectsNode) {
        Element eObjects = (Element) objectsNode;
        NodeList objectNodes = eObjects.getElementsByTagName("object");
        int objectNodesLength = objectNodes.getLength();
        Object[] objects = new Object[objectNodesLength];

        for (int i = 0; i < objectNodesLength; i++) {
            Node locationNode = objectNodes.item(i);

            if (locationNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eObject = (Element) locationNode;

                int id = Integer.valueOf(eObject.getAttribute("id"));
                String description = eObject.getTextContent();

                objects[i] = new Object(id, description);
            }
        }
        return objects;
    }

    private Object[] getAllObjects(Document document) {
        NodeList objectNodes = document.getElementsByTagName("object");
        int objectNodesLength = objectNodes.getLength();
        Object[] objects = new Object[objectNodesLength];

        for (int i = 0; i < objectNodesLength; i++) {
            Node locationNode = objectNodes.item(i);

            if (locationNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eObject = (Element) locationNode;

                int id = Integer.valueOf(eObject.getAttribute("id"));
                String description = eObject.getTextContent();

                objects[i] = new Object(id, description);
            }
        }
        return objects;
    }
}
