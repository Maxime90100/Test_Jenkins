package izly;

import java.util.Random;

public class SecretCode {
    private String code;
    private boolean firstCall;

    public SecretCode(String code) {
        this.code = code;
    }

    public SecretCode() {
        new Random().ints(0, 10).limit(4).forEach(i -> code += i);
    }

    public boolean checkCode(String code) {
        return this.code.equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String revealCode() {
        if(firstCall) {
            firstCall = false;
            return code;
        } else {
            return "xxxx";
        }
    }

    public boolean verifyCode(String code) {
        return this.code.equals(code);
    }

    public boolean isCodeBloque() {
        return false;
    }

    public boolean isCode(String code) {
        return code.length() == 4;
    }


}
