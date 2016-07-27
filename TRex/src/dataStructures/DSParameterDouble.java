package dataStructures;

public class DSParameterDouble extends DSDoubleAdapter{
	private Double paramDouble;
	
	@Override
	public void setParameter(Double paramDouble) {
		this.paramDouble = paramDouble;
	}

	@Override
	public Object getParameter() {
		return this.paramDouble;
	}

}
