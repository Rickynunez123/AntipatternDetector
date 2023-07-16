package edu.mtu.cs.webta.critiquer;

public enum CodingAspect {
	STRUCTURE("Structure" ),
	BEHAVIOR("Behavior" ),
	STYLE("Style"),
	TBD("TBD");

	public final String label;

	private CodingAspect( String label ) {
		this.label = label;
	}
}