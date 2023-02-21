package com.deng.bot.api.test;

import com.alibaba.fastjson.JSON;
import com.deng.bot.api.domain.ai.IOpenAI;
import com.deng.bot.api.domain.zsxq.IZsxqApi;
import com.deng.bot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.deng.bot.api.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @Author: deng
 * @Date: 2023/2/17
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles("qa")
public class SpringbootTest {

    private Logger logger = LoggerFactory.getLogger(SpringbootTest.class);

    @Value("${chatbot-api.group01.groupId}")
    private String groupId;
    @Value("${chatbot-api.group01.cookie}")
    private String cookie;
    @Value("${chatbot-api.group01.openAiKey}")
    private String openAiKey;

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAI openAI;

    @Test
    public void test_zsApi() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicId：{} text：{}", topicId, text);

            // 回答问题
            zsxqApi.answer(groupId, cookie, topicId, openAI.doChatGPT(openAiKey, text), false);
        }
    }

    @Test
    public void test_openAi() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String question="怎么获取知识星球的邀请码？";

        String response = openAI.doChatGPT(openAiKey, question);
        logger.info("测试结果：{}", response);
    }
}
