package com.nie.tool.common.flowable.common.listener;

import com.nie.tool.common.flowable.common.config.ExecutionEvent;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

/**
 * @author njy
 * @since 2024/7/3 14:33
 */
public abstract class BaseExecutionListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) {
        switch (execution.getEventName()) {
            case ExecutionEvent.EXECUTION_EVENT_START:
                afterStart(execution);
                break;
            case ExecutionEvent.EXECUTION_EVENT_END:
                afterEnd(execution);
                break;
            case ExecutionEvent.EXECUTION_EVENT_TAKE:
                afterTake(execution);
                break;
            default:
                break;
        }
    }

    /**
     * 流程实例启动后
     *
     * @param execution execution
     */
    protected abstract void afterStart(DelegateExecution execution);

    /**
     * 流程实例结束后
     *
     * @param execution execution
     */
    protected abstract void afterEnd(DelegateExecution execution);

    /**
     * 流程实例执行一个序列流之后
     *
     * @param execution execution
     */
    protected abstract void afterTake(DelegateExecution execution);

}
