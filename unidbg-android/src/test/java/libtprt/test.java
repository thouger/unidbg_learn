package libtprt;

import capstone.Capstone;
import capstone.api.Instruction;
import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.arm.backend.Backend;
import com.github.unidbg.arm.backend.CodeHook;
import com.github.unidbg.arm.backend.UnHook;
import com.github.unidbg.arm.backend.Unicorn2Factory;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.memory.Memory;
import keystone.Keystone;
import keystone.KeystoneArchitecture;
import keystone.KeystoneEncoded;
import keystone.KeystoneMode;
import unicorn.Arm64Const;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

public class test {
    private AndroidEmulator emulator;
    private VM vm;
    private DalvikModule dm;
    private Module module;
    private long start= 0x5E388;
    private long end = 0x5E7A0;
    private Stack<InsAndCtx> instructions;
    private List<PatchIns> patchs;
    private static final String inName = "unidbg-android/src/test/resources/libtprt.so";
    private static final String outName = "unidbg-android/src/test/resources/libtprt2.so";
    private static final long dispatcher = 0x5E46C;
    private static final long toend = 0x5E6BC;
    //记录真实块
    private List<TrueBlock>tbs;
    //记录条件块
    private List<selectBr> sbs ;
    //记录索引顺序
    private List<Long> indexOrder;

    public test()
    {
        instructions = new Stack<>();
        patchs = new ArrayList<>();
        tbs = new ArrayList<>();
        sbs = new ArrayList<>();
        indexOrder = new ArrayList<>();
        //创建模拟器
        emulator = AndroidEmulatorBuilder
                .for64Bit()
                .addBackendFactory(new Unicorn2Factory(true))
                .setProcessName("com.example.antiollvm")
                .build();
        Memory memory = emulator.getMemory();
        //设置andorid系统库版本
        memory.setLibraryResolver(new AndroidResolver(23));
        //创建虚拟机
        vm = emulator.createDalvikVM();
        vm.setVerbose(true);
        dm = vm.loadLibrary(new File(inName), false);
        module = dm.getModule();
        processFlt();
    }

    public void logIns()
    {
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user)  {
                Capstone capstone = new Capstone(Capstone.CS_ARCH_ARM64,Capstone.CS_MODE_ARM);
                byte[] bytes = emulator.getBackend().mem_read(address, 4);
                Instruction[] disasm = capstone.disasm(bytes, 0);
                System.out.printf("%x:%s %s\n",address-module.base ,disasm[0].getMnemonic(),disasm[0].getOpStr());
            }

            @Override
            public void onAttach(UnHook unHook) {

            }

