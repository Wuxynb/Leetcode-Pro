package util;

import java.util.List;

public class NNode {
    public int val;
    public List<NNode> children;

    public NNode() {}

    public NNode(int _val) {
        val = _val;
    }

    public NNode(int _val, List<NNode> _children) {
        val = _val;
        children = _children;
    }

    @Override
    public String toString() {
        return "NNode{" +
                "val=" + val +
                ", children=" + children +
                '}';
    }
}
