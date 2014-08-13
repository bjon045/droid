package bioroid.model.game;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import bioroid.model.character.GameCharacter;

@XmlRootElement
public class SavedGame {

    // currentMap -> events/entities/npc
    // currentLocation
    // Party -> PC's -> items/stats/skills etc

    private List<GameCharacter> playerCharacters = new ArrayList<GameCharacter>();

    private String currentMapCode;

    @XmlElement(name = "playerCharacter")
    public List<GameCharacter> getPlayerCharacters() {
        return playerCharacters;
    }

    public void setPlayerCharacters(List<GameCharacter> playerCharacter) {
        playerCharacters = playerCharacter;
    }

    public String getCurrentMapCode() {
        return currentMapCode;
    }

    public void setCurrentMapCode(String currentMapCode) {
        this.currentMapCode = currentMapCode;
    }

}
