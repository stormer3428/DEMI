package fr.stormer3428.demi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.RawGatewayEvent;
import net.dv8tion.jda.api.events.StatusChangeEvent;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.GenericChannelEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateArchiveTimestampEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateArchivedEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateAutoArchiveDurationEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateBitrateEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateInvitableEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateLockedEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateNSFWEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateRegionEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateSlowmodeEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateTopicEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateTypeEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateUserLimitEvent;
import net.dv8tion.jda.api.events.channel.update.GenericChannelUpdateEvent;
import net.dv8tion.jda.api.events.emoji.EmojiAddedEvent;
import net.dv8tion.jda.api.events.emoji.EmojiRemovedEvent;
import net.dv8tion.jda.api.events.emoji.GenericEmojiEvent;
import net.dv8tion.jda.api.events.emoji.update.EmojiUpdateNameEvent;
import net.dv8tion.jda.api.events.emoji.update.EmojiUpdateRolesEvent;
import net.dv8tion.jda.api.events.emoji.update.GenericEmojiUpdateEvent;
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
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberUpdateEvent;
import net.dv8tion.jda.api.events.guild.member.update.GenericGuildMemberUpdateEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateAvatarEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdatePendingEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateTimeOutEvent;
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
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNSFWLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNotificationLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateOwnerEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateRulesChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSplashEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSystemChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVanityCodeEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVerificationLevelEvent;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceRequestToSpeakEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceStreamEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSuppressEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceVideoEvent;
import net.dv8tion.jda.api.events.http.HttpRequestEvent;
import net.dv8tion.jda.api.events.interaction.GenericAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.ApplicationCommandUpdatePrivilegesEvent;
import net.dv8tion.jda.api.events.interaction.command.ApplicationUpdatePrivilegesEvent;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.GenericContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.GenericPrivilegeUpdateEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericComponentInteractionCreateEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEmojiEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.role.GenericRoleEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.GenericRoleUpdateEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateColorEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateHoistedEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateIconEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateMentionableEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateNameEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePositionEvent;
import net.dv8tion.jda.api.events.self.GenericSelfUpdateEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateAvatarEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateMFAEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateNameEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateVerifiedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.events.stage.GenericStageInstanceEvent;
import net.dv8tion.jda.api.events.stage.StageInstanceCreateEvent;
import net.dv8tion.jda.api.events.stage.StageInstanceDeleteEvent;
import net.dv8tion.jda.api.events.stage.update.GenericStageInstanceUpdateEvent;
import net.dv8tion.jda.api.events.stage.update.StageInstanceUpdatePrivacyLevelEvent;
import net.dv8tion.jda.api.events.stage.update.StageInstanceUpdateTopicEvent;
import net.dv8tion.jda.api.events.sticker.GenericGuildStickerEvent;
import net.dv8tion.jda.api.events.sticker.GuildStickerAddedEvent;
import net.dv8tion.jda.api.events.sticker.GuildStickerRemovedEvent;
import net.dv8tion.jda.api.events.sticker.update.GenericGuildStickerUpdateEvent;
import net.dv8tion.jda.api.events.sticker.update.GuildStickerUpdateAvailableEvent;
import net.dv8tion.jda.api.events.sticker.update.GuildStickerUpdateDescriptionEvent;
import net.dv8tion.jda.api.events.sticker.update.GuildStickerUpdateNameEvent;
import net.dv8tion.jda.api.events.sticker.update.GuildStickerUpdateTagsEvent;
import net.dv8tion.jda.api.events.thread.GenericThreadEvent;
import net.dv8tion.jda.api.events.thread.ThreadHiddenEvent;
import net.dv8tion.jda.api.events.thread.ThreadRevealedEvent;
import net.dv8tion.jda.api.events.thread.member.GenericThreadMemberEvent;
import net.dv8tion.jda.api.events.thread.member.ThreadMemberJoinEvent;
import net.dv8tion.jda.api.events.thread.member.ThreadMemberLeaveEvent;
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

