package com.company;

/*
    AND - &&
    OR - ||
    NOT - !

    i++
    i--
    i -=
    I +=
    i *=
    i /=

    for (int i = 1; i <= 10; i+=2) { } --> increment by two

*/
public class Main {

    public static void main(String[] args) {
        numberMessage(rollDice());
    }

    public static void printInReverse(String[] stringArray) {
        for (int i = stringArray.length - 1; i >= 0; i--) {
            System.out.println(stringArray[i]); }
    }

    public static int indexOfFirstOccurrence(String[] stringArray, String target) {
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static String findLongestName(String [] names){
        String longestName = names[0];
        for (String name : names) {
            if (name.length() > longestName.length()) {
                longestName = name;
            }
        }
        return longestName;
    }

    public static int martingale() {
        int money = 1000;
        int target = 1200;
        int bet = 10;
        while (money > bet) {
            boolean win = play();
            if (win) {
                money += bet;
                bet = 10;
            } else {
                money -= bet;
                bet *= 2;
            }
            if(money >= target)
                break;
        }
        return money;
    }

    public static boolean play() {
        // not implemented
        return true;
    }

    public static boolean rollASix() {
        int dice = rollDice();
        while(dice!=6) {
            dice = rollDice();
            if(dice == 3) break; // roll a three = you lose
        }
        return dice == 6;
    }

    public static int rollDice() {
        double randomNumber = Math.random() * 6;
        int randomInt = (int) randomNumber + 1;
        return randomInt;
    }

    public static void numberMessage(int number) {
        switch (number) {
            case 1, 2, 3 -> System.out.println("You rolled three or less.");
            case 4, 5, 6 -> System.out.println("You rolled four or more.");
            default -> System.out.println("Invalid dice roll.");
        }
    }

    public static double studentAverage(int [][] grades, int student) {
        int total = 0;
        int subjects = grades.length;
        for (int[] grade : grades) {
            total += grade[student];
        }
        double average = total / (double) subjects;
        return average;
    }
}