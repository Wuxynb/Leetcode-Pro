package algorithms;

public class Recursion {
    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi();
        hanoi.hanoi(3, 'A', 'B', 'C');
    }

    /**
     * link: https://blog.csdn.net/qq_44096670/article/details/112003723
     */
    static class Hanoi {
        /**
         * 将n个盘子从 柱子p1 移动到 柱子p3
         * <p>
         *     <ul>
         *         <li>当 n == 1时，直接将盘子从 A 移动到C</li>
         *         <li>当 n > 1时，可以拆分成3大步骤
         *             <ol>
         *                 <li>将 n– 1 个盘子从 A 移动到B</li>
         *                 <li>将编号为 n 的盘子从 A 移动到C</li>
         *                 <li>将 n– 1 个盘子从 B 移动到C</li>
         *             </ol>
         *         </li>
         *     </ul>
         * </p>
         * <ul>
         *      <li>T(n) = 2 ∗ T(n − 1) + O(1) => T(n) = 2^n - 1 因此</li>
         *      <li>时间复杂度是：O(2n)</li>
         *      <li>空间复杂度：O(n)</li>
         * </ul>
         *
         * @param n  盘子个数 1~n
         * @param p1 柱子 p1 -- 起始位置
         * @param p2 柱子 p2 -- 中介位置
         * @param p3 柱子 p3 -- 终点位置
         */
        public void hanoi(int n, char p1, char p2, char p3) {
            if (n == 1) move(1, p1, p3);
            else {
                hanoi(n - 1, p1, p3, p2); // 将p3看作中间柱子，将n-1个碟子从p1移动到p2
                move(n, p1, p3); // 把 n 从 p1移动到 p3
                hanoi(n - 1, p2, p1, p3); // 将p1看作中间柱子，将n-1个碟子从p2移动到p3
            }
        }

        public void move(int n, char p1, char p2) {
            System.out.println("将" + n + "从" + p1 + "移动到" + p2);
        }
    }
}
