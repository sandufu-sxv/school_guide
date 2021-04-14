package com.jiang.school_guide.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jiang.school_guide.common.authentication.JWTUtil;
import com.jiang.school_guide.common.authentication.TokenUntil;
import com.jiang.school_guide.common.domain.Const;
import com.jiang.school_guide.common.domain.ServerResponse;
import com.jiang.school_guide.entity.User;
import com.jiang.school_guide.dao.UserMapper;
import com.jiang.school_guide.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author evildoer
 * @since 2021-04-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse login(String code) {

        ServerResponse  result = getOpenId(code);
        if(result.isSuccess()){
            String openId=result.getData().toString();
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("open_id", openId);
            User user = userMapper.selectOne(queryWrapper);
            if (user == null)
            {
                if (!register(openId)){
                    return ServerResponse.createByErrorMessage("注册失败");
                }

                user = userMapper.selectOne(queryWrapper);
            }
            if (user != null)
            {
                if(user.getStatus().equals(Const.STATUS.PROHIBITION.getStatus())){
                    return ServerResponse.createByErrorMessage("账号已封禁");
                }
                String token = JWTUtil.encryptToken(JWTUtil.sign(user.getId(),Const.USER));
                return ServerResponse.createBySuccessMessage("登陆成功", token);
            }
        }
        return ServerResponse.createByErrorMessage("服务繁忙,请稍后再试");
    }


    public boolean register(String openId) {
        User user = new User();
        user.setOpenId(openId);
        user.setStatus(3);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.insert(user) > 0;
    }

    @Override
    public ServerResponse getInfo() {
        int id = TokenUntil.getIdByToken();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            return ServerResponse.createByErrorMessage("未知错误，请稍后重试");
        }
        return ServerResponse.createBySuccess(user);
    }

    @Override
    public ServerResponse updateInfo(User user) {
        int id = TokenUntil.getIdByToken();
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        user.setUpdateTime(LocalDateTime.now());
        if(userMapper.update(user,updateWrapper) == 1){
            return ServerResponse.createBySuccess("信息修改成功");
        }
        return ServerResponse.createByErrorMessage("信息修改失败");
    }

    @Override
    public ServerResponse deleteUser() {
        return null;
    }


    //获取微信openId
    private ServerResponse getOpenId(String code)
    {
        String open_id = "";
        HttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx33d0d5bece2538f0&secret" +
                "=ea8d8318d6b92d776b838fc2def2b2bb&js_code=" + code + "&grant_type=authorization_code";
        try
        {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1200).setSocketTimeout(1200).build();
            httpGet.setConfig(requestConfig);
            HttpResponse httpResponse = null;
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null && httpResponse.getStatusLine() != null)
            {
                String content = "";
                if (httpResponse.getEntity() != null)
                {
                    content = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                    JSONObject json = new JSONObject(content);
                    open_id = (String) json.get("openid");
                    return ServerResponse.createBySuccess(open_id);
                }
            }
        } catch (URISyntaxException e)
        {
            return ServerResponse.createByErrorMessage("发生异常错误");
        } catch (ClientProtocolException e)
        {
            return ServerResponse.createByErrorMessage("发生异常错误");
        } catch (IOException e)
        {
            return ServerResponse.createByErrorMessage("发生异常错误");
        }
        return ServerResponse.createByErrorMessage("发生异常错误");
    }



}
