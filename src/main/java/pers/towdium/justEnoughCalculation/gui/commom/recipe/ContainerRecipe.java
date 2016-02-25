package pers.towdium.justEnoughCalculation.gui.commom.recipe;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import pers.towdium.justEnoughCalculation.JustEnoughCalculation;
import pers.towdium.justEnoughCalculation.core.ItemStackWrapper;
import pers.towdium.justEnoughCalculation.core.Recipe;


/**
 * @author Towdium
 */
public class ContainerRecipe extends Container {

    InventoryRecipe inventoryRecipe;

    public ContainerRecipe(){
        inventoryRecipe = new InventoryRecipe();
        int i, left, top;
        i = 0; left = 9; top = 9;
        for(int a = 0; a < 2; a++){
            for(int b =0; b < 2; b++){
                addSlotToContainer(new Slot(inventoryRecipe,i+2*a+b,left+b*59,top+a*24));
            }
        }
        i = 4; left = 9; top = 69;
        for(int a = 0; a < 4; a++){
            for(int b =0; b < 3; b++){
                addSlotToContainer(new Slot(inventoryRecipe,i+3*a+b,left+b*59,top+a*24));
            }
        }
    }

    @Override
    public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer playerIn) {
        if(slotId<0 || slotId>=16){
            return null;
        }
        Slot slot = getSlot(slotId);
        if(slot != null){
            ItemStack itemStack = slot.inventory.getStackInSlot(slot.getSlotIndex());
            if(itemStack != null){
                if(clickedButton == 0 && mode == 0){
                    ItemStackWrapper.Click.leftClick(slot.getStack(), true);
                }else if(clickedButton == 0 && mode == 1){
                    ItemStackWrapper.Click.leftShift(slot.getStack(), true);
                }else if(clickedButton == 1 && mode == 0){
                    ItemStackWrapper.Click.rightClick(slot.getStack(), true);
                }else if(clickedButton == 1 && mode == 1){
                    ItemStackWrapper.Click.rightShift(slot.getStack(), true);
                }
                slot.putStack(itemStack.stackSize == 0 ? null : itemStack);
            }
        }
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return null;
    }

    public Recipe buildRecipe(){
        ItemStack[] output = new ItemStack[4];
        boolean flag1 = false, flag2 = false;
        for(int i=0; i<4; i++){
            output[i] = getSlot(i).getStack();
            if(output[i] != null){
                flag1 = true;
            }
        }
        ItemStack[] input = new ItemStack[12];
        for(int i=4; i<16; i++){
            input[i-4] = getSlot(i).getStack();
            if(input[i-4] != null){
                flag2 = true;
            }
        }
        return flag1 && flag2 ? new Recipe(output, input) : null;
    }
}
