package py.com.datapar.master.weather.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Clouds {
  
    private long value;
    
    private String name;

    @XmlAttribute
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
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
        return "Clouds{" + "value=" + value + ", name=" + name + '}';
    }
    
    
    
}
