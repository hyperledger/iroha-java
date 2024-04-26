//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int
import kotlin.Unit

/**
 * Expression
 *
 * Generated from 'Expression' enum
 */
public sealed class Expression : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Add' variant
     */
    public data class Add(
        public val add: jp.co.soramitsu.iroha2.generated.Add,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Add>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Add> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Add =
                try {
                    Add(
                        jp.co.soramitsu.iroha2.generated.Add.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Add,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Add.write(writer, instance.add)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Subtract' variant
     */
    public data class Subtract(
        public val subtract: jp.co.soramitsu.iroha2.generated.Subtract,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Subtract>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Subtract> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Subtract = try {
                Subtract(
                    jp.co.soramitsu.iroha2.generated.Subtract.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Subtract,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Subtract.write(writer, instance.subtract)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Multiply' variant
     */
    public data class Multiply(
        public val multiply: jp.co.soramitsu.iroha2.generated.Multiply,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Multiply>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Multiply> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Multiply = try {
                Multiply(
                    jp.co.soramitsu.iroha2.generated.Multiply.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Multiply,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Multiply.write(writer, instance.multiply)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Divide' variant
     */
    public data class Divide(
        public val divide: jp.co.soramitsu.iroha2.generated.Divide,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Divide>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Divide> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Divide = try {
                Divide(
                    jp.co.soramitsu.iroha2.generated.Divide.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Divide,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Divide.write(writer, instance.divide)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Mod' variant
     */
    public data class Mod(
        public val mod: jp.co.soramitsu.iroha2.generated.Mod,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Mod>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Mod> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Mod =
                try {
                    Mod(
                        jp.co.soramitsu.iroha2.generated.Mod.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Mod,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Mod.write(writer, instance.mod)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'RaiseTo' variant
     */
    public data class RaiseTo(
        public val raiseTo: jp.co.soramitsu.iroha2.generated.RaiseTo,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.RaiseTo>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.RaiseTo> {
            public const val DISCRIMINANT: Int = 5

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.RaiseTo = try {
                RaiseTo(
                    jp.co.soramitsu.iroha2.generated.RaiseTo.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.RaiseTo,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.RaiseTo.write(writer, instance.raiseTo)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Greater' variant
     */
    public data class Greater(
        public val greater: jp.co.soramitsu.iroha2.generated.Greater,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Greater>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Greater> {
            public const val DISCRIMINANT: Int = 6

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Greater = try {
                Greater(
                    jp.co.soramitsu.iroha2.generated.Greater.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Greater,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Greater.write(writer, instance.greater)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Less' variant
     */
    public data class Less(
        public val less: jp.co.soramitsu.iroha2.generated.Less,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Less>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Less> {
            public const val DISCRIMINANT: Int = 7

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Less = try {
                Less(
                    jp.co.soramitsu.iroha2.generated.Less.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Less,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Less.write(writer, instance.less)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Equal' variant
     */
    public data class Equal(
        public val equal: jp.co.soramitsu.iroha2.generated.Equal,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Equal>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Equal> {
            public const val DISCRIMINANT: Int = 8

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Equal = try {
                Equal(
                    jp.co.soramitsu.iroha2.generated.Equal.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Equal,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Equal.write(writer, instance.equal)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Not' variant
     */
    public data class Not(
        public val not: jp.co.soramitsu.iroha2.generated.Not,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Not>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Not> {
            public const val DISCRIMINANT: Int = 9

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Not =
                try {
                    Not(
                        jp.co.soramitsu.iroha2.generated.Not.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Not,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Not.write(writer, instance.not)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'And' variant
     */
    public data class And(
        public val and: jp.co.soramitsu.iroha2.generated.And,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.And>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.And> {
            public const val DISCRIMINANT: Int = 10

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.And =
                try {
                    And(
                        jp.co.soramitsu.iroha2.generated.And.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.And,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.And.write(writer, instance.and)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Or' variant
     */
    public data class Or(
        public val or: jp.co.soramitsu.iroha2.generated.Or,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Or>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Or> {
            public const val DISCRIMINANT: Int = 11

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Or =
                try {
                    Or(
                        jp.co.soramitsu.iroha2.generated.Or.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Or,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Or.write(writer, instance.or)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'If' variant
     */
    public data class If(
        public val `if`: jp.co.soramitsu.iroha2.generated.If,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.If>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.If> {
            public const val DISCRIMINANT: Int = 12

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.If =
                try {
                    If(
                        jp.co.soramitsu.iroha2.generated.If.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.If,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.If.write(writer, instance.`if`)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Raw' variant
     */
    public data class Raw(
        public val `value`: Value,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Raw>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Raw> {
            public const val DISCRIMINANT: Int = 13

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Raw =
                try {
                    Raw(
                        Value.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Raw,
            ): Unit = try {
                Value.write(writer, instance.`value`)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Query' variant
     */
    public data class Query(
        public val queryBox: QueryBox,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Query>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Query> {
            public const val DISCRIMINANT: Int = 14

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Query = try {
                Query(
                    QueryBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Query,
            ): Unit = try {
                QueryBox.write(writer, instance.queryBox)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Contains' variant
     */
    public data class Contains(
        public val contains: jp.co.soramitsu.iroha2.generated.Contains,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Contains>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Contains> {
            public const val DISCRIMINANT: Int = 15

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Contains = try {
                Contains(
                    jp.co.soramitsu.iroha2.generated.Contains.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Contains,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Contains.write(writer, instance.contains)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ContainsAll' variant
     */
    public data class ContainsAll(
        public val containsAll: jp.co.soramitsu.iroha2.generated.ContainsAll,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.ContainsAll>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.ContainsAll> {
            public const val DISCRIMINANT: Int = 16

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.ContainsAll = try {
                ContainsAll(
                    jp.co.soramitsu.iroha2.generated.ContainsAll.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.ContainsAll,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.ContainsAll.write(writer, instance.containsAll)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ContainsAny' variant
     */
    public data class ContainsAny(
        public val containsAny: jp.co.soramitsu.iroha2.generated.ContainsAny,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.ContainsAny>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.ContainsAny> {
            public const val DISCRIMINANT: Int = 17

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.ContainsAny = try {
                ContainsAny(
                    jp.co.soramitsu.iroha2.generated.ContainsAny.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.ContainsAny,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.ContainsAny.write(writer, instance.containsAny)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Where' variant
     */
    public data class Where(
        public val `where`: jp.co.soramitsu.iroha2.generated.Where,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.Where>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.Where> {
            public const val DISCRIMINANT: Int = 18

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.Where = try {
                Where(
                    jp.co.soramitsu.iroha2.generated.Where.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.Where,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.Where.write(writer, instance.`where`)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ContextValue' variant
     */
    public data class ContextValue(
        public val contextValue: jp.co.soramitsu.iroha2.generated.ContextValue,
    ) : Expression() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.Expression.ContextValue>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.Expression.ContextValue> {
            public const val DISCRIMINANT: Int = 19

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.Expression.ContextValue = try {
                ContextValue(
                    jp.co.soramitsu.iroha2.generated.ContextValue.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.Expression.ContextValue,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.ContextValue.write(writer, instance.contextValue)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Expression>, ScaleWriter<Expression> {
        override fun read(reader: ScaleCodecReader): Expression = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Add.read(reader)
            1 -> Subtract.read(reader)
            2 -> Multiply.read(reader)
            3 -> Divide.read(reader)
            4 -> Mod.read(reader)
            5 -> RaiseTo.read(reader)
            6 -> Greater.read(reader)
            7 -> Less.read(reader)
            8 -> Equal.read(reader)
            9 -> Not.read(reader)
            10 -> And.read(reader)
            11 -> Or.read(reader)
            12 -> If.read(reader)
            13 -> Raw.read(reader)
            14 -> Query.read(reader)
            15 -> Contains.read(reader)
            16 -> ContainsAll.read(reader)
            17 -> ContainsAny.read(reader)
            18 -> Where.read(reader)
            19 -> ContextValue.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: Expression) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Add.write(writer, instance as Add)
                1 -> Subtract.write(writer, instance as Subtract)
                2 -> Multiply.write(writer, instance as Multiply)
                3 -> Divide.write(writer, instance as Divide)
                4 -> Mod.write(writer, instance as Mod)
                5 -> RaiseTo.write(writer, instance as RaiseTo)
                6 -> Greater.write(writer, instance as Greater)
                7 -> Less.write(writer, instance as Less)
                8 -> Equal.write(writer, instance as Equal)
                9 -> Not.write(writer, instance as Not)
                10 -> And.write(writer, instance as And)
                11 -> Or.write(writer, instance as Or)
                12 -> If.write(writer, instance as If)
                13 -> Raw.write(writer, instance as Raw)
                14 -> Query.write(writer, instance as Query)
                15 -> Contains.write(writer, instance as Contains)
                16 -> ContainsAll.write(writer, instance as ContainsAll)
                17 -> ContainsAny.write(writer, instance as ContainsAny)
                18 -> Where.write(writer, instance as Where)
                19 -> ContextValue.write(writer, instance as ContextValue)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
