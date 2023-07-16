package edu.mtu.cs.webta.critiquer;

public enum AntipatternSource {
	DIAGNOSTIC("Diagnostic"),
	EXCEPTION("Exception");


	public final String label;
	private AntipatternSource( String label ) {
		this.label = label;
	}
}
