package bioroid.engine.entity.ui.raceselection;

import java.util.ArrayList;
import java.util.List;

import bioroid.GameHolder.CharacterGenerationHolder;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.StringEntity;
import bioroid.engine.entity.listener.PopupListener;
import bioroid.font.Fonts;
import bioroid.model.ModelResources;
import bioroid.model.attribute.Attribute;
import bioroid.model.career.Career;
import bioroid.model.character.GameCharacter;
import bioroid.model.race.Race;
import bioroid.utils.CodeAndValueUtils;
import bioroid.utils.CollectionUtils;
import bioroid.utils.StringUtils;

public class RaceSelectionPanel extends Entity {

    public static final int FIRST_LEFT_OFFSET = 20;

    public static final int SECOND_LEFT_OFFSET = 80;

    public static final int THIRD_LEFT_OFFSET = 320;

    public static final int FORTH_LEFT_OFFSET = 380;

    public static final int FIFTH_LEFT_OFFSET = 450;

    public static final int SIXTH_LEFT_OFFSET = 620;

    public static final int SEVENTH_LEFT_OFFSET = 680;

    public static final int PANEL_GAP = 25;

    private List<Entity> entities = new ArrayList<Entity>();

    public RaceSelectionPanel(int x, int y, int width, int height) {
        super(x, y, width, height, 0);
    }

