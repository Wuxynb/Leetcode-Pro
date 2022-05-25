package ojs.hdoj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Good Luck in CET-4 Everybody!
 * Time Limit: 1000/1000 MS (Java/Others)    Memory Limit: 32768/32768 K (Java/Others)
 * Total Submission(s): 20525    Accepted Submission(s): 12821
 * <p>
 * <p>
 * Problem Description
 * <p>
 * 大学英语四级考试就要来临了，你是不是在紧张的复习？也许紧张得连短学期的ACM都没工夫练习了，反正我知道的Kiki和Cici都是如此。当然，作为在考场浸润了十几载的当代大学生，Kiki和Cici更懂得考前的放松，所谓“张弛有道”就是这个意思。这不，Kiki和Cici在每天晚上休息之前都要玩一会儿扑克牌以放松神经。
 * “升级”？“双扣”？“红五”？还是“斗地主”？
 * 当然都不是！那多俗啊~
 * 作为计算机学院的学生，Kiki和Cici打牌的时候可没忘记专业，她们打牌的规则是这样的：
 * <p>
 * 1、  总共n张牌;
 * <p>
 * 2、  双方轮流抓牌；
 * <p>
 * 3、  每人每次抓牌的个数只能是2的幂次（即：1，2，4，8，16…）
 * <p>
 * 4、  抓完牌，胜负结果也出来了：最后抓完牌的人为胜者；
 * <p>
 * 假设Kiki和Cici都是足够聪明（其实不用假设，哪有不聪明的学生~），并且每次都是Kiki先抓牌，请问谁能赢呢？
 * 当然，打牌无论谁赢都问题不大，重要的是马上到来的CET-4能有好的状态。
 * <p>
 * Good luck in CET-4 everybody!
 * <p>
 * <p>
 * Input
 * <p>
 * 输入数据包含多个测试用例，每个测试用例占一行，包含一个整数n（1<=n<=1000）。
 * <p>
 * Output
 * <p>
 * 如果Kiki能赢的话，请输出“Kiki”，否则请输出“Cici”，每个实例的输出占一行。
 * <p>
 * Sample Input
 * <p>
 * 1
 * <p>
 * 3
 * <p>
 * Sample Output
 * <p>
 * Kiki
 * <p>
 * Cici
 * <p>
 * Author
 * lcy
 * <p>
 * Source
 * ACM Short Term Exam_2007/12/13
 * <p>
 * link: http://acm.hdu.edu.cn/showproblem.php?pid=1847
 */
public class P1847 {
    static int[] array = new int[15], sg = new int[1005];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        array[0] = 1;
        for (int i = 1; i <= 10; i++) array[i] = array[i - 1] * 2;
        int n;
        while (input.hasNext() && (n = input.nextInt()) != 0) {
            Arrays.fill(sg, -1);
            if (mex(n) != 0) System.out.println("Kiki");
            else System.out.println("Cici");
        }
        input.close();
    }

    public static int mex(int x) {
        if (sg[x] != -1) return sg[x];
        boolean[] visited = new boolean[1005];
        for (int i = 0; i <= 10; i++) {
            int temp = x - array[i];
            if (temp < 0) break;
            sg[temp] = mex(temp);
            visited[sg[temp]] = true;
        }
        for (int i = 0; ; i++) {
            if (!visited[i]) {
                sg[x] = i;
                break;
            }
        }
        return sg[x];
    }
}
