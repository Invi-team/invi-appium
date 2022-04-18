package invi;

import invi.runners.AwsRunner;

public class App
{
    public static void main( String[] args ) {
        String deviceSystem = args[0];
        new AwsRunner(deviceSystem).run();
    }
}
