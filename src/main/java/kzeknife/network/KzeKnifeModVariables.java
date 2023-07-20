package kzeknife.network;

import java.util.function.Supplier;

import kzeknife.KzeKnifeMod;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KzeKnifeModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		KzeKnifeMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level.isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level.isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level.isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			clone.Survival_knife_switch = original.Survival_knife_switch;
			clone.Survival_knife_number = original.Survival_knife_number;
			clone.Survival_knife_pick1 = original.Survival_knife_pick1;
			clone.Survival_knife_pick2 = original.Survival_knife_pick2;
			clone.Survival_knife_pick3 = original.Survival_knife_pick3;
			clone.Survival_knife_rare = original.Survival_knife_rare;
			clone.Crowbar_switch = original.Crowbar_switch;
			clone.Crowbar_number = original.Crowbar_number;
			clone.Crowbar_pick1 = original.Crowbar_pick1;
			clone.Crowbar_pick2 = original.Crowbar_pick2;
			clone.Pen_knife_switch = original.Pen_knife_switch;
			clone.Pen_knife_number = original.Pen_knife_number;
			clone.Pen_knife_pick1 = original.Pen_knife_pick1;
			clone.Pen_knife_pick2 = original.Pen_knife_pick2;
			clone.Raw_fish_switch = original.Raw_fish_switch;
			clone.Raw_fish_number = original.Raw_fish_number;
			clone.Raw_fish_pick1 = original.Raw_fish_pick1;
			clone.Raw_fish_pick2 = original.Raw_fish_pick2;
			clone.Tetra_knife_switch = original.Tetra_knife_switch;
			clone.Tetra_knife_number = original.Tetra_knife_number;
			clone.Tetra_knife_pick1 = original.Tetra_knife_pick1;
			clone.Tetra_knife_pick2 = original.Tetra_knife_pick2;
			if (!event.isWasDeath()) {
			}
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("kze_knife", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public boolean Survival_knife_switch = false;
		public double Survival_knife_number = 0;
		public boolean Survival_knife_pick1 = false;
		public boolean Survival_knife_pick2 = false;
		public boolean Survival_knife_pick3 = false;
		public double Survival_knife_rare = 0;
		public boolean Crowbar_switch = false;
		public double Crowbar_number = 0;
		public boolean Crowbar_pick1 = false;
		public boolean Crowbar_pick2 = false;
		public boolean Pen_knife_switch = false;
		public double Pen_knife_number = 0;
		public boolean Pen_knife_pick1 = false;
		public boolean Pen_knife_pick2 = false;
		public boolean Raw_fish_switch = false;
		public double Raw_fish_number = 0;
		public boolean Raw_fish_pick1 = false;
		public boolean Raw_fish_pick2 = false;
		public boolean Tetra_knife_switch = false;
		public double Tetra_knife_number = 0;
		public boolean Tetra_knife_pick1 = false;
		public boolean Tetra_knife_pick2 = false;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				KzeKnifeMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putBoolean("Survival_knife_switch", Survival_knife_switch);
			nbt.putDouble("Survival_knife_number", Survival_knife_number);
			nbt.putBoolean("Survival_knife_pick1", Survival_knife_pick1);
			nbt.putBoolean("Survival_knife_pick2", Survival_knife_pick2);
			nbt.putBoolean("Survival_knife_pick3", Survival_knife_pick3);
			nbt.putDouble("Survival_knife_rare", Survival_knife_rare);
			nbt.putBoolean("Crowbar_switch", Crowbar_switch);
			nbt.putDouble("Crowbar_number", Crowbar_number);
			nbt.putBoolean("Crowbar_pick1", Crowbar_pick1);
			nbt.putBoolean("Crowbar_pick2", Crowbar_pick2);
			nbt.putBoolean("Pen_knife_switch", Pen_knife_switch);
			nbt.putDouble("Pen_knife_number", Pen_knife_number);
			nbt.putBoolean("Pen_knife_pick1", Pen_knife_pick1);
			nbt.putBoolean("Pen_knife_pick2", Pen_knife_pick2);
			nbt.putBoolean("Raw_fish_switch", Raw_fish_switch);
			nbt.putDouble("Raw_fish_number", Raw_fish_number);
			nbt.putBoolean("Raw_fish_pick1", Raw_fish_pick1);
			nbt.putBoolean("Raw_fish_pick2", Raw_fish_pick2);
			nbt.putBoolean("Tetra_knife_switch", Tetra_knife_switch);
			nbt.putDouble("Tetra_knife_number", Tetra_knife_number);
			nbt.putBoolean("Tetra_knife_pick1", Tetra_knife_pick1);
			nbt.putBoolean("Tetra_knife_pick2", Tetra_knife_pick2);
			return nbt;
		}

		public void readNBT(Tag Tag) {
			CompoundTag nbt = (CompoundTag) Tag;
			Survival_knife_switch = nbt.getBoolean("Survival_knife_switch");
			Survival_knife_number = nbt.getDouble("Survival_knife_number");
			Survival_knife_pick1 = nbt.getBoolean("Survival_knife_pick1");
			Survival_knife_pick2 = nbt.getBoolean("Survival_knife_pick2");
			Survival_knife_pick3 = nbt.getBoolean("Survival_knife_pick3");
			Survival_knife_rare = nbt.getDouble("Survival_knife_rare");
			Crowbar_switch = nbt.getBoolean("Crowbar_switch");
			Crowbar_number = nbt.getDouble("Crowbar_number");
			Crowbar_pick1 = nbt.getBoolean("Crowbar_pick1");
			Crowbar_pick2 = nbt.getBoolean("Crowbar_pick2");
			Pen_knife_switch = nbt.getBoolean("Pen_knife_switch");
			Pen_knife_number = nbt.getDouble("Pen_knife_number");
			Pen_knife_pick1 = nbt.getBoolean("Pen_knife_pick1");
			Pen_knife_pick2 = nbt.getBoolean("Pen_knife_pick2");
			Raw_fish_switch = nbt.getBoolean("Raw_fish_switch");
			Raw_fish_number = nbt.getDouble("Raw_fish_number");
			Raw_fish_pick1 = nbt.getBoolean("Raw_fish_pick1");
			Raw_fish_pick2 = nbt.getBoolean("Raw_fish_pick2");
			Tetra_knife_switch = nbt.getBoolean("Tetra_knife_switch");
			Tetra_knife_number = nbt.getDouble("Tetra_knife_number");
			Tetra_knife_pick1 = nbt.getBoolean("Tetra_knife_pick1");
			Tetra_knife_pick2 = nbt.getBoolean("Tetra_knife_pick2");
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.Survival_knife_switch = message.data.Survival_knife_switch;
					variables.Survival_knife_number = message.data.Survival_knife_number;
					variables.Survival_knife_pick1 = message.data.Survival_knife_pick1;
					variables.Survival_knife_pick2 = message.data.Survival_knife_pick2;
					variables.Survival_knife_pick3 = message.data.Survival_knife_pick3;
					variables.Survival_knife_rare = message.data.Survival_knife_rare;
					variables.Crowbar_switch = message.data.Crowbar_switch;
					variables.Crowbar_number = message.data.Crowbar_number;
					variables.Crowbar_pick1 = message.data.Crowbar_pick1;
					variables.Crowbar_pick2 = message.data.Crowbar_pick2;
					variables.Pen_knife_switch = message.data.Pen_knife_switch;
					variables.Pen_knife_number = message.data.Pen_knife_number;
					variables.Pen_knife_pick1 = message.data.Pen_knife_pick1;
					variables.Pen_knife_pick2 = message.data.Pen_knife_pick2;
					variables.Raw_fish_switch = message.data.Raw_fish_switch;
					variables.Raw_fish_number = message.data.Raw_fish_number;
					variables.Raw_fish_pick1 = message.data.Raw_fish_pick1;
					variables.Raw_fish_pick2 = message.data.Raw_fish_pick2;
					variables.Tetra_knife_switch = message.data.Tetra_knife_switch;
					variables.Tetra_knife_number = message.data.Tetra_knife_number;
					variables.Tetra_knife_pick1 = message.data.Tetra_knife_pick1;
					variables.Tetra_knife_pick2 = message.data.Tetra_knife_pick2;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
