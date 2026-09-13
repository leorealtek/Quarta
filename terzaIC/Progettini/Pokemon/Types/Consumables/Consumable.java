package terzaIC.Progettini.Pokemon.Types.Consumables;

import terzaIC.Progettini.Pokemon.Exceptions.UnsupportedActionException;
import terzaIC.Progettini.Pokemon.Other.Pokemon;

public interface Consumable {
    public void use(Pokemon p) throws UnsupportedActionException;
}