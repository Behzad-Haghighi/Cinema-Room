package cinema

import kotlin.math.ceil
import kotlin.math.floor

fun showSalone(salone: MutableList<MutableList<String>>, row: Int, col: Int) {
    println("Cinema:")
    for (i in 0..col) if (i == 0) print(" ") else print(" " + i)
    for (i in 0 until row) print("\n${i + 1} ${salone[i].joinToString(" ")}")
}

fun buySeat(
    salone: MutableList<MutableList<String>>,
    row: Int,
    col: Int,
) {
    try {
        var crow: Int = 0
        var ccol: Int = 0
        do {
            println("\nEnter a row number:")
            crow = readln().toInt()
            println("Enter a seat number in that row:")
            ccol = readln().toInt()
        } while (if (salone[crow - 1][ccol - 1] == "B") {
                println("That ticket has already been purchased!")
                true
            } else {
                false
            }
        )
        var seatprice = if (row * col < 60) 10 else if (crow <= floor(row / 2.0)) 10 else 8
        println("Ticket price: \$$seatprice\n")
        salone[crow - 1][ccol - 1] = "B"
    } catch (e: IndexOutOfBoundsException) {
        println("Wrong input!")
        buySeat(salone, row, col)
    }
}

fun statistics(
    salone: MutableList<MutableList<String>>,
    srow: Int,
    scol: Int,
) {
    var firstclass = 0
    var regular = 0
    for (i in 0 until srow) {
        for (j in 0 until scol) {
            if (srow * scol < 60) {
                if (salone[i][j] == "B") {
                    firstclass++
                }
            } else {
                if (i < floor(srow / 2.0) && salone[i][j] == "B") {
                    firstclass++
                } else if (salone[i][j] == "B") {
                    regular++
                }
            }
        }

    }
    val percent = (firstclass + regular).toDouble() / (srow * scol).toDouble() * 100
    val formatPercent = "%.2f".format(percent)
    println("Number of purchased tickets: ${firstclass + regular}")
    println("Percentage: $formatPercent%")
    val currentIncome = firstclass * 10 + regular * 8
    println("Current income: $$currentIncome")
    val totalIncome =
        if (srow * scol < 60) srow * scol * 10 else floor(srow / 2.0) * scol * 10 + ceil(srow / 2.0) * scol * 8
    println("Total income: $${totalIncome.toInt()}")
}

fun main() {
    println("Enter the number of rows:")
    val row = readln().toInt()
    println("Enter the number of seats in each row:")
    val col = readln().toInt()
    val salone = MutableList(row) { MutableList(col) { "S" } }
    while (true) {
        println("\n1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        when (readln()) {
            "1" -> showSalone(salone, row, col)
            "2" -> buySeat(salone, row, col)
            "3" -> statistics(salone, row, col)
            "0" -> return
        }
    }
}