@SuppressWarnings({"rawtypes"})
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

	public String getName() {return getClass().getSimpleName();};
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
		OUTPUT.info("Attempting to hook into softDependency " + moduleName +"...");
		for(Module module : Demi.i.getActiveModules()) if(module.getName().equals(moduleName)) {
			OUTPUT.ok("Hook into softDependency " + moduleName +" successful");
			return module;
		}
		OUTPUT.cancelled("Failed to hook");
		return null;
	}


	public void onException(ExceptionEvent event) {}
	public void onGatewayPing(GatewayPingEvent event) {}
	public void onGenericEvent(GenericEvent event){}
	public void onGenericGuild(GenericGuildEvent event){}
	public void onGenericGuildInvite(GenericGuildInviteEvent event){}
	public void onGenericGuildMember(GenericGuildMemberEvent event){}
	public void onGenericGuildMemberUpdate(GenericGuildMemberUpdateEvent event){}
	public void onGenericGuildUpdate(GenericGuildUpdateEvent event){}
	public void onGenericGuildVoice(GenericGuildVoiceEvent event){}
	public void onGenericMessage(GenericMessageEvent event){}
	public void onGenericMessageReaction(GenericMessageReactionEvent event){}
	public void onGenericPermissionOverride(GenericPermissionOverrideEvent event){}
	public void onGenericRole(GenericRoleEvent event){}
	public void onGenericRoleUpdate(GenericRoleUpdateEvent event){}
	public void onGenericSelfUpdate(GenericSelfUpdateEvent event){}
	public void onGenericUpdate(UpdateEvent<?, ?> event){}
	public void onGenericUser(GenericUserEvent event){}
	public void onGenericUserPresence(GenericUserPresenceEvent event){}
	public void onGuildAvailable(GuildAvailableEvent event){}
	public void onGuildBan(GuildBanEvent event){}
	public void onGuildInviteCreate(GuildInviteCreateEvent event){}
	public void onGuildInviteDelete(GuildInviteDeleteEvent event){}
	public void onGuildJoin(GuildJoinEvent event){}
	public void onGuildLeave(GuildLeaveEvent event){}
	public void onGuildMemberJoin(GuildMemberJoinEvent event){}
	public void onGuildMemberRemove(GuildMemberRemoveEvent event){}
	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event){}
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event){}
	public void onGuildMemberUpdate(GuildMemberUpdateEvent event){}
	public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent event){}
	public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event){}
	public void onGuildMemberUpdatePending(GuildMemberUpdatePendingEvent event){}
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
	public void onGuildUpdateRulesChannel(GuildUpdateRulesChannelEvent event){}
	public void onGuildUpdateSplash(GuildUpdateSplashEvent event){}
	public void onGuildUpdateSystemChannel(GuildUpdateSystemChannelEvent event){}
	public void onGuildUpdateVanityCode(GuildUpdateVanityCodeEvent event){}
	public void onGuildUpdateVerificationLevel(GuildUpdateVerificationLevelEvent event){}
	public void onGuildVoiceDeafen(GuildVoiceDeafenEvent event){}
	public void onGuildVoiceGuildDeafen(GuildVoiceGuildDeafenEvent event){}
	public void onGuildVoiceGuildMute(GuildVoiceGuildMuteEvent event){}
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
	public void onMessageReceived(MessageReceivedEvent event){}
	public void onMessageUpdate(MessageUpdateEvent event){}
	public void onPermissionOverrideCreate(PermissionOverrideCreateEvent event){}
	public void onPermissionOverrideDelete(PermissionOverrideDeleteEvent event){}
	public void onPermissionOverrideUpdate(PermissionOverrideUpdateEvent event){}
	public void onRawGateway(RawGatewayEvent event){}
	public void onReady(ReadyEvent event){}
	public void onRoleCreate(RoleCreateEvent event){}
	public void onRoleDelete(RoleDeleteEvent event){}
	public void onRoleUpdateColor(RoleUpdateColorEvent event){}
	public void onRoleUpdateHoisted(RoleUpdateHoistedEvent event){}
	public void onRoleUpdateMentionable(RoleUpdateMentionableEvent event){}
	public void onRoleUpdateName(RoleUpdateNameEvent event){}
	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event){}
	public void onRoleUpdatePosition(RoleUpdatePositionEvent event){}
	public void onSelfUpdateAvatar(SelfUpdateAvatarEvent event){}
	public void onSelfUpdateMFA(SelfUpdateMFAEvent event){}
	public void onSelfUpdateName(SelfUpdateNameEvent event){}
	public void onSelfUpdateVerified(SelfUpdateVerifiedEvent event){}
	public void onShutdown(ShutdownEvent event){}
	public void onStatusChange(StatusChangeEvent event){}
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
	public void onApplicationCommandUpdatePrivileges(ApplicationCommandUpdatePrivilegesEvent event){}
	public void onApplicationUpdatePrivileges(ApplicationUpdatePrivilegesEvent event){}
	public void onButtonInteraction(ButtonInteractionEvent event){}
	public void onChannelCreate(ChannelCreateEvent event){}
	public void onChannelDelete(ChannelDeleteEvent event){}
	public void onChannelUpdateArchived(ChannelUpdateArchivedEvent event){}
	public void onChannelUpdateArchiveTimestamp(ChannelUpdateArchiveTimestampEvent event){}
	public void onChannelUpdateAutoArchiveDuration(ChannelUpdateAutoArchiveDurationEvent event){}
	public void onChannelUpdateBitrate(ChannelUpdateBitrateEvent event){}
	public void onChannelUpdateInvitable(ChannelUpdateInvitableEvent event){}
	public void onChannelUpdateLocked(ChannelUpdateLockedEvent event){}
	public void onChannelUpdateName(ChannelUpdateNameEvent event){}
	public void onChannelUpdateNSFW(ChannelUpdateNSFWEvent event){}
	public void onChannelUpdateParent(ChannelUpdateParentEvent event){}
	public void onChannelUpdatePosition(ChannelUpdatePositionEvent event){}
	public void onChannelUpdateRegion(ChannelUpdateRegionEvent event){}
	public void onChannelUpdateSlowmode(ChannelUpdateSlowmodeEvent event){}
	public void onChannelUpdateTopic(ChannelUpdateTopicEvent event){}
	public void onChannelUpdateType(ChannelUpdateTypeEvent event){}
	public void onChannelUpdateUserLimit(ChannelUpdateUserLimitEvent event){}
	public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event){}
	public void onEmojiAdded(EmojiAddedEvent event){}
	public void onEmojiRemoved(EmojiRemovedEvent event){}
	public void onEmojiUpdateName(EmojiUpdateNameEvent event){}
	public void onEmojiUpdateRoles(EmojiUpdateRolesEvent event){}
	public void onGenericAutoCompleteInteraction(GenericAutoCompleteInteractionEvent event){}
	public void onGenericChannel(GenericChannelEvent event){}
	public void onGenericChannelUpdate(GenericChannelUpdateEvent<?> event){}
	public void onGenericCommandInteraction(GenericCommandInteractionEvent event){}
	public void onGenericComponentInteractionCreate(GenericComponentInteractionCreateEvent event){}
	public void onGenericContextInteraction(GenericContextInteractionEvent<?> event){}
	public void onGenericEmoji(GenericEmojiEvent event){}
	public void onGenericEmojiUpdate(GenericEmojiUpdateEvent event){}
	public void onGenericGuildSticker(GenericGuildStickerEvent event){}
	public void onGenericGuildStickerUpdate(GenericGuildStickerUpdateEvent event){}
	public void onGenericInteractionCreate(GenericInteractionCreateEvent event){}
	public void onGenericPrivilegeUpdate(GenericPrivilegeUpdateEvent event){}
	public void onGenericStageInstance(GenericStageInstanceEvent event){}
	public void onGenericStageInstanceUpdate(GenericStageInstanceUpdateEvent event){}
	public void onGenericThread(GenericThreadEvent event){}
	public void onGenericThreadMember(GenericThreadMemberEvent event){}
	public void onGuildMemberUpdateAvatar(GuildMemberUpdateAvatarEvent event){}
	public void onGuildMemberUpdateTimeOut(GuildMemberUpdateTimeOutEvent event){}
	public void onGuildStickerAdded(GuildStickerAddedEvent event){}
	public void onGuildStickerRemoved(GuildStickerRemovedEvent event){}
	public void onGuildStickerUpdateAvailable(GuildStickerUpdateAvailableEvent event){}
	public void onGuildStickerUpdateDescription(GuildStickerUpdateDescriptionEvent event){}
	public void onGuildStickerUpdateName(GuildStickerUpdateNameEvent event){}
	public void onGuildStickerUpdateTags(GuildStickerUpdateTagsEvent event){}
	public void onGuildUpdateNSFWLevel(GuildUpdateNSFWLevelEvent event){}
	public void onGuildVoiceRequestToSpeak(GuildVoiceRequestToSpeakEvent event){}
	public void onGuildVoiceVideo(GuildVoiceVideoEvent event){}
	public void onMessageContextInteraction(MessageContextInteractionEvent event){}
	public void onMessageReactionRemoveEmoji(MessageReactionRemoveEmojiEvent event){}
	public void onModalInteraction(ModalInteractionEvent event){}
	public void onRoleUpdateIcon(RoleUpdateIconEvent event){}
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event){}
	public void onStageInstanceCreate(StageInstanceCreateEvent event){}
	public void onStageInstanceDelete(StageInstanceDeleteEvent event){}
	public void onStageInstanceUpdatePrivacyLevel(StageInstanceUpdatePrivacyLevelEvent event){}
	public void onStageInstanceUpdateTopic(StageInstanceUpdateTopicEvent event){}
	public void onThreadHidden(ThreadHiddenEvent event){}
	public void onThreadMemberJoin(ThreadMemberJoinEvent event){}
	public void onThreadMemberLeave(ThreadMemberLeaveEvent event){}
	public void onThreadRevealed(ThreadRevealedEvent event){}
	public void onUserContextInteraction(UserContextInteractionEvent event){}
	
	public boolean onCommand(DemiCommandReceiveEvent event){return false;}

}
