package invi.tests;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import invi.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({
        ExtentITestListenerClassAdapter.class,
        TestListener.class
})
public class InvitationAcceptationTest {

    @Test
    public void invitationAcceptationTest() {


    }
}
