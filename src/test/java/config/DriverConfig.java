package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/driver.properties"})
public interface DriverConfig extends Config {
        @Key("remote.user")
        String remoteWebUser();

        @Key("remote.password")
        String remoteWebPassword();
}
