package com.javarush.task.task19.task1918;


import java.io.*;
import java.util.ArrayList;

public class Solution {
    private static ArrayList<String> list = new ArrayList<String>();

    public enum Type
    {
        OPEN,
        CLOSE;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        if (fileName.isEmpty()) System.exit(1);
        BufferedReader reader1 = new BufferedReader(new FileReader(new File(fileName)));
      //  BufferedReader reader1 = new BufferedReader(new FileReader(new File("D:\\JavaProjects\\IO\\input.txt")));
        String openTag = "";
        String closeTag = "";
        try
        {
            openTag = "<" + args[0];
            closeTag = "</" + args[0];
        }
        catch (Exception e)
        {
            System.exit(1);
        }

        String input = "";
        while (true)
        {
            String line = reader1.readLine();
            if (line == null) break;
            input += line;
        }

        reader1.close();

        ArrayList<Type> openClose = new ArrayList<Type>();
        ArrayList<Integer> indexes = new ArrayList<Integer>();

        int startIndex = 0;
        while (true)
        {
            int index1 = input.indexOf(openTag, startIndex);
            int index2 = input.indexOf(closeTag, startIndex);
            if (index1 < 0 && index2 < 0) break;
            if (index1 < 0)
            {
                openClose.add(Type.CLOSE);
                indexes.add(index2);
                startIndex = index2 + 1;
            } else if (index2 < 0)
            {
                openClose.add(Type.OPEN);
                indexes.add(index1);
                startIndex = index1 + 1;
            } else if (index1 < index2 && index1 >= 0 && index2 >= 0)
            {
                openClose.add(Type.OPEN);
                indexes.add(index1);
                startIndex = index1 + 1;
            } else if (index1 > index2 && index1 >= 0 && index2 >= 0)
            {
                openClose.add(Type.CLOSE);
                indexes.add(index2);
                startIndex = index2 + 1;
            }
        }

        startIndex = 0;

        int counter = 0;
        int delta = 0;
        while (true)

        {
            if (startIndex > openClose.size() - 2 ) break;
            if ( openClose.get(startIndex) == Type.OPEN && openClose.get(startIndex + 1) == Type.CLOSE )
            {
                if ( counter == 0 )
                {
                    list.add(input.substring(indexes.get(startIndex), indexes.get(startIndex + 1) + 3 + args[0].length()));
                    startIndex = startIndex + 2 + delta;
                    delta = 0;
                }
                else
                {
                    list.add(input.substring(indexes.get(startIndex - counter), indexes.get(startIndex + counter + 1) + 3 + args[0].length()));
                    counter--;
                }
            }
            else if (openClose.get(startIndex) == Type.OPEN && openClose.get(startIndex + 1) == Type.OPEN)
            {
                counter++;
                delta++;

                startIndex++;
            }
        }

        for (String entry : list)
        {
            System.out.println(entry);
        }
    }
}
