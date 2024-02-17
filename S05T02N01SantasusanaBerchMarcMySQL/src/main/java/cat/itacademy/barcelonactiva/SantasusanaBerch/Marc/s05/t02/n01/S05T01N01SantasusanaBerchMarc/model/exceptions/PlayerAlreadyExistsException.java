package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.exceptions;

public class PlayerAlreadyExistsException extends RuntimeException{

    public PlayerAlreadyExistsException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public PlayerAlreadyExistsException(String message)
    {
        super(message);

    }
}
