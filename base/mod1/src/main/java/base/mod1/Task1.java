package base.mod1;

import java.util.stream.IntStream;

public class Task1 {

    public static void main(String[] args) {
        int result = IntStream.range(0, 1001)
                .filter(x -> x % 3 == 0 || x % 5 == 0)
                .sum();
        System.out.println(result);
    }

}
