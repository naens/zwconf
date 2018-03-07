// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.naens.idea;// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.UserDataHolder;

public class MarkBegin extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();

        Caret primaryCaret = caretModel.getPrimaryCaret();
        Boolean visible = editor.getUserData(Mark.visibleKey);
        Integer markBegin = editor.getUserData(Mark.beginKey);
        Integer markEnd = editor.getUserData(Mark.endKey);
        int offset = primaryCaret.getOffset();
        if (markBegin == null) {
            markBegin = primaryCaret.getOffset();
        } else if (markBegin == offset) {
            markBegin = null;
        } else {
            markBegin = primaryCaret.getOffset();
        }
        if (markBegin == null && markEnd == null) {
            visible = null;
        } else {
            visible = true;
        }
        editor.putUserData(Mark.visibleKey, visible);
        editor.putUserData(Mark.beginKey, markBegin);
        Messages.showMessageDialog(project, Mark.state(editor), "Mark Begin", Messages.getInformationIcon());
        Mark.updateDisplay(editor);
    }
}
