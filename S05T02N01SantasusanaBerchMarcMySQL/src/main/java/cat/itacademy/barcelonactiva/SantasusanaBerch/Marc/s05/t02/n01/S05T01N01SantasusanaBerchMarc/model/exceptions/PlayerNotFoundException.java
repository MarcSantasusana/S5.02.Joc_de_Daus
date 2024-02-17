package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.exceptions;

public class PlayerNotFoundException extends RuntimeException{

    public PlayerNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public PlayerNotFoundException(String message)
    {
        super(message);
    }
}
