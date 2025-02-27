package com.github.catppuccin.jetbrains_icons.patcher

import com.github.catppuccin.jetbrains_icons.settings.PluginSettingsState
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.util.IconPathPatcher
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class NodesIconPatcher : IconPathPatcher() {

  private val settingsInstance = PluginSettingsState.instance

  override fun patchPath(path: String, classLoader: ClassLoader?): String? =
    if (settingsInstance.nodesSupport && overrideMap.containsKey(path)) {
      "/jetbrains_icons/icons/${settingsInstance.variant}${overrideMap[path]}"
    } else {
      null
    }

  override fun getContextClassLoader(
    path: String,
    originalClassLoader: ClassLoader?
  ): ClassLoader? = javaClass.classLoader

  companion object {
    private val isInstalledMutex = Mutex()
    private var isInstalled = false

    suspend fun install() =
      isInstalledMutex.withLock {
        if (!isInstalled) {
          isInstalled = true
          IconLoader.installPathPatcher(NodesIconPatcher())
        }
      }

    private val overrideMap: Map<String, String> =
      mapOf(
        "/icons/expui/nodes/annotation.svg" to "_java-annotation.svg",
        "/icons/expui/nodes/interface.svg" to "_java-interface.svg",
        "/icons/expui/nodes/enum.svg" to "_java-enum.svg",
        "/icons/expui/nodes/class.svg" to "_java-class.svg",
        "/icons/expui/nodes/exception.svg" to "_java-exception.svg",
        "/icons/expui/nodes/classAbstract.svg" to "_java-abstract.svg",
      )
  }
}
