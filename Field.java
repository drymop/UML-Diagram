
public class Field {
	private String type;
	private String name;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Field))
			return false;
		Field otherField = (Field)other;
		return this.name.equals(otherField.name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
