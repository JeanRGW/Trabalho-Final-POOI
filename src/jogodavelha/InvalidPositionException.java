package jogodavelha;

/**
 * Exceção lançada quando uma jogada é realizada em uma posição inválida é do
 * tabuleiro.
 * 
 * @author GuilhermeKT
 */
public class InvalidPositionException extends Exception {
    public InvalidPositionException(String msg) {
        super(msg);
    }
}
