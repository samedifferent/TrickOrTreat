package samebutdifferent.trickortreat.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModSoundEvents;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class GoodieBagItem extends Item {
    Item contents;

    public GoodieBagItem(Item candy) {
        super(new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).tab(TrickOrTreat.TAB));
        this.contents = candy;
    }


    @Override
    public void inventoryTick(ItemStack stack, World level, Entity entity, int slotId, boolean isSelected) {
        CompoundNBT nbt = stack.getOrCreateTag();
        if (!nbt.hasUUID("Owner")) {
            nbt.putUUID("Owner", entity.getUUID());
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        UUID uuid = player.getUUID();
        if (!player.abilities.instabuild) {
            stack.shrink(1);
        }
        if (!stack.getTag().getUUID("Owner").equals(uuid)) {
            player.addItem(new ItemStack(contents, 2));
        } else {
            player.addItem(new ItemStack(contents, 1));
        }
        player.playSound(ModSoundEvents.GOODIE_BAG_OPEN.get(), 1.0F, 1.0F);
        return ActionResult.consume(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("item.trickortreat.goodie_bag.tooltip"));
    }
}
