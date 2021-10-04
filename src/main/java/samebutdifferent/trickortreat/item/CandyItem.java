package samebutdifferent.trickortreat.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import samebutdifferent.trickortreat.TrickOrTreat;

public class CandyItem extends Item {
    public CandyItem() {
        super(new Item.Properties().stacksTo(16).tab(TrickOrTreat.TAB).food(new FoodProperties.Builder().fast().alwaysEat().build()));
    }
}
