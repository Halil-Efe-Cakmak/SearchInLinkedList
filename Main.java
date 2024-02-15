import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        try {
            linkedList.insertUniqueValueFromFile(linkedList, "src/Source.txt");
            linkedList.display();
        } catch (IOException E) {
            System.out.println("Error");
        }
        long startFirst = System.nanoTime();

        linkedList.searchFromFile("src/Search.txt");

        long finishFirst = System.nanoTime();

        System.out.println(finishFirst - startFirst);
    }
}