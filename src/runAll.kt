fun main() {
    runAndPrintLineBreak { day1.main() }
    runAndPrintLineBreak { day2.main() }
    runAndPrintLineBreak { day3.main() }
    runAndPrintLineBreak { day4.main() }
    runAndPrintLineBreak { day5.main() }
    runAndPrintLineBreak { day6.main() }
    runAndPrintLineBreak { day7.main() }
    runAndPrintLineBreak { day8.main() }
//    runAndPrintLineBreak { day9.main() }
    runAndPrintLineBreak { day10.main() }
//    runAndPrintLineBreak { day11.main() }
//    runAndPrintLineBreak { day12.main() }
}

private fun runAndPrintLineBreak(mainFn: () -> Unit) {
    mainFn()
    println()
}
