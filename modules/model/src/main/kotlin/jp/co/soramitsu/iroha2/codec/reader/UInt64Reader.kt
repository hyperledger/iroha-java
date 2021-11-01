package jp.co.soramitsu.iroha2.codec.reader

import java.math.BigInteger
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleReader

class UInt64Reader : ScaleReader<BigInteger?> {
    override fun read(rdr: ScaleCodecReader): BigInteger {
        var result = BigInteger.ZERO
        result = result.add(BigInteger.valueOf(rdr.readUByte().toLong()))
        result = result.add(BigInteger.valueOf(rdr.readUByte().toLong()).shiftLeft(8))
        result = result.add(BigInteger.valueOf(rdr.readUByte().toLong()).shiftLeft(16))
        result = result.add(BigInteger.valueOf(rdr.readUByte().toLong()).shiftLeft(24))
        result = result.add(BigInteger.valueOf(rdr.readUByte().toLong()).shiftLeft(32))
        result = result.add(BigInteger.valueOf(rdr.readUByte().toLong()).shiftLeft(40))
        result = result.add(BigInteger.valueOf(rdr.readUByte().toLong()).shiftLeft(48))
        result = result.add(BigInteger.valueOf(rdr.readUByte().toLong()).shiftLeft(56))
        return result
    }
}