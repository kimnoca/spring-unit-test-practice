package global.utils;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private int httpStatus;
    private String message;
}
