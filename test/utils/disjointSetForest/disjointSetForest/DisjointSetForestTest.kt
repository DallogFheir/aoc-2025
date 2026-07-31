package utils.disjointSetForest.disjointSetForest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.disjointSetForest.DisjointSetForest

class DisjointSetForestTest {
    companion object {
        @JvmStatic
        fun areConnectedUnionAndMakeSetCases() = listOf(
            AreConnectedUnionAndMakeSetTestCase(
                disjointSetsToAdd = listOf(listOf(1)),
                element1 = 1,
                element2 = 1,
                expected = true,
            ),
            AreConnectedUnionAndMakeSetTestCase(
                disjointSetsToAdd = listOf(listOf(1), listOf(1)),
                element1 = 1,
                element2 = 1,
                expected = true,
            ),
            AreConnectedUnionAndMakeSetTestCase(
                disjointSetsToAdd = listOf(listOf(1, 2, 3)),
                element1 = 1,
                element2 = 2,
                expected = true,
            ),
            AreConnectedUnionAndMakeSetTestCase(
                disjointSetsToAdd = listOf(listOf(1, 2, 3, 1)),
                element1 = 1,
                element2 = 2,
                expected = true,
            ),
            AreConnectedUnionAndMakeSetTestCase(
                disjointSetsToAdd = listOf(listOf(1, 2, 3)),
                element1 = 1,
                element2 = 3,
                expected = true,
            ),
            AreConnectedUnionAndMakeSetTestCase(
                disjointSetsToAdd = listOf(listOf(1, 2, 3)),
                element1 = 2,
                element2 = 3,
                expected = true,
            ),
            AreConnectedUnionAndMakeSetTestCase(
                disjointSetsToAdd = listOf(listOf(1, 2, 3)),
                element1 = 1,
                element2 = 4,
                expected = false,
            ),
            AreConnectedUnionAndMakeSetTestCase(
                disjointSetsToAdd = listOf(listOf(1, 2, 3), listOf(4)),
                element1 = 2,
                element2 = 4,
                expected = false,
            ),
            AreConnectedUnionAndMakeSetTestCase(
                disjointSetsToAdd = listOf(listOf(1, 2, 3), listOf(4, 1)),
                element1 = 2,
                element2 = 4,
                expected = true,
            ),
        )

        @JvmStatic
        fun getDisjointSetsCases() = listOf(
            GetDisjointSetsTestCase(
                disjointSetsToAdd = listOf(listOf(1, 2, 3)),
            ),
            GetDisjointSetsTestCase(
                disjointSetsToAdd = listOf(listOf(1, 2, 3), listOf(4)),
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("areConnectedUnionAndMakeSetCases")
    fun `returns whether 2 elements are connected after union or making set correctly`(case: AreConnectedUnionAndMakeSetTestCase<Int>) {
        val cut = DisjointSetForest<Int>()

        addDisjointSetsToCut(disjointSets = case.disjointSetsToAdd, cut = cut)

        val result = cut.areConnected(case.element1, case.element2)

        Assertions.assertEquals(case.expected, result)
    }

    @ParameterizedTest
    @MethodSource("getDisjointSetsCases")
    fun `returns disjoint sets correctly`(case: GetDisjointSetsTestCase<Int>) {
        val cut = DisjointSetForest<Int>()

        addDisjointSetsToCut(disjointSets = case.disjointSetsToAdd, cut = cut)

        val result = cut.getDisjointSets().map { subset -> subset.sorted() }.sortedBy { it.toString() }

        val expected = case.disjointSetsToAdd.map { list -> list.sorted() }.sortedBy { it.toString() }

        Assertions.assertEquals(expected, result)
    }

    private fun <T> addDisjointSetsToCut(disjointSets: List<List<T>>, cut: DisjointSetForest<T>) {
        disjointSets.forEach { disjointSet ->
            cut.makeSetFor(disjointSet.first())

            disjointSet.zip(disjointSet.slice(1..disjointSet.lastIndex))
                .forEach { (element1, element2) ->
                    cut.union(element1, element2)
                }
        }
    }
}
