package utils.listUtils

data class ProductTestCase<T>(
    val list1: List<T>,
    val list2: List<T>,
    val expected: List<Pair<T, T>>,
)
