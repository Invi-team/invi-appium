package invi.utils;

import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InviFile {

    public static String composeNameByTestRun(ITestResult iTestResult) {
        StringBuffer completeFileName = new StringBuffer();

        completeFileName.append(getDateToFileName());
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

    public static String getDateToFileName() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateString = formatter.format(calendar.getTime());
        return dateString.replaceAll(" ", "_");
    }
}
