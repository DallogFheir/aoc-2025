package utils.graphs.node

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import utils.graphs.DAGNode

class DAGNodeTest {
    companion object {
        @JvmStatic
        fun addNeighborCases() = listOf(
            AddNeighborTestCase(
                neighborsToAdd = listOf(
                    DAGNode(value = 1),
                    DAGNode(value = 2),
                    DAGNode(value = 3),
                )
            )
        )

        @JvmStatic
        fun aggregateCases() = listOf(
            AggregateTestCase(
                neighborsToAdd = listOf(),
                expected = 0,
            ),
            AggregateTestCase(
                neighborsToAdd = listOf(
                    DAGNode(value = 1),
                ),
                expected = 1,
            ),
            AggregateTestCase(
                neighborsToAdd = listOf(
                    DAGNode(value = 1),
                    DAGNode(value = 2),
                    DAGNode(value = 3),
                ),
                expected = 6,
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("addNeighborCases")
    fun `adds neighbors correctly`(case: AddNeighborTestCase<Int>) {
        val cut = DAGNode(value = 0)

        case.neighborsToAdd.forEach { cut.addNeighbor(it) }

        Assertions.assertEquals(case.neighborsToAdd, cut.neighbors)
    }

    @Test
    fun `throws if adding a neighbor with a cycle`() {
        val node1 = DAGNode(value = 1)
        val node2 = DAGNode(value = 2)
        val node3 = DAGNode(value = 3)

        node1.addNeighbor(node2)
        node2.addNeighbor(node3)

        Assertions.assertThrows(IllegalArgumentException::class.java) {
            node3.addNeighbor(node1)
        }
    }

    @ParameterizedTest
    @MethodSource("aggregateCases")
    fun `aggregates values from neighbors correctly`(case: AggregateTestCase) {
        val cut = DAGNode(value = 0)

        case.neighborsToAdd.forEach { cut.addNeighbor(it) }

        val result = cut.aggregate({ neighbors -> neighbors.sumOf { it.value } })

        Assertions.assertEquals(case.expected, result)
    }
}
