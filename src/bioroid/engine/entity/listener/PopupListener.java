package bioroid.engine.entity.listener;

import bioroid.Constants;
import bioroid.control.EntityManager;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.StringEntity;
import bioroid.engine.entity.ui.BackgroundPanel;
import bioroid.model.location.Location;
import bioroid.utils.StringUtils;

public class PopupListener implements EntityListener {

    protected String popupText;

    public PopupListener(String popupText) {
        this.popupText = popupText;
    }

    @Override
    public void mouseEntered(Entity e) {
        // show popup
        if (StringUtils.isBlank(popupText)) {
            return;
        }
        BackgroundPanel popupPanel = EntityManager.getPopupPanel();
        popupPanel.setActive(true);
        int startX = e.getX() + e.getWidth();
        int startY = e.getY() + e.getHeight();
        popupPanel.setX(startX);
        popupPanel.setY(startY);
        popupPanel.getChildren().clear();
        Location l = popupPanel.getMainStart();

        StringEntity se = new StringEntity(l.getX(), l.getY(), Constants.POPUP_CHARACTER_WIDTH, popupText);

        popupPanel.setWidth(se.getWidth() + (popupPanel.getBorderThickness() * 2));
        popupPanel.setHeight(se.getHeight() + (popupPanel.getBorderThickness() * 2));

        popupPanel.getChildren().add(se);
    }

    @Override
    public void mouseExited(Entity e) {
        // hide popup
        if (StringUtils.isBlank(popupText)) {
            return;
        }
        BackgroundPanel popupPanel = EntityManager.getPopupPanel();
        popupPanel.setActive(false);
    }

    @Override
    public void mousePressed(Entity e, int mouseX, int mouseY) {
        // no action
    }

}
