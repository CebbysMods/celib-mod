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

	public CelibBlockPos(BlockPos pos) {
		super(pos.getX(), pos.getY(), pos.getZ());
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

	public BlockPos add(Vec3d p) {
		setX((int) (getX() + p.x));
		setX((int) (getY() + p.y));
		setX((int) (getZ() + p.z));

		return this;
	}

	public BlockPos sub(BlockPos p) {
		setX(getX() - p.getX());
		setY(getY() - p.getY());
		setZ(getZ() - p.getZ());

		return this;
	}

	public BlockPos sub(Vec3d p) {
		setX((int) (getX() - p.x));
		setX((int) (getY() - p.y));
		setX((int) (getZ() - p.z));

		return this;
	}

	public BlockPos sub(Vec3i p) {
		setX(getX() - p.getX());
		setX(getY() - p.getY());
		setX(getZ() - p.getZ());

		return this;
	}

	public BlockPos sub(int x, int y, int z) {
		setX(getX() - x);
		setX(getY() - y);
		setX(getZ() - z);

		return this;
	}

	public BlockPos sub(double x, double y, double z) {
		setX((int) (getX() - x));
		setX((int) (getY() - y));
		setX((int) (getZ() - z));

		return this;
	}

	public Vec3d toVec3d() {
		return new Vec3d(getX(), getY(), getZ());
	}

	public Vec3i toVec3i() {
		return new Vec3i(getX(), getY(), getZ());
	}

	public static CelibBlockPos ofVec3d(Vec3d vec) {
		return new CelibBlockPos(vec.x, vec.y, vec.z);
	}

	public static CelibBlockPos ofVec3i(Vec3d vec) {
        return new CelibBlockPos(vec.x, vec.y, vec.z);
    }
}
