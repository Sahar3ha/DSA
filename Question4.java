package DSA.Assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question4 {

    class Pair {
        int value, frequency;

        public Pair(int value, int frequency)
        {
            this.value = value;
            this.frequency = frequency;
        }
    }

    class LFU {
        int cacheSize;
        Map<Integer, Pair> cache;

        public LFU(int cacheSize)
        {
            this.cacheSize = cacheSize;
            this.cache = new HashMap<Integer, Pair>();
        }

        // Self made heap tp Rearranges
        // the nodes in order to maintain the heap property
        public void increment(int value)
        {
            if (cache.containsKey(value)) {
                cache.get(value).frequency += 1;
            }
        }

        // Function to Insert a new node in the heap
        public void insert(int value)
        {
            if (cache.size() == cacheSize) {
                // remove least frequently used
                int lfuKey = findLFU();
                System.out.println("Cache block " + lfuKey
                        + " removed.");
                cache.remove(lfuKey);
            }

            Pair newPair = new Pair(value, 1);
            cache.put(value, newPair);
            System.out.println("Cache block " + value
                    + " inserted.");
        }

        // Function to refer to the block value in the cache
        public void refer(int value)
        {
            if (!cache.containsKey(value)) {
                insert(value);
            }
            else {
                increment(value);
            }
        }

        // Function to find the ques4.LFU block
        public int findLFU()
        {
            int lfuKey = 0;
            int minFrequency = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Pair> entry :
                    cache.entrySet()) {
                if (entry.getValue().frequency < minFrequency) {
                    minFrequency = entry.getValue().frequency;
                    lfuKey = entry.getKey();
                }
            }
            return lfuKey;
        }
    }






    //Question 4(b)
    public static void main(String[] args) {
        Node head = new Node(3);
        head.next = new Node(1);
        head.next.next = new Node(2);

        int steps = countStepsToSortLinkedList(head);
        System.out.println("Number of steps to sort linked list: " + steps);
    }

    public static int countStepsToSortLinkedList(Node head) {
        // Convert linked list to array
        List<Integer> arr = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            arr.add(curr.val);
            curr = curr.next;
        }

        // Bubble sort and count steps
        int steps = 0;
        int n = arr.size();
        for (int i = 0; i < n; i++) {
            int swaps = 0;
            for (int j = 1; j < n - i; j++) {
                if (arr.get(j - 1) > arr.get(j)) {
                    swap(arr, j - 1, j);
                    swaps += 1;
                }
            }
            if (swaps == 0) {
                break;
            }
            steps += 1;
        }

        return steps;
    }

    private static void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

}

class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }


}
