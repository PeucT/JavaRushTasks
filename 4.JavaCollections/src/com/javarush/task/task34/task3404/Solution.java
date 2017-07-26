package com.javarush.task.task34.task3404;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //solution.recursion("sin(2*(-5+1.5*4)+28)", 0);
        //solution.recursion("sin(2*(-5+1.5*4 + 3^3)+28)+cos(4) ", 0); //expected output 0.5 6
        //solution.recursion("2*(-5+1.5*4 + 3^3)+(28 +5)*2 ", 0); //expected output 0.5 6
        //Double x = 2*(-5+1.5*4 + 27)+(28 +5)*2;
        //System.out.println();
        //System.out.println(x);
        String s = "28^sin(1-98098^0)";
        /*System.out.print(s + " expected output 1 4 actually ");
        solution.recursion(s, 0);
        System.out.println();*/

        /*s="(-2)^(-2)";
        System.out.print(s + " expected output 0.25 3 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "tan(44+sin(89-cos(180)^2))";
        System.out.print(s + " expected output 1 6 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s="89-cos(180)^2";
        System.out.print(s + " expected output 88 3 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output 0.5 6 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "tan(45)";
        System.out.print(s + " expected output 1 1 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "tan(-45)";
        System.out.print(s + " expected output -1 2 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "0.305";
        System.out.print(s + " expected output 0.3 0 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "0.3051";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "(0.3051)";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
        System.out.print(s + " expected output 12 8 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "tan(44+sin(89-cos(180)^2))";
        System.out.print(s + " expected output 1 6 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "-2+(-2+(-2)-2*(2+2))";
        System.out.print(s + " expected output -14 8 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "sin(80+(2+(1+1))*(1+1)+2)";
        System.out.print(s + " expected output 1 7 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
        System.out.print(s + " expected output 6 11 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "2^10+2^(5+5)";
        System.out.print(s + " expected output 2048 4 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1";
        System.out.print(s + " expected output 72.96 8 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "0.000025+0.000012";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)";
        System.out.print(s + " expected output -3 16 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "cos(3 + 19*3)";
        System.out.print(s + " expected output 0.5 3 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)";
        System.out.print(s + " expected output 8302231.36 14 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "(-1 + (-2))";
        System.out.print(s + " expected output -3 3 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "-sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output -0.5 7 actually ");
        solution.recursion(s, 0);
        System.out.println();


        s = "sin(100)-sin(100)";
        System.out.print(s + " expected output 0 3 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "-(-22+22*2)";
        System.out.print(s + " expected output -22 4 actually ");
        solution.recursion(s, 0);
        System.out.println();*/

        s = "-2^(-2)";
        System.out.print(s + " expected output -0.25 3 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "-(-2^(-2))+2+(-(-2^(-2)))";
        System.out.print(s + " expected output 2.5 10 actually ");
        solution.recursion(s, 0);
        System.out.println();

        /*s = "(-2)*(-2)";
        System.out.print(s + " expected output 4 3 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "(-2)/(-2)";
        System.out.print(s + " expected output 1 3 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "sin(-30)";
        System.out.print(s + " expected output -0.5 2 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "cos(-30)";
        System.out.print(s + " expected output 0.87 2 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "tan(-30)";
        System.out.print(s + " expected output -0.58 2 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "2+8*(9/4-1.5)^(1+1)";
        System.out.print(s + " expected output 6.5 6 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "0.005 ";
        System.out.print(s + " expected output 0.01 0 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "0.0049 ";
        System.out.print(s + " expected output 0 0 actually ");
        solution.recursion(s, 0);
        System.out.println();

        s = "0+0.304";
        System.out.print(s + " expected output 0.3 1 actually ");
        solution.recursion(s, 0);*/
    }

    public void recursion(final String expression, int countOperation) {
        //System.out.println(expression);
        Locale.setDefault(Locale.ENGLISH);

        String handledExpression = "";
        /*if (!expression.contains("(")) {
            handledExpression = new String(expression);
        }*/
        boolean isFirstEntry = countOperation == 0;
        int countOperForOutput = 0;

        // Считаем операции сразу же, при первом входе в рекурсию.
        // Все остальные обращения к countOperation - старая реализация

        if (isFirstEntry) {
            Pattern pattern = Pattern.compile("[\\+\\-\\*\\^\\/]");
            Pattern patternSin = Pattern.compile("sin");
            Pattern patternCos = Pattern.compile("cos");
            Pattern patternTan = Pattern.compile("tan");
            Matcher match = pattern.matcher(expression);
            while (match.find()) {
                countOperation +=1;
            }

            Matcher matchSin = patternSin.matcher(expression);
            while (matchSin.find()){
                countOperation +=1;
            }

            Matcher matchCos = patternCos.matcher(expression);
            while (matchCos.find()){
                countOperation +=1;
            }

            Matcher matchTan = patternTan.matcher(expression);
            while (matchTan.find()){
                countOperation +=1;
            }

            countOperForOutput = countOperation;
        }

        int handledExpressionStartIndex = 0;
        for (int i = 0; i < expression.length(); i ++){
            if (String.valueOf(expression.charAt(i)).equals("(")) {
                /*handledExpression = "";*/
                int newExpressionStartIndex = i + 1;
                int parenthesisCounter = 1;
                //int lengthCounter = i;
                i++;
                while ( (parenthesisCounter != 0) && (i < expression.length() )) {
                    if (String.valueOf(expression.charAt(i)).equals("(")) {
                        parenthesisCounter += 1;
                    }else if (String.valueOf(expression.charAt(i)).equals(")")) {
                        parenthesisCounter -= 1;
                    }
                    i++;
                }

                Double result = 0.0;
                /*try (
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        ) {
                    result = Double.valueOf(reader.readLine());
                } catch (IOException e) {
                }*/
                PrintStream oldOut = System.out;
                ByteArrayOutputStream outArray = new ByteArrayOutputStream();
                PrintStream out = new PrintStream(outArray);
                System.setOut(out);

                recursion(expression.substring(newExpressionStartIndex, i - 1), countOperation);
                System.setOut(oldOut);
                result = Double.valueOf(outArray.toString().substring(0, outArray.toString().indexOf(" ")));
                countOperation = Integer.valueOf(outArray.toString().substring(outArray.toString().indexOf(" ") + 1));

                handledExpression = handledExpression + expression.substring(handledExpressionStartIndex, newExpressionStartIndex - 1) +
                    new BigDecimal(result).setScale(4, RoundingMode.HALF_UP).toString();
                    /*expression.substring(i);*/
                handledExpressionStartIndex = i;
                int j = 0;
            }
        }
        handledExpression = handledExpression + expression.substring(handledExpressionStartIndex);
        if (!handledExpression.contains("(") && !handledExpression.contains(")")) {
            int[] forCountOperRet = new int[1];
            forCountOperRet[0] = countOperation;
            Double result = letsCountExpression(handledExpression, forCountOperRet);
            /*byte[] byteArray = new byte[1];
            byteArray[0] = result.byteValue();
            ByteArrayInputStream newIn = new ByteArrayInputStream(byteArray);
            System.setIn(newIn);*/
            countOperation = forCountOperRet[0];

            DecimalFormat df = isFirstEntry ? new DecimalFormat("#.##") : new DecimalFormat("#.######");

            System.out.format("%s %d", df.format(result), countOperForOutput);

            /*ByteArrayOutputStream outArray = new ByteArrayOutputStream();
            outArray.write(result.byteValue());
            PrintStream out = new PrintStream(outArray);
            System.setOut(out);*/

        }
        //implement
    }

    public double letsCountExpression(String expression, int[] countOperations){
        ArrayList<Double> numbers = new ArrayList<Double>();
        ArrayList<String> almostNumbers = new ArrayList<String>();
        ArrayList<String> operations = new ArrayList<>();
        expression = expression.replaceAll(" ", "");
        int startIndex = 0;
        /*if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }*/

        for (int i = 1; i < expression.length(); i++) {
            if (expression.charAt(i) == '+'
                    || expression.charAt(i) == '/'
                    || expression.charAt(i) == '-'
                    || expression.charAt(i) == '*'
                    || expression.charAt(i) == '^') {
                if (expression.charAt(i+1) == '-' ) {
                    almostNumbers.add(expression.substring(startIndex, i));
                    operations.add(String.valueOf(expression.charAt(i)));
                    startIndex = i + 1;
                    i++;
                } else if (expression.charAt(i-1) == 's' || expression.charAt(i-1) == 'n' ){

                } else {
                    almostNumbers.add(expression.substring(startIndex, i));
                    operations.add(String.valueOf(expression.charAt(i)));
                    startIndex = i + 1;
                }
            }
        }
        /*for (int i = 0; i < expression.length(); i++){
            if (expression.charAt(i) == '+'
                    || expression.charAt(i) == '-'
                    || expression.charAt(i) == '*'
                    || expression.charAt(i) == '^' ) {


                almostNumbers.add(expression.substring(startIndex, i));
                //numbers.add(Double.valueOf(expression.substring(startIndex, i)));
                operations.add(String.valueOf(expression.charAt(i)));
                startIndex = i + 1;
            }
        }*/
        almostNumbers.add(expression.substring(startIndex, expression.length()));
        //numbers.add(Double.valueOf(expression.substring(startIndex, expression.length())));

        for (int i = 0; i < almostNumbers.size(); i++){
            if (almostNumbers.get(i).contains("sin")) {
                numbers.add(Math.sin(Math.toRadians(Double.valueOf(almostNumbers.get(i).replace("sin", "")))));
                countOperations[0] += 1;
            }else if (almostNumbers.get(i).contains("cos")){
                numbers.add(Math.cos(Math.toRadians(Double.valueOf(almostNumbers.get(i).replace("cos", "")))));
                countOperations[0] += 1;
            }else if (almostNumbers.get(i).contains("tan")){
                numbers.add(Math.tan(Math.toRadians(Double.valueOf(almostNumbers.get(i).replace("tan", "")))));
                countOperations[0] += 1;
            }else {
                numbers.add(Double.valueOf(almostNumbers.get(i)));
            }
        }

        countOperations[0] += operations.size();
        if (operations.size() == 0){
            return numbers.get(0);
        } else {
            for (int i = 0; i < operations.size(); i++){
                if (operations.get(i).equals("^")){
                    numbers.add(i, Math.pow(numbers.get(i), numbers.get(i+1)));
                    numbers.remove(i + 1);
                    numbers.remove(i + 1);
                    operations.remove(i);
                    i--;
                }
            }

            for (int i = 0; i < operations.size(); i++){
                if (operations.get(i).equals("/")){
                    numbers.add(i, numbers.get(i) / numbers.get(i+1));
                    numbers.remove(i + 1);
                    numbers.remove(i + 1);
                    operations.remove(i);
                    i--;
                }
            }

            for (int i = 0; i < operations.size(); i++){
                if (operations.get(i).equals("*")){
                    numbers.add(i, numbers.get(i) * numbers.get(i+1));
                    numbers.remove(i + 1);
                    numbers.remove(i + 1);
                    operations.remove(i);
                    i--;
                }
            }

            double result = numbers.get(0);
            for (int i = 0; i < operations.size(); i++){
                if (operations.get(i).equals("+")){
                    result = result + numbers.get(i+1);
                }else {
                    result = result - numbers.get(i+1);
                }
            }
            return result;
        }
    }

    public Solution() {
        //don't delete
    }
}
