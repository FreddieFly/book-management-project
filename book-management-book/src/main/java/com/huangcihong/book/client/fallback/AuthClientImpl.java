package com.huangcihong.book.client.fallback;

import com.huangcihong.book.client.AuthClient;
import com.huangcihong.common.entity.vo.auth.UserVo;
import com.huangcihong.common.entity.vo.result.ResultInfo;

import java.util.List;
import java.util.Set;

/**
 * @author huangcihong
 */
public class AuthClientImpl implements AuthClient {
    @Override
    public ResultInfo<List<UserVo>> getUserList(Set<Long> userIds) {
        return null;
    }
}
