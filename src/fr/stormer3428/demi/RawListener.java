package fr.stormer3428.demi;

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
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@SuppressWarnings({"deprecation", "rawtypes"})
public class RawListener extends ListenerAdapter{
	
	@Override
	public void onCategoryCreate(CategoryCreateEvent event) {
		for(Module module : Demi.i.getActiveModules()) {module.onCategoryCreate(event);}
	}
	
	@Override
	public void onCategoryDelete(CategoryDeleteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onCategoryDelete(event);}
	}
	
	@Override
	public void onCategoryUpdateName(CategoryUpdateNameEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onCategoryUpdateName(event);}
	}
	
	@Override
	public void onCategoryUpdatePermissions(CategoryUpdatePermissionsEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onCategoryUpdatePermissions(event);}
	}
	
	@Override
	public void onCategoryUpdatePosition(CategoryUpdatePositionEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onCategoryUpdatePosition(event);}
	}
	
	@Override
	public void onDisconnect(DisconnectEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onDisconnect(event);}
	}
	
	@Override
	public void onEmoteAdded(EmoteAddedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onEmoteAdded(event);}
	}
	
	@Override
	public void onEmoteRemoved(EmoteRemovedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onEmoteRemoved(event);}
	}
	
	@Override
	public void onEmoteUpdateName(EmoteUpdateNameEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onEmoteUpdateName(event);}
	}
	
	@Override
	public void onEmoteUpdateRoles(EmoteUpdateRolesEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onEmoteUpdateRoles(event);}
	}
	
	@Override
	public void onException(ExceptionEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onException(event);}
	}
	
	@Override
	public void onGatewayPing(GatewayPingEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGatewayPing(event);}
	}
	
	@Override
	public void onGenericCategory(GenericCategoryEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericCategory(event);}
	}
	
	@Override
	public void onGenericCategoryUpdate(GenericCategoryUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericCategoryUpdate(event);}
	}
	
	@Override
	public void onGenericEmote(GenericEmoteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericEmote(event);}
	}
	
	@Override
	public void onGenericEmoteUpdate(GenericEmoteUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericEmoteUpdate(event);}
	}
	
	@Override
	public void onGenericEvent(GenericEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericEvent(event);}
	}
	
	@Override
	public void onGenericGuild(GenericGuildEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericGuild(event);}
	}
	
	@Override
	public void onGenericGuildInvite(GenericGuildInviteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericGuildInvite(event);}
	}
	
	@Override
	public void onGenericGuildMember(GenericGuildMemberEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericGuildMember(event);}
	}
	
	@Override
	public void onGenericGuildMemberUpdate(GenericGuildMemberUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericGuildMemberUpdate(event);}
	}
	
	@Override
	public void onGenericGuildMessage(GenericGuildMessageEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericGuildMessage(event);}
	}
	
	@Override
	public void onGenericGuildMessageReaction(GenericGuildMessageReactionEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericGuildMessageReaction(event);}
	}
	
	@Override
	public void onGenericGuildUpdate(GenericGuildUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericGuildUpdate(event);}
	}
	
	@Override
	public void onGenericGuildVoice(GenericGuildVoiceEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericGuildVoice(event);}
	}
	
	@Override
	public void onGenericMessage(GenericMessageEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericMessage(event);}
	}
	
	@Override
	public void onGenericMessageReaction(GenericMessageReactionEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericMessageReaction(event);}
	}
	
	@Override
	public void onGenericPermissionOverride(GenericPermissionOverrideEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericPermissionOverride(event);}
	}
	
	@Override
	public void onGenericPrivateMessage(GenericPrivateMessageEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericPrivateMessage(event);}
	}
	
	@Override
	public void onGenericPrivateMessageReaction(GenericPrivateMessageReactionEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericPrivateMessageReaction(event);}
	}
	
	@Override
	public void onGenericRole(GenericRoleEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericRole(event);}
	}
	
	@Override
	public void onGenericRoleUpdate(GenericRoleUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericRoleUpdate(event);}
	}
	
	@Override
	public void onGenericSelfUpdate(GenericSelfUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericSelfUpdate(event);}
	}
	
	@Override
	public void onGenericStoreChannel(GenericStoreChannelEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericStoreChannel(event);}
	}
	
	@Override
	public void onGenericStoreChannelUpdate(GenericStoreChannelUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericStoreChannelUpdate(event);}
	}
	
	@Override
	public void onGenericTextChannel(GenericTextChannelEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericTextChannel(event);}
	}
	
	@Override
	public void onGenericTextChannelUpdate(GenericTextChannelUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericTextChannelUpdate(event);}
	}
	
	@Override
	public void onGenericUpdate(UpdateEvent<?, ?> event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericUpdate(event);}
	}
	
	@Override
	public void onGenericUser(GenericUserEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericUser(event);}
	}
	
	@Override
	public void onGenericUserPresence(GenericUserPresenceEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericUserPresence(event);}
	}
	
	@Override
	public void onGenericVoiceChannel(GenericVoiceChannelEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericVoiceChannel(event);}
	}
	
	@Override
	public void onGenericVoiceChannelUpdate(GenericVoiceChannelUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGenericVoiceChannelUpdate(event);}
	}
	
	@Override
	public void onGuildAvailable(GuildAvailableEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildAvailable(event);}
	}
	
	@Override
	public void onGuildBan(GuildBanEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildBan(event);}
	}
	
	@Override
	public void onGuildInviteCreate(GuildInviteCreateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildInviteCreate(event);}
	}
	
	@Override
	public void onGuildInviteDelete(GuildInviteDeleteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildInviteDelete(event);}
	}
	
	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildJoin(event);}
	}
	
	@Override
	public void onGuildLeave(GuildLeaveEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildLeave(event);}
	}
	
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMemberJoin(event);}
	}
	
	@Override
	public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMemberLeave(event);}
	}
	
	@Override
	public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMemberRemove(event);}
	}
	
	@Override
	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMemberRoleAdd(event);}
	}
	
	@Override
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMemberRoleRemove(event);}
	}
	
	@Override
	public void onGuildMemberUpdate(GuildMemberUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMemberUpdate(event);}
	}
	
	@Override
	public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMemberUpdateBoostTime(event);}
	}
	
	@Override
	public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMemberUpdateNickname(event);}
	}
	
	@Override
	public void onGuildMemberUpdatePending(GuildMemberUpdatePendingEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMemberUpdatePending(event);}
	}
	
	@Override
	public void onGuildMessageDelete(GuildMessageDeleteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMessageDelete(event);}
	}
	
	@Override
	public void onGuildMessageEmbed(GuildMessageEmbedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMessageEmbed(event);}
	}
	
	@Override
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMessageReactionAdd(event);}
	}
	
	@Override
	public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMessageReactionRemove(event);}
	}
	
	@Override
	public void onGuildMessageReactionRemoveAll(GuildMessageReactionRemoveAllEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMessageReactionRemoveAll(event);}
	}
	
	@Override
	public void onGuildMessageReactionRemoveEmote(GuildMessageReactionRemoveEmoteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMessageReactionRemoveEmote(event);}
	}
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMessageReceived(event);}
	}
	
	@Override
	public void onGuildMessageUpdate(GuildMessageUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildMessageUpdate(event);}
	}
	
	@Override
	public void onGuildReady(GuildReadyEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildReady(event);}
	}
	
	@Override
	public void onGuildTimeout(GuildTimeoutEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildTimeout(event);}
	}
	
	@Override
	public void onGuildUnavailable(GuildUnavailableEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUnavailable(event);}
	}
	
	@Override
	public void onGuildUnban(GuildUnbanEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUnban(event);}
	}
	
	@Override
	public void onGuildUpdateAfkChannel(GuildUpdateAfkChannelEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateAfkChannel(event);}
	}
	
	@Override
	public void onGuildUpdateAfkTimeout(GuildUpdateAfkTimeoutEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateAfkTimeout(event);}
	}
	
	@Override
	public void onGuildUpdateBanner(GuildUpdateBannerEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateBanner(event);}
	}
	
	@Override
	public void onGuildUpdateBoostCount(GuildUpdateBoostCountEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateBoostCount(event);}
	}
	
	@Override
	public void onGuildUpdateBoostTier(GuildUpdateBoostTierEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateBoostTier(event);}
	}
	
	@Override
	public void onGuildUpdateCommunityUpdatesChannel(GuildUpdateCommunityUpdatesChannelEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateCommunityUpdatesChannel(event);}
	}
	
	@Override
	public void onGuildUpdateDescription(GuildUpdateDescriptionEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateDescription(event);}
	}
	
	@Override
	public void onGuildUpdateExplicitContentLevel(GuildUpdateExplicitContentLevelEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateExplicitContentLevel(event);}
	}
	
	@Override
	public void onGuildUpdateFeatures(GuildUpdateFeaturesEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateFeatures(event);}
	}
	
	@Override
	public void onGuildUpdateIcon(GuildUpdateIconEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateIcon(event);}
	}
	
	@Override
	public void onGuildUpdateLocale(GuildUpdateLocaleEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateLocale(event);}
	}
	
	@Override
	public void onGuildUpdateMaxMembers(GuildUpdateMaxMembersEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateMaxMembers(event);}
	}
	
	@Override
	public void onGuildUpdateMaxPresences(GuildUpdateMaxPresencesEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateMaxPresences(event);}
	}
	
	@Override
	public void onGuildUpdateMFALevel(GuildUpdateMFALevelEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateMFALevel(event);}
	}
	
	@Override
	public void onGuildUpdateName(GuildUpdateNameEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateName(event);}
	}
	
	@Override
	public void onGuildUpdateNotificationLevel(GuildUpdateNotificationLevelEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateNotificationLevel(event);}
	}
	
	@Override
	public void onGuildUpdateOwner(GuildUpdateOwnerEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateOwner(event);}
	}
	
	@Override
	public void onGuildUpdateRegion(GuildUpdateRegionEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateRegion(event);}
	}
	
	@Override
	public void onGuildUpdateRulesChannel(GuildUpdateRulesChannelEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateRulesChannel(event);}
	}
	
	@Override
	public void onGuildUpdateSplash(GuildUpdateSplashEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateSplash(event);}
	}
	
	@Override
	public void onGuildUpdateSystemChannel(GuildUpdateSystemChannelEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateSystemChannel(event);}
	}
	
	@Override
	public void onGuildUpdateVanityCode(GuildUpdateVanityCodeEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateVanityCode(event);}
	}
	
	@Override
	public void onGuildUpdateVerificationLevel(GuildUpdateVerificationLevelEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildUpdateVerificationLevel(event);}
	}
	
	@Override
	public void onGuildVoiceDeafen(GuildVoiceDeafenEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildVoiceDeafen(event);}
	}
	
	@Override
	public void onGuildVoiceGuildDeafen(GuildVoiceGuildDeafenEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildVoiceGuildDeafen(event);}
	}
	
	@Override
	public void onGuildVoiceGuildMute(GuildVoiceGuildMuteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildVoiceGuildMute(event);}
	}
	
	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildVoiceJoin(event);}
	}
	
	@Override
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildVoiceLeave(event);}
	}
	
	@Override
	public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildVoiceMove(event);}
	}
	
	@Override
	public void onGuildVoiceMute(GuildVoiceMuteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildVoiceMute(event);}
	}
	
	@Override
	public void onGuildVoiceSelfDeafen(GuildVoiceSelfDeafenEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildVoiceSelfDeafen(event);}
	}
	
	@Override
	public void onGuildVoiceSelfMute(GuildVoiceSelfMuteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildVoiceSelfMute(event);}
	}
	
	@Override
	public void onGuildVoiceStream(GuildVoiceStreamEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildVoiceStream(event);}
	}
	
	@Override
	public void onGuildVoiceSuppress(GuildVoiceSuppressEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildVoiceSuppress(event);}
	}
	
	@Override
	public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onGuildVoiceUpdate(event);}
	}
	
	@Override
	public void onHttpRequest(HttpRequestEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onHttpRequest(event);}
	}
	
	@Override
	public void onMessageDelete(MessageDeleteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onMessageDelete(event);}
	}
	
	@Override
	public void onMessageBulkDelete(MessageBulkDeleteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onMessageBulkDelete(event);}
	}
	
	@Override
	public void onMessageEmbed(MessageEmbedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onMessageEmbed(event);}
	}
	
	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onMessageReactionAdd(event);}
	}
	
	@Override
	public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onMessageReactionRemove(event);}
	}
	
	@Override
	public void onMessageReactionRemoveAll(MessageReactionRemoveAllEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onMessageReactionRemoveAll(event);}
	}
	
	@Override
	public void onMessageReactionRemoveEmote(MessageReactionRemoveEmoteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onMessageReactionRemoveEmote(event);}
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onMessageReceived(event);}
	}
	
	@Override
	public void onMessageUpdate(MessageUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onMessageUpdate(event);}
	}
	
	@Override
	public void onPermissionOverrideCreate(PermissionOverrideCreateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onPermissionOverrideCreate(event);}
	}
	
	@Override
	public void onPermissionOverrideDelete(PermissionOverrideDeleteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onPermissionOverrideDelete(event);}
	}
	
	@Override
	public void onPermissionOverrideUpdate(PermissionOverrideUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onPermissionOverrideUpdate(event);}
	}
	
	@Override
	public void onPrivateChannelCreate(PrivateChannelCreateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onPrivateChannelCreate(event);}
	}
	
	@Override
	public void onPrivateChannelDelete(PrivateChannelDeleteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onPrivateChannelDelete(event);}
	}
	
	@Override
	public void onPrivateMessageDelete(PrivateMessageDeleteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onPrivateMessageDelete(event);}
	}
	
	@Override
	public void onPrivateMessageEmbed(PrivateMessageEmbedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onPrivateMessageEmbed(event);}
	}
	
	@Override
	public void onPrivateMessageReactionAdd(PrivateMessageReactionAddEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onPrivateMessageReactionAdd(event);}
	}
	
	@Override
	public void onPrivateMessageReactionRemove(PrivateMessageReactionRemoveEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onPrivateMessageReactionRemove(event);}
	}
	
	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onPrivateMessageReceived(event);}
	}
	
	@Override
	public void onPrivateMessageUpdate(PrivateMessageUpdateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onPrivateMessageUpdate(event);}
	}
	
	@Override
	public void onRawGateway(RawGatewayEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onRawGateway(event);}
	}
	
	@Override
	public void onReady(ReadyEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onReady(event);}
	}
	
	@Override
	public void onReconnect(ReconnectedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onReconnect(event);}
	}
	
	@Override
	public void onReconnected(ReconnectedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onReconnected(event);}
	}
	
	@Override
	public void onResume(ResumedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onResume(event);}
	}
	
	@Override
	public void onResumed(ResumedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onResumed(event);}
	}
	
	@Override
	public void onRoleCreate(RoleCreateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onRoleCreate(event);}
	}
	
	@Override
	public void onRoleDelete(RoleDeleteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onRoleDelete(event);}
	}
	
	@Override
	public void onRoleUpdateColor(RoleUpdateColorEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onRoleUpdateColor(event);}
	}
	
	@Override
	public void onRoleUpdateHoisted(RoleUpdateHoistedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onRoleUpdateHoisted(event);}
	}
	
	@Override
	public void onRoleUpdateMentionable(RoleUpdateMentionableEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onRoleUpdateMentionable(event);}
	}
	
	@Override
	public void onRoleUpdateName(RoleUpdateNameEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onRoleUpdateName(event);}
	}
	
	@Override
	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onRoleUpdatePermissions(event);}
	}
	
	@Override
	public void onRoleUpdatePosition(RoleUpdatePositionEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onRoleUpdatePosition(event);}
	}
	
	@Override
	public void onSelfUpdateAvatar(SelfUpdateAvatarEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onSelfUpdateAvatar(event);}
	}
	
	@Override
	public void onSelfUpdateEmail(SelfUpdateEmailEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onSelfUpdateEmail(event);}
	}
	
	@Override
	public void onSelfUpdateMFA(SelfUpdateMFAEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onSelfUpdateMFA(event);}
	}
	
	@Override
	public void onSelfUpdateName(SelfUpdateNameEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onSelfUpdateName(event);}
	}
	
	@Override
	public void onSelfUpdateVerified(SelfUpdateVerifiedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onSelfUpdateVerified(event);}
	}
	
	@Override
	public void onShutdown(ShutdownEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onShutdown(event);}
	}
	
	@Override
	public void onStatusChange(StatusChangeEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onStatusChange(event);}
	}
	
	@Override
	public void onStoreChannelCreate(StoreChannelCreateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onStoreChannelCreate(event);}
	}
	
	@Override
	public void onStoreChannelDelete(StoreChannelDeleteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onStoreChannelDelete(event);}
	}
	
	@Override
	public void onStoreChannelUpdateName(StoreChannelUpdateNameEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onStoreChannelUpdateName(event);}
	}
	
	@Override
	public void onStoreChannelUpdatePermissions(StoreChannelUpdatePermissionsEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onStoreChannelUpdatePermissions(event);}
	}
	
	@Override
	public void onStoreChannelUpdatePosition(StoreChannelUpdatePositionEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onStoreChannelUpdatePosition(event);}
	}
	
	@Override
	public void onTextChannelCreate(TextChannelCreateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onTextChannelCreate(event);}
	}
	
	@Override
	public void onTextChannelDelete(TextChannelDeleteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onTextChannelDelete(event);}
	}
	
	@Override
	public void onTextChannelUpdateName(TextChannelUpdateNameEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onTextChannelUpdateName(event);}
	}
	
	@Override
	public void onTextChannelUpdateNews(TextChannelUpdateNewsEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onTextChannelUpdateNews(event);}
	}
	
	@Override
	public void onTextChannelUpdateNSFW(TextChannelUpdateNSFWEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onTextChannelUpdateNSFW(event);}
	}
	
	@Override
	public void onTextChannelUpdateParent(TextChannelUpdateParentEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onTextChannelUpdateParent(event);}
	}
	
	@Override
	public void onTextChannelUpdatePermissions(TextChannelUpdatePermissionsEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onTextChannelUpdatePermissions(event);}
	}
	
	@Override
	public void onTextChannelUpdatePosition(TextChannelUpdatePositionEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onTextChannelUpdatePosition(event);}
	}
	
	@Override
	public void onTextChannelUpdateSlowmode(TextChannelUpdateSlowmodeEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onTextChannelUpdateSlowmode(event);}
	}
	
	@Override
	public void onTextChannelUpdateTopic(TextChannelUpdateTopicEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onTextChannelUpdateTopic(event);}
	}
	
	@Override
	public void onUnavailableGuildJoined(UnavailableGuildJoinedEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onUnavailableGuildJoined(event);}
	}
	
	@Override
	public void onUnavailableGuildLeave(UnavailableGuildLeaveEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onUnavailableGuildLeave(event);}
	}
	
	@Override
	public void onUserActivityEnd(UserActivityEndEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onUserActivityEnd(event);}
	}
	
	@Override
	public void onUserActivityStart(UserActivityStartEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onUserActivityStart(event);}
	}
	
	@Override
	public void onUserTyping(UserTypingEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onUserTyping(event);}
	}
	
	@Override
	public void onUserUpdateActivities(UserUpdateActivitiesEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onUserUpdateActivities(event);}
	}
	
	@Override
	public void onUserUpdateActivityOrder(UserUpdateActivityOrderEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onUserUpdateActivityOrder(event);}
	}
	
	@Override
	public void onUserUpdateAvatar(UserUpdateAvatarEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onUserUpdateAvatar(event);}
	}
	
	@Override
	public void onUserUpdateDiscriminator(UserUpdateDiscriminatorEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onUserUpdateDiscriminator(event);}
	}
	
	@Override
	public void onUserUpdateFlags(UserUpdateFlagsEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onUserUpdateFlags(event);}
	}
	
	@Override
	public void onUserUpdateName(UserUpdateNameEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onUserUpdateName(event);}
	}
	
	@Override
	public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onUserUpdateOnlineStatus(event);}
	}
	
	@Override
	public void onVoiceChannelCreate(VoiceChannelCreateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onVoiceChannelCreate(event);}
	}
	
	@Override
	public void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onVoiceChannelDelete(event);}
	}
	
	@Override
	public void onVoiceChannelUpdateBitrate(VoiceChannelUpdateBitrateEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onVoiceChannelUpdateBitrate(event);}
	}
	
	@Override
	public void onVoiceChannelUpdateName(VoiceChannelUpdateNameEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onVoiceChannelUpdateName(event);}
	}
	
	@Override
	public void onVoiceChannelUpdateParent(VoiceChannelUpdateParentEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onVoiceChannelUpdateParent(event);}
	}
	
	@Override
	public void onVoiceChannelUpdatePermissions(VoiceChannelUpdatePermissionsEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onVoiceChannelUpdatePermissions(event);}
	}
	
	@Override
	public void onVoiceChannelUpdatePosition(VoiceChannelUpdatePositionEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onVoiceChannelUpdatePosition(event);}
	}
	
	@Override
	public void onVoiceChannelUpdateUserLimit(VoiceChannelUpdateUserLimitEvent event) {
		
		for(Module module : Demi.i.getActiveModules()) {module.onVoiceChannelUpdateUserLimit(event);}
	}
}
