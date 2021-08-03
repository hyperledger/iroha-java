package jp.co.soramitsu.iroha2.listener

import io.ktor.http.cio.websocket.DefaultWebSocketSession
import io.ktor.http.cio.websocket.Frame

class WebsocketListener(private val wsSession: DefaultWebSocketSession) {

    suspend fun foo() {
        when (val frame = wsSession.incoming.receive()) {
            is Frame.Text -> onText(frame)
            is Frame.Binary -> onBinary(frame)
        }
    }

    fun onText(frame: Frame.Text) { }

    fun onBinary(frame: Frame.Binary) { }
}