    /**
     * Call this method when underlying party structure is changed
     */
    public void reset() {
        // refresh list of entities
        entities.clear();
        GameCharacter pc = CharacterGenerationHolder.activeCharacter;
        int counter1 = 1;

        // Race
        StringEntity raceLabel = new StringEntity(getX() + FIRST_LEFT_OFFSET, getY() + (counter1 * PANEL_GAP),
                "Choose a Race:");
        entities.add(raceLabel);
        counter1++;

        for (Race raceType : ModelResources.races.getRaces()) {
            StringEntity raceValue = new StringEntity(getX() + SECOND_LEFT_OFFSET, getY() + (counter1 * PANEL_GAP),
                    raceType.getName());
            raceValue.setListener(new RaceSelectionListener(raceType.getDescription(), raceType));
            entities.add(raceValue);
            counter1++;

            if (raceType.getCode().equals(pc.getRace())) {
                raceValue.setDrawBorder(true);

                // subrace
                if (CollectionUtils.isNotEmpty(raceType.getSubraces())) {
                    int counter2 = 1;

                    StringEntity subraceLabel = new StringEntity(getX() + THIRD_LEFT_OFFSET, getY()
                            + (counter2 * PANEL_GAP), "Choose a Sub-race:");
                    entities.add(subraceLabel);
                    counter2++;

                    // No subrace option
                    StringEntity noSubrace = new StringEntity(getX() + FORTH_LEFT_OFFSET, getY()
                            + (counter2 * PANEL_GAP), "None");
                    noSubrace.setListener(new SubraceSelectionListener(null, new Race()));
                    if (StringUtils.isBlank(pc.getSubrace())) {
                        noSubrace.setDrawBorder(true);
                    }
                    entities.add(noSubrace);
                    counter2++;

                    for (String subraceCode : raceType.getSubraces()) {
                        Race subraceType = (Race) ModelResources.modelObjects.get(subraceCode);
                        StringEntity subraceTypeValue = new StringEntity(getX() + FORTH_LEFT_OFFSET, getY()
                                + (counter2 * PANEL_GAP), subraceType.getName());
                        subraceTypeValue.setListener(new SubraceSelectionListener(subraceType.getDescription(),
                                subraceType));
                        entities.add(subraceTypeValue);
                        counter2++;

                        if (subraceType.getCode().equals(pc.getSubrace())) {
                            subraceTypeValue.setDrawBorder(true);
                        }
                    }
                }
            }
        }

        // career
        int counter2 = 1;
        StringEntity careerLabel = new StringEntity(getX() + SIXTH_LEFT_OFFSET, getY() + (counter2 * PANEL_GAP), 200,
                100, "Choose a Career:");
        entities.add(careerLabel);
        counter2++;

        for (Career careerType : ModelResources.careers.getCareers()) {
            StringEntity careerValue = new StringEntity(getX() + SEVENTH_LEFT_OFFSET, getY() + (counter2 * PANEL_GAP),
                    careerType.getName());
            careerValue.setListener(new CareerSelectionListener(careerType.getDescription(), careerType));

            if (!careerType.canTakeCareer(pc)) {
                careerValue.setColour(Fonts.DARK_GREEN);
            }

            if (careerType.getCode().equals(pc.getCareer())) {
                careerValue.setDrawBorder(true);
            }

            entities.add(careerValue);
            counter2++;
        }

        if (StringUtils.isBlank(pc.getRace())) {
            return;
        }

        // attribute
        counter1++;

        // top labels
        StringEntity attributeLabel = new StringEntity(getX() + FIRST_LEFT_OFFSET, getY() + (counter1 * PANEL_GAP),
                "Attributes:");
        entities.add(attributeLabel);

        StringEntity baselabel = new StringEntity(getX() + THIRD_LEFT_OFFSET, getY() + (counter1 * PANEL_GAP), "Base:");
        entities.add(baselabel);

        StringEntity totallabel = new StringEntity(getX() + FIFTH_LEFT_OFFSET, getY() + (counter1 * PANEL_GAP),
                "Total:");
        entities.add(totallabel);

        counter1++;

        for (Attribute attribute : ModelResources.attributes.getAttributes()) {
            // attribute row
            StringEntity attributeItemLabel = new StringEntity(getX() + SECOND_LEFT_OFFSET, getY()
                    + (counter1 * PANEL_GAP), attribute.getName());
            attributeItemLabel.setListener(new PopupListener(attribute.getDescription()));
            entities.add(attributeItemLabel);

            // total value
            int total = CodeAndValueUtils.getValue(attribute.getCode(), pc.getAttributes());
            int spentPoints = CodeAndValueUtils.getValue(attribute.getCode(), CharacterGenerationHolder.spentPoints);

            StringEntity attributeRaceValue = new StringEntity(getX() + THIRD_LEFT_OFFSET, getY()
                    + (counter1 * PANEL_GAP), String.valueOf(total - spentPoints));
            entities.add(attributeRaceValue);

            // total value

            StringEntity totalValue = new StringEntity(getX() + FIFTH_LEFT_OFFSET, getY() + (counter1 * PANEL_GAP),
                    String.valueOf(total));
            // totalValue.setDrawBorder(true);
            entities.add(totalValue);

            if (spentPoints > 0) {
                // show minus button
                StringEntity minusButton = new StringEntity((getX() + FIFTH_LEFT_OFFSET) - 25, getY()
                        + (counter1 * PANEL_GAP), "-");
                minusButton.setListener(new AttributeSelectionListener(attribute.getCode(), -1));
                entities.add(minusButton);
            }

            int maxValue = CodeAndValueUtils.getValue(attribute.getCode(), CharacterGenerationHolder.maximumPoints);
            if (((total) < maxValue) && (CharacterGenerationHolder.attributePoints > 0)) {
                // show plus button
                StringEntity plusButton = new StringEntity(getX() + FIFTH_LEFT_OFFSET + 25, getY()
                        + (counter1 * PANEL_GAP), "+");
                plusButton.setListener(new AttributeSelectionListener(attribute.getCode(), 1));
                entities.add(plusButton);
            }

            counter1++;
        }

        StringEntity pointsLabel = new StringEntity((getX() + THIRD_LEFT_OFFSET) - 150,
                getY() + (counter1 * PANEL_GAP), "Available points:");
        entities.add(pointsLabel);

        StringEntity pointsValue = new StringEntity(getX() + FIFTH_LEFT_OFFSET, getY() + (counter1 * PANEL_GAP),
                String.valueOf(CharacterGenerationHolder.attributePoints));
        pointsValue.setDrawBorder(true);
        entities.add(pointsValue);

        counter1++;

        // next button
        if (!StringUtils.isBlank(pc.getCareer())) {
            StringEntity nextButton = new StringEntity((getX() + getWidth()) - (getWidth() / 4), (getY() + getHeight())
                    - (getHeight() / 4), 12, "Next");
            nextButton.setDrawBorder(true);
            nextButton.setListener(new RaceSelectionNextButtonListener());
            entities.add(nextButton);
        }

    }

    @Override
    public void update(int delta) {

    }

    @Override
    public List<Entity> getChildren() {
        return entities;
    }

}
