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
 * GrantBox
 *
 * Generated from 'GrantBox' enum
 */
public sealed class GrantBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'PermissionToken' variant
     */
    public data class PermissionToken(
        public val grantOfPermissionTokenAndAccount: GrantOfPermissionTokenAndAccount,
    ) : GrantBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.GrantBox.PermissionToken>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.GrantBox.PermissionToken> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.GrantBox.PermissionToken = try {
                PermissionToken(
                    GrantOfPermissionTokenAndAccount.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.GrantBox.PermissionToken,
            ): Unit = try {
                GrantOfPermissionTokenAndAccount.write(writer, instance.grantOfPermissionTokenAndAccount)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Role' variant
     */
    public data class Role(
        public val grantOfRoleIdAndAccount: GrantOfRoleIdAndAccount,
    ) : GrantBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.GrantBox.Role>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.GrantBox.Role> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.GrantBox.Role =
                try {
                    Role(
                        GrantOfRoleIdAndAccount.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.GrantBox.Role,
            ): Unit = try {
                GrantOfRoleIdAndAccount.write(writer, instance.grantOfRoleIdAndAccount)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'RolePermissionToken' variant
     */
    public data class RolePermissionToken(
        public val grantOfPermissionTokenAndRole: GrantOfPermissionTokenAndRole,
    ) : GrantBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.GrantBox.RolePermissionToken>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.GrantBox.RolePermissionToken> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.GrantBox.RolePermissionToken = try {
                RolePermissionToken(
                    GrantOfPermissionTokenAndRole.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.GrantBox.RolePermissionToken,
            ): Unit = try {
                GrantOfPermissionTokenAndRole.write(writer, instance.grantOfPermissionTokenAndRole)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<GrantBox>, ScaleWriter<GrantBox> {
        override fun read(reader: ScaleCodecReader): GrantBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> PermissionToken.read(reader)
            1 -> Role.read(reader)
            2 -> RolePermissionToken.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: GrantBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> PermissionToken.write(writer, instance as PermissionToken)
                1 -> Role.write(writer, instance as Role)
                2 -> RolePermissionToken.write(writer, instance as RolePermissionToken)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
