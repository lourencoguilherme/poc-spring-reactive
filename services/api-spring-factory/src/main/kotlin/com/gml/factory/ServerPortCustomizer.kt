package com.gml.factory

import org.springframework.boot.web.server.ConfigurableWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.stereotype.Component

@Component
class ServerPortCustomizer : WebServerFactoryCustomizer<ConfigurableWebServerFactory?> {

    override fun customize(factory: ConfigurableWebServerFactory?) {
        factory?.setPort(8081)
    }
}
