package kzeknife.item.model;

import kzeknife.item.SurvivalknifeItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SurvivalknifeItemModel extends GeoModel<SurvivalknifeItem> {

    public int customModelData = -1;

    @Override
    public ResourceLocation getAnimationResource(SurvivalknifeItem animatable) {
        if (customModelData == 1031) {
            return new ResourceLocation("kze_knife", "animations/survival_knife.animation.json");
        } else if (customModelData == 1040) {
            return new ResourceLocation("kze_knife", "animations/crowbar.animation.json");
        } else if (customModelData == 1131){
            return new ResourceLocation("kze_knife", "animations/pen_knife.animation.json");
        } else if (customModelData == 1141){
            return new ResourceLocation("kze_knife", "animations/tetra_knife.animation.json");
        } else {
        	return new ResourceLocation("kze_knife", "animations/raw_fish.animation.json");
        } 
    }

    @Override
    public ResourceLocation getModelResource(SurvivalknifeItem animatable) {
        if (customModelData == 1031) {
            return new ResourceLocation("kze_knife", "geo/survival_knife.geo.json");
        } else if (customModelData == 1040) {
        	return new ResourceLocation("kze_knife", "geo/crowbar.geo.json");
        } else if (customModelData == 1131) {
        	return new ResourceLocation("kze_knife", "geo/pen_knife.geo.json");
        } else if (customModelData == 1141) {
        	return new ResourceLocation("kze_knife", "geo/tetra_knife.geo.json");
        } else {
        	return new ResourceLocation("kze_knife", "geo/raw_fish.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureResource(SurvivalknifeItem animatable) {
        if (customModelData == 1031) {
            return new ResourceLocation("kze_knife", "textures/item/survival_knife.png");
        } else if (customModelData == 1040) {
        	return new ResourceLocation("kze_knife", "textures/item/crowbar.png");
        } else if (customModelData == 1131) {
        	return new ResourceLocation("kze_knife", "textures/item/pen_knife.png");
        } else if (customModelData == 1141) {
        	return new ResourceLocation("kze_knife", "textures/item/tetra_knife.png");
        } else {
        	return new ResourceLocation("kze_knife", "textures/item/raw_fish.png");
        }
    }
}