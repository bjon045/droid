package bioroid.engine.entity;

import org.newdawn.slick.Image;

public class StaticImageEntity extends Entity {

    public StaticImageEntity(int x, int y, Image image) {
        super(x, y, image.getWidth(), image.getHeight(), 0);
        super.setImage(image);
    }

    @Override
    public void update(int delta) {
        // no update required
    }

}
