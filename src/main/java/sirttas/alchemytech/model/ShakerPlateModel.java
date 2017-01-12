package sirttas.alchemytech.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ShakerPlateModel  extends ModelBase {

	public ModelRenderer plate;

	FloatModelRenderer Elem0;
	FloatModelRenderer Elem1;
	FloatModelRenderer Elem2;
	FloatModelRenderer Elem3;
	FloatModelRenderer Elem4;
	
	public ShakerPlateModel() {
		textureWidth = 64;
		textureHeight = 32;

		plate = new ModelRenderer(this, 0, 0);
		plate.setRotationPoint(0F, 0F, 0F);
		plate.offsetY = 0;
		plate.setTextureSize(64, 32);
		setRotation(plate, -0F, -0F, -0F);

		// Element
		Elem0 = new FloatModelRenderer(this, 0, 0);
		Elem0.addBox(-5F, -6F, -5F, 10, 1F, 10);
		Elem0.setTextureSize(64, 32);
		setRotation(Elem0, -0F, -0F, -0F);
		Elem0.mirror = false;
		plate.addChild(Elem0);
		// Element
//		Elem1 = new FloatModelRenderer(this, 0, 0);
//		Elem1.addBox(3F, 6.5F, 3F, 13, 7, 3.5F);
//		Elem1.setTextureSize(64, 32);
//		setRotation(Elem1, -0F, -0F, -0F);
//		Elem1.mirror = false;
//		plate.addChild(Elem1);
//		// Element
//		Elem2 = new FloatModelRenderer(this, 0, 0);
//		Elem2.addBox(3, 6.5F, 12.5F, 13, 7, 13);
//		Elem2.setTextureSize(64, 32);
//		setRotation(Elem2, -0F, -0F, -0F);
//		Elem2.mirror = false;
//		plate.addChild(Elem2);
//		// Element
//		Elem3 = new FloatModelRenderer(this, 0, 0);
//		Elem3.addBox(3, 6.5F, 3, 3.5F, 7, 13);
//		Elem3.setTextureSize(64, 32);
//		setRotation(Elem3, -0F, -0F, -0F);
//		Elem3.mirror = false;
//		plate.addChild(Elem3);
//		// Element
//		Elem4 = new FloatModelRenderer(this, 0, 0);
//		Elem4.addBox(12.5F, 6.5F, 3, 13, 7, 13);
//		Elem4.setTextureSize(64, 32);
//		setRotation(Elem4, -0F, -0F, -0F);
//		Elem4.mirror = false;
//		plate.addChild(Elem4);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		plate.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity enity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, enity);
	}

}
