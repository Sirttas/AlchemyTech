package sirttas.alchemytech.block.tile.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ATRenderer<T extends TileEntity> extends TileEntitySpecialRenderer<T> {

	public void renderItem(ItemStack stack, double x, double y, double z, double size) {
		GL11.glPushMatrix();
		EntityItem item = new EntityItem(Minecraft.getMinecraft().theWorld, x, y, z, stack);
		item.hoverStart = 0;
		GL11.glTranslated(x, y, z);
		GL11.glScaled(size, size, size);
		GL11.glRotated(0, 0, 1, 0);
		Minecraft.getMinecraft().getRenderManager().doRenderEntity(item, 0, 0, 0, 0, 0, true);
		GL11.glPopMatrix();
	}

	public void drawCube(float startX, float startY, float startZ, float endX, float endY, float endZ) {
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glVertex3f(endX, endY, startZ);
		GL11.glVertex3f(startX, endY, startZ);
		GL11.glVertex3f(startX, endY, endZ);
		GL11.glVertex3f(endX, endY, endZ);

		GL11.glVertex3f(endX, startY, endZ);
		GL11.glVertex3f(startX, startY, endZ);
		GL11.glVertex3f(startX, startY, startZ);
		GL11.glVertex3f(endX, startY, startZ);

		GL11.glVertex3f(endX, endY, endZ);
		GL11.glVertex3f(startX, endY, endZ);
		GL11.glVertex3f(startX, startY, endZ);
		GL11.glVertex3f(endX, startY, endZ);

		GL11.glVertex3f(endX, startY, startZ);
		GL11.glVertex3f(startX, startY, startZ);
		GL11.glVertex3f(startX, endY, startZ);
		GL11.glVertex3f(endX, endY, startZ);

		GL11.glVertex3f(startX, endY, endZ);
		GL11.glVertex3f(startX, endY, startZ);
		GL11.glVertex3f(startX, startY, startZ);
		GL11.glVertex3f(startX, startY, endZ);

		GL11.glVertex3f(endX, endY, startZ);
		GL11.glVertex3f(endX, endY, endZ);
		GL11.glVertex3f(endX, startY, endZ);
		GL11.glVertex3f(endX, startY, startZ);
		GL11.glEnd();
	}

	public void drawColor(int color) {
		int r = (color >> 16) & 0xFF;
		int g = (color >> 8) & 0xFF;
		int b = (color) & 0xFF;

		GlStateManager.color((float) r / (float) 0xff, (float) g / (float) 0xff, (float) b / (float) 0xff);
	}
}
