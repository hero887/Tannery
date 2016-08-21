package com.minecraftuberverse.tannery.init;

import com.minecraftuberverse.tannery.tileentity.TileEntityButchersBench;
import com.minecraftuberverse.tannery.tileentity.TileEntityGallows;
import com.minecraftuberverse.tannery.util.CarcassType;
import com.minecraftuberverse.tannery.util.recipe.DurationRecipe;
import com.minecraftuberverse.tannery.util.recipe.Recipe;
import com.minecraftuberverse.tannery.util.recipe.RecipeHandler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TanneryRecipes
{
	public static void init()
	{
		initVanillaRecipes();
		initGallows();
		initButchersBench();
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
		// Gallows Recipe
		GameRegistry.addRecipe(new ItemStack(TanneryBlocks.gallows), "AA ", " A ", "BBB", 'A',
				Items.stick, 'B', Blocks.wooden_slab);
		// Butchers Bench Recipe
		GameRegistry.addRecipe(new ItemStack(TanneryBlocks.butcherBench), "AAA", "BBB", "CCC", 'A',
				Blocks.wooden_slab, 'B', Blocks.planks, 'C', Blocks.stone_slab);
		GameRegistry.addRecipe(new ItemStack(TanneryItems.boneKnife, 1), new Object[] {"  #", " !", "!  ", '#', Items.flint, '!', Items.bone});
		GameRegistry.addShapelessRecipe(new ItemStack(TanneryItems.barkSpud, 1),
				new Object[] {Items.flint, Items.stick, TanneryItems.sinew});
	}

	private static void initButchersBench()
	{
		RecipeHandler h = TileEntityButchersBench.recipeHandler;
		h.register(new Recipe(new ItemStack[] { new ItemStack(TanneryItems.cowCarcass) },
				new ItemStack[] { new ItemStack(Items.beef, 5), new ItemStack(Items.bone, 4) }));
		h.register(new Recipe(new ItemStack[] { new ItemStack(TanneryItems.elkCarcass) },
				new ItemStack[] { new ItemStack(Items.beef, 5), new ItemStack(Items.bone, 4) }));
		h.register(new Recipe(new ItemStack[] { new ItemStack(TanneryItems.pigCarcass) },
				new ItemStack[] { new ItemStack(Items.porkchop, 4), new ItemStack(Items.bone,
						4) }));
		h.register(new Recipe(new ItemStack[] { new ItemStack(TanneryItems.sheepCarcass) },
				new ItemStack[] { new ItemStack(Items.mutton, 3), new ItemStack(Items.bone, 4) }));
	}

	private static void initGallows()
	{
		RecipeHandler handler = TileEntityGallows.recipeHandler;
		if (handler != null)
		{
			// Bloody carcasses to drained carcasses to output
			// Cow
			CarcassType type = TanneryItems.bloodyCowCarcass.getCarcassType();
			handler.register(new DurationRecipe(
					new ItemStack[] { new ItemStack(TanneryItems.bloodyCowCarcass) },
					new ItemStack[] { new ItemStack(TanneryItems.drainedCowCarcass) },
					(int) Math.round(type.getSize() * 400)));
			handler.register(
					new Recipe(new ItemStack[] { new ItemStack(TanneryItems.drainedCowCarcass) },
							new ItemStack[] { new ItemStack(TanneryItems.cowHide), new ItemStack(
									TanneryItems.cowCarcass) },
							1));
			// Elk
			type = TanneryItems.bloodyElkCarcass.getCarcassType();
			handler.register(new DurationRecipe(
					new ItemStack[] { new ItemStack(TanneryItems.bloodyElkCarcass) },
					new ItemStack[] { new ItemStack(TanneryItems.drainedElkCarcass) },
					(int) Math.round(type.getSize() * 400)));
			handler.register(
					new Recipe(new ItemStack[] { new ItemStack(TanneryItems.drainedElkCarcass) },
							new ItemStack[] { new ItemStack(TanneryItems.antler, 2), new ItemStack(
									TanneryItems.elkhide), new ItemStack(TanneryItems.elkCarcass) },
							1));
			// Pig
			type = TanneryItems.bloodyPigCarcass.getCarcassType();
			handler.register(new DurationRecipe(
					new ItemStack[] { new ItemStack(TanneryItems.bloodyPigCarcass) },
					new ItemStack[] { new ItemStack(TanneryItems.drainedPigCarcass) },
					(int) Math.round(type.getSize() * 400)));
			handler.register(
					new Recipe(new ItemStack[] { new ItemStack(TanneryItems.drainedPigCarcass) },
							new ItemStack[] { new ItemStack(TanneryItems.pigskin), new ItemStack(
									TanneryItems.pigCarcass) },
							1));
			// Sheep
			type = TanneryItems.bloodySheepCarcass.getCarcassType();
			handler.register(new DurationRecipe(
					new ItemStack[] { new ItemStack(TanneryItems.bloodySheepCarcass) },
					new ItemStack[] { new ItemStack(TanneryItems.drainedSheepCarcass) },
					(int) Math.round(type.getSize() * 400)));
			handler.register(
					new Recipe(new ItemStack[] { new ItemStack(TanneryItems.drainedSheepCarcass) },
							new ItemStack[] { new ItemStack(TanneryItems.sheepskin), new ItemStack(
									TanneryItems.sheepCarcass) },
							1));
		}
	}
}
