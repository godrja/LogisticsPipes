package logisticspipes.pipefxhandlers.providers;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;

import logisticspipes.pipefxhandlers.GenericSparkleFactory;
import logisticspipes.pipefxhandlers.ParticleProvider;

public class EntityBlueSparkleFXProvider implements ParticleProvider {

    float red = 0F;
    float green = 0F;
    float blue = 1F;

    @Override
    public EntityFX createGenericParticle(WorldClient world, double x, double y, double z, int amount) {

        return GenericSparkleFactory.getSparkleInstance(world, x, y, z, red, green, blue, amount);
    }
}
