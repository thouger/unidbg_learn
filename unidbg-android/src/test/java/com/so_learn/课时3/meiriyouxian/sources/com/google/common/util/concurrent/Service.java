package com.google.common.util.concurrent;

public interface Service {

    public enum State {
        NEW {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        },
        STARTING {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        },
        RUNNING {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        },
        STOPPING {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return false;
            }
        },
        TERMINATED {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return true;
            }
        },
        FAILED {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.util.concurrent.Service.State
            public boolean isTerminal() {
                return true;
            }
        };

        /* access modifiers changed from: package-private */
        public abstract boolean isTerminal();
    }
}
