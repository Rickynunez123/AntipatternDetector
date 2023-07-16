package edu.mtu.cs.webta.critiquer;


import java.io.File;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;




public class CritiquerTools {
   public static final Description DEFAULT_STRUCTURE_DESCRIPTION = new Description(
           "STRUCTURE_ERROR", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC, false,
           "Triggered when code an unrecognized error occurs.",
           "[\\S\\s$]*",
           "ERROR:\n{0}\n\nTry your hand at fixing this error.\nIf you find yourself spending more that 30-60 days on this, ask your instructor for help.\n",
           "ERROR:\n{0}\n"
   );



   public static List<Description> regEx(String folder, String matlabFileName) {
      File matLabSourceFile = new File(folder + "/" + matlabFileName + ".m");

      List<Description> regEx = List.of(
              new Description(
                      "STRUCTURE_ERROR", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC, false,
                      "Triggered when code an unrecognized error occurs.",
                      "[\\S\\s$]*",
                      "ERROR:\n{0}\n\nTry your hand at fixing this error.\nIf you find yourself spending more that 30-60 days on this, ask your instructor for help.\n",
                      "ERROR:\n{0}\n"
              ),
              new Description(
                      "STRUCTURE_ERROR", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC, false,
                      "Triggered when code an unrecognized error occurs.",
                      "[\\S\\s$]*",
                      "ERROR:\n{0}\n\nTry your hand at fixing this error.\nIf you find yourself spending more that 30-60 days on this, ask your instructor for help.\n",
                      "ERROR:\n{0}\n"
              ));

      return regEx;

   }

   //I can use an array or a list
   //add all the regular expressions
   public static final Description[] ANTIPATTERN_CRITIQUE_DESCRIPTIONS = {
           new Description(
                   "MISSING_SEPARATOR", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                   false,
                   "Triggered when code is missing a something, such as a semicolon or a parenthesis.",
                   "^(?=Function).*",
                   "ERROR:\n{0}\nThe compiler is looking for a missing ''{6}'' somewhere near line {3} in file {2}.\nIf you don''t see the problem at that location,\ncarefully read backwards through the code\nlooking for the missing character or some other problem.\n",
                   "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
           )
           ,
           new Description(
                   "STRUCTURE_SYNTAX", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC, false,
                   "Syntax error, check int",
                   "^(?=.*?\\bint\\b)(?!.*int[\\(]).*$",
                   "ERROR:\n{0}\n\nTry your hand at fixing this error.\nIf you find yourself spending more that 30-60 days on this, ask your instructor for help.\n",
                   "ERROR:\n{0}\n"
           )


   };


   public static Map<Boolean, String> matchRegEx(File sourceFile, Iterable<String> iterate, List<Description> regEx) {

      //create a method that is going to help get all the required data nad then return it
      Map<Boolean, String> result =
              regEx.stream().collect(Collectors.partitioningBy(
                      m -> m.getRegexString() == " ",
                      Collectors.mapping(Description::getRegexString,
                              Collectors.joining(","))));

      System.out.println(result);

      return null;

   }


   public static List<MatchResult> getMatchResults(
           String regexString,
           String text) throws PatternSyntaxException {
      List<MatchResult> matches = new ArrayList<>();
      if (regexString != null && !regexString.isEmpty()) {
         Pattern regexPattern = Pattern.compile(regexString, Pattern.MULTILINE);
         Matcher matcher = regexPattern.matcher(text);
         while (matcher.find()) {
            matches.add(matcher.toMatchResult());
         }
      }
      return matches;
   }


