package algorithms;

/**
 * 知识汇总：https://www.cnblogs.com/zwx7616/p/10896848.html
 */
public class GameTheory {
    public static void main(String[] args) {
        bashGame(6, 2);
    }

    /**
     * 巴什博弈
     * 一堆n个物品，两名选手轮流从中取出1~m个，最后取光者胜。用一个数字（x）表示一堆物品中剩下的数量。
     * <p>
     * 还剩0个物品是一种必败态。当物品还剩下0个、就是一种必输的局势，被称为必败态。
     * <p>
     * 还剩m+1个物品也是一种必败态。因为如果你最多取m个，留给后手的一定小于或等于m个，所以这也是必败态。
     * <p>
     * 接着我们来讨论物品还剩（m+1+k）的情况：
     * <p>
     * k=0，转化为m+1，是一种必败态。
     * <p>
     * k>0时，有两种情况，
     * 第一种是0<k<=m，这是一种必胜态，一次性拿走k个，然后留给对手m+1个，给对手必败态。
     * 第二种情况是k>m，有k=t(m+1)+r，其中t>=0、0<=r<=m，所以我们很明显可以看出，如果局面是t(m+1)相当于进行了t次(m+1)操作，是一种必败态；
     * 又因为0<=r<=m，一次性取完r，留给对手必败态，自己就是必胜态。
     * 所以，综上所述：若 <b> n % (m + 1) == 0 </b>先手必败、反之先手必胜。
     *
     * <p>
     * summary:<br>
     * 如果我们要取n个数，每次最少报一个，最多报m个，我们可以找到这么一个整数k和r，使n=k*（m+1）+r，代入上面的例子我们就可以知道，
     * 如果r=0，那么先手必败；否则，先手必胜
     * </p>
     * <p>
     * Example：
     * n = 6, m = 2
     * (1, 2) -> (1, 2)  每次给对手剩 m+1=3 个
     * (1, 2) -> (2, 1)
     * <p>
     * (2, 1) -> (1, 2)
     * (2, 1) -> (2, 1)
     * Result：先手必败
     */
    public static void bashGame(int n, int m) {
        if (n % (m + 1) == 0) System.out.println("先手必败。");
        else System.out.println("先手必胜。");
    }

    /**
     * 威佐夫博弈（Wythoff Game）
     * 有两堆各若干个物品，两人轮流从某一堆或同时从两堆中取同样多的物品，每次至少取一个、多的不限。A先手。
     * <p>
     * 我们用(ak,bk)表示两堆物品数量局势，若A面对（0，0），那么A输，这种先手必输的局势，我们称为奇异局势。同样的我们可以得到（0，0）、（1，2）、
     * （3，5）、（4，7）、（6，10）、（8，13）、（9、15）、（11，18），（12，20）等等。我们在这些局势前很容易发现ak是未在前面出现过的最小自然数
     * ，又有：bk=ak+k。我们的ak也可以由[k*（√5+1）] / 2。
     * <p>
     * 所以我们先求出差，<b> 差值*黄金分割比==最小者 </b> 后手赢、否则先手赢。
     */
    public static void wythoffGame(int n1, int n2) {
        double r = (Math.sqrt(5) + 1) / 2; // 黄金分割比
        int d = (int) (Math.abs(n1 - n2) * r);
        if (d == Math.min(n1, n2)) System.out.println("先手必败");
        else System.out.println("先手必胜");
    }

    /**
     * 尼姆博弈（Nimm Game）：
     * <p>
     * 尼姆博弈指的是这样一个博弈游戏：有任意堆物品，每堆物品的个数是任意的，双方轮流从中取物品，每一次只能从一堆物品中取部分或全部物品，最少取一件，
     * 取到最后一件物品的人获胜。
     * <p>
     * 结论就是：把每堆物品数全部异或起来，如果得到的值为0，那么先手必败，否则先手必胜。
     * <p>
     * link: https://www.h3399.cn/202006/774909.html
     */
    public static void nimmGame(int[] ns) {
        int res = 0;
        for (int n : ns) {
            res ^= n;
        }
        if (res == 0) System.out.println("先手必败");
        else System.out.println("先手必胜");
    }

    /**
     * 斐波那契博弈
     * 一共n个物品，两人轮流取出1~任意个，但是不能取完，以后每次取的物品都不能超过上一次的2倍，取完者胜。
     * <p>
     * 我们令n代表，还有多少个物品，试着推导一下：
     * <p>
     * n=2，后手win。先手取一个，后手就能全部取完。
     * <p>
     * n=3，后手win。先手无论取一个还是两个，后手都可以一次性取完。
     * <p>
     * n=4，先手win。若先手取一个，后手剩3个，状态转移至n=3，当n=3时，后手先取，先手win。
     * <p>
     * n=5，后手win。先手取一个，剩余4个，状态转移至n=4，后手先取，后手赢。若先手取两个或者三个，后手可以一下全部取完获胜。
     * <p>
     * n=6，先手win。先手取一个，剩余5个，状态转移n=5，后手先取，先手赢。
     * <p>
     * n=7，先手win。先手取两个，状态转移至n=5，后手先取，先手赢。
     * <p>
     * n=8，后手win。先手取一个，状态转移n=7，后手赢。
     * <p>
     * 等等等等，不再陈述……
     * <p>
     * 我们不难发现，若n是斐波那契数列，后手赢。反之先手赢。
     */
    public static void fibonacciGame(int[] f, int n) {
        f[0] = f[1] = 1;
        for (int i = 2; f[i - 1] < n; i++) {
            f[i] = f[i - 1] + f[i - 2];
            if (f[i] == n) {
                System.out.println("先手必败");
                return;
            }
        }
        System.out.println("先手必胜");
    }
}
