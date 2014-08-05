package bioroid.engine.entity.listener;

import bioroid.engine.entity.Entity;

public interface EntityListener {

    public void mouseEntered(Entity e);

    public void mouseExited(Entity e);

    public void mousePressed(Entity e, int mouseX, int mouseY);
}