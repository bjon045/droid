package bioroid.engine.entity.ui.main;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import bioroid.GameHolder;
import bioroid.engine.entity.Entity;
import bioroid.font.Fonts;
import bioroid.model.character.GameCharacter;

public class CharacterPanel extends Entity {

    public CharacterPanel(int x, int y, int width, int height) {
	super(x, y, width, height, 1);
    }

    @Override
    protected void renderEntitySpecific(GameContainer container, Graphics g) {
	int x2 = getX() + 20;
	int y2 = getY() + 100;
	List<GameCharacter> pcs = GameHolder.currentGame.getPlayerCharacters();
	for (GameCharacter pc : pcs) {
	    Fonts.BASIC_FONT.drawString(x2, y2, pc.getName(), Color.green);
	    y2 = y2 + 20;
	    if (pc.getCurrentHP() < (pc.getMaxHP() / 2)) {
		Fonts.BASIC_FONT.drawString(x2, y2, "HP: " + pc.getCurrentHP() + "/" + pc.getMaxHP(), Color.red);
	    } else {
		Fonts.BASIC_FONT.drawString(x2, y2, "HP: " + pc.getCurrentHP() + "/" + pc.getMaxHP(), Color.green);
	    }
	    y2 = y2 + 80;
	}

    }

    @Override
    public void update(int delta) {
	// not needed yet (possibly show damage received or flash with conditions like blind?)
    }

}
