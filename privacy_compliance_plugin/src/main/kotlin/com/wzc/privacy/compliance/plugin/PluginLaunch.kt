package com.wzc.privacy.compliance.plugin

import com.android.build.api.instrumentation.*
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.wzc.privacy.compliance.plugin.model.PrivacyExtensions
import com.wzc.privacy.compliance.plugin.utils.Logger
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes

class PluginLaunch : Plugin<Project> {

    private val EXTENSIONS_NAME = "compliance"
    override fun apply(project: Project) {

        val app = project.plugins.hasPlugin(AppPlugin::class.java)
        val library = project.plugins.hasPlugin(LibraryPlugin::class.java)
        project.extensions.create(EXTENSIONS_NAME, PrivacyExtensions::class.java)
        val androidComponentsExtension =
            project.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponentsExtension.onVariants { variant ->
            val privacyExtensions =
                project.extensions.getByName(EXTENSIONS_NAME) as PrivacyExtensions
            variant.instrumentation.apply {
                if (app) {
                    transformClassesWith(
                        ExampleClassVisitorFactory::class.java, InstrumentationScope.ALL
                    ) { params ->
                        // parameters configuration
                        params.packageList.set(privacyExtensions.packageNameList)
                        params.debug.set("debug" == variant.buildType)
                        params.insertLog.set(privacyExtensions.insertLog)
                        params.logTag.set(privacyExtensions.logTag)
                    }
                } else if (library) {
                    transformClassesWith(
                        ExampleClassVisitorFactory::class.java, InstrumentationScope.PROJECT
                    ) { params ->
                        // parameters configuration
                        params.packageList.set(privacyExtensions.packageNameList)
                        params.debug.set("debug" == variant.buildType)
                        params.insertLog.set(privacyExtensions.insertLog)
                        params.logTag.set(privacyExtensions.logTag)
                    }
                }
                setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES)
            }
        }
    }

    interface ParametersImpl : InstrumentationParameters {
        @get:Internal
        val packageList: ListProperty<String>

        @get:Internal
        val debug: Property<Boolean>

        @get:Internal
        val insertLog: Property<Boolean>

        @get:Internal
        val logTag: Property<String>
    }

    abstract class ExampleClassVisitorFactory : AsmClassVisitorFactory<ParametersImpl> {

        override fun createClassVisitor(
            classContext: ClassContext, nextClassVisitor: ClassVisitor
        ): ClassVisitor {
            val insertLog = parameters.get().insertLog.get()
            val logTag = parameters.get().logTag.get()
            return ScanClassVisitor(Opcodes.ASM9, nextClassVisitor, insertLog, logTag)
        }

        override fun isInstrumentable(classData: ClassData): Boolean {
            if (!parameters.get().debug.get()) {
                return false
            }
            parameters.get().packageList.get().forEach {
                return classData.className.startsWith(it)
            }
            return false
        }
    }
}