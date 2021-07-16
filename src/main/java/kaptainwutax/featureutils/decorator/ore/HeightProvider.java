package kaptainwutax.featureutils.decorator.ore;

import kaptainwutax.seedutils.rand.JRand;

import java.util.function.Function;

import static kaptainwutax.featureutils.decorator.ore.HeightProvider.MathHelper.nextBetween;

public interface HeightProvider {

	//<=1.16.5
	static HeightProvider range(int bottomOffset, int topOffset, int maximumY) {
		return rand -> rand.nextInt(maximumY - topOffset) + bottomOffset;
	}

	//<=1.16.5
	static HeightProvider depthAverage(int baseline, int spread) {
		return rand -> rand.nextInt(spread) + rand.nextInt(spread) - spread + baseline;
	}

	//>=1.17
	static HeightProvider uniformRange(int minOffset, int maxOffset) {
		return rand -> {
			if(minOffset > maxOffset) {
				return minOffset;
			}
			return nextBetween(rand, minOffset, maxOffset);
		};
	}

	//>=1.17
	static HeightProvider triangleRange(int minOffset, int maxOffset) {
		return rand -> {
			if(minOffset > maxOffset) {
				return minOffset;
			}
			int range = maxOffset - minOffset;
			if(range <= 0) {
				return nextBetween(rand, minOffset, maxOffset);
			} else {
				int midPoint = range / 2;
				int midPoint2 = range - midPoint;
				return minOffset + nextBetween(rand, 0, midPoint2) + nextBetween(rand, 0, midPoint);
			}
		};
	}

	static HeightProvider spreadRange(int baseValue,int spread){
		return rand -> spread==0?baseValue:baseValue+rand.nextInt(spread+1);
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
