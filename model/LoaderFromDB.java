import java.util.ArrayList;
import java.util.List;

public class LoaderFromDB {
	public LoaderFromDB(DBTable table) {
		this.table=table;
	}

	public Tree makeTree(String rootedAt) {
		if (null == table.getRecord(rootedAt)) {
			return null;
		}

		List<String> kids=table.getChildrenOf(rootedAt);
		
		if (0 < kids.size()) {
			ArrayList<Tree> kids=new ArrayList<Tree>();

			for (String s : kids) {
				kids.add(makeTree(s));
			}
			
			return new Tree(rootedAt, kids);
		}
		// no kids -> leaf node

		if (table instanceof MoodDBTable) {
			MoodDBTable mdbt=(MoodDBTable)table;
			return new MoodLeaf(rootedAt, mdbt.getMood(rootedAt).getIntensity());
		}

		return new Leaf(rootedAt);
	}

	private DBTable table;
}
