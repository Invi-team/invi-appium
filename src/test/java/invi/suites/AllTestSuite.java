package invi.suites;


import invi.tests.LogInTest;
import invi.tests.SignUpTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        LogInTest.class,
        SignUpTest.class
})
public class AllTestSuite extends TestSuiteTemplate {

}
