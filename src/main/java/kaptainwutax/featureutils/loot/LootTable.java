package kaptainwutax.featureutils.loot;

import kaptainwutax.featureutils.loot.function.LootFunction;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.loot.roll.UniformRoll;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LootTable extends LootGenerator {

    public final LootPool[] lootPools;

    public LootTable(LootPool... lootPools) {
        this(Arrays.asList(lootPools), null);
    }

    public LootTable(Collection<LootPool> lootPools, Collection<LootFunction> lootFunctions) {
        this.lootPools = lootPools.toArray(new LootPool[0]);
        this.apply(lootFunctions);
    }

    public LootTable apply(LootFunction... lootFunctions) {
        this.apply(Arrays.asList(lootFunctions));
        return this;
    }

    @Override
    public void generate(LootContext context, Consumer<ItemStack> stackConsumer) {
        stackConsumer = LootFunction.stack(stackConsumer, this.combinedLootFunction, context);

        for (LootPool lootPool : this.lootPools) {
            lootPool.generate(context, stackConsumer);
        }
    }

    public LinkedList<ItemStack> generateIndexed(LootContext context){
        LinkedList<ItemStack> itemStacks = new LinkedList<>();
        this.generate(context, itemStacks::add);
        return shuffleItems(context,itemStacks);
    }

    public List<ItemStack> generate(LootContext context) {
        Map<Item, Integer> itemCounts = new HashMap<>();
        List<ItemStack> itemStacks = new ArrayList<>();

        this.generate(context, stack -> {
            int oldCount = itemCounts.getOrDefault(stack.getItem(), 0);
            itemCounts.put(stack.getItem(), oldCount + stack.getCount());
        });

        for (Map.Entry<Item, Integer> e : itemCounts.entrySet()) {
            itemStacks.add(new ItemStack(e.getKey(), e.getValue()));
        }

        return itemStacks;
    }

    public static LinkedList<ItemStack> shuffleItems(LootContext context, LinkedList<ItemStack> items) {
        List<Integer> container = IntStream.range(0,27).boxed().collect(Collectors.toList());
        context.shuffle(container);
        List<ItemStack> list = new ArrayList<>();
        Iterator<ItemStack> iterator = items.iterator();
        int size = container.size();
        while (iterator.hasNext()) {
            ItemStack itemstack = iterator.next();
            if (itemstack.isEmpty()) {
                iterator.remove();
            } else if (itemstack.getCount() > 1) {
                list.add(itemstack);
                iterator.remove();
            }
        }

        while (size - items.size() - list.size() > 0 && !list.isEmpty()) {
            ItemStack itemstack2 = list.remove(new UniformRoll(0, list.size() - 1).getCount(context));
            int half = (itemstack2.getCount() / 2);
            int i = new UniformRoll(1, half).getCount(context);
            ItemStack itemstack1 = itemstack2.split(i);
            if (itemstack2.getCount() > 1 && context.nextBoolean()) {
                list.add(itemstack2);
            } else {
                items.add(itemstack2);
            }

            if (itemstack1.getCount() > 1 && context.nextBoolean()) {
                list.add(itemstack1);
            } else {
                items.add(itemstack1);
            }
        }

        items.addAll(list);
        context.shuffle(items);
        HashMap<Integer,ItemStack> positions=new HashMap<>();
        int len=container.size();
        for(ItemStack itemstack : items) {
            if (container.isEmpty()) {
                return items; // that's an exception but better return something than nothing
            }

            if (itemstack.isEmpty()) {
                positions.put(container.remove(container.size()-1),ItemStack.EMPTY);
            } else {
                positions.put(container.remove(container.size()-1),itemstack);
            }
        }

        LinkedList<ItemStack> result=new LinkedList<>();
        for (int i = 0; i < len; i++) {
            result.add(positions.getOrDefault(i,ItemStack.EMPTY));
        }
        return result;
    }

}
