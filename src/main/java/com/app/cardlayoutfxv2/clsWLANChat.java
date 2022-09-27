package com.app.cardlayoutfxv2;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author narayan.punekar@yahoo.com
 * Chat Server
 *
 */
//public class clsWLANChat implements Runnable {
public class clsWLANChat extends Thread {
    ServerSocket ss = null;
    Socket s = null;
    clsLayout objLayout  = null;
    TextArea txaChatTranscriptArea = null;

    clsWLANChat(clsLayout objLayout, TextArea txaChatTranscriptArea) {
        this.objLayout = objLayout;
        this.txaChatTranscriptArea = txaChatTranscriptArea;
    }

    public void fnWLANServerSocket() {
        while(true) {
            try {
                //128 means the server can listen to 128 clients
                this.ss = new ServerSocket(5000, 128);
                this.ss.setSoTimeout(100);
            } catch(IOException e) {
                //e.printStackTrace();
                continue;
            }
            //new Thread(new clsWLANChat()).start();
            //new Thread().start();
            start();
            break;
        }
    }

    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + "running");
        while(true) {
            try {
                Thread.sleep(6000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            try {
                s = ss.accept();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String strLine = bufferedReader.readLine();
                System.out.println(s.getInetAddress().getHostName());
                System.out.println(strLine);
                this.objLayout.setChatTranscript(this.txaChatTranscriptArea, s.getInetAddress().getHostName() + ": ", strLine);
                s.close();
            } catch(IOException e) {
                //e.printStackTrace();
                continue;
            }
        }
    }
}
