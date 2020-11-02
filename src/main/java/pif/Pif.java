package pif;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Pif {

    private Map<String, Integer> pif = new HashMap<>();

    public void add(String key,
                    Integer value) {
        pif.put(key, value);
    }

    public void writeToFile() throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("pif.out"));
        bufferedOutputStream.write(toString().getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String token : pif.keySet()) {
            stringBuilder.append(token + " " + pif.get(token)+"\n");
        }
        return stringBuilder.toString();
    }
}
