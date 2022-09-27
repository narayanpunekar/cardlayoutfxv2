/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.cardlayoutfxv2;

import static javafx.application.Application.launch;
//import javafx.animation.Animation;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
//import javafx.util.Duration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import java.io.File;

/**
 *
 * @author narayan.punekar@yahoo.com
 */
public class clsBarChart extends Application {

    final static String LastCnt = "LastCnt";
    final static String SolvedCnt = "SolvedCnt";
    final static String SolvedNum = "SolvedNum";
            
    @Override
    public void start(Stage stage) throws Exception {
        DocumentBuilderFactory dbFactory = null;
        DocumentBuilder documentBuilder = null;
        Document xmlDocumentPieChart = null;
        Document xmlDocument = null;
        dbFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = dbFactory.newDocumentBuilder();
        xmlDocumentPieChart = documentBuilder.parse(new File("PieChartData.xml"));
        xmlDocument = documentBuilder.parse(new File("ProductBacklog.xml"));
        XPath xpath = XPathFactory.newInstance().newXPath();

        String strSolvedcnt = "/PieChartData/SolvedCnt[last()]";
        Double dSolvedcntVal = (Double)xpath.evaluate(strSolvedcnt, xmlDocumentPieChart, XPathConstants.NUMBER);
        int iSolvedCnt = dSolvedcntVal.intValue();
        
        String strSolvednum = "/PieChartData/SolvedNum[last()]";
        Double dSolvednumVal = (Double)xpath.evaluate(strSolvednum, xmlDocumentPieChart, XPathConstants.NUMBER);
        int iSolvedNum = dSolvednumVal.intValue();

        String strSnocnt = "/ProductBacklog/SNo[last()]/@cnt";
        Double dSnocntVal = (Double)xpath.evaluate(strSnocnt, xmlDocument, XPathConstants.NUMBER);
        int iSnocntVal = dSnocntVal.intValue();

        stage.setTitle("Product Backlog");
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number, String> bc =
                new BarChart<Number, String>(xAxis, yAxis);
        bc.setTitle("Product Backlog");
        xAxis.setLabel("Value");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Count");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("January");
        series1.getData().add(new XYChart.Data(iSolvedNum, SolvedNum));
        series1.getData().add(new XYChart.Data(iSolvedCnt, SolvedCnt));
        series1.getData().add(new XYChart.Data(iSnocntVal, LastCnt));

        /*
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), 
            new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                for (XYChart.Series<Number, String> series : bc.getData()) {
                    for (XYChart.Data<Number, String> data : series.getData()) {
                        data.setXValue(Math.random() * 1000);
                    }
                }
            }
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.setAutoReverse(true);
        tl.play();
        */

        Scene scene = new Scene(bc, 800, 600);
        //scene.getStylesheets().add("barchartsample/Chart.css");
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }

    public void start() throws Exception {
        launch();
    }
}
