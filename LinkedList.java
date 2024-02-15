import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class LinkedList<T extends Comparable> {
    Node<T> head;
    public LinkedList() {
        this.head = null;
    }
    public void insertAtEnd(T val) {
        Node<T> newNode = new Node<T>(val);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }
    public boolean isContains(T value) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.value.compareTo(value) == 0) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
    public void insertUniqueValueFromFile(LinkedList<T> list, String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    try {
                        T intValues = (T) Integer.valueOf(value);
                        if (!isContains(intValues)) {
                            insertAtEnd(intValues);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid value: " + value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int search(int value) {
        int accesses = 0;
        Node<T> temp = head;
        while (temp != null) {
            accesses++;
            if (temp.value.compareTo(value) == 0) {
                return accesses; //Value Found
            }
            temp = temp.next;
        }
        return -1; //Value not found
    }
    public void searchFromFile(String fileName) {
        int totalAccesses = 0;
        int totalValues = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    int intValue = Integer.parseInt(value.trim());
                    int accesses = search(intValue);
                    if (accesses != -1) {
                        totalAccesses = totalAccesses + accesses;
                        totalValues = totalValues + 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double averageAccesses = (double) totalAccesses / totalValues;
        System.out.println("Total Memory Accesses: " + totalAccesses);
        System.out.println("Total Values Found: " + totalValues);
        System.out.println("Average Memory Accesses: " + averageAccesses);
    }
    public void display() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.value + " " );
            temp = temp.next;
        }
        System.out.println(" ");
    }
}