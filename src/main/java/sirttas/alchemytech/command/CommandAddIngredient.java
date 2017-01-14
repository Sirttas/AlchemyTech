package sirttas.alchemytech.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.item.ItemPreparation;

public class CommandAddIngredient extends CommandBase {

	/**
	 * Return the required permission level for this command.
	 */
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public String getCommandName() {
		return "addIngredient";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/addIngredient <ingredientName>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (sender != null && sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;
			ItemStack preparation = player.getHeldItemMainhand();

			if (preparation.getItem() instanceof ItemPreparation) {
				ItemPreparation item = ((ItemPreparation) preparation.getItem());

				for (String arg : args) {
					ResourceLocation resourcelocation = new ResourceLocation(arg);
					Ingredient ingredient = Ingredient.REGISTRY.getObject(resourcelocation);

					item.addIngredient(preparation, ingredient);
				}
			}
		}
	}

	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos pos) {
		return getListOfStringsMatchingLastWord(args, Ingredient.REGISTRY.getKeys());
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}
