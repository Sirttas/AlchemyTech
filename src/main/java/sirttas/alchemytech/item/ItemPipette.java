package sirttas.alchemytech.item;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.api.IEssenceIngredient;
import sirttas.alchemytech.ingredient.api.IItemIngredient;

public class ItemPipette extends ItemAT {

	private static final String NAME = "pipette";
	private static final String INGREDIENT_TAG = "ingredient";

	public ItemPipette() {
		super(NAME);
	}

	public Ingredient getIngredient(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		if (!nbt.hasKey(INGREDIENT_TAG)) {
			return null;
		}
		return Ingredient.REGISTRY.getObject(new ResourceLocation(nbt.getString(INGREDIENT_TAG)));
	}

	public void setIngredient(ItemStack stack, Ingredient ingredient) {
		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}
		if (ingredient == null) {
			stack.getTagCompound().removeTag(INGREDIENT_TAG);
		} else {
			stack.getTagCompound().setString(INGREDIENT_TAG, ingredient.getRegistryName().toString());
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		Ingredient ingredient = this.getIngredient(stack);

		if (ingredient instanceof IEssenceIngredient) {
			tooltip.add("§b" + ingredient.getDisplayName());
		} else if (ingredient instanceof IItemIngredient) {
			tooltip.add("§f" + ingredient.getDisplayName());
		} else {
			tooltip.add(ingredient.getDisplayName());
		}
	}

	@SideOnly(Side.CLIENT)
	public void initColors() {
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return tintIndex > 0 ? -1 : getIngredient(stack).getColor();
			}
		}, new Item[] { this });

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

}
