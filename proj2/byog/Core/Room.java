package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;


public class Room {
    private static final int CANVAS_WIDTH = 80;
    private static final int CANVAS_HEIGHT = 30;

    private static final long SEED = 2873;
    private static final Random RANDOM = new Random(SEED);

    public Position leftDownCorner;
    public int width;
    public int height;

    public Position roomCenter; // 边长不一定被2整除，可能有误差
    public Position rightUpCorner;

    public int roomID;

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
        for (int x = this.leftDownCorner.x; x <= this.rightUpCorner.x; x++) {
            world[x][leftDownCorner.y] = Tileset.WALL;
            world[x][rightUpCorner.y] = Tileset.WALL;
        }

        // draw vertical wall
        for (int y = this.leftDownCorner.y + 1; y <= this.rightUpCorner.y - 1; y++) {
            world[leftDownCorner.x][y] = Tileset.WALL;
            world[rightUpCorner.x][y] = Tileset.WALL;
        }

        // draw floor
        for (int x = this.leftDownCorner.x + 1; x <= this.rightUpCorner.x - 1; x++) {
            for (int y = this.leftDownCorner.y + 1; y <= this.rightUpCorner.y - 1; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }

        // draw center
        world[this.roomCenter.x][this.roomCenter.y] = Tileset.SAND;
    }

    /**
     * get a random number from 0 to range
     *
     * @return
     */
    public static int getIntGaussian(Random random, int range) {
        return (int) Math.abs(Math.round(RandomUtils.gaussian(random, 0.5 * range, 0.1 * range)));
    }

    /**
     * Generate room object
     *
     * @param canvasWidth
     * @param canvasHeight
     * @return
     */
    public static Room generateRoom(int canvasWidth, int canvasHeight) {
        // change RANDOM to radObject
        // Random radObject = new Random(SEED);
        int[] XBuffer = randRoomParameters(RANDOM, canvasWidth);
        int randPosX = XBuffer[0];
        int randWid = XBuffer[1];
        int[] YBuffer = randRoomParameters(RANDOM, canvasHeight);
        int randPosY = YBuffer[0];
        int randHei = YBuffer[1];

        Position randLDCorner = new Position(randPosX, randPosY);
        return new Room(randLDCorner, randWid, randHei);
    }

