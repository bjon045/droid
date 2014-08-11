package bioroid.engine.entity.ui.dialog;

import org.lwjgl.opengl.Display;

import bioroid.Constants;
import bioroid.engine.entity.EntityManager;
import bioroid.engine.entity.StringEntity;
import bioroid.engine.entity.ui.BorderedPanel;
import bioroid.model.location.Location;

public class DialogGenerator {

    private DialogGenerator() {
    }

    public static void createDialog(String message) {
        BorderedPanel popupPanel = EntityManager.getPopupPanel();
        popupPanel.setActive(true);

        popupPanel.setX(Display.getWidth() / 3);
        popupPanel.setY(Display.getHeight() / 3);
        popupPanel.getChildren().clear();
        Location l = popupPanel.getMainStart();

        StringEntity se = new StringEntity(l.getX(), l.getY(), Constants.POPUP_CHARACTER_WIDTH / 2, message);

        popupPanel.setWidth(se.getWidth() + (popupPanel.getBorderThickness() * 2));
        popupPanel.setHeight(se.getHeight() + (popupPanel.getBorderThickness() * 2) + 60);

        popupPanel.getChildren().add(se);
    }

}
