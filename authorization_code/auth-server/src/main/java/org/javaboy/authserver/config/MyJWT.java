package org.javaboy.authserver.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: bai
 * @DateTime: 2020/6/26 15:29
 */
@Component
public class MyJWT implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        //TODO 自定义 JWT 响应数据
        Map<String, Object> mapInfo = oAuth2AccessToken.getAdditionalInformation();
        mapInfo.put("username", "root");
        mapInfo.put("qq", "211425401");
        mapInfo.put("weChat", "bai211425401");
        mapInfo.put("phone", "18738361512");
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(mapInfo);
        return oAuth2AccessToken;
    }
}
