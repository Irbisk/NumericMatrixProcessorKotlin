package processor

import kotlin.math.pow

fun calculateDeterminant() {
    val matrix = Matrix()
    println("The result is:")
    println(getDeterminant(matrix))
}

fun inverse() {
    val a = Matrix()
    val det = getDeterminant(a)
    if (det == 0.0) {
        println("This matrix doesn't have an inverse.")
    } else {
        printMatrix(Matrix(getAdj(a).matrix.map { it.map { it * (1 / det) } }))
    }
}

fun getAdj(a: Matrix): Matrix {
    val matrix = a.matrix.mapIndexed { i, doubles ->
        List(doubles.size) { j ->
            (-1.0).pow((i + j).toDouble()) * getMinor(Matrix(a.matrix), i, j) }
    }
    return transpose(1, Matrix(matrix))
}

fun getMinor (a: Matrix, x: Int, y: Int): Double {
    val matrix = a.matrix.filterIndexed { i, _ -> i != x }
        .map { it.filterIndexed { j, _ -> j != y } }
    return getDeterminant(Matrix(matrix))
}

fun getDeterminant(a: Matrix): Double {
    if (a.columns == 2 && a.rows == 2) {
        return a.matrix[0][0] * a.matrix[1][1] - a.matrix[1][0] * a.matrix[0][1]
    }

    var sum = 0.0

    for (j in 0 until a.columns) {
        sum += (-1.0).pow((2 + j).toDouble()) * a.matrix[0][j] * getMinor(Matrix(a.matrix), 0, j)
    }
    return sum;
}