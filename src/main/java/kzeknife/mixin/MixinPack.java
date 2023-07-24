package kzeknife.mixin;

import net.minecraft.server.packs.repository.Pack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Pack.class)
public abstract class MixinPack {

    @Shadow
    public abstract String getId();

    @ModifyVariable(method = "<init>", at = @At("LOAD"), ordinal = 1, argsOnly = true)
    private boolean constructorFixedPosition(boolean value) {
        return getId().equals("mod_resources") || value;
    }

    @ModifyVariable(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/Pack$Info;description()Lnet/minecraft/network/chat/Component;"), ordinal = 0, argsOnly = true)
    private Pack.Position constructorDefaultPosition(Pack.Position value) {
        return getId().equals("mod_resources") ? Pack.Position.TOP : value;
    }
}
