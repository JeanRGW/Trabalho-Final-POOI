package armazenamento;

/**
 * Indica algum erro ao processar o arquivo GameData.
 * 
 * @author JeanRGW
 */
public class DataParsingException extends Exception {
    public DataParsingException(String msg) {
        super(msg);
    }
}
