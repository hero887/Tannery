package com.minecraftuberverse.tannery.entity;

import com.minecraftuberverse.tannery.Tannery;
import com.minecraftuberverse.tannery.entity.elk.Elk;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;

//this is a basic Entity class

public class TanneryEntities
{
	public static void init()
	{
		registerEntity();
	}

	public static void registerEntity()
	{
		// Example
		// createEntity(Kangaroo.class, "Kangaroo", 0xDEA02C, 0xE3C286);
		createEntity(Elk.class, "Elk", 0xFFFFFF, 0xFFFFFFF);
	}

	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor)
	{
		int randomId = EntityRegistry.findGlobalUniqueEntityId();

		EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId);
		EntityRegistry.registerModEntity(entityClass, entityName, randomId, Tannery.modInstance, 42,
				1, true);
		createEgg(randomId, solidColor, spotColor);
		//Other devs change the spawn if needed -George
		 EntityRegistry.addSpawn(Elk.class, 62, 3, 7, EnumCreatureType.CREATURE, BiomeGenBase.iceMountains, BiomeGenBase.frozenRiver, BiomeGenBase.icePlains, BiomeGenBase.frozenOcean);
	}

	private static void createEgg(int randomId, int solidColor, int spotColor)
	{
		EntityList.entityEggs.put(Integer.valueOf(randomId),
				new EntityList.EntityEggInfo(randomId, solidColor, spotColor));
	}
}
