package samebutdifferent.trickortreat.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.item.CandyItem;
import samebutdifferent.trickortreat.item.GoodieBagItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TrickOrTreat.MOD_ID);

    // CANDIES
    public static final RegistryObject<CandyItem> FIREFINGERS = ITEMS.register("firefingers", () -> new CandyItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).tab(TrickOrTreat.TAB).food(new FoodProperties.Builder().fast().alwaysEat().effect(() -> new MobEffectInstance(ModEffects.FIREFINGER.get(), 300), 1).build())));
    public static final RegistryObject<CandyItem> FIZZLERS = ITEMS.register("fizzlers", CandyItem::new);
    public static final RegistryObject<CandyItem> DEADISH_FISH = ITEMS.register("deadish_fish", () -> new CandyItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).tab(TrickOrTreat.TAB).food(new FoodProperties.Builder().fast().alwaysEat().effect(() -> new MobEffectInstance(ModEffects.WATERBOLT.get(), 300), 1).build())));
    public static final RegistryObject<CandyItem> PEARL_POP = ITEMS.register("pearl_pop", CandyItem::new);
    public static final RegistryObject<CandyItem> SCREAMBURSTS = ITEMS.register("screambursts", () -> new CandyItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).tab(TrickOrTreat.TAB).food(new FoodProperties.Builder().fast().alwaysEat().effect(() -> new MobEffectInstance(ModEffects.SCARY.get(), 200), 1).build())));
    public static final RegistryObject<CandyItem> EYECE_CREAM = ITEMS.register("eyece_cream", CandyItem::new);
    public static final RegistryObject<CandyItem> MEMBRANE_BUTTER_CUPS = ITEMS.register("membrane_butter_cups", () -> new CandyItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).tab(TrickOrTreat.TAB).food(new FoodProperties.Builder().fast().alwaysEat().effect(() -> new MobEffectInstance(ModEffects.LIFE_LEECH.get(), 300), 1).build())));
    public static final RegistryObject<CandyItem> BONEBREAKER = ITEMS.register("bonebreaker", () -> new CandyItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).tab(TrickOrTreat.TAB).food(new FoodProperties.Builder().fast().alwaysEat().effect(() -> new MobEffectInstance(ModEffects.BONE_BREAKING.get(), 300), 1).build())));
    public static final RegistryObject<CandyItem> SLIME_GUM = ITEMS.register("slime_gum", () -> new CandyItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).tab(TrickOrTreat.TAB).food(new FoodProperties.Builder().fast().alwaysEat().effect(() -> new MobEffectInstance(ModEffects.BOUNCY.get(), 300), 1).build())));
    public static final RegistryObject<CandyItem> CHOCOLATE_SPIDER_EYE = ITEMS.register("chocolate_spider_eye", () -> new CandyItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).tab(TrickOrTreat.TAB).food(new FoodProperties.Builder().fast().alwaysEat().effect(() -> new MobEffectInstance(ModEffects.CLIMBING.get(), 500), 1).build())));
    public static final RegistryObject<CandyItem> SOUR_PATCH_ZOMBIES = ITEMS.register("sour_patch_zombies", CandyItem::new);

    // GOODIE BAGS
    public static final RegistryObject<GoodieBagItem> BLAZE_GOODIE_BAG = ITEMS.register("blaze_goodie_bag", () -> new GoodieBagItem(ModItems.FIREFINGERS.get()));
    public static final RegistryObject<GoodieBagItem> CREEPER_GOODIE_BAG = ITEMS.register("creeper_goodie_bag", () -> new GoodieBagItem(ModItems.FIZZLERS.get()));
    public static final RegistryObject<GoodieBagItem> DROWNED_GOODIE_BAG = ITEMS.register("drowned_goodie_bag", () -> new GoodieBagItem(ModItems.DEADISH_FISH.get()));
    public static final RegistryObject<GoodieBagItem> ENDERMAN_GOODIE_BAG = ITEMS.register("enderman_goodie_bag", () -> new GoodieBagItem(ModItems.PEARL_POP.get()));
    public static final RegistryObject<GoodieBagItem> GHAST_GOODIE_BAG = ITEMS.register("ghast_goodie_bag", () -> new GoodieBagItem(ModItems.SCREAMBURSTS.get()));
    public static final RegistryObject<GoodieBagItem> GUARDIAN_GOODIE_BAG = ITEMS.register("guardian_goodie_bag", () -> new GoodieBagItem(ModItems.EYECE_CREAM.get()));
    public static final RegistryObject<GoodieBagItem> PHANTOM_GOODIE_BAG = ITEMS.register("phantom_goodie_bag", () -> new GoodieBagItem(ModItems.MEMBRANE_BUTTER_CUPS.get()));
    public static final RegistryObject<GoodieBagItem> SKELETON_GOODIE_BAG = ITEMS.register("skeleton_goodie_bag", () -> new GoodieBagItem(ModItems.BONEBREAKER.get()));
    public static final RegistryObject<GoodieBagItem> SLIME_GOODIE_BAG = ITEMS.register("slime_goodie_bag", () -> new GoodieBagItem(ModItems.SLIME_GUM.get()));
    public static final RegistryObject<GoodieBagItem> SPIDER_GOODIE_BAG = ITEMS.register("spider_goodie_bag", () -> new GoodieBagItem(ModItems.CHOCOLATE_SPIDER_EYE.get()));
    public static final RegistryObject<GoodieBagItem> ZOMBIE_GOODIE_BAG = ITEMS.register("zombie_goodie_bag", () -> new GoodieBagItem(ModItems.SOUR_PATCH_ZOMBIES.get()));

}