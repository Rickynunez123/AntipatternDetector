package edu.mtu.cs.webta.util.junit;
import edu.mtu.cs.webta.critiquer.*;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

//CritiquerTools
import static org.junit.Assert.assertEquals;

public class JUnit extends CritiquerToolsMatlab{
//continue or return
    @Test
    public void test() throws IOException {
//        Function [outputArg1,outputArg2] = Function(inputArg1,inputArg2)
//        outputArg1 = inputArg1;
//        outputArg2 = inputArg2;
//        end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/testing.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/testing.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"Function should be in lower case since it is a MATLAB keyword");

    }

    @Test
    public void test2() throws IOException {
//        FUNCTION [outputArg1,outputArg2] = Function(inputArg1,inputArg2)
//        outputArg1 = inputArg1;
//        outputArg2 = inputArg2;
//        end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/FUNCTION2.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/FUNCTION2.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"FUNCTION should be in lower case since it is a MATLAB keyword");



    }

    @Test
    public void test3() throws IOException {
//        x = 2;
//        y = x + 3;
//        function = y * x;

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/NotVar.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/NotVar.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"function has the same name as a MATLAB builtin. We suggest you rename the function to avoid a potential name conflict.");



    }
    @Test
    public void test4() throws IOException {
//        function [outputArg1, outputArg2] = noEnd(inputArg1, inputArg2)
//        outputArg1 = inputArg1;
//        outputArg2 = inputArg2;

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/noEnd.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/noEnd.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"functions should be finalized with the word end");
    }
    @Test
    public void test5() throws IOException {
//        function outputArg1, outputArg2 = spaceCommaParam(inputArg1,inputArg2)
//        outputArg1 = inputArg1;
//        outputArg2 = inputArg2;
//        end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/OutputSyntax.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/OutputSyntax.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"functions outputs should be surrounded by [ ]");

    }

    @Test
    public void test6() throws IOException {
//        a = 10;
//        while (a < 20 )
//            fprintf('value of a: %d\n', a);
//        a = a + 1;
//        if( a > 15)
//        Break
//        end
//            end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/Break.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/Break.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"Break is a matlab keyword and it needs to be in lower case");

    }

    @Test
    public void test7() throws IOException {
//        limit = 0.8;
//        s = 0;
//
//        while 1
//        tmp = rand;
//        if tmp > limit
//        break
//        if tmp < limit
//                end
//        s = s + tmp;
//        end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/EndAfterBreak.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/EndAfterBreak.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"break terminates the execution, so it needs to be followed by end, else or ifelse.");

    }
    @Test
    public void test8() throws IOException {

//        for n = 1:50
//        if mod(n,7)
//        Continue
//                end
//        disp(['Divisible by 7: ' num2str(n)])
//        end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/Continue.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/Continue.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"Continue is a matlab keyword and it needs to be in lower case");

    }
    @Test
    public void test9() throws IOException {

//        for n = 1:50
//        if mod(n, 7)
//        continue
//        if mod(n, 8)
//        end
//        disp(['Divisible by 7: ' num2str(n)])
//        end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/EndAfterContinue.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/EndAfterContinue.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"continue passes control to the next iteration. It skips any remaining statements in the body of the loop, so it needs to be followed by end, else or elseif.");
    }

    @Test
    public void test10() throws IOException {

//        function idx = findSqrRootIndex(target,arrayToSearch)
//
//        idx = NaN;
//        if target < 0
//        Return
//                end
//
//        for idx = 1:length(arrayToSearch)
//        if arrayToSearch(idx) == sqrt(target)
//        Return
//                end
//        end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/ReturnKeyword.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/ReturnKeyword.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"Return is a matlab keyword and it needs to be in lower case");

    }
    @Test
    public void test11() throws IOException {

//        function [idx] = findSqrRootIndex(target,arrayToSearch)
//
//        idx = NaN;
//        if target < 0
//        return
//        if target <2
//        end
//                end
//
//        for idx = 1:length(arrayToSearch)
//        if arrayToSearch(idx) == sqrt(target)
//        return
//                end
//        end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/EndAfterReturn.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/EndAfterReturn.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"return terminates the execution, so it needs to be followed by end");

    }

    @Test
    public void test12() throws IOException {
//        n = input('Enter a number: ');
//
//        switch n
//        case -1
//        disp('negative one')
//        case 0
//        disp('zero')
//        case 1
//        disp('positive one')
//        otherwise
//        disp('other value')

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/Switch.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/Switch.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"switch needs to be terminated with 'end'");
    }

    @Test
    public void test13() throws IOException {

//        a = randi(100, 1);
//
//        if a < 30
//        disp('small')
//        elseif a < 80
//        disp('medium')
//         else
//        disp('large')

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/IfNoEnd.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/IfNoEnd.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"if needs to be terminated with 'end'");

    }


        @Test
    public void test14() throws IOException {

//            i = 3
//            v = [ i; 3i; 3*i; i+3i; i+3*i ]

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/Imaginaryi.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/Imaginaryi.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"Do not use i as a variable name since it also means an imaginary number");

    }

        @Test
    public void test15() throws IOException {

//            n = input('Enter a number: ');
//
//            switch n
//            case -1
//            disp('negative one')
//            case 0
//            disp('zero')
//            case 1
//            disp('positive one')
//            otherwise
//            disp('other value')
//            otherwise
//            disp('other other value')
//            end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/IncorrectUseOtherwise.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/IncorrectUseOtherwise.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"otherwise can not be followed by otherwise since it is executed only if none of the preceding case expressions match the switch expression");

    }

        @Test
    public void test16() throws IOException {
//            x = 10;
//            minVal = 2;
//            maxVal = 6;
//
//            if (x >= minVal) && (x <= maxVal)
//            disp('Value within specified range.')
//else (x > maxVal)
//            disp('Value exceeds maximum value.')
//else
//            disp('Value is below minimum value.')
//else
//            disp('Value is below minimum value.')
//            end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/IncorrectUseElse.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/IncorrectUseElse.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"else can not be followed by else since it is executed only if none of the preceding case expressions match the if expression, instead try elseif");

    }
        @Test
    public void test17() throws IOException {

//            s = 10;
//            H = zeros(s);
//            for c = 1:s

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/forforNoEnd.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/forforNoEnd.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"for needs to be terminated with 'end'");

    }
        @Test
    public void test18() throws IOException {
//        s = 10;
//        H = zeros(s);
//
//        for c = 1:s
//        for r = 1:s
//        H(r,c) = 1/(r+c-1);
//            end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/forforNoEnd.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/forforNoEnd.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"for needs to be terminated with 'end'");

    }
    @Test
    public void test19() throws IOException {
//        s = 10;
//        H = zeros(s);
//
//        for c = 1:s
//        for r = 1:s
//        H(r,c) = 1/(r+c-1);
//            end

        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/whileNoEnd.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/whileNoEnd.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"while needs to be terminated with 'end'");

    }
