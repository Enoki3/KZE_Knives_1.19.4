
package kzeknife.item;

import java.util.function.Consumer;

import kzeknife.item.renderer.SurvivalknifeItemRenderer;
import kzeknife.procedures.RawfishItemInHandTickProcedure;
import kzeknife.procedures.RawfishItemInInventoryTickProcedure;
import kzeknife.procedures.SurvivalknifeItemInHandTickProcedure;
import kzeknife.procedures.SurvivalknifeItemInInventoryTickProcedure;
import kzeknife.procedures.TetraknifeItemInHandTickProcedure;
import kzeknife.procedures.TetraknifeItemInInventoryTickProcedure;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SurvivalknifeItem extends HoeItem implements GeoItem {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	public String animationprocedure = "empty";
	public static ItemDisplayContext transformType;

	public SurvivalknifeItem() {
		super(Tiers.DIAMOND, -3, 0f, new Item.Properties());
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new IClientItemExtensions() {
			private final BlockEntityWithoutLevelRenderer renderer = new SurvivalknifeItemRenderer();

			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return renderer;
			}
		});
	}

	public void getTransformType(ItemDisplayContext type) {
		this.transformType = type;
	}

	private PlayState idlePredicate(AnimationState event) {
		if (this.transformType != null ? this.transformType.firstPerson() : false) {
			if (this.animationprocedure.equals("empty")) {
				event.getController().setAnimation(RawAnimation.begin().thenLoop("idle"));
				return PlayState.CONTINUE;
			}
		}
		return PlayState.STOP;
	}

	private PlayState procedurePredicate(AnimationState event) {
		if (this.transformType != null ? this.transformType.firstPerson() : false) {
			if (!(this.animationprocedure.equals("empty"))) {
				event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
				if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
					this.animationprocedure = "empty";
					event.getController().forceAnimationReset();
				}
			}
		}
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		AnimationController procedureController = new AnimationController(this, "procedureController", 0, this::procedurePredicate);
		data.add(procedureController);
		AnimationController idleController = new AnimationController(this, "idleController", 0, this::idlePredicate);
		data.add(idleController);
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);

        int id = itemstack.getOrCreateTag().getInt("CustomModelData");
        if (id == 1031) {
            if (selected)
                SurvivalknifeItemInHandTickProcedure.execute(entity, itemstack);
            SurvivalknifeItemInInventoryTickProcedure.execute(entity);
        } else if (id == 1141) {
            if (selected)
                TetraknifeItemInHandTickProcedure.execute(entity, itemstack);
            TetraknifeItemInInventoryTickProcedure.execute(entity);
        } else {
        	if (selected)
                RawfishItemInHandTickProcedure.execute(entity, itemstack);
            RawfishItemInInventoryTickProcedure.execute(entity);
        }
    }
}
