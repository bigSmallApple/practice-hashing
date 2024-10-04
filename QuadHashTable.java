package myHashingProject;

public class QuadHashTable {

    private Machine[] machineList;
    private int maxSize;
    private int numItems;
    private final double loadFactor;

    public QuadHashTable(int maxSize, double loadFactor) {
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
                    // making clone of machineList. I need new array of objects because new array of pointers won't work
                    machineListCopy[i] = new Machine(machineList[i].getMachineCode(), machineList[i].getName(), machineList[i].getDescription(), machineList[i].getLocation()); // or machineList[i].clone()
                }
            }

            Machine[] machineListBigger = new Machine[maxSize];
            machineList = machineListBigger;

            for (Machine machineListCopy1 : machineListCopy) {
                if (machineListCopy1 != null) {
                    int newIndex = hashFunction(machineListCopy1.getMachineCode());

                    int count = 0;

                    while (machineList[newIndex] != null) {
                        newIndex = (newIndex + (int) Math.pow(count, 2)) % maxSize;
                        count++;
                    }

                    machineList[newIndex] = machineListCopy1;
                }
            }

        }

        // Calculate index for the new machine and add it to machineList
        int index = hashFunction(m.getMachineCode());
        int count = 0;
        for (int i = 0; i < machineList.length; i++) {

            if (machineList[index] == null) {
                machineList[index] = m;
                return true;

            }
            index = (index + (int) Math.pow(count, 2)) % maxSize;
            count++;
        }


        //if element was not added, the numItems would get reduced by 1. The user will be notified that the element was not added.
        numItems--;
        System.out.println("ERROR: Element with key:\" " + m.getMachineCode() + " \" was not added. Reduce loadFactor value");
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

    public int getLocation(String key) {
        int index = hashFunction(key);
        int counter = 0;

        //looks for the key using quadratic hash function
        for (int i = 0; i < machineList.length; i++) {
            if (machineList[index] != null && machineList[index].getMachineCode().equals(key)) {
                return index;
            }
            index = (index + (int) Math.pow(counter, 2)) % maxSize;
            counter++;

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
