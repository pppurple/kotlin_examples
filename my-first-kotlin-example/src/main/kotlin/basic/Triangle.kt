package basic

// bottom, heightはプロパティになる
// プロパティはフィールド、ゲッター、セッター
class Triangle(val bottom: Int, val height: Int)

fun main(args: Array<String>) {
    val tri = Triangle(10, 20)
    println("bottom:" + tri.bottom)
    println("height:" + tri.height)
}
