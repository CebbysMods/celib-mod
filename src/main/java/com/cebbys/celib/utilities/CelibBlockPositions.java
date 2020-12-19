package com.cebbys.celib.utilities;

import net.minecraft.util.math.BlockPos;

public class CelibBlockPositions {
	public static BlockPos add( BlockPos p1, BlockPos p2 ) {
		return p1.add(p2.getX(), p2.getY(), p2.getZ());
	}
}
