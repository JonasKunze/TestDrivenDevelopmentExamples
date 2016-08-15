package junitExample;

public class Histogram {
	private final float firstBin;
	private final float binWidth;
	private short buckets[];

	public Histogram(float firstBin, float lastBin, int binNum) {
		assert(binNum > 1);
		assert(firstBin < lastBin);
		this.firstBin = firstBin;
		this.binWidth = (lastBin-firstBin)/(binNum - 1);
		buckets = new short[binNum];
	}
	
	public float getBinWidth() {
		return binWidth;
	}
	
	public boolean insert(float value) {
		float x = value - firstBin + binWidth/2.0f;
		int bucket = (int)(x/binWidth);
		if(x < 0 || bucket > buckets.length - 1) {
			return false;
		}
		buckets[bucket]++;
		return true;
	}
	
	public int getIntegral() {
		int integral = 0;
		for (short bucket : buckets) {
			integral += bucket;
		}
		return integral;
	}
	
	public short getBinHeight(int bin) throws ArrayIndexOutOfBoundsException{
		return buckets[bin];
	}
	
	public short getLargestBinHeight(){
		short maxHeight = 0;
		for (short height : buckets) {
			if(height > maxHeight) {
				maxHeight = height;
			}
		}
		return maxHeight;
	}
	
	public float getAverage() {
		float average = 0;
		int integral = 0;
		for (int x = 0; x < buckets.length; x++) {
			average += buckets[x] * (x*binWidth + firstBin);
			integral += buckets[x];
		}
		return average / integral;
	}

	@Override
	public String toString() {
		short largestBinHeight = getLargestBinHeight();
				
		StringBuilder str = new StringBuilder(largestBinHeight*buckets.length*2);
		for (int y = largestBinHeight; y > 0; --y) {
			for (int x = 0; x < buckets.length; ++x) {
				if(buckets[x] >= y) {
					str.append('*');
				} else {
					str.append(' ');
				}
			}
			str.append('\n');
		}
		str.append(generateXAxis());

		return str.toString();
	}
	
	private String generateXAxis(){
		StringBuilder str = new StringBuilder(2*(buckets.length));
		for (int i = 0; i < buckets.length; i++) {
			str.append("-");
		}
		str.append('\n');
		String firstBinStr = firstBin+"";
		String lastBinStr = (firstBin+binWidth*(buckets.length-1))+"";
		str.append(firstBinStr);
		for(int x = firstBinStr.length(); x < buckets.length-lastBinStr.length(); ++x) {
			str.append(' ');
		}
		str.append(lastBinStr);
		return str.toString();
	}

}
