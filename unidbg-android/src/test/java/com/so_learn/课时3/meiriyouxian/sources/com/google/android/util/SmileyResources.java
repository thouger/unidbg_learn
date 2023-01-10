package com.google.android.util;

import com.google.android.util.AbstractMessageParser;
import java.util.HashMap;
import java.util.Set;

/*  JADX ERROR: NullPointerException in pass: ExtractFieldInit
    java.lang.NullPointerException
    */
public class SmileyResources implements AbstractMessageParser.Resources {
    private HashMap<String, Integer> mSmileyToRes;
    private final AbstractMessageParser.TrieNode smileys;

    /*  JADX ERROR: Failed to decode insn: 0x0008: UNKNOWN(0x41E8), method: com.google.android.util.SmileyResources.<init>(java.lang.String[], int[]):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0008: UNKNOWN(0x41E8)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:44)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:80)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:39)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:199)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:270)
        	at jadx.core.ProcessClass.process(ProcessClass.java:53)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:85)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:261)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:226)
        */
    /*  JADX ERROR: Failed to decode insn: 0x000F: UNKNOWN(0x41E8), method: com.google.android.util.SmileyResources.<init>(java.lang.String[], int[]):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000F: UNKNOWN(0x41E8)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:44)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:80)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:39)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:199)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:270)
        	at jadx.core.ProcessClass.process(ProcessClass.java:53)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:85)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:261)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:226)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0015: UNKNOWN(0x41E5), method: com.google.android.util.SmileyResources.<init>(java.lang.String[], int[]):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0015: UNKNOWN(0x41E5)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:44)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:80)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:39)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:199)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:270)
        	at jadx.core.ProcessClass.process(ProcessClass.java:53)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:85)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:261)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:226)
        */
    /*  JADX ERROR: Failed to decode insn: 0x001F: UNKNOWN(0x41E5), method: com.google.android.util.SmileyResources.<init>(java.lang.String[], int[]):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x001F: UNKNOWN(0x41E5)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:44)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:80)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:39)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:199)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:270)
        	at jadx.core.ProcessClass.process(ProcessClass.java:53)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:85)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:261)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:226)
        */
    public SmileyResources(java.lang.String[] r5, int[] r6) {
        /*
            r4 = this;
            r4.<init>()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            // decode failed: Unknown instruction: '0x0008: UNKNOWN(0x41E8)'
            r0 = r290
            int r20 = r112 << 16
            r218 = move-result
            r0 = r0
            // decode failed: Unknown instruction: '0x000F: UNKNOWN(0x41E8)'
            r0 = move-result
            r0 = 0
            int r1 = r5.length
            if (r0 >= r1) goto L_0x002f
            // decode failed: Unknown instruction: '0x0015: UNKNOWN(0x41E5)'
            r0 = move-result
            r2 = r5[r0]
            java.lang.String r3 = ""
            com.google.android.util.AbstractMessageParser.TrieNode.addToTrie(r1, r2, r3)
            // decode failed: Unknown instruction: '0x001F: UNKNOWN(0x41E5)'
            r0 = r582
            r0 = r836
            r4209 = r56305
            r780 = r12521
            r0 = 0
            int r3 = r0.length
            int r0 = r0 + 1
            goto L_0x0012
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.util.SmileyResources.<init>(java.lang.String[], int[]):void");
    }

    /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0x21E5), method: com.google.android.util.SmileyResources.getSmileyRes(java.lang.String):int
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0x21E5)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:44)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:80)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:39)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:199)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:270)
        	at jadx.core.ProcessClass.process(ProcessClass.java:53)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:85)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:261)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:226)
        */
    /*  JADX ERROR: Failed to decode insn: 0x000C: UNKNOWN(0x10E9), method: com.google.android.util.SmileyResources.getSmileyRes(java.lang.String):int
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x000C: UNKNOWN(0x10E9)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:44)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:80)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:39)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:199)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:270)
        	at jadx.core.ProcessClass.process(ProcessClass.java:53)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:85)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:261)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:226)
        */
    public int getSmileyRes(java.lang.String r3) {
        /*
            r2 = this;
            // decode failed: Unknown instruction: '0x0000: UNKNOWN(0x21E5)'
            r0 = r8425
            return r0
            int r0 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 != 0) goto L_0x000c
            r1 = -1
            return r1
            // decode failed: Unknown instruction: '0x000C: UNKNOWN(0x10E9)'
            return
            r1 = move-result
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.util.SmileyResources.getSmileyRes(java.lang.String):int");
    }

    /*  JADX ERROR: Failed to decode insn: 0x0000: UNKNOWN(0x10E5), method: com.google.android.util.SmileyResources.getSmileys():com.google.android.util.AbstractMessageParser$TrieNode
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0000: UNKNOWN(0x10E5)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:44)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:80)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:39)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:199)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:270)
        	at jadx.core.ProcessClass.process(ProcessClass.java:53)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:85)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:261)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:226)
        */
    @Override // com.google.android.util.AbstractMessageParser.Resources
    public com.google.android.util.AbstractMessageParser.TrieNode getSmileys() {
        /*
            r1 = this;
            // decode failed: Unknown instruction: '0x0000: UNKNOWN(0x10E5)'
            r0 = move-result
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.util.SmileyResources.getSmileys():com.google.android.util.AbstractMessageParser$TrieNode");
    }

    @Override // com.google.android.util.AbstractMessageParser.Resources
    public Set<String> getSchemes() {
        return null;
    }

    @Override // com.google.android.util.AbstractMessageParser.Resources
    public AbstractMessageParser.TrieNode getDomainSuffixes() {
        return null;
    }

    @Override // com.google.android.util.AbstractMessageParser.Resources
    public AbstractMessageParser.TrieNode getAcronyms() {
        return null;
    }
}
