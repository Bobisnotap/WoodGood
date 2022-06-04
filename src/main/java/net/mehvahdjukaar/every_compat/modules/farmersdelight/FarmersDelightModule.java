package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class FarmersDelightModule extends SimpleModule {

    public FarmersDelightModule(String modId) {
        super(modId, "fd");

        SimpleEntrySet<?, ?> cabinets = SimpleEntrySet.builder("cabinet",
                        ModBlocks.OAK_CABINET, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new CompatCabinetBlock(BlockBehaviour.Properties.copy(w.planks).strength(2.5F)))
                .addTag(modRes("cabinets"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("cabinets"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .defaultRecipe()
                .addTile(CompatCabinetBlockTile::new)
                .setTab(FarmersDelight.CREATIVE_TAB)
                .createPaletteFromOak(Palette::increaseDown)
                .addTexture(WoodGood.res("block/oak_cabinet_front"))
                .addTexture(WoodGood.res("block/oak_cabinet_side"))
                .addTexture(WoodGood.res("block/oak_cabinet_top"))
                .addMaskedTexture(WoodGood.res("block/oak_cabinet_front_open"), WoodGood.res("block/oak_cabinet_front_open_m"))
                .build();

        this.addEntry(cabinets);
    }
}
