package com.minecraftuberverse.tannery.block.processing;

import java.util.Random;

import com.minecraftuberverse.tannery.block.TanneryBlockHorizontalDirectional;
import com.minecraftuberverse.tannery.tileentity.TileEntityGallows;
import com.minecraftuberverse.tannery.util.CarcassType;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGallows extends TanneryBlockHorizontalDirectional implements ITileEntityProvider
{
	public static final PropertyEnum CARCASS = PropertyEnum.create("carcass", CarcassType.class);
	public static final PropertyBool BLOODY = PropertyBool.create("bloody");
	public static final PropertyEnum PART = PropertyEnum.create("part", EnumPart.class);

	public BlockGallows()
	{
		super(Material.wood, "gallows");
		this.setHardness(2.0f);
		this.setResistance(5.0F);
		setDefaultState(getDefaultState().withProperty(CARCASS, CarcassType.NONE)
				.withProperty(BLOODY, false).withProperty(PART, EnumPart.BOTTOM));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		TileEntityGallows tile = getTileEntity(world, pos, state);
		return tile.onActivate(world, pos, playerIn, playerIn.getCurrentEquippedItem());
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		InventoryHelper.dropInventoryItems(world, pos, getTileEntity(world, pos, state));
		EnumFacing facing = (EnumFacing) state.getValue(FACING);
		EnumPart part = (EnumPart) state.getValue(PART);
		if (part == EnumPart.BOTTOM)
		{
			if (world.getBlockState(pos.up()).getBlock() == this) world.setBlockToAir(pos.up());
			if (world.getBlockState(pos.up().offset(facing)).getBlock() == this) world
					.setBlockToAir(pos.up().offset(facing));
		}
		else if (state.getValue(PART) == EnumPart.TOP)
		{
			if (world.getBlockState(pos.down()).getBlock() == this) world.setBlockToAir(pos.down());
			if (world.getBlockState(pos.offset(facing)).getBlock() == this) world
					.setBlockToAir(pos.offset(facing));
		}
		else if (part == EnumPart.TOPLEFT)
		{
			if (world.getBlockState(pos.offset(facing.getOpposite())).getBlock() == this) world
					.setBlockToAir(pos.offset(facing.getOpposite()));
			if (world.getBlockState(pos.offset(facing.getOpposite()).down())
					.getBlock() == this) world
							.setBlockToAir(pos.offset(facing.getOpposite()).down());
		}
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		TileEntityGallows tile = getTileEntity(worldIn, pos, state);
		state = super.getActualState(state, worldIn, pos);

		if (tile != null) state = state.withProperty(CARCASS, tile.getCarcassType())
				.withProperty(BLOODY, !tile.isDrained());
		return state;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { FACING, CARCASS, BLOODY, PART });
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		if (state.getValue(PART) == EnumPart.BOTTOM) return new TileEntityGallows();
		return null;
	}

	private static TileEntityGallows getTileEntity(IBlockAccess world, BlockPos pos, IBlockState state)
	{
		if (state.getValue(PART) == EnumPart.TOP || state
				.getValue(PART) == EnumPart.TOPLEFT) pos = pos.down();
		if (state.getValue(PART) == EnumPart.TOPLEFT) pos = pos
				.offset(((EnumFacing) state.getValue(FACING)).getOpposite());
		return (TileEntityGallows) world.getTileEntity(pos);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return createTileEntity(worldIn, getStateFromMeta(meta));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		// TODO implement blood particles if the gallows is currently draining
		super.updateTick(worldIn, pos, state, rand);
		if (!worldIn.isRemote && getTileEntity(worldIn, pos, state) != null && getTileEntity(
				worldIn, pos, state).isProcessing()) worldIn.spawnParticle(
						EnumParticleTypes.BLOCK_DUST, pos.getX(), pos.getY() + 1, pos.getZ(),
						(rand.nextInt(6) + 2) / 10d, 1.2, (rand.nextInt(6) + 2) / 10d, 20);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		int facing = (meta >> 2) & 0x0003;
		int part = meta & 0x0003;
		return super.getStateFromMeta(facing).withProperty(PART, EnumPart.getPartFromMeta(part));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int facing = ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		facing = facing << 2;

		int part = ((EnumPart) state.getValue(PART)).getMeta();

		return facing + part;
	}

	public enum EnumPart implements IStringSerializable
	{
		BOTTOM, TOP, TOPLEFT;

		public int getMeta()
		{
			if (this == EnumPart.BOTTOM) return 0;
			else if (this == EnumPart.TOP) return 1;
			else return 2;
		}

		public static EnumPart getPartFromMeta(int meta)
		{
			return meta == 0 ? EnumPart.BOTTOM : meta == 1 ? EnumPart.TOP : EnumPart.TOPLEFT;
		}

		@Override
		public String getName()
		{
			return this.toString().toLowerCase();
		}
	}
}
