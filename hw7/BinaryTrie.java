import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {
    private final Node root;

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> pq = new MinPQ<>();
        for (Map.Entry<Character, Integer> entry: frequencyTable.entrySet()) {
            pq.insert(new Node(entry.getKey(), entry.getValue(), null, null));
        }

        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            pq.insert(new Node('\0', left.freq + right.freq, left, right));
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
            return left == null || right == null;
        }
    }

    public Match longestPrefixMatch(BitSequence bitSequence) {
        Node cur = root;
        int pos = 0;
        BitSequence res = new BitSequence();
        while (!cur.isLeaf()) {
            res = res.appended(bitSequence.bitAt(pos));
            if (bitSequence.bitAt(pos) == 1) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return new Match(res, cur.ch);
    }

    public Map<Character, BitSequence> buildLookupTable() {
        Map<Character, BitSequence> map = new HashMap<>();
        lookupTableHelper(root, new BitSequence(), map);
        return map;
    }

    private void lookupTableHelper(Node node,
                                   BitSequence bitSequence,
                                   Map<Character, BitSequence> map) {
        if (node.isLeaf()) {
            map.put(node.ch, bitSequence);
        } else {
            lookupTableHelper(node.left, bitSequence.appended(0), map);
            lookupTableHelper(node.right, bitSequence.appended(1), map);
        }
    }
}
