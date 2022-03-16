package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.AdminIroha2Client
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
        val resources = initIfRequested(invocationContext)
        try {
            // invoke actual test method
            super.interceptTestMethod(invocation, invocationContext, extensionContext)
        } finally {
            // stop container and client if they were created
            resources.forEach { it.close() }
        }
    }

    private fun initIfRequested(invocationContext: ReflectiveInvocationContext<Method>): List<AutoCloseable> {
        return invocationContext
            .executable
            .declaredAnnotations.filterIsInstance<WithIroha>()
            .firstOrNull()
            ?.let {
                val utilizedResources = mutableListOf<AutoCloseable>()
                // start container
                val container = IrohaContainer { genesis = it.genesis.createInstance() }
                container.start()
                utilizedResources.add(container)

                val testClassInstance = invocationContext.target.get()
                val declaredProperties = testClassInstance::class.declaredMemberProperties

                // inject `Iroha2Client` if it is declared in test class
                setPropertyValue(declaredProperties, testClassInstance) {
                    Iroha2Client(container.getApiUrl(), log = true)
                        .also { utilizedResources.add(it) }
                }

                // inject `AdminIroha2Client` if it is declared in test class
                setPropertyValue(declaredProperties, testClassInstance) {
                    AdminIroha2Client(container.getApiUrl(), container.getTelemetryUrl(), log = true)
                        .also { utilizedResources.add(it) }
                }

                utilizedResources
            } ?: emptyList()
    }

    private inline fun <reified V : Any> setPropertyValue(
        declaredProperties: Collection<KProperty1<out Any, *>>,
        testClassInstance: Any,
        valueToSet: () -> V
    ) {
        (declaredProperties.filter { it.returnType.classifier == V::class })
            .filterIsInstance<KMutableProperty1<out Any, V>>()
            .also { check(it.size <= 1) { "Found more than one property with type `${V::class.qualifiedName}` in test class `${testClassInstance::class::qualifiedName}`" } }
            .firstOrNull()
            ?.setter
            ?.call(testClassInstance, valueToSet())
    }
}
