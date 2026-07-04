package interview;

import java.util.*;
import java.util.stream.Collectors;

public class JB {

    void main() {

    }
}

class VisitorCount {
    Map<Long, Long> count(Map<String, UserStats>... input) {
        if  (input == null || input.length == 0) {
            return Collections.emptyMap();
        }
        return Arrays.stream(input)
                .filter(Objects::nonNull)
                .flatMap(map -> map.entrySet().stream())
                .filter(this::isValid)
                .filter(e -> e.getValue().visitCount().isPresent())
                .collect(Collectors.toMap(ek -> Long.parseLong(ek.getKey()), e -> e.getValue().visitCount().get(), Long::sum));

    }
    boolean isValid(Map.Entry<String, UserStats> input) {
        if (input.getKey() != null) {
            try {
                Long.parseLong(input.getKey());
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return (input.getValue() != null);
    }
}

record UserStats(Optional<Long> visitCount) {}