            @Override
            public void detach() {

            }
        }, module.base+start, module.base+end, null);
    }


    public void callJniOnload()
    {
        dm.callJNI_OnLoad(emulator);
        // patch();
    }

    public static void main(String[] args) {
        test ao = new test();
        ao.processBr();
        ao.callJniOnload();
//        ao.reorderblock();
        ao.patch();
    }

    //保存指令和寄存器环境类：
    class InsAndCtx
    {
        long addr;
        Instruction ins;
        List<Number> regs;

        public long getAddr() {
            return addr;
        }

        public void setAddr(long addr) {
            this.addr = addr;
        }

        public void setIns(Instruction ins) {
            this.ins = ins;
        }

        public Instruction getIns() {
            return ins;
        }

        public void setRegs(List<Number> regs) {
            this.regs = regs;
        }

        public List<Number> getRegs() {
            return regs;
        }
    }

    //patch类
    class PatchIns{
        long addr;//patch 地址
        String ins;//patch的指令

        public long getAddr() {
            return addr;
        }

        public void setAddr(long addr) {
            this.addr = addr;
        }

        public String getIns() {
            return ins;
        }

        public void setIns(String ins) {
            this.ins = ins;
        }
    }

    public List<Number> saveRegs(Backend bk)
    {
        List<Number> nb = new ArrayList<>();
        for(int i=0;i<29;i++)
        {
            nb.add(bk.reg_read(i+ Arm64Const.UC_ARM64_REG_X0));
        }
        nb.add(bk.reg_read(Arm64Const.UC_ARM64_REG_FP));
        nb.add(bk.reg_read(Arm64Const.UC_ARM64_REG_LR));
        return nb;
    }
    public Number getRegValue(String reg,List<Number> regsaved)
    {
        if(reg.equals("xzr"))
        {
            return 0;
        }
        return regsaved.get(Integer.parseInt(reg.substring(1)));
    }

    public long readInt64(Backend bk,long addr)
    {
        byte[] bytes = bk.mem_read(addr, 8);
        long res = 0;
        for (int i=0;i<bytes.length;i++)
        {
            res =((bytes[i]&0xffL) << (8*i)) + res;
        }
        return res;
    }

    public long StringToLong(String bt)
    {
        return Long.parseLong(bt,16);
    }

    public void patch()
    {
        try {
            File f = new File(inName);
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();
            for(PatchIns pi:patchs)
            {
                System.out.println("procrss addr:"+Integer.toHexString((int) pi.addr)+",code:"+pi.getIns());
                Keystone ks = new Keystone(KeystoneArchitecture.Arm64, KeystoneMode.LittleEndian);
                KeystoneEncoded assemble = ks.assemble(pi.getIns());
                for(int i=0;i<assemble.getMachineCode().length;i++)
                {
                    data[(int) pi.addr+i] = assemble.getMachineCode()[i];
                }
            }
            File fo = new File(outName);
            FileOutputStream fos = new FileOutputStream(fo);
            fos.write(data);
            fos.flush();
            fos.close();
            System.out.println("finish");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    class selectBr
    {
        long insaddr;
        long trueindex;
        long falseindex;
        String cond;
        public String getCond() {
            return cond;
        }
        public void setCond(String cond) {
            this.cond = cond;
        }
        public long getInsaddr() {
            return insaddr;
        }
        public void setInsaddr(long insaddr) {
            this.insaddr = insaddr;
        }
        public long getTrueindex() {
            return trueindex;
        }
        public void setTrueindex(long trueindex) {
            this.trueindex = trueindex;
        }
        public long getFalseindex() {
            return falseindex;
        }
        public void setFalseindex(long falseindex) {
            this.falseindex = falseindex;
        }
    }

    class TrueBlock{

        long index;
        long startAddr;

        public TrueBlock(){}
        public TrueBlock(long l,long s)
        {
            index = l;
            startAddr = s;
        }

        public long getIndex() {
            return index;
        }

        public void setIndex(long index) {
            this.index = index;
        }

        public long getStartAddr() {
            return startAddr;
        }

        public void setStartAddr(long startAddr) {
            this.startAddr = startAddr;
        }
    }

    public long strToLong(String hexString)
    {
        BigInteger bi = new BigInteger(hexString,16);
        return bi.longValue();
    }

    public long getLongFromOpConst(String op)
    {
        if(op.startsWith("#0x"))
        {
            return strToLong(op.substring(3));
        }
        else if(op.startsWith("#"))
        {
            return strToLong(op.substring(1));
        }
        else
        {
            return 0;
        }
    }
    public void processFlt()
    {
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                Capstone capstone = new Capstone(Capstone.CS_ARCH_ARM64,Capstone.CS_MODE_ARM);
                byte[] bytes = emulator.getBackend().mem_read(address, 4);
                Instruction[] disasm = capstone.disasm(bytes, 0);
                InsAndCtx iac = new InsAndCtx();
                iac.setIns(disasm[0]);
                iac.setRegs(saveRegs(backend));
                iac.setAddr(address);
                instructions.add(iac);
                do_processflt();
            }

            @Override
            public void onAttach(UnHook unHook) {
                System.out.println("attach");
            }

            @Override
            public void detach() {
                System.out.println("detach");
            }
        },module.base+start, module.base+end, null);
    }
    public void do_processflt()
    {
        if(instructions.empty())
        {
            return;
        }
        Instruction ins = instructions.peek().getIns();
        if(instructions.peek().getAddr() - module.base == dispatcher)
        {
            indexOrder.add(getRegValue("x8",instructions.peek().getRegs()).longValue());
        }
        if(ins.getMnemonic().toLowerCase(Locale.ROOT).equals("b.eq")) {
            InsAndCtx beq = instructions.peek();
            //等于跳转，检查是否为cmp x8,
            while (true)
            {
                if(instructions.empty())
                {
                    break;
                }
                instructions.pop();
                ins = instructions.peek().getIns();
                if(ins.getMnemonic().toLowerCase(Locale.ROOT).equals("cmp"))
                {
                    String[] sp = ins.getOpStr().toLowerCase(Locale.ROOT).split(",");
                    if(sp[0].equals("w8"))
                    {
                        //找到一个真实块
                        TrueBlock tb = new TrueBlock();
                        long regValue = getRegValue(sp[1].trim(), instructions.peek().getRegs()).longValue();
                        long targetAddr = 0;
                        String offset = beq.getIns().getOpStr().toLowerCase(Locale.ROOT);
                        long offsetvalue = getLongFromOpConst(offset);
                        targetAddr = beq.getAddr() + offsetvalue - module.base;
                        tb.setIndex(regValue);
                        tb.setStartAddr(targetAddr);
                        tbs.add(tb);
                        break;
                    }
                }
            }
        }
        //处理分支块
        if(ins.getMnemonic().toLowerCase(Locale.ROOT).equals("b"))
        {
            long offset = getLongFromOpConst(ins.getOpStr());
            if(offset != 0)
            {
                long target = offset + instructions.peek().getAddr() - module.base;
                //直接跳向主发生器
                if(target == dispatcher)
                {
                    instructions.pop();
                    ins = instructions.peek().getIns();
                    if(ins.getMnemonic().toLowerCase(Locale.ROOT).equals("csel"))
                    {
                        String[] sp = ins.getOpStr().toLowerCase(Locale.ROOT).split(",");
                        if(sp[0].trim().equals("w8"))
                        {
                            String cond = sp[3].trim();
                            String reg1 = sp[1].trim();
                            String reg2 = sp[2].trim();
                            selectBr sb = new selectBr();
                            sb.setInsaddr(instructions.peek().getAddr() - module.base);
                            sb.setCond(cond);
                            sb.setTrueindex(getRegValue(reg1,instructions.peek().getRegs()).longValue());
                            sb.setFalseindex(getRegValue(reg2,instructions.peek().getRegs()).longValue());
                            sbs.add(sb);
                        }
                    }
                }
            }
        }
    }
    private long getIndexAddr(long index)
    {
        for(TrueBlock tb:tbs)
        {
            if(tb.getIndex() == index)
            {
                return tb.getStartAddr();
            }
        }
        System.out.printf("not found addr for index:%x,result may be wrong!\n",index);
        return -1;
    }

    private void reorderblock()
    {
        tbs.add(new TrueBlock(0x6e142ec8L,0x5E6B0));
        tbs.add(new TrueBlock(0xf07b1447L,0x5E608));
        tbs.add(new TrueBlock(0x5d7b4e5aL,0x5E5E8));
        tbs.add(new TrueBlock(0x5ad22f2fL,0x5E628));

        for(TrueBlock tb:tbs)
        {
            System.out.printf("true block index %x,addr %x\n",tb.getIndex(),tb.getStartAddr());
        }
        for(selectBr sb:sbs)
        {
            System.out.printf("select block inds addr: %x,cond: %s . true for %x,false for %x\n",sb.getInsaddr(),sb.getCond(),sb.getTrueindex(),sb.getFalseindex());
        }
        for(long l:indexOrder)
        {
            System.out.printf("index order:%x\n",l);
        }

        for(selectBr sb:sbs)
        {
            String ins1 = "b" + sb.getCond() + " 0x"+Integer.toHexString((int) (getIndexAddr(sb.getTrueindex()) -  sb.getInsaddr()));
            String ins2 = "b 0x"+ Integer.toHexString((int) (getIndexAddr(sb.getFalseindex())-sb.getInsaddr()-4));
            PatchIns pi1 = new PatchIns();
            pi1.setIns(ins1);
            pi1.setAddr(sb.getInsaddr());
            PatchIns pi2 = new PatchIns();
            pi2.setIns(ins2);
            pi2.setAddr(sb.getInsaddr() + 4);
            patchs.add(pi1);
            patchs.add(pi2);
        }

        PatchIns pi = new PatchIns();
        pi.setAddr(dispatcher);
        pi.setIns("b 0x"+Integer.toHexString((int) (getIndexAddr(0x22f0693f)-dispatcher)));
        patchs.add(pi);
        PatchIns pie = new PatchIns();
        pie.setAddr(toend);
        pie.setIns("b 0x"+Integer.toHexString((int) (getIndexAddr(0x83a9af56L)- toend)));
        patchs.add(pie);
        PatchIns pie1 = new PatchIns();
        pie1.setAddr(0x5E674L);
        pie1.setIns("b 0x"+Integer.toHexString((int) (getIndexAddr(0x83a9af56L) - 0x5E674L)));
        patchs.add(pie1);
    }

    public void do_processbr()
    {
        Instruction ins = instructions.peek().getIns();

//        if(instructions.peek().getAddr() - module.base == 0x5E770)
//        {
//            emulator.getBackend().reg_write(Arm64Const.UC_ARM64_REG_W8,0);
//        }

//        if(instructions.peek().getAddr() - module.base == 0x5e5ac)
//        {
//            emulator.getBackend().reg_write(Arm64Const.UC_ARM64_REG_W0,1);
//        }


        if(ins.getMnemonic().equals("br") && ins.getOpStr().equals("x9"))
        {
            boolean finish = false;
            long base = -1;
            long listoffset = -1;
            long cond1 = -1;
            long cond2 = -1;
            String cond = "";
            long addinstaddr = -1;
            long brinsaddr = instructions.peek().getAddr() - module.base;
            long selectaddr = -1;
            long ldaaddr = -1;

            try {
                while (!finish && !instructions.empty())
                {
                    instructions.pop();
                    ins = instructions.peek().getIns();
                    if(ins.getMnemonic().toLowerCase(Locale.ROOT).equals("add"))
                    {
                        String[] split = ins.getOpStr().split(",");
                        if(split.length == 3)
                        {
                            if(split[0].toLowerCase(Locale.ROOT).trim().equals("x9") && split[1].toLowerCase(Locale.ROOT).trim().equals("x9"))
                            {
                                String reg = split[2].trim().toLowerCase(Locale.ROOT);
                                base = getRegValue(reg,instructions.peek().getRegs()).longValue();
                                addinstaddr = instructions.peek().getAddr() - module.base;
                            }
                            else {
                                break;
                            }
                        }
                        else
                        {
                            break;
                        }
                    }

                    if(ins.getMnemonic().toLowerCase(Locale.ROOT).equals("ldr"))
                    {
                        String[] sp = ins.getOpStr().toLowerCase().split(",");
                        if(sp.length == 3)
                        {
                            if(sp[0].trim().toLowerCase(Locale.ROOT).equals("x9") && sp[2].trim().toLowerCase(Locale.ROOT).equals("x9]"))
                            {
                                String reg = sp[1].toLowerCase(Locale.ROOT).trim().substring(1);
                                listoffset = getRegValue(reg,instructions.peek().getRegs()).longValue()-module.base;
                                ldaaddr =  instructions.peek().getAddr()- module.base;
                            }
                        }
                    }

                    if(ins.getMnemonic().trim().toLowerCase(Locale.ROOT).equals("csel"))
                    {
                        String[] sp = ins.getOpStr().toLowerCase(Locale.ROOT).split(",");
                        if(sp.length == 4)
                        {
                            cond = sp[3].trim();
                            if(sp[0].trim().equals("x9"))
                            {
                                String reg1 = sp[1].trim();
                                String reg2 = sp[2].trim();
                                cond1 = getRegValue(reg1,instructions.peek().getRegs()).longValue();
                                cond2 = getRegValue(reg2,instructions.peek().getRegs()).longValue();
                                selectaddr = instructions.peek().getAddr() - module.base;
                            }
                        }
                    }

                    if(ins.getMnemonic().trim().toLowerCase(Locale.ROOT).equals("cmp"))
                    {
                        if(base == -1 || listoffset == -1 || cond1 == -1 || cond2 == -1 || cond.equals("") || addinstaddr == -1 || ldaaddr == -1 || selectaddr == -1)
                        {
                            break;
                        }
                        else
                        {
                            long offset1 = base + readInt64(emulator.getBackend(), module.base+listoffset+cond1) - module.base;
                            long offset2 = base + readInt64(emulator.getBackend(),module.base+listoffset+cond2) - module.base;
                            if( brinsaddr - addinstaddr != 4)
                            {
                                System.out.println("add ins and br ins gap more than 4 size,may make mistake");
                            }
                            String condBr = "b"+cond.toLowerCase(Locale.ROOT) + " 0x"+ Integer.toHexString((int) (offset1 - addinstaddr));
                            String br = "b 0x" + Integer.toHexString((int)(offset2 - brinsaddr));
                            PatchIns pi1 = new PatchIns();
                            pi1.setAddr(addinstaddr);
                            pi1.setIns(condBr);
                            patchs.add(pi1);
                            PatchIns pi2 = new PatchIns();
                            pi2.setAddr(brinsaddr);
                            pi2.setIns(br);
                            patchs.add(pi2);
                            PatchIns pi3 = new PatchIns();
                            pi3.setAddr(selectaddr);
                            pi3.setIns("nop");
                            patchs.add(pi3);
                            PatchIns pi4 = new PatchIns();
                            pi4.setAddr(ldaaddr);
                            pi4.setIns("nop");
                            patchs.add(pi4);
                            finish = true;
                        }
                    }
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void processBr()
    {
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                Capstone capstone = new Capstone(Capstone.CS_ARCH_ARM64,Capstone.CS_MODE_ARM);
                byte[] bytes = emulator.getBackend().mem_read(address, 4);
                Instruction[] disasm = capstone.disasm(bytes, 0);
                InsAndCtx iac = new InsAndCtx();
                iac.setIns(disasm[0]);
                iac.setRegs(saveRegs(backend));
                iac.setAddr(address);
                instructions.push(iac);
                do_processbr();
            }

            @Override
            public void onAttach(UnHook unHook) {
                System.out.println("attach");
            }

            @Override
            public void detach() {
                System.out.println("detach");
            }
        },module.base+start, module.base+end,null);
    }

}
