package com.nie.tool.common.flowable.oa.listener;

import com.nie.tool.common.flowable.common.listener.BaseTaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author njy
 * @since 2024/7/3 15:01
 */
public abstract class OaTaskListener extends BaseTaskListener {
    @Override
    protected void afterCreate(DelegateTask delegateTask) {

    }

    @Override
    protected void afterAssign(DelegateTask delegateTask) {
        String assignee = delegateTask.getAssignee();
    }

    @Override
    protected void afterComplete(DelegateTask delegateTask) {

    }

    @Override
    protected void afterDelete(DelegateTask delegateTask) {

    }


    protected abstract void notifyUserToDealWithTask();
}
