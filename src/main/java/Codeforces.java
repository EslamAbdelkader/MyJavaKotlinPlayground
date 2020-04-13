import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Codeforces {
    static int count = 0;

    public static void main(String[] args) throws IOException {
        Codeforces codeforces = new Codeforces();
        System.out.println(codeforces.removeDuplicates(Arrays.asList( 1000 ,1000 ,1000 ,1000 ,1001 ,1002 ,1003 ,1003 ,1004 ,1010)));
    }

    public int removeDuplicates(List<Integer> a) {
        int duplicatesCount = 0;
        int newSize = a.size() - duplicatesCount;
        for(int i=0; i<a.size() - 2 - duplicatesCount; i++){
            if (a.get(i+2).equals(a.get(i))){
                int j = 2;
                while (i+j< newSize && a.get(i+j).equals(a.get(i))){
                    duplicatesCount++;
                    j++;
                }
                for (int k = i+j; k<a.size(); k++){
                    a.set(k-j+2, a.get(k));
                }
                newSize = a.size() - duplicatesCount;
            }
        }
        return newSize;
    }

    public ArrayList<ArrayList<Integer>> threeSum(List<Integer> A) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Collections.sort(A);

        for(int i = 0; i< A.size() - 2 && A.get(i) <= 0; i++){
            int left = i+1;
            int right = A.size()-1;
            int num = A.get(i);

            if (i>1 && num == A.get(i-1)) continue;

            while(left<right){
                int leftElement = A.get(left);
                int rightElement = A.get(right);
                if (leftElement + rightElement == 0 - num){
                    ArrayList<Integer> arr = new ArrayList();
                    arr.add(num);
                    arr.add(leftElement);
                    arr.add(rightElement);
                    res.add(arr);
                    left++;
                    while (left < right && A.get(left) == leftElement){
                        left++;
                    }
                    while (left < right && A.get(right) == rightElement){
                        right--;
                    }
                }else if (leftElement + rightElement > 0 - num){
                    right--;
                }else {
                    left++;
                }
            }
        }
        return res;
    }

    int getBit(int num, int i) {
        return (num & (1 << i) ) == 0? 0 : 1;
    }

    int setBitToOne(int num, int i) {
        return num | (1 << i);
    }


    int setBitToValue(int num, int i, int value) {
        return (num & ~(1 << i) | (value << i));
    }

    int insertInto(int N, int M, int i, int j) {
        for ( int counter = 0; counter <= j-i; counter++){
            int value = getBit(M,counter);
            N = setBitToValue(N, i+counter, value);
        }
        return N;
    }

    private int countSmaller(final List<Integer> A, int B) {
        int left = 0, right = A.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (B <= A.get(mid))
                right = mid - 1;
            else // if kotlin.B > kotlin.A.get(mid)
                left = mid + 1;
        }
        return left;
    }

    private static ArrayList<ArrayList<Integer>> matrixArrayToList(int[][] arr) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int[] ints : arr) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int anInt : ints) {
                list.add(anInt);
            }
            result.add(list);
        }
        return result;
    }

    public int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
        if (a.size() == 0) return 0;
        int rowSize = a.get(0).size();
        int total = a.size() * rowSize;
        int start = 0;
        int end = total-1;

        while (start<end){
            int mid = (end+start)/2;
            int i = mid/rowSize;
            int j = mid % rowSize;
            int val = a.get(i).get(j);
            if (val == b) return 1;
            if (val < b) start = mid + 1;
            else if (val > b) end = mid - 1;
        }
        return 0;
    }

    public ArrayList<ArrayList<Integer>> prettyPrint(int A) {
        int[][] arr = new int[A * 2 - 1][A * 2 - 1];
        for (int i = A; i > 0; i--) {
            int start = A - i;
            int end = (A - i) + (2 * i - 1) - 1;
            for (int j = start; j <= end; j++) {
                arr[start][j] = i;
                arr[j][start] = i;
                arr[end][j] = i;
                arr[j][end] = i;
            }
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int[] ints : arr) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int anInt : ints) {
                list.add(anInt);
            }
            result.add(list);
        }
        return result;
    }


    // http://codeforces.com/contest/556/problem/A
    public int stringCutting(List<Integer> list) {
        if (list == null) return 0;

        int ones = 0;
        int zeros = 0;

        for (Integer num : list) {
            if (num == 0) {
                zeros++;
            } else {
                ones++;
            }
        }
        int pairs = 2 * Math.min(zeros, ones);
        return list.size() - pairs;
    }

    // http://codeforces.com/contest/225/problem/A
    public boolean balancedTower(int top, List<Integer> firstSides, List<Integer> secondSides) {
        int adjacent = 7 - top;
        for (int i = 0; i < firstSides.size(); i++) {
            if (firstSides.get(i) == top
                    || firstSides.get(i) == adjacent
                    || secondSides.get(i) == top
                    || secondSides.get(i) == adjacent) {
                return false;
            }
        }
        return true;
    }

    // Binary Search - Find any occurrence
    public int binarySearch(List<Integer> list, int num) {
        int low = 0;
        int high = list.size() - 1;
        while (high >= low) {
            int mid = low + (high - low) / 2;
            int guess = list.get(mid);

            if (guess == num) {
                return mid;
            } else if (guess < num) {
                low = mid + 1;
            } else if (guess > num) {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Binary Search - Find any occurrence - Recursive
    public int driverBinarySearch(List<Integer> list, int num) {
        int low = 0;
        int high = list.size() - 1;
        return recursiveBinarySearch(list, low, high, num);
    }

    public int recursiveBinarySearch(List<Integer> list, int low, int high, int num) {
        if (high < low) {
            return -1;
        }

        int mid = low + (high - low) / 2;
        int guess = list.get(mid);

        if (guess == num) {
            return mid;
        }

        if (guess < num) {
            return recursiveBinarySearch(list, mid + 1, high, num);
        } else { // guess > num
            return recursiveBinarySearch(list, low, mid - 1, num);
        }
    }

    // Binary Search - Find first occurrence
    public int firstOccurrence(List<Integer> list, int num) {
        int low = 0;
        int high = list.size() - 1;
        while (high >= low) {
            int mid = low + (high - low) / 2;
            int guess = list.get(mid);

            if (high == low && guess == num) {
                return mid;
            }

            if (guess > num) {
                high = mid - 1;
            }

            if (guess < num) {
                low = mid + 1;
            }

            if (guess == num) {
                high = mid;
            }
        }
        return -1;
    }

    // http://codeforces.com/contest/433/problem/B
    public int rangeSum(List<Integer> listInput, int left, int right) {
        ArrayList<Integer> list = new ArrayList(listInput);
        for (int i = 1; i < list.size(); i++) {
            list.set(i, list.get(i) + list.get(i - 1));
        }
        if (left == 0) {
            return list.get(right);
        }
        return list.get(right) - list.get(left - 1);
    }

    // http://codeforces.com/contest/6/problem/B
    public int adjacentElements(char[][] matrix, char color) {
        Set<Character> set = new HashSet();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == color) {
                    if (matrix[i - 1][j] != color && matrix[i - 1][j] != '.') {
                        set.add(matrix[i - 1][j]);
                    }
                    if (matrix[i + 1][j] != color && matrix[i + 1][j] != '.') {
                        set.add(matrix[i + 1][j]);
                    }
                    if (matrix[i][j - 1] != color && matrix[i][j - 1] != '.') {
                        set.add(matrix[i][j - 1]);
                    }
                    if (matrix[i][j + 1] != color && matrix[i][j + 1] != '.') {
                        set.add(matrix[i][j + 1]);
                    }
                }
            }
        }
        return set.size();
    }


    public void monkAndMultiplication(List<Integer> list) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < list.size(); i++) {
            queue.add(list.get(i));
            if (i < 2) {
                System.out.println(-1);
                continue;
            }

            List<Integer> arr = new ArrayList<>();
            int mul = 1;

            for (int j = 0; j < 3; j++) {
                Integer num = queue.poll();
                mul *= num;
                arr.add(num);
            }

            System.out.println(mul);

            for (int j = 0; j < 3; j++) {
                queue.add(arr.get(j));
            }
        }
    }

    public void add(int[] arr, int index, int num) {
        arr[index] = num;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (arr[parent] < arr[index]) {
                swap(arr, index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    public void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    //https://www.hackerearth.com/practice/data-structures/trees/heapspriority-queues/practice-problems/algorithm/monk-and-champions-league/

    // { 1, 2, 4 }  4
    // 11
    public int maxProfit(List<Integer> rows, int fansCount) {
        int maxProfit = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int item : rows) {
            pq.add(item);
        }

        while (fansCount > 0) {
            int num = pq.poll();
            maxProfit += num;
            pq.add(num - 1);
            fansCount--;
        }

        return maxProfit;
    }


    public static int[] solve(long[] Q, int k, String s, long n) {
        // n seats
        // k people
        // s preferences
        // Q queries
        int[] arr = new int[(int) n];
        // init array with -1
        for (int i = 0; i < n; i++) {
            arr[i] = -1;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> {
            int result = p2.value.compareTo(p1.value);
            if (result == 0) {
                result = p1.start.compareTo(p2.start);
            }
            return result;
        });

        pq.add(new Pair((int) n, 0));
        for (int i = 1; i <= k; i++) {
            Pair empty = pq.poll();
            if (empty.value % 2 == 1) {
                int place = empty.start + empty.value / 2;
                arr[place] = i;
                pq.add(new Pair(empty.value / 2, empty.start));
                pq.add(new Pair(empty.value / 2, place + 1));
            } else {
                int place = empty.start + empty.value / 2;
                if (s.charAt(i) == 'L') {
                    place--;
                    pq.add(new Pair(empty.value / 2 - 1, empty.start));
                    pq.add(new Pair(empty.value / 2, place + 1));
                } else {
                    pq.add(new Pair(empty.value / 2 - 1, place + 1));
                    pq.add(new Pair(empty.value / 2, empty.start));
                }
                arr[place] = i;
            }
        }

        int[] result = new int[Q.length];
        for (int i = 0; i < Q.length; i++) {
            result[i] = arr[(int) Q[i]];
        }
        return result;
    }

    static class Pair {
        Integer value;
        Integer start;

        public Pair(int value, int start) {
            this.start = start;
            this.value = value;
        }
    }

    // 3 Way Partitioning (dual pivot partitioning) - In Place
    public void partition(int[] arr, int low, int high) {
        // kotlin.Base Case
        if (low >= high) return;

        // left pivot must be smaller than right pivot
        if (arr[low] > arr[high]) {
            swap(arr, low, high);
        }

        // Init reader as low and start partitioning
        int reader = low;
        int leftPivot = arr[low];
        int rightPivot = arr[high];

        while (reader <= high) {
            int element = arr[reader];

            // element belongs to left partition
            if (element < leftPivot) {
                swap(arr, reader, low);
                reader++;
                low++;
            }

            // element belongs to middle partition
            else if (element <= rightPivot) {
                reader++;
            }

            // element belongs to right partition
            else {
                swap(arr, reader, high);
                high--;
            }

            System.out.println("processed element: " + element);
            System.out.println("element at low: " + arr[low]);
            System.out.println("element at high: " + arr[high]);
            System.out.println("-----------");
        }
    }

    // Meging two sorted arrays with smaller aux array
// https://www.coursera.org/learn/algorithms-part1/quiz/LojjQ/interview-questions-mergesort-ungraded
// 1,2,5,7,7,9,2,4,5,8,9,10 // j
// 1,2,5,7,7,9		// i
// 1,2,2,4,5,5,7,7,8,9,9,10

    public void mergeSortInSmallerExtraSpace(int[] arr, int[] aux) {
        int auxSize = aux.length;        // length of smaller array
        for (int i = 0; i < auxSize; i++) {
            aux[i] = arr[i];
        }

        int i = 0;
        int j = auxSize;
        int k = 0;

        while (i < auxSize && j < arr.length) {
            if (arr[j] < aux[i]) {
                arr[k] = arr[j];
                j++;
            } else {
                arr[k] = aux[i];
                i++;
            }
            k++;
        }

        while (i < auxSize) {
            arr[k] = aux[i];
            i++;
            k++;
        }

        while (j < arr.length) {
            arr[k] = arr[j];
            j++;
            k++;
        }
    }

    // Merge Sort
    public void sort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            System.out.println(start + " " + mid + " " + end);
            sort(arr, start, mid);
            sort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    public void merge(int[] arr, int start, int mid, int end) {
        int leftSize = mid - start + 1;
        int rightSize = end - mid;
        int[] L = new int[leftSize];
        int[] R = new int[rightSize];

        int left = 0, right = 0, writer = start;

        System.arraycopy(arr, start, L, 0, leftSize);
        System.arraycopy(arr, mid + 1, R, 0, rightSize);

        while (left < leftSize && right < rightSize) {
            if (L[left] > R[right]) {
                arr[writer] = L[left];
                left++;
                count += rightSize - right;
            } else {
                arr[writer] = R[right];
                right++;
            }
            writer++;
        }

        while (left < leftSize) {
            arr[writer] = L[left];
            writer++;
            left++;
        }

        while (right < rightSize) {
            arr[writer] = R[right];
            writer++;
            right++;
        }
    }

    static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.next = next;
            this.val = val;
        }
    }

    public Node getMiddle(Node node) {
        if (node.next == null) return node;
        Node fast = node;
        Node slow = node;

        while (fast.next != null) {
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;
    }

    public Node sort(Node node) {
        if (node == null || node.next == null) {
            return node;
        }

        Node mid = getMiddle(node);
        Node nextToMid = mid.next;

        mid.next = null;

        Node left = sort(node);
        Node right = sort(nextToMid);
        return merge(left, right);
    }

    public Node merge(Node left, Node right) {
        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        if (left.val < right.val) {
            left.next = merge(left.next, right);
            return left;
        } else {
            right.next = merge(left, right.next);
            return right;
        }
    }

    static class PQ {
        List<Integer> list = new ArrayList<>();

        void append(int val) {
            list.add(val);
            bubble();
        }

        Integer peek() {
            return list.isEmpty() ? null : list.get(0);
        }

        Integer pop() {
            Integer val = peek();
            if (list.isEmpty() || list.size() == 1) return val;
            list.set(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);
            sink(0);
            return val;
        }

        private void sink(int index) {
            int left = (index * 2) + 1;
            int right = (index * 2) + 2;
            int smallest = index;

            if (left < list.size() && list.get(left) < list.get(smallest)) {
                smallest = left;
            }

            if (right < list.size() && list.get(right) < list.get(smallest)) {
                smallest = right;
            }

            if (smallest != index) {
                int temp = list.get(smallest);
                list.set(smallest, list.get(index));
                list.set(index, temp);
                sink(smallest);
            }
        }

        private void bubble() {
            int index = list.size() - 1;
            if (index == 0) return;
            int parent = (index - 1) / 2;

            while (index > 0 && list.get(parent) > list.get(index)) {
                int temp = list.get(parent);
                list.set(parent, list.get(index));
                list.set(index, temp);
                index = parent;
                parent = (index - 1) / 2;
            }
        }
    }

    String replaceSpace(String string) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c != ' ') {
                builder.append(c);
            } else {
                builder.append("%20");
            }
        }
        return builder.toString();
    }

    void replaceSpaceInPlace(char[] str, int trueLength) {
        int spaceCount = 0;
        for (int i = 0; i < trueLength; i++) {
            if (str[i] == ' ') spaceCount++;
        }

        int index = trueLength + spaceCount * 2 - 1;

        for (int i = trueLength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index] = '0';
                str[index - 1] = '2';
                str[index - 2] = '%';
                index -= 3;
            } else {
                str[index] = str[i];
                index--;
            }
        }
    }

    public int sqrt(int a) {
        if (a < 0) return -1;
        if (a == 0) return 0;
        int result = 1;
        int left = 1;
        int right = a;
        int mid = 0;
        while (left < right) {
            mid = left + (left - right) / 2;
            int mul = mid * mid;
            if (mul == a) return mid;
            if (mul < a) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return mid;
    }

    public String simplifyPath(String A) {
        if (A.length() < 2) return A;
        List<String> list = new ArrayList<>();
        String regex = ".*?\\/";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(A);


        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                String matched = matcher.group(i);
                if (matched.equals("./")) continue;
                if (matched.equals("../")) {
                    pop(list);
                } else {
                    list.add(matched);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (String part : list) {
            builder.append(part);
        }
        String path = builder.toString();
        if (path == "/") return path;
        return path.substring(0, path.length() - 1);
    }

    public void pop(List<String> list) {
        list.remove(list.size() - 1);
        if (list.isEmpty()) list.add("/");
    }

    public ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {
        Queue<Integer> queue = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.size() && i < B; i++) {
            queue.add(A.get(i));
            if (A.get(i) > max) max = A.get(i);
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(max);

        for (int i = 1; i + B - 1 < A.size(); i++) {
            int in = A.get(i + B - 1);
            int out = queue.poll();
            queue.offer(in);
            // if (in >= max) max = max;
            if (in < max) {
                if (out == max) {    // else max is the same
                    max = Integer.MIN_VALUE;
                    for (int j = i; j < i + B; j++) {
                        if (A.get(j) > max) max = A.get(j);
                    }
                }
            }
            result.add(max);
        }
        return result;
    }

    public int largestRectangleArea(List<Integer> A) {
        if (A.size() == 1) return A.get(0);
        Stack<Integer> left = new Stack<>();
        Stack<Integer> right = new Stack<>();
        ArrayList<Integer> leftMin = new ArrayList<>();
        ArrayList<Integer> rightMin = new ArrayList<>();

        int max = 0;

        for (int i = 0; i < A.size(); i++) {
            while (!left.isEmpty() && A.get(left.peek()) >= A.get(i)) {
                left.pop();
            }
            if (left.isEmpty()) {
                leftMin.add(-1);
                left.push(i);
            } else {
                leftMin.add(left.peek());
            }
        }

        for (int i = A.size() - 1; i > 0; i--) {
            while (!right.isEmpty() && A.get(right.peek()) >= A.get(i)) {
                right.pop();
            }
            if (right.isEmpty()) {
                rightMin.add(A.size());
                right.push(i);
            } else {
                rightMin.add(right.peek());
            }

            int result = A.get(i) * (rightMin.get(A.size() - 1 - i) - leftMin.get(i) - 1);
            if (result > max) max = result;
        }

        return max;
    }

    public int compareVersion(String A, String B) {
        String[] aSplitted = A.split("\\.");
        String[] bSplitted = B.split("\\.");

        int length = (aSplitted.length > bSplitted.length) ? bSplitted.length : aSplitted.length;

        for (int i = 0; i < length; i++) {
            if (!aSplitted[i].equals(bSplitted[i]))
                return Integer.valueOf(aSplitted[i]).compareTo(Integer.valueOf(bSplitted[i]));
        }

        if (aSplitted.length == bSplitted.length) return 0;

        if (aSplitted.length > bSplitted.length)
            return 1;
        else return -1;
    }

    public int strStr(final String A, final String B) {
        if (A.length() == 0 || B.length() == 0 || A.length() < B.length()) return -1;

        for (int i = 0; i < A.length() - B.length() + 1; i++) {
            boolean found = true;
            for (int j = 0; j < B.length(); j++) {
                if (A.charAt(i) != B.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found) return i;
        }
        return -1;
    }


    public String phoneNumber(String S) {
        // write your code in Java SE 8
        int digitCount = 0;
        StringBuilder builder = new StringBuilder();

        // Grouping
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c != ' ' && c != '-') {
                if (digitCount > 0 && digitCount % 3 == 0) {
                    builder.append("-");
                }
                builder.append(c);
                digitCount++;
            }
        }
        String phone = builder.toString();

        // Making sure last group is at least 2 characters if possible
        int lastIndex = phone.lastIndexOf('-');
        if (lastIndex == phone.length() - 2) {
            // swap the dash with the character before
            char[] chars = phone.toCharArray();
            chars[lastIndex] = chars[lastIndex - 1];
            chars[lastIndex - 1] = '-';
            phone = new String(chars);
        }

        return phone;
    }

    static class MinStack {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty()) {
                minStack.push(x);
            } else {
                Integer peek = minStack.peek();
                if (x <= peek) {
                    minStack.push(x);
                }
            }
        }

        public Integer pop() {
            Integer result = stack.pop();
            if (result == null) return null;
            if (minStack.peek().intValue() == result) {
                minStack.pop();
            }
            return result;
        }

        public Integer min() {
            return minStack.peek();
        }
    }
}
