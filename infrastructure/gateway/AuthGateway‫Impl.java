package api.shared.infrastructure.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import api.shared.infrastructure.config.EnvConfig;
import api.shared.infrastructure.gateway.iam.DataResponse;
import api.shared.infrastructure.gateway.iam.StatusResponse;

@Component
public class AuthGateway‫Impl implements AuthGateway {

    private final String HEADER = "Authorization";

    @Override
    public List<String> getScopes(HttpServletRequest request) throws Exception {

        String iamEndpoint = EnvConfig.getProperty("spring.server.iam.url")
            + EnvConfig.getProperty("spring.server.iam.scopes")
            + "?userId=" + request.getUserPrincipal().getName()
            + "&clientId=" + EnvConfig.getProperty("spring.client.id")
            + "&isRequest=" + true;

        // Set the headers you need send
        HttpHeaders headers = new HttpHeaders();
        headers.set(HEADER, request.getHeader(HEADER));

        // Create a new HttpEntity
        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Execute the method writing your HttpEntity to the request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<StatusResponse> response = restTemplate
            .exchange(iamEndpoint, HttpMethod.GET, entity, StatusResponse.class);

        if (response.getBody() == null) {
            
            return new ArrayList<>();
        }
        
        return response.getBody().getData().getScopes();
    }
}
