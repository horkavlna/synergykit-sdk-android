package com.letsgood.synergykit.resource;

public class SynergyKITConfig {
	/* Attributes */
	private boolean parallelMode = false;
	private SynergyKITUri synergyKITUri = null;
	
	/* Parallel mode getter */
	public boolean isParallelMode() {
		return parallelMode;
	}
	
	/* Parallel mode setter */
	public void setParallelMode(boolean parallelMode) {
		this.parallelMode = parallelMode;
	}
	
	/* Uri getter */
	public SynergyKITUri getUri() {
		return synergyKITUri;
	}
	
	/* Uri setter */
	public void setUri(SynergyKITUri synergyKITUri) {
		this.synergyKITUri = synergyKITUri;
	}
	
	/* Valid getter */
	public boolean isValid(){
		
		//Uri
		if(synergyKITUri==null || synergyKITUri.getUri().length()==0)
			return false;
		
		return true;
	}
}
