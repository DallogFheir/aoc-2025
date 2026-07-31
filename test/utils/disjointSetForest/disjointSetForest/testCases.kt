package utils.disjointSetForest.disjointSetForest

data class AreConnectedUnionAndMakeSetTestCase<T>(
    val disjointSetsToAdd: List<List<T>>,
    val element1: T,
    val element2: T,
    val expected: Boolean,
)

data class GetDisjointSetsTestCase<T>(
    val disjointSetsToAdd: List<List<T>>,
)
