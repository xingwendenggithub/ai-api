package com.deng.bot.api.domain.ai;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: deng
 * @Date: 2023/2/17
 * @Description:
 */
public interface IOpenAI {

    String doChatGPT(String openAiKey, String question) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException;
}
