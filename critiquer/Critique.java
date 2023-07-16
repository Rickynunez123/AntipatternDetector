package edu.mtu.cs.webta.critiquer;

import java.util.regex.MatchResult;

public class Critique< T > {
   private String name = "";
   private String description = "";

   private Description matchedDescription = null; //?
   private MatchResult trigger = null; //?

   private T source = null; // e.g., Diagnostic
   private String sourceFile = ""; // If relevant

   private String matchText = "";
   private String text = "";
   private String altText = "";


   public Critique( Description matchedDescription, MatchResult trigger, T source,
                    String sourceFile, String text, String altText ) {
      name = matchedDescription.getName( );
      description = matchedDescription.getDescription( );
      this.matchedDescription = matchedDescription;
      this.trigger = trigger;
      this.source = source;
      this.sourceFile = sourceFile;
      this.text = text;
      this.altText = altText;
   }

   public String toString( ) {
      return toString( 0 );
   }

   public String toString( int textChoice ) {
      String result = "ANTIPATTERN: " + matchedDescription.getName( ) + "\n";
      if ( textChoice == 0 ) {
         result += matchedDescription.getDescription() + "\n\n" + text + "\n";
      } else {
         result += altText + "\n";
      }
      return result;
   }


//   public String toString( int textChoice ) {
//      String result = "ANTIPATTERN: " + matchedDescription.getName( ) + "\n";
//      if ( textChoice == 0 ) {
//
//         result += text + "\n";
//      } else {
//         result += altText + "\n";
//      }
//      return result;
//   }


   // GETTER METHODS
   public String getName( ) {
      return name;
   }

   public String getDescription( ) {
      return description;
   }

   public Description getMatchedDescription( ) {
      return matchedDescription;
   }

   public MatchResult getTrigger( ) {
      return trigger;
   }

   public T getSource( ) {
      return source;
   }

   public String getSourceFile( ) {
      return sourceFile;
   }

   public String getText( ) {
      return text;
   }

   public String getAltText( ) {
      return altText;
   }
}
