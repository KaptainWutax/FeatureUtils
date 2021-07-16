package kaptainwutax.featureutils.decorator.ore;

import kaptainwutax.seedutils.rand.JRand;

import java.util.function.Function;

import static kaptainwutax.featureutils.decorator.ore.HeightProvider.MathHelper.nextBetween;

public interface HeightProvider {

	//<=1.16.5
	static HeightProvider range(int bottomOffset, int topOffset, int maximumY) {
		return r -> r.nextInt(maximumY - topOffset) + bottomOffset;
	}

	//<=1.16.5
	static HeightProvider depthAverage(int baseline, int spread) {
		return r -> r.nextInt(spread) + r.nextInt(spread) - spread + baseline;
	}

	//>=1.17
	static HeightProvider uniformRange(int minOffset, int maxOffset) {
		return r -> nextBetween(r, minOffset, maxOffset);
	}

	//>=1.17
	static HeightProvider triangleRange(int minOffset, int maxOffset) {
		return r -> {
			int k = maxOffset - minOffset;
			if(0 >= k) {
				return nextBetween(r, minOffset, maxOffset);
			} else {
				int l = k / 2;
				int m = k - l;
				return minOffset + nextBetween(r, 0, m) + nextBetween(r, 0, l);
			}
		};
	}

	static HeightProvider custom(Function<JRand, Integer> func) {
		return func::apply;
	}

	int getY(JRand rand);

	class MathHelper {
		static int nextBetween(JRand rand, int min, int max) {
			return rand.nextInt(max - min + 1) + min;
		}
	}

}
