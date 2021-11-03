package fr.stormer3428.demi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.stormer3428.demi.module.LevelRoleCalculator;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.RawGatewayEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.events.ResumedEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.StatusChangeEvent;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.api.events.channel.category.GenericCategoryEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.category.update.GenericCategoryUpdateEvent;
import net.dv8tion.jda.api.events.channel.priv.PrivateChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.priv.PrivateChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.store.GenericStoreChannelEvent;
import net.dv8tion.jda.api.events.channel.store.StoreChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.store.StoreChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.store.update.GenericStoreChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.text.GenericTextChannelEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.update.GenericTextChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNSFWEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNewsEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateSlowmodeEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateTopicEvent;
import net.dv8tion.jda.api.events.channel.voice.GenericVoiceChannelEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.voice.update.GenericVoiceChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateBitrateEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateUserLimitEvent;
import net.dv8tion.jda.api.events.emote.EmoteAddedEvent;
import net.dv8tion.jda.api.events.emote.EmoteRemovedEvent;
import net.dv8tion.jda.api.events.emote.GenericEmoteEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateNameEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateRolesEvent;
import net.dv8tion.jda.api.events.emote.update.GenericEmoteUpdateEvent;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.guild.GuildAvailableEvent;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildTimeoutEvent;
import net.dv8tion.jda.api.events.guild.GuildUnavailableEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildJoinedEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.invite.GenericGuildInviteEvent;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteCreateEvent;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteDeleteEvent;
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberUpdateEvent;
import net.dv8tion.jda.api.events.guild.member.update.GenericGuildMemberUpdateEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdatePendingEvent;
import net.dv8tion.jda.api.events.guild.override.GenericPermissionOverrideEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideCreateEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideDeleteEvent;
import net.dv8tion.jda.api.events.guild.override.PermissionOverrideUpdateEvent;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateAfkChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateAfkTimeoutEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBannerEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostCountEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostTierEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateCommunityUpdatesChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateDescriptionEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateExplicitContentLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateFeaturesEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateIconEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateLocaleEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMFALevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMaxMembersEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMaxPresencesEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNotificationLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateOwnerEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateRegionEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateRulesChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSplashEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSystemChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVanityCodeEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVerificationLevelEvent;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceStreamEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSuppressEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.http.HttpRequestEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageEmbedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEmoteEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.priv.GenericPrivateMessageEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageEmbedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.priv.react.GenericPrivateMessageReactionEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEmoteEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.role.GenericRoleEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.GenericRoleUpdateEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateColorEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateHoistedEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateMentionableEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateNameEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePositionEvent;
import net.dv8tion.jda.api.events.self.GenericSelfUpdateEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateAvatarEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateEmailEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateMFAEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateNameEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateVerifiedEvent;
import net.dv8tion.jda.api.events.user.GenericUserEvent;
import net.dv8tion.jda.api.events.user.UserActivityEndEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateActivitiesEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateActivityOrderEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateDiscriminatorEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateFlagsEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;

@SuppressWarnings({"deprecation", "rawtypes", "unused"})
public abstract class Module extends HasConfig{

	protected MixedOutput OUTPUT;

	public Module(File file) {
		super(file);
		this.CONFIG_KEYS.add(new Key("enabled", "false", "Module status (enabled/disabled)"));
		this.defaultFileString = ""
				+ "// Module status (enabled/disabled)\r\n"
				+ "enabled:false\r\n"
				+ "\r\n" 
		+ this.defaultFileString;
	}

	public List<String> getDependencies(){return new ArrayList<>();}
	public List<String> getSoftDependencies(){return new ArrayList<>();}
	public boolean canBeLoaded(boolean countSoftDependencies) {
		List<String> activeModules = new ArrayList<>();
		for(Module module : Demi.ACTIVE_MODULES) activeModules.add(module.getName());
		return activeModules.containsAll(getDependencies()) && (!countSoftDependencies || activeModules.containsAll(getSoftDependencies()));
	}

	public abstract String getName();
	public abstract String getDescription();

	public boolean enabled() {
		try {
			return this.CONFIG.get("enabled").equalsIgnoreCase("true");
		}catch (Exception e) {
			DemiConsole.error("Caught an error while attempting to get module state of module "+getName()+", returning false");
			handleTrace(e);
			return false;
		}
	}

	@Override
	protected void handleTrace(Exception e) {
		if(this.PRINT_STACK_TRACE) {
			DemiConsole.info("printing stack trace");
			e.printStackTrace();
		}else DemiConsole.cancelled(getName() + " module set to not print stack trace");
	}

