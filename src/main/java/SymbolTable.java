public class SymbolTable {

    private final double c1 = 0.5;
    private final double c2 = 0.5;
    private int capacity = 16;
    private String[] elements = new String[capacity];
    private int size = 0;

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public int search(String key) {
        int j = 0;
        int startingPosition = hash(key, j);
        if (elements[startingPosition].equals(key)) {
            return startingPosition;
        }
        if (elements[startingPosition] == null) {
            return -1;
        }
        while (true) {
            j++;
            int currentPosition = hash(key, j);
            if (elements[currentPosition].equals(key)) {
                return currentPosition;
            }
            if (elements[currentPosition] == null) {
                return -1;
            }
        }
    }

    int add(String key) {
        if ((double) size / capacity > 0.7) {
            resize();
        }
        int index = 0;
        while (true) {
            int hash = hash(key, index);
            if (elements[hash] == null || elements[hash].equals("")) {
                elements[hash] = key;
                size++;
                return hash;
            }
            if(elements[hash].equals(key)){
                return hash;
            }
            index++;
        }
    }

    private void resize() {
        capacity *= 2;
        String[] auxArray = new String[capacity];
        for (int i = 0; i < capacity; i++) {
            auxArray[i] = null;
        }
        for (int i = 0; i < capacity / 2; i++) {
            if (elements[i] != null) {
                int index = 0;
                boolean added = false;
                while (!added && index < capacity) {
                    int hash = hash(elements[i], index);
                    if (auxArray[hash] == null) {
                        auxArray[hash] = elements[i];
                        added = true;
                    }
                    index++;
                }
            }
        }
        elements = auxArray;
    }

    private int calculateAsciiSum(String key) {
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i) - '0';
        }
        return sum;
    }

    public String getByIndex(int index) {
        return elements[index];
    }

    private int hashKey(String key) {
        int hash = calculateAsciiSum(key) % capacity;
        if (hash < 0) {
            return -hash;
        }
        return hash;
    }

    private int hash(String key,
                     int index) {
        return (int) (hashKey(key) + c1 * index + c2 * index * index) % capacity;
    }

}
