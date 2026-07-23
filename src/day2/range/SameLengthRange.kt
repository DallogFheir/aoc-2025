package day2.range

class SameLengthRange(start: Int, end: Int) : Range(start = start, end = end) {
    init {
        require(startDigitCount == endDigitCount) { "start and end must be of the same length" }
    }
}
