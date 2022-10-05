package pene.gc.nuzlocke.proto;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import pene.gc.nuzlocke.proto.AvatarDelNotifyOuterClass.AvatarDelNotify;

public class PacketAvatarDelNotify extends BasePacket {
	
	public PacketAvatarDelNotify(long avatarGuid) {
		super(PacketOpcodes.AvatarDelNotify);

		AvatarDelNotify proto = AvatarDelNotify.newBuilder()
				.addAvatarGuidList(avatarGuid)
				.build();
		
		this.setData(proto);
	}
}
