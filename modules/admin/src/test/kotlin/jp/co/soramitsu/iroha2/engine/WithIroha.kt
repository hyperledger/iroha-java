package jp.co.soramitsu.iroha2.engine

import org.junit.jupiter.api.Test
import java.lang.annotation.Inherited

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Test
@Inherited
annotation class WithIroha
