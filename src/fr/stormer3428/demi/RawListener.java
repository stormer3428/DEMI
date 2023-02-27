package fr.stormer3428.demi;

import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.events.RawGatewayEvent;
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
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RawListener extends ListenerAdapter{



	@Override
	public void onException(ExceptionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onException(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGatewayPing(GatewayPingEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGatewayPing(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGenericGuild(GenericGuildEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuild(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGenericGuildInvite(GenericGuildInviteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildInvite(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGenericGuildMember(GenericGuildMemberEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildMember(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onGenericGuildMemberUpdate(GenericGuildMemberUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildMemberUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onGenericGuildUpdate(GenericGuildUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGenericGuildVoice(GenericGuildVoiceEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildVoice(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGenericMessage(GenericMessageEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericMessage(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGenericMessageReaction(GenericMessageReactionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericMessageReaction(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGenericPermissionOverride(GenericPermissionOverrideEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericPermissionOverride(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGenericRole(GenericRoleEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericRole(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onGenericRoleUpdate(GenericRoleUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericRoleUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onGenericSelfUpdate(GenericSelfUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericSelfUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGenericUpdate(UpdateEvent<?, ?> event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGenericUser(GenericUserEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericUser(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGenericUserPresence(GenericUserPresenceEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericUserPresence(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildAvailable(GuildAvailableEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildAvailable(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildBan(GuildBanEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildBan(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildInviteCreate(GuildInviteCreateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildInviteCreate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildInviteDelete(GuildInviteDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildInviteDelete(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildJoin(GuildJoinEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildJoin(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildLeave(GuildLeaveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildLeave(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberJoin(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildMemberRemove(GuildMemberRemoveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberRemove(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberRoleAdd(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberRoleRemove(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildMemberUpdate(GuildMemberUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdateBoostTime(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdateNickname(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildMemberUpdatePending(GuildMemberUpdatePendingEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdatePending(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildReady(GuildReadyEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildReady(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildTimeout(GuildTimeoutEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildTimeout(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUnavailable(GuildUnavailableEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUnavailable(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUnban(GuildUnbanEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUnban(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateAfkChannel(GuildUpdateAfkChannelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateAfkChannel(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateAfkTimeout(GuildUpdateAfkTimeoutEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateAfkTimeout(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateBanner(GuildUpdateBannerEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateBanner(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateBoostCount(GuildUpdateBoostCountEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateBoostCount(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateBoostTier(GuildUpdateBoostTierEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateBoostTier(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateCommunityUpdatesChannel(GuildUpdateCommunityUpdatesChannelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateCommunityUpdatesChannel(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateDescription(GuildUpdateDescriptionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateDescription(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateExplicitContentLevel(GuildUpdateExplicitContentLevelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateExplicitContentLevel(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateFeatures(GuildUpdateFeaturesEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateFeatures(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateIcon(GuildUpdateIconEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateIcon(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateLocale(GuildUpdateLocaleEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateLocale(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateMaxMembers(GuildUpdateMaxMembersEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateMaxMembers(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateMaxPresences(GuildUpdateMaxPresencesEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateMaxPresences(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateMFALevel(GuildUpdateMFALevelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateMFALevel(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateName(GuildUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateName(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateNotificationLevel(GuildUpdateNotificationLevelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateNotificationLevel(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateOwner(GuildUpdateOwnerEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateOwner(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateRulesChannel(GuildUpdateRulesChannelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateRulesChannel(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateSplash(GuildUpdateSplashEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateSplash(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateSystemChannel(GuildUpdateSystemChannelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateSystemChannel(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateVanityCode(GuildUpdateVanityCodeEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateVanityCode(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildUpdateVerificationLevel(GuildUpdateVerificationLevelEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateVerificationLevel(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildVoiceDeafen(GuildVoiceDeafenEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceDeafen(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildVoiceGuildDeafen(GuildVoiceGuildDeafenEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceGuildDeafen(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildVoiceGuildMute(GuildVoiceGuildMuteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceGuildMute(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildVoiceMute(GuildVoiceMuteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceMute(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildVoiceSelfDeafen(GuildVoiceSelfDeafenEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceSelfDeafen(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildVoiceSelfMute(GuildVoiceSelfMuteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceSelfMute(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildVoiceStream(GuildVoiceStreamEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceStream(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildVoiceSuppress(GuildVoiceSuppressEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceSuppress(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onMessageDelete(MessageDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageDelete(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onMessageBulkDelete(MessageBulkDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageBulkDelete(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onMessageEmbed(MessageEmbedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageEmbed(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReactionAdd(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onMessageReactionRemove(MessageReactionRemoveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReactionRemove(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onMessageReactionRemoveAll(MessageReactionRemoveAllEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReactionRemoveAll(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReceived(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onMessageUpdate(MessageUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onPermissionOverrideCreate(PermissionOverrideCreateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPermissionOverrideCreate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onPermissionOverrideDelete(PermissionOverrideDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPermissionOverrideDelete(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onPermissionOverrideUpdate(PermissionOverrideUpdateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onPermissionOverrideUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onRawGateway(RawGatewayEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRawGateway(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onRoleCreate(RoleCreateEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleCreate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onRoleDelete(RoleDeleteEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleDelete(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onRoleUpdateColor(RoleUpdateColorEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateColor(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onRoleUpdateHoisted(RoleUpdateHoistedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateHoisted(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onRoleUpdateMentionable(RoleUpdateMentionableEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateMentionable(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onRoleUpdateName(RoleUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateName(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdatePermissions(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onRoleUpdatePosition(RoleUpdatePositionEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdatePosition(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onSelfUpdateAvatar(SelfUpdateAvatarEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateAvatar(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onSelfUpdateMFA(SelfUpdateMFAEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateMFA(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onSelfUpdateName(SelfUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateName(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onSelfUpdateVerified(SelfUpdateVerifiedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSelfUpdateVerified(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onUnavailableGuildJoined(UnavailableGuildJoinedEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUnavailableGuildJoined(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onUnavailableGuildLeave(UnavailableGuildLeaveEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUnavailableGuildLeave(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onUserActivityEnd(UserActivityEndEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserActivityEnd(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onUserActivityStart(UserActivityStartEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserActivityStart(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onUserTyping(UserTypingEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserTyping(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onUserUpdateActivities(UserUpdateActivitiesEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateActivities(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onUserUpdateActivityOrder(UserUpdateActivityOrderEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateActivityOrder(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onUserUpdateAvatar(UserUpdateAvatarEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateAvatar(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onUserUpdateDiscriminator(UserUpdateDiscriminatorEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateDiscriminator(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onUserUpdateFlags(UserUpdateFlagsEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateFlags(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onUserUpdateName(UserUpdateNameEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateName(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {try {

		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserUpdateOnlineStatus(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}


	@Override
	public void onApplicationCommandUpdatePrivileges(ApplicationCommandUpdatePrivilegesEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onApplicationCommandUpdatePrivileges(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onApplicationUpdatePrivileges(ApplicationUpdatePrivilegesEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onApplicationUpdatePrivileges(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onButtonInteraction(ButtonInteractionEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onButtonInteraction(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelCreate(ChannelCreateEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelCreate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelDelete(ChannelDeleteEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelDelete(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateArchived(ChannelUpdateArchivedEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateArchived(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateArchiveTimestamp(ChannelUpdateArchiveTimestampEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateArchiveTimestamp(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateAutoArchiveDuration(ChannelUpdateAutoArchiveDurationEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateAutoArchiveDuration(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateBitrate(ChannelUpdateBitrateEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateBitrate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateInvitable(ChannelUpdateInvitableEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateInvitable(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateLocked(ChannelUpdateLockedEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateLocked(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateName(ChannelUpdateNameEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateName(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateNSFW(ChannelUpdateNSFWEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateNSFW(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateParent(ChannelUpdateParentEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateParent(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdatePosition(ChannelUpdatePositionEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdatePosition(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateRegion(ChannelUpdateRegionEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateRegion(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateSlowmode(ChannelUpdateSlowmodeEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateSlowmode(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateTopic(ChannelUpdateTopicEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateTopic(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateType(ChannelUpdateTypeEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateType(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onChannelUpdateUserLimit(ChannelUpdateUserLimitEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onChannelUpdateUserLimit(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onCommandAutoCompleteInteraction(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onEmojiAdded(EmojiAddedEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onEmojiAdded(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onEmojiRemoved(EmojiRemovedEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onEmojiRemoved(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onEmojiUpdateName(EmojiUpdateNameEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onEmojiUpdateName(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onEmojiUpdateRoles(EmojiUpdateRolesEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onEmojiUpdateRoles(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericAutoCompleteInteraction(GenericAutoCompleteInteractionEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericAutoCompleteInteraction(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericChannel(GenericChannelEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericChannel(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericChannelUpdate(GenericChannelUpdateEvent<?> event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericChannelUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericCommandInteraction(GenericCommandInteractionEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericCommandInteraction(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericComponentInteractionCreate(GenericComponentInteractionCreateEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericComponentInteractionCreate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericContextInteraction(GenericContextInteractionEvent<?> event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericContextInteraction(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericEmoji(GenericEmojiEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericEmoji(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@SuppressWarnings("rawtypes")
	@Override
	public void onGenericEmojiUpdate(GenericEmojiUpdateEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericEmojiUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericGuildSticker(GenericGuildStickerEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildSticker(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@SuppressWarnings("rawtypes")
	@Override
	public void onGenericGuildStickerUpdate(GenericGuildStickerUpdateEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericGuildStickerUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericInteractionCreate(GenericInteractionCreateEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericInteractionCreate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericPrivilegeUpdate(GenericPrivilegeUpdateEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericPrivilegeUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericStageInstance(GenericStageInstanceEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericStageInstance(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@SuppressWarnings("rawtypes")
	@Override
	public void onGenericStageInstanceUpdate(GenericStageInstanceUpdateEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericStageInstanceUpdate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericThread(GenericThreadEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericThread(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGenericThreadMember(GenericThreadMemberEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGenericThreadMember(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGuildMemberUpdateAvatar(GuildMemberUpdateAvatarEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdateAvatar(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGuildMemberUpdateTimeOut(GuildMemberUpdateTimeOutEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildMemberUpdateTimeOut(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGuildStickerAdded(GuildStickerAddedEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildStickerAdded(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGuildStickerRemoved(GuildStickerRemovedEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildStickerRemoved(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGuildStickerUpdateAvailable(GuildStickerUpdateAvailableEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildStickerUpdateAvailable(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGuildStickerUpdateDescription(GuildStickerUpdateDescriptionEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildStickerUpdateDescription(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGuildStickerUpdateName(GuildStickerUpdateNameEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildStickerUpdateName(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGuildStickerUpdateTags(GuildStickerUpdateTagsEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildStickerUpdateTags(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGuildUpdateNSFWLevel(GuildUpdateNSFWLevelEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildUpdateNSFWLevel(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGuildVoiceRequestToSpeak(GuildVoiceRequestToSpeakEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceRequestToSpeak(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onGuildVoiceVideo(GuildVoiceVideoEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onGuildVoiceVideo(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onMessageContextInteraction(MessageContextInteractionEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageContextInteraction(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onMessageReactionRemoveEmoji(MessageReactionRemoveEmojiEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onMessageReactionRemoveEmoji(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onModalInteraction(ModalInteractionEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onModalInteraction(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onRoleUpdateIcon(RoleUpdateIconEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onRoleUpdateIcon(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onSlashCommandInteraction(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onStageInstanceCreate(StageInstanceCreateEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStageInstanceCreate(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onStageInstanceDelete(StageInstanceDeleteEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStageInstanceDelete(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onStageInstanceUpdatePrivacyLevel(StageInstanceUpdatePrivacyLevelEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStageInstanceUpdatePrivacyLevel(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onStageInstanceUpdateTopic(StageInstanceUpdateTopicEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onStageInstanceUpdateTopic(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onThreadHidden(ThreadHiddenEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onThreadHidden(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onThreadMemberJoin(ThreadMemberJoinEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onThreadMemberJoin(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onThreadMemberLeave(ThreadMemberLeaveEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onThreadMemberLeave(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onThreadRevealed(ThreadRevealedEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onThreadRevealed(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}
	@Override
	public void onUserContextInteraction(UserContextInteractionEvent event) {try {
		for(Module module : Demi.i.getActiveModules()) {new Thread(new Runnable() {@Override public void run() {module.onUserContextInteraction(event);}}).start();}}catch (Exception e) {e.printStackTrace();}
	}

}
