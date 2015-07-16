package net.cyanwool.core.network;

import net.cyanwool.api.network.Packet;

public class MCProtocolPacket implements Packet {

	private org.spacehq.packetlib.packet.Packet packet;

	public MCProtocolPacket(org.spacehq.packetlib.packet.Packet packet) {
		this.packet = packet;
	}

	@Override
	public org.spacehq.packetlib.packet.Packet getPacket() {
		return packet;
	}

}
