package com.app.cardlayoutfxv2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author narayan.punekar@yahoo.com
 * Card Layout based on CardLayoutDemo
 *
 */
public class clsLayout implements ItemListener, ActionListener {

    Panel cards;                //a panel that uses CardLayout
    Panel card1;                //a panel that uses first "card"
    Panel card2;                //a panel that uses second "card"
    Panel card3;                //a panel that uses third "card"
    Panel card4;                //a panel that uses fourth "card"
    Panel card5;                //a panel that uses fifth "card"
    Panel card6;                //a panel that uses sixth "card"
    Button btnPrevious;
    Button btnNext;
    TextField txtFirstName;
    TextField txtLastName;
    TextField txtEmail;
    TextField txtPhoneOne;
    TextField txtPhoneTwo;
    TextField txtFaxPager;
    Choice chcFaxPager;
    //Choice chcOS;
    CheckboxGroup cbgOS;
    Checkbox rdoWindows;
    Checkbox rdoUnix;
    Checkbox rdoLinux;
    Checkbox rdoOsx;
    Checkbox rdoMobile;
    Checkbox chkWord;
    Checkbox chkExcel;
    Checkbox chkPowerpoint;
    Checkbox chkOutlook;
    Checkbox chkVarious;
    TextArea txaProblemDescription;
    TextField txtPassword;
    char echoChar = '*';
    Label lblSolutionDescription;
    TextArea txaSolutionDescription;
    Panel panelOScbg;
    Panel panelSoftware;
    Button btnSubmitRequest;
    Button btnReset;
    Button btnSubmitSolution;
    TextArea txaTopTextArea;
    TextArea txaBottomTextArea;
    TextField txtSystemName;
    TextArea txaChatTranscriptArea;
    TextArea txaChatTextArea;
    final static String PROBLEMSPANEL = "IT Department";
    final static String SOLUTIONSPANEL = "Solutions Department";
    final static String PRODUCTBACKLOGPANEL = "Product Backlog";
    final static String PIECHARTPANEL = "Pie Chart";
    final static String BARCHARTPANEL = "Bar Chart";
    final static String WLANCHATPANEL = "LAN/WLAN Chat";
    final static String SUBMITREQUEST = "Submit Request";
    final static String SUBMITSOLUTION = "Submit Solution";
    final static String RESET = "Reset";
    final static String PREVIOUS = "Previous";
    final static String NEXT = "Next";
    final static String SEND = "Send";
//    final static String PREVIOUSSOLUTION = "PreviousSolution";
//    final static String NEXTSOLUTION = "NextSolution";
    final static String newline = "\n";
    int iCntVal;    //cnt value for Previous Next
    
    public clsLayout() {
        iCntVal = 0;
    }

