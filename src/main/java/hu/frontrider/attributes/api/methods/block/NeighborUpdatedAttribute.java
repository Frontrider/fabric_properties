package hu.frontrider.attributes.api.methods.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface NeighborUpdatedAttribute {
    void neighborUpdate(BlockState state, World world, BlockPos selfPos, Block targetBlock, BlockPos targetPos);
}
