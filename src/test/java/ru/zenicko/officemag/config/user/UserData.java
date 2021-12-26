package ru.zenicko.officemag.config.user;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;


public class UserData {
    private static UserConfig userData;

    public static UserConfig UserFactory(String fileName) {
        ConfigFactory.setProperty("userConfigFile", fileName);
        userData = ConfigFactory.create(UserConfig.class, System.getProperties());

        return userData;
    }
}
