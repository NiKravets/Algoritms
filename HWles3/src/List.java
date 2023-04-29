public class List {
    Node head;
    Node tail;

    public void reverse(){
        Node currentNode = head;
        while (currentNode != null){
            Node next = currentNode.next;
            Node prev = currentNode.prev;
            currentNode.next = prev;
            currentNode.prev = next;
            if (prev == null) tail = currentNode;
            if (next == null) head = currentNode;
            currentNode = next;
        }
    }

}
