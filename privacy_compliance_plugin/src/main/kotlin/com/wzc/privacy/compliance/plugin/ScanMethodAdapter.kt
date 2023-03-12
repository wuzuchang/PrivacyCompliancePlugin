package com.wzc.privacy.compliance.plugin

import com.wzc.privacy.compliance.plugin.utils.Logger
import com.wzc.privacy.compliance.plugin.utils.ScanSetting
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter


class ScanMethodAdapter(
    api: Int,
    methodVisitor: MethodVisitor?,
    access: Int,
    name: String?,
    descriptor: String?,
    owner: String?,
    insertLog: Boolean,
    logTag: String
) : AdviceAdapter(api, methodVisitor, access, name, descriptor) {
    //当前扫描的方法名
    private var mMethodName: String? = name

    //当前扫描的类名
    private var mTargetClassName: String? = owner

    //方法传入的参数
    private var mValue: Any? = null

    //代码行号
    private var mLine = 0

    //是否在代码运行是插入log
    private var mInsertLog = insertLog

    //插入log时的Tag
    private var mLogTag: String = logTag

    override fun visitLineNumber(line: Int, start: Label?) {
        mLine = line
        super.visitLineNumber(line, start)
    }

    override fun visitLdcInsn(value: Any?) {
        mValue = value
        super.visitLdcInsn(value)
    }

    override fun visitMethodInsn(
        opcodeAndSource: Int,
        owner: String?,
        name: String?,
        descriptor: String?,
        isInterface: Boolean
    ) {
        super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface)
        //静态扫描代码
        complianceCodeScanning(owner, name, descriptor)
    }


    /**
     * 静态代码合规扫描
     *
     * @param owner      类名
     * @param name       方法名
     * @param descriptor 方法描述
     */
    private fun complianceCodeScanning(owner: String?, name: String?, descriptor: String?) {
        ScanSetting.sRiskMethodsList.forEach { riskMethod ->
            if (riskMethod.owner == owner && riskMethod.name == name && riskMethod.descriptor == descriptor && riskMethod.value == mValue) {
                Logger.d("⚠合规检测👉👉👉静态代码扫描检测到 " + mTargetClassName + "->" + mMethodName + "方法的第" + mLine + "行在获取" + riskMethod.output)
                if (mInsertLog) {
                    // 插入日志
                    insertLog(owner, name)
                }
            }
        }
    }

    /**
     * 插入日志
     * ⚠只有debug模式下才会插入日志
     *
     * @param owner 违法合规要求的类名
     * @param name  违法合规要求的方法名
     */
    private fun insertLog(owner: String?, name: String?) {
        val label = Label()
        this.visitLabel(label)
        this.visitLineNumber(mLine + 1, label)
        this.visitLdcInsn(mLogTag)
        val log =
            "⚠合规检测👉👉👉" + mTargetClassName + "->" + mMethodName + "方法的第" + mLine + "行在调用" + owner + "->" + name
        this.visitLdcInsn(log)
        this.visitMethodInsn(
            Opcodes.INVOKESTATIC,
            "android/util/Log",
            "d",
            "(Ljava/lang/String;Ljava/lang/String;)I",
            false
        )
        this.visitInsn(Opcodes.POP)
    }
}
