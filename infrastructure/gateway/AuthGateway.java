package api.shared.infrastructure.gateway;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public interface AuthGateway {

    public HttpServletRequest getRequest();

    public UUID getUserId();

    public List<String> getScopes(/* HttpServletRequest request */) throws Exception;
}
