package jp.co.soramitsu.iroha2.codegen.generator

import com.squareup.kotlinpoet.ClassName

class ScaleConstants {
    companion object {
        val SCALE_READER = ClassName("io.emeraldpay.polkaj.scale", "ScaleReader")
        val SCALE_CODEC_READER = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecReader")
        val SCALE_WRITER = ClassName("io.emeraldpay.polkaj.scale", "ScaleWriter")
        val SCALE_CODEC_WRITER = ClassName("io.emeraldpay.polkaj.scale", "ScaleCodecWriter")
    }

}
