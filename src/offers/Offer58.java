package offers;

public class Offer58 {
    public static void main(String[] args) {
        System.out.println(reverseWords("a good   example "));
        System.out.println(reverseWordsDoublePointer("a good   example "));
    }

    public static String reverseWordsDoublePointer(String s) {
        int j = s.length() - 1, i = j;
        StringBuilder sb = new StringBuilder();
        while (i >= 0) {
            while (j >= 0 && s.charAt(j) == ' ') j--;
            i = j;
            while (i >= 0 && s.charAt(i) != ' ') i--;
            sb.append(s, i + 1, j + 1).append(" ");
            j = i;
        }
        return sb.toString().trim();
    }

    public static String reverseWords(String s) {
        String[] strs = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if (!strs[i].equals("")) sb.append(strs[i].trim()).append(" ");
        }
        return sb.toString().trim();
    }
}
