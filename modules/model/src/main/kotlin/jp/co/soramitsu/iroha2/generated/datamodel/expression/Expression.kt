//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int

/**
 * Expression
 *
 * Generated from 'iroha_data_model::expression::Expression' enum
 */
public sealed class Expression {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Add' variant
     */
    public data class Add(
        public val add: jp.co.soramitsu.iroha2.generated.datamodel.expression.Add
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Add>, ScaleWriter<Add> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Add = try {
                Add(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.Add.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Add) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.Add.write(writer, instance.add)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Subtract' variant
     */
    public data class Subtract(
        public val subtract: jp.co.soramitsu.iroha2.generated.datamodel.expression.Subtract
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Subtract>, ScaleWriter<Subtract> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Subtract = try {
                Subtract(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.Subtract.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Subtract) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.Subtract.write(
                    writer,
                    instance.subtract
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Multiply' variant
     */
    public data class Multiply(
        public val multiply: jp.co.soramitsu.iroha2.generated.datamodel.expression.Multiply
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Multiply>, ScaleWriter<Multiply> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Multiply = try {
                Multiply(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.Multiply.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Multiply) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.Multiply.write(
                    writer,
                    instance.multiply
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Divide' variant
     */
    public data class Divide(
        public val divide: jp.co.soramitsu.iroha2.generated.datamodel.expression.Divide
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Divide>, ScaleWriter<Divide> {
            public const val DISCRIMINANT: Int = 3

            public override fun read(reader: ScaleCodecReader): Divide = try {
                Divide(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.Divide.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Divide) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.Divide.write(writer, instance.divide)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Mod' variant
     */
    public data class Mod(
        public val mod: jp.co.soramitsu.iroha2.generated.datamodel.expression.Mod
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Mod>, ScaleWriter<Mod> {
            public const val DISCRIMINANT: Int = 4

            public override fun read(reader: ScaleCodecReader): Mod = try {
                Mod(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.Mod.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Mod) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.Mod.write(writer, instance.mod)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'RaiseTo' variant
     */
    public data class RaiseTo(
        public val raiseTo: jp.co.soramitsu.iroha2.generated.datamodel.expression.RaiseTo
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<RaiseTo>, ScaleWriter<RaiseTo> {
            public const val DISCRIMINANT: Int = 5

            public override fun read(reader: ScaleCodecReader): RaiseTo = try {
                RaiseTo(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.RaiseTo.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: RaiseTo) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.RaiseTo.write(
                    writer,
                    instance.raiseTo
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Greater' variant
     */
    public data class Greater(
        public val greater: jp.co.soramitsu.iroha2.generated.datamodel.expression.Greater
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Greater>, ScaleWriter<Greater> {
            public const val DISCRIMINANT: Int = 6

            public override fun read(reader: ScaleCodecReader): Greater = try {
                Greater(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.Greater.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Greater) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.Greater.write(
                    writer,
                    instance.greater
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Less' variant
     */
    public data class Less(
        public val less: jp.co.soramitsu.iroha2.generated.datamodel.expression.Less
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Less>, ScaleWriter<Less> {
            public const val DISCRIMINANT: Int = 7

            public override fun read(reader: ScaleCodecReader): Less = try {
                Less(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.Less.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Less) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.Less.write(writer, instance.less)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Equal' variant
     */
    public data class Equal(
        public val equal: jp.co.soramitsu.iroha2.generated.datamodel.expression.Equal
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Equal>, ScaleWriter<Equal> {
            public const val DISCRIMINANT: Int = 8

            public override fun read(reader: ScaleCodecReader): Equal = try {
                Equal(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.Equal.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Equal) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.Equal.write(writer, instance.equal)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Not' variant
     */
    public data class Not(
        public val not: jp.co.soramitsu.iroha2.generated.datamodel.expression.Not
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Not>, ScaleWriter<Not> {
            public const val DISCRIMINANT: Int = 9

            public override fun read(reader: ScaleCodecReader): Not = try {
                Not(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.Not.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Not) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.Not.write(writer, instance.not)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'And' variant
     */
    public data class And(
        public val and: jp.co.soramitsu.iroha2.generated.datamodel.expression.And
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<And>, ScaleWriter<And> {
            public const val DISCRIMINANT: Int = 10

            public override fun read(reader: ScaleCodecReader): And = try {
                And(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.And.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: And) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.And.write(writer, instance.and)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Or' variant
     */
    public data class Or(
        public val or: jp.co.soramitsu.iroha2.generated.datamodel.expression.Or
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Or>, ScaleWriter<Or> {
            public const val DISCRIMINANT: Int = 11

            public override fun read(reader: ScaleCodecReader): Or = try {
                Or(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.Or.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Or) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.Or.write(writer, instance.or)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'If' variant
     */
    public data class If(
        public val `if`: jp.co.soramitsu.iroha2.generated.datamodel.expression.If
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<If>, ScaleWriter<If> {
            public const val DISCRIMINANT: Int = 12

            public override fun read(reader: ScaleCodecReader): If = try {
                If(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.If.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: If) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.If.write(writer, instance.`if`)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Raw' variant
     */
    public data class Raw(
        public val `value`: Value
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Raw>, ScaleWriter<Raw> {
            public const val DISCRIMINANT: Int = 13

            public override fun read(reader: ScaleCodecReader): Raw = try {
                Raw(
                    Value.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Raw) = try {
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
        public val queryBox: QueryBox
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Query>, ScaleWriter<Query> {
            public const val DISCRIMINANT: Int = 14

            public override fun read(reader: ScaleCodecReader): Query = try {
                Query(
                    QueryBox.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Query) = try {
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
        public val contains: jp.co.soramitsu.iroha2.generated.datamodel.expression.Contains
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Contains>, ScaleWriter<Contains> {
            public const val DISCRIMINANT: Int = 15

            public override fun read(reader: ScaleCodecReader): Contains = try {
                Contains(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.Contains.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Contains) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.Contains.write(
                    writer,
                    instance.contains
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ContainsAll' variant
     */
    public data class ContainsAll(
        public val containsAll: jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAll
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ContainsAll>, ScaleWriter<ContainsAll> {
            public const val DISCRIMINANT: Int = 16

            public override fun read(reader: ScaleCodecReader): ContainsAll = try {
                ContainsAll(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAll.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ContainsAll) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAll.write(
                    writer,
                    instance.containsAll
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ContainsAny' variant
     */
    public data class ContainsAny(
        public val containsAny: jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAny
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ContainsAny>, ScaleWriter<ContainsAny> {
            public const val DISCRIMINANT: Int = 17

            public override fun read(reader: ScaleCodecReader): ContainsAny = try {
                ContainsAny(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAny.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ContainsAny) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAny.write(
                    writer,
                    instance.containsAny
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Where' variant
     */
    public data class Where(
        public val `where`: jp.co.soramitsu.iroha2.generated.datamodel.expression.Where
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Where>, ScaleWriter<Where> {
            public const val DISCRIMINANT: Int = 18

            public override fun read(reader: ScaleCodecReader): Where = try {
                Where(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.Where.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Where) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.Where.write(writer, instance.`where`)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'ContextValue' variant
     */
    public data class ContextValue(
        public val contextValue: jp.co.soramitsu.iroha2.generated.datamodel.expression.ContextValue
    ) : Expression() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<ContextValue>, ScaleWriter<ContextValue> {
            public const val DISCRIMINANT: Int = 19

            public override fun read(reader: ScaleCodecReader): ContextValue = try {
                ContextValue(
                    jp.co.soramitsu.iroha2.generated.datamodel.expression.ContextValue.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: ContextValue) = try {
                jp.co.soramitsu.iroha2.generated.datamodel.expression.ContextValue.write(
                    writer,
                    instance.contextValue
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<Expression>, ScaleWriter<Expression> {
        public override fun read(reader: ScaleCodecReader): Expression = when (
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
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: Expression) {
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
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
