### 期望DP
#### 概念
- 期望
  > - 性质  
  >   $C 为常数，X,Y 为随机变量  $
  >   1. $E(C) = C $ 
  >   2. $E(Cx)=CE(x) $ 
  >   3. $E(x+y)=E(x)+E(y) $ 
  >   4. $当 x,y 相互独立时，E(xy)=E(x)E(y)$
  >   5. $全期望公式是利用条件期望计算数学期望的公式：EY = E[E(Y|X)]$。
  
- 期望DP  
&emsp;&emsp;期望 DP，是一种 DP，所以核心思想与一般的 DP 差不多。一般，设 $f[i][j]$ 为此状态的期望，状态转移时，将上一个状态的值和花费的和乘以转移的概率求和。（如成环，高斯消元）

- 常见状态转移方程  
  
  **概率DP主要用于求解期望、概率等题目。**
  
  期望dp几种常见设转移方程数组的方法
  
    1. 设 $f[i]$ 表示的是由 $i$ 状态变成最终状态的期望 (由末状态逆推)
    2. 按照题意直接设
    3. 把选择的东西加入数组，如 $f[i][j]$ 表示第i个物品选j个的期望或 $f[i][j]$ 表示有i个A 物品，j个B物品的期望(结合第一种的话就是, $dp[i][j]$ :已经有i个A ，j个B 离达到最终状态还差多少期望)
  
  求转移方程  
  > 先考虑逆向（从最终状态的解开始逆推）；  
  > 如果逆向没有思路，则考虑正向；  
  > 一般而言，末状态已经决定了的话，就是逆向递推了； 
  > 也可以先看看边界在哪边，从边界开始递推转移。  
  >
  > **一般求概率是正推，求期望是逆推。通过题目可以体会到这点**

