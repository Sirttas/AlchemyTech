package sirttas.alchemytech.block.jar;

import static sirttas.alchemytech.block.BlockAT.BIT_SIZE;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import sirttas.alchemytech.block.tile.renderer.ATRenderer;

public class IngredientJarRenderer extends ATRenderer<TileIngredientJar> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("alchemytech", "textures/blocks/milk.png");

	@Override
	public void renderTileEntityAt(TileIngredientJar tileEntity, double x, double y, double z, float partialTicks,
			int destroyStage) {
		if (tileEntity.getIngredientCount() > 0) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(x, y, z);
			GlStateManager.scale(1f,
					(float) tileEntity.getIngredientCount() / (float) ConfigIngredientJar.MAX_INGREDIENTS, 1f);
			bindTexture(TEXTURE);
			drawColor(tileEntity.getIngredientsColor());
			drawCube(5.5f * BIT_SIZE, 0.5f * BIT_SIZE, 5.5f * BIT_SIZE, 10.5f * BIT_SIZE, 6 * BIT_SIZE,
					10.5f * BIT_SIZE);
			GlStateManager.popMatrix();
		}
	}

}