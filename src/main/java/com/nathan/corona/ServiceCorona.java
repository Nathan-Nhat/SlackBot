package com.nathan.corona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class ServiceCorona {
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private ExecuteThread executeThread;
    public void executeAsynchronously(String reqUrl, String channelId) {
        executeThread.setReqUrl(reqUrl);
        executeThread.setChannelId(channelId);
        taskExecutor.execute(executeThread);
    }
}
