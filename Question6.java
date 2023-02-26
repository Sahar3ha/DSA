package DSA.Assignment;

import java.util.*;

public class Question6 {
    // Node class for building the Huffman tree
    private static class Node implements Comparable<Node> {
        char ch;
        int frequency;
        Node left, right;

        Node(char ch, int frequency,Node left, Node right) {
            this.ch = ch;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.frequency, other.frequency);
        }
    }

    // Build the Huffman tree
    private static Node buildHuffmanTree(String input) {
        // Count the frequency of each character in the input
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : input.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        // Build a priority queue of nodes, with the smallest frequency at the front
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (char ch : frequencyMap.keySet()) {
            pq.offer(new Node(ch, frequencyMap.get(ch), null, null));
        }

        // Combine the two nodes with the smallest frequency until only one node remains
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.offer(new Node('\0', left.frequency + right.frequency, left, right));
        }

        // The remaining node is the root of the Huffman tree
        return pq.poll();
    }

    // Encode the input using the Huffman tree
    private static String encode(String input, Node root) {
        Map<Character, String> encodingMap = buildEncodingMap(root);
        StringBuilder sb = new StringBuilder();
        for (char ch : input.toCharArray()) {
            sb.append(encodingMap.get(ch));
        }
        return sb.toString();
    }

    // Build a mapping of each character to its Huffman encoding
    private static Map<Character, String> buildEncodingMap(Node node) {
        Map<Character, String> encodingMap = new HashMap<>();
        buildEncodingMapHelper(node, "", encodingMap);
        return encodingMap;
    }

    private static void buildEncodingMapHelper(Node node, String encoding, Map<Character, String> encodingMap) {
        if (node.isLeaf()) {
            encodingMap.put(node.ch, encoding);
        } else {
            buildEncodingMapHelper(node.left, encoding + "0", encodingMap);
            buildEncodingMapHelper(node.right, encoding + "1", encodingMap);
        }
    }

    private static String decode(String input, Node root) {
        StringBuilder sb = new StringBuilder();
        Node curr = root;
        for (char bit : input.toCharArray()) {
            if (bit == '0') {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
            if (curr.isLeaf()) {
                sb.append(curr.ch);
                curr = root;
            }
        }
        return sb.toString();
    }
    //Question 6(b)

    private static final int[] POW_10 = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000};
    public boolean isSolvable(String[] words, String result) {
        Set<Character> charSet = new HashSet<>();
        int[] charCount = new int[91];
        boolean[] nonLeadingZero = new boolean[91]; // ASCII of A..Z chars are in range 65..90
        for (String word : words) {
            char[] cs = word.toCharArray();
            for (int i = 0; i < cs.length; i++) {
                if (i == 0 && cs.length > 1) nonLeadingZero[cs[i]] = true;
                charSet.add(cs[i]);
                charCount[cs[i]] += POW_10[cs.length - i - 1]; // charCount is calculated by units
            }
        }
        char[] cs = result.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (i == 0 && cs.length > 1) nonLeadingZero[cs[i]] = true;
            charSet.add(cs[i]);
            charCount[cs[i]] -= POW_10[cs.length - i - 1]; // charCount is calculated by units
        }
        boolean[] used = new boolean[10];
        char[] charList = new char[charSet.size()];
        int i = 0;
        for (char c : charSet) charList[i++] = c;
        return backtracking(used, charList, nonLeadingZero, 0, 0, charCount);
    }

    private boolean backtracking(boolean[] used, char[] charList, boolean[] nonLeadingZero, int step, int diff, int[] charCount) {
        if (step == charList.length) return diff == 0; // difference between sum of words and result equal to 0
        for (int d = 0; d <= 9; d++) { // each character is decoded as one digit (0 - 9).
            char c = charList[step];
            if (!used[d] // each different characters must map to different digits
                    && (d > 0 || !nonLeadingZero[c])) {  // decoded as one number without leading zeros.
                used[d] = true;
                if (backtracking(used, charList, nonLeadingZero, step + 1, diff + charCount[c] * d, charCount)) return true;
                used[d] = false;
            }
        }
        return false;
    }
}
