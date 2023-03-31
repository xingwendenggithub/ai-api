package com.deng.bot.api.application.job;

import com.alibaba.fastjson.JSON;
import com.deng.bot.api.domain.ai.IOpenAI;
import com.deng.bot.api.domain.zsxq.IZsxqApi;
import com.deng.bot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.deng.bot.api.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @Author: deng
 * @Date: 2023/2/20
 * @Description:
 */
@Configuration
@EnableScheduling
public class AiSchedule {

    private Logger logger = LoggerFactory.getLogger(AiSchedule.class);

    @Value("${chatbot-api.group01.groupId}")
    private String groupId;
    @Value("${chatbot-api.group01.cookie}")
    private String cookie;
    @Value("${chatbot-api.group01.openAiKey}")
    private String openAiKey;
    @Value("${chatbot-api.group01.cronExpression}")
    private String cronExpression;

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAI openAI;

    /**
     * 单个task 进行coding ，
     */
    //@Scheduled(cron = "0/30 * * * * ?")
    @Scheduled(cron=cronExpression)
    private void run(){
        if (new Random().nextBoolean()) {
            logger.info("随机打烊中。。。。");
            return;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int hour = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
        if(hour<7||hour>22){
            logger.info("休息时间中。。。。");

        }

        // 1.查询出待回答问题
        // 2. 回答问题
        // 3. 插入答案
        try {
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("未回答的问题：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if(CollectionUtils.isEmpty(topics)){
                logger.info("本次没有检索到要回答的问题");
                return;
            }
            Topics topic = topics.get(0);
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();

            String answer = openAI.doChatGPT(openAiKey, topic.getQuestion().getText());
            logger.info("回答的问题答案：{}", answer);

            boolean answerStatus = zsxqApi.answer(groupId, cookie, topicId, answer, false);
            logger.info("编号:{},问题:{},答案:{},结果:{}", topicId,answer,answerStatus);

        } catch (IOException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            logger.error("出现错误了：{}",e);
        }

    }



}
