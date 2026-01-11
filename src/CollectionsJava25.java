import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class CollectionsJava25 {

    LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();

    List<Integer> list = new ArrayList<>();


    void linkedHashSetMethod() {
        linkedHashSet.add(1);
        linkedHashSet.addFirst(2);
        linkedHashSet.addLast(3);
        linkedHashSet.getFirst();

        list.getLast();
    }
}
