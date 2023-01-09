package com.github.unidbg.arm.backend.hypervisor.arm64;

import capstone.api.Disassembler;
import capstone.api.Instruction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleMemorySizeDetector implements MemorySizeDetector {

    private static final Log log = LogFactory.getLog(SimpleMemorySizeDetector.class);

    @Override
    public int detectReadSize(Disassembler disassembler, byte[] code, long pc) {
        Instruction insn = disassembler.disasm(code, pc, 1)[0];
        int size = 0;
        switch (insn.getMnemonic()) {
            case "ldursb":
                size = 1;
                break;
            case "ldursh":
                size = 2;
                break;
            case "ldr":
            case "ldxr":
            case "ldur":
                if (insn.getOpStr().startsWith("w")) {
                    size = 4;
                    break;
                }
                if (insn.getOpStr().startsWith("x")) {
                    size = 8;
                    break;
                }
            default:
                log.info("onHit: insn=" + insn);
                break;
        }
        return size;
    }

}
