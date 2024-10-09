package base.mod1;

public class Task2 {

    public static void main(String[] args) {
        int[][] x = { {20, 34, 2}, {9, 12, 18}, {3, 4, 5} };
        int min = Integer.MAX_VALUE;
        for (int[] sub_arr : x) {
            for (int value : sub_arr) {
                min = Math.min(min, value);
            }
        }
        System.out.println(min);
    }

}
