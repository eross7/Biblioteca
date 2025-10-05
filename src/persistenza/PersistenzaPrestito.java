/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenza;

import dominio.Prestito;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LO.ZAKY
 */
public class PersistenzaPrestito extends PersistenzaGenerica<Integer, Prestito> {

    static Map<Integer, Prestito> elencoPrestiti = new HashMap<>();

    @Override
    protected Map<Integer, Prestito> getMappa() {
        return elencoPrestiti;
    }

}
