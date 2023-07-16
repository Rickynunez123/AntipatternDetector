package edu.mtu.cs.webta.critiquer;

import java.util.regex.Pattern;

public class Description implements AntipatternDescription, CritiqueDescription {
	private String name;
	private String description;

	private CodingAspect type = CodingAspect.TBD;
	private AntipatternSource source;  // AST, Code, Diagnostic, Exception
	private boolean useAltSource = false;

	private String regexString = null;
	private Pattern regexPattern = null;

	private String textTemplate = "";
	private String altTextTemplate = "";


public Description( String name,
						  CodingAspect type,
						  AntipatternSource source,
						  boolean useAltSource,
						  String description,
						  String regexString,
						  String textTemplate,
						  String altTextTemplate
						) {
		this.name = name;
		this.type = type;
		this.source = source;
		this.useAltSource = useAltSource;
		this.description = description;
		this.regexString = regexString;
		regexPattern = Pattern.compile( regexString, Pattern.MULTILINE );
		this.textTemplate = textTemplate;
		this.altTextTemplate = altTextTemplate;
	}

	@Override
	public String getName( ) {
		return name;
	}

	@Override
	public String getDescription( ) {
		return description;
	}

	@Override
	public CodingAspect getType( ) {
		return type;
	}

	@Override
	public AntipatternSource getSource( ) {
		return source;
	}

	public boolean useAltSource ( ) {
		return useAltSource;
	}

	@Override
	public String getRegexString( ) {
		return regexString;
	}


	@Override
	public String getTextTemplate( ) {
		return textTemplate;
	}

	@Override
	public String getAltTextTemplate( ) {
		return altTextTemplate;
	}
}
