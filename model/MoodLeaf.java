public class MoodLeaf extends Leaf {
	public MoodLeaf(String name, int intensity) {
		super(name);
		this.intensity=intensity;
	}

	public int getIntensity() { return intensity; }

	private int intensity;
}
