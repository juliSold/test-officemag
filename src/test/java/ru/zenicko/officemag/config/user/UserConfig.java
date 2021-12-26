package ru.zenicko.officemag.config.user;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/users/${userConfigFile}.properties"})
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
