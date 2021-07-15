//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.expression

import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.query.QueryBox

/**
 * Expression
 *
 * Generated from 'jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression' enum
 */
public sealed class Expression {
  /**
   * 'Add' variant
   */
  public class Add(
    private val add: jp.co.soramitsu.iroha2.generated.datamodel.expression.Add
  ) : Expression()

  /**
   * 'Subtract' variant
   */
  public class Subtract(
    private val subtract: jp.co.soramitsu.iroha2.generated.datamodel.expression.Subtract
  ) : Expression()

  /**
   * 'Multiply' variant
   */
  public class Multiply(
    private val multiply: jp.co.soramitsu.iroha2.generated.datamodel.expression.Multiply
  ) : Expression()

  /**
   * 'Divide' variant
   */
  public class Divide(
    private val divide: jp.co.soramitsu.iroha2.generated.datamodel.expression.Divide
  ) : Expression()

  /**
   * 'Mod' variant
   */
  public class Mod(
    private val mod: jp.co.soramitsu.iroha2.generated.datamodel.expression.Mod
  ) : Expression()

  /**
   * 'RaiseTo' variant
   */
  public class RaiseTo(
    private val raiseTo: jp.co.soramitsu.iroha2.generated.datamodel.expression.RaiseTo
  ) : Expression()

  /**
   * 'Greater' variant
   */
  public class Greater(
    private val greater: jp.co.soramitsu.iroha2.generated.datamodel.expression.Greater
  ) : Expression()

  /**
   * 'Less' variant
   */
  public class Less(
    private val less: jp.co.soramitsu.iroha2.generated.datamodel.expression.Less
  ) : Expression()

  /**
   * 'Equal' variant
   */
  public class Equal(
    private val equal: jp.co.soramitsu.iroha2.generated.datamodel.expression.Equal
  ) : Expression()

  /**
   * 'Not' variant
   */
  public class Not(
    private val not: jp.co.soramitsu.iroha2.generated.datamodel.expression.Not
  ) : Expression()

  /**
   * 'And' variant
   */
  public class And(
    private val and: jp.co.soramitsu.iroha2.generated.datamodel.expression.And
  ) : Expression()

  /**
   * 'Or' variant
   */
  public class Or(
    private val or: jp.co.soramitsu.iroha2.generated.datamodel.expression.Or
  ) : Expression()

  /**
   * 'If' variant
   */
  public class If(
    private val `if`: jp.co.soramitsu.iroha2.generated.datamodel.expression.If
  ) : Expression()

  /**
   * 'Raw' variant
   */
  public class Raw(
    private val raw: Value
  ) : Expression()

  /**
   * 'Query' variant
   */
  public class Query(
    private val query: QueryBox
  ) : Expression()

  /**
   * 'Contains' variant
   */
  public class Contains(
    private val contains: jp.co.soramitsu.iroha2.generated.datamodel.expression.Contains
  ) : Expression()

  /**
   * 'ContainsAll' variant
   */
  public class ContainsAll(
    private val containsAll: jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAll
  ) : Expression()

  /**
   * 'ContainsAny' variant
   */
  public class ContainsAny(
    private val containsAny: jp.co.soramitsu.iroha2.generated.datamodel.expression.ContainsAny
  ) : Expression()

  /**
   * 'Where' variant
   */
  public class Where(
    private val `where`: jp.co.soramitsu.iroha2.generated.datamodel.expression.Where
  ) : Expression()

  /**
   * 'ContextValue' variant
   */
  public class ContextValue(
    private val contextValue: jp.co.soramitsu.iroha2.generated.datamodel.expression.ContextValue
  ) : Expression()
}
