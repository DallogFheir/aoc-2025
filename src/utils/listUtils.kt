package utils

fun <T> product(list1: List<T>, list2: List<T>): List<Pair<T, T>> {
    return list1.flatMap { item1 ->
        list2.map { item2 -> item1 to item2 }
    }
}
