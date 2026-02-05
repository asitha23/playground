package collectionsframework;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayConcurrentModification {

    void main(){
        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("A");
        copyOnWriteArrayList.add("B");

        Iterator<String> iterator = copyOnWriteArrayList.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            iterator.remove(); // NOTE : CopyOnWriteArrayList will throw exception on remove
        }
    }
}
