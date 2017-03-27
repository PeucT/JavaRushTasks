package com.javarush.task.task30.task3008.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ArchMage on 27.03.17.
 */
public class BotClient extends Client{
    public class BotSocketThread extends SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            super.processIncomingMessage(message);
            String command = null;
            String name = null;
            SimpleDateFormat format = null;
            if (message != null && message.contains(":")) {
                int index = message.indexOf(": ");
                if (index >= 0){
                    name = message.substring(0, index );
                    command = message.substring(index + 2);
                    switch (command) {
                        case "дата":
                            format = new SimpleDateFormat("d.MM.YYYY");
                            break;
                        case "день":
                            format = new SimpleDateFormat("d");
                            break;
                        case "месяц":
                            format = new SimpleDateFormat("MM");
                            break;
                        case "год":
                            format = new SimpleDateFormat("YYYY");
                            break;
                        case "время":
                            format = new SimpleDateFormat("H:mm:ss");
                            break;
                        case "час":
                            format = new SimpleDateFormat("H");
                            break;
                        case "минуты":
                            format = new SimpleDateFormat("m");
                            break;
                        case "секунды":
                            format = new SimpleDateFormat("s");
                            break;
                    }
                    Date currentDate = Calendar.getInstance().getTime();
                    if (format != null && name != null) {
                        sendTextMessage(String.format("Информация для %s: %s", name, format.format(currentDate)));
                    }
                }
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public static void main(String[] args){
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
