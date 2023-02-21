package com.deng.bot.api.domain.zsxq.model.req;

/**
 * @Author: deng
 * @Date: 2023/2/17
 * @Description:
 */
public class AnswerReq {

    private ReqData req_data;

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }
}