#### 方法
##### 方法一：直接定义期望状态
- [217. 绿豆蛙的归宿](https://www.acwing.com/problem/content/description/219/)  
  题意：给定一个起点为 1，终点为 n 的有向无环图。到达每一个顶点时，如果有 K 条离开该点的道路，可以选择任意一条道路离开该点，并且走向每条路的概率为 $1\over K$。问你从 1 出发走到 n 的路径期望总长度是多少。  

  &emsp;&emsp;这道题的终点很明确，那就是走到 n 即停止。对于期望 DP，我们一般采用逆序的方式来定义状态，即考虑从当前状态到达终点的期望代价。因为在大多数情况下，终点不唯一，而起点是唯一的。
  &emsp;&emsp;我们定义 $dp(i)$ 为从 $i$  出发走到终点 $n$ 的路径期望总长度，根据全期望公式，得到（设​ $Gi$ 为从 $i$ 的边的集合）：
  $$
    dp(i)=\sum_{e∈Gi}{dp(e_{to})+e_{const}\over|Gi|}
  $$
  &emsp;&emsp;因为这是一个有向无环图，每个点需要其能到达的点的状态，故我们采用拓扑序的逆序进行计算即可。

代码：
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P217 {
    static int maxn = 100005;
    static double[] dp = new double[maxn]; // dp[i]，表示从 i 到 n 所经过的路径总长度
    static int[] degrees = new int[maxn];
    static List<int[]>[] adjacency;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Arrays.fill(dp, -1);
        int n = scan.nextInt(), m = scan.nextInt();
        adjacency = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) adjacency[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = scan.nextInt(), v = scan.nextInt(), w = scan.nextInt();
            adjacency[u].add(new int[]{v, w});
            degrees[u]++;
        }
        System.out.printf("%.2f", dfs(1));
    }

    public static double dfs(int u) {
        if (dp[u] != -1.) return dp[u];
        dp[u] = 0;
        for (int[] vw : adjacency[u]) {
            int v = vw[0], w = vw[1];
            dp[u] +=  (w + dfs(v)) / degrees[u];
        }
        return dp[u];
    }
}  
```

##### 方法二：利用期望的线性性质
&emsp;&emsp;根据期望的线性性质，$E[aX+bY]=aE[X]+bE[Y]$ 。所以另外一种求期望的方式是分别求出每一种代价产生的期望贡献，然后相加得到答案。在本题中，路径期望总长度等于每条边产生的期望贡献之和。而每条边产生又等于经过这条边的期望次数乘这条边的代价。所以，我们只需要算每条边的期望经过次数即可。  

​        边 $ (u,v,w) $ 的期望经过次数是不好直接算的，但如果我们能算得点 $u$ 的期望经过次数为 $dp(u)$，那么边 $(u,v,w) $的期望经过次数是 $dp(u)∗({1\over|Gu|}) $，对答案的贡献就是 $w∗dp(u)∗({1\over|Gu|})$  。

​        如何计算点 $u$ 的期望经过次数 $dp(u)$呢？我们依然考虑 DP 的方式，首先有 $dp(u)=1$，转移采取刷表的方式：  
$$
dp(e_{to})←dp(u)∗{1\over|Gu|},e∈Gu
$$
​        在用边 e 刷表的同时，边 e 的贡献就可以计算了，十分简洁。因为这种方法计算答案十分的便捷，而且适用范围广，所以这种『利用期望的线性性质，单独计算贡献的方法』是我们计算期望首选的方法。

代码：
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int maxn = 100005;
    static double[] dp = new double[maxn]; // dp[i]表示点i的期望经过次数（概率）
    static int[] inDegs = new int[maxn];
    static int[] outDegs = new int[maxn];
    static List<int[]>[] adjacency;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        adjacency = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) adjacency[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = scan.nextInt() - 1, v = scan.nextInt() - 1, w = scan.nextInt();
            adjacency[u].add(new int[]{v, w});
            inDegs[v]++; // 统计点 v 的入度
            outDegs[u]++; // 统计点 u 的出度
        }
        solve();
    }
  
    public static void solve() {
        int[] q = new int[maxn]; // 存储入度为0的点（拓扑排序）-- 队列角色
        int l = 0, r = 0;
        q[r++] = 0;
        double ans = 0.;
        dp[0] = 1.; // 初始化，第0个起始点的期望经过次数为 1
        while (r - l >= 1) {
            int u = q[l++];
            for (int[] vw : adjacency[u]) {
                int v = vw[0], w = vw[1];
                dp[v] += dp[u] / outDegs[u]; // 计算点v的期望经过次数
                ans += dp[u] * w / (double) outDegs[u]; // 计算边 (u,v) 的贡献值
                if (--inDegs[v] == 0) q[r++] = v; // 当点v的入度为0时，选择从点v开始继续计算
            }
        }
        System.out.printf("%.2f", ans);
    }
}
```

##### 方法三：使用期望的定义计算

