// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.naens.idea;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Key;

public class Mark {
    static Key<Integer> beginKey = new Key<Integer>("mark_begin");
    static Key<Integer> endKey = new Key<Integer>("mark_end");
    static Key<Boolean> visibleKey = new Key<Boolean>("mark_visible");

    static void msg(String message, String title) {
        Messages.showInfoMessage(message, title);
    }

    static String state(Editor editor) {
        CaretModel caretModel = editor.getCaretModel();
        Caret primaryCaret = caretModel.getPrimaryCaret();
        Integer markBegin = editor.getUserData(Mark.beginKey);
        Integer markEnd = editor.getUserData(Mark.endKey);
        Boolean visible = editor.getUserData(Mark.visibleKey);
        int primaryOffset = primaryCaret.getOffset();

        String visString = visible == null ? "<vis:null>" : (visible ? "<visible>" : "<hidden>");
        String mbString = markBegin == null ? "<mb:null>" : String.format("<mb:%d>", markBegin);
        String meString = markEnd == null ? "<me:null>" : String.format("<me:%d>", markEnd);
        return String.format("%s\n%s\n%s", visString, mbString, meString);
    }

    static void updateDisplay(Editor editor) {
        Boolean visible = editor.getUserData(visibleKey);
        if (visible != null && visible.booleanValue()) {
            /* TODO: display selection OR mark positions if defined */
        }
    }
}
