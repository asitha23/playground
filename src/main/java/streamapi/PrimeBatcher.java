package streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Gatherer;

public class PrimeBatcher {

    // 1. Helper: Prime Check Function
    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    // 2. Define the Gatherer
    public static Gatherer<Integer, List<Integer>, List<Integer>> gatherPrimes(int batchSize) {
        return Gatherer.ofSequential(
            // Initializer: Create a new list for each batch
            ArrayList::new,
            // Integrator: Filter primes and batch them
            (state, element, downstream) -> {
                if (isPrime(element)) {
                    state.add(element);
                    if (state.size() == batchSize) {
                        downstream.push(new ArrayList<>(state));
                        state.clear();
                    }
                }
                return true; // Keep processing
            },
            // Finisher: Emit remaining elements if any
            (state, downstream) -> {
                if (!state.isEmpty()) {
                    downstream.push(new ArrayList<>(state));
                }
            }
        );
    }

}
