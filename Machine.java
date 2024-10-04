package myHashingProject;

public class Machine {

    private String machineCode;
    private String name;
    private String description;
    private int location;

    public Machine(String machineCode, String name, String description, int location) {
        this.machineCode = machineCode;
        this.name = name;
        this.description = description;
    }

    public String getMachineCode() { //This is the code that is associated with the machine. This will become the index in the function
        return machineCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLocation() {
        return location;
    }
    

    @Override
    public String toString() {
        String info = "Machine: " + name + ", Code: " + machineCode + ", Description: " + description + ", Location: " + location;
        return info;
    }
}
