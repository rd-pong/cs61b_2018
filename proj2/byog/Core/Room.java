package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;


public class Room {
    private static final int CANVAS_WIDTH = 80;
    private static final int CANVAS_HEIGHT = 30;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public Position leftDownCorner;
    public int width;
    public int height;

    public Position roomCenter; // 边长不一定被2整除，可能有误差
    public Position rightUpCorner;

    /**
     * Constructor for Room
     */
    public Room(Position ldc, int w, int h) {
        this.leftDownCorner = ldc;
        this.width = w;
        this.height = h;

        this.roomCenter = new Position(this.leftDownCorner.x + Math.round(this.width / 2), this.leftDownCorner.y + Math.round(this.height / 2));
        this.rightUpCorner = new Position(this.leftDownCorner.x + this.width - 1, this.leftDownCorner.y + this.height - 1);
    }

    /**
     * add a room to canvas after checking overlap
     * #############
     * #...........#
     * #...floor...# wall
     * #...........#
     * #############
     */
    public void drawRoom(TETile[][] world) {
        // draw horizontal wall
        for (int x = this.leftDownCorner.x; x < this.rightUpCorner.x + 1; x++) {
            world[x][leftDownCorner.y] = Tileset.WALL;
            world[x][rightUpCorner.y] = Tileset.WALL;
        }

        // draw vertical wall
        for (int y = this.leftDownCorner.y + 1; y < this.rightUpCorner.y; y++) {
            world[leftDownCorner.x][y] = Tileset.WALL;
            world[rightUpCorner.x][y] = Tileset.WALL;
        }

        // draw floor
        for (int x = this.leftDownCorner.x + 1; x < this.rightUpCorner.x; x++) {
            for (int y = this.leftDownCorner.y + 1; y < this.rightUpCorner.y; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

    /**
     * Generate room object
     *
     * @param canvasWidth
     * @param canvasHeight
     * @return
     */
    public static Room generateRoom(int canvasWidth, int canvasHeight) {
        /**
         * ####################################
         * #                                  #
         * #     ******************           #
         * #     *place room center           #
         * #     *in the middle*  *           #
         * #     ******************           #
         * #                                  #
         * ####################################
         */
        // place room center relatively to the center of the canvas
        int randPosX = (int) Math.round(Math.random() * canvasWidth);
        int randPosY = (int) Math.round(Math.random() * canvasHeight);
        Position randLDCorner = new Position(randPosX, randPosY);

        int randWid;
        int randHei;
        // check room be in canvas
        do {
            // randomly generate room width
            //randWid = RandomUtils.poisson(new Random(), 0.3 * canvasWidth);
            randWid = (int) Math.round(Math.random() * canvasWidth);
        } while (randPosX + randWid > canvasWidth - 1);
        do {
            // randomly generate room height
            // randHei = RandomUtils.poisson(new Random(), 0.3 * canvasHeight);
            randHei = (int) Math.round(Math.random() * canvasHeight);
        } while (randPosY + randHei > canvasHeight);

        return new Room(randLDCorner, randWid, randHei);
    }

    public boolean isRoomOverlap(Room roomToCheck) {
        // If one rectangle is on left side of other
        if (this.leftDownCorner.x > roomToCheck.rightUpCorner.x
                || this.rightUpCorner.x < roomToCheck.leftDownCorner.x) {
            return false;
        }

        // If one rectangle is above other
        if (this.leftDownCorner.y > roomToCheck.rightUpCorner.y
                || this.rightUpCorner.y < roomToCheck.leftDownCorner.y) {
            return false;
        }
        return true;
    }

    // TODO
    public boolean isListOverlap(ArrayList<Room> roomList) {
        for (int i = 0; i < roomList.size(); i++) {
            if (this.isRoomOverlap(roomList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(CANVAS_WIDTH, CANVAS_HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[CANVAS_WIDTH][CANVAS_HEIGHT];
        for (int x = 0; x < CANVAS_WIDTH; x += 1) {
            for (int y = 0; y < CANVAS_HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // fill with rooms
        ArrayList<Room> roomArrayList = new ArrayList<>();
        Position pos1 = new Position(2, 4);
        Room room1 = new Room(pos1, 5, 8);
        roomArrayList.add(room1);

//        for (int i = 0; i < 7; i++) {
//            Room roomBuffer = generateRoom(CANVAS_WIDTH, CANVAS_HEIGHT);
//            roomArrayList.add(roomBuffer);
//        }

        while (roomArrayList.size() < 15) {
            Room roomBuffer = generateRoom(CANVAS_WIDTH, CANVAS_HEIGHT);
            if (roomBuffer.isListOverlap(roomArrayList)) {
                continue;
            }
            roomArrayList.add(roomBuffer);
        }

        for (int i = 0; i < roomArrayList.size(); i++) {
            roomArrayList.get(i).drawRoom(world);
        }

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
