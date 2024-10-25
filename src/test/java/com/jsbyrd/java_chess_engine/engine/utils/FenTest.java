package com.jsbyrd.java_chess_engine.engine.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FenTest {

    @Test
    void convertFenToCharArray_StartingPosition() {
        String startingFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        String[][] result = Fen.convertFenToCharArray(startingFen);

        // Verify black pieces (first row)
        assertEquals("r", result[0][0]);
        assertEquals("n", result[0][1]);
        assertEquals("b", result[0][2]);
        assertEquals("q", result[0][3]);
        assertEquals("k", result[0][4]);
        assertEquals("b", result[0][5]);
        assertEquals("n", result[0][6]);
        assertEquals("r", result[0][7]);

        // Verify black pawns (second row)
        for (int i = 0; i < 8; i++) {
            assertEquals("p", result[1][i]);
        }

        // Verify empty squares (middle rows)
        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                assertNull(result[row][col]);
            }
        }

        // Verify white pawns (seventh row)
        for (int i = 0; i < 8; i++) {
            assertEquals("P", result[6][i]);
        }

        // Verify white pieces (last row)
        assertEquals("R", result[7][0]);
        assertEquals("N", result[7][1]);
        assertEquals("B", result[7][2]);
        assertEquals("Q", result[7][3]);
        assertEquals("K", result[7][4]);
        assertEquals("B", result[7][5]);
        assertEquals("N", result[7][6]);
        assertEquals("R", result[7][7]);
    }

    @Test
    void convertFenToCharArray_MidGamePosition() {
        String midGameFen = "r3k2r/ppp2ppp/2n2n2/2b1p3/4P3/2N2N2/PPP2PPP/R3K2R";
        String[][] result = Fen.convertFenToCharArray(midGameFen);

        // Test specific positions
        assertEquals("r", result[0][0]);
        assertNull(result[0][1]);
        assertNull(result[0][2]);
        assertEquals("k", result[0][4]);
        assertEquals("r", result[0][7]);

        assertEquals("n", result[2][2]);
        assertEquals("n", result[2][5]);

        assertEquals("b", result[3][2]);

        assertEquals("P", result[6][0]);
        assertEquals("P", result[6][6]);
    }

    @Test
    void convertFenToCharArray_EmptyBoard() {
        String emptyBoardFen = "8/8/8/8/8/8/8/8";
        String[][] result = Fen.convertFenToCharArray(emptyBoardFen);

        // Verify all squares are null
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                assertNull(result[row][col]);
            }
        }
    }

    @Test
    void convertFenToCharArray_SinglePiece() {
        String singlePieceFen = "8/8/8/8/4K3/8/8/8";
        String[][] result = Fen.convertFenToCharArray(singlePieceFen);

        // Verify the single king position
        assertEquals("K", result[4][4]);

        // Verify all other squares are null
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row != 4 || col != 4) {
                    assertNull(result[row][col]);
                }
            }
        }
    }

    @Test
    void convertFenToCharArray_FullFen() {
        // Test with FEN string that includes move information
        String fenWithMoveInfo = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        String[][] result = Fen.convertFenToCharArray(fenWithMoveInfo);

        // Verify black pieces (first row)
        assertEquals("r", result[0][0]);
        assertEquals("n", result[0][1]);
        assertEquals("b", result[0][2]);
        assertEquals("q", result[0][3]);
        assertEquals("k", result[0][4]);
        assertEquals("b", result[0][5]);
        assertEquals("n", result[0][6]);
        assertEquals("r", result[0][7]);

        // Verify black pawns (second row)
        for (int i = 0; i < 8; i++) {
            assertEquals("p", result[1][i]);
        }

        // Verify empty squares (middle rows)
        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                assertNull(result[row][col]);
            }
        }

        // Verify white pawns (seventh row)
        for (int i = 0; i < 8; i++) {
            assertEquals("P", result[6][i]);
        }

        // Verify white pieces (last row)
        assertEquals("R", result[7][0]);
        assertEquals("N", result[7][1]);
        assertEquals("B", result[7][2]);
        assertEquals("Q", result[7][3]);
        assertEquals("K", result[7][4]);
        assertEquals("B", result[7][5]);
        assertEquals("N", result[7][6]);
        assertEquals("R", result[7][7]);
    }
}