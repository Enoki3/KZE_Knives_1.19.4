package kzeknife.init;

import kzeknife.item.SurvivalknifeItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KzeKnifeModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");
	public static final RegistryObject<Item> SURVIVALKNIFE = REGISTRY.register("diamond_hoe", () -> new SurvivalknifeItem());
	//public static final RegistryObject<Item> CROWBAR = REGISTRY.register("diamond_hoe", () -> new CrowbarItem());
	//public static final RegistryObject<Item> PENKNIFE = REGISTRY.register("diamond_hoe", () -> new PenknifeItem());
	//public static final RegistryObject<Item> RAWFISH = REGISTRY.register("diamond_hoe", () -> new RawfishItem());
	//public static final RegistryObject<Item> TETRAKNIFE = REGISTRY.register("diamond_hoe", () -> new TetraknifeItem());
}
