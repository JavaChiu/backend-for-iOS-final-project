package mobi.uchicago.finalproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FileUploadException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public FileUploadException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
}