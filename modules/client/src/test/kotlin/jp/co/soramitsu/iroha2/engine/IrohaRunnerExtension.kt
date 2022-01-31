package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.Iroha2Client
import jp.co.soramitsu.iroha2.testcontainers.IrohaContainer
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.InvocationInterceptor
import org.junit.jupiter.api.extension.ReflectiveInvocationContext
import java.lang.reflect.Method
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties

/**
 * Starts Iroha2 docker containers
 */
class IrohaRunnerExtension : InvocationInterceptor {

    override fun interceptTestMethod(
        invocation: InvocationInterceptor.Invocation<Void>,
        invocationContext: ReflectiveInvocationContext<Method>,
        extensionContext: ExtensionContext
    ) {
        // init container and client if annotation was passed on test method
        val (iroha2Client, container) = initIfRequested(invocationContext)
        try {
            // invoke actual test method
            super.interceptTestMethod(invocation, invocationContext, extensionContext)
        } finally {
            // stop container and client if they were created
            iroha2Client?.close()
            container?.stop()
        }
    }

    private fun initIfRequested(invocationContext: ReflectiveInvocationContext<Method>): Pair<Iroha2Client?, IrohaContainer?> {
        return invocationContext
            .executable
            .declaredAnnotations.filterIsInstance<WithIroha>()
            .firstOrNull()
            ?.let {
                val container = IrohaContainer { genesis = it.genesis.createInstance() }
                container.start()
                val irohaClient = Iroha2Client(container.getApiUrl(), container.getTelemetryUrl(), log = true)
                val testClassInstance = invocationContext.target.get()
                val declaredProperties = testClassInstance::class.declaredMemberProperties
                setPropertyValue(declaredProperties, testClassInstance, container)
                setPropertyValue(declaredProperties, testClassInstance, irohaClient)
                (irohaClient to container)
            } ?: (null to null)
    }

    private inline fun <reified V : Any> setPropertyValue(
        declaredProperties: Collection<KProperty1<out Any, *>>,
        testClassInstance: Any,
        valueToSet: V
    ) {
        (declaredProperties.filter { it.returnType.classifier == V::class })
            .filterIsInstance<KMutableProperty1<out Any, V>>()
            .also { check(it.size <= 1) { "Found more than one property with type `${V::class.qualifiedName}` in test class `${testClassInstance::class::qualifiedName}`" } }
            .firstOrNull()
            ?.setter
            ?.call(testClassInstance, valueToSet)
    }
}
