package kaptainwutax.featureutils.loot.effect.attribute;

public class Attribute {
    private final double value;
    private final String description;
    private boolean syncable;

    protected Attribute(String description, double value) {
        this.value = value;
        this.description = description;
    }

    public double getValue() {
        return this.value;
    }

    public boolean isClientSyncable() {
        return this.syncable;
    }

    public Attribute setSyncable(boolean syncable) {
        this.syncable = syncable;
        return this;
    }

    public double sanitizeValue(double current) {
        return current;
    }

    public String getDescription() {
        return this.description;
    }
}
