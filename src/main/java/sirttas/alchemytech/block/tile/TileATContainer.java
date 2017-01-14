package sirttas.alchemytech.block.tile;

public abstract class TileATContainer extends TileAT {
	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}
}
