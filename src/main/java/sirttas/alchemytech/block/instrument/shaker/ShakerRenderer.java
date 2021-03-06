package sirttas.alchemytech.block.instrument.shaker;

import static sirttas.alchemytech.block.BlockAT.BIT_SIZE;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import sirttas.alchemytech.block.tile.renderer.ATRenderer;
import sirttas.alchemytech.model.ShakerPlateModel;

public class ShakerRenderer extends ATRenderer<TileShaker> {

	private static final ResourceLocation IRON_TEXTURE = new ResourceLocation("alchemytech",
			"textures/blocks/iron.png");

	private ShakerPlateModel plate;

	@Override
	public void renderTileEntityAt(TileShaker te, double x, double y, double z, float partialTicks, int destroyStage) {
		plate = new ShakerPlateModel();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5f, (float) y + 0.5f + ((te.getProgress() % 20 / 10) * 0.05F),
				(float) z + 0.5f);
		bindTexture(IRON_TEXTURE);
		plate.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();

		if (te.getStackInSlot(0) != null) {
			renderItem(te.getStackInSlot(0), x + 0.5, y + 3.5 * BIT_SIZE + ((te.getProgress() % 20 / 10) * 0.05F),
					z + 0.5, 1);
		}
	}
}
