package kzeknife.mixin;

import net.minecraft.server.packs.repository.PackRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collection;

@Mixin(PackRepository.class)
public abstract class MixinPackRepository {

    @Shadow
    public abstract Collection<String> getSelectedIds();

    @Shadow
    public abstract void setSelected(Collection<String> collection);

    @Inject(method = "reload", at = @At("TAIL"))
    private void reload(CallbackInfo ci) {
        Collection<String> selected = new ArrayList<>(getSelectedIds());
        if (selected.contains("server")) {
            selected.remove("server");
            selected.add("server");
        }
        if (selected.contains("mod_resources")) {
            selected.remove("mod_resources");
            selected.add("mod_resources");
        }
        setSelected(selected);
    }
}
