package kzeknife.init;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KzeKnifeModTabs {
	@SubscribeEvent
	public static void buildTabContentsVanilla(CreativeModeTabEvent.BuildContents tabData) {

		if (tabData.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(KzeKnifeModItems.SURVIVALKNIFE.get());
			//tabData.accept(KzeKnifeModItems.CROWBAR.get());
			//tabData.accept(KzeKnifeModItems.PENKNIFE.get());
			//tabData.accept(KzeKnifeModItems.RAWFISH.get());
			//tabData.accept(KzeKnifeModItems.TETRAKNIFE.get());
		}
	}
}
