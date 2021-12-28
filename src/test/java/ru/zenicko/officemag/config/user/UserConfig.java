package ru.zenicko.officemag.config.user;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/users/${userConfigFile}.properties"})
public interface UserConfig extends Config {
    String firstName();
    String lastName();
    String codePhoneNumber();
    String phoneNumber();
    String email();
    String password();
    boolean budget();
    boolean agreeRules();
    boolean agreeSpam();
}
