package izly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;
import java.util.stream.IntStream;

public class SecretCodeUnitTest {

    @Test
    public void testRevealCode() {
        SecretCode secretCode = new SecretCode("1234");
        Assertions.assertTrue(isCode(secretCode.revealCode()));
        Assertions.assertEquals("xxxx", secretCode.revealCode());
        Assertions.assertEquals("xxxx", secretCode.revealCode());

    }

    private boolean isCode(String code) {
        // return code.matches("[0-9]{4}");
        if (code.length() != 4) {
            return false;
        }
        for (int i = 0; i < code.length(); i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testRandomGenerationOfSecretCode() {
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.ints(0, 10).limit(4)).thenReturn(IntStream.of(1, 2, 3, 4));
        SecretCode secretCode = new SecretCode();
        secretCode.setCode(random.ints(0, 10).limit(4).mapToObj(String::valueOf).reduce("", String::concat));
        Assertions.assertEquals("1234", secretCode.getCode());
    }

    @Test
    public void testCodeBlockedAfterThreeFailedAttempt() {
        secretCode.verifyCode(badCode);
        secretCode.verifyCode(badCode);
        Assertions.assertFalse(secretCode.isCodeBloque());
        secretCode.verifyCode(badCode);
        Assertions.assertTrue(secretCode.isCodeBloque());
    }
}
