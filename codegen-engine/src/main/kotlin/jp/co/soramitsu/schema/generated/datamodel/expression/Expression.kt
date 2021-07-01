// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.expression

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import jp.co.soramitsu.schema.generated.datamodel.Value
import jp.co.soramitsu.schema.generated.datamodel.query.QueryBox
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
    private val add: jp.co.soramitsu.schema.generated.datamodel.expression.Add
  ) : Expression() {
    public override fun discriminant(): Int = 0

    public companion object CODEC : ScaleReader<Add>, ScaleWriter<Add> {
      public override fun read(reader: ScaleCodecReader): Add =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Add(jp.co.soramitsu.schema.generated.datamodel.expression.Add.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Add): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.Add.write(writer, instance.`add`)
      }
    }
  }

  /**
   * 'Subtract' variant
   */
  public class Subtract(
    private val subtract: jp.co.soramitsu.schema.generated.datamodel.expression.Subtract
  ) : Expression() {
    public override fun discriminant(): Int = 1

    public companion object CODEC : ScaleReader<Subtract>, ScaleWriter<Subtract> {
      public override fun read(reader: ScaleCodecReader): Subtract =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Subtract(jp.co.soramitsu.schema.generated.datamodel.expression.Subtract.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Subtract): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.Subtract.write(writer,
            instance.`subtract`)
      }
    }
  }

  /**
   * 'Multiply' variant
   */
  public class Multiply(
    private val multiply: jp.co.soramitsu.schema.generated.datamodel.expression.Multiply
  ) : Expression() {
    public override fun discriminant(): Int = 2

    public companion object CODEC : ScaleReader<Multiply>, ScaleWriter<Multiply> {
      public override fun read(reader: ScaleCodecReader): Multiply =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Multiply(jp.co.soramitsu.schema.generated.datamodel.expression.Multiply.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Multiply): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.Multiply.write(writer,
            instance.`multiply`)
      }
    }
  }

  /**
   * 'Divide' variant
   */
  public class Divide(
    private val divide: jp.co.soramitsu.schema.generated.datamodel.expression.Divide
  ) : Expression() {
    public override fun discriminant(): Int = 3

    public companion object CODEC : ScaleReader<Divide>, ScaleWriter<Divide> {
      public override fun read(reader: ScaleCodecReader): Divide =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Divide(jp.co.soramitsu.schema.generated.datamodel.expression.Divide.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Divide): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.Divide.write(writer,
            instance.`divide`)
      }
    }
  }

  /**
   * 'Mod' variant
   */
  public class Mod(
    private val mod: jp.co.soramitsu.schema.generated.datamodel.expression.Mod
  ) : Expression() {
    public override fun discriminant(): Int = 4

    public companion object CODEC : ScaleReader<Mod>, ScaleWriter<Mod> {
      public override fun read(reader: ScaleCodecReader): Mod =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Mod(jp.co.soramitsu.schema.generated.datamodel.expression.Mod.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Mod): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.Mod.write(writer, instance.`mod`)
      }
    }
  }

  /**
   * 'RaiseTo' variant
   */
  public class RaiseTo(
    private val raiseTo: jp.co.soramitsu.schema.generated.datamodel.expression.RaiseTo
  ) : Expression() {
    public override fun discriminant(): Int = 5

    public companion object CODEC : ScaleReader<RaiseTo>, ScaleWriter<RaiseTo> {
      public override fun read(reader: ScaleCodecReader): RaiseTo =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.RaiseTo(jp.co.soramitsu.schema.generated.datamodel.expression.RaiseTo.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: RaiseTo): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.RaiseTo.write(writer,
            instance.`raiseTo`)
      }
    }
  }

  /**
   * 'Greater' variant
   */
  public class Greater(
    private val greater: jp.co.soramitsu.schema.generated.datamodel.expression.Greater
  ) : Expression() {
    public override fun discriminant(): Int = 6

    public companion object CODEC : ScaleReader<Greater>, ScaleWriter<Greater> {
      public override fun read(reader: ScaleCodecReader): Greater =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Greater(jp.co.soramitsu.schema.generated.datamodel.expression.Greater.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Greater): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.Greater.write(writer,
            instance.`greater`)
      }
    }
  }

  /**
   * 'Less' variant
   */
  public class Less(
    private val less: jp.co.soramitsu.schema.generated.datamodel.expression.Less
  ) : Expression() {
    public override fun discriminant(): Int = 7

    public companion object CODEC : ScaleReader<Less>, ScaleWriter<Less> {
      public override fun read(reader: ScaleCodecReader): Less =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Less(jp.co.soramitsu.schema.generated.datamodel.expression.Less.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Less): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.Less.write(writer, instance.`less`)
      }
    }
  }

  /**
   * 'Equal' variant
   */
  public class Equal(
    private val equal: jp.co.soramitsu.schema.generated.datamodel.expression.Equal
  ) : Expression() {
    public override fun discriminant(): Int = 8

    public companion object CODEC : ScaleReader<Equal>, ScaleWriter<Equal> {
      public override fun read(reader: ScaleCodecReader): Equal =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Equal(jp.co.soramitsu.schema.generated.datamodel.expression.Equal.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Equal): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.Equal.write(writer, instance.`equal`)
      }
    }
  }

  /**
   * 'Not' variant
   */
  public class Not(
    private val not: jp.co.soramitsu.schema.generated.datamodel.expression.Not
  ) : Expression() {
    public override fun discriminant(): Int = 9

    public companion object CODEC : ScaleReader<Not>, ScaleWriter<Not> {
      public override fun read(reader: ScaleCodecReader): Not =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Not(jp.co.soramitsu.schema.generated.datamodel.expression.Not.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Not): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.Not.write(writer, instance.`not`)
      }
    }
  }

  /**
   * 'And' variant
   */
  public class And(
    private val and: jp.co.soramitsu.schema.generated.datamodel.expression.And
  ) : Expression() {
    public override fun discriminant(): Int = 10

    public companion object CODEC : ScaleReader<And>, ScaleWriter<And> {
      public override fun read(reader: ScaleCodecReader): And =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.And(jp.co.soramitsu.schema.generated.datamodel.expression.And.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: And): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.And.write(writer, instance.`and`)
      }
    }
  }

  /**
   * 'Or' variant
   */
  public class Or(
    private val or: jp.co.soramitsu.schema.generated.datamodel.expression.Or
  ) : Expression() {
    public override fun discriminant(): Int = 11

    public companion object CODEC : ScaleReader<Or>, ScaleWriter<Or> {
      public override fun read(reader: ScaleCodecReader): Or =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Or(jp.co.soramitsu.schema.generated.datamodel.expression.Or.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Or): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.Or.write(writer, instance.`or`)
      }
    }
  }

  /**
   * 'If' variant
   */
  public class If(
    private val `if`: jp.co.soramitsu.schema.generated.datamodel.expression.If
  ) : Expression() {
    public override fun discriminant(): Int = 12

    public companion object CODEC : ScaleReader<If>, ScaleWriter<If> {
      public override fun read(reader: ScaleCodecReader): If =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.If(jp.co.soramitsu.schema.generated.datamodel.expression.If.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: If): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.If.write(writer, instance.`if`)
      }
    }
  }

  /**
   * 'Raw' variant
   */
  public class Raw(
    private val raw: Value
  ) : Expression() {
    public override fun discriminant(): Int = 13

    public companion object CODEC : ScaleReader<Raw>, ScaleWriter<Raw> {
      public override fun read(reader: ScaleCodecReader): Raw =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Raw(jp.co.soramitsu.schema.generated.datamodel.Value.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Raw): Unit {
        jp.co.soramitsu.schema.generated.datamodel.Value.write(writer, instance.`raw`)
      }
    }
  }

  /**
   * 'Query' variant
   */
  public class Query(
    private val query: QueryBox
  ) : Expression() {
    public override fun discriminant(): Int = 14

    public companion object CODEC : ScaleReader<Query>, ScaleWriter<Query> {
      public override fun read(reader: ScaleCodecReader): Query =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Query(jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Query): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.write(writer, instance.`query`)
      }
    }
  }

  /**
   * 'Contains' variant
   */
  public class Contains(
    private val contains: jp.co.soramitsu.schema.generated.datamodel.expression.Contains
  ) : Expression() {
    public override fun discriminant(): Int = 15

    public companion object CODEC : ScaleReader<Contains>, ScaleWriter<Contains> {
      public override fun read(reader: ScaleCodecReader): Contains =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Contains(jp.co.soramitsu.schema.generated.datamodel.expression.Contains.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Contains): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.Contains.write(writer,
            instance.`contains`)
      }
    }
  }

  /**
   * 'ContainsAll' variant
   */
  public class ContainsAll(
    private val containsAll: jp.co.soramitsu.schema.generated.datamodel.expression.ContainsAll
  ) : Expression() {
    public override fun discriminant(): Int = 16

    public companion object CODEC : ScaleReader<ContainsAll>, ScaleWriter<ContainsAll> {
      public override fun read(reader: ScaleCodecReader): ContainsAll =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.ContainsAll(jp.co.soramitsu.schema.generated.datamodel.expression.ContainsAll.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: ContainsAll): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.ContainsAll.write(writer,
            instance.`containsAll`)
      }
    }
  }

  /**
   * 'ContainsAny' variant
   */
  public class ContainsAny(
    private val containsAny: jp.co.soramitsu.schema.generated.datamodel.expression.ContainsAny
  ) : Expression() {
    public override fun discriminant(): Int = 17

    public companion object CODEC : ScaleReader<ContainsAny>, ScaleWriter<ContainsAny> {
      public override fun read(reader: ScaleCodecReader): ContainsAny =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.ContainsAny(jp.co.soramitsu.schema.generated.datamodel.expression.ContainsAny.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: ContainsAny): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.ContainsAny.write(writer,
            instance.`containsAny`)
      }
    }
  }

  /**
   * 'Where' variant
   */
  public class Where(
    private val `where`: jp.co.soramitsu.schema.generated.datamodel.expression.Where
  ) : Expression() {
    public override fun discriminant(): Int = 18

    public companion object CODEC : ScaleReader<Where>, ScaleWriter<Where> {
      public override fun read(reader: ScaleCodecReader): Where =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.Where(jp.co.soramitsu.schema.generated.datamodel.expression.Where.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: Where): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.Where.write(writer, instance.`where`)
      }
    }
  }

  /**
   * 'ContextValue' variant
   */
  public class ContextValue(
    private val contextValue: jp.co.soramitsu.schema.generated.datamodel.expression.ContextValue
  ) : Expression() {
    public override fun discriminant(): Int = 19

    public companion object CODEC : ScaleReader<ContextValue>, ScaleWriter<ContextValue> {
      public override fun read(reader: ScaleCodecReader): ContextValue =
          jp.co.soramitsu.schema.generated.datamodel.expression.Expression.ContextValue(jp.co.soramitsu.schema.generated.datamodel.expression.ContextValue.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: ContextValue): Unit {
        jp.co.soramitsu.schema.generated.datamodel.expression.ContextValue.write(writer,
            instance.`contextValue`)
      }
    }
  }

  public companion object CODEC : ScaleReader<Expression>, ScaleWriter<Expression> {
    public override fun read(reader: ScaleCodecReader): Expression = when(reader.readUByte()) {
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
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
    }

    public override fun write(writer: ScaleCodecWriter, instance: Expression): Unit {
      writer.directWrite(instance.discriminant())
      when(instance.discriminant()) {
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
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
      }
    }
  }
}
