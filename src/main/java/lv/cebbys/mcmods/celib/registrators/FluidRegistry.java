package lv.cebbys.mcmods.celib.registrators;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FluidRegistry {
    public static void registerFluid(Fluid fluid, String modId, String fluidId) {
        Registry.register(
                Registry.FLUID,
                new Identifier(modId, fluidId),
                fluid
        );
    }
}