   public static <S> Critique<S> makeCritique(
           S source,
           String sourceFile,
           Description[] descriptions) {
      Critique<S> critique = null;
      boolean critiqueFound = false;

      for (Description description : descriptions) {
         if (description.getSource() != AntipatternSource.DIAGNOSTIC) {
            continue;
         }
         List<MatchResult> matchResults = getMatchResults(
                 description.getRegexString(),
                 source.toString());


         //this is going to loop and it is going to send all the errors found in the code
         if (!matchResults.isEmpty()) {
            critiqueFound = true;

            //it will get all the errors
            MatchResult matchResult = matchResults.get(0);
            String[] matchedGroups = new String[matchResult.groupCount() + 1];
            for (int i = 0; i < matchedGroups.length; i++) {
               matchedGroups[i] = matchResult.group(i);
            }

            //display all the errors
            String matchedText = matchedGroups[0];
            String critiqueText = MessageFormat.format(description.getTextTemplate(), matchedGroups);
            String altCritiqueText = MessageFormat.format(description.getAltTextTemplate(), matchedGroups);
            critique = new Critique<>(
                    description,
                    matchResult,
                    source,
                    sourceFile,
                    critiqueText,
                    altCritiqueText
            );
         }
      }


      //this will run if the critique was not found
      if (!critiqueFound) {
         Description defaultDescription = DEFAULT_STRUCTURE_DESCRIPTION;
         List<MatchResult> matchResults = getMatchResults(defaultDescription.getRegexString(), source.toString());
         String critiqueText = MessageFormat.format(defaultDescription.getTextTemplate(), source);
         String altCritiqueText = MessageFormat.format(defaultDescription.getAltTextTemplate(), source);
         for (MatchResult critiqueMatch : matchResults) {
            critique = new Critique<S>(
                    defaultDescription,
                    critiqueMatch,
                    source,
                    sourceFile,
                    critiqueText,
                    altCritiqueText
            );
         }
      }
      return critique;
   }



//   public static < S extends Failure> Critique< S > makeCritique(S source,
//                                                                 String sourceFile,
//                                                                 Description[] descriptions ) {
//		Critique< S > critique = null;
//		boolean critiqueFound = false;
////		System.out.println(">"+source.getDescription( ).getAnnotations());
//      String hint = "";
//       //source.getDescription( ).getAnnotation( org.junit.Test.class ).hint;
//      for ( Description description : descriptions ) {
//			if ( description.getSource( ) != AntipatternSource.EXCEPTION ) { continue; }
//         List< MatchResult > matchResults = getMatchResults(
//              description.getRegexString( ),
//              description.useAltSource( )
//                   ? UnitTestTools.getFilteredTrace(source )
//                   : UnitTestTools.getTrimmedTrace( source ) );
//			if ( !matchResults.isEmpty( ) ) {
//				critiqueFound = true;
//            MatchResult matchResult = matchResults.get( 0 );
//            String[] matchedGroups = new String[ matchResult.groupCount( ) + 1 ];
//            for ( int i = 0; i < matchedGroups.length; i++ ) {
//               matchedGroups[ i ] = matchResult.group( i );
//            }
//            String matchedText = matchedGroups[ 0 ];
//            String critiqueText = MessageFormat
//                                       .format( description.getTextTemplate( ),
//                                                matchedGroups );
//            String altCritiqueText = MessageFormat
//                                          .format( description.getAltTextTemplate( ),
//                                                   matchedGroups );
//            critique = new Critique<>(
//                 description,
//                 matchResult,
//                 source,
//                 sourceFile,
//                 critiqueText,
//                 altCritiqueText
//            );
//			}
//		}
//		if ( !critiqueFound ) {
//			Description defaultDescription = DEFAULT_BEHAVIOR_DESCRIPTION;
//			critique = new Critique<S>(
//					  defaultDescription,
//					  null,
//					  source,
//					  sourceFile,
//                 UnitTestTools.getTrimmedTrace( source) + "\n\n" + ( hint != null ? hint : "" ),
//                 UnitTestTools.getTrimmedTrace( source) + "\n\n" + ( hint != null ? hint : "" ));
//		}
//		return critique;
//	}

   public static void main(String[] args) {
      String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/testing.m";

      AntipatternSource diagnostic = AntipatternSource.DIAGNOSTIC;
       Critique<AntipatternSource> critique = makeCritique(diagnostic, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
      System.out.println(critique);
   }
}
