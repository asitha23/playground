

import java.util.List;
import java.util.Set;
import java.util.Stack;

import static java.lang.IO.println;

class ValidParentheses {
    
    List<Character> openBr =  List.of('(', '{', '[');
    List<Character> closeBr = List.of(')', '}', ']');

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;

        Stack<Character> openOrder = new Stack<>();

        for (char c : s.toCharArray()) {
            if (closeBr.contains(c) && (openOrder.isEmpty() || closeBr.indexOf(c) != openBr.indexOf(openOrder.pop())) ) {
                    return false;
            } else if (openBr.contains(c)){
                openOrder.push(c);
                if (openOrder.size() > s.length() /2)
                    return false;
            }
        }
        return openOrder.isEmpty();
    }
}

void main() {
    ValidParentheses sol = new ValidParentheses();
    println(sol.isValid("([])"));
}