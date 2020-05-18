package com.viettel.imdb.type;

import com.viettel.imdb.core.Hashing;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static java.lang.System.getProperty;

public class ClientConfig {
    // todo should be singleton
    private static final String HOSTS_PROP = "hosts";
    private static final String HOSTS_DEFAULT = "";
    private static final String NEED_AUTH_PROP = "auth";
    private static final boolean NEED_AUTH_DEFAULT = true;
    private static final String USERNAME_PROP = "username";
    private static final String USERNAME_DEFAULT = "admin";
    private static final String PASSWORD_PROP = "password";
    private static final String PASSWORD_DEFAULT = "admin";
    private String hostsStr;
    private List<String> hosts;
    private boolean needAuth;
    private String username;
    private String password;

    // todo configurable variables
    private Hashing hashFunction;

    private static ClientConfig instance = new ClientConfig();

    public static ClientConfig getInstance() {
        if(instance == null)
            instance = new ClientConfig();
        return instance;
    }

    public static ClientConfig just() {
        if(instance == null)
            instance = new ClientConfig();
        return instance;
    }

    private ClientConfig() {
    }

    public void loadConfig(String configFilePath) {
        // load properties from file
        final Properties properties = new Properties();
        try (InputStream in = ClientConfig.class.getClassLoader().getResourceAsStream(configFilePath)) {
            properties.load(in);
        } catch (final Exception ignored) {
        }

        try (FileInputStream in = new FileInputStream(configFilePath)) {
            properties.load(in);
        } catch (final Exception ignored) {
        }

        try (InputStream in = new URL(configFilePath).openStream()) {
            properties.load(in);
        } catch (final Exception ignored) {
        }

        // system properties
        Properties system = System.getProperties();
        properties.stringPropertyNames().forEach(s -> {
            if (!system.containsKey(s) || system.getProperty(s) == null || system.getProperty(s).isEmpty())
                system.put(s, properties.getProperty(s));
        });

        // set value here
        hostsStr = getProperty(HOSTS_PROP, HOSTS_DEFAULT);
        try {
            hosts = Arrays.asList(hostsStr.split(","));
        } catch (Exception ex) {
            ex.printStackTrace();
            hosts = new ArrayList<>();
        }
        needAuth = getProperty(NEED_AUTH_PROP).equals("true") || NEED_AUTH_DEFAULT;
        username = getProperty(USERNAME_PROP, USERNAME_DEFAULT);
        password = getProperty(PASSWORD_PROP, PASSWORD_DEFAULT);

        // todo here
        hashFunction = ConsistentHashing.getInstance();

    }

    public String getHostsStr() {
        return hostsStr;
    }

    public void setHostsStr(String hostsStr) {
        this.hostsStr = hostsStr;
    }

    public boolean isNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(boolean needAuth) {
        this.needAuth = needAuth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Hashing getHashFunction() {
        return hashFunction;
    }

    public void setHashFunction(Hashing hashFunction) {
        this.hashFunction = hashFunction;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }
}
