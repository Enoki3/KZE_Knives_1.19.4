package kzeknife.procedures;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import kzeknife.network.KzeKnifeModVariables;

public class CrowbaritemininventorytickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KzeKnifeModVariables.PlayerVariables())).Crowbar_switch == true
				&& !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_HOE
						&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().getDouble("CustomModelData") == 1040
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == Items.DIAMOND_HOE
								&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getOrCreateTag().getDouble("CustomModelData") == 1040)) {
			{
				boolean _setval = false;
				entity.getCapability(KzeKnifeModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Crowbar_switch = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
