import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.Map;

public class BinaryTrie implements Serializable {
    public Node root;

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> pq = new MinPQ<>();
        for (Map.Entry<Character, Integer> entry: frequencyTable.entrySet()) {
            pq.insert(new Node(entry.getKey(), entry.getValue(), null, null));
        }

        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            pq.insert(new Node('\0', left.freq + right.ch, left, right));
        }
        root = pq.delMin();
    }

    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }

        public boolean isLeaf() {
            return left != null && right != null;
        }
    }

    public Match longestPrefixMatch(BitSequence bitSequence) {
        Node cur = root;
        return null;
    }
}
