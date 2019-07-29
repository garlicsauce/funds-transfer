package spec

import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule

class WireMockTestConfig {

    private static final int PORT = 9995

    static WireMockRule defaultWireMockRule() {
        new WireMockRule(WireMockConfiguration.options().port(PORT))
    }
}
