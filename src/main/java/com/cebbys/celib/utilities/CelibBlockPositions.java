package com.cebbys.celib.utilities;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class CelibBlockPositions {
	public static BlockPos add( BlockPos p1, BlockPos p2 ) { return p1.add(p2.getX(), p2.getY(), p2.getZ()); }
	public static Vec3d toVec3d( BlockPos p ) { return new Vec3d(p.getX(), p.getY(), p.getZ()); }
}
