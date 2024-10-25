package com.jsbyrd.java_chess_engine.engine.utils;

public class Fen {
    public static String[][] convertFenToCharArray(String fen) {
        String[][] fenStringArray = new String[8][8];
        int x = 0;
        int y = 0;

        // Piece Placement
        for (int i = 0; i < fen.length(); i++) {
            char c = fen.charAt(i);
            if (c == ' ') {
                break;
            }
            if (c == '/') {
                x = 0;
                y++;
                continue;
            }
            if (Character.isDigit(c)) {
                int digit = Character.getNumericValue(c);
                x += digit - 1;
            } else {
                fenStringArray[y][x] = String.valueOf(c);
            }
            x++;
        }
        return fenStringArray;
    }
}
