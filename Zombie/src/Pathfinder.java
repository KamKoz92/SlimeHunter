import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pathfinder {

    List<Tile> openSet;
    List<Tile> closedSet;
    Level level;
    List<Tile> path;
    Tile startTile;
    Tile targetTile;

    public Pathfinder(Level level) {
        this.level = level;
        openSet = new ArrayList<Tile>();
        closedSet = new ArrayList<Tile>();
        path = new ArrayList<Tile>();
    }

    public void pathFinding(Tile _startTile, Tile _targetTile) {
        openSet.clear();
        closedSet.clear();
        path.clear();
        startTile = _startTile;
        targetTile = _targetTile;

        openSet.add(startTile);
        while (openSet.size() > 0) {
            ///////////////////////
            // Tile currentTile = openSet.removeFirst(); heap

            Tile currentTile = openSet.get(0);


            for (int i = 1; i < openSet.size(); i++) {
                if (openSet.get(i).fCost() < currentTile.fCost()
                        || openSet.get(i).fCost() == currentTile.fCost() && openSet.get(i).hCost < currentTile.hCost) {
                    currentTile = openSet.get(i);
                }
            }
            openSet.remove(currentTile);
            closedSet.add(currentTile);
            if (currentTile == targetTile) {
                retracePath(startTile, targetTile);
                return;
            }

            for (Tile neighbour : getNeighbours(currentTile)) {
                if (neighbour.isSolid() || closedSet.contains(neighbour)) {
                    continue;
                }
                int newMovementCostToNeighbour = currentTile.gCost + getDistance(currentTile, neighbour);
                if (newMovementCostToNeighbour < neighbour.gCost || !openSet.contains(neighbour)) {
                    neighbour.gCost = newMovementCostToNeighbour;
                    neighbour.hCost = getDistance(neighbour, targetTile);
                    neighbour.pathParent = currentTile;
                    if (!openSet.contains(neighbour)) {
                        openSet.add(neighbour);
                    }
                }
            }

        }
    }

    private void retracePath(Tile startTile, Tile targetTile) {

        Tile currentTile = targetTile;
        while (currentTile != startTile) {
            path.add(currentTile);
            currentTile = currentTile.pathParent;
        }
        Collections.reverse(path);
    }

    private int getDistance(Tile A, Tile B) {
        int dstX = Math.abs(A.getX() - B.getX());
        int dstY = Math.abs(A.getY() - B.getY());
        if (dstX > dstY) {
            return 14 * dstY + 10 * (dstX - dstY);
        }
        return 14 * dstX + 10 * (dstY - dstX);
    }

    private List<Tile> getNeighbours(Tile tile) {
        List<Tile> neighbours = new ArrayList<Tile>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }
                int checkX = tile.getX() + (x * 32);
                int checkY = tile.getY() + (y * 32);
                if (checkX >= 0 && checkX < level.getWidth() && checkY >= 0 && checkY < level.getHeight()) {
                    neighbours.add(level.getTile(checkX, checkY));
                }
            }
        }
        return neighbours;
    }
}
