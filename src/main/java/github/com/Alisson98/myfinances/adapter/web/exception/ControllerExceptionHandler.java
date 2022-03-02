package github.com.Alisson98.myfinances.adapter.web.exception;

import github.com.Alisson98.myfinances.adapter.web.dto.StandardErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<StandardErrorDto> resourceNotFoundException(EmailAlreadyRegisteredException e) {
        StandardErrorDto standardError = new StandardErrorDto(
                HttpStatus.BAD_REQUEST.value()
                , e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
}
