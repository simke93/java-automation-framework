package configs;


import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:default.properties"
})

public interface TestConfig extends org.aeonbits.owner.Config {
    @Key("apiBaseUrl")
    String getApiBaseUrl();

    @Key("uiBaseUrl")
    String getUiBaseUrl();

    @Key("login")
    String getLogin();

    @Key("password")
    String getPassword();


}
