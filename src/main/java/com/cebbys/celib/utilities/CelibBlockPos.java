package com.cebbys.celib.utilities;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class CelibBlockPos extends BlockPos {
	public CelibBlockPos(int i, int j, int k) {
		super(i, j, k);
	}

	public CelibBlockPos(double d, double e, double f) {
		super(d, e, f);
	}

	public CelibBlockPos(Vec3d pos) {
		super(pos);
	}

	public CelibBlockPos(Position pos) {
		super(pos);
	}

	public CelibBlockPos(Vec3i pos) {
		super(pos);
	}

	public BlockPos add(BlockPos p) {
		setX(getX() + p.getX());
		setY(getY() + p.getY());
		setZ(getZ() + p.getZ());
		return this;
	}

	public BlockPos sub(BlockPos p) {
		setX(getX() - p.getX());
		setY(getY() - p.getY());
		setZ(getZ() - p.getZ());
		return this;
	}

	public Vec3d toVec3d() {
		return new Vec3d(getX(), getY(), getZ());
	}

	public Vec3i toVec3i() {
		return new Vec3i(getX(), getY(), getZ());
	}

	public static CelibBlockPos ofVec3d(Vec3d vec) {
		return new CelibBlockPos(vec.getX(), vec.getY(), vec.getZ());
	}

	public static CelibBlockPos ofVec3i(Vec3i vec) {
		return new CelibBlockPos(vec.getX(), vec.getY(), vec.getZ());
	}
}
