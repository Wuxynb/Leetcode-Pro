package algorithms;

import java.util.Comparator;

/**
 * <? extends T> 表示类型的上界，表示参数化类型的可能是T 或是 T的子类;
 * <p>
 * <? super T> 表示类型下界（Java Core中叫超类型限定），表示参数化类型是此类型的超类型（父类型），直至Object;
 *
 * @param <K> 类型参数，键的类型
 * @param <V> 类型参数，值的类型
 */
public class BinarySearchTree<K extends Comparable<? super K>, V> {
    TreeNode<K, V> root;
    Comparator<K> comparator;
    int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
        this.comparator = Comparator.naturalOrder();
    }

    public boolean insert(K key, V val) {
        TreeNode<K, V> node = new TreeNode<>(key, val);
        if (root == null) {
            root = node;
            this.size++;
            return true;
        }

        TreeNode<K, V> parent, current = root;
        while (true) {
            parent = current;
            if (comparator.compare(key, current.key) == 0) {
                return false;
            } else if (comparator.compare(key, current.key) < 0) {
                current = current.left;
                if (current == null) {
                    parent.left = node;
                    this.size++;
                    return true;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = node;
                    this.size++;
                    return true;
                }
            }
        }
    }

    public TreeNode<K, V> get(K key) {
        if (this.root == null) return null;
        TreeNode<K, V> current = this.root;
        while (comparator.compare(current.key, key) != 0) {
            if (comparator.compare(key, current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current == null) return null;
        }

        return current;
    }

    /**
     * Example:<br>
     * 3                   <br>
     * /   \                 <br>
     * 1       6               <br>
     * \     /  \             <br>
     * 2   4    7            <br>
     * \               <br>
     * 5              <br>
     * 删除节点 3 <br>
     * <p>
     * 此时 3的 后继节点successor为 4, 后继节点的父节parent点为 6， current为节点4的左子树 null<br>
     * <p>
     * 4                   <br>
     * \                  <br>
     * 6                 <br>
     * / \                <br>
     * 5   7               <br>
     **/
    private TreeNode<K, V> successor(TreeNode<K, V> node) {
        // parent: 后继节点的父节点，successor: 后继节点，current：当前遍历的节点
        TreeNode<K, V> parent = node, successor = node, current = node.right;
        while (current != null) {
            parent = successor; // 后继节点父节点首先保存后继节点的状态
            successor = current;
            current = current.left; // 后继节点 不断的向左更新
        }
        // 假如我们找到的后继节点不直接是要删除节点的右节点，而是在其右节点那条子树上面最小的一个节点
        if (successor != node.right) {
            // 后继节点的父节点断开其与后继节点左边的引用，重新连接上后继节点的右子节点（因为后继节点是没有左子节点的，所以要保存之前树的状态，
            // 还要把后继节点的右子节点处理一下，不管其存在不存在）
            parent.left = successor.right;
            // 这时候后继节点的右边已经空了 上一条语句已经将其给了自己父节点的左子节点，然后让后继节点的右边连接要删除节点的右子树
            successor.right = node.right;
        }

        return successor;
    }

    public boolean delete(K key) {
        TreeNode<K, V> current = this.root, parent = new TreeNode<>();
        boolean isRightChild = true; // 判断current是否是parent的右孩子
        while (comparator.compare(current.key, key) != 0) {
            parent = current;
            if (comparator.compare(key, current.key) < 0) {
                current = current.left;
                isRightChild = false;
            } else {
                current = current.right;
                isRightChild = true;
            }
            if (current == null) return false; // not found
        }

        // current 即为要删除的节点，parent为其父节点
        // 要删除的节点为叶子节点
        if (current.left == null && current.right == null) {
            if (current == this.root) this.root = null;
            else {
                if (isRightChild) parent.right = null;
                else parent.left = null;
            }
            this.size--;
            return true;
        } else if (current.left == null) { // 要删除的节点只有右孩子
            if (current == this.root) {
                this.root = current.right;
            } else if (isRightChild) parent.right = current.right;
            else parent.left = current.right;
            this.size--;
            return true;
        } else if (current.right == null) { // 要删除的节点只有左孩子
            if (current == this.root) {
                this.root = current.left;
            } else if (isRightChild) parent.right = current.left;
            else parent.left = current.left;
            this.size--;
            return true;
        } else { // 要删除的节点有左右孩子
            TreeNode<K, V> successor = successor(current); // 找到要删除结点的后继结点
            if (current == this.root) {
                this.root = successor;
            } else if (isRightChild) parent.right = successor;
            else parent.left = successor;
            successor.left = current.left;
            this.size--;
            return true;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean empty() {
        return isEmpty();
    }

    public void preOrderTraverse() {
        System.out.print("[");
        preOrder(this.root);
        System.out.println("]");
    }

    private void preOrder(TreeNode<K, V> root) {
        if (root != null) {
            System.out.print(root.key + ": " + root.val + ", ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void inOrderTraverse() {
        System.out.print("[");
        inOrder(this.root);
        System.out.println("]");
    }

    private void inOrder(TreeNode<K, V> root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.key + ": " + root.val + ", ");
            inOrder(root.right);
        }
    }

    public void postOrderTraverse() {
        System.out.print("[");
        postOrder(this.root);
        System.out.println("]");
    }

    private void postOrder(TreeNode<K, V> root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.key + ": " + root.val + ", ");
        }
    }

    private static class TreeNode<K extends Comparable<? super K>, V> {
        private K key;
        private V val;
        private TreeNode<K, V> left;
        private TreeNode<K, V> right;

        public TreeNode() {

        }

        public TreeNode(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public TreeNode(K key, V val, TreeNode<K, V> left, TreeNode<K, V> right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getVal() {
            return val;
        }

        public void setVal(V val) {
            this.val = val;
        }

        public TreeNode<K, V> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<K, V> left) {
            this.left = left;
        }

        public TreeNode<K, V> getRight() {
            return right;
        }

        public void setRight(TreeNode<K, V> right) {
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
        int[] keys = new int[]{3, 1, 2, 6, 4, 7, 5};
        String[] vals = new String[]{"C", "A", "B", "F", "D", "G", "E"};
        for (int i = 0; i < keys.length; i++) {
            bst.insert(keys[i], vals[i]);
        }
        bst.preOrderTraverse();
        bst.inOrderTraverse();
        bst.postOrderTraverse();

        System.out.println("删除成功？---> " + bst.delete(3));

        bst.preOrderTraverse();
        bst.inOrderTraverse();
        bst.postOrderTraverse();
    }
}
