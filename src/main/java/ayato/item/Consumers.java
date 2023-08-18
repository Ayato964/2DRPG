package ayato.item;

public interface Consumers<A, B> {
    public void accept(A a, B b);
}
