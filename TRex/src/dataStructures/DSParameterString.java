package dataStructures;

public class DSParameterString extends DSStringAdapter {

	private String paramString;
	
	@Override
	public void setParameter(String paramString) {
		this.paramString = paramString;
		
	}

	@Override
	public Object getParameter() {
		return this.paramString;
	}

}
