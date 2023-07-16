package edu.mtu.cs.webta.util.compile;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class diagnosticRegEx  {


//This method regEx work
    public static Map<String, String> regExFunctions() {
        Map<String, String> regEx = new HashMap<>();


        //Function should be in lower case since it is a matlab keyword
//        regEx.put("Function should be in lower case since it is a matlab keyword", "^(?=.*?\\bFunction\\b).*$");
        //FUNCTION should be in lower case since it is a matlab keyword
//        regEx.put("FUNCTION should be in lower case since it is a matlab keyword", "^(?=FUNCTION).*");
        //There should be an space between function and your outputs

        regEx.put("There should be an space between function and your outputs", "^function\\[?[[a-zA-Z]+[a-zA-Z0-9_-]*[/,]\\s?]*\\]?\\s*=+\\s*[a-zA-Z]+[0-9_-]*.*");
        //When you have a function with two outputs, the outputs should be surrounded by "[ ]"
        regEx.put("When you have a function with two or more outputs, the outputs should be surrounded by [ ] not { }", "^function\\s\\{[[a-zA-Z]+[a-zA-Z0-9_-]*[/,]\\s?]*\\}\\s*=+\\s*[a-zA-Z]+[0-9_-]*.*");
        //When you have a function with two outputs, the outputs should be surrounded by "[ ]"
        regEx.put("When you have a function with two or more outputs, the outputs should be surrounded by [ ] not ( )", "^function\\s\\([[a-zA-Z]+[a-zA-Z0-9_-]*[/,]\\s?]*\\)\\s*=+\\s*[a-zA-Z]+[0-9_-]*.*");
        //When you have more than one output you should use a comma between each output
        regEx.put("When you have more than one output you should use a comma between each output", "^(?=.*?\\bfunction\\b)\\s\\[[[a-zA-Z]+[a-zA-Z0-9_-]*\\s?]*\\]\\s*=.*");
        //There should be an space between function and your outputs
        regEx.put("Remember to add space after a comma to make your code more readable, you can find your error inside in between [output1,output2]", "^function\\s+[[\\[[[a-zA-Z0-9]+[a-zA-Z0-9_-]*[/,]]*\\]]*|[[[a-zA-Z]+[a-zA-Z0-9_-]*]*]]+\\s=\\s*[a-zA-Z]+[0-9_-]*.*");
        //For best practice remember to add space after a comma
        regEx.put("Remember to add space after a comma to make your code more readable, you can find your error inside in between (param1,param2)",
                "^function\\s+[[\\[[[a-zA-Z0-9]+[a-zA-Z0-9_-]*[/,]\\s]*\\]]*|[[[a-zA-Z]+[a-zA-Z0-9_-]*]*]]+\\s=\\s*[a-zA-Z]+[0-9_-]*[/(][[a-zA-Z]+[0-9_-]*[/,]?]*[/)]\\s*.*");
        //Remember to add a space before an equal symbol
        regEx.put("Remember to add space before an equal symbol",  "^function\\s+[[\\[[[a-zA-Z0-9]+[a-zA-Z0-9_-]*[/,]\\s]*\\]]*|[[[a-zA-Z]+[a-zA-Z0-9_-]*]*]]+=\\s*[a-zA-Z]+[0-9_-]*[/(][[a-zA-Z]+[0-9_-]*[/,]?]*[/)]\\s*.*");
        //For best practice remember to add a space after an equal symbol
        regEx.put("For best practice remember to add a space before an equal symbol ", "^function\\s+[[\\[[[a-zA-Z0-9]+[a-zA-Z0-9_-]*[/,]\\s]*\\]]*|[[[a-zA-Z]+[a-zA-Z0-9_-]*]*]]+\\s=[a-zA-Z]+[0-9_-]*[/(][[a-zA-Z]+[0-9_-]*[/,]?]*[/)]\\s*.*");
        //For best practice remember to add a space after an equal symbol
        regEx.put("For best practice remember to add a space after an equal symbol ", "^function\\s+[[\\[[[a-zA-Z0-9]+[a-zA-Z0-9_-]*[/,]\\s]*\\]]*|[[[a-zA-Z]+[a-zA-Z0-9_-]*]*]]+=*[a-zA-Z]+[0-9_-]*[/(][[a-zA-Z]+[0-9_-]*[/,]?]*[/)]\\s*.*");
        //Parameters should be surrounded by parentheses
        regEx.put("Parameters should be surrounded by parentheses not {}", "^function\\s+[[\\[[[a-zA-Z]+[a-zA-Z0-9_-]*[/,]\\s]*\\]]*|[[[a-zA-Z]+[a-zA-Z0-9_-]*]*]]+\\s=+\\s*[a-zA-Z]+[0-9_-]*[/{][[a-zA-Z]+[0-9_-]*[/,]?\\s]*[/}]\\s*.*");
        //Parameters should be surrounded by parentheses
        regEx.put("Parameters should be surrounded by parentheses not []","^function\\s+[[\\[[[a-zA-Z]+[a-zA-Z0-9_-]*[/,]\\s]*\\]]*|[[[a-zA-Z]+[a-zA-Z0-9_-]*]*]]+\\s=+\\s*[a-zA-Z]+[0-9_-]*\\[[[a-zA-Z]+[0-9_-]*[/,]?\\s]*\\]\\s*.*");

        //functions should be finalized with the word end
//        regEx.put("functions should be finalized with the word end", "^(?=.*?\\bfunction\\s*)(?!.*end).*");



        //does not
        //if function file name and function name does not match
        //When you have a function with two outputs, the outputs should be surrounded by "[ ]"
//        regEx.put("When you have a function with two or more outputs, the outputs should be surrounded by [ ]", "^\\bfunction\\b\\s[[a-zA-Z]+[a-zA-Z0-9_-]*[/,]\\s]+\\s*=*.*");



//        regEx.put("Break is a matlab keyword and it needs to be in lower case","^(?=.*?\\bBreak\\b).*$");
//        regEx.put("BREAK is a matlab keyword and it needs to be in lower case","^(?=.*?\\bBREAK\\b).*$");
//        regEx.put("break terminates the execution, so it needs to be followed by end","^(?=.*?break\\s*)(?!.*break\\s*end).*");
//        regEx.put("break terminates the execution, so it needs to be followed by end","^(?=.*?\\bbreak\\b\\s*[a-z]\\s*[/=]).*$"); //if is not followed by end


//        regEx.put("Case is a matlab keyword and it needs to be in lower case","^(?=.*?\\bCase\\b).*$");
//        regEx.put("CASE is a matlab keyword and it needs to be in lower case","^(?=.*?\\bCASE\\b).*$");

        regEx.put("Classdef is a matlab keyword and it needs to be in lower case","^(?=.*?\\bClassdef\\b).*$");
        regEx.put("CLASSDEF is a matlab keyword and it needs to be in lower case","^(?=.*?\\bCLASSDEF\\b).*$");



//        regEx.put("When you open a loop you need to close it with an end","^(?=.*?\\bfor\\s*)(?!.*end).*"); //                                  TEST
//        regEx.put("FOR is a matlab keyword and it needs to be in lower case","^(?=.*?\\bFOR\\b).*$");
//        regEx.put("For is a matlab keyword and it needs to be in lower case","^(?=.*?\\bFor\\b).*$");


//        regEx.put("Continue is a matlab keyword and it needs to be in lower case","^(?=.*?\\bContinue\\b).*$");
//        regEx.put("CONTINUE is a matlab keyword and it needs to be in lower case","^(?=.*?\\bCONTINUE\\b).*$");
//        regEx.put("continue terminates the execution, so it needs to be followed by end","^(?=.*?\\bcontinue\\b)(?!.*\\bcontinue\\b\\s\\bend\\b).*$"); //if is not followed by end

//        regEx.put("Else is a matlab keyword and it needs to be in lower case","^(?=.*?\\bElse\\b).*$");
//        regEx.put("ELSE is a matlab keyword and it needs to be in lower case","^(?=.*?\\bELSE\\b).*$");


//        regEx.put("Elseif is a matlab keyword and it needs to be in lower case","^(?=.*?\\bEsleif\\b).*$");
//        regEx.put("ELSEIF is a matlab keyword and it needs to be in lower case","^(?=.*?\\bELSEIF\\b).*$");
//        regEx.put("elseIf is a matlab keyword and it needs to be in lower case","^(?=.*?\\belseIf\\b).*$");
//        regEx.put("ElseIf is a matlab keyword and it needs to be in lower case","^(?=.*?\\bElseIf\\b).*$");
//        regEx.put("else if is incorrect syntax, it should be elseif","^(?=.*?\\belse if\\b).*$");
//        regEx.put("if else incorrect syntax, it should be elseif","^(?=.*?\\bif else\\b).*$");
//        regEx.put("ifelse incorrect syntax, it should be elseif","^(?=.*?\\bifelse\\b).*$");


//        regEx.put("End is a matlab keyword and it needs to be in lower case","^(?=.*?\\bEnd\\b).*$");
//        regEx.put("END is a matlab keyword and it needs to be in lower case","^(?=.*?\\bEND\\b).*$");


        regEx.put("Global is a matlab keyword and it needs to be in lower case","^(?=.*?\\bGlobal\\b).*$");
        regEx.put("GLOBAL is a matlab keyword and it needs to be in lower case","^(?=.*?\\bGLOBAL\\b).*$");


//        regEx.put("IF is a matlab keyword and it needs to be in lower case","^(?=.*?\\bIF\\b).*$");
//        regEx.put("If is a matlab keyword and it needs to be in lower case","^(?=.*?\\bIf\\b).*$");
//
//
//        regEx.put("Otherwise is a matlab keyword and it needs to be in lower case","^(?=.*?\\bOtherwise\\b).*$");
//        regEx.put("OTHERWISE is a matlab keyword and it needs to be in lower case","^(?=.*?\\bOTHERWISE\\b).*$");
//
//
//        regEx.put("Return is a matlab keyword and it needs to be in lower case","^(?=.*?\\bReturn\\b).*$");
//        regEx.put("RETURN is a matlab keyword and it needs to be in lower case","^(?=.*?\\bRETURN\\b).*$");


        regEx.put("Parfor is a matlab keyword and it needs to be in lower case","^(?=.*?\\bParfor\\b).*$");
        regEx.put("PARFOR is a matlab keyword and it needs to be in lower case","^(?=.*?\\bPARFOR\\b).*$");

        regEx.put("Persistent is a matlab keyword and it needs to be in lower case","^(?=.*?\\bPersistent\\b).*$");
        regEx.put("PERSISTENT is a matlab keyword and it needs to be in lower case","^(?=.*?\\bPERSISTENT\\b).*$");


        regEx.put("SPMD is a matlab keyword and it needs to be in lower case","^(?=.*?\\bSPMD\\b).*$");
        regEx.put("Spmd is a matlab keyword and it needs to be in lower case","^(?=.*?\\bSpmd\\b).*$");

//        regEx.put("Switch is a matlab keyword and it needs to be in lower case","^(?=.*?\\bSwitch\\b).*$");
//        regEx.put("SWITCH is a matlab keyword and it needs to be in lower case","^(?=.*?\\bSWITCH\\b).*$");

        regEx.put("TRY is a matlab keyword and it needs to be in lower case","^(?=.*?\\bTRY\\b).*$");
        regEx.put("Try is a matlab keyword and it needs to be in lower case","^(?=.*?\\bTry\\b).*$");

//        regEx.put("While is a matlab keyword and it needs to be in lower case","^(?=.*?\\bWhile\\b).*$");
//        regEx.put("WHILE is a matlab keyword and it needs to be in lower case","^(?=.*?\\bWHILE\\b).*$");

        //continue from here

//        regEx.put("CLC is a matlab keyword and it needs to be in lower case","^(?=.*?\\bCLC\\b).*$");
//        regEx.put("Clc is a matlab keyword and it needs to be in lower case","^(?=.*?\\bCls\\b).*$");
//
//        regEx.put("Clear is a matlab keyword and it needs to be in lower case","^(?=.*?\\bClear\\b).*$");
//        regEx.put("CLEAR is a matlab keyword and it needs to be in lower case","^(?=.*?\\bCLEAR\\b).*$");

        regEx.put("Limit is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLimit\\b).*$");
        regEx.put("LIMIT is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLIMIT\\b).*$");

        regEx.put("Diff is a matlab keyword and it needs to be in lower case","^(?=.*?\\bDiff\\b).*$");
        regEx.put("DIFF is a matlab keyword and it needs to be in lower case","^(?=.*?\\bDIFF\\b).*$");

        regEx.put("functionalderivative is a matlab keyword and it needs to be in lower case","^(?=.*?\\bfunctionalderivative\\b).*$");
        regEx.put("FUNCTIONALDERIVATIVE is a matlab keyword and it needs to be in lower case","^(?=.*?\\bFUNCTIONALDERIVATIVE\\b).*$");

        regEx.put("INT is a matlab keyword and it needs to be in lower case","^(?=.*?\\bINT\\b).*$");
        regEx.put("Int is a matlab keyword and it needs to be in lower case","^(?=.*?\\bInt\\b).*$");
        regEx.put("Syntax error, int syntax is the following int() not int[]","^(?=.*?\\bint\\b)(?!.*int[\\(]).*$");                             //     TEST 23


        regEx.put("Release is a matlab keyword and it needs to be in lower case","^(?=.*?\\bRelease\\b).*$");
        regEx.put("RELEASE is a matlab keyword and it needs to be in lower case","^(?=.*?\\bRELEASE\\b).*$");
        regEx.put("Syntax error, check release","^(?=.*?\\brelease\\b)(?!.*release[\\(]).*$");

        regEx.put("Reset is a matlab keyword and it needs to be in lower case","^(?=.*?\\bReset\\b).*$");
        regEx.put("RESET is a matlab keyword and it needs to be in lower case","^(?=.*?\\bRESET\\b).*$");
        regEx.put("Syntax error, check reset","^(?=.*?\\breset\\b)(?!.*reset[\\(]).*$");


        regEx.put("Potential is a matlab keyword and it needs to be in lower case","^(?=.*?\\bPotential\\b).*$");
        regEx.put("POTENTIAL is a matlab keyword and it needs to be in lower case","^(?=.*?\\bPOTENTIAL\\b).*$");

        regEx.put("Series is a matlab keyword and it needs to be in lower case","^(?=.*?\\bSeries\\b).*$");
        regEx.put("SERIES is a matlab keyword and it needs to be in lower case","^(?=.*?\\bSERIES\\b).*$");

        regEx.put("Taylor is a matlab keyword and it needs to be in lower case","^(?=.*?\\bTaylor\\b).*$");
        regEx.put("TAYLOR is a matlab keyword and it needs to be in lower case","^(?=.*?\\bTAYLOR\\b).*$");

        regEx.put("Null is a matlab keyword and it needs to be in lower case","^(?=.*?\\bNull\\b).*$");
        regEx.put("NULL is a matlab keyword and it needs to be in lower case","^(?=.*?\\bNULL\\b).*$");

        regEx.put("EXPM is a matlab keyword and it needs to be in lower case","^(?=.*?\\bEXPM\\b).*$");
        regEx.put("Expm is a matlab keyword and it needs to be in lower case","^(?=.*?\\bExpm\\b).*$");

          regEx.put("Do not use i as a variable name since it also means an imaginary number","^(?=.*?\\bi\\b\\s?=).*$");
          regEx.put("Do not use j as a variable name since it also means an imaginary number","^(?=.*?\\bi\\b\\s?=).*$");

        regEx.put("Funm is a matlab keyword and it needs to be in lower case","^(?=.*?\\bFunm\\b).*$");
        regEx.put("FUNM is a matlab keyword and it needs to be in lower case","^(?=.*?\\bFUNM\\b).*$");

        regEx.put("Logm is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLogm\\b).*$");
        regEx.put("LogM is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLogM\\b).*$");
        regEx.put("LOGM is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLOGM\\b).*$");

        regEx.put("SQRTM is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLOGM\\b).*$");
        regEx.put("Sqrtm is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLOGM\\b).*$");

        regEx.put("Assume is a matlab keyword and it needs to be in lower case","^(?=.*?\\bAssume\\b).*$");
        regEx.put("ASSUME is a matlab keyword and it needs to be in lower case","^(?=.*?\\bASSUME\\b).*$");

        regEx.put("IN is a matlab keyword and it needs to be in lower case","^(?=.*?\\bIN\\b).*$");
        regEx.put("In is a matlab keyword and it needs to be in lower case","^(?=.*?\\bIn\\b).*$");

        regEx.put("Logical is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLogical\\b).*$");
        regEx.put("LOGICAL is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLOGICAL\\b).*$");

        regEx.put("ROOT is a matlab keyword and it needs to be in lower case","^(?=.*?\\bROOT\\b).*$");
        regEx.put("Root is a matlab keyword and it needs to be in lower case","^(?=.*?\\bRoot\\b).*$");

        regEx.put("LOG is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLOG\\b).*$");
        regEx.put("Log is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLog\\b).*$");
        regEx.put("LOG10 is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLOG10\\b).*$");
        regEx.put("Log10 is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLog10\\b).*$");
        regEx.put("Log2 is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLog2\\b).*$");
        regEx.put("LOG2 is a matlab keyword and it needs to be in lower case","^(?=.*?\\bLOG2\\b).*$");


//        //Input is a matlab keyword and it needs to be in lower case
//        regEx.put("Input is a matlab keyword and it needs to be in lower case","^(?=.*?\\bInput\\b[\\(].*[\\)]).*$"); //!!!!!
//        //INPUT is a matlab keyword and it needs to be in lower case
//        regEx.put("INPUT is a matlab keyword and it needs to be in lower case","^(?=.*?\\bINPUT\\b).*$");
        //Remember to use single quotes not double
        regEx.put("Use single quotes for strings NOT double", "^(?=.*?\\binput\\b[\\([\"]].*[\"][\\)]).*$");
        //input uses ( )  not [ ]
        regEx.put("input uses ( ) not [ ]","^(?=.*?\\binput\\b[\\[].*[\\]]).*$");
        //input uses ( )  not { }
        regEx.put("input uses ( ) not { }","^(?=.*?\\binput\\b[\\{].*[\\}]).*$");
        //input is empty,
        regEx.put("Your input is empty","^(?=.*?\\binput\\b[\\(][\\)]).*$");
        //input should not end with ; since the main point of the input is to ask for the input of the user
        regEx.put("Remember input should not end with ; since the main point of the input is to ask for the input of the user","^(?=.*?\\binput\\b[\\(].*[\\)])[\\;].*$");

        //fprintf is used for all output statements
//        //fprintf is a keyword and it needs to be in lower case
//        regEx.put("Fprintf is a matlab keyword and it needs to be in lower case","^(?=.*?\\bFprintf\\b).*$");
//        //FPRINTF is a keyword and it needs to be in lower case
//        regEx.put("FPRINTF is a matlab keyword and it needs to be in lower case","^(?=.*?\\bFPRINTF\\b).*$");
        //Remember to use single quotes not double
        regEx.put("Use single quotes for strings not double", "^(?=.*?\\bfprintf\\b[\\([\"]].*[\"][\\)]).*$");
        //fprintf uses () not []
        regEx.put("fprintf uses ( ) not [ ]","^(?=.*?\\bfprintf\\b[\\[].*[\\]]).*$");
        //fprintf uses ( )  not { }
        regEx.put("fprintf uses ( ) not { }","^(?=.*?\\bfprintf\\b[\\{].*[\\}]).*$");


        //disp
        //Disp is a keyword and it needs to be in lower case
//        regEx.put("Disp is a matlab keyword and it needs to be in lower case","^(?=.*?\\bDisp\\b).*$");
//        //DISP is a keyword and it needs to be in lower case
//        regEx.put("DISP is a matlab keyword and it needs to be in lower case","^(?=.*?\\bDISP\\b).*$");
        //Remember to use single quotes not double
        regEx.put("Remember to use single quotes for strings NOT double", "^(?=.*?\\bdisp\\b[\\([\"]].*[\"][\\)]).*$");
        //fprintf uses () not []
        regEx.put("disp uses ( ) not [ ]","^(?=.*?\\bdisp\\b[\\[].*[\\]]).*$");
        //fprintf uses ( )  not { }
        regEx.put("disp uses ( ) not { }","^(?=.*?\\bdisp\\b[\\{].*[\\}]).*$");


        //sprintf
        //fprintf is a keyword and it needs to be in lower case
//        regEx.put("Sprintf is a matlab keyword and it needs to be in lower case","^(?=.*?\\bSprintf\\b).*$");
//        //FPRINTF is a keyword and it needs to be in lower case
//        regEx.put("SPRINTF is a matlab keyword and it needs to be in lower case","^(?=.*?\\bSPRINTF\\b).*$");
        //Remember to use single quotes not double
        regEx.put("Do not use double quotes for strings", "^(?=.*?\\bsprintf\\b[\\([\"]].*[\"][\\)]).*$");
        //fprintf uses () not []
        regEx.put("sprintf uses ( ) not [ ]","^(?=.*?\\bsprintf\\b[\\[].*[\\]]).*$");
        //fprintf uses ( )  not { }
        regEx.put("sprintf uses ( ) not { }","^(?=.*?\\bsprintf\\b[\\{].*[\\}]).*$");





        return regEx;
    }





//        regEx.put("Funm is a matlab keyword and it needs to be in lower case",);
//        regEx.put("FUNM is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("Logm is a matlab keyword and it needs to be in lower case",);
//        regEx.put("LogM is a matlab keyword and it needs to be in lower case",);
//        regEx.put("LOGM is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("SQRTM is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Sqrtm is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("Assume is a matlab keyword and it needs to be in lower case",);
//        regEx.put("ASSUME is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("IN is a matlab keyword and it needs to be in lower case",);
//        regEx.put("In is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("Logical is a matlab keyword and it needs to be in lower case",);
//        regEx.put("LOGICAL is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("ROOT is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Root is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("LOG is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Log is a matlab keyword and it needs to be in lower case",);
//        regEx.put("LOG10 is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Log10 is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Log2 is a matlab keyword and it needs to be in lower case",);
//        regEx.put("LOG2 is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("Zeta is a matlab keyword and it needs to be in lower case",);
//        regEx.put("ZETA is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("SIN is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Sin is a matlab keyword and it needs to be in lower case",);
//        regEx.put("SINC is a matlab keyword and it needs to be in lower case",);
//        regEx.put("SinC is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Sinc is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Cos is a matlab keyword and it needs to be in lower case",);
//        regEx.put("COS is a matlab keyword and it needs to be in lower case",);
//        regEx.put("TAN is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Tan is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Cot is a matlab keyword and it needs to be in lower case",);
//        regEx.put("COT is a matlab keyword and it needs to be in lower case",);
//        regEx.put("SEC is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Sec is a matlab keyword and it needs to be in lower case",);
//        regEx.put("CSC is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Csc is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("ASIN is a matlab keyword and it needs to be in lower case",);
//        regEx.put("ASin is a matlab keyword and it needs to be in lower case",);
//        regEx.put("aCos is a matlab keyword and it needs to be in lower case",);
//        regEx.put("ACOS is a matlab keyword and it needs to be in lower case",);
//        regEx.put("aTan is a matlab keyword and it needs to be in lower case",);
//        regEx.put("ATAN is a matlab keyword and it needs to be in lower case",);
//        regEx.put("ACOT is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Acot is a matlab keyword and it needs to be in lower case",);
//        regEx.put("ASEC is a matlab keyword and it needs to be in lower case",);
//        regEx.put("ASec is a matlab keyword and it needs to be in lower case",);
//        regEx.put("ACSc is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Acsc is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("ABS is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Abs is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Real is a matlab keyword and it needs to be in lower case",);
//        regEx.put("REAL is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("Factorial is a matlab keyword and it needs to be in lower case",);
//        regEx.put("FACTORIAL is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Gamma is a matlab keyword and it needs to be in lower case",);
//        regEx.put("GAMMA is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("Divisors is a matlab keyword and it needs to be in lower case",);
//        regEx.put("DIVISORS is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("MAX is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Max is a matlab keyword and it needs to be in lower case",);
//        regEx.put("Min is a matlab keyword and it needs to be in lower case",);
//        regEx.put("MIN is a matlab keyword and it needs to be in lower case",);
//
//        regEx.put("Mod is a matlab keyword and it needs to be in lower case",);
//        regEx.put("MOD is a matlab keyword and it needs to be in lower case",);
//



    public static Map<String, String> regExPlot(){
        Map<String, String> regEx = new HashMap<>();
        //vectors matrices cell arrays
        //row vector x = [2 4 6] or [2, 3, 4]
        //Column vector y = [2; 3; 4]
        //Matrix z = [2 4 6; 3 5 7] or [2, 4, 6; 3, 5, 7]


        //x = 1:2:10
        //names = txt(2:5,1);
        //goals = num(:,1);


        //different ways to import files -- do not use  .mat or .m files
        //importData
        //A = importData('filename')


        //Use MATLAB load to bring in data from a .dat or .csv file.
        //myData = load('datafile.csv'); it can be csv, txt, .dat or many others
        //vector_name = my_data(:,1); extracts the first column
        //: this means all in this case being in the first position means all rows, but first column (:,1)


        //Use MATLAB xlsread to bring in data from an excel file.
        //[num,txt,raw] = xlsread('datafile.xls');

        //because strings are arrays of characters, and to compare arrays with '==' they must be the same size.
        //plus(2,4)


        //elseif
        //wrong way
        //if user wants JPEG output
        //    file = name + .JPG
        //    else file = name + .TIF

        //right
        //if user wants JPEG output
        //    file = [ 'name' '.jpg' ];
        //    else file = [ 'name' '.tif' ];


        //to compare character by character use ==
        //Strings must be the same length




        //plot  should not be finalized with ;
        //plot(x1,y1,'ok',x2,y2,'sb')
        //it only allows b,g,r,c,m,y,k,w,.,o,x,+,*,s,d,v,^,<,>,p,h,-,s,:,-.,--,(none)
        //hold on
        //legend()
        //xlabel()
        //ylabel()
        //title()
        //sin() cos() linspace()
        //hold off
        // clear, clc, clf
        //Plot should be in lower case since it is a matlab keyword
        regEx.put("Plot should be in lower case since it is a matlab keyword", "^(?=Plot).*");
        //PLOT should be in lower case since it is a matlab keyword
        regEx.put("PLOT should be in lower case since it is a matlab keyword", "^(?=PLOT).*");


        //subplot to plot different graphs
        /**cfl
         * figure(1)
         * subplot(2, 3, 4)
         * plot(1: 0.1: 10, sin(1: 0.1: 10), '-b'
         * subplot(2, 3, 5:6)
         * plot(1: 0.1:20, cos(1:0.1:20), '-r');
         * */
        //defining axis limits axis([xmin,xmax,ymin,ymax])
        //Adding text to plot
//        text(x,y,string)
//        text(x,y,string,Name,Value)

        /**text(x, y, 'text to display', 'FontSize', 14, 'HorizontalAlignment', 'left'); */



//        x = [1 2 3 4 5];
//        y = [1 4 9 16 25];
//        plot(x,y,'.’);
//        title('My title’);
//        xlabel('xvar, x (units)');
//        ylabel('yvar, y (units)');


        return regEx;
    }



    public static Map<String, String> regExLoops(){
        Map<String, String> regEx = new HashMap<>();
        //For should be in lower case since it is a matlab keyword
        regEx.put("For should be in lower case since it is a matlab keyword", "^(?=For).*");
        //FOR should be in lower case since it is a matlab keyword
        regEx.put("FOR should be in lower case since it is a matlab keyword", "^(?=FOR).*");
        //Remember to write end to close the loops
        regEx.put("Remember to write end to close the loops","^for[[a-zA-Z0-9_-:]+[[for][.*][end]]]?end$");


        //While should be in lower case since it is a matlab keyword
        regEx.put("While should be in lower case since it is a matlab keyword", "^(?=While).*");
        //WHILE should be in lower case since it is a matlab keyword
        regEx.put("WHILE should be in lower case since it is a matlab keyword", "^(?=WHILE).*");
        //to close the loop you should write end
        regEx.put("Remember to write end to close the while loop","^while[[a-zA-Z0-9_-:]+[[while][.*][end]]]?end$");

        return regEx;
    }


    //NOW CREATE A METHOD THAT COMPARES THE REGULAR EXPRESSION WITH THE ANTIPATTERN
    //This method is going to check which function matches with the regular expression
    public String matchMaker(File file) throws FileNotFoundException {
        boolean result = false;
        Scanner scan = new Scanner(file);

        //scanning file
        StringBuilder fileReader = new StringBuilder();
        while (scan.hasNext()) {
            fileReader.append(scan.nextLine()); //does the scanner reads line by line or word by word, so it stops when there is a space
        }


        for (Map.Entry<String, String> reg : regExFunctions().entrySet()) {
            while (reg.getValue() != null && !result) {
                Pattern pt = Pattern.compile(reg.getValue());
                Matcher mt = pt.matcher(fileReader.toString());
                result = mt.matches();
                if (result) {
                    return reg.getKey();
                }
                break;
            }
        }

        return "Match not found";
    }

    public String findParameters(File file) throws FileNotFoundException {
        String fileReader;
        Scanner scan = new Scanner(file);
        StringBuilder fileReaderBuilder = new StringBuilder();
        while (scan.hasNext()) {
            fileReaderBuilder.append(scan.nextLine());
        }
        fileReader = fileReaderBuilder.toString();

        int parenthesis = fileReader.indexOf("(");
        int parenthesis2 = fileReader.indexOf(")");

        String param = fileReader.substring(parenthesis + 1, parenthesis2 + 1);
        if (param.contains(",")) {
            String[] result = param.split(",");
            return Arrays.toString(result);
        }
        return param;
    }


    public String findOutputs(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        StringBuilder fileReader = new StringBuilder();
        while (scan.hasNext()) {
            fileReader.append(scan.nextLine());
        }
        int outputIndex = fileReader.indexOf("function");
        int outputIndex2 = fileReader.indexOf("=");

        String checkNumberOfOutputs = fileReader.substring(outputIndex + 1, outputIndex2);
        if (checkNumberOfOutputs.contains(",")) {
            // get the index of the output
            int squareBrackets = fileReader.indexOf("[");
            int squareBrackets2 = fileReader.indexOf("]");
            //get the string between the parenthesis
            String outputWithComma = fileReader.substring(squareBrackets + 1, squareBrackets2);
            String[] output = outputWithComma.split(",");
            return Arrays.toString(output);
        }
        return checkNumberOfOutputs;
    }

    public String checkFunction(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line2;
        br.readLine();
        StringBuilder line = new StringBuilder();
        while ((line2 = br.readLine()) != null) {
            line.append(line2).append("\n");
        }
        return line.toString();
    }

    public boolean isEmpty(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        int from = 0;
        int to = 0;

        //scanning the file
        StringBuilder fileReader = new StringBuilder();
        while(scan.hasNext()){
            fileReader.append(scan.nextLine());
        }

        //get indexes
             from = fileReader.indexOf(")");
             to = fileReader.indexOf("end");


        //get the function body
        String functionsBody = fileReader.substring(from + 1, to);
        if(functionsBody.isEmpty()){
            return true; }
        return false;
    }

    //This method is going to make sure that the parameters and outputs are being used in the function
    public String compareBody(File file) throws IOException {
        String match = matchMaker(file);
        if(!match.equals("Match not found")){ return match;}
        if (isEmpty(file)) return "The function is empty";
        if(match.equals("Match not found")){
            String param = findParameters(file);
            String output = findOutputs(file);
            String functionBody = checkFunction(file);
            if(!functionBody.contains(param) && !functionBody.contains(output)){
                return "Your function should contain the parameters and outputs that you specified";
            }
            else if(!functionBody.contains(param)){
                return "You function does not contain the specified parameters";
            }
            else if(!functionBody.contains(output)){
                return "Your function does not contain the specified outputs";
            }
        }
        return match;
    }



    public static void main(String[] org) throws IOException {
        String matlabClassName = "keywords";
        diagnosticRegEx critiquer = new diagnosticRegEx();
        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/imaginaryNumberI.m");
        System.out.println("Diagnostic Critiques for " + matlabClassName + ".m");
        System.out.println(critiquer.compareBody(file));



    }
}
//I can check for all the errors and make a list that is going to iterate through all the errors and it will say which errors are true
//Save everything in another hash map
//MAKE A METHOD THAT WILL ITERATE THROUGH ALL THE REGEX and depending if it matches or does not matches if will throw more errors
//create different classes for different syntax errors they can make