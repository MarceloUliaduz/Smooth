package Outils;

import java.io.Serializable;
import java.util.Collection;

/**
 * Classe d'une paire d'objets.
 * @param <T> Classe du premier élément
 * @param <U> Classe du second élément
 */
public class Paire<T, U> {
    T premierElement;
    U secondElement;

    public Paire(T element1, U element2) {
        premierElement = element1;
        secondElement = element2;
    }

    public T getFirst() {
        return premierElement;
    }

    public U getSecond() {
        return secondElement;
    }
}
