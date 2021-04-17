package kaptainwutax.featureutils.loot.effect.attribute;

import kaptainwutax.seedutils.rand.JRand;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

public class AttributeModifier {
    private final double amount;
    private final AttributeModifier.Operation operation;
    private final Supplier<String> nameGetter;
    private final UUID uuid;

    public AttributeModifier(String name, double amount, AttributeModifier.Operation operation) {
        this(createInsecureUUID(new JRand(0)), () -> name, amount, operation);
    }

    public AttributeModifier(UUID uuid, String name, double amount, AttributeModifier.Operation operation) {
        this(uuid, () -> name, amount, operation);
    }

    public AttributeModifier(UUID uuid, Supplier<String> name, double amount, AttributeModifier.Operation p_i50377_5_) {
        this.uuid = uuid;
        this.nameGetter = name;
        this.amount = amount;
        this.operation = p_i50377_5_;
    }

    public static UUID createInsecureUUID(JRand rand) {
        long mostSigBits = rand.nextLong() & -61441L | 16384L;
        long leastSigBits = rand.nextLong() & 4611686018427387903L | Long.MIN_VALUE;
        return new UUID(mostSigBits, leastSigBits);
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getName() {
        return this.nameGetter.get();
    }

    public AttributeModifier.Operation getOperation() {
        return this.operation;
    }

    public double getAmount() {
        return this.amount;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            AttributeModifier attributemodifier = (AttributeModifier) other;
            return Objects.equals(this.uuid, attributemodifier.uuid);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.uuid.hashCode();
    }

    public String toString() {
        return "AttributeModifier{amount=" + this.amount + ", operation=" + this.operation + ", name='" + (String) this.nameGetter.get() + '\'' + ", id=" + this.uuid + '}';
    }


    public enum Operation {
        ADDITION(0),
        MULTIPLY_BASE(1),
        MULTIPLY_TOTAL(2);

        private static final AttributeModifier.Operation[] OPERATIONS = new AttributeModifier.Operation[] {ADDITION, MULTIPLY_BASE, MULTIPLY_TOTAL};
        private final int value;

        private Operation(int p_i50050_3_) {
            this.value = p_i50050_3_;
        }

        public static AttributeModifier.Operation fromValue(int opId) {
            if (opId >= 0 && opId < OPERATIONS.length) {
                return OPERATIONS[opId];
            } else {
                throw new IllegalArgumentException("No operation with value " + opId);
            }
        }

        public int toValue() {
            return this.value;
        }
    }
}