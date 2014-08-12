package bioroid.model.location;

import static bioroid.Constants.RESOURCE_FOLDER;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.PathFinder;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import bioroid.Constants;
import bioroid.model.ModelObject;
import bioroid.model.character.GameCharacter;
import bioroid.utils.StringUtils;

@XmlRootElement
public class GameMap extends ModelObject implements TileBasedMap {

    private String tmxFile;

    private List<GameCharacter> npc = new ArrayList<GameCharacter>();

    private TiledMap map;

    private PathFinder pathFinder;

    public String getTmxFile() {
        return tmxFile;
    }

    public void setTmxFile(String tmxFile) {
        this.tmxFile = tmxFile;

        try {
            map = new TiledMap(RESOURCE_FOLDER + "/" + tmxFile);
        } catch (SlickException e) {
            throw new RuntimeException();
        }
    }

    @XmlTransient
    public List<GameCharacter> getNpc() {
        return npc;
    }

    @XmlTransient
    public TiledMap getMap() {
        return map;
    }

    @XmlTransient
    public PathFinder getPathFinder() {
        if (pathFinder == null) {
            pathFinder = new AStarPathFinder(this, 30, true);
        }
        return pathFinder;
    }

    public void setPathFinder(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    @Override
    public boolean validate() {
        if (StringUtils.isBlank(tmxFile)) {
            return false;
        }
        return super.validate();
    }

    @XmlTransient
    @Override
    public int getWidthInTiles() {
        return map.getWidth();
    }

    @XmlTransient
    @Override
    public int getHeightInTiles() {
        return map.getHeight();
    }

    @Override
    public void pathFinderVisited(int x, int y) {
        // System.out.println("x:" + x + "y:" + y);
    }

    @Override
    public boolean blocked(PathFindingContext context, int tx, int ty) {
        GameCharacter person = (GameCharacter) context.getMover();
        Location location = person.getLocation();
        if ((location.getX() == tx) && (location.getY() == ty)) {
            return false;
        }

        boolean blocked = isMoveBlocked(tx, ty);
        // System.out.println("x:" + tx + " y:" + ty + " blocked:" + blocked);

        for (GameCharacter character : npc) {
            if (character.getLocation().equals(tx, ty)) {
                return true;
            }
        }

        return blocked;
    }

    @Override
    public float getCost(PathFindingContext context, int tx, int ty) {
        return 1;
    }

    public boolean isMoveBlocked(int x, int y) {
        // check for impassable objects
        int tileId = map.getTileId(x, y, Constants.MAP_LAYER_IMPASSABLES);
        if (tileId > 0) {
            return true;
        }
        // check for no floor
        tileId = map.getTileId(x, y, Constants.MAP_LAYER_FLOOR);
        if (tileId < 1) {
            return true;
        }

        return false;
    }

    public void lineOfSightVisitor(int sourceX, int sourceY, int x1, int y1) {
        // TODO: implement callback on visitor object/map
        int dx = Math.abs(x1 - sourceX);
        int dy = Math.abs(y1 - sourceY);
        int x = sourceX;
        int y = sourceY;
        int n = 1 + dx + dy;
        int x_inc = (x1 > sourceX) ? 1 : -1;
        int y_inc = (y1 > sourceY) ? 1 : -1;
        int error = dx - dy;
        dx *= 2;
        dy *= 2;

        for (; n > 0; --n) {
            // visit(x, y);
            System.out.println("Visting x:" + x + " y:" + y);
            if (error > 0) {
                x += x_inc;
                error -= dy;
            } else if (error < 0) {
                y += y_inc;
                error += dx;
            } else {
                x += x_inc;
                y += y_inc;
                n--;
            }
        }
    }

}