​        [D 伊利亚和自动扶梯](http://codeforces.com/problemset/problem/518/D)
​        题意：有 n 个人排成一列，每秒中队伍最前面的人有 p 的概率走上电梯（一旦走上就不会下电梯），或者有 1−p 的概率不动。问你 T 秒过后，在电梯上的人的期望。
​        *注意：在本题这样一个情况中，方法一是用不了的，因为我们的结束状态不明确。* 

&emsp;&emsp;如果 $X$ 是离散的随机变量，输出值为 $x1,x2,...，$ 输出值相应的概率为 $p1,p2,...$ 那么期望值是一个无限数列的和（如果不收敛，那么期望不存在）：
$$
    E[x]=\sum_ip_ix_i
$$
&emsp;&emsp;在本题中，如果设 $dp(i,j)$ 为 $i$ 秒过后，电梯上有 $j$ 个人的概率，那么答案是：
$$
    \sum_{0≤k≤n}dp(T,K)∗K
$$
&emsp;&emsp;所以我们只需要求 $dp(i,j)$ 就可以了，初始值 $dp(0,0)=1$ 就可以了，仍然是采用刷表法：
$$
dp(i+1,j+1)←dp(i,j)∗p \\ dp(i+1,j)←dp(i,j)∗(1−p)
$$
代码：

```java
import java.util.Scanner;

public class Main {
    static int N = 2005;
    static double p;
    static double[][] dp = new double[N][N]; // dp[i][j]表示在i时刻电梯上有j个人的概率

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), p = scan.nextInt(), t = scan.nextInt();
        dp[0][0] = 1.;
        for (int i = 0; i < t; i++) {
            dp[i + 1][n] += dp[i][n];
            for (int j = 0; j < n; j++) {
                if (dp[i][j] > 1e-10) {
                    dp[i + 1][j + 1] += dp[i][j] * p;
                    dp[i + 1][j] += dp[i][j] * (1 - p);
                }
            }
        }
        double ans = 0;
        for (int i = 0; i <= n; i++) ans += i * dp[t][i];
        System.out.println(ans);
    }
}
```
- 方法二
&emsp;&emsp;那么之前提到的适用范围广的方法二，是否能在这里用呢？答案是肯定的。  
&emsp;&emsp;延续方法三的 DP，我们不妨将状态之间的转移抽象成边，只不过只有 $dp(i,j)$ 到 $dp(i+1,j+1)$ 的边才有为 1 的边权，其余都为 0。因为这个 DP 涵盖了所有可能出现的情况，所以我们仍然可以利用期望的线性性质，在刷表的过程中进行计算答案。  
&emsp;&emsp;本题中，没有直观的边的概念，但是我们可以将状态之间的转移抽象成边，由于  $dp(i,j)$  到 $dp(i+1,j+1)$ 这一个转移是对答案有 1 的贡献的，所以我们将它们之间的边权赋为 1。  
&emsp;&emsp;这一题将方法二抽象化了，实际上大多数题并非是直观的，而是这种抽象的形式。  

代码：

```java
import java.util.Scanner;

public class Main {
    static int maxn = 2005;
    static double p;
    static double[][] dp = new double[maxn][maxn];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); double p = scan.nextDouble(); int t = scan.nextInt();
        double ans = 0.;
        dp[0][0] = 1.;
        for (int i = 0; i < t; i++) {
            dp[i + 1][n] += dp[i][n];
            for (int j = 0; j < n; j++) {
                if (dp[i][j] > 1e-10) {
                    dp[i + 1][j + 1] += dp[i][j] * p;
                    dp[i + 1][j] += dp[i][j] * (1 - p);
                    ans += dp[i][j] * p;
                }
            }
        }
        System.out.println(ans);
    }
}
```

- [BZOJ Tyvj1952 Easy](https://darkbzoj.cc/problem/3450)
题意：给定一个序列，一些位置未确定（是 $o$ 与 $x$ 的几率各占 50%）。对于一个 $ox $ 序列，连续 $x$ 长度的 $o$ 会得到 $x^2$ 的收益，请问最终得到的序列的期望收益是多少？

分析：
&emsp;&emsp;这个题如果一段一段的处理，实际上并不是很好做。我们观察到 $(x+1)^2−x^2=2x+1$ ，那么根据期望的线性性质，我们可以单独算每一个字符的贡献。我们设 $dp_i$ 为考虑前 $ii$ 个字符的期望得分，$li$ 为以 $i$ 为结尾的 $comb$ 的期望长度，$Comb$i 为第 $i$个字符，那么有 3 种情况：
$$
\begin{aligned}
  & 1. s_i=o ，则 dp_i=dp_{i−1}+l_{i−1}∗2+1,l_i=l_{i−1}+1 \\
  & 2. s_i=x ，则 dp_i=dp_{i−1} \\  
  & 3. s_i=? ，则 dp_i=dp_{i−1}+{l_i∗2+1\over2},l_i={l_{i−1}+1\over2} \\
\end{aligned}
$$
&emsp;&emsp;对于前两种情况，其实是非常直观的，对于第三种情况，实际上是求了一个平均长度。例如 $?oo$ ，两种情况的长度  $l_i$ 分别为 $[0,1,2] $和$ [1,2,3] $，但是求了平均之后，长度 $l_i $变成了 $[0.5,1.5,2.5] $，这样由于我们的贡献是一个关于长度的一次多项式$ (2x+1) $，所以长度平均之后，贡献也相当于求了一个平均，自然能够求得正确的得分期望。

代码：

```java
import java.util.Scanner;

public class Main {
    static int maxn = 300005;
    static double[] dp = new double[maxn]; // dp[i]表示第i次点击的期望得分
    static double[] comb = new double[maxn]; // 贡献度数组，表示第i次点击时o的期望长度

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String s = scan.nextLine();
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            if (c == 'o') {
                dp[i] = dp[i - 1] + comb[i - 1] * 2 + 1;
                comb[i] = comb[i - 1] + 1;
            } else if (c == 'x') {
                dp[i] = dp[i - 1];
                comb[i] = 0;
            } else {
                dp[i] = dp[i - 1] + (comb[i - 1] * 2 + 1) / 2.;
                comb[i] = (comb[i - 1] + 1) / 2.;
            }
        }
        System.out.println(dp[n]);
    }
}
```
  思考：如果长度为 $a$ 的 $comb$ 的贡献为 $a^3$ 时该如何解决？
> Tips：由于 $(a+1)^3−a^3=3a^2+3a+1$ ，所以我们要维护 $a^2$ 和 $a$ 的期望，注意 $Ea^2≠E^2a$，所以维护 $a^2$ 的期望是必要的。

- [BZOJ 4318 OSU!](https://darkbzoj.cc/problem/4318)
  题意：给定一个序列，每个位置为 $o$ 的几率为 $p_i$，为 $x$ 的几率为 $1−p_i$ 。对于一个 $ox$ 序列，连续 $x$ 长度的 $o$ 会得到 $x^3$的收益，请问最终得到的 $ox$ 序列的期望收益是多少？

分析：
        延续例三的思路，我们还是分别求每一个位置的贡献。根据  $(a+1)^3−a^3=3a^2+3a+1$ ，我们只需要维护 $l(i)$ 为以 $i$  为结尾的 $comb$ 的期望长度，$l_2(i)$ 为以 $i$  为结尾的 $comb$ 的长度平方的期望（不能一起维护，因为后者平方并不是前者）。注意 $E[a^2]≠E^2[a]$，所以维护 $a^2$ 的期望是必要的。

代码：

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        double p, l1 = 0, l2 = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            p = scan.nextDouble();
            // 一个点的贡献度为 comb(a) = (a+1)^3-a^3 = 3a^2+3a+1（a表示结尾的出度），故期望E为 comb(a)*pa
            ans += (3 * l2 + 3 * l1 + 1) * p; 
            l2 = (l2 + 2 * l1 + 1) * p; // E((x+1)^2) = E{x^2+2x+1} = E(x^2) + 2E(x) + 1
            l1 = (l1 + 1) * p; // E(x+1) = E(x) + 1
        }
        System.out.printf("%.1f", ans);
    }
}
```

### 总结
&emsp;&emsp;期望 DP 一般来说有它固定的模式，一种模式是直接 DP，定义状态为到终点期望，采用逆序计算得到答案。一种模式是利用期望的线性性质，对贡献分别计算，这种模式一般要求我们求出每种代价的期望使用次数，而每种代价往往体现在 DP 的转移之中。最后的两个例题是典型的分离变量，用期望的线性性质计算答案的例子，如果状态过于巨大，那么就得考虑分离随机变量了。

&emsp;&emsp;本总结只是解释了概率与期望 DP 的冰山一角，它可以变化多端，但那些实际上并不只属于概率与期望 DP，真正核心的内容，还是逃不出我们几种方法。

### 资料链接
[概率与期望DP](https://www.cnblogs.com/RioTian/p/15053904.html)
[动态规划之经典数学期望和概率DP](https://www.cnblogs.com/RioTian/p/14117154.html)
