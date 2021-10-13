package samebutdifferent.trickortreat.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import samebutdifferent.trickortreat.TrickOrTreatModInit;
import samebutdifferent.trickortreat.registry.ModSoundEvents;

import java.util.List;
import java.util.UUID;

public class GoodieBagItem extends Item {
    private final Item contents;

    public GoodieBagItem(Item candy) {
        super(new Item.Settings().rarity(Rarity.EPIC).maxCount(16).group(TrickOrTreatModInit.TAB));
        this.contents = candy;
    }

    @Override
    public void inventoryTick(ItemStack stack, World level, Entity entity, int slotId, boolean isSelected) {
        NbtCompound nbt = stack.getOrCreateNbt();
        if (!nbt.containsUuid("Owner")) {
            nbt.putUuid("Owner", entity.getUuid());
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        UUID uuid = player.getUuid();
        if (!player.getAbilities().creativeMode) {
            stack.decrement(1);
        }
        if (!stack.getNbt().getUuid("Owner").equals(uuid)) {
            player.giveItemStack(new ItemStack(contents, 2));
        } else {
            player.giveItemStack(new ItemStack(contents, 1));
        }
        player.playSound(ModSoundEvents.GOODIE_BAG_OPEN, 1.0F, 1.0F);
        return TypedActionResult.consume(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext flag) {
        tooltip.add(new TranslatableText("item.trickortreat.goodie_bag.tooltip"));
    }
}
