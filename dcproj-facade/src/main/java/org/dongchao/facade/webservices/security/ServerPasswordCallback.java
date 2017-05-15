package org.dongchao.facade.webservices.security;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaodongchao on 2017/5/14.
 */
public class ServerPasswordCallback implements CallbackHandler {
    private static final Map<String, String> userMap = new HashMap<>();

    public ServerPasswordCallback() {
        userMap.put("joe", "123456");
        userMap.put("bob", "123456");
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];
        String clientUsername = callback.getIdentifier();
        String serverPassword = userMap.get(clientUsername);

        if (serverPassword != null) {
            // set the password on the callback. This will be compared to the  password which was sent from the client.
            callback.setPassword(serverPassword);
        }

    }
}
