package bioroid.engine.entity;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import bioroid.engine.entity.listener.EntityListener;
import bioroid.utils.CollectionUtils;

public abstract class Entity implements Comparable<Entity> {

    private int x, y, width, height;

    private Image image;

    private Image backgroundImage;

    private Color background; // null -> transparent/ignore

    private boolean drawBorder;

    private EntityListener listener;

    private boolean mouseInside;

    private int priorty; // render/update order 0 = first

    private boolean active = true;

    public Entity(int x, int y, int width, int height, int priority) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        priorty = priority;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(GameContainer container, Graphics g) {
        if (!active) {
            return;
        }
        if (background != null) {
            g.setColor(background);
            g.fillRect(x, y, width, height);
        }
        if (backgroundImage != null) {
            backgroundImage.draw(x, y, width, height);
        }
        if (drawBorder) {
            g.setLineWidth(5);
            g.setColor(Color.green);
            g.drawRect(x - 5, y - 5, width + 10, height + 10);
        }

        if (image != null) {
            image.draw(x, y, width, height);
        }

        renderEntitySpecific(container, g);

        List<? extends Entity> children = getChildren();
        if (CollectionUtils.isNotEmpty(children)) {
            // render npcs/events
            for (Entity e : children) {
                e.render(container, g);
            }
        }

    }

    protected void renderEntitySpecific(GameContainer container, Graphics g) {
        // if entity has any particular render requirements implement here
    }

    public abstract void update(int delta);

    public boolean inside(int xp, int yp) {
        if (!active) {
            return false;
        }
        return (xp >= x) && (yp >= y) && (xp < (x + width)) && (yp < (y + height));
    }

    @Override
    public int compareTo(Entity oEntity) {
        if (priorty == oEntity.priorty) {
            return 0;
        } else if (priorty > oEntity.priorty) {
            return 1;
        }
        return -1;
    }

    public List<? extends Entity> getChildren() {
        return null;
    }

    public EntityListener getListener() {
        return listener;
    }

    public void setListener(EntityListener listener) {
        this.listener = listener;
    }

    public void mouseEntered() {
        mouseInside = true;
        if (listener != null) {
            listener.mouseEntered(this);
        }
    }

    public void mouseExited() {
        mouseInside = false;
        if (listener != null) {
            listener.mouseExited(this);
        }
    }

    public void mousePressed(int mouseX, int mouseY) {
        if (listener != null) {
            listener.mousePressed(this, mouseX, mouseY);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public boolean isDrawBorder() {
        return drawBorder;
    }

    public void setDrawBorder(boolean drawBorder) {
        this.drawBorder = drawBorder;
    }

    public boolean isMouseInside() {
        return mouseInside;
    }

    public void setMouseInside(boolean mouseInside) {
        this.mouseInside = mouseInside;
    }

    public int getPriorty() {
        return priorty;
    }

    public void setPriorty(int priorty) {
        this.priorty = priorty;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

}
