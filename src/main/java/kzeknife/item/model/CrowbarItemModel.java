package kzeknife.item.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import kzeknife.item.CrowbarItem;

public class CrowbarItemModel extends GeoModel<CrowbarItem> {
	@Override
	public ResourceLocation getAnimationResource(CrowbarItem animatable) {
		return new ResourceLocation("kze_knife", "animations/crowbar.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(CrowbarItem animatable) {
		return new ResourceLocation("kze_knife", "geo/crowbar.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CrowbarItem animatable) {
		return new ResourceLocation("kze_knife", "textures/item/crowbar.png");
	}
}
