package jogodavelha;

public class Jogada {
    private int x;
    private int y;
    private boolean player;

    public Jogada(Boolean player, int x, int y){
        this.x = x;
        this.y = y;
        this.player = player;
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
