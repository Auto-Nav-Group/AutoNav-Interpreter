package net.autonav;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private Map<String, String> data = new HashMap<>();

    private long unix = System.currentTimeMillis();
    private short pitch;
    private String id = genID();

    private void collectData() {
        //TODO: Set field data
    }

    protected String genID() {
        int n = 6;
        String alphaStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "0123456789"
        + "abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder(n);
        for (int i=0;i<n;i++) {
            int index = (int)(alphaStr.length() * Math.random());

            sb.append(alphaStr.charAt(index));            
        }
        sb.insert(3, "-", 0, 1);
        return sb.toString();
    }
}
