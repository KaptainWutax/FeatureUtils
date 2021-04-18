package kaptainwutax.featureutils.structure.device;

public class ParentInfo {

	public final ParentInfo parent;
	public final int x;
	public final int z;
	public final boolean present;

	public ParentInfo(ParentInfo parent, int x, int z, boolean present) {
		this.parent = parent;
		this.x = x;
		this.z = z;
		this.present = present;
	}

}
