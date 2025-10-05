package persistenza;

import java.util.List;
import java.util.NoSuchElementException;

public interface IPersistenza<K, T extends Persistibile> {

    public void create(T obj) throws IllegalArgumentException;

    public T read(K chiave) throws NoSuchElementException;

    public T update(K chiave, T new_obj) throws NoSuchElementException;

    public T delete(K chiave) throws NoSuchElementException;

    public List<T> elencoCompleto();

}
