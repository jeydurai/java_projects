package dataStructures;

public abstract class DSStringAdapter implements DSCustomizable {

	public void setParameter(String paramString) {}
	public void setParameter(Integer paramInteger) {}
	public void setParameter(Double paramDouble) {};
	public void setParameter(Float paramFloat) {};
	
	public Object getParameter() {
		return null;
	}
}
