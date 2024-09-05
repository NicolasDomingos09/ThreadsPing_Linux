package controller;

public class ControllerOS {

	public ControllerOS() {
		super();
	}

	private String identOS() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public boolean getOS() {
		boolean osValido;
		
		if(identOS().contains("Linux")) {
			osValido = true;
		}else {
			osValido = false;
		}
		
		return osValido;
	}
}
