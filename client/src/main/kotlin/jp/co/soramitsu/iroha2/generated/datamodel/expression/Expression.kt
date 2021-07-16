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
import kotlin.Int
import kotlin.Unit

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
  public class Add(
    private val add: jp.co.soramitsu.iroha2.generated.datamodel.expression.Add
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Add>, ScaleWriter<Add> {
      public const val DISCRIMINANT: Int = 0

      public override fun read(reader: ScaleCodecReader): Add {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Add): Unit {
      }
    }
  }

  /**
   * 'Subtract' variant
   */
  public class Subtract(
    private val subtract: jp.co.soramitsu.iroha2.generated.datamodel.expression.Subtract
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Subtract>, ScaleWriter<Subtract> {
      public const val DISCRIMINANT: Int = 1

      public override fun read(reader: ScaleCodecReader): Subtract {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Subtract): Unit {
      }
    }
  }

  /**
   * 'Multiply' variant
   */
  public class Multiply(
    private val multiply: jp.co.soramitsu.iroha2.generated.datamodel.expression.Multiply
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Multiply>, ScaleWriter<Multiply> {
      public const val DISCRIMINANT: Int = 2

      public override fun read(reader: ScaleCodecReader): Multiply {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Multiply): Unit {
      }
    }
  }

  /**
   * 'Divide' variant
   */
  public class Divide(
    private val divide: jp.co.soramitsu.iroha2.generated.datamodel.expression.Divide
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Divide>, ScaleWriter<Divide> {
      public const val DISCRIMINANT: Int = 3

      public override fun read(reader: ScaleCodecReader): Divide {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Divide): Unit {
      }
    }
  }

  /**
   * 'Mod' variant
   */
  public class Mod(
    private val mod: jp.co.soramitsu.iroha2.generated.datamodel.expression.Mod
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Mod>, ScaleWriter<Mod> {
      public const val DISCRIMINANT: Int = 4

      public override fun read(reader: ScaleCodecReader): Mod {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Mod): Unit {
      }
    }
  }

  /**
   * 'RaiseTo' variant
   */
  public class RaiseTo(
    private val raiseTo: jp.co.soramitsu.iroha2.generated.datamodel.expression.RaiseTo
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<RaiseTo>, ScaleWriter<RaiseTo> {
      public const val DISCRIMINANT: Int = 5

      public override fun read(reader: ScaleCodecReader): RaiseTo {
      }

      public override fun write(writer: ScaleCodecWriter, instance: RaiseTo): Unit {
      }
    }
  }

  /**
   * 'Greater' variant
   */
  public class Greater(
    private val greater: jp.co.soramitsu.iroha2.generated.datamodel.expression.Greater
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Greater>, ScaleWriter<Greater> {
      public const val DISCRIMINANT: Int = 6

      public override fun read(reader: ScaleCodecReader): Greater {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Greater): Unit {
      }
    }
  }

  /**
   * 'Less' variant
   */
  public class Less(
    private val less: jp.co.soramitsu.iroha2.generated.datamodel.expression.Less
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Less>, ScaleWriter<Less> {
      public const val DISCRIMINANT: Int = 7

      public override fun read(reader: ScaleCodecReader): Less {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Less): Unit {
      }
    }
  }

  /**
   * 'Equal' variant
   */
  public class Equal(
    private val equal: jp.co.soramitsu.iroha2.generated.datamodel.expression.Equal
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Equal>, ScaleWriter<Equal> {
      public const val DISCRIMINANT: Int = 8

      public override fun read(reader: ScaleCodecReader): Equal {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Equal): Unit {
      }
    }
  }

  /**
   * 'Not' variant
   */
  public class Not(
    private val not: jp.co.soramitsu.iroha2.generated.datamodel.expression.Not
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Not>, ScaleWriter<Not> {
      public const val DISCRIMINANT: Int = 9

      public override fun read(reader: ScaleCodecReader): Not {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Not): Unit {
      }
    }
  }

  /**
   * 'And' variant
   */
  public class And(
    private val and: jp.co.soramitsu.iroha2.generated.datamodel.expression.And
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<And>, ScaleWriter<And> {
      public const val DISCRIMINANT: Int = 10

      public override fun read(reader: ScaleCodecReader): And {
      }

      public override fun write(writer: ScaleCodecWriter, instance: And): Unit {
      }
    }
  }

  /**
   * 'Or' variant
   */
  public class Or(
    private val or: jp.co.soramitsu.iroha2.generated.datamodel.expression.Or
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Or>, ScaleWriter<Or> {
      public const val DISCRIMINANT: Int = 11

      public override fun read(reader: ScaleCodecReader): Or {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Or): Unit {
      }
    }
  }

  /**
   * 'If' variant
   */
  public class If(
    private val `if`: jp.co.soramitsu.iroha2.generated.datamodel.expression.If
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<If>, ScaleWriter<If> {
      public const val DISCRIMINANT: Int = 12

      public override fun read(reader: ScaleCodecReader): If {
      }

      public override fun write(writer: ScaleCodecWriter, instance: If): Unit {
      }
    }
  }

  /**
   * 'Raw' variant
   */
  public class Raw(
    private val raw: Value
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Raw>, ScaleWriter<Raw> {
      public const val DISCRIMINANT: Int = 13

      public override fun read(reader: ScaleCodecReader): Raw {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Raw): Unit {
      }
    }
  }

  /**
   * 'Query' variant
   */
  public class Query(
    private val query: QueryBox
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Query>, ScaleWriter<Query> {
      public const val DISCRIMINANT: Int = 14

      public override fun read(reader: ScaleCodecReader): Query {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Query): Unit {
      }
    }
  }

  /**
   * 'Contains' variant
   */
  public class Contains(
    private val contains: jp.co.soramitsu.iroha2.generated.datamodel.expression.Contains
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Contains>, ScaleWriter<Contains> {
      public const val DISCRIMINANT: Int = 15

      public override fun read(reader: ScaleCodecReader): Contains {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Contains): Unit {
      }
    }
  }

  /**
   * 'ContainsAll' variant
   */
  public class ContainsAll(
    private val containsAll: jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAll
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<ContainsAll>, ScaleWriter<ContainsAll> {
      public const val DISCRIMINANT: Int = 16

      public override fun read(reader: ScaleCodecReader): ContainsAll {
      }

      public override fun write(writer: ScaleCodecWriter, instance: ContainsAll): Unit {
      }
    }
  }

  /**
   * 'ContainsAny' variant
   */
  public class ContainsAny(
    private val containsAny: jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAny
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<ContainsAny>, ScaleWriter<ContainsAny> {
      public const val DISCRIMINANT: Int = 17

      public override fun read(reader: ScaleCodecReader): ContainsAny {
      }

      public override fun write(writer: ScaleCodecWriter, instance: ContainsAny): Unit {
      }
    }
  }

  /**
   * 'Where' variant
   */
  public class Where(
    private val `where`: jp.co.soramitsu.iroha2.generated.datamodel.expression.Where
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<Where>, ScaleWriter<Where> {
      public const val DISCRIMINANT: Int = 18

      public override fun read(reader: ScaleCodecReader): Where {
      }

      public override fun write(writer: ScaleCodecWriter, instance: Where): Unit {
      }
    }
  }

  /**
   * 'ContextValue' variant
   */
  public class ContextValue(
    private val contextValue: jp.co.soramitsu.iroha2.generated.datamodel.expression.ContextValue
  ) : Expression() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<ContextValue>, ScaleWriter<ContextValue> {
      public const val DISCRIMINANT: Int = 19

      public override fun read(reader: ScaleCodecReader): ContextValue {
      }

      public override fun write(writer: ScaleCodecWriter, instance: ContextValue): Unit {
      }
    }
  }

  public companion object : ScaleReader<Expression>, ScaleWriter<Expression> {
    public override fun read(reader: ScaleCodecReader): Expression = when(val discriminant =
        reader.readUByte()) {
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
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: Expression): Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
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
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant $discriminant")}
    }
  }
}
