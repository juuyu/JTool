package com.nie.tool.common.flowable.common.listener;

import com.nie.tool.common.flowable.common.config.TaskEvent;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author njy
 * @since 2024/7/3 14:38
 */
public abstract class BaseTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        switch (delegateTask.getEventName()) {
            case TaskEvent.TASK_EVENT_CREATE:
                afterCreate(delegateTask);
                break;
            case TaskEvent.TASK_EVENT_ASSIGN:
                afterAssign(delegateTask);
                break;
            case TaskEvent.TASK_EVENT_COMPLETE:
                afterComplete(delegateTask);
                break;
            case TaskEvent.TASK_EVENT_DELETE:
                afterDelete(delegateTask);
                break;
            default:
                break;
        }
    }

    /**
     * 任务创建后
     *
     * @param delegateTask delegateTask
     */
    protected abstract void afterCreate(DelegateTask delegateTask);

    /**
     * 任务分配后
     *
     * @param delegateTask delegateTask
     */

    protected abstract void afterAssign(DelegateTask delegateTask);

    /**
     * 任务完成后
     *
     * @param delegateTask delegateTask
     */
    protected abstract void afterComplete(DelegateTask delegateTask);

    /**
     * 任务删除后
     *
     * @param delegateTask delegateTask
     */
    protected abstract void afterDelete(DelegateTask delegateTask);


}
