package byog.Core;

import edu.princeton.cs.introcs.Draw;
import edu.princeton.cs.introcs.StdDraw;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

import static org.junit.Assert.*;

public class RoomTest {

    private static final int CANVAS_WIDTH = 80;
    private static final int CANVAS_HEIGHT = 30;

    @Test
    public void testRandRoomParameters() {
        Random radObject = new Random();
        for (int i = 0; i < 10000; i++) {
            int[] tt = Room.randRoomParameters(radObject, CANVAS_WIDTH);// randPosXY, randLength
            Assert.assertTrue(tt[0] + tt[1] < CANVAS_WIDTH);
        }
    }

    @Test
    public void testGenerateRoom() {
        for (int i = 0; i < 1000; i++) {
            Room getRoom = Room.generateRoom(CANVAS_WIDTH, CANVAS_HEIGHT);
            assertTrue(getRoom.rightUpCorner.x < CANVAS_WIDTH);
        }
    }

    @Test
    public void testInsideTri() {
        Room room1 = new Room(new Position(9, 9), 3, 3);
        Room room2 = new Room(new Position(19, 19), 3, 3);
        Room room3 = new Room(new Position(29, 9), 3, 3);
        Room roomTest = new Room(new Position(20, 12), 3, 3);
        Assert.assertTrue(roomTest.insideTri(room1, room2, room3));
    }

    @Test
    public void testEquals() {
        Room room1 = new Room(new Position(9, 9), 3, 3);
        Room room2 = new Room(new Position(9, 9), 3, 3);
        Room room3 = new Room(new Position(19, 19), 3, 3);
        assertTrue(room1.equals(room2));
        assertFalse(room1.equals(room3));
        assertFalse(room2.equals(room3));
    }

    public static void main(String[] args) {
        StdDraw.setCanvasSize(80 * 16, 30 * 16);
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(new Position(9, 9), 3, 3));
        rooms.add(new Room(new Position(19, 19), 3, 3));
        rooms.add(new Room(new Position(29, 9), 3, 3));
        rooms.add(new Room(new Position(20, 12), 3, 3));
        Room.delaunayEdges(rooms);

    }
}

