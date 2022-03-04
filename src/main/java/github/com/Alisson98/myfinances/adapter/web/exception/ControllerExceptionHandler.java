package github.com.Alisson98.myfinances.adapter.web.exception;

import github.com.Alisson98.myfinances.adapter.web.dto.StandardErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<StandardErrorDto> emailAlreadyRegisteredException(EmailAlreadyRegisteredException e) {
        StandardErrorDto standardError = new StandardErrorDto(
                HttpStatus.BAD_REQUEST.value()
                , e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardErrorDto> resourceNotFoundException(ResourceNotFoundException e) {
        StandardErrorDto standardError = new StandardErrorDto(
                HttpStatus.NOT_FOUND.value()
                , e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<StandardErrorDto> invalidPasswordException(InvalidPasswordException e) {
        StandardErrorDto standardError = new StandardErrorDto(
                HttpStatus.BAD_REQUEST.value()
                , e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
}
