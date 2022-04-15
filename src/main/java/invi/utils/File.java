package invi.utils;

import org.testng.ITestResult;

public class File {

    public static String composeNameByTestRun(ITestResult iTestResult) {
        StringBuffer completeFileName = new StringBuffer();
        Date dateUtils = new Date();

        completeFileName.append(dateUtils.getCurrentDate());
        completeFileName.append("_");
        completeFileName.append(iTestResult.getName());
        completeFileName.append("_");
        completeFileName.append(iTestResult.getTestClass().getRealClass().getSimpleName());
        completeFileName.append("_");
        completeFileName.append(iTestResult.getTestContext().getSuite().getName());
        Object[] parameters = iTestResult.getParameters();

        for(Object parameter : parameters) {
            completeFileName.append("_");
            completeFileName.append(parameter);
        }

        return completeFileName.toString();
    }
}
