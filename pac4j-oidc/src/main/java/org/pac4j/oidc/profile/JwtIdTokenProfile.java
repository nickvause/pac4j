package org.pac4j.oidc.profile;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Profile of the JWT ID token.
 *
 * @author Jerome Leleu
 * @since 1.9.2
 */
public interface JwtIdTokenProfile {

    default String getSubject() {
        return getId();
    }

    default String getIssuer() {
        return (String) getAttribute(JwtIdTokenClaims.ISSUER);
    }

    @SuppressWarnings("unchecked")
    default List<String> getAudience() {
        final Object audience = getAttribute(JwtIdTokenClaims.AUDIENCE);
        if (audience instanceof String) {
            return Collections.singletonList((String) audience);
        } else {
            return (List<String>) audience;
        }
    }

    default Date getExpirationDate() {
        return (Date) getAttribute(JwtIdTokenClaims.EXPIRATION_TIME);
    }

    default Date getIssuedAt() {
        return (Date) getAttribute(JwtIdTokenClaims.ISSUED_AT);
    }

    default Date getAuthTime() {
        return (Date) getAttribute(JwtIdTokenClaims.AUTH_TIME);
    }

    default String getNonce() {
        return (String) getAttribute(JwtIdTokenClaims.NONCE);
    }

    default String getAcr() {
        return (String) getAttribute(JwtIdTokenClaims.ACR);
    }

    default Object getAmr() {
        return getAttribute(JwtIdTokenClaims.AMR);
    }

    default String getAzp() {
        return (String) getAttribute(JwtIdTokenClaims.AZP);
    }

    String getId();

    Object getAttribute(String name);
}