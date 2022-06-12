package net.mehvahdjukaar.every_compat.modules.quark;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.quark.content.building.block.be.VariantChestBlockEntity;

public class CompatChestBlockTile extends VariantChestBlockEntity {

    protected CompatChestBlockTile(BlockPos pos, BlockState state) {
        super(QuarkModule.CHEST_TILE, pos, state);
    }


}