	public void onDisable() {}
	public void onEnable() {
		this.OUTPUT = new MixedOutput(this.CONFIG.get("loggingChannelID"), this.CONFIG.get("logToChannel").equalsIgnoreCase("true"), this.CONFIG.get("logToConsole").equalsIgnoreCase("true"), getName());
		this.CONFIG.fileCheck(true);
	}
	
	public static Module softLoad(String moduleName, MixedOutput OUTPUT) {
		OUTPUT.info("Attempting to hook into softDependency LevelRoleCalculator...");
		for(Module module : Demi.i.getActiveModules()) if(module.getName().equals(moduleName)) {
			OUTPUT.ok("Hook into softDependency " + moduleName +" successful");
			return module;
		}
		OUTPUT.cancelled("Failed to hook");
		return null;
	}

	
	public void onCategoryCreate(CategoryCreateEvent event) {}
	public void onCategoryDelete(CategoryDeleteEvent event){}
	public void onCategoryUpdateName(CategoryUpdateNameEvent event){}
	public void onCategoryUpdatePermissions(CategoryUpdatePermissionsEvent event){}
	public void onCategoryUpdatePosition(CategoryUpdatePositionEvent event){}
	public void onDisconnect(DisconnectEvent event){}
	public void onEmoteAdded(EmoteAddedEvent event){}
	public void onEmoteRemoved(EmoteRemovedEvent event){}
	public void onEmoteUpdateName(EmoteUpdateNameEvent event){}
	public void onEmoteUpdateRoles(EmoteUpdateRolesEvent event){}
	public void onException(ExceptionEvent event){}
	public void onGatewayPing(GatewayPingEvent event){}
	public void onGenericCategory(GenericCategoryEvent event){}
	public void onGenericCategoryUpdate(GenericCategoryUpdateEvent event){}
	public void onGenericEmote(GenericEmoteEvent event){}
	public void onGenericEmoteUpdate(GenericEmoteUpdateEvent event){}
	public void onGenericEvent(GenericEvent event){}
	public void onGenericGuild(GenericGuildEvent event){}
	public void onGenericGuildInvite(GenericGuildInviteEvent event){}
	public void onGenericGuildMember(GenericGuildMemberEvent event){}
	public void onGenericGuildMemberUpdate(GenericGuildMemberUpdateEvent event){}
	public void onGenericGuildMessage(GenericGuildMessageEvent event){}
	public void onGenericGuildMessageReaction(GenericGuildMessageReactionEvent event){}
	public void onGenericGuildUpdate(GenericGuildUpdateEvent event){}
	public void onGenericGuildVoice(GenericGuildVoiceEvent event){}
	public void onGenericMessage(GenericMessageEvent event){}
	public void onGenericMessageReaction(GenericMessageReactionEvent event){}
	public void onGenericPermissionOverride(GenericPermissionOverrideEvent event){}
	public void onGenericPrivateMessage(GenericPrivateMessageEvent event){}
	public void onGenericPrivateMessageReaction(GenericPrivateMessageReactionEvent event){}
	public void onGenericRole(GenericRoleEvent event){}
	public void onGenericRoleUpdate(GenericRoleUpdateEvent event){}
	public void onGenericSelfUpdate(GenericSelfUpdateEvent event){}
	public void onGenericStoreChannel(GenericStoreChannelEvent event){}
	public void onGenericStoreChannelUpdate(GenericStoreChannelUpdateEvent event){}
	public void onGenericTextChannel(GenericTextChannelEvent event){}
	public void onGenericTextChannelUpdate(GenericTextChannelUpdateEvent event){}
	public void onGenericUpdate(UpdateEvent<?, ?> event){}
	public void onGenericUser(GenericUserEvent event){}
	public void onGenericUserPresence(GenericUserPresenceEvent event){}
	public void onGenericVoiceChannel(GenericVoiceChannelEvent event){}
	public void onGenericVoiceChannelUpdate(GenericVoiceChannelUpdateEvent event){}
	public void onGuildAvailable(GuildAvailableEvent event){}
	public void onGuildBan(GuildBanEvent event){}
	public void onGuildInviteCreate(GuildInviteCreateEvent event){}
	public void onGuildInviteDelete(GuildInviteDeleteEvent event){}
	public void onGuildJoin(GuildJoinEvent event){}
	public void onGuildLeave(GuildLeaveEvent event){}
	public void onGuildMemberJoin(GuildMemberJoinEvent event){}
	public void onGuildMemberLeave(GuildMemberLeaveEvent event){}
	public void onGuildMemberRemove(GuildMemberRemoveEvent event){}
	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event){}
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event){}
	public void onGuildMemberUpdate(GuildMemberUpdateEvent event){}
	public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent event){}
	public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event){}
	public void onGuildMemberUpdatePending(GuildMemberUpdatePendingEvent event){}
	public void onGuildMessageDelete(GuildMessageDeleteEvent event){}
	public void onGuildMessageEmbed(GuildMessageEmbedEvent event){}
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event){}
	public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event){}
	public void onGuildMessageReactionRemoveAll(GuildMessageReactionRemoveAllEvent event){}
	public void onGuildMessageReactionRemoveEmote(GuildMessageReactionRemoveEmoteEvent event){}
	public void onGuildMessageReceived(GuildMessageReceivedEvent event){}
	public void onGuildMessageUpdate(GuildMessageUpdateEvent event){}
	public void onGuildReady(GuildReadyEvent event){}
	public void onGuildTimeout(GuildTimeoutEvent event){}
	public void onGuildUnavailable(GuildUnavailableEvent event){}
	public void onGuildUnban(GuildUnbanEvent event){}
	public void onGuildUpdateAfkChannel(GuildUpdateAfkChannelEvent event){}
	public void onGuildUpdateAfkTimeout(GuildUpdateAfkTimeoutEvent event){}
	public void onGuildUpdateBanner(GuildUpdateBannerEvent event){}
	public void onGuildUpdateBoostCount(GuildUpdateBoostCountEvent event){}
	public void onGuildUpdateBoostTier(GuildUpdateBoostTierEvent event){}
	public void onGuildUpdateCommunityUpdatesChannel(GuildUpdateCommunityUpdatesChannelEvent event){}
	public void onGuildUpdateDescription(GuildUpdateDescriptionEvent event){}
	public void onGuildUpdateExplicitContentLevel(GuildUpdateExplicitContentLevelEvent event){}
	public void onGuildUpdateFeatures(GuildUpdateFeaturesEvent event){}
	public void onGuildUpdateIcon(GuildUpdateIconEvent event){}
	public void onGuildUpdateLocale(GuildUpdateLocaleEvent event){}
	public void onGuildUpdateMaxMembers(GuildUpdateMaxMembersEvent event){}
	public void onGuildUpdateMaxPresences(GuildUpdateMaxPresencesEvent event){}
	public void onGuildUpdateMFALevel(GuildUpdateMFALevelEvent event){}
	public void onGuildUpdateName(GuildUpdateNameEvent event){}
	public void onGuildUpdateNotificationLevel(GuildUpdateNotificationLevelEvent event){}
	public void onGuildUpdateOwner(GuildUpdateOwnerEvent event){}
	public void onGuildUpdateRegion(GuildUpdateRegionEvent event){}
	public void onGuildUpdateRulesChannel(GuildUpdateRulesChannelEvent event){}
	public void onGuildUpdateSplash(GuildUpdateSplashEvent event){}
	public void onGuildUpdateSystemChannel(GuildUpdateSystemChannelEvent event){}
	public void onGuildUpdateVanityCode(GuildUpdateVanityCodeEvent event){}
	public void onGuildUpdateVerificationLevel(GuildUpdateVerificationLevelEvent event){}
	public void onGuildVoiceDeafen(GuildVoiceDeafenEvent event){}
	public void onGuildVoiceGuildDeafen(GuildVoiceGuildDeafenEvent event){}
	public void onGuildVoiceGuildMute(GuildVoiceGuildMuteEvent event){}
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event){}
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event){}
	public void onGuildVoiceMove(GuildVoiceMoveEvent event){}
	public void onGuildVoiceMute(GuildVoiceMuteEvent event){}
	public void onGuildVoiceSelfDeafen(GuildVoiceSelfDeafenEvent event){}
	public void onGuildVoiceSelfMute(GuildVoiceSelfMuteEvent event){}
	public void onGuildVoiceStream(GuildVoiceStreamEvent event){}
	public void onGuildVoiceSuppress(GuildVoiceSuppressEvent event){}
	public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event){}
	public void onHttpRequest(HttpRequestEvent event){}
	public void onMessageDelete(MessageDeleteEvent event){}
	public void onMessageBulkDelete(MessageBulkDeleteEvent event){}
	public void onMessageEmbed(MessageEmbedEvent event){}
	public void onMessageReactionAdd(MessageReactionAddEvent event){}
	public void onMessageReactionRemove(MessageReactionRemoveEvent event){}
	public void onMessageReactionRemoveAll(MessageReactionRemoveAllEvent event){}
	public void onMessageReactionRemoveEmote(MessageReactionRemoveEmoteEvent event){}
	public void onMessageReceived(MessageReceivedEvent event){}
	public void onMessageUpdate(MessageUpdateEvent event){}
	public void onPermissionOverrideCreate(PermissionOverrideCreateEvent event){}
	public void onPermissionOverrideDelete(PermissionOverrideDeleteEvent event){}
	public void onPermissionOverrideUpdate(PermissionOverrideUpdateEvent event){}
	public void onPrivateChannelCreate(PrivateChannelCreateEvent event){}
	public void onPrivateChannelDelete(PrivateChannelDeleteEvent event){}
	public void onPrivateMessageDelete(PrivateMessageDeleteEvent event){}
	public void onPrivateMessageEmbed(PrivateMessageEmbedEvent event){}
	public void onPrivateMessageReactionAdd(PrivateMessageReactionAddEvent event){}
	public void onPrivateMessageReactionRemove(PrivateMessageReactionRemoveEvent event){}
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event){}
	public void onPrivateMessageUpdate(PrivateMessageUpdateEvent event){}
	public void onRawGateway(RawGatewayEvent event){}
	public void onReady(ReadyEvent event){}
	public void onReconnect(ReconnectedEvent event){}
	public void onReconnected(ReconnectedEvent event){}
	public void onResume(ResumedEvent event){}
	public void onResumed(ResumedEvent event){}
	public void onRoleCreate(RoleCreateEvent event){}
	public void onRoleDelete(RoleDeleteEvent event){}
	public void onRoleUpdateColor(RoleUpdateColorEvent event){}
	public void onRoleUpdateHoisted(RoleUpdateHoistedEvent event){}
	public void onRoleUpdateMentionable(RoleUpdateMentionableEvent event){}
	public void onRoleUpdateName(RoleUpdateNameEvent event){}
	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event){}
	public void onRoleUpdatePosition(RoleUpdatePositionEvent event){}
	public void onSelfUpdateAvatar(SelfUpdateAvatarEvent event){}
	public void onSelfUpdateEmail(SelfUpdateEmailEvent event){}
	public void onSelfUpdateMFA(SelfUpdateMFAEvent event){}
	public void onSelfUpdateName(SelfUpdateNameEvent event){}
	public void onSelfUpdateVerified(SelfUpdateVerifiedEvent event){}
	public void onShutdown(ShutdownEvent event){}
	public void onStatusChange(StatusChangeEvent event){}
	public void onStoreChannelCreate(StoreChannelCreateEvent event){}
	public void onStoreChannelDelete(StoreChannelDeleteEvent event){}
	public void onStoreChannelUpdateName(StoreChannelUpdateNameEvent event){}
	public void onStoreChannelUpdatePermissions(StoreChannelUpdatePermissionsEvent event){}
	public void onStoreChannelUpdatePosition(StoreChannelUpdatePositionEvent event){}
	public void onTextChannelCreate(TextChannelCreateEvent event){}
	public void onTextChannelDelete(TextChannelDeleteEvent event){}
	public void onTextChannelUpdateName(TextChannelUpdateNameEvent event){}
	public void onTextChannelUpdateNews(TextChannelUpdateNewsEvent event){}
	public void onTextChannelUpdateNSFW(TextChannelUpdateNSFWEvent event){}
	public void onTextChannelUpdateParent(TextChannelUpdateParentEvent event){}
	public void onTextChannelUpdatePermissions(TextChannelUpdatePermissionsEvent event){}
	public void onTextChannelUpdatePosition(TextChannelUpdatePositionEvent event){}
	public void onTextChannelUpdateSlowmode(TextChannelUpdateSlowmodeEvent event){}
	public void onTextChannelUpdateTopic(TextChannelUpdateTopicEvent event){}
	public void onUnavailableGuildJoined(UnavailableGuildJoinedEvent event){}
	public void onUnavailableGuildLeave(UnavailableGuildLeaveEvent event){}
	public void onUserActivityEnd(UserActivityEndEvent event){}
	public void onUserActivityStart(UserActivityStartEvent event){}
	public void onUserTyping(UserTypingEvent event){}
	public void onUserUpdateActivities(UserUpdateActivitiesEvent event){}
	public void onUserUpdateActivityOrder(UserUpdateActivityOrderEvent event){}
	public void onUserUpdateAvatar(UserUpdateAvatarEvent event){}
	public void onUserUpdateDiscriminator(UserUpdateDiscriminatorEvent event){}
	public void onUserUpdateFlags(UserUpdateFlagsEvent event){}
	public void onUserUpdateName(UserUpdateNameEvent event){}
	public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event){}
	public void onVoiceChannelCreate(VoiceChannelCreateEvent event){}
	public void onVoiceChannelDelete(VoiceChannelDeleteEvent event){}
	public void onVoiceChannelUpdateBitrate(VoiceChannelUpdateBitrateEvent event){}
	public void onVoiceChannelUpdateName(VoiceChannelUpdateNameEvent event){}
	public void onVoiceChannelUpdateParent(VoiceChannelUpdateParentEvent event){}
	public void onVoiceChannelUpdatePermissions(VoiceChannelUpdatePermissionsEvent event){}
	public void onVoiceChannelUpdatePosition(VoiceChannelUpdatePositionEvent event){}
	public void onVoiceChannelUpdateUserLimit(VoiceChannelUpdateUserLimitEvent event){}
	public boolean onCommand(DemiCommandReceiveEvent event){return false;}
}
