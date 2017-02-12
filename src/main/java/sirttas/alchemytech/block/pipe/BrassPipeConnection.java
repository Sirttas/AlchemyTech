package sirttas.alchemytech.block.pipe;

import net.minecraft.util.EnumFacing;

public class BrassPipeConnection {

	public enum Type {
		NONE, CONNECT, INSERT, EXTRACT
	}

	private EnumFacing facing;
	private Type type;

	public EnumFacing getFacing() {
		return facing;
	}

	public void setFacing(EnumFacing facing) {
		this.facing = facing;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}