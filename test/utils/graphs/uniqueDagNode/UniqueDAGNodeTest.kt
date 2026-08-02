package utils.graphs.uniqueDagNode

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.graphs.UniqueDAGNode

class UniqueDAGNodeTest {
    @Test
    fun `nodes with same ID have same identity`() {
        val nodeSet = mutableSetOf<UniqueDAGNode<Int?>>()

        val node = UniqueDAGNode<Int?>(1)
        val sameNode = UniqueDAGNode<Int?>(1)
        val otherNode = UniqueDAGNode<Int?>(null)

        nodeSet.add(node)

        Assertions.assertTrue(nodeSet.contains(node))
        Assertions.assertTrue(nodeSet.contains(sameNode))
        Assertions.assertFalse(nodeSet.contains(otherNode))
    }

    @Test
    fun `other objects are not equal to a node`() {
        val node = UniqueDAGNode(1)
        val notNode = listOf<Int>()

        val result = node == notNode

        Assertions.assertFalse(result)
    }
}
