package com.villagetown.villagetown.schematics;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class StructureReader {

    private StructureTemplate template;

    public boolean loadStructure(ResourceManager resourceManager, RegistryAccess registryAccess) {
        ResourceLocation location = new ResourceLocation("villagetown", "nbt/nbt.nbt");

        try {
            Optional<Resource> optionalResource = resourceManager.getResource(location);
            if (optionalResource.isEmpty()) {
                System.out.println("Resource not found: " + location);
                return false;
            }

            Resource resource = optionalResource.get();
            InputStream stream = resource.open();

            CompoundTag nbt = NbtIo.readCompressed(stream);
            stream.close();

            HolderGetter<Block> blockGetter = registryAccess.lookupOrThrow(Registries.BLOCK);
            template = new StructureTemplate();
            template.load(blockGetter, nbt);

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void printBlocks() {
        if (template == null) {
            System.out.println("Template not loaded");
            return;
        }

        CompoundTag nbt = template.save(new CompoundTag());
        ListTag blocks = nbt.getList("blocks", 10); // 10 = TAG_Compound

        for (Tag t : blocks) {
            CompoundTag blockTag = (CompoundTag) t;
            ListTag posTag = blockTag.getList("pos", 3); // 3 = TAG_Int
            CompoundTag stateTag = blockTag.getCompound("state");

            int x = posTag.getInt(0);
            int y = posTag.getInt(1);
            int z = posTag.getInt(2);
            String blockName = stateTag.getString("Name");

            System.out.println("Block: " + blockName + " at " + x + "," + y + "," + z);
        }
    }

    public StructureTemplate getTemplate() {
        return template;
    }
}
