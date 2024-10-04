package myHashingProject;

public class DoubleHashTable {

    private Machine[] machineList;
    private int maxSize;
    private int numItems;
    private final double loadFactor;

    public DoubleHashTable(int maxSize, double loadFactor) {
        this.maxSize = maxSize;
        this.loadFactor = loadFactor;
        this.machineList = (Machine[]) new Machine[maxSize]; // Initialize the array
    }

    public boolean add(Machine m) {
        numItems++; //increase the numItems

        // Check if resizing is needed
        if ((((double) numItems / (double) maxSize)) >= this.loadFactor) {
            this.maxSize = (int) (maxSize * (1 + loadFactor));  //make maxSize bigger

            Machine[] machineListCopy = new Machine[machineList.length];
            for (int i = 0; i < machineList.length; i++) {
                if (machineList[i] != null) {
                    // Assuming TestMachine has a copy constructor or a method to clone itself
                    machineListCopy[i] = new Machine(machineList[i].getMachineCode(), machineList[i].getName(), machineList[i].getDescription(), machineList[i].getLocation()); // or machineList[i].clone()
                }
            }

            Machine[] machineListBigger = new Machine[maxSize];
            machineList = machineListBigger;

            for (int i = 0; i < machineListCopy.length; i++) {
                if (machineListCopy[i] != null) {

                    int firstHash = hashFunction(machineListCopy[i].getMachineCode());
                    int secondHash = doubleHashFunction(machineListCopy[i].getMachineCode());
                    int newIndex = ((firstHash + (0 * secondHash)) / 3) % maxSize;
                    int counter = 0;
                    while (machineList[newIndex] != null) {
                        counter++;
                        newIndex = ((firstHash + (counter * secondHash)) / 3) % maxSize;
                    }

                    machineList[newIndex] = machineListCopy[i];

                }
            }

        }

        // Calculate index for the new machine and add it to machineList
        int firstHash = hashFunction(m.getMachineCode());
        int secondHash = doubleHashFunction(m.getMachineCode());

        for (int i = 0; i < machineList.length; i++) {
            int index = ((firstHash + (i * secondHash)) / 3) % maxSize;
            if (machineList[index] == null) {
                machineList[index] = m;
                return true;
            }
        }

        return false;
    }

    public int hashFunction(String key) {

        char[] charArray = key.toCharArray();
        int sum = 0;

        for (int i = 0; i < charArray.length; i++) {
            int num = (int) charArray[i];
            sum += (num * Math.pow(2, i));
        }
        sum = sum % maxSize;

        return sum;
    }

    public int doubleHashFunction(String input) {
        char[] charArray = input.toCharArray();
        int sum = 0;

        for (int i = 0; i < charArray.length; i++) {
            int num = (int) charArray[i];
            num = num + 1;
            sum += (num * Math.pow(2, i));
        }
        sum = sum % maxSize;

        return sum;
    }

    public int getLocation(String key) {

        // Calculate index for the new machine and add it to machineList
        int firstHash = hashFunction(key);
        int secondHash = doubleHashFunction(key);
        int index = ((firstHash + (0 * secondHash)) / 3) % maxSize;
        int counter = 0;

        for (int i = 0; i < machineList.length; i++) {
            if (machineList[index] != null && machineList[index].getMachineCode().equals(key)) {
                return index;
            }
            counter++;
            index = ((firstHash + (counter * secondHash)) / 3) % maxSize;
        }

        return -1; // Key not found
    }

    public Machine retrieve(String key) {
        int index = getLocation(key);

        if (index == -1) {
            return null;
        } else {
            return machineList[index];
        }
    }

    public boolean delete(String key) {
        int index = getLocation(key);
        if (index != -1) {
            machineList[index] = null;
            return true;
        }
        return false;
    }

    public String displayMachines() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < maxSize; i++) {
            if (machineList[i] != null) {
                list.append(machineList[i].toString()).append("\n");
            } else {
                list.append(i).append("\n");
            }
        }
        return list.toString();
    }

}
