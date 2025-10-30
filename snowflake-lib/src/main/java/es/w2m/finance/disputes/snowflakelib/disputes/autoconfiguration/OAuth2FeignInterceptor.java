package es.w2m.finance.disputes.snowflakelib.disputes.autoconfiguration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OAuth2FeignInterceptor implements RequestInterceptor {

    private final OAuth2AuthorizedClientManager manager;
    private final String clientRegistrationId;
    private final String principal;

    @Override
    public void apply(feign.RequestTemplate template) {
        OAuth2AuthorizeRequest authReq = OAuth2AuthorizeRequest
                .withClientRegistrationId(clientRegistrationId)
                .principal(principal)
                .build();

        OAuth2AuthorizedClient client = manager.authorize(authReq);
        if (client == null || client.getAccessToken() == null) {
            throw new IllegalStateException("Could not obtain access token (client_credentials).");
        }
        template.header("Authorization", "Bearer " + client.getAccessToken().getTokenValue());
    }
}