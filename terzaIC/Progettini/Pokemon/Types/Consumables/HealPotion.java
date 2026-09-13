package terzaIC.Progettini.Pokemon.Types.Consumables;

import terzaIC.Progettini.Pokemon.Exceptions.OverMaxHpException;
import terzaIC.Progettini.Pokemon.Other.Pokemon;
import terzaIC.Progettini.Pokemon.Types.Item;

public class HealPotion extends Item implements Consumable {
    protected int heal = 20;
    public HealPotion(String name, String description, int quantity, int stack) {
        super(name, description, quantity, stack);
    }
    
    @Override
    public void use(Pokemon p) throws OverMaxHpException {
        if (p.getHP() == p.getHPmax())
            throw new OverMaxHpException(p.getHP(), p.getHPmax());
        p.setHP(Math.min(p.getHP() + heal, p.getHPmax()));
    }

    public int getHeal() {
        return heal;
    }

    @Override
    public String toString() {
        return "Heal Potion [Name: " + name + " Description: " + description + " Quantity: " + quantity + " Stack: " + stack + "]";
    }
}