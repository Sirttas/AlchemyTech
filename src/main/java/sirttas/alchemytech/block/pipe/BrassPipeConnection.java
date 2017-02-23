package sirttas.alchemytech.block.pipe;

import net.minecraft.util.EnumFacing;

public class BrassPipeConnection {

	public enum Type {
		NONE(0), CONNECT(1), INSERT(2), EXTRACT(3);

		private final int value;

		private Type(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static Type fromInteger(int x) {
			switch (x) {
			case 0:
				return NONE;
			case 1:
				return CONNECT;
			case 2:
				return INSERT;
			case 3:
				return EXTRACT;
			}
			return null;
		}
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

	@Override
	public String toString() {
		return this.type.toString();
	}

}