package bioroid.model.location;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Maps {

    private List<String> map;

    public List<String> getMap() {
        return map;
    }

    public void setMap(List<String> map) {
        this.map = map;
    }

}
