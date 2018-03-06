// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.naens.idea;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.Key;

public class Mark {
    static Key<Caret> beginKey = new Key<Caret>("mark_begin");
    static Key<Caret> endKey = new Key<Caret>("mark_end");
    static Key<Boolean> visibleKey = new Key<Boolean>("mark_visible");

    static void updateDisplay(Editor editor) {
        Boolean visible = editor.getUserData(visibleKey);
        if (visible != null && visible.booleanValue()) {
            /* TODO: display selection OR mark positions if defined */
        }
    }
}
