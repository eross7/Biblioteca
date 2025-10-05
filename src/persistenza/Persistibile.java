/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenza;

import java.io.Serializable;

/**
 *
 * @author LO.ZAKY
 */
public interface Persistibile<T> extends Serializable, Cloneable {

    public T getChiave();

    public Object clone();

}
