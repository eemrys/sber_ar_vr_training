package com.company;

public class Main {

    public static void main(String[] args) {

    }

    public void printInReverse(String[] stringArray) {
        for (int i = stringArray.length - 1; i >= 0; i--) {
            System.out.println(stringArray[i]); }
    }

    public int indexOfFirstOccurrence(String[] stringArray, String target) {
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public String findLongestName(String [] names){
        int size = names.length;
        String longestName = names[0];
        for (int i = 0; i < size; i++) {
            if (names[i].length() > longestName.length()) {
                longestName = names[i];
            }
        }
        return longestName;
    }

    public int martingale() {
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
            // Add the break here:
            if(money >= target)
                break;
        }
        return money;
    }

    private boolean play() {
        // not implemented
        return true;
    }
}
