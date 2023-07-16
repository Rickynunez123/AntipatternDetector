package edu.mtu.cs.webta.critiquer;

public interface CritiqueDescription {
	public String getName( );
	public String getDescription( );

	public CodingAspect getType( );

	public String getTextTemplate( );
	public String getAltTextTemplate( );
}
