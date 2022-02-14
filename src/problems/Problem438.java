package problems;

import java.util.ArrayList;
import java.util.List;

public class Problem438 {
    public static void main(String[] args) {
        String s = "ivxqakfyaqahufxfizupjrkkifjlbfqfeprqrfjvxnubntdtlvz", p =
        "vxapufjqtnaviffihkpyrbrzfjenqtxlxfqkfjvazubkrdqluf";
        System.out.println(findAnagrams(s, p));
        System.out.println(findAnagramsOpt(s, p));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        char[] ss = s.toCharArray(), ps = p.toCharArray();
        List<Integer> ans = new ArrayList<>();
        int[] hash1 = new int[26], hash2 = new int[26];
        for (char c : ps) hash2[c - 'a']++;
        for (int left = 0, right = 0; right < ss.length; right++) {
            int idxR = ss[right] - 'a';
            hash1[idxR]++;
            while (hash1[idxR] > hash2[idxR]) {
                int idxL = ss[left] - 'a';
                hash1[idxL]--;
                left++;
            }
            if (right - left + 1 == ps.length) ans.add(left);
        }
        return ans;
    }

    public static List<Integer> findAnagramsOpt(String s, String p) {
        int[] hash = new int[26];
        char[] ss = s.toCharArray(), ps = p.toCharArray();
        for (char c : ps) hash[c - 'a']++;
        List<Integer> ans = new ArrayList<>();
        int left = 0, right = 0;
        // 如果遇到没有的字符，high原地等待；low++遍历到这个字符后（即low比high多1后），下一次high也会消耗掉这个没有的字符
        while (right < ss.length) {
            if (hash[ss[right] - 'a'] > 0) {
                hash[ss[right++] - 'a']--;
                if (right - left == ps.length) ans.add(left);
            } else hash[ss[left++] - 'a']++;
        }
        return ans;
    }

    public static List<Integer> findAnagramsBf(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        char[] charss = s.toCharArray(), charsp = p.toCharArray();
        int[] hash = new int[26];
        for (char c : charsp) hash[c - 'a']++;
        for (int i = 0; i <= charss.length - charsp.length; i++) {
            System.out.println("i: " + i);
            int[] tmpHash = new int[26], idxHash = new int[26];
            boolean f = true;
            for (int j = i; j < i + charsp.length; j++) {
                int idx = charss[j] - 'a';
                tmpHash[idx]++;
                if (hash[idx] == 0 || tmpHash[idx] > hash[idx]) {
                    if (hash[idx] == 0) i = j;
                    else if (tmpHash[idx] > hash[idx]) i = idxHash[idx] - 1;
                    f = false;
                    break;
                }
                idxHash[idx] = idxHash[idx] != 0 ? idxHash[idx] : j + 1;
            }
            if (f) ans.add(i);
        }
        return ans;
    }
}
