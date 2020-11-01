package pif;

import java.util.HashMap;
import java.util.Map;

public class Pif {

    private Map<String, Integer> pif = new HashMap<>();

    public void add(String key,
                    Integer value) {
        pif.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Program Internal Form\n");
        stringBuilder.append("Key\ttoken\n");
        for (String token : pif.keySet()) {
            stringBuilder.append(token + "\t" + pif.get(token)+"\n");
        }
        return stringBuilder.toString();
    }
}
