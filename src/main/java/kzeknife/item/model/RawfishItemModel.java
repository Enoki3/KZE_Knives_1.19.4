package kzeknife.item.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import kzeknife.item.RawfishItem;

public class RawfishItemModel extends GeoModel<RawfishItem> {
	@Override
	public ResourceLocation getAnimationResource(RawfishItem animatable) {
		return new ResourceLocation("kze_knife", "animations/raw_fish.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(RawfishItem animatable) {
		return new ResourceLocation("kze_knife", "geo/raw_fish.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(RawfishItem animatable) {
		return new ResourceLocation("kze_knife", "textures/item/raw_fish.png");
	}
}
