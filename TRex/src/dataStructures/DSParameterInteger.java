package dataStructures;

public class DSParameterInteger extends DSIntegerAdapter {

	private Integer paramInteger;
	
	@Override
	public void setParameter(Integer paramInteger) {
		this.paramInteger = paramInteger;
	}

	@Override
	public Object getParameter() {
		return this.paramInteger;
	}

}
