package sirttas.alchemytech.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class FloatModelRenderer extends ModelRenderer {

	/** The X offset into the texture used for displaying this model */
	private int textureOffsetX;
	/** The Y offset into the texture used for displaying this model */
	private int textureOffsetY;

	public FloatModelRenderer(ModelBase model, int texOffX, int texOffY) {
		super(model, texOffX, texOffY);
	}

	public ModelRenderer addBox(float offX, float offY, float offZ, float width, float height, float depth) {
		this.cubeList.add(new FloatModelBox(this, this.textureOffsetX, this.textureOffsetY, offX, offY, offZ, width,
				height, depth, 0.0F));
		return this;
	}

	@Override
	public ModelRenderer setTextureOffset(int x, int y) {
		super.setTextureOffset(x, y);
		this.textureOffsetX = x;
		this.textureOffsetY = y;
		return this;
	}

}
