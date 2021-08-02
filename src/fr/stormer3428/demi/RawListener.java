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
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onCategoryCreate(event);}}).start();}
	}

	@Override
	public void onCategoryDelete(CategoryDeleteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onCategoryDelete(event);}}).start();}
	}

	@Override
	public void onCategoryUpdateName(CategoryUpdateNameEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onCategoryUpdateName(event);}}).start();}
	}

	@Override
	public void onCategoryUpdatePermissions(CategoryUpdatePermissionsEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onCategoryUpdatePermissions(event);}}).start();}
	}

	@Override
	public void onCategoryUpdatePosition(CategoryUpdatePositionEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onCategoryUpdatePosition(event);}}).start();}
	}

	@Override
	public void onDisconnect(DisconnectEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onDisconnect(event);}}).start();}
	}

	@Override
	public void onEmoteAdded(EmoteAddedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onEmoteAdded(event);}}).start();}
	}

	@Override
	public void onEmoteRemoved(EmoteRemovedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onEmoteRemoved(event);}}).start();}
	}

	@Override
	public void onEmoteUpdateName(EmoteUpdateNameEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onEmoteUpdateName(event);}}).start();}
	}

	@Override
	public void onEmoteUpdateRoles(EmoteUpdateRolesEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onEmoteUpdateRoles(event);}}).start();}
	}

	@Override
	public void onException(ExceptionEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onException(event);}}).start();}
	}

	@Override
	public void onGatewayPing(GatewayPingEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGatewayPing(event);}}).start();}
	}

	@Override
	public void onGenericCategory(GenericCategoryEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericCategory(event);}}).start();}
	}

	@Override
	public void onGenericCategoryUpdate(GenericCategoryUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericCategoryUpdate(event);}}).start();}
	}

	@Override
	public void onGenericEmote(GenericEmoteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericEmote(event);}}).start();}
	}

	@Override
	public void onGenericEmoteUpdate(GenericEmoteUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericEmoteUpdate(event);}}).start();}
	}

	@Override
	public void onGenericEvent(GenericEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericEvent(event);}}).start();}
	}

	@Override
	public void onGenericGuild(GenericGuildEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuild(event);}}).start();}
	}

	@Override
	public void onGenericGuildInvite(GenericGuildInviteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildInvite(event);}}).start();}
	}

	@Override
	public void onGenericGuildMember(GenericGuildMemberEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildMember(event);}}).start();}
	}

	@Override
	public void onGenericGuildMemberUpdate(GenericGuildMemberUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildMemberUpdate(event);}}).start();}
	}

	@Override
	public void onGenericGuildMessage(GenericGuildMessageEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildMessage(event);}}).start();}
	}

	@Override
	public void onGenericGuildMessageReaction(GenericGuildMessageReactionEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildMessageReaction(event);}}).start();}
	}

	@Override
	public void onGenericGuildUpdate(GenericGuildUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildUpdate(event);}}).start();}
	}

	@Override
	public void onGenericGuildVoice(GenericGuildVoiceEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildVoice(event);}}).start();}
	}

	@Override
	public void onGenericMessage(GenericMessageEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericMessage(event);}}).start();}
	}

	@Override
	public void onGenericMessageReaction(GenericMessageReactionEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericMessageReaction(event);}}).start();}
	}

	@Override
	public void onGenericPermissionOverride(GenericPermissionOverrideEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericPermissionOverride(event);}}).start();}
	}

	@Override
	public void onGenericPrivateMessage(GenericPrivateMessageEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericPrivateMessage(event);}}).start();}
	}

	@Override
	public void onGenericPrivateMessageReaction(GenericPrivateMessageReactionEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericPrivateMessageReaction(event);}}).start();}
	}

	@Override
	public void onGenericRole(GenericRoleEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericRole(event);}}).start();}
	}

	@Override
	public void onGenericRoleUpdate(GenericRoleUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericRoleUpdate(event);}}).start();}
	}

	@Override
	public void onGenericSelfUpdate(GenericSelfUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericSelfUpdate(event);}}).start();}
	}

	@Override
	public void onGenericStoreChannel(GenericStoreChannelEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericStoreChannel(event);}}).start();}
	}

	@Override
	public void onGenericStoreChannelUpdate(GenericStoreChannelUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericStoreChannelUpdate(event);}}).start();}
	}

	@Override
	public void onGenericTextChannel(GenericTextChannelEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericTextChannel(event);}}).start();}
	}

	@Override
	public void onGenericTextChannelUpdate(GenericTextChannelUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericTextChannelUpdate(event);}}).start();}
	}

	@Override
	public void onGenericUpdate(UpdateEvent<?, ?> event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericUpdate(event);}}).start();}
	}

	@Override
	public void onGenericUser(GenericUserEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericUser(event);}}).start();}
	}

	@Override
	public void onGenericUserPresence(GenericUserPresenceEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericUserPresence(event);}}).start();}
	}

	@Override
	public void onGenericVoiceChannel(GenericVoiceChannelEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericVoiceChannel(event);}}).start();}
	}

	@Override
	public void onGenericVoiceChannelUpdate(GenericVoiceChannelUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericVoiceChannelUpdate(event);}}).start();}
	}

	@Override
	public void onGuildAvailable(GuildAvailableEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildAvailable(event);}}).start();}
	}

	@Override
	public void onGuildBan(GuildBanEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildBan(event);}}).start();}
	}

	@Override
	public void onGuildInviteCreate(GuildInviteCreateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildInviteCreate(event);}}).start();}
	}

	@Override
	public void onGuildInviteDelete(GuildInviteDeleteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildInviteDelete(event);}}).start();}
	}

	@Override
	public void onGuildJoin(GuildJoinEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildJoin(event);}}).start();}
	}

	@Override
	public void onGuildLeave(GuildLeaveEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildLeave(event);}}).start();}
	}

	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberJoin(event);}}).start();}
	}

	@Override
	public void onGuildMemberLeave(GuildMemberLeaveEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberLeave(event);}}).start();}
	}

	@Override
	public void onGuildMemberRemove(GuildMemberRemoveEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberRemove(event);}}).start();}
	}

	@Override
	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberRoleAdd(event);}}).start();}
	}

	@Override
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberRoleRemove(event);}}).start();}
	}

	@Override
	public void onGuildMemberUpdate(GuildMemberUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdate(event);}}).start();}
	}

	@Override
	public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdateBoostTime(event);}}).start();}
	}

	@Override
	public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdateNickname(event);}}).start();}
	}

	@Override
	public void onGuildMemberUpdatePending(GuildMemberUpdatePendingEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdatePending(event);}}).start();}
	}

	@Override
	public void onGuildMessageDelete(GuildMessageDeleteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageDelete(event);}}).start();}
	}

	@Override
	public void onGuildMessageEmbed(GuildMessageEmbedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageEmbed(event);}}).start();}
	}

	@Override
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageReactionAdd(event);}}).start();}
	}

	@Override
	public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageReactionRemove(event);}}).start();}
	}

	@Override
	public void onGuildMessageReactionRemoveAll(GuildMessageReactionRemoveAllEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageReactionRemoveAll(event);}}).start();}
	}

	@Override
	public void onGuildMessageReactionRemoveEmote(GuildMessageReactionRemoveEmoteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageReactionRemoveEmote(event);}}).start();}
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageReceived(event);}}).start();}
	}

	@Override
	public void onGuildMessageUpdate(GuildMessageUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageUpdate(event);}}).start();}
	}

	@Override
	public void onGuildReady(GuildReadyEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildReady(event);}}).start();}
	}

	@Override
	public void onGuildTimeout(GuildTimeoutEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildTimeout(event);}}).start();}
	}

	@Override
	public void onGuildUnavailable(GuildUnavailableEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUnavailable(event);}}).start();}
	}

	@Override
	public void onGuildUnban(GuildUnbanEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUnban(event);}}).start();}
	}

	@Override
	public void onGuildUpdateAfkChannel(GuildUpdateAfkChannelEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateAfkChannel(event);}}).start();}
	}

	@Override
	public void onGuildUpdateAfkTimeout(GuildUpdateAfkTimeoutEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateAfkTimeout(event);}}).start();}
	}

	@Override
	public void onGuildUpdateBanner(GuildUpdateBannerEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateBanner(event);}}).start();}
	}

	@Override
	public void onGuildUpdateBoostCount(GuildUpdateBoostCountEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateBoostCount(event);}}).start();}
	}

	@Override
	public void onGuildUpdateBoostTier(GuildUpdateBoostTierEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateBoostTier(event);}}).start();}
	}

	@Override
	public void onGuildUpdateCommunityUpdatesChannel(GuildUpdateCommunityUpdatesChannelEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateCommunityUpdatesChannel(event);}}).start();}
	}

	@Override
	public void onGuildUpdateDescription(GuildUpdateDescriptionEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateDescription(event);}}).start();}
	}

	@Override
	public void onGuildUpdateExplicitContentLevel(GuildUpdateExplicitContentLevelEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateExplicitContentLevel(event);}}).start();}
	}

	@Override
	public void onGuildUpdateFeatures(GuildUpdateFeaturesEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateFeatures(event);}}).start();}
	}

	@Override
	public void onGuildUpdateIcon(GuildUpdateIconEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateIcon(event);}}).start();}
	}

	@Override
	public void onGuildUpdateLocale(GuildUpdateLocaleEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateLocale(event);}}).start();}
	}

	@Override
	public void onGuildUpdateMaxMembers(GuildUpdateMaxMembersEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateMaxMembers(event);}}).start();}
	}

	@Override
	public void onGuildUpdateMaxPresences(GuildUpdateMaxPresencesEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateMaxPresences(event);}}).start();}
	}

	@Override
	public void onGuildUpdateMFALevel(GuildUpdateMFALevelEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateMFALevel(event);}}).start();}
	}

	@Override
	public void onGuildUpdateName(GuildUpdateNameEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateName(event);}}).start();}
	}

	@Override
	public void onGuildUpdateNotificationLevel(GuildUpdateNotificationLevelEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateNotificationLevel(event);}}).start();}
	}

	@Override
	public void onGuildUpdateOwner(GuildUpdateOwnerEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateOwner(event);}}).start();}
	}

	@Override
	public void onGuildUpdateRegion(GuildUpdateRegionEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateRegion(event);}}).start();}
	}

	@Override
	public void onGuildUpdateRulesChannel(GuildUpdateRulesChannelEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateRulesChannel(event);}}).start();}
	}

	@Override
	public void onGuildUpdateSplash(GuildUpdateSplashEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateSplash(event);}}).start();}
	}

	@Override
	public void onGuildUpdateSystemChannel(GuildUpdateSystemChannelEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateSystemChannel(event);}}).start();}
	}

	@Override
	public void onGuildUpdateVanityCode(GuildUpdateVanityCodeEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateVanityCode(event);}}).start();}
	}

	@Override
	public void onGuildUpdateVerificationLevel(GuildUpdateVerificationLevelEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateVerificationLevel(event);}}).start();}
	}

	@Override
	public void onGuildVoiceDeafen(GuildVoiceDeafenEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceDeafen(event);}}).start();}
	}

	@Override
	public void onGuildVoiceGuildDeafen(GuildVoiceGuildDeafenEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceGuildDeafen(event);}}).start();}
	}

	@Override
	public void onGuildVoiceGuildMute(GuildVoiceGuildMuteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceGuildMute(event);}}).start();}
	}

	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceJoin(event);}}).start();}
	}

	@Override
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceLeave(event);}}).start();}
	}

	@Override
	public void onGuildVoiceMove(GuildVoiceMoveEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceMove(event);}}).start();}
	}

	@Override
	public void onGuildVoiceMute(GuildVoiceMuteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceMute(event);}}).start();}
	}

	@Override
	public void onGuildVoiceSelfDeafen(GuildVoiceSelfDeafenEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceSelfDeafen(event);}}).start();}
	}

	@Override
	public void onGuildVoiceSelfMute(GuildVoiceSelfMuteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceSelfMute(event);}}).start();}
	}

	@Override
	public void onGuildVoiceStream(GuildVoiceStreamEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceStream(event);}}).start();}
	}

	@Override
	public void onGuildVoiceSuppress(GuildVoiceSuppressEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceSuppress(event);}}).start();}
	}

	@Override
	public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceUpdate(event);}}).start();}
	}

	@Override
	public void onHttpRequest(HttpRequestEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onHttpRequest(event);}}).start();}
	}

	@Override
	public void onMessageDelete(MessageDeleteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageDelete(event);}}).start();}
	}

	@Override
	public void onMessageBulkDelete(MessageBulkDeleteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageBulkDelete(event);}}).start();}
	}

	@Override
	public void onMessageEmbed(MessageEmbedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageEmbed(event);}}).start();}
	}

	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReactionAdd(event);}}).start();}
	}

	@Override
	public void onMessageReactionRemove(MessageReactionRemoveEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReactionRemove(event);}}).start();}
	}

	@Override
	public void onMessageReactionRemoveAll(MessageReactionRemoveAllEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReactionRemoveAll(event);}}).start();}
	}

	@Override
	public void onMessageReactionRemoveEmote(MessageReactionRemoveEmoteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReactionRemoveEmote(event);}}).start();}
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReceived(event);}}).start();}
	}

	@Override
	public void onMessageUpdate(MessageUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageUpdate(event);}}).start();}
	}

	@Override
	public void onPermissionOverrideCreate(PermissionOverrideCreateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPermissionOverrideCreate(event);}}).start();}
	}

	@Override
	public void onPermissionOverrideDelete(PermissionOverrideDeleteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPermissionOverrideDelete(event);}}).start();}
	}

	@Override
	public void onPermissionOverrideUpdate(PermissionOverrideUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPermissionOverrideUpdate(event);}}).start();}
	}

	@Override
	public void onPrivateChannelCreate(PrivateChannelCreateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateChannelCreate(event);}}).start();}
	}

	@Override
	public void onPrivateChannelDelete(PrivateChannelDeleteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateChannelDelete(event);}}).start();}
	}

	@Override
	public void onPrivateMessageDelete(PrivateMessageDeleteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateMessageDelete(event);}}).start();}
	}

	@Override
	public void onPrivateMessageEmbed(PrivateMessageEmbedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateMessageEmbed(event);}}).start();}
	}

	@Override
	public void onPrivateMessageReactionAdd(PrivateMessageReactionAddEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateMessageReactionAdd(event);}}).start();}
	}

	@Override
	public void onPrivateMessageReactionRemove(PrivateMessageReactionRemoveEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateMessageReactionRemove(event);}}).start();}
	}

	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateMessageReceived(event);}}).start();}
	}

	@Override
	public void onPrivateMessageUpdate(PrivateMessageUpdateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateMessageUpdate(event);}}).start();}
	}

	@Override
	public void onRawGateway(RawGatewayEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRawGateway(event);}}).start();}
	}

	@Override
	public void onReady(ReadyEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onReady(event);}}).start();}
	}

	@Override
	public void onReconnect(ReconnectedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onReconnect(event);}}).start();}
	}

	@Override
	public void onReconnected(ReconnectedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onReconnected(event);}}).start();}
	}

	@Override
	public void onResume(ResumedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onResume(event);}}).start();}
	}

	@Override
	public void onResumed(ResumedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onResumed(event);}}).start();}
	}

	@Override
	public void onRoleCreate(RoleCreateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleCreate(event);}}).start();}
	}

	@Override
	public void onRoleDelete(RoleDeleteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleDelete(event);}}).start();}
	}

	@Override
	public void onRoleUpdateColor(RoleUpdateColorEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateColor(event);}}).start();}
	}

	@Override
	public void onRoleUpdateHoisted(RoleUpdateHoistedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateHoisted(event);}}).start();}
	}

	@Override
	public void onRoleUpdateMentionable(RoleUpdateMentionableEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateMentionable(event);}}).start();}
	}

	@Override
	public void onRoleUpdateName(RoleUpdateNameEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateName(event);}}).start();}
	}

	@Override
	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdatePermissions(event);}}).start();}
	}

	@Override
	public void onRoleUpdatePosition(RoleUpdatePositionEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdatePosition(event);}}).start();}
	}

	@Override
	public void onSelfUpdateAvatar(SelfUpdateAvatarEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateAvatar(event);}}).start();}
	}

	@Override
	public void onSelfUpdateEmail(SelfUpdateEmailEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateEmail(event);}}).start();}
	}

	@Override
	public void onSelfUpdateMFA(SelfUpdateMFAEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateMFA(event);}}).start();}
	}

	@Override
	public void onSelfUpdateName(SelfUpdateNameEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateName(event);}}).start();}
	}

	@Override
	public void onSelfUpdateVerified(SelfUpdateVerifiedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateVerified(event);}}).start();}
	}

	@Override
	public void onShutdown(ShutdownEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onShutdown(event);}}).start();}
	}

	@Override
	public void onStatusChange(StatusChangeEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStatusChange(event);}}).start();}
	}

	@Override
	public void onStoreChannelCreate(StoreChannelCreateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStoreChannelCreate(event);}}).start();}
	}

	@Override
	public void onStoreChannelDelete(StoreChannelDeleteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStoreChannelDelete(event);}}).start();}
	}

	@Override
	public void onStoreChannelUpdateName(StoreChannelUpdateNameEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStoreChannelUpdateName(event);}}).start();}
	}

	@Override
	public void onStoreChannelUpdatePermissions(StoreChannelUpdatePermissionsEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStoreChannelUpdatePermissions(event);}}).start();}
	}

	@Override
	public void onStoreChannelUpdatePosition(StoreChannelUpdatePositionEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStoreChannelUpdatePosition(event);}}).start();}
	}

	@Override
	public void onTextChannelCreate(TextChannelCreateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelCreate(event);}}).start();}
	}

	@Override
	public void onTextChannelDelete(TextChannelDeleteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelDelete(event);}}).start();}
	}

	@Override
	public void onTextChannelUpdateName(TextChannelUpdateNameEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdateName(event);}}).start();}
	}

	@Override
	public void onTextChannelUpdateNews(TextChannelUpdateNewsEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdateNews(event);}}).start();}
	}

	@Override
	public void onTextChannelUpdateNSFW(TextChannelUpdateNSFWEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdateNSFW(event);}}).start();}
	}

	@Override
	public void onTextChannelUpdateParent(TextChannelUpdateParentEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdateParent(event);}}).start();}
	}

	@Override
	public void onTextChannelUpdatePermissions(TextChannelUpdatePermissionsEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdatePermissions(event);}}).start();}
	}

	@Override
	public void onTextChannelUpdatePosition(TextChannelUpdatePositionEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdatePosition(event);}}).start();}
	}

	@Override
	public void onTextChannelUpdateSlowmode(TextChannelUpdateSlowmodeEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdateSlowmode(event);}}).start();}
	}

	@Override
	public void onTextChannelUpdateTopic(TextChannelUpdateTopicEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdateTopic(event);}}).start();}
	}

	@Override
	public void onUnavailableGuildJoined(UnavailableGuildJoinedEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUnavailableGuildJoined(event);}}).start();}
	}

	@Override
	public void onUnavailableGuildLeave(UnavailableGuildLeaveEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUnavailableGuildLeave(event);}}).start();}
	}

	@Override
	public void onUserActivityEnd(UserActivityEndEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserActivityEnd(event);}}).start();}
	}

	@Override
	public void onUserActivityStart(UserActivityStartEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserActivityStart(event);}}).start();}
	}

	@Override
	public void onUserTyping(UserTypingEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserTyping(event);}}).start();}
	}

	@Override
	public void onUserUpdateActivities(UserUpdateActivitiesEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateActivities(event);}}).start();}
	}

	@Override
	public void onUserUpdateActivityOrder(UserUpdateActivityOrderEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateActivityOrder(event);}}).start();}
	}

	@Override
	public void onUserUpdateAvatar(UserUpdateAvatarEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateAvatar(event);}}).start();}
	}

	@Override
	public void onUserUpdateDiscriminator(UserUpdateDiscriminatorEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateDiscriminator(event);}}).start();}
	}

	@Override
	public void onUserUpdateFlags(UserUpdateFlagsEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateFlags(event);}}).start();}
	}

	@Override
	public void onUserUpdateName(UserUpdateNameEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateName(event);}}).start();}
	}

	@Override
	public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateOnlineStatus(event);}}).start();}
	}

	@Override
	public void onVoiceChannelCreate(VoiceChannelCreateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelCreate(event);}}).start();}
	}

	@Override
	public void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelDelete(event);}}).start();}
	}

	@Override
	public void onVoiceChannelUpdateBitrate(VoiceChannelUpdateBitrateEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelUpdateBitrate(event);}}).start();}
	}

	@Override
	public void onVoiceChannelUpdateName(VoiceChannelUpdateNameEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelUpdateName(event);}}).start();}
	}

	@Override
	public void onVoiceChannelUpdateParent(VoiceChannelUpdateParentEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelUpdateParent(event);}}).start();}
	}

	@Override
	public void onVoiceChannelUpdatePermissions(VoiceChannelUpdatePermissionsEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelUpdatePermissions(event);}}).start();}
	}

	@Override
	public void onVoiceChannelUpdatePosition(VoiceChannelUpdatePositionEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelUpdatePosition(event);}}).start();}
	}

	@Override
	public void onVoiceChannelUpdateUserLimit(VoiceChannelUpdateUserLimitEvent event) {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelUpdateUserLimit(event);}}).start();}
	}
}
