package api.carreras.shared.domain.response;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class OnResponse {

    @SuppressWarnings("unchecked")
    public static ResponseEntity<Object> onSuccessPagination(Object result, HttpStatus status) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> temp = objectMapper.convertValue(result, LinkedHashMap.class);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", ResponseEnum.SUCCESS);
        response.putAll(temp);

        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Object> onSuccess(Object result, HttpStatus status) {

        return response(result, status, ResponseEnum.SUCCESS, "data");
    }

    public static ResponseEntity<Object> onError(Object result, HttpStatus status) {

        return response(result, status, ResponseEnum.ERROR, "error");
    }

    private static
        ResponseEntity<Object>
        response(Object result, HttpStatus status, ResponseEnum tagStatus, String tagType) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", tagStatus);
        response.put(tagType, result);

        return new ResponseEntity<>(response, status);
    }
}
