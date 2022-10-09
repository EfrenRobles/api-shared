package api.shared.infrastructure.gateway;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface AuthGateway {

    public List<String> getScopes(HttpServletRequest request) throws Exception;
}
