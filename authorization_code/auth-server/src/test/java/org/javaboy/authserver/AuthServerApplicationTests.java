package org.javaboy.authserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@SpringBootTest
class AuthServerApplicationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 随机生成1条加密密码
     */
    @Test
    public void test() {
        System.out.println(passwordEncoder.encode("123"));
    }

    /**
     * 对 JWT 内容解码
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test2() throws UnsupportedEncodingException {
        String s = new String(Base64.getDecoder().decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"), "UTF-8");
        System.out.println(s);  //TODO 结果为: {"alg":"HS256","typ":"JWT"}
        String s1 = new String(Base64.getDecoder().decode("eyJhdWQiOlsicmVzMSJdLCJ1c2VyX25hbWUiOiJqYXZhYm95Iiwic2NvcGUiOlsiYWxsIl0sImV4cCI6MTU5MzE2Mjc1OSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9hZG1pbiJdLCJqdGkiOiI1MWMxYWI2YS1mOTYzLTQxMmMtODA3YS0yZDg4YTJhY2MzZjciLCJjbGllbnRfaWQiOiJqYXZhYm95In0"), "UTF-8");
        System.out.println(s1); //TODO 结果为: {"aud":["res1"],"user_name":"javaboy","scope":["all"],"exp":1593162759,"authorities":["ROLE_admin"],"jti":"51c1ab6a-f963-412c-807a-2d88a2acc3f7","client_id":"javaboy"}
    }
}
