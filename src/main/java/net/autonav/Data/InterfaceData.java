package net.autonav.Data;

public class InterfaceData {
    private String type;
    private String data;

    public InterfaceData(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return this.type;
    }

    public String getData() {
        return this.data;
    }
}
