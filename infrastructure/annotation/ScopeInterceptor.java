package api.shared.infrastructure.annotation;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import api.shared.domain.exception.ScopeException;
import api.shared.infrastructure.gateway.AuthGateway;

@Aspect
@Component
public class ScopeInterceptor {
    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String SUPER_VIEWABILITY_ALL = "SUPER.VIEWABILITY.ALL";

    @Autowired
    private AuthGateway authGateway;

    @Around("@annotation(api.shared.infrastructure.annotation.Scope)")
    public Object validateScope(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
            .getRequest();

        if (request.getParameter("isRequest") != null) {

            return proceedingJoinPoint.proceed();
        }

        if (can(authGateway.getScopes(request), getScopeValue(proceedingJoinPoint))) {

            return proceedingJoinPoint.proceed();
        }

        throw new ScopeException("Your request requires higher privileges that provided.");

    }

    private boolean can(List<String> scopes, String scopeValue) {

        return !scopes
            .parallelStream()
            .filter(s -> s.equals(scopeValue) || s.equals(SUPER_VIEWABILITY_ALL))
            .findFirst()
            .isEmpty();
    }

    private String getScopeValue(ProceedingJoinPoint pjp) {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        Scope scope = method.getAnnotation(Scope.class);
        return scope.value();
    }

}
