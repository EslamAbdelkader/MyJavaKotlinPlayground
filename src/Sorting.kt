import java.util.Collections.swap

fun main() {
    val list = mutableListOf(10, 4, 1, 8, 2, 0, 4, 5)
    print(insertionSort(list))
}

// Selecting the minimum one of the unordered list
fun selectionSort(list: MutableList<Int>): MutableList<Int> {
    for (i in 0 until list.size - 1) {
        var minIndex = i
        for (j in i + 1 until list.size) {
            if (list[j] < list[minIndex]) {
                minIndex = j
            }
        }
        swap(list, i, minIndex)
    }
    return list
}

// bubbling up the minimum one of the unordered list
fun bubbleSort(list: MutableList<Int>): MutableList<Int> {
    for (i in 0 until list.size - 1) {
        for (j in list.size - 1 downTo i + 1) {
            if (list[j] < list[j - 1]) {
                swap(list, j, j - 1)
            }
        }
    }
    return list
}

// inserting the next element of the unordered list in its right position in the ordered list
fun insertionSort(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        val num = list[i]
        var j = i
        while (j > 0 && list[j-1] > num){
            list[j] = list[j-1]
            j--
        }
        list[j] = num
    }
    return list
}