package bioroid.engine.entity.ui.main;

import static bioroid.Constants.RESOURCE_FOLDER;
import static bioroid.Constants.TILE_SIZE;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import bioroid.GameHolder;
import bioroid.control.action.Action;
import bioroid.control.action.ActionType;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.MapEntity;
import bioroid.engine.entity.PlayerCharacterEntity;
import bioroid.engine.entity.listener.EntityListener;
import bioroid.model.character.GameCharacter;
import bioroid.model.location.GameMap;
import bioroid.model.location.Location;

public class MapPanel extends Entity {

    private GameMap gameMap;

    private List<MapEntity> entities = new ArrayList<MapEntity>();

    // location where the map should be centered (in tiles not pixels)
    private Location viewPoint;

    private int mapOffsetX, mapStartX, mapEndX;

    private int mapOffsetY, mapStartY, mapEndY;

    public int mapHeight, mapWidth, mapHalfHeight, mapHalfWidth;

    public MapPanel(int x, int y, int width, int height) {
	super(x, y, width, height, 1);

	Image mapBackground;
	try {
	    mapBackground = new Image(RESOURCE_FOLDER + "/map_background.png", false);
	} catch (SlickException e) {
	    throw new RuntimeException(e);
	}
	super.setImage(mapBackground);

	mapHeight = getHeight() / TILE_SIZE;
	mapWidth = getWidth() / TILE_SIZE;
	mapHalfHeight = mapHeight / 2;
	mapHalfWidth = mapWidth / 2;

	// move topleft and bottom right to center of available window
	int widthRemainder = getWidth() % TILE_SIZE;
	int newXOffset = (widthRemainder / 2);
	setX(getX() + newXOffset);

	int heightRemainder = getHeight() % TILE_SIZE;
	int newYOffset = heightRemainder / 2;
	setY(getY() + newYOffset);

	setWidth(getWidth() - widthRemainder);
	setHeight(getHeight() - heightRemainder);

	setListener(new MapPanelListener(this));
    }

    @Override
    protected void renderEntitySpecific(GameContainer container, Graphics g) {
	// render tmxMap
	gameMap.getMap().render(mapOffsetX, mapOffsetY, mapStartX, mapStartY, mapEndX, mapEndY, false);
    }

    private void updateOffsets() {
	mapOffsetX = getX();
	mapStartX = 0;
	mapEndX = mapWidth;

	if (viewPoint.getX() < mapHalfWidth) {
	    mapOffsetX = mapHalfWidth - viewPoint.getX();
	    mapOffsetX = getX() + (mapOffsetX * TILE_SIZE);
	    mapEndX = mapHalfWidth + viewPoint.getX();
	    if ((mapWidth % 2) == 1) {
		mapEndX++;
	    }
	} else {
	    mapStartX = viewPoint.getX() - mapHalfWidth;
	    // map end incorrect!
	}

	mapOffsetY = getY();
	mapStartY = 0;
	mapEndY = mapHeight;

	if (viewPoint.getY() < mapHalfHeight) {
	    mapOffsetY = mapHalfHeight - viewPoint.getY();
	    mapOffsetY = getY() + (mapOffsetY * TILE_SIZE);
	    mapEndY = mapHalfHeight + viewPoint.getY();
	    if ((mapHeight % 2) == 1) {
		mapEndY++;
	    }
	} else {
	    mapStartY = viewPoint.getY() - mapHalfHeight;
	}
    }

    @Override
    public void update(int delta) {
	// determine what is the current viewpoint
	// note: this is just an optimisation for when the viewpoint has not moved.
	if (!viewPoint.equals(GameHolder.viewPoint)) {
	    viewPoint = GameHolder.viewPoint.copy();
	    // calculate what part of the map should be shown
	    updateOffsets();
	}

	// update npc render offsets as they may have moved!
	for (MapEntity e : entities) {
	    e.updateRenderLocation(mapOffsetX, mapOffsetY, mapStartX, mapStartY, mapEndX, mapEndY);
	}
    }

    /**
     * Call this method on a map transition.
     */
    public void reset() {
	gameMap = GameHolder.maps.get(GameHolder.currentGame.getCurrentMapCode());
	GameHolder.currentMap = gameMap;
	viewPoint = GameHolder.viewPoint.copy();
	updateOffsets();

	// refresh list of entities
	entities.clear();
	List<GameCharacter> pcs = GameHolder.currentGame.getPlayerCharacters();
	for (GameCharacter pc : pcs) {
	    PlayerCharacterEntity pce = new PlayerCharacterEntity(pc);
	    entities.add(pce);
	}
    }

    @Override
    public List<MapEntity> getChildren() {
	return entities;
    }

    public class MapPanelListener implements EntityListener {

	private MapPanel mapPanel;

	public MapPanelListener(MapPanel mapPanel) {
	    this.mapPanel = mapPanel;
	}

	@Override
	public void mouseEntered(Entity e) {
	    // no action required
	}

	@Override
	public void mouseExited(Entity e) {
	    // no action required
	}

	@Override
	public void mousePressed(Entity e, int mouseX, int mouseY) {
	    int x = mouseX - mapPanel.mapOffsetX;
	    int y = mouseY - mapPanel.mapOffsetY;
	    x = x / TILE_SIZE;
	    y = y / TILE_SIZE;
	    x = x + mapPanel.mapStartX;
	    y = y + mapPanel.mapStartY;

	    System.out.println("Clicked on location x:" + x + " y:" + y);
	    GameHolder.currentAction = new Action(ActionType.MOVE, new Location(x, y));

	}

    }

}
