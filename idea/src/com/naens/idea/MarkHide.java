// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.naens.idea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class MarkHide extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        Boolean visible = editor.getUserData(Mark.visibleKey);
        visible = visible == null ? null : !visible;
        editor.putUserData(Mark.visibleKey, visible);
        Messages.showMessageDialog(project, Mark.state(editor), "Mark Hide", Messages.getInformationIcon());
        Mark.updateDisplay(editor);
    }
}
