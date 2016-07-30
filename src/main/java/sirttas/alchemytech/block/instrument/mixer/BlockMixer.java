package sirttas.alchemytech.block.instrument.mixer;

import static sirttas.alchemytech.block.BlockAT.BIT_SIZE;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.block.instrument.BlockInstrument;
import sirttas.alchemytech.block.tile.instrument.IInstrument;
import sirttas.alchemytech.ingredient.recipe.instrument.MixerRecipe;
import sirttas.alchemytech.item.ItemPreparation;
import sirttas.alchemytech.particle.ParticleMixerBubble;

public class BlockMixer extends BlockInstrument {

	public BlockMixer() {
		super(ConfigMixer.NAME, TileMixer.class);
		addBoundingBox(ConfigMixer.BASE_AABB);
		addBoundingBox(ConfigMixer.BOWL1_AABB);
		addBoundingBox(ConfigMixer.BOWL2_AABB);
		addBoundingBox(ConfigMixer.BOWL3_AABB);
		addBoundingBox(ConfigMixer.BOWL4_AABB);
		addBoundingBox(ConfigMixer.OUTPUT_AABB);
	}

	@Override
	public TileMixer createNewTileEntity(World worldIn, int meta) {
		return new TileMixer();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		super.initModel();
		ClientRegistry.bindTileEntitySpecialRenderer(TileMixer.class, new MixerRenderer());
	}

	private boolean onBowlActivated(IInstrument instrument, EntityPlayer player, ItemStack heldItem, int index) {
		if (heldItem == null || MixerRecipe.isMixable(heldItem)) {
			return onSlotActivated(instrument, player, heldItem, index);
		}
		return false;
	}

	@Override
	protected boolean onBoundingBoxActivated(AxisAlignedBB boundingBox, IInstrument instrument, EntityPlayer player,
			ItemStack heldItem) {
		if (boundingBox.equals(ConfigMixer.BOWL1_AABB)) {
			return onBowlActivated(instrument, player, heldItem, 0);
		} else if (boundingBox.equals(ConfigMixer.BOWL2_AABB)) {
			return onBowlActivated(instrument, player, heldItem, 1);
		} else if (boundingBox.equals(ConfigMixer.BOWL3_AABB)) {
			return onBowlActivated(instrument, player, heldItem, 2);
		} else if (boundingBox.equals(ConfigMixer.BOWL4_AABB)) {
			return onBowlActivated(instrument, player, heldItem, 3);
		} else if (boundingBox.equals(ConfigMixer.OUTPUT_AABB) && (heldItem == null
				|| heldItem.getItem() instanceof ItemPotion
						&& PotionUtils.getPotionFromItem(heldItem).equals(PotionTypes.WATER)
				|| heldItem.getItem() instanceof ItemPreparation)) {
			return onSlotActivated(instrument, player, heldItem, 4);
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double x = pos.getX() + (5 + rand.nextDouble() * 6) * BIT_SIZE;
		double y = pos.getY() + 10 * BIT_SIZE;
		double z = pos.getZ() + (5 + rand.nextDouble() * 6) * BIT_SIZE;
		TileMixer mixer = (TileMixer) worldIn.getTileEntity(pos);

		if (mixer != null && mixer.getProgress() > 0) {
			Minecraft.getMinecraft().effectRenderer
					.addEffect(new ParticleMixerBubble(worldIn, x, y, z, 0.0D, 1.0D, 0.0D));
		}
	}

}
