package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by ArchMage on 27.03.17.
 */
public class Client{
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread{
        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage("User " + userName + " is connected");
        }

        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage("User " + userName + " has left the conversation");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this){
                Client.this.notifyAll();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException{
            while (true){
                Message message = connection.receive();
                if (MessageType.NAME_REQUEST.equals(message.getType())) {
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                } else if (MessageType.NAME_ACCEPTED.equals(message.getType())) {
                    notifyConnectionStatusChanged(true);
                    break;
                } else {
                    throw new IOException("Unexpected MessageType");
                }

            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true){
                Message message = connection.receive();
                if (MessageType.TEXT.equals(message.getType())) {
                    processIncomingMessage(message.getData());
                } else if (MessageType.USER_ADDED.equals(message.getType())) {
                    informAboutAddingNewUser(message.getData());
                } else if (MessageType.USER_REMOVED.equals(message.getType())) {
                    informAboutDeletingNewUser(message.getData());
                } else {
                    throw new IOException("Unexpected MessageType");
                }

            }
        }

        public void run(){
            String ipAddress = getServerAddress();
            int port = getServerPort();
            try {
                Socket socket = new Socket(ipAddress, port);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }

    protected String getServerAddress(){
        System.out.println("Enter server ip address");
        return ConsoleHelper.readString();
    }

    protected int getServerPort(){
        System.out.println("Enter server port number");
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        System.out.println("Enter your name");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    protected void sendTextMessage(String text){
        try {
            connection.send(new Message(MessageType.TEXT ,text));
        } catch (IOException e) {
            clientConnected = false;
            ConsoleHelper.writeMessage(e.getMessage());
        }
    }

    public void run(){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this){
            try {
                wait();
                if (clientConnected) {
                    ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
                    String message = null;
                    while (clientConnected){
                        message = ConsoleHelper.readString();
                        if ("exit".equals(message)) { break; }
                        if (shouldSendTextFromConsole()) {
                            sendTextMessage(message);
                        }
                    }

                } else {
                    ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
                }
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage(e.getMessage());
                return;
            }
        }
    }


    public static void main(String[] args){
        Client client = new Client();
        client.run();
    }
}
