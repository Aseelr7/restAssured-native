package auth;

import config.ConfigLoader;

import java.time.Instant;

public class TokenManager {

    private static String supervisorToken;
    private static String generalUserToken;

    private static Instant supervisorTokenGeneratedAt;
    private static Instant generalUserTokenGeneratedAt;

    private static final long TOKEN_EXPIRATION_MINUTES = ConfigLoader.getIntProperty("token.expiration.min");

    public static String getSupervisorToken(){
        if (supervisorToken == null || isTokenExpired(supervisorTokenGeneratedAt)){
            supervisorToken = TokenGenerator.getSupervisorToken();
            supervisorTokenGeneratedAt = Instant.now();
        }
        return supervisorToken;
    }

    public static String getGeneralUserToken(){
        if (supervisorToken == null || isTokenExpired(generalUserTokenGeneratedAt)){
            generalUserToken = TokenGenerator.getGeneralUserToken();
            generalUserTokenGeneratedAt = Instant.now();
        }
        return supervisorToken;
    }


    private static boolean isTokenExpired(Instant generatedAt){
        if (generatedAt == null) return true;
      Instant expirationTime =  generatedAt.plusSeconds(TOKEN_EXPIRATION_MINUTES * 60);
      return Instant.now().isAfter(expirationTime);
    }

    public static void tearDown(){
        supervisorToken = null;
        generalUserToken = null;
        supervisorTokenGeneratedAt = null;
        generalUserTokenGeneratedAt = null;
    }
}
