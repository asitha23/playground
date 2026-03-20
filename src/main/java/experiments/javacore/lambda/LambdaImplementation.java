package experiments.javacore.lambda;

public class LambdaImplementation {

    InterfaceNotMarkedFunctional interfaceNotMarkedFunctional = System.out::println;
    InterfaceNotMarkedFunctional interfaceNotMarkedFunctional2 = (a) -> System.out.println(" lenght print " + a.length());

    void main() {
        interfaceNotMarkedFunctional.acceptSomeToPrint("something to print");
        interfaceNotMarkedFunctional2.acceptSomeToPrint("something to print for length");
    }
}
