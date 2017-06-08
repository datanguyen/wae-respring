package wae.thesis.indiv.service.product.external.action;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import wae.thesis.indiv.api.behavior.ActionHandler;
import wae.thesis.indiv.api.model.ServiceInfo;
import wae.thesis.indiv.service.product.internal.model.Code;

import java.util.Random;

/**
 * Created by Nguyen Tan Dat.
 */
public class VerifyCodeAction implements ActionHandler {

    public static final String ACCOUNT_SID = "AC0383c5191e58904728a5259eab8baf88";
    public static final String AUTH_TOKEN = "5fa7dccb01e2c171d589ac43cc4769e8";

    @Override
    public Object handle(ServiceInfo serviceInfo) {
        String code = String.valueOf((new Random().ints(10, 1000, 9999)).findAny().orElse(2910));
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("+841216933470"),
                new PhoneNumber("+13012346392"),
                code
        ).create();
        System.out.println(message);

        return new Code(code);
    }
}
