package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            //File your_file_name = File.createTempFile("your_file_name", null);
            File your_file_name = new File("D:\\JavaProjects\\IO\\temp.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();

            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            outputStream.flush();


            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);

            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            if (loadedObject.equals(javaRush)) {
                System.out.println("Everything is ok");
            } else {
                System.out.println("Objects are not identical");
            }

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            OutputStream writer = outputStream;

                boolean isFirst = true;

            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy" , Locale.ENGLISH);

            if (users.isEmpty()) {
                writer.write("start".getBytes());
                writer.write("\r\n".getBytes());
                writer.write("end".getBytes());
                writer.write("\r\n".getBytes());
                return;
            }

            for (User entry : users)
            {
                String firstName = entry.getFirstName();
                String lastName = entry.getLastName() ;
                Date birthDate = entry.getBirthDate();
                boolean isMale = entry.isMale();
                User.Country country = entry.getCountry();
                if (!isFirst) {
                    writer.write("\r\n".getBytes());

                } else {
                    writer.write("start".getBytes());
                    writer.write("\r\n".getBytes());
                    isFirst = false;
                }
                writer.write((firstName != null) ? firstName.getBytes() : "empty".getBytes());
                writer.write("\r\n".getBytes());
                writer.write((lastName != null) ? lastName.getBytes() : "empty".getBytes());
                writer.write("\r\n".getBytes());
                writer.write((birthDate != null) ? String.valueOf(birthDate.getTime()).getBytes() : "empty".getBytes());
                writer.write("\r\n".getBytes());
                writer.write(isMale ? "male".getBytes() : "female".getBytes());
                writer.write("\r\n".getBytes());
                writer.write((country != null) ? country.toString().getBytes() : "empty".getBytes());
            }
            writer.write("\r\n".getBytes());
            writer.write("end".getBytes());
            writer.write("\r\n".getBytes());
        }

        public void load(InputStream inputStream) throws Exception {
            DataInputStream reader = new DataInputStream(inputStream);
            int ourLines = 0;

            while (true)
            {
                String line = reader.readLine();
                if ("start".equals(line)) {
                    line = reader.readLine();
                    ourLines++;
                }
                User user = new User();
                if (ourLines == 1 && !"end".equals(line))
                {

                    user.setFirstName("empty".equals(line) ? null : line);
                    line = reader.readLine();
                    user.setLastName("empty".equals(line) ? null : line);
                    line = reader.readLine();
                    user.setBirthDate("empty".equals(line) ? null : new Date(Long.parseLong(line)));
                    line = reader.readLine();
                    user.setMale("male".equals(line) ? true : false);
                    line = reader.readLine();
                    user.setCountry("empty".equals(line) ? null : User.Country.valueOf(line));
                } else break;
                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
