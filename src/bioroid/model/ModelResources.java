package bioroid.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bioroid.model.attribute.Attributes;
import bioroid.model.career.Careers;
import bioroid.model.character.Characters;
import bioroid.model.event.Events;
import bioroid.model.item.Accessories;
import bioroid.model.item.Armours;
import bioroid.model.item.Weapons;
import bioroid.model.race.Races;
import bioroid.model.skill.SkillGroups;
import bioroid.model.skill.Skills;
import bioroid.utils.ModelUtils;

public class ModelResources {

    public static Weapons weapons;

    public static Armours armours;

    public static Accessories accessories;

    public static Skills skills;

    public static SkillGroups skillGroups;

    public static Races races;

    public static Careers careers;

    public static Attributes attributes;

    public static Events events;

    public static Characters characters;

    public static Map<String, ModelObject> modelObjects = new HashMap<String, ModelObject>();

    static {

        // load resources that do not change
        weapons = ModelUtils.loadModelObject(Weapons.class, "/item/weapons.xml");
        addItems(modelObjects, weapons.getWeapons());

        armours = ModelUtils.loadModelObject(Armours.class, "/item/armours.xml");
        addItems(modelObjects, armours.getArmours());

        accessories = ModelUtils.loadModelObject(Accessories.class, "/item/accessories.xml");
        addItems(modelObjects, accessories.getAccessories());

        skills = ModelUtils.loadModelObject(Skills.class, "/skill/skills.xml");
        addItems(modelObjects, skills.getSkills());

        skillGroups = ModelUtils.loadModelObject(SkillGroups.class, "/skill/skillgroups.xml");
        addItems(modelObjects, skillGroups.getSkillGroups());

        races = ModelUtils.loadModelObject(Races.class, "/race/races.xml");
        addItems(modelObjects, races.getRaces());
        addItems(modelObjects, ModelUtils.loadModelObject(Races.class, "/race/subraces.xml").getRaces());

        careers = ModelUtils.loadModelObject(Careers.class, "/career/careers.xml");
        addItems(modelObjects, careers.getCareers());

        attributes = ModelUtils.loadModelObject(Attributes.class, "/attribute/attributes.xml");
        addItems(modelObjects, attributes.getAttributes());

        events = ModelUtils.loadModelObject(Events.class, "/event/events.xml");
        addItems(modelObjects, events.getEvents());

        characters = ModelUtils.loadModelObject(Characters.class, "/character/characters.xml");
        addItems(modelObjects, characters.getCharacters());

        // validation block
        validate(modelObjects);
    }

    private static void validate(Map<String, ? extends ModelObject> listToValidate) {
        for (ModelObject modelObject : listToValidate.values()) {
            if (!modelObject.validate()) {
                throw new RuntimeException("Core XML is not valid. Object with code: " + modelObject.getCode());
            }

        }
    }

    private static <T extends ModelObject> void addItems(Map<String, ModelObject> list, List<T> listToAdd) {
        for (ModelObject modelObject : listToAdd) {
            if (list.containsKey(modelObject.getCode())) {
                throw new RuntimeException("Duplicate Item.");
            }
            list.put(modelObject.getCode(), modelObject);
        }
    }

}
