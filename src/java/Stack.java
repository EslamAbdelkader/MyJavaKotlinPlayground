package java;

class Stack {
//    private List<Integer> list = new ArrayList<>();
//
//    public void push(int x) {
//        list.add(x);
//    }
//
//    public Integer pop() {
//        if (list.isEmpty()) return null;
//        Integer x = list.get(list.size()-1);
//        list.remove(list.size()-1);
//        return x;
//    }

    private int[] arr = new int[30];
    private int top = -1;

    public void push(int x) {
        if (top == arr.length - 1) return;
        top++;
        arr[top] = x;
    }

    public int pop() {
        if (top < 0) throw new RuntimeException();
        int x = arr[top];
        top--;
        return x;
    }
}
