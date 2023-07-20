package kzeknife.procedures;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import kzeknife.network.KzeKnifeModVariables;

public class RawfishItemInInventoryTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KzeKnifeModVariables.PlayerVariables())).Raw_fish_switch == true
				&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_HOE
						&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().getDouble("CustomModelData") == 1171
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_HOE
								&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getOrCreateTag().getDouble("CustomModelData") == 1171)) {
			{
				boolean _setval = false;
				entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Raw_fish_switch = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
