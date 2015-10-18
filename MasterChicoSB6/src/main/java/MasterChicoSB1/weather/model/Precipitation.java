package MasterChicoSB1.weather.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Precipitation {
    
    private long value;
    
    private String mode;
    
    @XmlAttribute
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @XmlAttribute
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Precipitation{" + "value=" + value + ", mode=" + mode + '}';
    }
    
    
    
}