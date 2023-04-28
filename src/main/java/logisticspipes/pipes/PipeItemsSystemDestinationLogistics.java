package logisticspipes.pipes;

import java.util.UUID;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

import logisticspipes.LogisticsPipes;
import logisticspipes.modules.abstractmodules.LogisticsModule;
import logisticspipes.network.GuiIDs;
import logisticspipes.pipefxhandlers.Particles;
import logisticspipes.pipes.basic.CoreRoutedPipe;
import logisticspipes.textures.Textures;
import logisticspipes.textures.Textures.TextureType;
import logisticspipes.utils.item.ItemIdentifierInventory;

public class PipeItemsSystemDestinationLogistics extends CoreRoutedPipe {

    public ItemIdentifierInventory inv = new ItemIdentifierInventory(1, "Freq Slot", 1);

    public PipeItemsSystemDestinationLogistics(Item item) {
        super(item);
    }

    @Override
    public ItemSendMode getItemSendMode() {
        return ItemSendMode.Normal;
    }

    @Override
    public TextureType getCenterTexture() {
        return Textures.LOGISTICSPIPE_DESTINATION_TEXTURE;
    }

    @Override
    public LogisticsModule getLogisticsModule() {
        return null;
    }

    public Object getTargetUUID() {
        if (inv.getStackInSlot(0) == null) {
            return null;
        }
        if (!inv.getStackInSlot(0).hasTagCompound()) {
            return null;
        }
        if (!inv.getStackInSlot(0).getTagCompound().hasKey("UUID")) {
            return null;
        }
        spawnParticle(Particles.WhiteParticle, 2);
        return UUID.fromString(inv.getStackInSlot(0).getTagCompound().getString("UUID"));
    }

    @Override
    public void onAllowedRemoval() {
        dropFreqCard();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        inv.writeToNBT(nbttagcompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        inv.readFromNBT(nbttagcompound);
    }

    private void dropFreqCard() {
        if (inv.getStackInSlot(0) == null) {
            return;
        }
        EntityItem item = new EntityItem(getWorld(), getX(), getY(), getZ(), inv.getStackInSlot(0));
        getWorld().spawnEntityInWorld(item);
        inv.clearInventorySlotContents(0);
    }

    @Override
    public void onWrenchClicked(EntityPlayer entityplayer) {
        entityplayer.openGui(LogisticsPipes.instance, GuiIDs.GUI_Freq_Card_ID, getWorld(), getX(), getY(), getZ());
    }
}
