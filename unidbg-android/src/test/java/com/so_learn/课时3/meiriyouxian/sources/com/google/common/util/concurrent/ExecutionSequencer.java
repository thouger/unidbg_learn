package com.google.common.util.concurrent;

public final class ExecutionSequencer {

    enum RunningState {
        NOT_RUN,
        CANCELLED,
        STARTED
    }
}
