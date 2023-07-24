package processor

fun main() {
    while (true) {
        println("1. Add matrices\n" +
                "2. Multiply matrix by a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "6. Inverse matrix\n" +
                "0. Exit\n" +
                "Your choice:")
        when (readln().toInt()) {
            1 -> printMatrix(add())
            2 -> printMatrix(multByNumber())
            3 -> mult()
            4 -> transpose()
            5 -> calculateDeterminant()
            6 -> inverse()
            else -> break
        }
    }
}

fun add(): Matrix? {
    val a = Matrix(1)
    val b = Matrix(2)
    return if (a.sizeIsEqual(b)) {
        Matrix(a.matrix.zip(b.matrix) { rowA, rowB -> rowA.zip(rowB) { x, y -> x + y } })
    } else {
        println("The operation cannot be performed.\n")
        null
    }
}

fun multByNumber(): Matrix {
    val a = Matrix()
    println("Enter constant:")
    val num = readln().toDouble()
    return Matrix(a.matrix.map { it.map { it * num } })
}

fun transpose() {
    println("1. Main diagonal\n" +
            "2. Side diagonal\n" +
            "3. Vertical line\n" +
            "4. Horizontal line\n" +
            "Your choice:")
    val choice = readln().toInt()
    val a = Matrix()
    printMatrix(transpose(choice, a))
}

fun transpose(choice: Int, a: Matrix): Matrix {
    val result = MutableList(a.rows) { MutableList(a.columns) { 0.0 } }
    when (choice) {
        1 -> {
            for (x in result.indices) {
                for (y in result[0].indices)
                    result[x][y] = a.matrix[y][x]
            }
        }
        2 -> {
            for (x in result.indices) {
                for (y in result[0].indices)
                    result[x][y] = a.matrix[a.matrix[0].size - 1 - y][a.matrix.size - 1 - x]
            }
        }
        3 -> {
            for (x in result.indices) {
                for (y in result[0].indices)
                    result[x][y] = a.matrix[x][a.matrix[0].size - 1 - y]
            }
        }
        4 -> {
            for (x in result.indices) {
                for (y in result[0].indices)
                    result[x][y] = a.matrix[a.matrix.size - 1 - x][y]
            }
        }
    }
    return Matrix(result)
}

fun mult() {
    val a = Matrix(1)
    val b = Matrix(2)
    return if (a.multIsPossible(b)) {
        val result = MutableList(a.rows) { MutableList(b.columns) { 0.0 } }
        result.forEachIndexed { rowIndex, x -> x.forEachIndexed { columnIndex, _ ->
            var value = 0.0
            a.matrix[rowIndex].forEachIndexed { index, i ->
                value += i * b.matrix[index][columnIndex]
            }
            result[rowIndex][columnIndex] = value
        } }
        printMatrix(Matrix(result))
    } else {
        println("The operation cannot be performed.\n")
    }
}

fun printMatrix(matrix: Matrix?) {
    matrix?.matrix?.forEach { println(it.joinToString(" ")) }
}