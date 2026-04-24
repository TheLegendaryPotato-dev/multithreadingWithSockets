package com.example;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.*;


public class SocketClientExample {
   
   
    /*
     * Modify this example so that it opens a dialogue window using java swing,
     * takes in a user message and sends it
     * to the server. The server should output the message back to all connected clients
     * (you should see your own message pop up in your client as well when you send it!).
     *  We will build on this project in the future to make a full fledged server based game,
     *  so make sure you can read your code later! Use good programming practices.
     *  ****HINT**** you may wish to have a thread be in charge of sending information
     *  and another thread in charge of receiving information.
    */
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = new Socket(host.getHostName(), 9876);
            //write to socket using ObjectOutputStream
        ObjectOutputStream   oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        JFrame chatBoot = new JFrame();
        chatBoot.setSize(600, 800);
        JTextField input = new JTextField();
        JTextArea otherTexters = new JTextArea();
        JPanel drawFace = new SimpleDraw();
        chatBoot.add(input, BorderLayout.CENTER);
        chatBoot.add(drawFace, BorderLayout.SOUTH);
        
        //chatBoot.add(new SimpleDraw());
        chatBoot.add(otherTexters, BorderLayout.EAST);
        chatBoot.setVisible(true);
        input.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try{
                    oos.writeObject(input.getText());
                    oos.flush();
                }catch(Exception x){}

            }});

            while(true){
                try {
                    String message = (String)ois.readObject();
                    //add text
                   
                }
                catch(EOFException e){
                    System.out.println("the server disconnected, bye!!!");
                   
                }
                catch (Exception e){
                    System.out.println("Error on connection with: "
                           + ": " + e);
            }
            
            chatBoot.setVisible(true);
        // while(!(line = input.nextLine()).equals("disconnect")){
        //     oos.writeObject(line);
        //     oos.flush();       
        //     
        // }
        // ObjectInputStream listen;
        // socket.shutdownOutput();
        
        // System.out.println("connection closed!");
    }
}
}