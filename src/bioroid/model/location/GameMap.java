package bioroid.model.location;

import static bioroid.Constants.RESOURCE_FOLDER;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.PathFinder;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import bioroid.model.ModelObject;
import bioroid.model.character.GameCharacter;
import bioroid.utils.MapUtils;
import bioroid.utils.StringUtils;

@XmlRootElement
public class GameMap extends ModelObject implements TileBasedMap {

    private String tmxFile;

    private List<Event> event;

    private List<GameCharacter> npc;

    private TiledMap map;

    private PathFinder pathFinder;

    public String getTmxFile() {
        return tmxFile;
    }

    public void setTmxFile(String tmxFile) {
        this.tmxFile = tmxFile;

        try {
            setMap(new TiledMap(RESOURCE_FOLDER + "/" + tmxFile));
        } catch (SlickException e) {
            throw new RuntimeException();
        }
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    public List<GameCharacter> getNpc() {
        return npc;
    }

    public void setNpc(List<GameCharacter> npc) {
        this.npc = npc;
    }

    @XmlTransient
    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
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

        boolean blocked = MapUtils.isMoveBlocked(this, tx, ty, false);
        // System.out.println("x:" + tx + " y:" + ty + " blocked:" + blocked);
        return blocked;
    }

    @Override
    public float getCost(PathFindingContext context, int tx, int ty) {
        return 1;
    }

}
