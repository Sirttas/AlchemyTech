package sirttas.alchemytech.model;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("hiding")
public class FloatModelBox extends ModelBox {

	/**
	 * The (x,y,z) vertex positions and (u,v) texture coordinates for each of
	 * the 8 points on a cube
	 */
	private final PositionTextureVertex[] vertexPositions;
	/** An array of 6 TexturedQuads, one for each face of a cube */
	private final TexturedQuad[] quadList;
	/** X vertex coordinate of upper box corner */

	public final float posX2;
	/** Y vertex coordinate of upper box corner */
	public final float posY2;
	/** Z vertex coordinate of upper box corner */
	public final float posZ2;

	public FloatModelBox(ModelRenderer renderer, int textureX, int textureY, float x, float y, float z, float width,
			float height, float depth, float scale) {
		this(renderer, textureX, textureY, x, y, z, width, height, depth, scale, renderer.mirror);
	}

	@SuppressWarnings("all")
	public FloatModelBox(ModelRenderer renderer, int textureX, int textureY, float x, float y, float z, float width,
			float height, float depth, float scale, boolean miror) {
		super(renderer, textureX, textureY, x, y, z, (int) width, (int) height, (int) depth, scale, miror);
		this.posX2 = x + width;
		this.posY2 = y + height;
		this.posZ2 = z + depth;
		this.vertexPositions = new PositionTextureVertex[8];
		this.quadList = new TexturedQuad[6];
		float f = x + width;
		float f1 = y + height;
		float f2 = z + depth;
		int intwidth = (int) width;
		int intheight = (int) height;
		int intdepth = (int) depth;

		x = x - scale;
		y = y - scale;
		z = z - scale;
		f = f + scale;
		f1 = f1 + scale;
		f2 = f2 + scale;
		if (miror) {
			float f3 = f;
			f = x;
			x = f3;
		}

		PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(x, y, z, 0.0F, 0.0F);
		PositionTextureVertex positiontexturevertex = new PositionTextureVertex(f, y, z, 0.0F, 8.0F);
		PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f, f1, z, 8.0F, 8.0F);
		PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(x, f1, z, 8.0F, 0.0F);
		PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(x, y, f2, 0.0F, 0.0F);
		PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(f, y, f2, 0.0F, 8.0F);
		PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f, f1, f2, 8.0F, 8.0F);
		PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(x, f1, f2, 8.0F, 0.0F);
		this.vertexPositions[0] = positiontexturevertex7;
		this.vertexPositions[1] = positiontexturevertex;
		this.vertexPositions[2] = positiontexturevertex1;
		this.vertexPositions[3] = positiontexturevertex2;
		this.vertexPositions[4] = positiontexturevertex3;
		this.vertexPositions[5] = positiontexturevertex4;
		this.vertexPositions[6] = positiontexturevertex5;
		this.vertexPositions[7] = positiontexturevertex6;
		this.quadList[0] = new TexturedQuad(
				new PositionTextureVertex[] { positiontexturevertex4, positiontexturevertex, positiontexturevertex1,
						positiontexturevertex5 },
				textureX + intdepth + intwidth, textureY + intdepth, textureX + intdepth + intwidth + intdepth,
				textureY + intdepth + intheight, renderer.textureWidth, renderer.textureHeight);
		this.quadList[1] = new TexturedQuad(
				new PositionTextureVertex[] { positiontexturevertex7, positiontexturevertex3, positiontexturevertex6,
						positiontexturevertex2 },
				textureX, textureY + intdepth, textureX + intdepth, textureY + intdepth + intheight,
				renderer.textureWidth, renderer.textureHeight);
		this.quadList[2] = new TexturedQuad(
				new PositionTextureVertex[] { positiontexturevertex4, positiontexturevertex3, positiontexturevertex7,
						positiontexturevertex },
				textureX + intdepth, textureY, textureX + intdepth + intwidth, textureY + intdepth,
				renderer.textureWidth, renderer.textureHeight);
		this.quadList[3] = new TexturedQuad(
				new PositionTextureVertex[] { positiontexturevertex1, positiontexturevertex2, positiontexturevertex6,
						positiontexturevertex5 },
				textureX + intdepth + intwidth, textureY + intdepth, textureX + intdepth + intwidth + intwidth,
				textureY, renderer.textureWidth, renderer.textureHeight);
		this.quadList[4] = new TexturedQuad(
				new PositionTextureVertex[] { positiontexturevertex, positiontexturevertex7, positiontexturevertex2,
						positiontexturevertex1 },
				textureX + intdepth, textureY + intdepth, textureX + intdepth + intwidth,
				textureY + intdepth + intheight, renderer.textureWidth, renderer.textureHeight);
		this.quadList[5] = new TexturedQuad(
				new PositionTextureVertex[] { positiontexturevertex3, positiontexturevertex4, positiontexturevertex5,
						positiontexturevertex6 },
				textureX + intdepth + intwidth + intdepth, textureY + intdepth,
				textureX + intdepth + intwidth + intdepth + intwidth, textureY + intdepth + intheight,
				renderer.textureWidth, renderer.textureHeight);

		if (miror) {
			for (TexturedQuad texturedquad : this.quadList) {
				texturedquad.flipFace();
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void render(VertexBuffer renderer, float scale) {
		for (TexturedQuad texturedquad : this.quadList) {
			texturedquad.draw(renderer, scale);
		}
	}
}
