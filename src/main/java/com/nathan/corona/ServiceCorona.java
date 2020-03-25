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
    public void executeAsynchronously(String reqUrl) {
        executeThread.setReqUrl(reqUrl);
        taskExecutor.execute(executeThread);
    }
}
