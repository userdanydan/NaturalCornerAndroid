package be.daniel.naturalcornerandroid;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class LoginActivityTest {
    @Test
    public void checkEmailAndPasswordValidityTest() throws  Exception{

        String email="emailTest";
        String password="passwordTest";

        assertFalse(LoginActivity.checkEmailAndPasswordValidity(email, password));

        email="admin";
        password="admin";

        assertTrue(LoginActivity.checkEmailAndPasswordValidity(email, password));
    }
}