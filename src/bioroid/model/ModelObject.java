package bioroid.model;

import bioroid.utils.StringUtils;

public abstract class ModelObject {

    private String code;

    private String name;

    private String description;

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public boolean validate() {
	if (StringUtils.isBlank(code) || StringUtils.isBlank(name)) {
	    return false;
	}
	return true;
    }

}