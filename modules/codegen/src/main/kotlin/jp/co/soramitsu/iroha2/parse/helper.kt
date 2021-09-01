package jp.co.soramitsu.iroha2.parse

private val GENERIC_REGEX = "^([^<]*)<(.+)>\$".toRegex() // PartName<SubType>
private const val TYPE_GROUP_INDEX = 2 // first one will be the entire typeDef, the second one will be raw type

fun extractGeneric(name: String, parser: SchemaParser): List<TypeNest> {
    val groups = GENERIC_REGEX.find(name)?.groupValues ?: return listOf()
    val rawType = groups.getOrNull(TYPE_GROUP_INDEX) ?: return listOf()
    return listOf(parser.createAndGetNest(rawType))
}
