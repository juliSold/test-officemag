package ru.zenicko.officemag.config.app;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/app/app.properties"
})
public interface AppConfig extends Config {

    @DefaultValue("https://www.officemag.ru")
    String webUrl();

    String apiUrl();
    String userLogin();
    String userPassword();

    @DefaultValue("existuser")
    String typeOfUser();
}
