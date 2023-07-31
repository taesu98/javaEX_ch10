package p;

public class Child {
	
	private int age;
	int height;
	boolean parent;
	boolean heartDisease;
	String[] attractions;
	
	public Child() {
		
	}
	
//	public Child(int age, int height, boolean parent, boolean heartDisease) {
//		this.age = age;
//		this.height = height;
//		this.parent = parent;
//		this.heartDisease = heartDisease;
//	}
	
	public int getAge() {
		return age;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}

	public boolean isHeartDisease() {
		return heartDisease;
	}

	public void setHeartDisease(boolean heartDisease) {
		this.heartDisease = heartDisease;
	}

	public String[] getAttractions() {
		if(this.attractions == null) {
			this.attractions = new String[0];
		}
		return attractions;
	}

	public void setAttractions(String[] attractions) {
		this.attractions = attractions;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean isCanRide() {
		boolean rtn = false;

	  if (!this.heartDisease) { 
		  if (this.age >= 6 && this.height >= 120) { 
			  rtn = true; 
			  } else { 
		if(this.height >= 120 && this.parent) { 
			rtn = true;
	  }
	}
}
	return rtn;
	}
	
	public String toString() {
		String rtn = null;

		StringBuilder sb = new StringBuilder();
		for (String attraction : attractions) {
			sb.append(attraction);
			sb.append(",");

		}
		rtn = String.format("%s\t%s\t%s\t%s\t", age, height, parent, heartDisease);
		rtn += sb.toString();
		return rtn;

	}
}
