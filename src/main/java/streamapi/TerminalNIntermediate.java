package streamapi;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.IO.println;

public class TerminalNIntermediate {

    List<String> stringList = List.of("A", "B","C");

    protected void printOutExecutionOrder() {
        Stream<String> streamOut = stringList.stream()
                .filter(s -> {
                    println("Filtering " + s);
                    return s.startsWith("A");
                });
        println("Strem processing completed" + streamOut);
        /*
           It's worthy to remember that intermediate operation (method) will not be invoked unless terminal operation is
           called upon the stream
           Ex : streamOut.count() will trigger execution of the filter lambda
        */
    }

    void main() {
        TerminalNIntermediate terminalNIntermediate = new TerminalNIntermediate();
        terminalNIntermediate.printOutExecutionOrder();
    }
}
