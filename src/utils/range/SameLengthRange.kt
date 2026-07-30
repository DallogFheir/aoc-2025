package utils.range

class SameLengthRange(start: Long, end: Long) : Range(start = start, end = end) {
    init {
        require(startDigitCount == endDigitCount) { "start and end must be of the same length" }
    }
}
