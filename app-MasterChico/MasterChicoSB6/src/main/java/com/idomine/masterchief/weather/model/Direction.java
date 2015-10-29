 
package com.idomine.masterchief.weather.model;
 
import javax.xml.bind.annotation.XmlAttribute;

class Direction {
    
 
    private String value;
    
    private String code;
    
    private String name;

    @XmlAttribute
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlAttribute
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Direction{" + "value=" + value + ", code=" + code + ", name=" + name + '}';
    }
    
    
}
