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

@SuppressWarnings({"deprecation"})
public class RawListener extends ListenerAdapter{

	@Override
	public void onCategoryCreate(CategoryCreateEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onCategoryCreate(event);}}).start();}}catch (Exception e) {}
	
		}

	@Override
	public void onCategoryDelete(CategoryDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onCategoryDelete(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onCategoryUpdateName(CategoryUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onCategoryUpdateName(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onCategoryUpdatePermissions(CategoryUpdatePermissionsEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onCategoryUpdatePermissions(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onCategoryUpdatePosition(CategoryUpdatePositionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onCategoryUpdatePosition(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onDisconnect(DisconnectEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onDisconnect(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onEmoteAdded(EmoteAddedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onEmoteAdded(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onEmoteRemoved(EmoteRemovedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onEmoteRemoved(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onEmoteUpdateName(EmoteUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onEmoteUpdateName(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onEmoteUpdateRoles(EmoteUpdateRolesEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onEmoteUpdateRoles(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onException(ExceptionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onException(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGatewayPing(GatewayPingEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGatewayPing(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericCategory(GenericCategoryEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericCategory(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericCategoryUpdate(GenericCategoryUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericCategoryUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericEmote(GenericEmoteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericEmote(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericEmoteUpdate(GenericEmoteUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericEmoteUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericEvent(GenericEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericEvent(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericGuild(GenericGuildEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuild(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericGuildInvite(GenericGuildInviteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildInvite(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericGuildMember(GenericGuildMemberEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildMember(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericGuildMemberUpdate(GenericGuildMemberUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildMemberUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericGuildMessage(GenericGuildMessageEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildMessage(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericGuildMessageReaction(GenericGuildMessageReactionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildMessageReaction(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericGuildUpdate(GenericGuildUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericGuildVoice(GenericGuildVoiceEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildVoice(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericMessage(GenericMessageEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericMessage(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericMessageReaction(GenericMessageReactionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericMessageReaction(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericPermissionOverride(GenericPermissionOverrideEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericPermissionOverride(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericPrivateMessage(GenericPrivateMessageEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericPrivateMessage(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericPrivateMessageReaction(GenericPrivateMessageReactionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericPrivateMessageReaction(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericRole(GenericRoleEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericRole(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericRoleUpdate(GenericRoleUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericRoleUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericSelfUpdate(GenericSelfUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericSelfUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericStoreChannel(GenericStoreChannelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericStoreChannel(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericStoreChannelUpdate(GenericStoreChannelUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericStoreChannelUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericTextChannel(GenericTextChannelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericTextChannel(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericTextChannelUpdate(GenericTextChannelUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericTextChannelUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericUpdate(UpdateEvent<?, ?> event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericUser(GenericUserEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericUser(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericUserPresence(GenericUserPresenceEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericUserPresence(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericVoiceChannel(GenericVoiceChannelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericVoiceChannel(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGenericVoiceChannelUpdate(GenericVoiceChannelUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericVoiceChannelUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildAvailable(GuildAvailableEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildAvailable(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildBan(GuildBanEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildBan(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildInviteCreate(GuildInviteCreateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildInviteCreate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildInviteDelete(GuildInviteDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildInviteDelete(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildJoin(GuildJoinEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildJoin(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildLeave(GuildLeaveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildLeave(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberJoin(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMemberLeave(GuildMemberLeaveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberLeave(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMemberRemove(GuildMemberRemoveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberRemove(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberRoleAdd(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberRoleRemove(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMemberUpdate(GuildMemberUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdateBoostTime(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdateNickname(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMemberUpdatePending(GuildMemberUpdatePendingEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdatePending(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMessageDelete(GuildMessageDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageDelete(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMessageEmbed(GuildMessageEmbedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageEmbed(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageReactionAdd(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageReactionRemove(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMessageReactionRemoveAll(GuildMessageReactionRemoveAllEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageReactionRemoveAll(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMessageReactionRemoveEmote(GuildMessageReactionRemoveEmoteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageReactionRemoveEmote(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageReceived(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildMessageUpdate(GuildMessageUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMessageUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildReady(GuildReadyEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildReady(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildTimeout(GuildTimeoutEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildTimeout(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUnavailable(GuildUnavailableEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUnavailable(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUnban(GuildUnbanEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUnban(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateAfkChannel(GuildUpdateAfkChannelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateAfkChannel(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateAfkTimeout(GuildUpdateAfkTimeoutEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateAfkTimeout(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateBanner(GuildUpdateBannerEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateBanner(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateBoostCount(GuildUpdateBoostCountEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateBoostCount(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateBoostTier(GuildUpdateBoostTierEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateBoostTier(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateCommunityUpdatesChannel(GuildUpdateCommunityUpdatesChannelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateCommunityUpdatesChannel(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateDescription(GuildUpdateDescriptionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateDescription(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateExplicitContentLevel(GuildUpdateExplicitContentLevelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateExplicitContentLevel(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateFeatures(GuildUpdateFeaturesEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateFeatures(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateIcon(GuildUpdateIconEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateIcon(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateLocale(GuildUpdateLocaleEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateLocale(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateMaxMembers(GuildUpdateMaxMembersEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateMaxMembers(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateMaxPresences(GuildUpdateMaxPresencesEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateMaxPresences(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateMFALevel(GuildUpdateMFALevelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateMFALevel(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateName(GuildUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateName(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateNotificationLevel(GuildUpdateNotificationLevelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateNotificationLevel(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateOwner(GuildUpdateOwnerEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateOwner(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateRegion(GuildUpdateRegionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateRegion(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateRulesChannel(GuildUpdateRulesChannelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateRulesChannel(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateSplash(GuildUpdateSplashEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateSplash(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateSystemChannel(GuildUpdateSystemChannelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateSystemChannel(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateVanityCode(GuildUpdateVanityCodeEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateVanityCode(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildUpdateVerificationLevel(GuildUpdateVerificationLevelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateVerificationLevel(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildVoiceDeafen(GuildVoiceDeafenEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceDeafen(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildVoiceGuildDeafen(GuildVoiceGuildDeafenEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceGuildDeafen(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildVoiceGuildMute(GuildVoiceGuildMuteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceGuildMute(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceJoin(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceLeave(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildVoiceMove(GuildVoiceMoveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceMove(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildVoiceMute(GuildVoiceMuteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceMute(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildVoiceSelfDeafen(GuildVoiceSelfDeafenEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceSelfDeafen(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildVoiceSelfMute(GuildVoiceSelfMuteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceSelfMute(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildVoiceStream(GuildVoiceStreamEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceStream(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildVoiceSuppress(GuildVoiceSuppressEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceSuppress(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onHttpRequest(HttpRequestEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onHttpRequest(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onMessageDelete(MessageDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageDelete(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onMessageBulkDelete(MessageBulkDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageBulkDelete(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onMessageEmbed(MessageEmbedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageEmbed(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReactionAdd(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onMessageReactionRemove(MessageReactionRemoveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReactionRemove(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onMessageReactionRemoveAll(MessageReactionRemoveAllEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReactionRemoveAll(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onMessageReactionRemoveEmote(MessageReactionRemoveEmoteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReactionRemoveEmote(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReceived(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onMessageUpdate(MessageUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onPermissionOverrideCreate(PermissionOverrideCreateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPermissionOverrideCreate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onPermissionOverrideDelete(PermissionOverrideDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPermissionOverrideDelete(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onPermissionOverrideUpdate(PermissionOverrideUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPermissionOverrideUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onPrivateChannelCreate(PrivateChannelCreateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateChannelCreate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onPrivateChannelDelete(PrivateChannelDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateChannelDelete(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onPrivateMessageDelete(PrivateMessageDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateMessageDelete(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onPrivateMessageEmbed(PrivateMessageEmbedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateMessageEmbed(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onPrivateMessageReactionAdd(PrivateMessageReactionAddEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateMessageReactionAdd(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onPrivateMessageReactionRemove(PrivateMessageReactionRemoveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateMessageReactionRemove(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateMessageReceived(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onPrivateMessageUpdate(PrivateMessageUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPrivateMessageUpdate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onRawGateway(RawGatewayEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRawGateway(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onReady(ReadyEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onReady(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onReconnect(ReconnectedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onReconnect(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onReconnected(ReconnectedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onReconnected(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onResume(ResumedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onResume(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onResumed(ResumedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onResumed(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onRoleCreate(RoleCreateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleCreate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onRoleDelete(RoleDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleDelete(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onRoleUpdateColor(RoleUpdateColorEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateColor(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onRoleUpdateHoisted(RoleUpdateHoistedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateHoisted(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onRoleUpdateMentionable(RoleUpdateMentionableEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateMentionable(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onRoleUpdateName(RoleUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateName(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdatePermissions(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onRoleUpdatePosition(RoleUpdatePositionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdatePosition(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onSelfUpdateAvatar(SelfUpdateAvatarEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateAvatar(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onSelfUpdateEmail(SelfUpdateEmailEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateEmail(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onSelfUpdateMFA(SelfUpdateMFAEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateMFA(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onSelfUpdateName(SelfUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateName(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onSelfUpdateVerified(SelfUpdateVerifiedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateVerified(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onShutdown(ShutdownEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onShutdown(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onStatusChange(StatusChangeEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStatusChange(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onStoreChannelCreate(StoreChannelCreateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStoreChannelCreate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onStoreChannelDelete(StoreChannelDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStoreChannelDelete(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onStoreChannelUpdateName(StoreChannelUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStoreChannelUpdateName(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onStoreChannelUpdatePermissions(StoreChannelUpdatePermissionsEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStoreChannelUpdatePermissions(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onStoreChannelUpdatePosition(StoreChannelUpdatePositionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStoreChannelUpdatePosition(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onTextChannelCreate(TextChannelCreateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelCreate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onTextChannelDelete(TextChannelDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelDelete(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onTextChannelUpdateName(TextChannelUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdateName(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onTextChannelUpdateNews(TextChannelUpdateNewsEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdateNews(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onTextChannelUpdateNSFW(TextChannelUpdateNSFWEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdateNSFW(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onTextChannelUpdateParent(TextChannelUpdateParentEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdateParent(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onTextChannelUpdatePermissions(TextChannelUpdatePermissionsEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdatePermissions(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onTextChannelUpdatePosition(TextChannelUpdatePositionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdatePosition(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onTextChannelUpdateSlowmode(TextChannelUpdateSlowmodeEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdateSlowmode(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onTextChannelUpdateTopic(TextChannelUpdateTopicEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onTextChannelUpdateTopic(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onUnavailableGuildJoined(UnavailableGuildJoinedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUnavailableGuildJoined(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onUnavailableGuildLeave(UnavailableGuildLeaveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUnavailableGuildLeave(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onUserActivityEnd(UserActivityEndEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserActivityEnd(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onUserActivityStart(UserActivityStartEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserActivityStart(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onUserTyping(UserTypingEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserTyping(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onUserUpdateActivities(UserUpdateActivitiesEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateActivities(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onUserUpdateActivityOrder(UserUpdateActivityOrderEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateActivityOrder(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onUserUpdateAvatar(UserUpdateAvatarEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateAvatar(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onUserUpdateDiscriminator(UserUpdateDiscriminatorEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateDiscriminator(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onUserUpdateFlags(UserUpdateFlagsEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateFlags(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onUserUpdateName(UserUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateName(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateOnlineStatus(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onVoiceChannelCreate(VoiceChannelCreateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelCreate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelDelete(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onVoiceChannelUpdateBitrate(VoiceChannelUpdateBitrateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelUpdateBitrate(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onVoiceChannelUpdateName(VoiceChannelUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelUpdateName(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onVoiceChannelUpdateParent(VoiceChannelUpdateParentEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelUpdateParent(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onVoiceChannelUpdatePermissions(VoiceChannelUpdatePermissionsEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelUpdatePermissions(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onVoiceChannelUpdatePosition(VoiceChannelUpdatePositionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelUpdatePosition(event);}}).start();}}catch (Exception e) {}
	}

	@Override
	public void onVoiceChannelUpdateUserLimit(VoiceChannelUpdateUserLimitEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onVoiceChannelUpdateUserLimit(event);}}).start();}}catch (Exception e) {}
	}
}
