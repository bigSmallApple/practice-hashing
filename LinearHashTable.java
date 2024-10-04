package myHashingProject;

public class LinearHashTable {

    private Machine[] machineList;
    private int maxSize;
    private int numItems;
    private final double loadFactor;

    public LinearHashTable(int maxSize, double loadFactor) {
        this.maxSize = maxSize;
        this.loadFactor = loadFactor;
        this.machineList = (Machine[]) new Machine[maxSize]; // Initialize the array
    }
    
    public void printInfo(){
        System.out.println("maxSize: " + maxSize + " numItems: " + numItems + " loadFactor: " + loadFactor);
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

            for (Machine machineListCopy1 : machineListCopy) {
                if (machineListCopy1 != null) {
                    int newIndex = hashFunction(machineListCopy1.getMachineCode());
                    machineList[newIndex] = machineListCopy1;
                }
            }

        }

        // Calculate index for the new machine and add it to machineList
        int index = hashFunction(m.getMachineCode());
        machineList[index] = m;

        return true;
    }

    public int hashFunction(String key) {

        char[] charArray = key.toCharArray();
        int sum = 0;
        for (int i = 0; i < charArray.length; i++) {
            int num = (int) charArray[i];
            sum += (num * Math.pow(2, i));
        }
        
        sum = sum % maxSize;
        while (machineList[sum] != null) {
            sum = (sum + 1) % maxSize;
        }
        

        return sum;
    }

    public Machine retrieve(String key) {
        int index = getLocation(key);

        if (index == -1) {
            return null;
        } else {
            return machineList[index];
        }
    }

    public int getLocation(String key) {
        int index = hashFunction(key); //hashes the key to find starting location
        
        //this part checks if the starting location is the key, if not it will iterated over the whole array using linear hashing to find it. 
        for (int i = 0; i < machineList.length; i++){ 
            if (machineList[index] != null && machineList[index].getMachineCode().equals(key)) {
                return index;
            }
            index = (index + 1) % maxSize;
        }

        return -1; // Key not found
    }

    public boolean delete(String key) {
        int index = getLocation(key);
        if (index != -1){
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
