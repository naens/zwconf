// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.naens.idea;

import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Key;
import com.intellij.ui.JBColor;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Mark {
    static Key<Integer> beginKey = new Key<Integer>("mark_begin");
    static Key<Integer> endKey = new Key<Integer>("mark_end");
    static Key<Boolean> visibleKey = new Key<Boolean>("mark_visible");
    static Key<List<RangeHighlighter>> selectionKey = new Key<List<RangeHighlighter>>("selection_highlight");

    static void msg(String message, String title) {
        Messages.showInfoMessage(message, title);
    }

    static void msg(String message) {
        Messages.showInfoMessage(message, "title");
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

    static void deleteSelectionHighlighters(Editor editor) {
        MarkupModel markupModel = editor.getMarkupModel();
        List<RangeHighlighter> highlighters = editor.getUserData(Mark.selectionKey);
        if (highlighters == null) {
            return;
        }
        for (RangeHighlighter highlighter: highlighters) {
            markupModel.removeHighlighter(highlighter);
        }
        editor.putUserData(Mark.selectionKey, null);
    }

    static void updateDisplay(Editor editor) {
        deleteSelectionHighlighters(editor);
        Boolean visible = editor.getUserData(Mark.visibleKey);
        Integer markBegin = editor.getUserData(Mark.beginKey);
        Integer markEnd = editor.getUserData(Mark.endKey);
        int colBegin = 0;
        int colEnd = 0;
        boolean bothVisible = visible && markBegin != null && markEnd != null;
        boolean columnMode = editor.isColumnMode();
        if (bothVisible) {
            colBegin = editor.offsetToXY(markBegin).y;
            colEnd = editor.offsetToXY(markEnd).y;
        }
        boolean showSelection = bothVisible && (columnMode ? (colBegin < colEnd) : (markBegin < markEnd));
        if (visible != null && visible.booleanValue() && (markBegin != null || markEnd != null)) {
            List<RangeHighlighter> highlighters = new LinkedList<RangeHighlighter>();
            if (showSelection) {
                if (columnMode) {
                    /* TODO: column mode highlight */
                } else {
                    MarkupModel markupModel = editor.getMarkupModel();
                    TextAttributes textAttributes = new TextAttributes();
                    textAttributes.setBackgroundColor(JBColor.lightGray);
                    RangeHighlighter rangeHighlighter = markupModel.addRangeHighlighter(markBegin, markEnd, 2579, textAttributes,
                                                                                        HighlighterTargetArea.EXACT_RANGE);
                    highlighters.add(rangeHighlighter);
                }
            } else {
                /* TODO: display separate <B> and <K> */
            }
            editor.putUserData(Mark.selectionKey, highlighters);
        }
    }
}
