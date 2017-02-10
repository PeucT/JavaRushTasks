package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
*/
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            //File your_file_name = File.createTempFile("D:\\JavaProjects\\IO\\temp.txt", null);
            File your_file_name = new File("D:\\JavaProjects\\IO\\temp.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            if (somePerson.equals(ivanov)) {
                System.out.println("Everything is ok");
            } else {
                System.out.println("Objects are not identical");
            }
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            if (name != null) {
                writer.write("yes");
                writer.write("\r\n");
                writer.write(name);
                writer.write("\r\n");
            } else {
                writer.write("no");
                writer.write("\r\n");
            }

            if (assets.size() != 0)
            {
                writer.write("yes");
            }

            else {
                writer.write("no");
            }

            for (Asset entry: assets)
            {
                writer.write("\r\n");
                writer.write(entry.getName());
                writer.write("\r\n");
                writer.write(String.valueOf(entry.getPrice()));
            }
            writer.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            if ("yes".equals(reader.readLine())) this.name = reader.readLine();
            else this.name = null;
            if ("yes".equals(reader.readLine()))
            {
                while (true)
                {
                    String name = reader.readLine();

                    if (name == null) break;
                    Double price = Double.parseDouble(reader.readLine());
                    Asset asset = new Asset(name);
                    asset.setPrice(price);
                    this.assets.add(asset);
                }
            }

        }
    }
}
