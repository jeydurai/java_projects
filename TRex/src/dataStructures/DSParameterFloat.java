package dataStructures;

public class DSParameterFloat extends DSFloatAdapter{
	private Float paramFloat;
	
	@Override
	public void setParameter(Float paramFloat) {
		this.paramFloat = paramFloat;
	}

	@Override
	public Object getParameter() {
		return this.paramFloat;
	}

}
