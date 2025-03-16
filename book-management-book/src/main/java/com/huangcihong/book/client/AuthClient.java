package com.huangcihong.book.client;

import com.huangcihong.book.client.fallback.AuthClientImpl;
import com.huangcihong.common.entity.vo.auth.UserVo;
import com.huangcihong.common.entity.vo.result.ResultInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

/**
 * @author huangcihong
 */
@FeignClient(name = "book-management-auth", fallback = AuthClientImpl.class)
public interface AuthClient {
    @PostMapping("/user/getUserList")
    ResultInfo<List<UserVo>> getUserList(@RequestBody Set<Long> userIds);

}
