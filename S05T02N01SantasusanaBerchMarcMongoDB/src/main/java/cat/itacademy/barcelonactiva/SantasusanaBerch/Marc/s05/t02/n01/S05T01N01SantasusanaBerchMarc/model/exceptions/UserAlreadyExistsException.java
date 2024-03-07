package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public UserAlreadyExistsException(String message)
    {
        super(message);
    }
}
