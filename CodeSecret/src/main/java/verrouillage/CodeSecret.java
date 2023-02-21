package verrouillage;

import org.mockito.Mockito;

public class CodeSecret {
    private final CodeSecret fakeCode;

    public CodeSecret() {
        fakeCode = Mockito.mock(CodeSecret.class);
        Mockito.when(revelerCode()).thenReturn("1234");
        Mockito.when(verifierCode("1234")).thenReturn(true);
    }

    public String revelerCode() {
        return  fakeCode.revelerCode();
    }

    public boolean verifierCode(String goodCode) {
        return  fakeCode.verifierCode(goodCode);
    }
}
