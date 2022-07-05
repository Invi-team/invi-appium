package invi.utils;

import org.testng.ITestResult;

public class File {

    public static String composeNameByTestRun(ITestResult iTestResult) {
        StringBuffer completeFileName = new StringBuffer();

        completeFileName.append(iTestResult.getTestClass().getRealClass().getSimpleName());
//        Object[] parameters = iTestResult.getParameters();
//        for(Object parameter : parameters) {
//            completeFileName.append("_");
//            completeFileName.append(parameter);
//        }
        return completeFileName.toString();
    }
}