    public static int[] randRoomParameters(Random radObject, int canvasLength) {
        int randPosXY = getIntGaussian(radObject, canvasLength);
        int randLength;
        // check width be in canvas
        do {
            // randomly generate room width >= 3, to ensure all room have floor
            randLength = radObject.nextInt((int) Math.round(0.2 * canvasLength)) + 3;
        } while (randPosXY + randLength >= canvasLength);

        return new int[]{randPosXY, randLength};
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

    /**
     * check if this room is overlap with rooms in the list
     * @param roomList
     * @return
     */
    public boolean isListOverlap(ArrayList<Room> roomList) {
        for (int i = 0; i < roomList.size(); i++) {
            if (this.isRoomOverlap(roomList.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * find if this.roomcenter is in the given triangle
     *
     * @param room1 Room center 1
     * @param room2 Room center 2
     * @param room3 Room center 3
     * @return
     */
    public boolean insideTri(Room room1, Room room2, Room room3) {
        double triArea = Position.triArea(room1.roomCenter, room2.roomCenter, room3.roomCenter);
        double subArea1 = Position.triArea(this.roomCenter, room2.roomCenter, room3.roomCenter);
        double subArea2 = Position.triArea(room1.roomCenter, this.roomCenter, room3.roomCenter);
        double subArea3 = Position.triArea(room1.roomCenter, room2.roomCenter, this.roomCenter);
        if (triArea == subArea1 + subArea2 + subArea3) {
            return true;
        }
        return false;
    }

    public static boolean inEdgeStorage(ArrayList<int[]> edges, int[] addEdge) {
        for (int i = 0; i < edges.size(); i++) {
            if (addEdge[0] == edges.get(i)[0] &&
                    addEdge[1] == edges.get(i)[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Delaunay edges
     */
    public static ArrayList<int[]> delaunayEdges(ArrayList<Room> roomList) {
        // todo this method is flawed
        // use someones else's https://github.com/jdiemke/delaunay-triangulator#how-to-use
        // https://github.com/themadcreator/delaunay
        int N = roomList.size();
        ArrayList<int[]> edgeStorage = new ArrayList<int[]>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    boolean isTriangle = true;
                    for (int a = 0; a < N; a++) {
                        if (a == i || a == j || a == k) continue;
                        // if (points[a].inside(points[i], points[j], points[k])) {
                        if (roomList.get(a).insideTri(roomList.get(i), roomList.get(j), roomList.get(k))) {
                            isTriangle = false;
                            break;
                        }
                    }
                    if (isTriangle) {
                        System.out.println("-------delaunay triangle found");
                        // points[i].drawTo(points[j], draw);
                        // points[i].drawTo(points[k], draw);
                        // points[j].drawTo(points[k], draw);

                        // save Delaunay triangle edges
                        int buffArray1[] = {i, j};
                        int buffArray2[] = {i, k};
                        int buffArray3[] = {j, k};
                        // there will be some duplicity, check 'contain' before add edge?
                        // TODO 'contains' method use == rather than .equals, use other method to delete duplicate
                        if (!inEdgeStorage(edgeStorage, buffArray1)) {
                            edgeStorage.add(buffArray1);
                        }
                        if (!inEdgeStorage(edgeStorage, buffArray2)) {
                            edgeStorage.add(buffArray2);
                        }
                        if (!inEdgeStorage(edgeStorage, buffArray3)) {
                            edgeStorage.add(buffArray3);
                        }
                    }
                }
            }
        }
        return edgeStorage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;
        if (width != room.width) return false;
        if (height != room.height) return false;
        if (!leftDownCorner.equals(room.leftDownCorner)) return false;
        if (!roomCenter.equals(room.roomCenter)) return false;
        return rightUpCorner.equals(room.rightUpCorner);
    }

    @Override
    public int hashCode() {
        int result = leftDownCorner.hashCode();
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + roomCenter.hashCode();
        result = 31 * result + rightUpCorner.hashCode();
        return result;
    }

    public int distance(Room testRoom) {
        return this.roomCenter.distance(testRoom.roomCenter);
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(CANVAS_WIDTH, CANVAS_HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[CANVAS_WIDTH][CANVAS_HEIGHT];
        for (int x = 0; x < CANVAS_WIDTH; x += 1) {
            for (int y = 0; y < CANVAS_HEIGHT; y += 1) {
                world[x][y] = Tileset.FLOOR;
            }
        }

        // fill with rooms
        ArrayList<Room> roomArrayList = new ArrayList<>();
//        Position pos1 = new Position(2, 4);
//        Room room1 = new Room(pos1, 5, 8);
//        roomArrayList.add(room1);

        int roomCount = 0;
        while (roomArrayList.size() < 5) {
            Room roomBuffer = generateRoom(CANVAS_WIDTH, CANVAS_HEIGHT);
            if (roomBuffer.isListOverlap(roomArrayList)) {
                continue;
            }
            roomBuffer.roomID = roomCount++;
            roomArrayList.add(roomBuffer);
        }

        for (int i = 0; i < roomArrayList.size(); i++) {
            roomArrayList.get(i).drawRoom(world);
        }

        // delaunayEdges
        ArrayList<int[]> edges = delaunayEdges(roomArrayList);

        // draws the world to the screen
        ter.renderFrame(world);

        // Minimum spanning tree
        int vertices = roomArrayList.size();
        KrushkalMST.Graph graph = new KrushkalMST.Graph(vertices);
        for (int i = 0; i < vertices; i++) {
            graph.addEgde(edges.get(i)[0], edges.get(i)[1], roomArrayList.get(edges.get(i)[0]).distance(roomArrayList.get(edges.get(i)[1])));
        }
        graph.kruskalMST();

    }
}
