import java.util.HashSet;

public abstract class Structure {
	public enum Visibility {
		PUBLIC, PRIVATE, PROTECTED, DEFAULT
	};
	
	public enum Scope {
		STATIC, NON_STATIC
	};
	
	protected static HashSet<String> usedName = new HashSet<>();
	
	protected String name;
	
	protected Visibility visibility;
	
	protected Scope scope;
	
	protected Structure container;
	
	public Structure() {
		visibility = Visibility.DEFAULT;
		scope = Scope.NON_STATIC;
	}
	
	/**
	 * @param name name of structure
	 */
	public void setName(String name) {
		this.name = name;
	}
}
