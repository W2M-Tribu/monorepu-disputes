package es.w2m.finance.disputes.libsnowflake.client.interceptor;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@RequiredArgsConstructor
public class OAuth2FeignInterceptor implements RequestInterceptor {
    private final OAuth2AuthorizedClientManager manager;
    private final String registrationId;
    private final String principal;

    @Override
    public void apply(feign.RequestTemplate template) {
        var req = OAuth2AuthorizeRequest.withClientRegistrationId(registrationId)
                .principal(principal).build();
        OAuth2AuthorizedClient client = manager.authorize(req);
        if (client == null || client.getAccessToken() == null) {
            throw new IllegalStateException("Could not obtain access token (client_credentials).");
        }
        template.header("Authorization", "Bearer " + client.getAccessToken().getTokenValue());
    }
}
