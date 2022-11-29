package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwroofs.kikoz.MacawsRoofs;
import com.mcwroofs.kikoz.init.BlockInit;
import com.mcwroofs.kikoz.objects.roofs.BaseRoof;
import com.mcwroofs.kikoz.objects.roofs.Lower;
import com.mcwroofs.kikoz.objects.roofs.RoofGlass;
import com.mcwroofs.kikoz.objects.roofs.RoofTopNew;
import com.mcwroofs.kikoz.objects.roofs.Steep;
import com.mcwroofs.kikoz.objects.roofs.SteepRoof;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;


public class MacawRoofsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> ATTIC_ROOFS;
    public final SimpleEntrySet<WoodType, Block> LOWER_ROOFS;
    public final SimpleEntrySet<WoodType, Block> ROOFS;
    public final SimpleEntrySet<WoodType, Block> STEEP_ROOFS;
    public final SimpleEntrySet<WoodType, Block> TOP_ROOFS;
    public final SimpleEntrySet<WoodType, Block> UPPER_LOWER_ROOFS;
    public final SimpleEntrySet<WoodType, Block> UPPER_STEEP_ROOFS;

    public MacawRoofsModule(String modId) {
        super(modId, "mcr");
        CreativeModeTab tab = MacawsRoofs.ROOFSITEMGROUP;

        ATTIC_ROOFS = SimpleEntrySet.builder(WoodType.class, "attic_roof",
                        BlockInit.OAK_ATTIC_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RoofGlass(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(ATTIC_ROOFS);

        LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "lower_roof",
                        BlockInit.OAK_LOWER_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(LOWER_ROOFS);

        ROOFS = SimpleEntrySet.builder(WoodType.class, "roof",
                        BlockInit.OAK_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BaseRoof(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(ROOFS);

        STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "steep_roof",
                        BlockInit.OAK_STEEP_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new SteepRoof(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(STEEP_ROOFS);

        TOP_ROOFS = SimpleEntrySet.builder(WoodType.class, "top_roof",
                        BlockInit.OAK_TOP_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new RoofTopNew(Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(TOP_ROOFS);

        UPPER_LOWER_ROOFS = SimpleEntrySet.builder(WoodType.class, "upper_lower_roof",
                        BlockInit.OAK_UPPER_LOWER_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Lower(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(UPPER_LOWER_ROOFS);

        UPPER_STEEP_ROOFS = SimpleEntrySet.builder(WoodType.class, "upper_steep_roof",
                        BlockInit.OAK_UPPER_STEEP_ROOF, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new Steep(Blocks.OAK_PLANKS.defaultBlockState(), Utils.copyPropertySafe(w.log)))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::solid)
                .setTab(() -> tab)
                .defaultRecipe()
                .build();

        this.addEntry(UPPER_STEEP_ROOFS);
    }
}
