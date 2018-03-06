// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.naens.idea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;

public class MarkEnd extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        CaretModel caretModel = editor.getCaretModel();

        Caret primaryCaret = caretModel.getPrimaryCaret();
        Caret markEnd = editor.getUserData(Mark.endKey);
        int offset = primaryCaret.getOffset();
        if (markEnd == null) {
            markEnd = caretModel.addCaret(primaryCaret.getVisualPosition(), false);
            editor.putUserData(Mark.endKey, markEnd);
        } else if (markEnd.getOffset() == offset) {
            editor.putUserData(Mark.endKey, null);
            caretModel.removeCaret(markEnd);
        } else {
            markEnd.moveToOffset(offset);
        }
        Mark.updateDisplay(editor);
    }
}
