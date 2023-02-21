package com.deng.bot.api.domain.zsxq.model.res;

import com.deng.bot.api.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * @Author: deng
 * @Date: 2023/2/17
 * @Description:
 */
public class RespData {
    private List<Topics> topics;

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }

}
