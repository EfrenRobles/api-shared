package api.shared.domain.response;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class OnResponse {

    public static ResponseEntity<PaginationResponse> onSuccessPagination(PaginationResponse result, HttpStatus status) {
        
        return new ResponseEntity<>(result, status);
    }

    public static ResponseEntity<Object> onSuccess(Object result, HttpStatus status) {

        return response(result, status, ResponseEnum.SUCCESS, "data");
    }

    public static ResponseEntity<Object> onError(Object result, HttpStatus status) {

        return response(result, status, ResponseEnum.ERROR, "error");
    }

    private static ResponseEntity<Object> response(
        Object result,
        HttpStatus status,
        ResponseEnum tagStatus,
        String tagType
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", tagStatus);
        response.put(tagType, result);

        return new ResponseEntity<>(response, status);
    }
}
