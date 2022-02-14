package algorithms;

public class JointFindSet {
    int[] fa;

    public static void main(String[] args) {

    }

    /*
        1, 2, 3, ..., n的n个元素
        一开始，我们先将它们的父节点设为自己
     */
    public void init(int n) {
        fa = new int[n];
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
        }
    }

    /*
        递归的写法实现对代表元素的查询：一层一层访问父节点，直至根节点（根节点的标志就是父节点是本身）。
        要判断两个元素是否属于同一个集合，只需要看它们的根节点是否相同即可
     */
    public int find(int x) { // 合并（路径压缩）
        if(x == fa[x]) return x;
        else{
            fa[x] = find(fa[x]);  // 父节点设为根节点
            return fa[x];         // 返回父节点
        }
    }

    /*
        先找到两个集合的代表元素，然后将前者的父节点设为后者即可
     */
    public void merge(int i, int j) {
        fa[find(i)] = find(j);
    }
}
