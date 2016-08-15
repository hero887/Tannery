package com.minecraftuberverse.tannery.init;

import com.minecraftuberverse.tannery.tileentity.TileEntityGallows;
import com.minecraftuberverse.tannery.util.CarcassType;
import com.minecraftuberverse.tannery.util.recipe.ButchersBenchRecipe;
import com.minecraftuberverse.tannery.util.recipe.GallowsDrainingRecipe;
import com.minecraftuberverse.ubercore.tileentity.TileEntityMachine;
import com.minecraftuberverse.ubercore.util.RecipeHandler;
import com.minecraftuberverse.ubercore.util.recipe.Recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TanneryRecipes
{
	public static RecipeHandler<ButchersBenchRecipe> butchersBenchRecipeHandler;

	public static void init()
	{
		initVanillaRecipes();
		initGallows();
		initButchersBench();
	}

	private static void initButchersBench()
	{
		butchersBenchRecipeHandler = new RecipeHandler<>(1, 20);
		butchersBenchRecipeHandler.register((ButchersBenchRecipe) new ButchersBenchRecipe()
				.addInput(TanneryItems.cowCarcass)
				.addOutput(Items.beef, Items.beef, Items.bone, Items.bone, Items.bone, Items.bone));
	}

	private static void initVanillaRecipes()
	{
		// Wool to string
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 0) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 1) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 2) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 3) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 4) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 5) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 6) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 7) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 8) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 9) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 10) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 11) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 12) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 13) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 14) });
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4),
				new Object[] { new ItemStack(Blocks.wool, 1, 15) });
		GameRegistry.addRecipe(new ItemStack(TanneryItems.boneKnife, 1), new Object[] {"  #", " !", "!  ", '#', Items.flint, '!', Items.bone});
		GameRegistry.addShapelessRecipe(new ItemStack(TanneryItems.barkSpud, 1),
				new Object[] {Items.flint, Items.stick, TanneryItems.sinew});
	}

	private static void initGallows()
	{
		RecipeHandler<Recipe> handler = TileEntityMachine
				.getRecipeHandler(TileEntityGallows.RECIPE_HANDLER_KEY);
		if (handler != null)
		{
			// Bloody carcasses to drained carcasses to output
			GallowsDrainingRecipe drainingRecipe;
			Recipe cuttingRecipe;
			CarcassType type;
			// Cow
			type = TanneryItems.bloodyCowCarcass.getCarcassType();
			drainingRecipe = new GallowsDrainingRecipe(TanneryItems.bloodyCowCarcass,
					TanneryItems.drainedCowCarcass, (int) Math.round(type.getSize() * 20 * 20));
			handler.register(drainingRecipe);
			cuttingRecipe = new Recipe(TanneryItems.drainedCowCarcass,
					new Item[] { TanneryItems.cowCarcass });
			handler.register(cuttingRecipe);
			// Elk
			type = TanneryItems.bloodyElkCarcass.getCarcassType();
			drainingRecipe = new GallowsDrainingRecipe(TanneryItems.bloodyElkCarcass,
					TanneryItems.drainedElkCarcass, (int) Math.round(type.getSize() * 20 * 20));
			handler.register(drainingRecipe);
			cuttingRecipe = new Recipe(TanneryItems.drainedElkCarcass,
					new Item[] { TanneryItems.elkCarcass, TanneryItems.antler });
			handler.register(cuttingRecipe);
			// Pig
			type = TanneryItems.bloodyPigCarcass.getCarcassType();
			drainingRecipe = new GallowsDrainingRecipe(TanneryItems.bloodyPigCarcass,
					TanneryItems.drainedPigCarcass, (int) Math.round(type.getSize() * 20 * 20));
			handler.register(drainingRecipe);
			cuttingRecipe = new Recipe(TanneryItems.drainedPigCarcass,
					new Item[] { TanneryItems.pigCarcass });
			handler.register(cuttingRecipe);
			// Sheep
			type = TanneryItems.bloodySheepCarcass.getCarcassType();
			drainingRecipe = new GallowsDrainingRecipe(TanneryItems.bloodySheepCarcass,
					TanneryItems.drainedSheepCarcass, (int) Math.round(type.getSize() * 20 * 20));
			handler.register(drainingRecipe);
			cuttingRecipe = new Recipe(TanneryItems.drainedSheepCarcass,
					new Item[] { TanneryItems.sheepCarcass });
			handler.register(cuttingRecipe);
		}
	}
}
