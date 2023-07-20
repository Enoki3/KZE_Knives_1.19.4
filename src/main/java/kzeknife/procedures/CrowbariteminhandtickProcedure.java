package kzeknife.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import kzeknife.network.KzeKnifeModVariables;

import kzeknife.item.CrowbarItem;

public class CrowbariteminhandtickProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_HOE
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().getDouble("CustomModelData") == 1040
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()
				&& (entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KzeKnifeModVariables.PlayerVariables())).Crowbar_switch == false) {
			{
				double _setval = Mth.nextInt(RandomSource.create(), 1, 10000);
				entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Crowbar_number = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if ((entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KzeKnifeModVariables.PlayerVariables())).Crowbar_number > 1) {
				if ((entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KzeKnifeModVariables.PlayerVariables())).Crowbar_pick1 == false) {
					if (itemstack.getItem() instanceof CrowbarItem)
						itemstack.getOrCreateTag().putString("geckoAnim", "pick1");
					{
						boolean _setval = true;
						entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Crowbar_pick1 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						boolean _setval = false;
						entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Crowbar_pick2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					if (itemstack.getItem() instanceof CrowbarItem)
						itemstack.getOrCreateTag().putString("geckoAnim", "pick1_sub");
					{
						boolean _setval = false;
						entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Crowbar_pick1 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			} else if ((entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KzeKnifeModVariables.PlayerVariables())).Crowbar_number == 1) {
				if ((entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KzeKnifeModVariables.PlayerVariables())).Crowbar_pick2 == false) {
					if (itemstack.getItem() instanceof CrowbarItem)
						itemstack.getOrCreateTag().putString("geckoAnim", "pick2");
					{
						boolean _setval = false;
						entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Crowbar_pick1 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					{
						boolean _setval = true;
						entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Crowbar_pick2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				} else {
					if (itemstack.getItem() instanceof CrowbarItem)
						itemstack.getOrCreateTag().putString("geckoAnim", "pick2_sub");
					{
						boolean _setval = false;
						entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Crowbar_pick2 = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
			{
				boolean _setval = true;
				entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Crowbar_switch = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
