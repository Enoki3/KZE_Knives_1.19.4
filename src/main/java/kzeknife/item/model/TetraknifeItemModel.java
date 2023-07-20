package kzeknife.item.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import kzeknife.item.TetraknifeItem;

public class TetraknifeItemModel extends GeoModel<TetraknifeItem> {
	@Override
	public ResourceLocation getAnimationResource(TetraknifeItem animatable) {
		return new ResourceLocation("kze_knife", "animations/tetra_knife.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(TetraknifeItem animatable) {
		return new ResourceLocation("kze_knife", "geo/tetra_knife.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TetraknifeItem animatable) {
		return new ResourceLocation("kze_knife", "textures/item/tetra_knife.png");
	}
}
