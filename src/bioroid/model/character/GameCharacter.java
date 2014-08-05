package bioroid.model.character;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

import bioroid.model.CodeAndValue;
import bioroid.model.location.Location;

import org.newdawn.slick.util.pathfinding.Mover;

public class GameCharacter implements Mover {

    private String name;

    private String race;

    private String subrace;

    private String career;

    private int currentHP;

    private int maxHP;

    private List<CodeAndValue> attributes = new ArrayList<CodeAndValue>();

    private List<CodeAndValue> skills = new ArrayList<CodeAndValue>();

    private Location location;

    private String spriteName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSubrace() {
        return subrace;
    }

    public void setSubrace(String subrace) {
        this.subrace = subrace;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String currentCareer) {
        career = currentCareer;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    @XmlElement(name = "attribute")
    public List<CodeAndValue> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CodeAndValue> attributes) {
        this.attributes = attributes;
    }

    @XmlElement(name = "skill")
    public List<CodeAndValue> getSkills() {
        return skills;
    }

    public void setSkills(List<CodeAndValue> skills) {
        this.skills = skills;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getSpriteName() {
        return spriteName;
    }

    public void setSpriteName(String spriteName) {
        this.spriteName = spriteName;
    }

}
