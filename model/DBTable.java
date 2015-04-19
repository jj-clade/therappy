import java.util.List;

public interface DBTable {
	public List<String> getChildrenOf(String name);
	public DBRecord getRecord(String name);
}
