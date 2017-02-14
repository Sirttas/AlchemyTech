package sirttas.alchemytech.block.jar;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import sirttas.alchemytech.AlchemyTech;

public class IngredientJarRenderer extends TileEntitySpecialRenderer<TileIngredientJar> {

	protected static BlockRendererDispatcher blockRenderer;

	public static IBakedModel modelMilkLiquid;

	@Override
	public void renderTileEntityAt(TileIngredientJar tileEntity, double x, double y, double z, float partialTicks,
			int destroyStage) {
		if (modelMilkLiquid == null) {
			try {
				IModel model = ModelLoaderRegistry
						.getModel(new ResourceLocation(AlchemyTech.MODID, "block/milkJarLiquid"));
				modelMilkLiquid = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK,
						ModelLoader.defaultTextureGetter());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!tileEntity.hasWorldObj()) {
			return;
		}
		if (blockRenderer == null) {
			blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
		}
		if (tileEntity.getIngredientCount() > 0) {
			RenderHelper.disableStandardItemLighting();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.enableBlend();
			GlStateManager.disableCull();
			GlStateManager.pushMatrix();

			GlStateManager.translate(x, y, z);
			GlStateManager.scale(1f, tileEntity.getIngredientCount() / ConfigIngredientJar.MAX_INGREDIENTS, 1f);
			bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			blockRenderer.getBlockModelRenderer().renderModelBrightnessColor(modelMilkLiquid, 1, 0, 0, 0);
			GlStateManager.popMatrix();

			RenderHelper.enableStandardItemLighting();
		}
	}

}