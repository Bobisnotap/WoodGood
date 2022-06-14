package net.mehvahdjukaar.every_compat.modules.quark;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.resourcepack.BlockTypeResTransformer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.Nullable;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.base.module.QuarkModule;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

class QuarkSimpleEntrySet<T extends BlockType, B extends Block> extends SimpleEntrySet<T, B> {

    private final BiFunction<T, QuarkModule, B> blockSupplier;
    private final Class<? extends vazkii.quark.base.module.QuarkModule> quarkModule;

    public QuarkSimpleEntrySet(Class<T> type,
                               String name, @Nullable String prefix,
                               Class<? extends vazkii.quark.base.module.QuarkModule> module,
                               Supplier<B> baseBlock, Supplier<T> baseType,
                               BiFunction<T, vazkii.quark.base.module.QuarkModule, B> blockSupplier,
                               CreativeModeTab tab, boolean copyLoot,
                               @Nullable TriFunction<T, B, Item.Properties, Item> itemFactory,
                               @Nullable SimpleEntrySet.TileHolder<?> tileFactory,
                               @Nullable Supplier<Supplier<RenderType>> renderType,
                               @Nullable BiFunction<T, ResourceManager, Pair<List<Palette>, @Nullable AnimationMetadataSection>> paletteSupplier,
                               @Nullable Consumer<BlockTypeResTransformer<T>> extraTransform) {
        super(type, name, prefix, null, baseBlock, baseType, tab, copyLoot, itemFactory, tileFactory, renderType, paletteSupplier, extraTransform);
        this.blockSupplier = blockSupplier;
        this.quarkModule = module;
    }

    @Override
    public void registerBlocks(CompatModule module, IForgeRegistry<Block> registry, Collection<T> woodTypes) {
        Block base = baseBlock.get();
        if (base == null)
            throw new UnsupportedOperationException("Base block cant be null");
        baseType.get().addChild(module.shortenedId() + "/" + typeName, base);

        for (T w : woodTypes) {
            String name = module.makeBlockId(w, this.postfix);
            if (w.isVanilla() || module.isEntryAlreadyRegistered(name, w, registry)) continue;
            var m = ModuleLoader.INSTANCE.getModuleInstance(quarkModule);
            B block = blockSupplier.apply(w, m);
            if (block != null) {
                this.blocks.put(w, block);

                registry.register(block); //does not set registry name
                w.addChild(module.shortenedId() + "/" + typeName, block);
            }
        }
    }

    //this does not work. all modules seem to be disabled here. why??
    /*
    @Override
    protected CreativeModeTab getTab(T w, B b) {
        boolean e = b instanceof IQuarkBlock qb ? qb.isEnabled() : ModuleLoader.INSTANCE.isModuleEnabled(quarkModule);
        return e ? super.getTab(w, b) : null;
    }*/

    public static <T extends BlockType, B extends Block> QuarkSimpleEntrySet.Builder<T, B> builder(
            Class<T> type,
            String name,
            Class<? extends vazkii.quark.base.module.QuarkModule> quarkModule,
            Supplier<B> baseBlock, Supplier<T> baseType,
            BiFunction<T, QuarkModule, B> factory) {
        return new QuarkSimpleEntrySet.Builder<>(type, name, null, quarkModule, baseType, baseBlock, factory);
    }

    public static <T extends BlockType, B extends Block> QuarkSimpleEntrySet.Builder<T, B> builder(
            Class<T> type,
            String name, String prefix,
            Class<? extends vazkii.quark.base.module.QuarkModule> quarkModule,
            Supplier<B> baseBlock, Supplier<T> baseType,
            BiFunction<T, QuarkModule, B> factory) {
        return new QuarkSimpleEntrySet.Builder<>(type, name, prefix, quarkModule, baseType, baseBlock, factory);
    }

    public static class Builder<T extends BlockType, B extends Block> extends SimpleEntrySet.Builder<T, B> {

        private final BiFunction<T, QuarkModule, B> blockSupplier;
        private final Class<? extends vazkii.quark.base.module.QuarkModule> quarkModule;

        protected Builder(Class<T> type, String name, @Nullable String prefix,
                          Class<? extends vazkii.quark.base.module.QuarkModule> quarkModule,
                          Supplier<T> baseType, Supplier<B> baseBlock, BiFunction<T, QuarkModule, B> factory) {
            super(type, name, prefix, baseType, baseBlock, null);
            this.quarkModule = quarkModule;
            this.blockSupplier = factory;
        }

        @Override
        public QuarkSimpleEntrySet<T, B> build() {
            var e = new QuarkSimpleEntrySet<>(type,name, prefix, quarkModule,
                    baseBlock, baseType, blockSupplier, tab, copyLoot,
                    itemFactory, tileFactory, renderType, palette, extraModelTransform);
            e.recipeLocations.addAll(this.recipes);
            e.tags.putAll(this.tags);
            e.textures.addAll(textures);
            return e;
        }
    }

}