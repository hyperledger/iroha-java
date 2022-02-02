package jp.co.soramitsu.iroha2.engine

import java.lang.annotation.Inherited
import org.junit.jupiter.api.Test

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Test
@Inherited
annotation class WithIroha
