import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Spliterator;


public class calcu_exp7 {
    public static void main(String[] args) {
        Convert convertik = new Convert();
        String[] znaki = {"+", "-", "/", "*"};
        String[] regZnaki = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String vvod = scn.nextLine();
        int actInd = -1;
        for (int i = 0; i < znaki.length; i++) {
            if (vvod.contains(znaki[i])) {
                actInd = i;
                break;
            }
        }
        if (actInd == -1) {
            System.out.println("строка не является математической операцией");
            return;
        }
        int countPlus=0;
        int countMinus=0;
        int countUmnoj=0;
        int countDelenie=0;
        for (char element: vvod.toCharArray()){
            if (element == '+') countPlus++;
            if (element == '-') countMinus++;
            if (element == '/') countDelenie++;
            if (element == '*') countUmnoj++;
        }
        String[] da = vvod.split(regZnaki[actInd]);
        if (convertik.romanNumb(da[0]) == convertik.romanNumb(da[1])) {
            int a, b;
            int[] numbs = new int[2];
            int counter = 0;
            boolean romanNumb = convertik.romanNumb(da[0]);
            if (romanNumb) {
                a = convertik.romanToInt(da[0]);
                b = convertik.romanToInt(da[1]);
            } else {
                a = Integer.parseInt(da[0]);
                b = Integer.parseInt(da[1]);
            }
            boolean shouldRunOnce = true;
            while (shouldRunOnce) {
                if (a > 10 || b > 10) {
                    try {
                        throw new IOException();
                    } catch (IOException e) {
                        System.out.println("Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.");
                    }
                    shouldRunOnce = false;
                } else {
                    int result;
                    switch (znaki[actInd]) {
                        case "+":
                            result = a + b;
                            break;
                        case "-":
                            result = a - b;
                            break;
                        case "/":
                            result = a / b;
                            break;
                        case "*":
                            result = a * b;
                            break;
                        default:
                            String Error = "формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
                            result = Integer.parseInt(Error);
                            break;

                    }
                    try {
                        if (result == 0) throw new Exception("в римской системе нет нуля");
                        if (result < 0) throw new Exception("в римской системе нет отрицательных чисел");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                    try {
                        if (countMinus > 1 || countDelenie > 1 || countPlus >1 || countUmnoj >1) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        return;
                    }

                    shouldRunOnce = false;
                    if (romanNumb) {
                        System.out.println(convertik.intRoman(result));
                        shouldRunOnce = false;

                    } else {
                        System.out.println(result);
                        shouldRunOnce = false;
                    }
                }
            }
        } else {
            System.out.println("используются одновременно разные системы счисления");
        }
    }
}

