fun main() {
    runAndPrintLineBreak { day01.main() }
    runAndPrintLineBreak { day02.main() }
    runAndPrintLineBreak { day03.main() }
    runAndPrintLineBreak { day04.main() }
    runAndPrintLineBreak { day05.main() }
    runAndPrintLineBreak { day06.main() }
    runAndPrintLineBreak { day07.main() }
    runAndPrintLineBreak { day08.main() }
    runAndPrintLineBreak { day09.main() }
    runAndPrintLineBreak { day10.main() }
    runAndPrintLineBreak { day11.main() }
    runAndPrintLineBreak { day12.main() }
}

private fun runAndPrintLineBreak(mainFn: () -> Unit) {
    mainFn()
    println()
}
