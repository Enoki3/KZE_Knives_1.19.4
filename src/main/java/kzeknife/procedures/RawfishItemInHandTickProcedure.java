package kzeknife.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import kzeknife.network.KzeKnifeModVariables;

import kzeknife.item.RawfishItem;

public class RawfishItemInHandTickProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_HOE
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().getDouble("CustomModelData") == 1171
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()
				&& (entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KzeKnifeModVariables.PlayerVariables())).Raw_fish_switch == false) {
			{
				double _setval = Mth.nextInt(RandomSource.create(), 1, 10000);
				entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Raw_fish_number = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if ((entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KzeKnifeModVariables.PlayerVariables())).Raw_fish_number > 1) {
				if ((entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KzeKnifeModVariables.PlayerVariables())).Raw_fish_pick1 == false) {
					if (itemstack.getItem() instanceof RawfishItem)
						itemstack.getOrCreateTag().putString("geckoAnim", "pick1");
					{
						boolean _setval = true;
						entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Raw_fish_pick1 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						boolean _setval = false;
						entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Raw_fish_pick2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					if (itemstack.getItem() instanceof RawfishItem)
						itemstack.getOrCreateTag().putString("geckoAnim", "pick1_sub");
					{
						boolean _setval = false;
						entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Raw_fish_pick1 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			} else if ((entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KzeKnifeModVariables.PlayerVariables())).Raw_fish_number == 1) {
				if ((entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KzeKnifeModVariables.PlayerVariables())).Raw_fish_pick2 == false) {
					if (itemstack.getItem() instanceof RawfishItem)
						itemstack.getOrCreateTag().putString("geckoAnim", "pick2");
					{
						boolean _setval = false;
						entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Raw_fish_pick1 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						boolean _setval = true;
						entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Raw_fish_pick2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					if (itemstack.getItem() instanceof RawfishItem)
						itemstack.getOrCreateTag().putString("geckoAnim", "pick2_sub");
					{
						boolean _setval = false;
						entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Raw_fish_pick2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
			{
				boolean _setval = true;
				entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Raw_fish_switch = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
