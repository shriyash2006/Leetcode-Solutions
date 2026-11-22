
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {

            int sum = carry;

            // Add first linked list digit
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            // Add second linked list digit
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            // Calculate digit and carry
            carry = sum / 10;
            int digit = sum % 10;

            // Create new node
            curr.next = new ListNode(digit);
            curr = curr.next;
        }

        return dummy.next;
    }
}
