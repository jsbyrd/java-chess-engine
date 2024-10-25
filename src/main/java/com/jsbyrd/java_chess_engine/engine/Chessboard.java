package com.jsbyrd.java_chess_engine.engine;

import com.jsbyrd.java_chess_engine.engine.utils.Fen;

public class Chessboard {
    public long WB, WK, WN, WP, WQ, WR; // Bitboards for white pieces
    public long BB, BK, BN, BP, BQ, BR; // Bitboards for black pieces

    public Chessboard(String fen) {
        String[][] fenStringArray = Fen.convertFenToCharArray(fen);
        // Convert array to bitboards
        convertArrayToBitboards(fenStringArray);
    }

    public void convertArrayToBitboards(String[][] fenStringArray) {
        String binaryString;
        for (int i = 0; i < 64; i++) {
            binaryString = "0000000000000000000000000000000000000000000000000000000000000000";
            binaryString = binaryString.substring(0, i) + "1" + binaryString.substring(i + 1);
            int rank = i / 8;
            int file = i % 8;
            if (fenStringArray[rank][file] == null) continue;
//            System.out.println("(i: " + i + ", piece: " + fenStringArray[rank][file] + "), " + binaryString);
            switch (fenStringArray[rank][file]) {
                case "B":
                    WB = WB | convertStringToLong(binaryString);
                    break;
                case "K":
                    WK = WK | convertStringToLong(binaryString);
                    break;
                case "N":
                    WN = WN | convertStringToLong(binaryString);
                    break;
                case "P":
                    WP = WP | convertStringToLong(binaryString);
                    break;
                case "Q":
                    WQ = WQ | convertStringToLong(binaryString);
                    break;
                case "R":
                    WR = WR | convertStringToLong(binaryString);
                    break;
                case "b":
                    BB = BB | convertStringToLong(binaryString);
                    break;
                case "k":
                    BK = BK | convertStringToLong(binaryString);
                    break;
                case "n":
                    BN = BN | convertStringToLong(binaryString);
                    break;
                case "p":
                    BP = BP | convertStringToLong(binaryString);
                    break;
                case "q":
                    BQ = BQ | convertStringToLong(binaryString);
                    break;
                case "r":
                    BR = BR | convertStringToLong(binaryString);
                    break;
                default:
                    break;
            }
        }
    }

    public static long convertStringToLong(String binaryString) {
        if (binaryString.charAt(0) == '0') {
            return Long.parseLong(binaryString, 2);
        }
        // Long.parseLong doesn't work for "10000...00000" for some reason, maybe due to two's complement issues? No clue,
        // but this does fix the issue
        else {
            return Long.parseLong("1" + binaryString.substring(2), 2) * 2;
        }
    }

    public void printAllBitboards() {
        System.out.println("White Bishops: " + WB);
        System.out.println("White Kings: " + WK);
        System.out.println("White Knights: " + WN);
        System.out.println("White Pawns: " + WP);
        System.out.println("White Queens: " + WQ);
        System.out.println("White Rooks: " + WR);
        System.out.println("Black Bishops: " + BB);
        System.out.println("Black Kings: " + BK);
        System.out.println("Black Knights: " + BN);
        System.out.println("Black Pawns: " + BP);
        System.out.println("Black Queens: " + BQ);
        System.out.println("Black Rooks: " + BR);
    }
}
