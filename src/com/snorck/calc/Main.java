package com.snorck.calc;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static String toRoman(int arabicInt){
        String [] ones = new String[]{"","I","II","III","IV","V","VI","VII","VIII","IX"};
        String [] tens = new String[]{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String [] hunds = new String[]{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        String [] thous = new String[]{"","M","MM","MMM","MMMM"};

        String t = thous[arabicInt / 1000];
        String h = hunds[arabicInt / 100 % 10];
        String te = tens[arabicInt / 10 % 10];
        String o =  ones[arabicInt % 10];
        return t+h+te+o;
    }
    public static String calc(String input){

            boolean workWithRoman;
            int firstOperand;
            int secondOperand;
            int result;

            String [] operandsArray = new String[] {"+", "-", "/", "*"};
            String [] romanArray = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            String [] arabicArray = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

            String [] operands = input.split("\\s");

            //System.out.println(operands.length);
            if(operands.length != 3) try {
                throw new Exception("Неверная запись");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (!Arrays.asList(operandsArray).contains(operands[1])) try {
                throw new Exception("Неверная запись, неверный оператор");
            } catch (Exception e){
                throw new RuntimeException(e);
            }

            if(Arrays.asList(romanArray).contains(operands[0]) && Arrays.asList(romanArray).contains(operands[2])){
                //System.out.println("будем работать с римскими цифрами");
                workWithRoman = true;

            }
            else if (Arrays.asList(arabicArray).contains(operands[0]) && Arrays.asList(arabicArray).contains(operands[2])){
                //System.out.println("будем работать с арабскими цифрами");
                workWithRoman = false;
            }
            else {
                try {
                    throw new Exception("Разные операнды");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if(workWithRoman) {
                firstOperand = Integer.parseInt(arabicArray[Arrays.asList(romanArray).indexOf(operands[0])]);
                secondOperand = Integer.parseInt(arabicArray[Arrays.asList(romanArray).indexOf(operands[2])]);
            }else {
                firstOperand = Integer.parseInt(operands[0]);
                secondOperand = Integer.parseInt(operands[2]);
            }

            switch (operands[1]){
                case("+"):
                    //System.out.println("будем складывать ");
                    result = firstOperand + secondOperand;
                    if(workWithRoman) {
                        return toRoman(result);
                    }else {
                        return String.valueOf(result);
                    }

                    //break;
                case("-"):
                    //System.out.println("будем вычитать ");
                    result = firstOperand - secondOperand;
                    if((workWithRoman) && (result > 0)){
                        return toRoman(result);
                        }else if (result < 1){
                            try {
                                throw new Exception("результат меньше либо равен нулю");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        }else {
                        return String.valueOf(result);
                    }
                    //break;
                case("/"):
                    //System.out.println("будем делить ");
                    result = firstOperand / secondOperand;
                    if((workWithRoman) && (firstOperand > secondOperand)) {
                        return toRoman(result);
                    } else if (firstOperand < secondOperand){
                            try {
                                throw new Exception("результат меньше единицы");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        } else {
                        return String.valueOf(result);
                    }
                    //break;
                case("*"):
                    //System.out.println("будем умножать ");
                    result = firstOperand * secondOperand;
                    if(workWithRoman) {
                        return toRoman(result);
                    }else {
                        return String.valueOf(result);
                    }
                    //break;
                default:
                    try {
                        throw new Exception("Неверный оператор");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
            }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите пример: ");
        String userInput = sc.nextLine();
        System.out.println(calc(userInput));
    }
}