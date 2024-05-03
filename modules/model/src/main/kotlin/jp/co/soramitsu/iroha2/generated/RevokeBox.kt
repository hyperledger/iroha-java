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
 * RevokeBox
 *
 * Generated from 'RevokeBox' enum
 */
public sealed class RevokeBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'PermissionToken' variant
     */
    public data class PermissionToken(
        public val revokeOfPermissionTokenAndAccount: RevokeOfPermissionTokenAndAccount,
    ) : RevokeBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.RevokeBox.PermissionToken>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.RevokeBox.PermissionToken> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.RevokeBox.PermissionToken = try {
                PermissionToken(
                    RevokeOfPermissionTokenAndAccount.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.RevokeBox.PermissionToken,
            ): Unit = try {
                RevokeOfPermissionTokenAndAccount.write(writer, instance.revokeOfPermissionTokenAndAccount)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Role' variant
     */
    public data class Role(
        public val revokeOfRoleIdAndAccount: RevokeOfRoleIdAndAccount,
    ) : RevokeBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.RevokeBox.Role>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.RevokeBox.Role> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.RevokeBox.Role =
                try {
                    Role(
                        RevokeOfRoleIdAndAccount.read(reader),
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.RevokeBox.Role,
            ): Unit = try {
                RevokeOfRoleIdAndAccount.write(writer, instance.revokeOfRoleIdAndAccount)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'RolePermissionToken' variant
     */
    public data class RolePermissionToken(
        public val revokeOfPermissionTokenAndRole: RevokeOfPermissionTokenAndRole,
    ) : RevokeBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.RevokeBox.RolePermissionToken>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.RevokeBox.RolePermissionToken> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.RevokeBox.RolePermissionToken = try {
                RolePermissionToken(
                    RevokeOfPermissionTokenAndRole.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.RevokeBox.RolePermissionToken,
            ): Unit = try {
                RevokeOfPermissionTokenAndRole.write(writer, instance.revokeOfPermissionTokenAndRole)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<RevokeBox>, ScaleWriter<RevokeBox> {
        override fun read(reader: ScaleCodecReader): RevokeBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> PermissionToken.read(reader)
            1 -> Role.read(reader)
            2 -> RolePermissionToken.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: RevokeBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> PermissionToken.write(writer, instance as PermissionToken)
                1 -> Role.write(writer, instance as Role)
                2 -> RolePermissionToken.write(writer, instance as RolePermissionToken)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
