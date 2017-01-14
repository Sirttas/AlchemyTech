package sirttas.alchemytech.block.tile.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class InstrumentRenderer<T extends TileEntity> extends TileEntitySpecialRenderer<T> {

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

}
