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

/**
 * SocketAddr
 *
 * Generated from 'SocketAddr' enum
 */
public sealed class SocketAddr : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'Ipv4' variant
     */
    public data class Ipv4(
        public val socketAddrV4: SocketAddrV4
    ) : SocketAddr() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Ipv4>, ScaleWriter<Ipv4> {
            public const val DISCRIMINANT: Int = 0

            public override fun read(reader: ScaleCodecReader): Ipv4 = try {
                Ipv4(
                    SocketAddrV4.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Ipv4) = try {
                SocketAddrV4.write(writer, instance.socketAddrV4)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Ipv6' variant
     */
    public data class Ipv6(
        public val socketAddrV6: SocketAddrV6
    ) : SocketAddr() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Ipv6>, ScaleWriter<Ipv6> {
            public const val DISCRIMINANT: Int = 1

            public override fun read(reader: ScaleCodecReader): Ipv6 = try {
                Ipv6(
                    SocketAddrV6.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Ipv6) = try {
                SocketAddrV6.write(writer, instance.socketAddrV6)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'Host' variant
     */
    public data class Host(
        public val socketAddrHost: SocketAddrHost
    ) : SocketAddr() {
        public override fun discriminant(): Int = DISCRIMINANT

        public companion object : ScaleReader<Host>, ScaleWriter<Host> {
            public const val DISCRIMINANT: Int = 2

            public override fun read(reader: ScaleCodecReader): Host = try {
                Host(
                    SocketAddrHost.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            public override fun write(writer: ScaleCodecWriter, instance: Host) = try {
                SocketAddrHost.write(writer, instance.socketAddrHost)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<SocketAddr>, ScaleWriter<SocketAddr> {
        public override fun read(reader: ScaleCodecReader): SocketAddr = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> Ipv4.read(reader)
            1 -> Ipv6.read(reader)
            2 -> Host.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
        }

        public override fun write(writer: ScaleCodecWriter, instance: SocketAddr) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> Ipv4.write(writer, instance as Ipv4)
                1 -> Ipv6.write(writer, instance as Ipv6)
                2 -> Host.write(writer, instance as Host)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")
            }
        }
    }
}
