package pene.gc.nuzlocke;

import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.server.event.game.ReceivePacketEvent;

public final class EventListeners {
    public static void onDeath(ReceivePacketEvent event) {
        if(event.getPacketId() == PacketOpcodes.AvatarDieAnimationEndReq){
            Player targetPlayer = event.getGameSession().getPlayer();
            Avatar deadAvatar = targetPlayer.getTeamManager().getCurrentAvatarEntity().getAvatar();
            Nuzlocke.NuzlockeFunction(targetPlayer,deadAvatar);
        }
    }
}
