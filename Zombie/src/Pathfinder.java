import java.util.ArrayList;
import java.util.List;

public class Pathfinder {
    


    List<Tile> openSet = new ArrayList<Tile>();
    List<Tile> closedSet = new ArrayList<Tile>();
    Tile targetTile;
    Tile startTile;
    


    public void pathFinding() {
        openSet.add(startTile);

        while(!openSet.isEmpty()) {
            Tile currentTile = openSet.get(0);
            
            for (int i = 1; i< openSet.size(); i++) {
                if(openSet.get(i).fCost() < currentTile.fCost() || openSet.get(i).fCost() == currentTile.fCost() && openSet.get(i).hCost < currentTile.hCost) {
                    currentTile = openSet.get(i);
                }
            }
            openSet.remove(currentTile);
            closedSet.add(currentTile);
            if (currentTile == targetTile) {
                retracePath(startTile, targetTile);
                return;
            }
            for (Tile neighbour : getNeighbours()) {
                if(!neighbour.isSolid() || closedSet.contains(neighbour)) {
                    continue;
                }
                int newMovementCostToNeighbour = currentTile.gCost + getDistance(currentTile, neighbour);
                if(newMovementCostToNeighbour < neighbour.gCost || !openSet.contains(neighbour)) {
                    neighbour.gCost = newMovementCostToNeighbour;
                    neighbour.hCost = getDistance(neighbour, targetTile);
                    //neighbour.parent = currentTile;
                    if(!openSet.contains(neighbour)) {
                        openSet.add(neighbour);
                    }
                }
            }
        }
    }

    private void retracePath(Tile startTile, Tile endTile) {
        List<Tile> path = new ArrayList<Tile>();
        Tile currentTile = targetTile;
        while(currentTile != startTile) {
            path.add(currentTile);
            //currentTile = currentTile.parent;//
        }
        //path.reverse;
    }


    private int getDistance(Tile A, Tile B) {
        int dstX = Math.abs(A.getX() - B.getX());
        int dstY = Math.abs(A.getY() - B.getY());
        if(dstX > dstY) {
            return 14*dstY + 10*(dstX-dstY);
        }
        return 14*dstX + 10 * (dstY - dstX);
    }
    private List<Tile> getNeighbours() {
        List<Tile> neighbours = new ArrayList<Tile>();
        Tile target = new Tile();
        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y <= 1; y++) {
                if(x == 0 && y == 0) {
                    continue;
                }
                int checkX = 2;
                int checkY = 2;
                int size = 2;
                if(checkX >= 0 && checkX < size && checkY >= 0 && checkY < size) {
                    neighbours.add(target);
                }
            }
        }
        
        
        
        return neighbours; 
    }    
}
