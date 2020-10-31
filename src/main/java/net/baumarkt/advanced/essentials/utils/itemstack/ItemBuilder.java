package net.baumarkt.advanced.essentials.utils.itemstack;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {

    private final Material material;
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        this.material = material;
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack itemStack) {
        this.material = itemStack.getType();
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setAmount(final int amount){
        this.itemStack.setAmount(amount);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setDisplayName(final String displayname){
        this.itemMeta.setDisplayName(displayname);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addItemFlag(final ItemFlag itemFlag){
        this.itemMeta.addItemFlags(itemFlag);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addLore(final String... lore){
        this.itemMeta.setLore(Arrays.asList(lore));
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack build(){
        return itemStack;
    }
}
