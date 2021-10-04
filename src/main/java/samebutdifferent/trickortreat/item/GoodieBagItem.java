package samebutdifferent.trickortreat.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import samebutdifferent.trickortreat.TrickOrTreat;
import samebutdifferent.trickortreat.registry.ModConfig;

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
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        CompoundTag nbt = stack.getOrCreateTag();
        if (!nbt.hasUUID("Owner")) {
            nbt.putUUID("Owner", entity.getUUID());
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        UUID uuid = player.getUUID();
        if (!stack.getTag().getUUID("Owner").equals(uuid) || !ModConfig.GOODIE_BAG_TRADING.get()) {
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            player.addItem(new ItemStack(contents));
            player.playSound(SoundEvents.AZALEA_PLACE, 1.0F, 1.0F);
            return InteractionResultHolder.consume(stack);
        } else {
            return InteractionResultHolder.fail(stack);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(new TranslatableComponent("Give to a friend to open!"));
    }
}
