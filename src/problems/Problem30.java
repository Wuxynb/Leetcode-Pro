package problems;

import java.util.*;

/**
 * 30. 串联所有单词的子串
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 *
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 示例 3：
 *
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 *
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/
 */
public class Problem30 {

    public static void main(String[] args) {
        System.out.println(findSubstringPlus("pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkm" +
                        "yxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhg" +
                        "ilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvay" +
                        "fsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjh" +
                        "ducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpi" +
                        "pguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdapr" +
                        "xmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojeg" +
                        "ggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinye" +
                        "toybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllph" +
                        "shivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroq" +
                        "oclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqt" +
                        "yeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireih" +
                        "xsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqg" +
                        "cdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbpr" +
                        "justquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvr" +
                        "fhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiah" +
                        "arkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkv" +
                        "kofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel",
                new String[]{"dhvf", "sind", "ffsl", "yekr", "zwzq", "kpeo", "cila", "tfty", "modg", "ztjg", "ybty",
                        "heqg", "cpwo", "gdcj", "lnle", "sefg", "vimw", "bxcb"}));
    }

    public static List<Integer> findSubstringPlus(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || words == null || s.length() == 0 || words.length == 0) return ans;
        int oneLen = words[0].length();
        int totalLen = words.length * oneLen;
        int nums = words.length;
        Map<String, Integer> map = new HashMap<>();
        for (String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        for (int i = 0; i < oneLen; i++) {
            int left = i, right = i, count = 0;
            Map<String, Integer> tempMap = new HashMap<>();
            while (right + oneLen <= s.length()) {
                String s1 = s.substring(right, right + oneLen);
                right += oneLen;
                if (!map.containsKey(s1)) {
                    count = 0;
                    left = right;
                    tempMap.clear();
                } else {
                    count++;
                    tempMap.put(s1, tempMap.getOrDefault(s1, 0) + 1);

                    // 若单词数大于默认，则需退出完上一个s1位置后结束循环
                    while (tempMap.getOrDefault(s1, 0) > map.getOrDefault(s1, 0)) {
                        String s2 = s.substring(left, left + oneLen);
                        tempMap.put(s2, tempMap.getOrDefault(s2, 0) - 1);
                        count--;
                        left += oneLen;
                    }
                    if (count == nums) ans.add(left);
                }
            }
        }

        return ans;
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (null == s || null == words || s.length() == 0 || words.length == 0) return ans;
        int oneLen = words[0].length();
        int nums = words.length;
        int totalLen = oneLen * nums;
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < s.length() - totalLen + 1; i++) {
            String temp = s.substring(i, i + totalLen);
            Map<String, Integer> tempMap = new HashMap<>();
            for (int j = 0; j < temp.length(); j += oneLen) {
                String substring = temp.substring(j, j + oneLen);
                tempMap.put(substring, tempMap.getOrDefault(substring, 0) + 1);
            }
            if (map.equals(tempMap)) ans.add(i);
        }

        return ans;
    }

    /*
        Out of Memory -- Permutation
     */
    public static List<Integer> findSubstringPermutation(String s, String[] words) {
        Map<String, String> strings = new HashMap<>();
        backtrack(words, strings, "", 0, new ArrayList<>());
        List<String> list = new ArrayList<>(strings.keySet());
        System.out.println(list.size());

        Map<Integer, Integer> map = new HashMap<>();
        for (String str : list) {
            for (int i = 0; i < s.length(); i++) {
                int idx = s.indexOf(str, i);
                if (idx == -1) break;
                else map.putIfAbsent(idx, idx);
                i = idx;
            }
        }
        Set<Integer> set = map.keySet();
        return new ArrayList<>(set);
    }

    public static void backtrack(String[] words, Map<String, String> strings, String str, int n, List<Integer> t) {
        if (n >= words.length) strings.put(str, "");
        else {
            for (int i = 0; i < words.length; i++) {
                if (!t.contains(i)) {
                    t.add(i);
                    backtrack(words, strings, str + words[i], n + 1, t);
                    t.remove(Integer.valueOf(i));
                }
            }
        }
    }
}
