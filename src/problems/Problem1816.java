package problems;

public class Problem1816 {
    public static void main(String[] args) {

    }

    public String truncateSentence(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == ' ') if (++cnt == k) return sb.toString();
            sb.append(c);
        }
        return sb.toString();
//        return String.join(" ", Arrays.copyOfRange(s.split(" "), 0, k));
    }
}
