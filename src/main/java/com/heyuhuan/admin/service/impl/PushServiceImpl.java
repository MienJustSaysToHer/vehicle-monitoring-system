package com.heyuhuan.admin.service.impl;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.heyuhuan.admin.dto.Json;
import com.heyuhuan.admin.service.PushService;
import org.springframework.stereotype.Service;

/**
 * 推送服务类
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
@Service
public class PushServiceImpl implements PushService {

    private JPushClient jpushClient = new JPushClient("a7f271faea29b2f8fee065bd", "15d6d85d628bb0b341af5392", null, ClientConfig.getInstance());

    @Override
    public Json push(PushPayload pushPayload) {
        Json j = new Json();
        try {
            PushResult result = jpushClient.sendPush(pushPayload);
            j.setMsg(result.toString());
            j.setSuccess(true);
        } catch (APIConnectionException e) {
            j.setMsg("连接错误，请稍后再重试：" + e.getMessage());
        } catch (APIRequestException e) {
            j.setMsg("请检查错误：HTTP 状态：" + e.getStatus() + "；错误码：" + e.getErrorCode() + "；错误消息：" + e.getErrorMessage());
        }
        return j;
    }

}