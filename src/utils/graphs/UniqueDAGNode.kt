package utils.graphs

class UniqueDAGNode<T>(val id: T, neighbors: MutableList<DAGNode> = mutableListOf()) :
    DAGNode(neighbors = neighbors) {
    override fun equals(other: Any?): Boolean {
        if (other !is UniqueDAGNode<*>) {
            return false
        }

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
