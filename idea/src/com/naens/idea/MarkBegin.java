// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.naens.idea;// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.UserDataHolder;

public class MarkBegin extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();

        Caret primaryCaret = caretModel.getPrimaryCaret();
        Caret markBegin = editor.getUserData(Mark.beginKey);
        int offset = primaryCaret.getOffset();
        if (markBegin == null) {
            markBegin = caretModel.addCaret(primaryCaret.getVisualPosition(), false);
            editor.putUserData(Mark.beginKey, markBegin);
        } else if (markBegin.getOffset() == offset) {
            editor.putUserData(Mark.beginKey, null);
            caretModel.removeCaret(markBegin);
        } else {
            markBegin.moveToOffset(offset);
        }
        Mark.updateDisplay(editor);
    }
}
