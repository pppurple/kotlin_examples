package basic

// bottom, heightはプロパティになる
// プロパティはフィールド、ゲッター、セッター
class Triangle(val bottom: Int, val height: Int) {
    init {
        require(bottom != 0, {"bottom requires over 1"})
    }
    private val s = area(bottom, height)
    override fun toString(): String = "bottom:${bottom},height:${height},area:${s}"
    private fun area(b: Int, h: Int): Int = b * h / 2
}

fun main(args: Array<String>) {
    val tri = Triangle(10, 20)
    println("bottom:" + tri.bottom)
    println("height:" + tri.height)
    println("tri:" + tri)

    // error!!
    // val tri2 = Triangle(0, 20)
}
