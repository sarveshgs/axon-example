package com.tw.axonbootcamp.service;

import org.axonframework.commandhandling.CommandCallback;
import org.springframework.web.context.request.async.DeferredResult;

public class AccountCommandCallBack<R> extends DeferredResult implements CommandCallback<R> {
    private R result;

    AccountCommandCallBack(R result) {
        this.result = result;
    }


    @Override
    public void onSuccess(R r) {

        this.setResult(result);

    }

    @Override
    public void onFailure(Throwable throwable) {
        this.setErrorResult(throwable);
    }


}
