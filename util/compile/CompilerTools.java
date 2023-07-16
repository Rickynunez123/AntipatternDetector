package edu.mtu.cs.webta.util.compile;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CompilerTools {
	//return true if the compilation is successful or false if it failed
	//ARGUMENTS
	// 1. File object source code
	// 2. options is a list of compiler options
	// 3. Listener that processes Diagnostic Object as they are generated (contains information about any error or warning
	// discovered in the code by the compiler)
	//maybe I can create a diagnostic, it will compare all the options with the type of error that was found and the line
   public static boolean compile ( File sourcefile, Iterable< String > options, DiagnosticListener< JavaFileObject > diagnosticListener) {
      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler( );
      /** Gets a new instance of the standard file manager implementation for this tool. The file manager will use the given diagnostic
      listener for producing any non-fatal diagnostics. Fatal errors will be signaled with the appropriate exceptions.*/
      StandardJavaFileManager manager = compiler.getStandardFileManager(diagnosticListener, Locale.ENGLISH, null);

      Iterable< ? extends JavaFileObject > files = manager.getJavaFileObjectsFromFiles( ( List< File > ) Arrays.asList( sourcefile ) );

      boolean result = true;
      for( JavaFileObject file : files ) {
         JavaCompiler.CompilationTask task = compiler.getTask(
              null,
              manager,
              diagnosticListener,
              options,
              null,
              Arrays.asList( file )
            );
         result = task.call( ) && result;
      }
      return result;
   }

	public static List< Diagnostic< ? extends JavaFileObject > > generateDiagnostics(
			String folder, String javaClassname ) {
     	/**Takes the file source */
		File javaSourceFile = new File( folder + "/" + javaClassname + ".java" );
		//this is going to store the entire diagnostic made by the compiler, maybe we can create a new class and send the diagnostic we created

		/**For example:
		 * Claas with a bunch of regular expressions and my code is going to iterate though it until it finds the antipattern, anyway, remember we are going to use a database to store all the regular expressions, write as many as you can.... */


		List< Diagnostic< ? extends JavaFileObject > > diagnosticsList = Collections.synchronizedList( new ArrayList<>( ) );
		Iterable<String > options =  Arrays.asList(
			  "-Xlint:all",
			  "-Xdoclint:all",
			  "-Xmaxerrs", "1000",
			  "-Xmaxwarns", "1000",
			  "-Xdiags:verbose",
			  //"-deprecation",
			  "-source","11",
			  "-target","11",
			  "-g",
			  "-d", folder,
			  "-cp", folder,
			  "-sourcepath", "."
		 );

		compile(javaSourceFile, options, diagnostic -> { diagnosticsList.add(diagnostic); });

		return diagnosticsList;
	}

	public static void main(String [] args){
       List<Diagnostic<? extends JavaFileObject>> po = generateDiagnostics("C:/Users/ricky/eclipse-workspace/MissingSeparator/src","MissingSeparators");
		System.out.println(po);
    }
}
