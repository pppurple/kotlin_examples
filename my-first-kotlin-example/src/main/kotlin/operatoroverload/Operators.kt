package operatoroverload

fun main(args: Array<String>) {
    val num2 = Number(2)
    val num3 = Number(3)
    val num4 = Number(4)

    // plus
    val plus = num2 + num4
    println("plus:" + plus.n)

    // minus
    val minus = num2 - num4
    println("minus:" + minus.n)

    // times
    val times = num2 * num4
    println("times:" + times.n)

    // div
    val div = num4 / num2
    println("div:" + div.n)

    // rem (旧mod)
    val rem = num3 % num2
    println("rem:" + rem.n)

    // inc
    // post increment
    var num10 = Number(10)
    val postInc = num10++
    println("inc(post):" + num10.n)
    println("result(post):" + postInc.n)
    // pre increment
    var num20 = Number(20)
    val preInc = ++num20
    println("inc(pre):" + num20.n)
    println("result(pre):" + preInc.n)

    // dec
    // post decrement
    var num30 = Number(30)
    val postDec = num30++
    println("dec(post):" + num30.n)
    println("result(post):" + postDec.n)
    // pre decrement
    var num40 = Number(40)
    val preDec = ++num40
    println("dec(pre):" + num40.n)
    println("result(pre):" + preDec.n)

    // not
    val bool = BoolObject(true)
    println("bool:" + bool.b)
    val not = !bool
    println("not:$not")
}

class Number(val n: Int) {
    // +
    operator fun plus(that: Number): Number = Number(n + that.n)
    // -
    operator fun minus(that: Number): Number = Number(n - that.n)
    // *
    operator fun times(that: Number): Number = Number(n * that.n)
    // /
    operator fun div(that: Number): Number = Number(n / that.n)
    // %
    operator fun rem(that: Number): Number = Number(n % that.n)
    // ++
    operator fun inc(): Number = Number(n + 1)
    // --
    operator fun dec(): Number = Number(n - 1)
}

class BoolObject(val b: Boolean) {
    // !
    operator fun not(): Boolean = !b
}
