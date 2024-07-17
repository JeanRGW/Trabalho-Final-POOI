package jogodavelha;

public class Pair<T1, T2> {
    public T1 x;
    public T2 y;

    public Pair(T1 x, T2 y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (!x.equals(pair.x)) return false;
        return y.equals(pair.y);
    }
}
