package pene.gc.nuzlocke;

import com.mongodb.client.result.DeleteResult;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.command.commands.TeamCommand;
import emu.grasscutter.database.DatabaseManager;
import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.game.player.Player;
import pene.gc.nuzlocke.proto.PacketAvatarDelNotify;

import java.util.ArrayList;
import java.util.List;



public class Nuzlocke {
    public static void NuzlockeFunction(Player targetPlayer, Avatar deadAvatar) {
        try {
            long avatarGuid = targetPlayer.getTeamManager().getCurrentCharacterGuid(); //Can't loose it earlier

            List<String> args = new ArrayList<>();
            args.add("remove");
            args.add(String.valueOf(targetPlayer.getTeamManager().getCurrentCharacterIndex()+1));

            TeamCommand Tc = new TeamCommand();
            Tc.execute(targetPlayer, targetPlayer, args);

            DeleteResult result = DatabaseManager.getGameDatastore().delete(deadAvatar);
            targetPlayer.getScene().broadcastPacket(new PacketAvatarDelNotify(avatarGuid));

            if(result.wasAcknowledged()){
                Grasscutter.getLogger().info(String.format("%s was deleted", deadAvatar.getAvatarData().getName()));
                CommandHandler.sendMessage(targetPlayer, String.format("You lost %s", deadAvatar.getAvatarData().getName()));
            } else{
                Grasscutter.getLogger().error("Character was no deleted, database error");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
