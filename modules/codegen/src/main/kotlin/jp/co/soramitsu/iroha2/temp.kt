package jp.co.soramitsu.iroha2

fun main() {
    val map = mapOf(
        "foo_bar::abc" to Any(),
        "foo_bar1::def" to Any(),
        "foo_bar2::def" to Any(),
        "foo_bar3::def" to Any(),
        "foo_bar1::qqq" to Any(),
        "foo_bar2::qqq" to Any(),
        "foo_bar::zzz" to Any()
    )

//    println(map.keys.groupBy { it.split("::").last() })
    println("foo::bar::Id".contains("(.+)::(.+)".toRegex()))
}
