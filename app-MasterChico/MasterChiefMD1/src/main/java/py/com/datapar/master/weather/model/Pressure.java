package py.com.datapar.master.weather.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Pressure {
   
    private long value;
    
    private String unit;

    @XmlAttribute
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @XmlAttribute
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Pressure{" + "value=" + value + ", unit=" + unit + '}';
    }
    
    
}
