package kzeknife.item.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import kzeknife.item.PenknifeItem;

public class PenknifeItemModel extends GeoModel<PenknifeItem> {
	@Override
	public ResourceLocation getAnimationResource(PenknifeItem animatable) {
		return new ResourceLocation("kze_knife", "animations/pen_knife.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(PenknifeItem animatable) {
		return new ResourceLocation("kze_knife", "geo/pen_knife.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(PenknifeItem animatable) {
		return new ResourceLocation("kze_knife", "textures/item/pen_knife.png");
	}
}
