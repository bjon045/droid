package bioroid.model.character;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Characters {

    private List<GameCharacter> characters;

    @XmlElement(name = "character")
    public List<GameCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(List<GameCharacter> characters) {
        this.characters = characters;
    }

}
