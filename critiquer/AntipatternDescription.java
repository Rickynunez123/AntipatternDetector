package edu.mtu.cs.webta.critiquer;


public interface AntipatternDescription {
	public String getName( );
	public String getDescription( );

	public AntipatternSource getSource( );

	public String getRegexString( );

}
