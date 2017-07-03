package com.heyuhuan.admin.service;

import cn.jpush.api.push.model.PushPayload;
import com.heyuhuan.admin.dto.Json;

/**
 * 推送服务类接口
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
public interface PushService {

    Json push(PushPayload pushPayload);

}