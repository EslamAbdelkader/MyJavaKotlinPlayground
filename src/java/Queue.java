package java;

class Queue {
    private int[] arr = new int[5];
    private int front = 0;
    private int rear = -1;

    private int plusOne(int x) {
        int result = ++x;
        if (result == arr.length) result = 0;
        return result;
    }

    private boolean isFull() {
        return rear != -1 && front == plusOne(rear);
    }

    private boolean isEmpty() {
        return front == 0 && rear == -1;
    }

    public void offer(int x) {
        if (!isFull()) {
            rear = plusOne(rear);
            arr[rear] = x;
        }
    }

    public int poll() {
        if (!isEmpty()) {
            int result = arr[front];
            if (front == rear) {
                front = 0;
                rear = -1;
            } else front = plusOne(front);
            return result;
        }
        throw new RuntimeException();
    }
}