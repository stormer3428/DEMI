package fr.stormer3428.demi;

import java.util.ArrayList;
import java.util.List;

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

@SuppressWarnings({"deprecation", "rawtypes"})
public interface Module {
	
	public List<String> getDependencies();
	public default boolean canBeLoaded() {
		List<String> activeModules = new ArrayList<>();
		for(Module module : Demi.ACTIVE_MODULES) activeModules.add(module.getName());
		return activeModules.containsAll(getDependencies());
	}
	
	public String getName();
	public String getDescription();
	public boolean enabled();

	public void onDisable();
	public void onEnable();
	
	public default void onCategoryCreate(CategoryCreateEvent event) {}
	public default void onCategoryDelete(CategoryDeleteEvent event){}
	public default void onCategoryUpdateName(CategoryUpdateNameEvent event){}
	public default void onCategoryUpdatePermissions(CategoryUpdatePermissionsEvent event){}
	public default void onCategoryUpdatePosition(CategoryUpdatePositionEvent event){}
	public default void onDisconnect(DisconnectEvent event){}
	public default void onEmoteAdded(EmoteAddedEvent event){}
	public default void onEmoteRemoved(EmoteRemovedEvent event){}
	public default void onEmoteUpdateName(EmoteUpdateNameEvent event){}
	public default void onEmoteUpdateRoles(EmoteUpdateRolesEvent event){}
	public default void onException(ExceptionEvent event){}
	public default void onGatewayPing(GatewayPingEvent event){}
	public default void onGenericCategory(GenericCategoryEvent event){}
	public default void onGenericCategoryUpdate(GenericCategoryUpdateEvent event){}
	public default void onGenericEmote(GenericEmoteEvent event){}
	public default void onGenericEmoteUpdate(GenericEmoteUpdateEvent event){}
	public default void onGenericEvent(GenericEvent event){}
	public default void onGenericGuild(GenericGuildEvent event){}
	public default void onGenericGuildInvite(GenericGuildInviteEvent event){}
	public default void onGenericGuildMember(GenericGuildMemberEvent event){}
	public default void onGenericGuildMemberUpdate(GenericGuildMemberUpdateEvent event){}
	public default void onGenericGuildMessage(GenericGuildMessageEvent event){}
	public default void onGenericGuildMessageReaction(GenericGuildMessageReactionEvent event){}
	public default void onGenericGuildUpdate(GenericGuildUpdateEvent event){}
	public default void onGenericGuildVoice(GenericGuildVoiceEvent event){}
	public default void onGenericMessage(GenericMessageEvent event){}
	public default void onGenericMessageReaction(GenericMessageReactionEvent event){}
	public default void onGenericPermissionOverride(GenericPermissionOverrideEvent event){}
	public default void onGenericPrivateMessage(GenericPrivateMessageEvent event){}
	public default void onGenericPrivateMessageReaction(GenericPrivateMessageReactionEvent event){}
	public default void onGenericRole(GenericRoleEvent event){}
	public default void onGenericRoleUpdate(GenericRoleUpdateEvent event){}
	public default void onGenericSelfUpdate(GenericSelfUpdateEvent event){}
	public default void onGenericStoreChannel(GenericStoreChannelEvent event){}
	public default void onGenericStoreChannelUpdate(GenericStoreChannelUpdateEvent event){}
	public default void onGenericTextChannel(GenericTextChannelEvent event){}
	public default void onGenericTextChannelUpdate(GenericTextChannelUpdateEvent event){}
	public default void onGenericUpdate(UpdateEvent<?, ?> event){}
	public default void onGenericUser(GenericUserEvent event){}
	public default void onGenericUserPresence(GenericUserPresenceEvent event){}
	public default void onGenericVoiceChannel(GenericVoiceChannelEvent event){}
	public default void onGenericVoiceChannelUpdate(GenericVoiceChannelUpdateEvent event){}
	public default void onGuildAvailable(GuildAvailableEvent event){}
	public default void onGuildBan(GuildBanEvent event){}
	public default void onGuildInviteCreate(GuildInviteCreateEvent event){}
	public default void onGuildInviteDelete(GuildInviteDeleteEvent event){}
	public default void onGuildJoin(GuildJoinEvent event){}
	public default void onGuildLeave(GuildLeaveEvent event){}
	public default void onGuildMemberJoin(GuildMemberJoinEvent event){}
	public default void onGuildMemberLeave(GuildMemberLeaveEvent event){}
	public default void onGuildMemberRemove(GuildMemberRemoveEvent event){}
	public default void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event){}
	public default void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event){}
	public default void onGuildMemberUpdate(GuildMemberUpdateEvent event){}
	public default void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent event){}
	public default void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event){}
	public default void onGuildMemberUpdatePending(GuildMemberUpdatePendingEvent event){}
	public default void onGuildMessageDelete(GuildMessageDeleteEvent event){}
	public default void onGuildMessageEmbed(GuildMessageEmbedEvent event){}
	public default void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event){}
	public default void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event){}
	public default void onGuildMessageReactionRemoveAll(GuildMessageReactionRemoveAllEvent event){}
	public default void onGuildMessageReactionRemoveEmote(GuildMessageReactionRemoveEmoteEvent event){}
	public default void onGuildMessageReceived(GuildMessageReceivedEvent event){}
	public default void onGuildMessageUpdate(GuildMessageUpdateEvent event){}
	public default void onGuildReady(GuildReadyEvent event){}
	public default void onGuildTimeout(GuildTimeoutEvent event){}
	public default void onGuildUnavailable(GuildUnavailableEvent event){}
	public default void onGuildUnban(GuildUnbanEvent event){}
	public default void onGuildUpdateAfkChannel(GuildUpdateAfkChannelEvent event){}
	public default void onGuildUpdateAfkTimeout(GuildUpdateAfkTimeoutEvent event){}
	public default void onGuildUpdateBanner(GuildUpdateBannerEvent event){}
	public default void onGuildUpdateBoostCount(GuildUpdateBoostCountEvent event){}
	public default void onGuildUpdateBoostTier(GuildUpdateBoostTierEvent event){}
	public default void onGuildUpdateCommunityUpdatesChannel(GuildUpdateCommunityUpdatesChannelEvent event){}
	public default void onGuildUpdateDescription(GuildUpdateDescriptionEvent event){}
	public default void onGuildUpdateExplicitContentLevel(GuildUpdateExplicitContentLevelEvent event){}
	public default void onGuildUpdateFeatures(GuildUpdateFeaturesEvent event){}
	public default void onGuildUpdateIcon(GuildUpdateIconEvent event){}
	public default void onGuildUpdateLocale(GuildUpdateLocaleEvent event){}
	public default void onGuildUpdateMaxMembers(GuildUpdateMaxMembersEvent event){}
	public default void onGuildUpdateMaxPresences(GuildUpdateMaxPresencesEvent event){}
	public default void onGuildUpdateMFALevel(GuildUpdateMFALevelEvent event){}
	public default void onGuildUpdateName(GuildUpdateNameEvent event){}
	public default void onGuildUpdateNotificationLevel(GuildUpdateNotificationLevelEvent event){}
	public default void onGuildUpdateOwner(GuildUpdateOwnerEvent event){}
	public default void onGuildUpdateRegion(GuildUpdateRegionEvent event){}
	public default void onGuildUpdateRulesChannel(GuildUpdateRulesChannelEvent event){}
	public default void onGuildUpdateSplash(GuildUpdateSplashEvent event){}
	public default void onGuildUpdateSystemChannel(GuildUpdateSystemChannelEvent event){}
	public default void onGuildUpdateVanityCode(GuildUpdateVanityCodeEvent event){}
	public default void onGuildUpdateVerificationLevel(GuildUpdateVerificationLevelEvent event){}
	public default void onGuildVoiceDeafen(GuildVoiceDeafenEvent event){}
	public default void onGuildVoiceGuildDeafen(GuildVoiceGuildDeafenEvent event){}
	public default void onGuildVoiceGuildMute(GuildVoiceGuildMuteEvent event){}
	public default void onGuildVoiceJoin(GuildVoiceJoinEvent event){}
	public default void onGuildVoiceLeave(GuildVoiceLeaveEvent event){}
	public default void onGuildVoiceMove(GuildVoiceMoveEvent event){}
	public default void onGuildVoiceMute(GuildVoiceMuteEvent event){}
	public default void onGuildVoiceSelfDeafen(GuildVoiceSelfDeafenEvent event){}
	public default void onGuildVoiceSelfMute(GuildVoiceSelfMuteEvent event){}
	public default void onGuildVoiceStream(GuildVoiceStreamEvent event){}
	public default void onGuildVoiceSuppress(GuildVoiceSuppressEvent event){}
	public default void onGuildVoiceUpdate(GuildVoiceUpdateEvent event){}
	public default void onHttpRequest(HttpRequestEvent event){}
	public default void onMessageDelete(MessageDeleteEvent event){}
	public default void onMessageBulkDelete(MessageBulkDeleteEvent event){}
	public default void onMessageEmbed(MessageEmbedEvent event){}
	public default void onMessageReactionAdd(MessageReactionAddEvent event){}
	public default void onMessageReactionRemove(MessageReactionRemoveEvent event){}
	public default void onMessageReactionRemoveAll(MessageReactionRemoveAllEvent event){}
	public default void onMessageReactionRemoveEmote(MessageReactionRemoveEmoteEvent event){}
	public default void onMessageReceived(MessageReceivedEvent event){}
	public default void onMessageUpdate(MessageUpdateEvent event){}
	public default void onPermissionOverrideCreate(PermissionOverrideCreateEvent event){}
	public default void onPermissionOverrideDelete(PermissionOverrideDeleteEvent event){}
	public default void onPermissionOverrideUpdate(PermissionOverrideUpdateEvent event){}
	public default void onPrivateChannelCreate(PrivateChannelCreateEvent event){}
	public default void onPrivateChannelDelete(PrivateChannelDeleteEvent event){}
	public default void onPrivateMessageDelete(PrivateMessageDeleteEvent event){}
	public default void onPrivateMessageEmbed(PrivateMessageEmbedEvent event){}
	public default void onPrivateMessageReactionAdd(PrivateMessageReactionAddEvent event){}
	public default void onPrivateMessageReactionRemove(PrivateMessageReactionRemoveEvent event){}
	public default void onPrivateMessageReceived(PrivateMessageReceivedEvent event){}
	public default void onPrivateMessageUpdate(PrivateMessageUpdateEvent event){}
	public default void onRawGateway(RawGatewayEvent event){}
	public default void onReady(ReadyEvent event){}
	public default void onReconnect(ReconnectedEvent event){}
	public default void onReconnected(ReconnectedEvent event){}
	public default void onResume(ResumedEvent event){}
	public default void onResumed(ResumedEvent event){}
	public default void onRoleCreate(RoleCreateEvent event){}
	public default void onRoleDelete(RoleDeleteEvent event){}
	public default void onRoleUpdateColor(RoleUpdateColorEvent event){}
	public default void onRoleUpdateHoisted(RoleUpdateHoistedEvent event){}
	public default void onRoleUpdateMentionable(RoleUpdateMentionableEvent event){}
	public default void onRoleUpdateName(RoleUpdateNameEvent event){}
	public default void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event){}
	public default void onRoleUpdatePosition(RoleUpdatePositionEvent event){}
	public default void onSelfUpdateAvatar(SelfUpdateAvatarEvent event){}
	public default void onSelfUpdateEmail(SelfUpdateEmailEvent event){}
	public default void onSelfUpdateMFA(SelfUpdateMFAEvent event){}
	public default void onSelfUpdateName(SelfUpdateNameEvent event){}
	public default void onSelfUpdateVerified(SelfUpdateVerifiedEvent event){}
	public default void onShutdown(ShutdownEvent event){}
	public default void onStatusChange(StatusChangeEvent event){}
	public default void onStoreChannelCreate(StoreChannelCreateEvent event){}
	public default void onStoreChannelDelete(StoreChannelDeleteEvent event){}
	public default void onStoreChannelUpdateName(StoreChannelUpdateNameEvent event){}
	public default void onStoreChannelUpdatePermissions(StoreChannelUpdatePermissionsEvent event){}
	public default void onStoreChannelUpdatePosition(StoreChannelUpdatePositionEvent event){}
	public default void onTextChannelCreate(TextChannelCreateEvent event){}
	public default void onTextChannelDelete(TextChannelDeleteEvent event){}
	public default void onTextChannelUpdateName(TextChannelUpdateNameEvent event){}
	public default void onTextChannelUpdateNews(TextChannelUpdateNewsEvent event){}
	public default void onTextChannelUpdateNSFW(TextChannelUpdateNSFWEvent event){}
	public default void onTextChannelUpdateParent(TextChannelUpdateParentEvent event){}
	public default void onTextChannelUpdatePermissions(TextChannelUpdatePermissionsEvent event){}
	public default void onTextChannelUpdatePosition(TextChannelUpdatePositionEvent event){}
	public default void onTextChannelUpdateSlowmode(TextChannelUpdateSlowmodeEvent event){}
	public default void onTextChannelUpdateTopic(TextChannelUpdateTopicEvent event){}
	public default void onUnavailableGuildJoined(UnavailableGuildJoinedEvent event){}
	public default void onUnavailableGuildLeave(UnavailableGuildLeaveEvent event){}
	public default void onUserActivityEnd(UserActivityEndEvent event){}
	public default void onUserActivityStart(UserActivityStartEvent event){}
	public default void onUserTyping(UserTypingEvent event){}
	public default void onUserUpdateActivities(UserUpdateActivitiesEvent event){}
	public default void onUserUpdateActivityOrder(UserUpdateActivityOrderEvent event){}
	public default void onUserUpdateAvatar(UserUpdateAvatarEvent event){}
	public default void onUserUpdateDiscriminator(UserUpdateDiscriminatorEvent event){}
	public default void onUserUpdateFlags(UserUpdateFlagsEvent event){}
	public default void onUserUpdateName(UserUpdateNameEvent event){}
	public default void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event){}
	public default void onVoiceChannelCreate(VoiceChannelCreateEvent event){}
	public default void onVoiceChannelDelete(VoiceChannelDeleteEvent event){}
	public default void onVoiceChannelUpdateBitrate(VoiceChannelUpdateBitrateEvent event){}
	public default void onVoiceChannelUpdateName(VoiceChannelUpdateNameEvent event){}
	public default void onVoiceChannelUpdateParent(VoiceChannelUpdateParentEvent event){}
	public default void onVoiceChannelUpdatePermissions(VoiceChannelUpdatePermissionsEvent event){}
	public default void onVoiceChannelUpdatePosition(VoiceChannelUpdatePositionEvent event){}
	public default void onVoiceChannelUpdateUserLimit(VoiceChannelUpdateUserLimitEvent event){}
}
