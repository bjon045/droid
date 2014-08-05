package bioroid.model.item;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Armours {

    private List<Armour> armours;

    @XmlElement(name = "armour")
    public List<Armour> getArmours() {
        return armours;
    }

    public void setArmours(List<Armour> armours) {
        this.armours = armours;
    }

}
