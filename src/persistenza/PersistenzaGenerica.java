/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenza;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 * @author LO.ZAKY
 */
public abstract class PersistenzaGenerica<K, T extends Persistibile> implements IPersistenza<K, T> {

    private Map<K, T> elenco;

    public PersistenzaGenerica() {
        elenco = getMappa();
    }

    protected abstract Map<K, T> getMappa();

    @Override
    public void create(T obj) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("Oggetto nullo");
        }

        T objCopia = (T) obj.clone();
        if (objCopia == null) {
            throw new IllegalArgumentException("Copia nulla");
        }

        K chiave = (K) obj.getChiave();
        if (elenco.containsKey(chiave)) {
            throw new IllegalArgumentException("L'oggetto è già stato inserito");
        }
        elenco.put(chiave, objCopia);
    }

    @Override
    public T read(K chiave) throws NoSuchElementException {
        if (chiave == null) {
            throw new NoSuchElementException("Chiave nulla");
        }

        if (!elenco.containsKey(chiave)) {
            throw new NoSuchElementException("L'elenco non contiene la chiave");
        }

        T obj = elenco.get(chiave);

        T objCopia = (T) obj.clone();
        if (objCopia == null) {
            throw new NoSuchElementException("Oggetto copia nullo");
        }
        return objCopia;
    }

    @Override
    public T update(K chiave, T new_obj) throws NoSuchElementException {
        if (chiave == null) {
            throw new NoSuchElementException("Chiave nulla");
        }
        if (new_obj == null) {
            throw new NoSuchElementException("Oggetto aggiornato nullo");
        }
        if (!elenco.containsKey(chiave)) {
            throw new NoSuchElementException("L'elenco non contiene la chiave");
        }

        T objRimosso = elenco.remove(chiave);

        K chiaveObjAggiornato = (K) new_obj.getChiave();

        T newObjClone = (T) new_obj.clone();
        if (newObjClone == null) {
            throw new NoSuchElementException("Oggetto copia nullo");
        }
        elenco.put(chiaveObjAggiornato, newObjClone);

        return objRimosso;
    }

    @Override
    public T delete(K chiave) throws NoSuchElementException {
        if (chiave == null) {
            throw new NoSuchElementException("chiave nulla");
        }

        if (!elenco.containsKey(chiave)) {
            throw new NoSuchElementException("L'elenco non contiene la chiave");
        }

        T objRimosso = elenco.remove(chiave);
        if (objRimosso == null) {
            throw new NoSuchElementException("Oggetto rimosso nullo");
        }

        return objRimosso;
    }

    @Override
    public List<T> elencoCompleto() {
        return new ArrayList<>(elenco.values());
    }

}
