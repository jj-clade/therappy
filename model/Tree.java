import java.util.ArrayList;

public class Tree {
	public Tree(String name, ArrayList<Tree> kids) {
		this.name=name;
		this.kids=kids;
		comment=null;
	}

	public static Tree getEmptyTree() {
		if (null == theEmptyTree) {
			theEmptyTree=new Tree(null, null);
		}

		return theEmptyTree;
	}

	public String getName() { return name; }
	public ArrayList<Tree> getKids() { return kids; }
	public String getComment() { return comment; }
	
	public Tree setComment(String comment) {
		this.comment=comment;
		return this;
	}

	private String name;
	private ArrayList<Tree> kids;
	private String comment;

	private static Tree theEmptyTree=null;
}
