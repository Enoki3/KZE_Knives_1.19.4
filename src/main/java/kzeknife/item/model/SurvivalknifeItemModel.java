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
        }
        return new ResourceLocation("kze_knife", "animations/tetra_knife.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(SurvivalknifeItem animatable) {
        if (customModelData == 1031) {
            return new ResourceLocation("kze_knife", "geo/survival_knife.geo.json");
        }
        return new ResourceLocation("kze_knife", "geo/tetra_knife.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SurvivalknifeItem animatable) {
        if (customModelData == 1031) {
            return new ResourceLocation("kze_knife", "textures/item/survival_knife.png");
        }
        return new ResourceLocation("kze_knife", "textures/item/tetra_knife.png");
    }
}