    /**
     * Save the form
     */
    private void fnProcessRequest() {
        DocumentBuilderFactory dbFactory = null;
        DocumentBuilder documentBuilder = null;
        Document xmlDocument = null;
        Element elemProductBacklog = null;

        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = dbFactory.newDocumentBuilder();
            xmlDocument = documentBuilder.parse(new File("ProductBacklog.xml"));
            elemProductBacklog = xmlDocument.getDocumentElement();
        } catch(Exception e) {
            xmlDocument = documentBuilder.newDocument();
            elemProductBacklog = xmlDocument.createElement("ProductBacklog");
            xmlDocument.appendChild(elemProductBacklog);
            e.printStackTrace();
        } finally {
            try {
                XPath xpath = XPathFactory.newInstance().newXPath();
                String strSnocnt = "/ProductBacklog/SNo[last()]/@cnt";
                Double dSnocntVal = (Double)xpath.evaluate(strSnocnt, xmlDocument, XPathConstants.NUMBER);
                Integer strSnocntVal = dSnocntVal.intValue()+1;

                Element elemSNo = xmlDocument.createElement("SNo");
                elemProductBacklog.appendChild(elemSNo);

                Attr attrSNoCnt  = xmlDocument.createAttribute("cnt");
                attrSNoCnt.appendChild(xmlDocument.createTextNode(strSnocntVal.toString()));
                elemSNo.setAttributeNode(attrSNoCnt);

                Element elemFirstName = xmlDocument.createElement("FirstName");
                elemFirstName.appendChild(xmlDocument.createTextNode(txtFirstName.getText()));
                elemSNo.appendChild(elemFirstName);

                Element elemLastName = xmlDocument.createElement("LastName");
                elemLastName.appendChild(xmlDocument.createTextNode(txtLastName.getText()));
                elemSNo.appendChild(elemLastName);

                Element elemEmail = xmlDocument.createElement("Email");
                elemEmail.appendChild(xmlDocument.createTextNode(txtEmail.getText()));
                elemSNo.appendChild(elemEmail);

                Element elemPhoneOne = xmlDocument.createElement("PhoneOne");
                elemPhoneOne.appendChild(xmlDocument.createTextNode(txtPhoneOne.getText()));
                elemSNo.appendChild(elemPhoneOne);

                Element elemPhoneTwo = xmlDocument.createElement("PhoneTwo");
                elemPhoneTwo.appendChild(xmlDocument.createTextNode(txtPhoneTwo.getText()));
                elemSNo.appendChild(elemPhoneTwo);

                Element elemFaxPager = xmlDocument.createElement("FaxPagerchoice");
                elemSNo.appendChild(elemFaxPager);
                Attr attrFaxPager = xmlDocument.createAttribute("faxpager");
                attrFaxPager.appendChild(xmlDocument.createTextNode(chcFaxPager.getSelectedItem()));
                elemFaxPager.setAttributeNode(attrFaxPager);
                
                Element elemFaxPagerNum = xmlDocument.createElement("FaxPagerNum");
                elemFaxPagerNum.appendChild(xmlDocument.createTextNode(txtFaxPager.getText()));
                elemSNo.appendChild(elemFaxPagerNum);

                /*
                Element elemOSchoice = xmlDocument.createElement("OSchoice");
                //elemOSchoice.appendChild(xmlDocument.createTextNode("Operating System choice"));
                elemSNo.appendChild(elemOSchoice);

                Attr attrOSchoice = xmlDocument.createAttribute("ostypechoice");
                attrOSchoice.appendChild(xmlDocument.createTextNode(chcOS.getSelectedItem()));
                elemOSchoice.setAttributeNode(attrOSchoice);
                */

                Element elemOSradio = xmlDocument.createElement("OSradio");
                //elemOSradio.appendChild(xmlDocument.createTextNode("Operating System radio"));
                elemSNo.appendChild(elemOSradio);

                Attr attrOSradio = xmlDocument.createAttribute("ostyperadio");
                attrOSradio.appendChild(xmlDocument.createTextNode(cbgOS.getSelectedCheckbox().getLabel()));
                elemOSradio.setAttributeNode(attrOSradio);

                Element elemSoftware = xmlDocument.createElement("Software");
                //elemSoftware.appendChild(xmlDocument.createTextNode("Software"));
                elemSNo.appendChild(elemSoftware);

                String strWord = new String();
                if(chkWord.getState()==true){
                    strWord = chkWord.getLabel();
                }
                Attr attrWord = xmlDocument.createAttribute("Word");
                attrWord.appendChild(xmlDocument.createTextNode(strWord));
                elemSoftware.setAttributeNode(attrWord);

                String strExcel = new String();
                if(chkExcel.getState()==true){
                    strExcel = chkExcel.getLabel();
                }
                Attr attrExcel = xmlDocument.createAttribute("Excel");
                attrExcel.appendChild(xmlDocument.createTextNode(strExcel));
                elemSoftware.setAttributeNode(attrExcel);

                String strPowerpoint = new String();
                if(chkPowerpoint.getState()==true){
                    strPowerpoint = chkPowerpoint.getLabel();
                }
                Attr attrPowerpoint = xmlDocument.createAttribute("Powerpoint");
                attrPowerpoint.appendChild(xmlDocument.createTextNode(strPowerpoint));
                elemSoftware.setAttributeNode(attrPowerpoint);

                String strOutlook = new String();
                if(chkOutlook.getState()==true){
                    strOutlook = chkOutlook.getLabel();
                }
                Attr attrOutlook = xmlDocument.createAttribute("Outlook");
                attrOutlook.appendChild(xmlDocument.createTextNode(strOutlook));
                elemSoftware.setAttributeNode(attrOutlook);

                String strVarious = new String();
                if(chkVarious.getState()==true){
                    strVarious = chkVarious.getLabel();
                }
                Attr attrVarious = xmlDocument.createAttribute("Various");
                attrVarious.appendChild(xmlDocument.createTextNode(strVarious));
                elemSoftware.setAttributeNode(attrVarious);

                Element elemProblemDescription = xmlDocument.createElement("ProblemDescription");
                elemProblemDescription.appendChild(xmlDocument.createTextNode(txaProblemDescription.getText()));
                elemSNo.appendChild(elemProblemDescription);

                Element elemPassword = xmlDocument.createElement("Password");
                elemPassword.appendChild(xmlDocument.createTextNode(txtPassword.getText()));
                elemSNo.appendChild(elemPassword);

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(new DOMSource(xmlDocument), new StreamResult(new File("ProductBacklog.xml")));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Save the Solution
     */
    private void fnProcessSolution() {
        DocumentBuilderFactory dbFactory = null;
        DocumentBuilder documentBuilder = null;
        Document xmlDocument = null;
        Element elemSolutionDescription = null;
        Node nodeSNo = null;
        Element elemSNo = null;

        Document xmlDocumentPieChart = null;
        Element elemPieChartData = null;

        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = dbFactory.newDocumentBuilder();
            xmlDocumentPieChart = documentBuilder.parse(new File("PieChartData.xml"));
            elemPieChartData = xmlDocumentPieChart.getDocumentElement();
        } catch(Exception e) {
            xmlDocumentPieChart = documentBuilder.newDocument();
            elemPieChartData = xmlDocumentPieChart.createElement("PieChartData");
            xmlDocumentPieChart.appendChild(elemPieChartData);
            e.printStackTrace();
        } finally {
            try {
                xmlDocument = documentBuilder.parse(new File("ProductBacklog.xml"));
                XPath xpath = XPathFactory.newInstance().newXPath();

                //new <SNo><SolutionDescription>, increment <SolvedCnt>
                String strSolutionDescription = "/ProductBacklog/SNo[@cnt=" + iCntVal + "][SolutionDescription]";
                Node nodeSolutionDescriptionExists = null;
                boolean bNodeSolutionDescriptionExists = false;
                nodeSolutionDescriptionExists = (Node)xpath.evaluate(strSolutionDescription, xmlDocument, XPathConstants.NODE);
                if (nodeSolutionDescriptionExists != null)
                    bNodeSolutionDescriptionExists = true;

                if (!bNodeSolutionDescriptionExists) {
                    String strSolvedcnt = "/PieChartData/SolvedCnt[last()]";
                    Double dSolvedcntVal = (Double)xpath.evaluate(strSolvedcnt, xmlDocumentPieChart, XPathConstants.NUMBER);
                    Element elemSolvedCnt = xmlDocumentPieChart.createElement("SolvedCnt");
                    int iSolvedcntVal = dSolvedcntVal.intValue() + 1;
                    elemSolvedCnt.appendChild(xmlDocumentPieChart.createTextNode((new Integer(iSolvedcntVal)).toString()));
                    elemPieChartData.appendChild(elemSolvedCnt);
                }
                
                //new <SolutionDescription>, increment <SolvedNum>
                String strSolvednum = "/PieChartData/SolvedNum[last()]";
                Double dSolvednumVal = (Double)xpath.evaluate(strSolvednum, xmlDocumentPieChart, XPathConstants.NUMBER);
                Element elemSolvedNum = xmlDocumentPieChart.createElement("SolvedNum");
                int iSolvednumVal = dSolvednumVal.intValue() + 1;
                elemSolvedNum.appendChild(xmlDocumentPieChart.createTextNode((new Integer(iSolvednumVal)).toString()));
                elemPieChartData.appendChild(elemSolvedNum);

                String strSnocnt = "/ProductBacklog/SNo[@cnt=" + iCntVal +"]";
                nodeSNo = (Node)xpath.evaluate(strSnocnt, xmlDocument, XPathConstants.NODE);
                elemSNo = (Element) nodeSNo;

                elemSolutionDescription = xmlDocument.createElement("SolutionDescription");
                elemSolutionDescription.appendChild(xmlDocument.createTextNode(txaSolutionDescription.getText()));
                elemSNo.appendChild(elemSolutionDescription);

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(new DOMSource(xmlDocument), new StreamResult(new File("ProductBacklog.xml")));

                Transformer transformerPieChart = TransformerFactory.newInstance().newTransformer();
                transformerPieChart.transform(new DOMSource(xmlDocumentPieChart), new StreamResult(new File("PieChartData.xml")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Populate the form with the saved data
     */
    private void fnProcessXPath(int iCnt) {
        try {
            out.println("iCnt: " + iCnt);
            DocumentBuilderFactory dbFactory = null;
            DocumentBuilder documentBuilder = null;
            Document xmlDocument = null;
            XPath xpath = null;

            dbFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = dbFactory.newDocumentBuilder();
            xmlDocument = documentBuilder.parse(new File("ProductBacklog.xml"));
            xpath = XPathFactory.newInstance().newXPath();

            String strFirstName = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/FirstName";
            String strFirstNameVal = (String)xpath.evaluate(strFirstName, xmlDocument, XPathConstants.STRING);
            txtFirstName.setText(strFirstNameVal);

            String strLastName = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/LastName";
            String strLastNameVal = (String)xpath.evaluate(strLastName, xmlDocument, XPathConstants.STRING);
            txtLastName.setText(strLastNameVal);
            
            String strEmail = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/Email";
            String strEmailVal = (String)xpath.evaluate(strEmail, xmlDocument, XPathConstants.STRING);
            txtEmail.setText(strEmailVal);

            String strPhoneOne = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/PhoneOne";
            String strPhoneOneVal = (String)xpath.evaluate(strPhoneOne, xmlDocument, XPathConstants.STRING);
            txtPhoneOne.setText(strPhoneOneVal);

            String strPhoneTwo = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/PhoneTwo";
            String strPhoneTwoVal = (String)xpath.evaluate(strPhoneTwo, xmlDocument, XPathConstants.STRING);
            txtPhoneTwo.setText(strPhoneTwoVal);

            String strFaxPagerChoice = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/FaxPagerchoice/@faxpager";
            String strFaxPagerChoiceVal = (String)xpath.evaluate(strFaxPagerChoice, xmlDocument, XPathConstants.STRING);
            chcFaxPager.select(strFaxPagerChoiceVal);

            String strFaxPagerNum = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/FaxPagerNum";
            String strFaxPagerNumVal = (String)xpath.evaluate(strFaxPagerNum, xmlDocument, XPathConstants.STRING);
            txtFaxPager.setText(strFaxPagerNumVal);

            //String strOSchoice = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/OSchoice/@ostypechoice";
            //String strOSchoiceVal = (String)xpath.evaluate(strOSchoice, xmlDocument, XPathConstants.STRING);
            //chcOS.select(strOSchoiceVal);

            String strOSradio = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/OSradio/@ostyperadio";
            String strOSradioVal = (String)xpath.evaluate(strOSradio, xmlDocument, XPathConstants.STRING);
            //Begin branch210201_1: Support for mobile
            switch(strOSradioVal) {
                case "Windows": 
                    cbgOS.setSelectedCheckbox(rdoWindows);
                    break;
                case "Unix": 
                    cbgOS.setSelectedCheckbox(rdoUnix);
                    break;
                case "Linux": 
                    cbgOS.setSelectedCheckbox(rdoLinux);
                    break;
                case "OS X": 
                    cbgOS.setSelectedCheckbox(rdoOsx);
                    break;
                case "Mobile":
                    cbgOS.setSelectedCheckbox(rdoMobile);
                    break;
                default:
                    //cbgOS.setSelectedCheckbox(rdoWindows);
                    cbgOS.setSelectedCheckbox(rdoMobile);
                    break;
            }
            //End branch210201_1: Support for mobile

            //String strSoftware = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/Software";
            //Node nodeSoftware = (Node)xpath.evaluate(strSoftware, xmlDocument, XPathConstants.NODE);
            //String strWordVal = nodeSoftware.getAttributes().getNamedItem("Word").getNodeValue();
            //String strExcelVal = nodeSoftware.getAttributes().getNamedItem("Excel").getNodeValue();
            //String strPowerpointVal = nodeSoftware.getAttributes().getNamedItem("Powerpoint").getNodeValue();
            //String strOutlookVal = nodeSoftware.getAttributes().getNamedItem("Outlook").getNodeValue();
            //String strVariousVal = nodeSoftware.getAttributes().getNamedItem("Various").getNodeValue();

            String strWord = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/Software/@Word";
            String strWordVal = (String)xpath.evaluate(strWord, xmlDocument, XPathConstants.STRING);
            if(strWordVal.equals("Word")) {
                chkWord.setState(true);
            } else {
                chkWord.setState(false);
            }

            String strExcel = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/Software/@Excel";
            String strExcelVal = (String)xpath.evaluate(strExcel, xmlDocument, XPathConstants.STRING);
            if(strExcelVal.equals("Excel")) {
                chkExcel.setState(true);
            } else {
                chkExcel.setState(false);
            }

            String strPowerpoint = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/Software/@Powerpoint";
            String strPowerpointVal = (String)xpath.evaluate(strPowerpoint, xmlDocument, XPathConstants.STRING);
            if(strPowerpointVal.equals("Powerpoint")) {
                chkPowerpoint.setState(true);
            } else {
                chkPowerpoint.setState(false);
            }

            String strOutlook = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/Software/@Outlook";
            String strOutlookVal = (String)xpath.evaluate(strOutlook, xmlDocument, XPathConstants.STRING);
            if(strOutlookVal.equals("Outlook")) {
                chkOutlook.setState(true);
            } else {
                chkOutlook.setState(false);
            }

            String strVarious = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/Software/@Various";
            String strVariousVal = (String)xpath.evaluate(strVarious, xmlDocument, XPathConstants.STRING);
            if(strVariousVal.equals("Various")) {
                chkVarious.setState(true);
            } else {
                chkVarious.setState(false);
            }

            String strProblemDescription = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/ProblemDescription";
            String strProblemDescriptionVal = (String)xpath.evaluate(strProblemDescription, xmlDocument, XPathConstants.STRING);
            txaProblemDescription.setText(strProblemDescriptionVal);
            
            String strPassword = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/Password";
            String strPasswordVal = (String)xpath.evaluate(strPassword, xmlDocument, XPathConstants.STRING);
            txtPassword.setText(strPasswordVal);
            
            //Begin branch210123_1: Solution Description placeholder text @ Solutions Department screen
            /* 
            String strSolutionDescription = "/ProductBacklog/SNo[@cnt=" + iCnt + "]/SolutionDescription";
            String strSolutionDescriptionVal = (String)xpath.evaluate(strSolutionDescription, xmlDocument, XPathConstants.STRING);
            txaSolutionDescription.setText(strSolutionDescriptionVal);
            */
            txaSolutionDescription.setFont(new Font(Font.SERIF,Font.ITALIC,15));
            txaSolutionDescription.setText("<Enter Solution...>");
            //End branch210123_1: Solution Description placeholder text @ Solutions Department screen
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

/**
 * Pie Chart
 */
    private void fnPieChart() {
        try {
            clsPieChart pieChart = new clsPieChart();
            pieChart.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/**
 * Bar Chart
 */
    private void fnBarChart() {
        //Begin branch210123_2: private class clsChartBar
        try {
            clsBarChart barChart = new clsBarChart();
            barChart.start();
            /*
            JFrame frmBarChart = new JFrame("Product Backlog : Bar Chart");
            frmBarChart.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
            //Create and set up the content pane.
            clsBarChart barChart = new clsBarChart();
            //Display the window.
            frmBarChart.add(barChart);
            frmBarChart.pack();
            frmBarChart.setSize(1000, 700);
            //frmBarChart.setAutoRequestFocus(true);
            frmBarChart.setVisible(true);
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
        //End branch210123_2: private class clsChartBar
    }

    /**
    * Product Backlog
    */
    private void fnProductBacklog() {
        try {
            DocumentBuilderFactory dbFactory = null;
            DocumentBuilder documentBuilder = null;
            Document xmlDocument = null;
            NodeList nodelistSNo = null;
            Node nodeSNo = null;
            Element elemSNo = null;
            NodeList nodelistProblemDescription = null;
            Node nodeProblemDescription = null;
            Element elemProblemDescription = null;
            NodeList nodelistSolutionDescription = null;
            Node nodeSolutionDescription = null;
            Element elemSolutionDescription = null;

            dbFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = dbFactory.newDocumentBuilder();
            xmlDocument = documentBuilder.parse(new File("ProductBacklog.xml"));
            nodelistSNo = xmlDocument.getElementsByTagName("SNo");
         
            txaTopTextArea.setText(new String(""));
            txaBottomTextArea.setText(new String(""));
            out.println("nodelistSNo.getLength(): " + nodelistSNo.getLength());
            for (int j = 0; j < nodelistSNo.getLength(); j++) {
                nodeSNo = nodelistSNo.item(j);
            
                if (nodeSNo.getNodeType() == Node.ELEMENT_NODE) {
                    elemSNo = (Element) nodeSNo;
                    out.println("cnt : " + elemSNo.getAttribute("cnt"));
                    nodelistProblemDescription = elemSNo.getElementsByTagName("ProblemDescription");
                    nodeProblemDescription = nodelistProblemDescription.item(0);
                    if (nodeProblemDescription.getNodeType() == Node.ELEMENT_NODE) {
                        elemProblemDescription = (Element) nodeProblemDescription;
                        out.println("ProblemDescription : " + elemProblemDescription.getTextContent());
                        txaTopTextArea.append("#" + elemSNo.getAttribute("cnt") + "] " + elemProblemDescription.getTextContent()
                                            + clsLayout.newline);
                        txaBottomTextArea.append("#" + elemSNo.getAttribute("cnt") + "] " + elemProblemDescription.getTextContent()
                                            + clsLayout.newline);
                    }
                    nodelistSolutionDescription = elemSNo.getElementsByTagName("SolutionDescription");
                    for (int k = 0; k < nodelistSolutionDescription.getLength(); k++) {
                        nodeSolutionDescription = nodelistSolutionDescription.item(k);
                        if(nodeSolutionDescription.getNodeType()==Node.ELEMENT_NODE) {
                            elemSolutionDescription = (Element)nodeSolutionDescription;
                            out.println("SolutionDescription : " + elemSolutionDescription.getTextContent());
                            txaBottomTextArea.append(elemSNo.getAttribute("cnt") + "." + (k+1) + "] " + elemSolutionDescription.getTextContent()
                                                + clsLayout.newline);
                        }
                    }
                    txaBottomTextArea.append(new String("") 
                                        + clsLayout.newline);
                }
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void fnReset() {
        try {
            txtFirstName.setText(new String(""));
            txtLastName.setText(new String(""));
            txtEmail.setText(new String(""));
            txtPhoneOne.setText(new String(""));
            txtPhoneTwo.setText(new String(""));
            chcFaxPager.select("Fax");
            txtFaxPager.setText(new String(""));
            //chcOS.select("Windows");
            //cbgOS.setSelectedCheckbox(rdoWindows);
            cbgOS.setSelectedCheckbox(rdoMobile);
            chkWord.setState(false);
            chkExcel.setState(false);
            chkPowerpoint.setState(false);
            chkOutlook.setState(false);
            chkVarious.setState(false);
            txaProblemDescription.setText(new String(""));
            txtPassword.setText(new String(""));
            txaSolutionDescription.setText(new String(""));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
     
    @Override
    public void actionPerformed(ActionEvent evt) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(evt.getActionCommand().equals(SUBMITREQUEST)) {
            fnProcessRequest();
            fnReset();
        }
        if(evt.getActionCommand().equals(SUBMITSOLUTION)) {
            fnProcessSolution();
            fnReset();
        }
        if(evt.getActionCommand().equals(RESET)) {
            fnReset();
            //System.exit(0);
        }
        if(evt.getActionCommand().equals(PREVIOUS)) {
            fnProcessXPath(--iCntVal);
        }
        if(evt.getActionCommand().equals(NEXT)) {
            fnProcessXPath(++iCntVal);
        }
        if(evt.getActionCommand().equals(SEND)) {
            fnWLANClientSocket();
        }
    }

/**
 * Card Layout based on CardLayoutDemo
 * The "card"
 */
    private void fnPanelOne() {
        card1 = new Panel();
        card1.setLayout(new GridLayout(15,2,15,15));

        btnPrevious = new Button("Previous");
        btnPrevious.addActionListener(this);
        card1.add(btnPrevious);
        btnNext = new Button("Next");
        btnNext.addActionListener(this);
        card1.add(btnNext);

        card1.add(new Label("First Name"));
        txtFirstName = new TextField(20);
        card1.add(txtFirstName);
        
        card1.add(new Label("Last Name"));
        txtLastName = new TextField(20);
        card1.add(txtLastName);

        card1.add(new Label("Email"));
        txtEmail = new TextField(20);
        card1.add(txtEmail);

        card1.add(new Label("Phone:Primary"));
        txtPhoneOne = new TextField(20);
        card1.add(txtPhoneOne);

        card1.add(new Label("Phone:Secondary"));
        txtPhoneTwo = new TextField(20);
        card1.add(txtPhoneTwo);

        card1.add(new Label("Fax or Pager: select any one"));
        chcFaxPager = new Choice();
        chcFaxPager.add("Fax");
        chcFaxPager.add("Pager");
        card1.add(chcFaxPager);

        card1.add(new Label("Fax Pager"));
        txtFaxPager = new TextField(20);
        card1.add(txtFaxPager);

        /*
        card1.add(new Label("Operating System: select any one"));
        chcOS = new Choice();
        chcOS.add("Windows");
        chcOS.add("Unix");
        chcOS.add("Linux");
        chcOS.add("OS X");
        card1.add(chcOS);
        */

        card1.add(new Label("Operating System: select any one"));
        panelOScbg = new Panel();
        cbgOS = new CheckboxGroup();
        //rdoWindows = new Checkbox("Windows", cbgOS, true);
        rdoWindows = new Checkbox("Windows", cbgOS, false);
        panelOScbg.add(rdoWindows);
        rdoUnix = new Checkbox("Unix", cbgOS, false);
        panelOScbg.add(rdoUnix);
        rdoLinux = new Checkbox("Linux", cbgOS, false);
        panelOScbg.add(rdoLinux);
        rdoOsx = new Checkbox("OS X", cbgOS, false);
        panelOScbg.add(rdoOsx);
        //Begin branch210201_1: Support for mobile
        rdoMobile = new Checkbox("Mobile", cbgOS, true);
        panelOScbg.add(rdoMobile);
        //End branch210201_1: Support for mobile
        card1.add(panelOScbg);

        card1.add(new Label("Software"));
        panelSoftware = new Panel();
        chkWord = new Checkbox("Word");
        panelSoftware.add(chkWord);
        chkExcel = new Checkbox("Excel");
        panelSoftware.add(chkExcel);
        chkPowerpoint = new Checkbox("Powerpoint");
        panelSoftware.add(chkPowerpoint);
        chkOutlook = new Checkbox("Outlook");
        panelSoftware.add(chkOutlook);
        chkVarious = new Checkbox("Various");
        panelSoftware.add(chkVarious);
        card1.add(panelSoftware);
        
        card1.add(new Label("Problem Description"));
        txaProblemDescription = new TextArea("", 20, 40, TextArea.SCROLLBARS_BOTH);
        txaProblemDescription.setCaretPosition(0);
        card1.add(txaProblemDescription);
        
        card1.add(new Label("Password"));
        txtPassword = new TextField(20);
        txtPassword.setEchoChar(echoChar);
        card1.add(txtPassword);

        lblSolutionDescription = new Label("Solution Description");
        card1.add(lblSolutionDescription);
        txaSolutionDescription = new TextArea("", 20, 40, TextArea.SCROLLBARS_BOTH);
        txaSolutionDescription.setCaretPosition(0);
        card1.add(txaSolutionDescription);

        lblSolutionDescription.setVisible(false);
        txaSolutionDescription.setVisible(false);

        btnReset = new Button("Reset");
        btnReset.addActionListener(this);
        card1.add(btnReset);
        btnSubmitRequest = new Button(SUBMITREQUEST);
        btnSubmitRequest.addActionListener(this);
        card1.add(btnSubmitRequest);
    }

    /**
    * Card Layout based on CardLayoutDemo
    * The "card"
    */
    private void fnPanelThree() {
        card3 = new Panel();
        card3.setLayout(new GridBagLayout());
        GridBagLayout gridbag = (GridBagLayout)card3.getLayout();
        GridBagConstraints c = new GridBagConstraints();

        Label lblProblem = null;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        lblProblem = new Label(PROBLEMSPANEL);
        gridbag.setConstraints(lblProblem, c);
        card3.add(lblProblem);
        
        //Begin branch210201_1: Product Backlog screen changes
        //c.weighty = 1.0;
        c.weighty = 0.0;
        //End branch210201_1: Product Backlog screen changes
        txaTopTextArea = new TextArea();
        txaTopTextArea.setEditable(false);
        txaTopTextArea.setCaretPosition(0);
        //Begin branch210201_1: Product Backlog screen changes
        txaTopTextArea.setBackground(Color.magenta);
        //End branch210201_1: Product Backlog screen changes
        ScrollPane scrpTopScrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrpTopScrollPane.add(txaTopTextArea);
        gridbag.setConstraints(scrpTopScrollPane, c);
        card3.add(scrpTopScrollPane);
        
        c.weightx = 0.0;
        c.weighty = 0.0;
        Label lblSolution = null;
        lblSolution = new Label(SOLUTIONSPANEL);
        gridbag.setConstraints(lblSolution, c);
        card3.add(lblSolution);
        
        c.weighty = 1.0;
        txaBottomTextArea = new TextArea();
        txaBottomTextArea.setEditable(false);
        txaBottomTextArea.setCaretPosition(0);
        //Begin branch210201_1: Product Backlog screen changes
        txaBottomTextArea.setBackground(Color.cyan);
        //End branch210201_1: Product Backlog screen changes
        ScrollPane scrpBottomScrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrpBottomScrollPane.add(txaBottomTextArea);
        gridbag.setConstraints(scrpBottomScrollPane, c);
        card3.add(scrpBottomScrollPane);
        
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.insets = new Insets(10,10,0,10);
        btnPrevious = new Button("Previous");
        gridbag.setConstraints(btnPrevious, c);
        card3.add(btnPrevious);
        
        c.gridwidth = GridBagConstraints.REMAINDER;
        btnNext = new Button("Next");
        gridbag.setConstraints(btnNext, c);
        card3.add(btnNext);

        btnPrevious.addActionListener(this);
        btnNext.addActionListener(this);
    }

/**
 * Card Layout based on CardLayoutDemo
 * The "card"
 */
    private void fnPanelFour() {
        card4 = new Panel();
    }
    
/**
 * Card Layout based on CardLayoutDemo
 * The "card"
 */
    private void fnPanelFive() {
        card5 = new Panel();
    }
    
/**
 * Card Layout based on CardLayoutDemo
 * The "card"
 */
    private void fnPanelSix() {
        card6 = new Panel();
        card6.setLayout(new GridBagLayout());
        GridBagLayout gridbag = (GridBagLayout)card6.getLayout();
        GridBagConstraints c = new GridBagConstraints();

        Label lblSystemName = new Label("System Name or IPv4 Address:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,20,0,0);
        c.weightx = 0.5;
        c.weighty = 0.0;
        gridbag.setConstraints(lblSystemName, c);
        card6.add(lblSystemName);
        
        txtSystemName = new TextField(20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,20,0,20);
        c.weightx = 0.5;
        c.weighty = 0.0;
        gridbag.setConstraints(txtSystemName, c);
        card6.add(txtSystemName);
        
        Button btnSend = new Button("Send");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0,750,0,20);
        c.weightx = 0.5;
        c.weighty = 0.0;
        gridbag.setConstraints(btnSend, c);
        card6.add(btnSend);
        btnSend.addActionListener(this);

        Label lblChatText = new Label("Chat Text:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(20,20,0,0);
        c.weightx = 0.5;
        c.weighty = 0.0;
        gridbag.setConstraints(lblChatText, c);
        card6.add(lblChatText);

        txaChatTextArea = new TextArea();
        txaChatTextArea.setEditable(true);
        txaChatTextArea.setCaretPosition(0);
        txaChatTextArea.setBackground(Color.white);
        ScrollPane scrpChatTextScrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrpChatTextScrollPane.add(txaChatTextArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.gridx = 1; 
        c.gridy = 3;
        c.insets = new Insets(0,20,0,20);
        c.ipady = 15;       //make this component tall
        c.weightx = 0.5;
        c.weighty = 1.0;
        gridbag.setConstraints(scrpChatTextScrollPane, c);
        card6.add(scrpChatTextScrollPane);

        Label lblChatTranscript = new Label("Chat Transcript:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(20,20,0,0);
        c.ipady = 0;       //reset to default
        c.weightx = 0.5;
        c.weighty = 0.0;
        gridbag.setConstraints(lblChatTranscript, c);
        card6.add(lblChatTranscript);

        txaChatTranscriptArea = new TextArea();
        txaChatTranscriptArea.setEditable(false);
        txaChatTranscriptArea.setCaretPosition(0);
        txaChatTranscriptArea.setBackground(Color.magenta);
        ScrollPane scrpChatTranscriptScrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrpChatTranscriptScrollPane.add(txaChatTranscriptArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(0,20,0,20);
        c.ipady = 65;       //make this component taller
        c.weightx = 0.5;
        c.weighty = 1.0;
        gridbag.setConstraints(scrpChatTranscriptScrollPane, c);
        card6.add(scrpChatTranscriptScrollPane);

        Label lblSystems = new Label("Systems:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(20,20,0,20);
        c.ipady = 0;       //reset to default
        c.weightx = 0.5;
        c.weighty = 0.0;
        gridbag.setConstraints(lblSystems, c);
        card6.add(lblSystems);

        TextArea txaSystems = new TextArea();
        txaSystems.setEditable(false);
        txaSystems.setCaretPosition(0);
        txaSystems.setBackground(Color.cyan);
        ScrollPane scrpUsersScrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrpUsersScrollPane.add(txaSystems);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.gridheight = 3;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0,20,0,20);
        c.ipady = 80;       //make this component tallest; ipady(tallest)=ipady(tall)+ipady(taller)
        c.weightx = 0.5;
        c.weighty = 1.0;
        gridbag.setConstraints(scrpUsersScrollPane, c);
        card6.add(scrpUsersScrollPane);
        
        c.ipady = 0;        //reset to default
    }

    private void fnWLANChatServer() {
        clsLayout clsLayout = new clsLayout();
        new clsWLANChat(clsLayout, txaChatTranscriptArea).fnWLANServerSocket();
    }

    private void fnWLANClientSocket() {
        try {
            InetAddress inetAddress = InetAddress.getByName(txtSystemName.getText());
            Socket s = new Socket(inetAddress, 5000);
            PrintWriter printWriter = new PrintWriter(s.getOutputStream());
            txaChatTextArea.setText(txaChatTextArea.getText().trim());
            printWriter.print(txaChatTextArea.getText() + clsLayout.newline);
            setChatTranscript(txaChatTranscriptArea, InetAddress.getLocalHost().getHostName() + ": ", txaChatTextArea.getText());
            //txaChatTranscriptArea.append(layout.newline + InetAddress.getLocalHost().getHostName() + ": ");
            //txaChatTranscriptArea.append(txaChatTextArea.getText());
            printWriter.flush();
            txaChatTextArea.setText(new String(""));
            txaChatTextArea.setText(txaChatTextArea.getText().trim());
            s.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setChatTranscript(TextArea txaChatTranscriptArea, String str1, String str2) {
        synchronized(this) {
            txaChatTranscriptArea.append(clsLayout.newline + str1);
            txaChatTranscriptArea.append(str2);
        }
    }
    
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cardLayout = (CardLayout)cards.getLayout();
        cardLayout.show(cards, (String)evt.getItem());
        if(evt.getItem().toString()==PROBLEMSPANEL) {
            txtFirstName.setEditable(true);
            txtLastName.setEditable(true);
            txtEmail.setEditable(true);
            txtPhoneOne.setEditable(true);
            txtPhoneTwo.setEditable(true);
            chcFaxPager.setEnabled(true);
            txtFaxPager.setEditable(true);
            //chcOS.setEnabled(true);
            rdoWindows.setEnabled(true);
            rdoUnix.setEnabled(true);
            rdoLinux.setEnabled(true);
            rdoOsx.setEnabled(true);
            //Begin branch210201_1: Support for mobile
            rdoMobile.setEnabled(true);
            //End branch210201_1: Support for mobile
            chkWord.setEnabled(true);
            chkExcel.setEnabled(true);
            chkPowerpoint.setEnabled(true);
            chkOutlook.setEnabled(true);
            chkVarious.setEnabled(true);
            txaProblemDescription.setEditable(true);
            txtPassword.setEditable(true);
            btnPrevious.setVisible(false);
            btnNext.setVisible(false);
            lblSolutionDescription.setVisible(false);
            txaSolutionDescription.setVisible(false);
            btnSubmitRequest.setActionCommand(SUBMITREQUEST);
        }
        if(evt.getItem().toString()==SOLUTIONSPANEL) {
            iCntVal = 0;
            txtFirstName.setEditable(false);
            txtLastName.setEditable(false);
            txtEmail.setEditable(false);
            txtPhoneOne.setEditable(false);
            txtPhoneTwo.setEditable(false);
            chcFaxPager.setEnabled(false);
            txtFaxPager.setEditable(false);
            //chcOS.setEnabled(false);
            rdoWindows.setEnabled(false);
            rdoUnix.setEnabled(false);
            rdoLinux.setEnabled(false);
            rdoOsx.setEnabled(false);
            //Begin branch210201_1: Support for mobile
            rdoMobile.setEnabled(false);
            //End branch210201_1: Support for mobile
            chkWord.setEnabled(false);
            chkExcel.setEnabled(false);
            chkPowerpoint.setEnabled(false);
            chkOutlook.setEnabled(false);
            chkVarious.setEnabled(false);
            txaProblemDescription.setEditable(false);
            txtPassword.setEditable(false);
            btnPrevious.setVisible(true);
            btnNext.setVisible(true);
            lblSolutionDescription.setVisible(true);
            txaSolutionDescription.setVisible(true);
            btnSubmitRequest.setActionCommand(SUBMITSOLUTION);
            //btnPrevious.setActionCommand(PREVIOUSSOLUTION);
            //btnNext.setActionCommand(NEXTSOLUTION);
            fnProcessXPath(++iCntVal);
        }
        if(evt.getItem().toString()==PRODUCTBACKLOGPANEL) {
            fnProductBacklog();
        }
        if(evt.getItem().toString()==PIECHARTPANEL) {
            fnPieChart();
        }
        if(evt.getItem().toString()==BARCHARTPANEL) {
            fnBarChart();
        }
        if(evt.getItem().toString()==WLANCHATPANEL) {
            fnWLANChatServer();
        }
    }
    
    public void addComponentToPane(Container pane) {
        //Put the Choice in a Panel to get a nicer look.
        Panel comboBoxPane = new Panel();   //use FlowLayout
        Choice cb = new Choice();
        cb.add(PROBLEMSPANEL);
        cb.add(SOLUTIONSPANEL);
        cb.add(PRODUCTBACKLOGPANEL);
        cb.add(PIECHARTPANEL);
        cb.add(BARCHARTPANEL);
        cb.add(WLANCHATPANEL);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        
        //Create the "cards"
        fnPanelOne();
        //fnPanelTwo();
        fnPanelThree();
        fnPanelFour();
        fnPanelFive();
        fnPanelSix();

        //Create the panel that contains the "cards".
        cards = new Panel(new CardLayout());
        cards.add(card1, PROBLEMSPANEL);
        cards.add(card1, SOLUTIONSPANEL);
        cards.add(card3, PRODUCTBACKLOGPANEL);
        cards.add(card4, PIECHARTPANEL);
        cards.add(card5, BARCHARTPANEL);
        cards.add(card6, WLANCHATPANEL);

        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("CardLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        clsLayout demo = new clsLayout();
        demo.addComponentToPane(frame.getContentPane());
        
        //Display the window.
        frame.pack();
        frame.setSize(3000, 3000);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
