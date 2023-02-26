package DSA.Assignment;

import java.util.ArrayList;
import java.util.List;

public class Question4 {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);

        int steps = countStepsToSortLinkedList(head);
        System.out.println("Number of steps to sort linked list: " + steps);
    }

    public static int countStepsToSortLinkedList(ListNode head) {
        // Convert linked list to array
        List<Integer> arr = new ArrayList<>();
        ListNode curr = head;
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
    ListNode next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }


}
