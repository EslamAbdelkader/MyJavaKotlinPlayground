class LinkedList {
    static class Node {
        int counter = 1;
        int data;
        Node next;
        Node ( int data) {
            this.data = data;
        }
    }

    Node head;

    LinkedList(Node head) {
        this.head = head;
    }

    void append(int data) {
        Node node = new Node(data);
        Node current = head;
        while(current.next != null) {
            current = current.next;
        }
        current.next = node;
    }

    void print(){
        if (head == null) return;
        Node current = head;
        System.out.print(current.data);
        while (current.next != null) {
            System.out.print(" -> " + current.next.data);
            current = current.next;
        }
    }

    Node findKthElementFromLast(int k) {
        int length = 1;
        Node node = head;
        while (node.next != null) {
            node = node.next;
            length++;
        }
        if (length < k) return null;
        node = head;
        for ( int i = 0; i < length - k ; i++){
            node = node.next;
        }
        return node;
    }

    Wrapper findKthElementFromLastRecursively(Node node, int k) {
        if (node == null) return new Wrapper(null, 0);
        Wrapper wrapper = findKthElementFromLastRecursively(node.next, k);
        int counter = wrapper.counter + 1;
        if (counter == k) return new Wrapper(node, counter);
        return new Wrapper(wrapper.node, counter);
    }

    class Wrapper {
        Node node;
        int counter;
        Wrapper (Node node, int counter) {
            this.node = node;
            this.counter = counter;
        }
    }

    void partition (int pivot) {
        head = partition(head,pivot);
    }

    private Node partition(Node node, int pivot) {
        Node head = node;
        Node tail = node;

        while (node != null) {
            Node next = node.next;

            if (node.data < pivot) {
                node.next = head;
                head = node;
            } else {
                tail.next = node;
                tail = node;
            }
            node = next;
        }
        tail.next = null;
        return head;
    }


    void add(int index, int data) {
        Node newNode = new Node(data);
        if (index == 0) {
            addAtStart(data);
        }
        Node node = head;
        for ( int i = 0; i<index-1; i++){
            if (node.next != null) {
                node = node.next;
            }
        }
        newNode.next = node.next;
        node.next = newNode;
    }

    void addAtStart(int data) {
        Node newNode = new Node(data);
        if ( head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    Node remove(int data) {
        if (head == null) return null;
        if (head.data == data) {
            Node temp = head;
            head = head.next;
            return temp;
        }
        Node node = head;
        while (node.next != null) {
            if (node.next.data == data) {
                Node next = node.next;
                node.next = next.next;
                return next;
            }
            node = node.next;
        }
        return null;
    }

}
