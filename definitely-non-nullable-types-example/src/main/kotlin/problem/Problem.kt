package problem

/*
fun <T : CharSequence?> foo(t: T, tn: T?) {
    t.length // call is not allowed, `t` may be null
    tn.length // call is not allowed, `tn` may be null
}

fun <F : CharSequence> bar(f: F, fn: F?) {
    f.length // call is allowed, `f` may not be null
    fn.length // call is not allowed, `fn` may be null
}
*/

fun <T> printT(t: T) {
    println(t)
}

fun main() {
    printT<String>("abc")
    // error
    // printT<String>(null)
    printT<String?>(null)

    // type inference
    printT("abc")
    printT(123)
    printT(null)
}
