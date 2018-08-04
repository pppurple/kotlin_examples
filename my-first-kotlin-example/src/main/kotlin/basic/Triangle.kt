package basic

// bottom, heightはプロパティになる
// プロパティはフィールド、ゲッター、セッター
class Triangle(val bottom: Int, val height: Int) {
    init {
        require(bottom != 0, {"bottom requires over 1"})
    }
    private val s = area(bottom, height)

    // fun plus(t: Triangle): Triangle = Triangle(bottom + t.bottom, height + t.height)

    // 演算子overload
    operator fun plus(t: Triangle): Triangle = Triangle(bottom + t.bottom, height + t.height)
    operator fun plus(n: Int): Triangle = Triangle(bottom + n, height + n)

    // override
    override fun toString(): String = "bottom:${bottom},height:${height},area:${s}"
    private fun area(b: Int, h: Int): Int = b * h / 2
}

// 拡張関数
operator fun Int.plus(t: Triangle): Triangle = t + this

fun main(args: Array<String>) {
    val tri = Triangle(10, 20)
    println("bottom:" + tri.bottom)
    println("height:" + tri.height)
    println("tri:" + tri)

    // error!!
    // val tri2 = Triangle(0, 20)

    // plus
    val tri2 = Triangle(20, 40)
    // println("plus:" + tri.plus(tri2))

    // plus operator
    print("+:")
    println(tri + tri2)
    print("+:")
    println(tri + 100)

    // plus 拡張関数
    print("+(extension):")
    println(200 + tri)
}
