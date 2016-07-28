package sirttas.alchemytech.particle;

import net.minecraft.client.particle.ParticleBubble;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleMixerBubble extends ParticleBubble {

	public ParticleMixerBubble(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.particleMaxAge-- > 0) {
			this.isExpired = false;
		}
	}
}
