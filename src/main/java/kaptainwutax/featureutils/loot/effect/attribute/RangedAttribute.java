package kaptainwutax.featureutils.loot.effect.attribute;

import kaptainwutax.terrainutils.utils.MathHelper;

public class RangedAttribute extends Attribute {
	private final double minValue;
	private final double maxValue;

	public RangedAttribute(String name, double value, double minValue, double maxValue) {
		super(name, value);
		this.minValue = minValue;
		this.maxValue = maxValue;
		if(minValue > maxValue) {
			throw new IllegalArgumentException("Minimum value cannot be bigger than maximum value!");
		} else if(value < minValue) {
			throw new IllegalArgumentException("Default value cannot be lower than minimum value!");
		} else if(value > maxValue) {
			throw new IllegalArgumentException("Default value cannot be bigger than maximum value!");
		}
	}

	public double getMinValue() {
		return minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public double sanitizeValue(double current) {
		return MathHelper.clamp(current, this.minValue, this.maxValue);
	}
}
