package config;

public enum Environments {
    DEV("https://bookstore.demoqa.com/"),
    QA("https://bookstore.demoqa.com/"),
    PROD("https://bookstore.demoqa.com/");

    private final String baseUrl;

    Environments(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl(){
        return this.baseUrl;
    }

    public static Environments getCurrentEnvironment(){
      String env =  ConfigLoader.getProperty("environment").toUpperCase();
      return Environments.valueOf(env);
    }
}
