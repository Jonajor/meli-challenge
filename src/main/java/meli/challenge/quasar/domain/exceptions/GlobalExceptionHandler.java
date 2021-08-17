package meli.challenge.quasar.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalServerErrorException(
            InternalServerErrorException internalServerErrorException){
        return new ResponseEntity("System failure, please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> handleUnprocessableEntityException(
            UnprocessableEntityException unprocessableEntityException){
        return new ResponseEntity("it was not possible to process the request", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(SatelliteNotFoundException.class)
    public ResponseEntity<Object> handleExpenseNotFoundExceptionException(
            SatelliteNotFoundException notFoundException){
        return new ResponseEntity("Satellite does not exist in the system", HttpStatus.NOT_FOUND);
    }
}
