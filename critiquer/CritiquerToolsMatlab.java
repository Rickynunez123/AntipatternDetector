package edu.mtu.cs.webta.critiquer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class CritiquerToolsMatlab {
    public static final Description DEFAULT_STRUCTURE_DESCRIPTION = new Description(
            "STRUCTURE_ERROR", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC, false,
            "Triggered when code an unrecognized error occurs.",
            "[\\S\\s$]*",
            "ERROR:\nTry your hand at fixing this error.\nIf you find yourself spending more that 30-60 days on this, ask your instructor for help.\n",
            "ERROR:\n{0}\n"
    );


    public static final Description[] ANTIPATTERN_CRITIQUE_DESCRIPTIONS = {
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Function should be in lower case since it is a MATLAB keyword",
                    "^(?=.*?\\bFunction\\b).*",
                    "ERROR: {0}\nThe interpreter/critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "FUNCTION should be in lower case since it is a MATLAB keyword",
                    "^(?=.*?\\bFUNCTION\\b).*",
                    "ERROR: {0}\nThe compiler/interpreter found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Variable_Name_Keyword", CodingAspect.STYLE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "function has the same name as a MATLAB builtin. We suggest you rename the function to avoid a potential name conflict.",
                    "^(?=.*?\\bfunction\\b\\s?=).*$",
                    "WARNING: {0}\nThe compiler found a warning in line ''{1}'' {2}\n\nIf you don''t see the problem at that location,\ncarefully read backwards through the code to find the warning. ",
                    //do we want something more specific? Do we want to give a link to the list of the matlab build in functions?
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,//4
            new Description(
                    "Function_Missing_End", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "functions should be finalized with the word end",
                    "(?s)(?=.*?\\bfunction\\b\\s?\\[\\b)(?!.*?\\bend\\b).*",
                    "ERROR: {0}\nThe compiler found an error in: \n\n{2}end <- MISSING \n \nIf you don''t see the problem at that location,\ncarefully read backwards through the code\nlooking for the missing character or some other problem.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,//5
            new Description(
                    "Function_Output_Style", CodingAspect.STYLE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "functions outputs should be surrounded by [ ]",
                    "^(?=.*?\\bfunction\\b\\s.*)(?!.*\\bfunction\\b\\s?=)(?!.*\\bfunction\\s?\\[\\b).*",
                    "WARNING: {0}\nThe compiler found a warning in line: \n''{1}'' {2}\n\nFor better readability try this:\nfunction [Output1,...,OutputN] = myfun(param1,...,paramN)" +
                            "\n\nIf you don''t see the problem at that location,\ncarefully read backwards through the code to find the warning. ",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,//6
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Break is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bBreak\\b).*",
                    "ERROR: {0}\nThe compiler/interpreter found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "BREAK is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bBREAK\\b).*",
                    "ERROR: {0}\nThe compiler/interpreter found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,//7
            new Description(
                    "Incorrect_Use_Of_Break", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "break terminates the execution, so it needs to be followed by end, else or ifelse.", //"The break statement exits a for or while loop completely. To skip the rest of the instructions in the loop and begin the next iteration, use a continue statement."
                    "^(?=.*?\\bbreak\\b\\s*)(?!.*\\bbreak\\b\\s*\\bend\\b)(?!.*\\bbreak\\b\\s*\\belse\\b)(?!.*\\bbreak\\b\\s*\\belseif\\b).*$",
                    "ERROR: {0}\nThe compiler found an error in line: \n\n{1} {2}\n     end <- MISSING \n \nIf you don''t see the problem at that location," +
                               "\ncarefully read backwards through the code\nlooking for the missing character or some other problem.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,//8
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Continue is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bContinue\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "CONTINUE is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bCONTINUE\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,//9
            new Description(
                    "Incorrect_Use_Of_Continue", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "continue passes control to the next iteration. It skips any remaining statements in the body of the loop, so it needs to be followed by end, else or elseif.",
                    "^(?=.*?\\bcontinue\\b\\s*)(?!.*\\bcontinue\\b\\s*\\bend\\b)(?!.*\\bcontinue\\b\\s*\\belse\\b)(?!.*\\bcontinue\\b\\s*\\belseif\\b).*",
                    "ERROR: {0}\nThe compiler found an error in line: \n\n{1} {2}\n     end <- MISSING \n \nIf you don''t see the problem at that location," +
                            "\ncarefully read backwards through the code\nlooking for the missing character or some other problem.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,//10
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Return is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bReturn\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "RETURN is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bRETURN\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,//11
            new Description(
                    "Incorrect_Use_Of_Return", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "return terminates the execution, so it needs to be followed by end",
                    "^(?=.*?\\breturn\\b\\s*)(?!.*\\breturn\\b\\s*\\bend\\b)(?!.*\\breturn\\b\\s*\\belse\\b)(?!.*\\breturn\\b\\s*\\belseif\\b).*",
                    "ERROR: {0}\nThe compiler found an error in line: \n\n{1} {2}\n     end <- MISSING \n \nIf you don''t see the problem at that location," +
                            "\ncarefully read backwards through the code\nlooking for the missing character or some other problem.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )

            ,//12
            new Description(
                    "Switch_Missing_End", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "switch needs to be terminated with 'end'",
                    "(?s)(?=.*?\\bswitch\\b)(?!.*end).*",
                    "ERROR: {0}\nThe critiquer found an error in: \n\n{2}    end <- MISSING \n \nIf you don''t see the problem at that location,\ncarefully read backwards through the " +
                            "code\nlooking for the missing character or some other problem.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Otherwise is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bOtherwise\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "OTHERWISE is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bOTHERWISE\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Switch is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bSwitch\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "SWITCH is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bSWITCH\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Case is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bCase\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "CASE is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bCASE\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description( //13
                    "If_Missing_End", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "if needs to be terminated with 'end'",
                    "(?s)(?=.*?\\bif\\b\\s)(?!.*?end).*",
                    "ERROR: {0}\nThe compiler found an error in: \n\n{2}end <- MISSING \n \nIf you don''t see the problem at that location,\ncarefully read backwards through the code\nlooking for the missing character or some other problem.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "If is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bIf\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "IF is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bIF\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "ELSEIF is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bELSEIF\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Wrong_Syntax", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "ifelse is incorrect syntax, try elseif",
                    "^(?=.*?\\bifelse\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Wrong_Syntax", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "if else is incorrect syntax, try elseif",
                    "^(?=.*?\\bif else\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Elseif is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bElseif\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Else is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bElse\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "ELSE is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bELSE\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,//14
            new Description(
                    "Variable_Name_Keyword", CodingAspect.STYLE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Do not use i as a variable name since it also means an imaginary number",
                    "^(?=.*?\\bi\\b\\s?=).*$",
                    "ERROR: {0}\nThe critiquer found an error in line: \n{1}. {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Variable_Name_Keyword", CodingAspect.STYLE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Do not use j as a variable name since it also means an imaginary number",
                    "^(?=.*?\\bj\\b\\s?=).*$",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,//15
            new Description(
                    "Incorrect_Use_Otherwise", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "otherwise can not be followed by otherwise since it is executed only if none of the preceding case expressions match the switch expression",
                    "(?s)(?:.*?\\bswitch\\b)(?:.*?\\botherwise\\b){2,}.*",
                    "ERROR: {0}\nThe compiler found an error: otherwise should be followed by a statement followed by a end\n" +
                            "\notherwise\n  statements \nend \n\nYour code:\notherwise\n    statements\notherwise\n    ...\nend",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,//16
            new Description(
                    "Incorrect_Use_Else", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "else can not be followed by else since it is executed only if none of the preceding case expressions match the if expression, instead try elseif",
                    "(?s)(?:.*?\\bif\\b)(?:.*?\\belse\\b){2,}.*",
                    "ERROR: {0}\nThe compiler found an error: else should be followed by a statement followed by a end\n\nif expression\n   statements\nelseif expression\n    statements\nelse\n   statements\nend" +
                            "\n\n\nYour code:\nelse\n    statements\nelse\n    ...\nend",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(//17
                    "For_Missing_End", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "for needs to be terminated with 'end'",
                    "(?s)(?=.*?\\bfor\\b)(?!.*end).*",
                    "ERROR: {0}\nThe critiquer found an error in: \n\n{2}end <- MISSING \n \nIf you don''t see the problem at that location,\ncarefully read backwards through the " +
                            "code\nlooking for the missing character or some other problem.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(//18
            "NestedLoop_Missing_End", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
            false,
            "for needs to be terminated with 'end'",
            "(?s)(?:.*?\\bfor\\b){2}(?:.*?\\bend\\b){1,1}.*",
            "ERROR: {0}\nThe critiquer found an error in: \n\n{2}end <- MISSING \n \nIf you don''t see the problem at that location,\ncarefully read backwards through the " +
                    "code\nlooking for the missing character or some other problem.\n",
            "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(//19
                    "While_Missing_End", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "while needs to be terminated with 'end'",
                    "(?s)(?=.*?\\bwhile\\b)(?!.*end).*",
                    "ERROR: {0}\nThe critiquer found an error in: \n\n{2}end <- MISSING \n \nIf you don''t see the problem at that location,\ncarefully read backwards through the " +
                            "code\nlooking for the missing character or some other problem.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
//            ,
//            new Description(//20 <- check the logic
//                    "While_If_Missing_End", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
//                    false,
//                    "1 while needs to be terminated with 'end'",
//                    " (?s)(?:.*?\\bwhile\\b){1}(?:.*?\\bif\\b){1}(?:.*?\\bend\\b){0}\\b(?!end\\b)\\.*",//(?!.*end)
//                    "ERROR: {0}\nThe critiquer found an error in: \n\n{2}   end <- MISSING \n \nIf you don''t see the problem at that location,\ncarefully read backwards through the " +
//                            "code\n looking for the missing character or some other problem.\n",
//                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
//            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "While is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bWhile\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "WHILE is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bWHILE\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "FOR is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bFOR\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "For is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bFor\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "End is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bEnd\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "END is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bEND\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "CLC is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bCLC\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Clear is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bClear\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "CLEAR is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bCLEAR\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "LOG is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bLOG\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Log is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bLog\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Input is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bInput\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "INPUT is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bINPUT\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "FPRINTF is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bFPRINTF\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Fprintf is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bFprintf\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Disp is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bDisp\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "DISP is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bDISP\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "DISP is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bSPRINTF\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "DISP is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bSprintf\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Plot is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bPlot\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "PLOT is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bPLOT\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Length is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bLength\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Size is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bSize\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "SUBPLOT is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bSUBPLOT\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Upper_Case_Keyword", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Subplot is a matlab keyword and it needs to be in lower case",
                    "^(?=.*?\\bSubplot\\b).*",
                    "ERROR: {0}\nThe critiquer found an error in line ''{1}'' {2}\n\nIf you don't see the problem at that location,\ncarefully read backwards through the code to find the error.\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Variable_Name_Keyword", CodingAspect.STYLE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "plot has the same name as a MATLAB builtin. We suggest you rename the function to avoid a potential name conflict.",
                    "^(?=.*?\\bplot\\b\\s?=).*$",
                    "WARNING: {0}\nThe compiler found a warning in line ''{1}'' {2}\n\nIf you don''t see the problem at that location,\ncarefully read backwards through the code to find the warning. ",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Variable_Name_Keyword", CodingAspect.STYLE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "function has the same name as a MATLAB builtin. We suggest you rename the function to avoid a potential name conflict.",
                    "^(?=.*?\\btitle\\b\\s?=).*$",
                    "WARNING: {0}\nThe compiler found a warning in line ''{1}'' {2}\n\nIf you don''t see the problem at that location,\ncarefully read backwards through the code to find the warning. ",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Variable_Name_Keyword", CodingAspect.STYLE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "function has the same name as a MATLAB builtin. We suggest you rename the function to avoid a potential name conflict.",
                    "^(?=.*?\\bxlabel\\b\\s?=).*$",
                    "WARNING: {0}\nThe compiler found a warning in line ''{1}'' {2}\n\nIf you don''t see the problem at that location,\ncarefully read backwards through the code to find the warning. ",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
            ,
            new Description(
                    "Variable_Name_Keyword", CodingAspect.STYLE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "function has the same name as a MATLAB builtin. We suggest you rename the function to avoid a potential name conflict.",
                    "^(?=.*?\\bylabel\\b\\s?=).*$",
                    "WARNING: {0}\nThe compiler found a warning in line ''{1}'' {2}\n\nIf you don''t see the problem at that location,\ncarefully read backwards through the code to find the warning. ",
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
            ,//21
            new Description(
                    "Empty_Function", CodingAspect.STRUCTURE, AntipatternSource.DIAGNOSTIC,
                    false,
                    "Your function is empty",
                    "(?s)(?=.*?\\bfunction\\b\\s?\\[\\b).*=\\s*[a-zA-Z]+[0-9_-]*[/(][[a-zA-Z]+[0-9_-]*[/,]?]*[/)]\\s*(\\bend\\b)",
                    "ERROR: {0}\nThe interpreter/critiquer found an error in line ''{1}'' {2}\n",
                    "ERROR: The compiler is looking for a missing ''{6}'' somewhere around line {3}.\n"
            )
    };

    //for better readability remember to add space after and before an equal sign




    public static List<MatchResult> getMatchResults(
            String regexString,
            File text) throws PatternSyntaxException, FileNotFoundException {
        List<MatchResult> matches = new ArrayList<>();

        Scanner scan = new Scanner(text);
        StringBuilder fileReader = new StringBuilder();
        while (scan.hasNext()) {
//            fileReader.append(scan.nextLine());
            fileReader.append(scan.nextLine()).append("\n"); //this line will format the code nicely but it is going to create issues with regEx
            //since .* does not matches new lines

        }

        if (regexString != null && !regexString.isEmpty()) {
            Pattern regexPattern = Pattern.compile(regexString, Pattern.MULTILINE);//UNIX_LINES

            Matcher matcher = regexPattern.matcher(fileReader.toString());
            while (matcher.find()) {
                matches.add(matcher.toMatchResult());
            }
        }
        return matches;
    }



    public static int returnLine (File file, String end) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        int count = 0;
        StringBuilder fileReader = new StringBuilder();
        while (scan.hasNext()) {
            fileReader.append(scan.nextLine()).append("\n");
        }
        String[] lines = fileReader.toString().split("\n");
        for(String s : lines){
            count++;
            if(s.equals(end)) return count;
        }

        return count;
    }



    public static <S> Critique<S> makeCritique(
            S source,
            String sourceFile,
            Description[] descriptions) throws FileNotFoundException {
        Critique<S> critique = null;
        boolean critiqueFound = false;

        for (Description description : descriptions) {
            if (description.getSource() != AntipatternSource.DIAGNOSTIC) {
                continue;
            }
            //this list is going to have the
            List<MatchResult> matchResults = getMatchResults(
                    description.getRegexString(),
                    new File(source.toString()));


            //this is going to loop and it is going to send all the errors found in the code
            if (!matchResults.isEmpty()) {
                critiqueFound = true;


                //it will get all the errors
                MatchResult matchResult = matchResults.get(0);
                String[] matchedGroups = new String[matchResult.groupCount() + 1];
                for (int i = 0; i < matchedGroups.length; i++) {
                    matchedGroups[i] = matchResult.group(i);
                }

                int line = returnLine((File) source, matchedGroups[0]); //it gets the exact line number where the regEx was found
                String matchedText = matchedGroups[0]; //it gets the text line in which the regEx was found


                String critiqueText = MessageFormat.format(description.getTextTemplate(), sourceFile, line, matchedText); //this will determine what is going after ERROR:

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
            List<MatchResult> matchResults = getMatchResults(defaultDescription.getRegexString(), new File(source.toString()));

            String critiqueText = MessageFormat.format(defaultDescription.getTextTemplate(), source);
            String altCritiqueText = MessageFormat.format(defaultDescription.getAltTextTemplate(), source);
            for (MatchResult critiqueMatch : matchResults) {
                critique = new Critique<>(
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






    public static void main( String[] args ) throws IOException {
        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/emptyFunction.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/emptyFunction.m";

        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        System.out.println(critique);

    }
}
