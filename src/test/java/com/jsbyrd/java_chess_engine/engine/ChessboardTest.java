package com.jsbyrd.java_chess_engine.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessboardTest {

    @Test
    void constructor_StartingPosition() {
        String startingFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        Chessboard board = new Chessboard(startingFen);

        board.printAllBitboards();

        // White pieces initial positions
        assertEquals(0x24L, board.WB);  // Bishops on c1 and f1
        assertEquals(0x8L, board.WK);  // King on e1
        assertEquals(0x42L, board.WN);  // Knights on b1 and g1
        assertEquals(0xFF00L, board.WP); // Pawns on rank 2
        assertEquals(0x10L, board.WQ);   // Queen on d1
        assertEquals(0x81L, board.WR);  // Rooks on a1 and h1

        // Black pieces initial positions
        assertEquals(0x2400000000000000L, board.BB);  // Bishops on c8 and f8
        assertEquals(0x0800000000000000L, board.BK);  // King on e8
        assertEquals(0x4200000000000000L, board.BN);  // Knights on b8 and g8
        assertEquals(0xFF000000000000L, board.BP);    // Pawns on rank 7
        assertEquals(0x1000000000000000L, board.BQ);   // Queen on d8
        assertEquals(0x8100000000000000L, board.BR);  // Rooks on a8 and h8
    }

    @Test
    void constructor_EmptyBoard() {
        String emptyFen = "8/8/8/8/8/8/8/8";
        Chessboard board = new Chessboard(emptyFen);

        // Verify all bitboards are empty (0)
        assertEquals(0L, board.WB);
        assertEquals(0L, board.WK);
        assertEquals(0L, board.WN);
        assertEquals(0L, board.WP);
        assertEquals(0L, board.WQ);
        assertEquals(0L, board.WR);
        assertEquals(0L, board.BB);
        assertEquals(0L, board.BK);
        assertEquals(0L, board.BN);
        assertEquals(0L, board.BP);
        assertEquals(0L, board.BQ);
        assertEquals(0L, board.BR);
    }

    @Test
    void constructor_SinglePiece() {
        String singleKingFen = "8/8/8/8/4K3/8/8/8";
        Chessboard board = new Chessboard(singleKingFen);

        // Verify only white king is present on e4
        assertEquals(0x8000000L, board.WK);

        // Verify all other bitboards are empty
        assertEquals(0L, board.WB);
        assertEquals(0L, board.WN);
        assertEquals(0L, board.WP);
        assertEquals(0L, board.WQ);
        assertEquals(0L, board.WR);
        assertEquals(0L, board.BB);
        assertEquals(0L, board.BK);
        assertEquals(0L, board.BN);
        assertEquals(0L, board.BP);
        assertEquals(0L, board.BQ);
        assertEquals(0L, board.BR);
    }

    @Test
    void convertStringToLong_AllZeros() {
        String binaryString = "0000000000000000000000000000000000000000000000000000000000000000";
        assertEquals(0L, Chessboard.convertStringToLong(binaryString));
    }

    @Test
    void convertStringToLong_SingleBit() {
        // Test single bit in different positions
        assertEquals(1L, Chessboard.convertStringToLong("0000000000000000000000000000000000000000000000000000000000000001"));
        assertEquals(2L, Chessboard.convertStringToLong("0000000000000000000000000000000000000000000000000000000000000010"));
        assertEquals(0x8000000000000000L, Chessboard.convertStringToLong("1000000000000000000000000000000000000000000000000000000000000000"));
    }

    @Test
    void convertStringToLong_MultipleBits() {
        // Test multiple bits set
        assertEquals(0xFF00L, Chessboard.convertStringToLong("0000000000000000000000000000000000000000000000001111111100000000")); // Second rank pawns
        assertEquals(0x42L, Chessboard.convertStringToLong("0000000000000000000000000000000000000000000000000000000001000010")); // Knights on b1 and g1
    }

    @Test
    void convertArrayToBitboards_MidGamePosition() {
        String[][] midGamePosition = new String[8][8];
        // Set up a specific mid-game position
        midGamePosition[4][4] = "K"; // White king on e4
        midGamePosition[0][4] = "k"; // Black king on e8
        midGamePosition[3][3] = "P"; // White pawn on d5
        midGamePosition[3][5] = "b"; // Black bishop on f5

        Chessboard board = new Chessboard("4k3/8/8/3P1b2/4K3/8/8/8");

        // Verify piece positions
        assertEquals(0x8000000L, board.WK); // White king on e4
        assertEquals(0x0800000000000000L, board.BK); // Black king on e8
        assertEquals(0x0000001000000000L, board.WP); // White pawn on d5
        assertEquals(0x0000000400000000L, board.BB); // Black bishop on f5

        // Verify all other bitboards are empty
        assertEquals(0L, board.WB);
        assertEquals(0L, board.WN);
        assertEquals(0L, board.WQ);
        assertEquals(0L, board.WR);
        assertEquals(0L, board.BN);
        assertEquals(0L, board.BP);
        assertEquals(0L, board.BQ);
        assertEquals(0L, board.BR);
    }
}