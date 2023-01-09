package com.github.unidbg.listener;

import capstone.Capstone;
import capstone.api.Instruction;
import com.github.unidbg.Emulator;

public interface TraceCodeListener {

    void onInstruction(Emulator<?> emulator, long address, Instruction insn);

    void onInstruction(Emulator<?> emulator, long address, Capstone.CsInsn insn);
}
