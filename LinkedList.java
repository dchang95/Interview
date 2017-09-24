/* detects if there is a cycle in a linked list. (rabbit and the hare algo)

   class Node {
    int val;
    Node head;
   }
 */

public static boolean detectCycles(Node head) {
        if (head == null || head.next == null) {
                return false;
        }
        Node start;
        Node fast;
        start = head;
        fast = head.next;
        while (start != fast) {
                if (fast == null || fast.next == null) {
                        return false;
                }
                fast = fast.next.next;
                slow = slow.next;
        }
        return true;
}

// where the cycle starts
public static Node detectCycles2(Node head) {
        HashSet<Node> listOfNodes = new HashSet<Node>();
        if (head == null) {
                return null;
        }
        if (listOfNodes.contains(head)) {
                return head;
        } else {
                listOfNodes.add(head);
        }
        head = head.next;

}     

// reverse a singly linked list.
void reverseLinkedList(Node head) {
  Node prev = null;
  Node next = null;
  Node current = head;
  while (current != null) {
    next = current.next;
    current.next = prev;
    prev = current;
    current = next;
  }
  node = prev;
  return node;
}
