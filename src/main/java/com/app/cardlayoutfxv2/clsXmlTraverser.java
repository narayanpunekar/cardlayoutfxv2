package com.app.cardlayoutfxv2;

import static java.lang.System.out;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author narayan.punekar@yahoo.com
 */
public class clsXmlTraverser {
    
    List<Node> lstNodes = new ArrayList<Node>();
    Map mapNodes = new HashMap();

    public Document fnLoadXml() {
        DocumentBuilderFactory dbFactory = null;
        DocumentBuilder documentBuilder = null;
        Document xmlDocument = null;
        File xmlFile = null;
        
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = dbFactory.newDocumentBuilder();
            xmlFile = new File("ProductBacklog.xml");
            xmlDocument = documentBuilder.parse(xmlFile);
        } catch (ParserConfigurationException pcex) {
            pcex.printStackTrace();
        } catch (SAXException saxex) {
            saxex.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return xmlDocument;
    }

    public void fnTraverseNode(Node node) {
        NodeList nodelistChildren = null;
        Node nodeChild = null;

        out.print(node.getNodeName() + " gNN ");
        lstNodes.add(node);
        mapNodes.put(node, node);
        if(node.hasChildNodes()) {
            nodelistChildren = node.getChildNodes();
            for (int i=0; i<nodelistChildren.getLength(); i++) {
                nodeChild = nodelistChildren.item(i);
                if (nodeChild.getNodeType() == Node.TEXT_NODE) {
                    out.print(nodeChild.getTextContent() + " cNN ");
                    lstNodes.add(nodeChild);
                    mapNodes.put(nodeChild, nodeChild);
                //} else if (nodeChild.getNodeType() == Node.ATTRIBUTE_NODE) {
                //    out.print(nodeChild.getNodeValue() + " aNN ");
                } else {
                    out.println();
                    fnTraverseNode(nodeChild);
                }
            }
        }
    }

    private void fnIterator() {
        Node nodeListIterator = null;
        Node nodeMapKey = null;
        Node nodeMapVal = null;
        out.println();
        out.println(" * ");
        out.println(lstNodes.size());

        Iterator<Node> listIterator = lstNodes.iterator();
        while(listIterator.hasNext()) {
            nodeListIterator = listIterator.next();
            out.println(nodeListIterator.getNodeType() + " " + nodeListIterator.getNodeName() + " " + nodeListIterator.getNodeValue()+ " " + nodeListIterator.getTextContent());
            //if (nodeIterator.getNodeType() != Node.TEXT_NODE) {
            //    out.print(nodeIterator.getNodeName() + " ");
            //} else {
            //    out.println(nodeIterator.getTextContent() + " gTC ");
            //}
        }
        
        out.println();
        out.println(" * ");
        out.println(mapNodes.size());
        Iterator<Map.Entry> mapIterator = mapNodes.entrySet().iterator();
        while(mapIterator.hasNext()) {
            Map.Entry entry = mapIterator.next();
            nodeMapKey = (Node)entry.getKey();
            out.print("Key=" + nodeMapKey.getNodeType() + " " + nodeMapKey.getNodeName() + " " + nodeMapKey.getNodeValue()+ " " + nodeMapKey.getTextContent());
            nodeMapVal = (Node)entry.getValue();
            out.println(", Value=" + nodeMapVal.getNodeType() + " " + nodeMapVal.getNodeName() + " " + nodeMapVal.getNodeValue()+ " " + nodeMapVal.getTextContent());
        }
    }
    
    public static void main (String args[]) {
        clsXmlTraverser xmlTraverser = new clsXmlTraverser();
        Document xmlDocument = xmlTraverser.fnLoadXml();
        out.println(xmlDocument.getFirstChild().getNodeName() + " fc ");
        xmlTraverser.fnTraverseNode(xmlDocument.getFirstChild());
        xmlTraverser.fnIterator();
    }

}
