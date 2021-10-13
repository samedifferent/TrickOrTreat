package samebutdifferent.trickortreat.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.util.Rarity;
import samebutdifferent.trickortreat.TrickOrTreatModInit;
import samebutdifferent.trickortreat.item.CandyItem;
import samebutdifferent.trickortreat.item.GoodieBagItem;

@SuppressWarnings("SpellCheckingInspection")
public class ModItems implements RegistryClass {
    //region CANDIES
    public static final CandyItem FIREFINGERS = new CandyItem(getBaseSettings().food(new FoodComponent.Builder().snack().alwaysEdible().statusEffect(new StatusEffectInstance(ModEffects.FIREFINGER, 300), 1).build()));
    public static final CandyItem FIZZLERS = new CandyItem();
    public static final CandyItem DEADISH_FISH = new CandyItem(getBaseSettings().food(new FoodComponent.Builder().snack().alwaysEdible().statusEffect(new StatusEffectInstance(ModEffects.WATERBOLT, 200), 1).build()));
    public static final CandyItem PEARL_POP = new CandyItem();
    public static final CandyItem SCREAMBURSTS = new CandyItem(getBaseSettings().food(new FoodComponent.Builder().snack().alwaysEdible().statusEffect(new StatusEffectInstance(ModEffects.SCARY, 200), 1).build()));
    public static final CandyItem EYECE_CREAM = new CandyItem();
    public static final CandyItem MEMBRANE_BUTTER_CUPS = new CandyItem(getBaseSettings().food(new FoodComponent.Builder().snack().alwaysEdible().statusEffect(new StatusEffectInstance(ModEffects.LIFE_LEECH, 300), 1).build()));
    public static final CandyItem BONEBREAKER = new CandyItem(getBaseSettings().food(new FoodComponent.Builder().snack().alwaysEdible().statusEffect(new StatusEffectInstance(ModEffects.BONE_BREAKING, 300), 1).build()));
    public static final CandyItem SLIME_GUM = new CandyItem(getBaseSettings().food(new FoodComponent.Builder().snack().alwaysEdible().statusEffect(new StatusEffectInstance(ModEffects.BOUNCY, 300), 1).build()));
    public static final CandyItem CHOCOLATE_SPIDER_EYE = new CandyItem(getBaseSettings().food(new FoodComponent.Builder().snack().alwaysEdible().statusEffect(new StatusEffectInstance(ModEffects.CLIMBING, 500), 1).build()));
    public static final CandyItem SOUR_PATCH_ZOMBIES = new CandyItem(getBaseSettings().food(new FoodComponent.Builder().snack().alwaysEdible().statusEffect(new StatusEffectInstance(ModEffects.ROTTEN_BITE, 300), 1).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600), 1).build()));
    //endregion

    //region GOODIE BAGS
    public static final GoodieBagItem BLAZE_GOODIE_BAG = new GoodieBagItem(ModItems.FIREFINGERS);
    public static final GoodieBagItem CREEPER_GOODIE_BAG = new GoodieBagItem(ModItems.FIZZLERS);
    public static final GoodieBagItem DROWNED_GOODIE_BAG = new GoodieBagItem(ModItems.DEADISH_FISH);
    public static final GoodieBagItem ENDERMAN_GOODIE_BAG = new GoodieBagItem(ModItems.PEARL_POP);
    public static final GoodieBagItem GHAST_GOODIE_BAG = new GoodieBagItem(ModItems.SCREAMBURSTS);
    public static final GoodieBagItem GUARDIAN_GOODIE_BAG = new GoodieBagItem(ModItems.EYECE_CREAM);
    public static final GoodieBagItem PHANTOM_GOODIE_BAG = new GoodieBagItem(ModItems.MEMBRANE_BUTTER_CUPS);
    public static final GoodieBagItem SKELETON_GOODIE_BAG = new GoodieBagItem(ModItems.BONEBREAKER);
    public static final GoodieBagItem SLIME_GOODIE_BAG = new GoodieBagItem(ModItems.SLIME_GUM);
    public static final GoodieBagItem SPIDER_GOODIE_BAG = new GoodieBagItem(ModItems.CHOCOLATE_SPIDER_EYE);
    public static final GoodieBagItem ZOMBIE_GOODIE_BAG = new GoodieBagItem(ModItems.SOUR_PATCH_ZOMBIES);
    //endregion


    @Override
    public void register(String modId) {

    }

    private static FabricItemSettings getBaseSettings() {
        return new FabricItemSettings().rarity(Rarity.EPIC).maxCount(16).group(TrickOrTreatModInit.TAB);
    }
}