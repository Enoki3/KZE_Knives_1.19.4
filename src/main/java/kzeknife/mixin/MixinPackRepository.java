package kzeknife.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.repository.PackRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collection;

@Mixin(PackRepository.class)
public abstract class MixinPackRepository {

    @Inject(method = "reload", at = @At("TAIL"))
    private void reload(CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();

        PackRepository repository = mc.getResourcePackRepository();
        Collection<String> selected = new ArrayList<>(repository.getSelectedIds());
        if (selected.contains("server")) {
            selected.remove("server");
            selected.add("server");
        }
        if (selected.contains("mod_resources")) {
            selected.remove("mod_resources");
            selected.add("mod_resources");
        }
        repository.setSelected(selected);
    }
}