//    @Test
//    public void test20() throws IOException {
////        limit = 0.8;
////        s = 0;
////
////        while 1
////        tmp = rand;
////        if tmp > limit
////        break
////                end
////        s = s + tmp;
//
//
//        File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/WhileIF.m");
//        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/WhileIF.m";
//
//
//        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
//        String result = critique.getDescription();
//        assertEquals(result,"1 while needs to be terminated with 'end'");
//
//    }
        @Test
    public void test21() throws IOException {
//            function [p] = emptyFunction(n,r)
//            end
            File file = new File("C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/emptyFunction.m");
        String fileName = "C:/Users/ricky/OneDrive/Documentos/MATLAB/MATLAB/Critiquer/Functions/emptyFunction.m";


        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
        String result = critique.getDescription();
        assertEquals(result,"Your function is empty");

    }

    //    @Test
//    public void test12() throws IOException {
//
//
//        File file = new File("");
//        String fileName = "";
//
//
//        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
//        String result = critique.getDescription();
//        assertEquals(result,"");
//
//    }
    //    @Test
//    public void test12() throws IOException {
//
//
//        File file = new File("");
//        String fileName = "";
//
//
//        Critique<File> critique = makeCritique(file, fileName, ANTIPATTERN_CRITIQUE_DESCRIPTIONS);
//        String result = critique.getDescription();
//        assertEquals(result,"");
//
//    }
}