package myHashingProject;

import java.util.Random;

public class myHashingProject {

    public static void main(String[] args) {

        LinearHashTable linearHashTable = new LinearHashTable(10, 0.70);
        QuadHashTable quadHashTable = new QuadHashTable(10, 0.50);
        DoubleHashTable doubleHashTable = new DoubleHashTable(10, 0.50);

        Machine machine1 = new Machine("3", "apple1", "This is apple1", 1);
        Machine machine2 = new Machine("e", "apple2", "This is apple2", 2);
        Machine machine3 = new Machine("o", "apple3", "This is apple3", 3);
        Machine machine4 = new Machine("y", "apple4", "This is apple4", 4);
        Machine machine5 = new Machine("G", "apple5", "This is apple5", 5);
        Machine machine6 = new Machine("e", "apple6", "This is apple6", 6);
        Machine machine7 = new Machine("e", "apple7", "This is apple7", 7);
        Machine machine8 = new Machine("e", "apple8", "This is apple8", 8);
        Machine machine9 = new Machine("e", "apple9", "This is apple9", 9);
        Machine machine10 = new Machine("e", "apple10", "This is apple10",10);
        Machine machine11 = new Machine("e", "apple11", "This is apple11", 11);
        Machine machine12 = new Machine("e", "apple12", "This is apple12", 12);
                
        
        linearHashTable.add(machine1);
        linearHashTable.add(machine2);
        linearHashTable.add(machine3);
        linearHashTable.add(machine4);
        System.out.println(linearHashTable.displayMachines());
        System.out.println(linearHashTable.retrieve("e").toString());
        linearHashTable.delete("e");
        System.out.println(linearHashTable.displayMachines());         


        quadHashTable.add(machine1);
        quadHashTable.add(machine2);
        quadHashTable.add(machine3);
        quadHashTable.add(machine4);
        System.out.println(quadHashTable.displayMachines());
        System.out.println(quadHashTable.retrieve("e").toString());
        quadHashTable.delete("e");
        System.out.println(quadHashTable.displayMachines());   


        doubleHashTable.add(machine1);
        doubleHashTable.add(machine2);
        doubleHashTable.add(machine3);
        doubleHashTable.add(machine4);
        System.out.println(doubleHashTable.displayMachines());
        System.out.println(doubleHashTable.retrieve("e").toString());
        doubleHashTable.delete("e");
        System.out.println(doubleHashTable.displayMachines()); 


    }

}
