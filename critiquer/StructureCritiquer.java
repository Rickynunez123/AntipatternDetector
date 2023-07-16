package edu.mtu.cs.webta.critiquer;

import edu.mtu.cs.webta.util.compile.CompilerTools;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StructureCritiquer {
   public static final Description[] ANTIPATTERN_CRITIQUE_DESCRIPTIONS = {
        new Description(
             "MISSING_SEPARATOR", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
             false,
             "Triggered when code is missing a something, such as a semicolon or a parenthesis.",
             "^^function\\s+\\[?[[a-zA-Z]+[a-zA-Z0-9_-]*[/,]?]*\\]?\\s+=\\s+[a-zA-Z]+[0-9_-]*[/(][[a-zA-Z]+[0-9_-]*[/,]?]*[/)]\\s*" +
                     "[[a-zA-Z]+[a-zA-Z0-9_-]*\\s?=?\\s?[[[a-zA-Z0-9_-]*[[/(][a-zA-Z0-9:_-]*[/)]]?\\s?]*\\s[*+/-]?[[/^][/w]+]?\\s?]+\\s?]{1,70}end$",
             "ERROR:\n{0}\nThe compiler is looking for a missing ''{6}'' somewhere near line {3} in file {2}.\nIf you don''t see the problem at that location,\ncarefully read backwards through the code\nlooking for the missing character or some other problem.\n",
             "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
        ),

   };

   public List< Critique< Diagnostic< ? extends JavaFileObject > > > generateStructureCritiques(
        String folder,
        String javaClassname,
        Description[] descriptions
                             ) {
      //File javaSourceFile = new File( folder + "/" + javaClassname + ".jav" );


      List< Critique< Diagnostic< ? extends JavaFileObject > > > critiques = Collections.synchronizedList( new ArrayList<>( ) );

      ArrayList< String > options = new ArrayList< String> ( Arrays.asList(
           "-Xdoclint:all",
           "-deprecation",
           "-cp", folder ) );
      CompilerTools.generateDiagnostics(folder, javaClassname).forEach(diagnostic -> {
              critiques.add(
                   CritiquerTools.< Diagnostic< ? extends JavaFileObject > >
                           makeCritique(
                        diagnostic,
                        diagnostic.getSource( ).getName( ),
                        descriptions) );
           } );
      return critiques;
   }

   public static void main( String[] args ) {

      String folder = "C:/Users/ricky/eclipse-workspace/MissingSeparator/src";
      String javaClassname =  "MissingSeparators";
      StructureCritiquer critiquer = new StructureCritiquer( );
      System.out.println( "Diagnostic Critiques for " + javaClassname + ".java" );
      critiquer.generateStructureCritiques( folder, javaClassname, ANTIPATTERN_CRITIQUE_DESCRIPTIONS ).forEach( System.out::println );
   }
}
