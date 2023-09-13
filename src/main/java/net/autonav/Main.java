package net.autonav;

public class Main {
    Data data = new Data();
    String id = data.genID();

    public static void main(String[] args) {
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
        System.out.println(sb.toString());
    }

    public void buildDataJson() {
        
    }
}
