package com.github.catppuccin.jetbrains_icons.settings.views

import com.github.catppuccin.jetbrains_icons.settings.PluginSettingsState
import com.intellij.ui.components.JBCheckBox
import com.intellij.util.ui.FormBuilder
import java.awt.FlowLayout
import javax.swing.JPanel

class SettingsAdditionalSupportView : JPanel() {
  val python = JBCheckBox("Python", PluginSettingsState.instance.pythonSupport)
  val nodes = JBCheckBox("Nodes", PluginSettingsState.instance.nodesSupport)
  val go = JBCheckBox("Go", PluginSettingsState.instance.goSupport)

  init {
    val form =
      FormBuilder.createFormBuilder()
        .addComponent(python)
        .addTooltip("Override the Python plugin icons")
        .addComponent(nodes)
        .addTooltip(
          "Use different shapes and colors for the different filetypes (e.g. Class, Interface, Record, etc.)"
        )
        .addComponent(go)
        .addTooltip("Override the Go plugin icons")
        .panel

    add(form)

    layout = FlowLayout(FlowLayout.LEADING)
  }
}
