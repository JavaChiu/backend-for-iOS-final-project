package mobi.uchicago.finalproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FileEmptyException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public FileEmptyException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
}