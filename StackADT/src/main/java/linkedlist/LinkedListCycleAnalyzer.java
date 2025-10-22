package linkedlist;

import support.CycleInfo;
import support.LLNode;

public class LinkedListCycleAnalyzer {

    public static <T> CycleInfo detectCycleInfo(LLNode<T> head) {

        if (head == null) return new CycleInfo(-1, 0);

        LLNode<T> slow = head;
        LLNode<T> fast = head;
        boolean hasCycle = false;

        // Step 1: Detect cycle using Floyd's algorithm
        while (fast != null && fast.getLink() != null) {
            slow = slow.getLink();
            fast = fast.getLink().getLink();

            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        // Step 2: No cycle → return (-1, 0)
        if (!hasCycle) return new CycleInfo(-1, 0);

        // Step 3: Measure cycle length
        int cycleLength = 1;
        LLNode<T> temp = slow.getLink();
        while (temp != slow) {
            temp = temp.getLink();
            cycleLength++;
        }

        // Step 4: Find entry index
        LLNode<T> ptr1 = head;
        LLNode<T> ptr2 = head;

        // Move ptr2 ahead by cycleLength nodes
        for (int i = 0; i < cycleLength; i++) {
            ptr2 = ptr2.getLink();
        }

        int entryIndex = 0;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.getLink();
            ptr2 = ptr2.getLink();
            entryIndex++;
        }

        // Step 5: Return results
        return new CycleInfo(entryIndex, cycleLength);
    }
}
