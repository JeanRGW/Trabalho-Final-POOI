package jogodavelha;

public class Jogada {
    private Boolean player;
    private int x;
    private int y;

    public Jogada(Boolean player, int x, int y){
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public Jogada inverse(){
        return new Jogada(!player, x, y);
    }

    public Boolean getPlayer(){
        return player;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
