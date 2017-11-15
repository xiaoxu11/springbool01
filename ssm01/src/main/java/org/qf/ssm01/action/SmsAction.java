package org.qf.ssm01.action;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.qf.ssm01.dto.CusDTO;
import org.qf.ssm01.service.CusPhoneService;
import org.qf.ssm01.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SmsAction {

    @Autowired
    private RedisService redisService;

    @Autowired
    private CusPhoneService cusPhoneService;

    @RequestMapping("/ajaxNum")
    @ResponseBody
    public String sendMsg(String phoneNum,String token)throws Exception{//参数和ftl中的参数名相一致
        //取得token的信息
        //再需要根据token查询业务的信息，先从缓存查询，缓存没有再从数据库查询，
        //之后存入缓存中，以后查询仅从缓存查询即可
        CusDTO cusByToken = cusPhoneService.getCusByToken(token);//根据token去查询，直接调用之前设置好的方法逻辑
        if(cusByToken==null){
            //当所传手机号不属于各个业务时报错
            return "error";
        }
//        Integer maxphone=cusByToken.getMaxphone();//获取所在业务的手机号日总量
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK（设置为自身密钥）
        final String accessKeyId = AliAccessKey.accessKeyId;//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = AliAccessKey.accessKeySecret;//你的accessKeySecret，参考本文档步骤2
//初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);//注意导入阿里云的jar
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNum);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("吕自政");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_101125003");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        //生成验证码：
        String numeric = RandomStringTLUtils.randomNumeric(6);//设置生成验证码为6位
        request.setTemplateParam("{\"code\":\""+numeric+"\"}");//动态传递验证码给手机
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("java1712");
//请求失败这里会抛ClientException异常
//        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);//发送短信
//        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//            //请求成功,成功之后，将生成的验证码存储到缓存中
//            redisService.set(phoneNum,numeric,300l);
//            return numeric;
//        }
        redisService.set(phoneNum,numeric,300l);
        return numeric;
    }
    //显示页面
    @RequestMapping("/mt")
    public String login(){
        return "mtlogin";//返回模版页面的地址，不加ftl
    }
}
